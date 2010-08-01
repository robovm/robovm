#include <nullvm.h>
#include <stdio.h>
#include <stdarg.h>

void nvmStartup(void) {
    GC_set_pages_executable(1);
    if (!GC_get_pages_executable()) {
        nvmAbort("Failed to set GC_set_pages_executable(1)");
    }
    GC_INIT();
    // TODO: Handle args like -Xmx?
    // TODO: Cache java.lang.Object|String|...
    // Call init on modules
}


void nvmAbort(char* format, ...) {
    va_list args;
    va_start(args, format);
    vfprintf(stderr, format, args);
    va_end(args);
    fprintf(stderr, "\n");
    abort();
}

