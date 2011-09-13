#include <unwind.h>

// This file must be compiled with no optimization (specifically without -fomit-frame-pointer).
// It is important that the unwindBacktrace() function saves the CFA in a well defined register
// (%rbp on x86_64, %ebp on i386). It would probably be safer to write this in assembly but it's
// a pain to get the eh_frame tables right.

_Unwind_Reason_Code unwindBacktrace(_Unwind_Trace_Fn fn, void* data) {
    return _Unwind_Backtrace(fn, data);
}

