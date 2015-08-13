/*
 * Copyright (C) 2015 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This code was inspired by the ZIPURLProtocol sources at https://github.com/oysterbooks/ZIPURLProtocol
 */

#import <Foundation/Foundation.h>
#if TARGET_OS_IPHONE
# import <MobileCoreServices/MobileCoreServices.h>
#else
# import <CoreServices/CoreServices.h>
#endif
#import <objc/runtime.h>
#include "minizip/unzip.h"

#define BUF_SIZE 8192

NSString *const JARURLScheme = @"jar";
NSString *const defaultMimeType = @"application/octet-stream";

@implementation NSURL (JARPURLProtocolAdditions)

// We swizzle the initWithString:relativeToURL: initializer of NSURL to be
// able to catch Java style jar: URLs (starts with jar:file:) and transform
// them into jar URLs with proper host. The host in our URLs are the file: URL
// part of the Java jar URL base64 encoded. We must have a host to make
// UIWebView happy. Without it it will not be able to resolve relative URLs.

- (instancetype)swizzled_initWithString:(NSString *)URLString relativeToURL:(NSURL *)baseURL {
    if ([URLString hasPrefix:@"jar:file:"]) {
        NSArray *parts = [[URLString substringFromIndex:[JARURLScheme length] + 1] componentsSeparatedByString:@"!/"];
        if ([parts count] == 2) {
            return [self initWithJARFileURL:[NSURL URLWithString:[parts objectAtIndex:0]] 
                    entryPath:[[parts objectAtIndex:1] stringByReplacingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
        }
    }
    return [self swizzled_initWithString:URLString relativeToURL:baseURL];
}

- (instancetype)initWithJARFileURL:(NSURL *)jarURL entryPath:(NSString *)entryPath {
    NSString *jarURLString = [jarURL absoluteString];
    NSData *jarURLData = [jarURLString dataUsingEncoding:NSUTF8StringEncoding];
    NSString *host = [[jarURLData base64EncodedStringWithOptions:0] stringByReplacingOccurrencesOfString:@"=" withString:@"%3D"];
    if ([entryPath length] == 0 || [entryPath characterAtIndex:0] != '/') {
        entryPath = [NSString stringWithFormat:@"/%@", entryPath];
    }
    return [self initWithScheme:JARURLScheme host:host path:entryPath];
}

- (NSURL *)JARFileURL {
    NSString *host = [[self host] stringByReplacingOccurrencesOfString:@"%3D" withString:@"="];
    NSData *jarURLData = [[NSData alloc] initWithBase64EncodedString:host options:0];
    NSString *jarURLString = [[NSString alloc] initWithData:jarURLData encoding:NSUTF8StringEncoding];
    NSURL *jarURL = [NSURL URLWithString:jarURLString];
    [jarURLData release];
    [jarURLString release];
    return jarURL;
}
@end

@interface JARURLProtocol : NSURLProtocol
@end

@implementation JARURLProtocol

+ (BOOL)canInitWithRequest:(NSURLRequest *)request {
    return [[[request URL] scheme] caseInsensitiveCompare:JARURLScheme] == NSOrderedSame;
}

+ (NSURLRequest *)canonicalRequestForRequest:(NSURLRequest *)request {
    return request;
}

- (void)reportError:(NSInteger)code {
    [[self client] URLProtocol:self didFailWithError:[NSError errorWithDomain:NSURLErrorDomain code:code userInfo:nil]];
}

- (void)startLoading {

    unzFile f = NULL;
    NSURL *requestURL = [[self request] URL];
    NSURL *jarFileURL = [requestURL JARFileURL];
    if (!jarFileURL || ![jarFileURL isFileURL]) {
        [self reportError:NSURLErrorBadURL];
        return;
    }
    NSString *entryPath = [requestURL path];
    if ([entryPath length] == 0) {
        [self reportError:NSURLErrorBadURL];
        return;
    }
    if ([entryPath characterAtIndex:0] == '/') {
        entryPath = [entryPath substringFromIndex:1];
    }

    f = unzOpen64([jarFileURL.path UTF8String]);
    if (!f) {
        [self reportError:NSURLErrorFileDoesNotExist];
        return;
    }

    NSInteger errorCode = 0;

    if (UNZ_OK == unzLocateFile(f, [entryPath UTF8String], NULL)) {
        unz_file_info64 info;
        if (UNZ_OK == unzGetCurrentFileInfo64(f, &info, NULL, 0, NULL, 0, NULL, 0)) {
            if (UNZ_OK == unzOpenCurrentFile(f)) {
                NSString *contentType = nil;
                NSString *fileExtension = [entryPath pathExtension];
                if ([fileExtension length] > 0) {
                    NSString *UTI = (NSString *) UTTypeCreatePreferredIdentifierForTag(kUTTagClassFilenameExtension, (CFStringRef) fileExtension, NULL);
                    contentType = (NSString *) UTTypeCopyPreferredTagWithClass((CFStringRef) UTI, kUTTagClassMIMEType);
                    [contentType autorelease];
                    [UTI release];
                }
                if (!contentType) {
                    contentType = defaultMimeType;
                }

                NSURLResponse *response = [[NSURLResponse alloc] initWithURL:requestURL MIMEType:contentType expectedContentLength:info.uncompressed_size textEncodingName:nil];
                [response autorelease];
                [[self client] URLProtocol:self didReceiveResponse:response cacheStoragePolicy:NSURLCacheStorageNotAllowed];

                int64_t bytesLeft = info.uncompressed_size;
                if (bytesLeft == 0) {
                    // Zero length entry
                    [[self client] URLProtocol:self didLoadData:[NSData data]];
                } else {

                    while (bytesLeft > 0) {
                        int bufSize = bytesLeft < (int64_t) BUF_SIZE ? (int) bytesLeft : (int) BUF_SIZE;
                        void *buffer = malloc(bufSize);
                        if (!buffer) {
                            errorCode = NSURLErrorUnknown;
                            break;
                        }
                        int n = unzReadCurrentFile(f, buffer, bufSize);
                        if (n <= 0) {
                            free(buffer);
                            break;
                        }
                        bytesLeft -= n;
                        NSData *data = [NSData dataWithBytesNoCopy:buffer length:n freeWhenDone:YES];
                        if (!data) {
                            free(buffer);
                            errorCode = NSURLErrorUnknown;
                            break;
                        }
                        [[self client] URLProtocol:self didLoadData:data];
                    }
                }

                if (!errorCode) {
                    [[self client] URLProtocolDidFinishLoading:self];
                }

                unzCloseCurrentFile(f);

            } else {
                errorCode = NSURLErrorCannotOpenFile;
            }
        } else {
            errorCode = NSURLErrorCannotOpenFile;
        }
    } else {
        errorCode = NSURLErrorFileDoesNotExist;
    }

    if (errorCode) {
        [self reportError:errorCode];
    }

    unzClose(f);
}

- (void)stopLoading {
    return;
}

@end

void registerJARURLProtocol(void) {
    Method original = class_getInstanceMethod([NSURL class], @selector(initWithString:relativeToURL:));
    Method swizzled = class_getInstanceMethod([NSURL class], @selector(swizzled_initWithString:relativeToURL:));
    method_exchangeImplementations(original, swizzled);
    [NSURLProtocol registerClass:[JARURLProtocol class]];
}
