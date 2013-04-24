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
