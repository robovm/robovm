#import <Foundation/Foundation.h>

void readDarwinOSVersionFromSystemVersionPList(char* buffer) {
    assert(buffer != NULL);

    NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];

#if defined(IOS) && defined(RVM_X86)
    // Prepend value of $IPHONE_SIMULATOR_ROOT environment variable
    NSString* plistPath = [NSString stringWithFormat:@"%s/System/Library/CoreServices/SystemVersion.plist", getenv("IPHONE_SIMULATOR_ROOT")];
#else
    NSString* plistPath = @"/System/Library/CoreServices/SystemVersion.plist";
#endif

    NSDictionary* plist = [NSDictionary dictionaryWithContentsOfFile:plistPath];
    NSString* version = [plist objectForKey:@"ProductVersion"];
    assert(version != nil);
    strcat(buffer, [version UTF8String]);

    [pool release];
}

void getDarwinLocaleParts(char* lang, char* country, char* variant) {
    char tmp[64] = "\0";
    CFLocaleRef locale = CFLocaleCopyCurrent();
    CFStringRef locId = CFLocaleGetIdentifier(locale);
    CFDictionaryRef parts = CFLocaleCreateComponentsFromLocaleIdentifier(NULL, locId);
    CFStringRef s;
    s = CFDictionaryGetValue(parts, kCFLocaleLanguageCode);
    if (s) {
        CFStringGetCString(s, tmp, sizeof(tmp), kCFStringEncodingASCII);
        strcat(lang, tmp);
    }
    s = CFDictionaryGetValue(parts, kCFLocaleCountryCode);
    if (s) {
        CFStringGetCString(s, tmp, sizeof(tmp), kCFStringEncodingASCII);
        strcat(country, tmp);
    }
    s = CFDictionaryGetValue(parts, kCFLocaleVariantCode);
    if (s) {
        CFStringGetCString(s, tmp, sizeof(tmp), kCFStringEncodingASCII);
        strcat(variant, tmp);
    }
    CFRelease(parts);
    CFRelease(locale);
}
