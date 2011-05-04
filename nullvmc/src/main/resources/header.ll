%Env = type opaque
%Class = type {i8*, i8*, i8*, i32, i8*, i8*, i8*, i8, i32, i32, i8*, i8*, i8*, i32, i32, i32, i32, i8*}
%Object = type {%Class*, i8*}
%Array = type {%Object, i32}
%BooleanArray = type {%Object, i32, i8}
%ByteArray = type {%Object, i32, i8}
%CharArray = type {%Object, i32, i16}
%ShortArray = type {%Object, i32, i16}
%IntegerArray = type {%Object, i32, i32}
%LongArray = type {%Object, i32, i64}
%FloatArray = type {%Object, i32, float}
%DoubleArray = type {%Object, i32, double}
%ObjectArray = type {%Object, i32, %Object*}

declare %Class* @_nvmBcAllocateClass(%Env*, i8*, i8*, %Object*, i32, i32, i32)
declare void @_nvmBcAddInterface(%Env*, %Class*, i8*)
declare void @_nvmBcAddMethod(%Env*, %Class*, i8*, i8*, i32, i8*, i8*)
declare void @_nvmBcAddField(%Env*, %Class*, i8*, i8*, i32, i32, i8*, i8*)
declare void @_nvmBcRegisterClass(%Env*, %Class*)
declare %Object* @_nvmBcFindClassInLoader(%Env*, i8*, %Object*)

declare i8* @_nvmBcLookupVirtualMethod(%Env*, %Object*, i8*, i8*)
declare void @_nvmBcThrow(%Env*, %Object*)
declare void @_nvmBcRethrow(%Env*)
declare void @_nvmBcThrowIfExceptionOccurred(%Env*)
declare %Object* @_nvmBcExceptionClear(%Env*)
declare i32 @_nvmBcExceptionMatch(%Env*, %Class*)
declare void @_nvmBcExceptionSet(%Env*, %Object*)
declare void @_nvmBcThrowNullPointerException(%Env*)
declare void @_nvmBcThrowArrayIndexOutOfBoundsException(%Env*, i32)
declare void @_nvmBcThrowArithmeticException(%Env*)

declare %Object* @_nvmBcNew(%Env*, i8*)
declare %Object* @_nvmBcNewBooleanArray(%Env*, i32)
declare %Object* @_nvmBcNewByteArray(%Env*, i32)
declare %Object* @_nvmBcNewCharArray(%Env*, i32)
declare %Object* @_nvmBcNewShortArray(%Env*, i32)
declare %Object* @_nvmBcNewIntArray(%Env*, i32)
declare %Object* @_nvmBcNewLongArray(%Env*, i32)
declare %Object* @_nvmBcNewFloatArray(%Env*, i32)
declare %Object* @_nvmBcNewDoubleArray(%Env*, i32)
declare %Object* @_nvmBcNewObjectArray(%Env*, i32, i8*)
declare %Object* @_nvmBcNewMultiArray(%Env*, i32, i32*, i8*)
        
declare %Object* @_nvmBcLdcString(%Env*, i8*)
declare %Object* @_nvmBcLdcClass(%Env*, i8*)
        
declare void @_nvmBcMonitorEnter(%Env*, %Object*)
declare void @_nvmBcMonitorExit(%Env*, %Object*)
declare %Object* @_nvmBcCheckcast(%Env*, %Object*, i8*)
declare i32 @_nvmBcInstanceof(%Env*, %Object*, i8*)

declare i8* @_nvmBcResolveInvokespecial(%Env*, i8*, i8*, i8*, i8*)
declare i8* @_nvmBcResolveInvokestatic(%Env*, i8*, i8*, i8*, i8*)
declare i8* @_nvmBcResolveInvokevirtual(%Env*, i8*, i8*, i8*, i8*)
declare i8* @_nvmBcResolveInvokeinterface(%Env*, i8*, i8*, i8*, i8*)
declare i8* @_nvmBcResolveNative(%Env*, i8*, i8*, i8*, i8*, i8*, i8*)
declare i8* @_nvmBcResolveGetstatic(%Env*, i8*, i8*, i8*, i8*)
declare i8* @_nvmBcResolvePutstatic(%Env*, i8*, i8*, i8*, i8*)
declare i8* @_nvmBcResolveGetfield(%Env*, i8*, i8*, i8*, i8*)
declare i8* @_nvmBcResolvePutfield(%Env*, i8*, i8*, i8*, i8*)

declare i8* @llvm.eh.exception() nounwind
declare i32 @llvm.eh.selector(i8*, i8*, ...) nounwind
declare i8* @_nvmPersonality()


define linkonce_odr i32 @arraylength(%Object* %o) alwaysinline {
    %array = bitcast %Object* %o to %Array*
    %length = getelementptr %Array* %array, i32 0, i32 1
    %res = load i32* %length
    ret i32 %res
}

define linkonce_odr i32 @iaload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %IntegerArray*
    %base = getelementptr %IntegerArray* %array, i32 0, i32 2
    %ptr = getelementptr i32* %base, i32 %index
    %value = load i32* %ptr
    ret i32 %value
}

define linkonce_odr void @iastore(%Object* %o, i32 %index, i32 %value) alwaysinline {
    %array = bitcast %Object* %o to %IntegerArray*
    %base = getelementptr %IntegerArray* %array, i32 0, i32 2
    %ptr = getelementptr i32* %base, i32 %index
    store i32 %value, i32* %ptr
    ret void
}

define linkonce_odr i8 @baload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %ByteArray*
    %base = getelementptr %ByteArray* %array, i32 0, i32 2
    %ptr = getelementptr i8* %base, i32 %index
    %value = load i8* %ptr
    ret i8 %value
}

define linkonce_odr void @bastore(%Object* %o, i32 %index, i8 %value) alwaysinline {
    %array = bitcast %Object* %o to %ByteArray*
    %base = getelementptr %ByteArray* %array, i32 0, i32 2
    %ptr = getelementptr i8* %base, i32 %index
    store i8 %value, i8* %ptr
    ret void
}

define linkonce_odr i16 @saload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %ShortArray*
    %base = getelementptr %ShortArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    %value = load i16* %ptr
    ret i16 %value
}

define linkonce_odr void @sastore(%Object* %o, i32 %index, i16 %value) alwaysinline {
    %array = bitcast %Object* %o to %ShortArray*
    %base = getelementptr %ShortArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    store i16 %value, i16* %ptr
    ret void
}

define linkonce_odr i16 @caload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %CharArray*
    %base = getelementptr %CharArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    %value = load i16* %ptr
    ret i16 %value
}

define linkonce_odr void @castore(%Object* %o, i32 %index, i16 %value) alwaysinline {
    %array = bitcast %Object* %o to %CharArray*
    %base = getelementptr %CharArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    store i16 %value, i16* %ptr
    ret void
}

define linkonce_odr float @faload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %FloatArray*
    %base = getelementptr %FloatArray* %array, i32 0, i32 2
    %ptr = getelementptr float* %base, i32 %index
    %value = load float* %ptr
    ret float %value
}

define linkonce_odr void @fastore(%Object* %o, i32 %index, float %value) alwaysinline {
    %array = bitcast %Object* %o to %FloatArray*
    %base = getelementptr %FloatArray* %array, i32 0, i32 2
    %ptr = getelementptr float* %base, i32 %index
    store float %value, float* %ptr
    ret void
}

define linkonce_odr i64 @laload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %LongArray*
    %base = getelementptr %LongArray* %array, i32 0, i32 2
    %ptr = getelementptr i64* %base, i32 %index
    %value = load i64* %ptr
    ret i64 %value
}

define linkonce_odr void @lastore(%Object* %o, i32 %index, i64 %value) alwaysinline {
    %array = bitcast %Object* %o to %LongArray*
    %base = getelementptr %LongArray* %array, i32 0, i32 2
    %ptr = getelementptr i64* %base, i32 %index
    store i64 %value, i64* %ptr
    ret void
}

define linkonce_odr double @daload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %DoubleArray*
    %base = getelementptr %DoubleArray* %array, i32 0, i32 2
    %ptr = getelementptr double* %base, i32 %index
    %value = load double* %ptr
    ret double %value
}

define linkonce_odr void @dastore(%Object* %o, i32 %index, double %value) alwaysinline {
    %array = bitcast %Object* %o to %DoubleArray*
    %base = getelementptr %DoubleArray* %array, i32 0, i32 2
    %ptr = getelementptr double* %base, i32 %index
    store double %value, double* %ptr
    ret void
}

define linkonce_odr %Object* @aaload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %ObjectArray*
    %base = getelementptr %ObjectArray* %array, i32 0, i32 2
    %ptr = getelementptr %Object** %base, i32 %index
    %value = load %Object** %ptr
    ret %Object* %value
}

define linkonce_odr void @aastore(%Object* %o, i32 %index, %Object* %value) alwaysinline {
    %array = bitcast %Object* %o to %ObjectArray*
    %base = getelementptr %ObjectArray* %array, i32 0, i32 2
    %ptr = getelementptr %Object** %base, i32 %index
    store %Object* %value, %Object** %ptr
    ret void
}

define linkonce_odr void @checknull(%Env* %env, %Object* %o) alwaysinline {
    %cond = icmp ne %Object* %o, inttoptr (i32 0 to %Object*)
    br i1 %cond, label %success, label %failure
success:
    ret void
failure:
    call void @_nvmBcThrowNullPointerException(%Env* %env)
    unreachable
}

define linkonce_odr void @checklower(%Env* %env, %Object* %o, i32 %index) alwaysinline {
    %cond = icmp sge i32 %index, 0
    br i1 %cond, label %success, label %failure
success:
    ret void
failure:
    call void @_nvmBcThrowArrayIndexOutOfBoundsException(%Env* %env, i32 %index)
    unreachable
}

define linkonce_odr void @checkupper(%Env* %env, %Object* %o, i32 %index) alwaysinline {
    %length = call i32 @arraylength(%Object* %o)
    %cond = icmp slt i32 %index, %length
    br i1 %cond, label %success, label %failure
success:
    ret void
failure:
    call void @_nvmBcThrowArrayIndexOutOfBoundsException(%Env* %env, i32 %index)
    unreachable
}

define linkonce_odr i32 @f2i(float %op) alwaysinline {
    ; if (%op != %op) %res = 0       (%op == NaN)
    ; if (%op >= MAX_VALUE) %res = MAX_VALUE
    ; if (%op <= MIN_VALUE) %res = MIN_VALUE
    ; else %res = (i32) %op
    %1 = fcmp oeq float %op, %op
    %2 = fcmp oge float %op, bitcast (i32 1325400064 to float)
    %3 = fcmp ole float %op, bitcast (i32 -822083584 to float)
    %4 = fptosi float %op to i32
    %5 = select i1 %1, i32 %4, i32 0
    %6 = select i1 %2, i32  2147483647, i32 %5
    %7 = select i1 %3, i32 -2147483648, i32 %6
    ret i32 %7
}

define linkonce_odr i64 @f2l(float %op) alwaysinline {
    ; if (%op != %op) %res = 0       (%op == NaN)
    ; if (%op >= MAX_VALUE) %res = MAX_VALUE
    ; if (%op <= MIN_VALUE) %res = MIN_VALUE
    ; else %res = (i64) %op
    %1 = fcmp oeq float %op, %op
    %2 = fcmp oge float %op, bitcast (i32 1593835520 to float)
    %3 = fcmp ole float %op, bitcast (i32 -553648128 to float)
    %4 = fptosi float %op to i64
    %5 = select i1 %1, i64 %4, i64 0
    %6 = select i1 %2, i64  9223372036854775807, i64 %5
    %7 = select i1 %3, i64 -9223372036854775808, i64 %6
    ret i64 %7
}

define linkonce_odr i32 @d2i(double %op) alwaysinline {
    ; if (%op != %op) %res = 0       (%op == NaN)
    ; if (%op >= MAX_VALUE) %res = MAX_VALUE
    ; if (%op <= MIN_VALUE) %res = MIN_VALUE
    ; else %res = (i32) %op
    %1 = fcmp oeq double %op, %op
    %2 = fcmp oge double %op, bitcast (i64 4746794007244308480 to double)
    %3 = fcmp ole double %op, bitcast (i64 -4476578029606273024 to double)
    %4 = fptosi double %op to i32
    %5 = select i1 %1, i32 %4, i32 0
    %6 = select i1 %2, i32  2147483647, i32 %5
    %7 = select i1 %3, i32 -2147483648, i32 %6
    ret i32 %7
}

define linkonce_odr i64 @d2l(double %op) alwaysinline {
    ; if (%op != %op) %res = 0       (%op == NaN)
    ; if (%op >= MAX_VALUE) %res = MAX_VALUE
    ; if (%op <= MIN_VALUE) %res = MIN_VALUE
    ; else %res = (i64) %op
    %1 = fcmp oeq double %op, %op
    %2 = fcmp oge double %op, bitcast (i64 4746794007244308480 to double)
    %3 = fcmp ole double %op, bitcast (i64 -4476578029606273024 to double)
    %4 = fptosi double %op to i64
    %5 = select i1 %1, i64 %4, i64 0
    %6 = select i1 %2, i64  9223372036854775807, i64 %5
    %7 = select i1 %3, i64 -9223372036854775808, i64 %6
    ret i64 %7
}

define linkonce_odr i32 @idiv(%Env* %env, i32 %op1, i32 %op2) alwaysinline {
    %condZero = icmp ne i32 %op2, 0
    br i1 %condZero, label %notZero, label %zero
notZero:
    %condNotMinusOne = icmp ne i32 %op2, -1
    br i1 %condNotMinusOne, label %notMinusOne, label %minusOne
notMinusOne:
    %result1 = sdiv i32 %op1, %op2
    ret i32 %result1
minusOne:
    %result2 = mul i32 %op1, %op2
    ret i32 %result2
zero:
    call void @_nvmBcThrowArithmeticException(%Env* %env)
    unreachable
}

define linkonce_odr i64 @ldiv(%Env* %env, i64 %op1, i64 %op2) alwaysinline {
    %condZero = icmp ne i64 %op2, 0
    br i1 %condZero, label %notZero, label %zero
notZero:
    %condNotMinusOne = icmp ne i64 %op2, -1
    br i1 %condNotMinusOne, label %notMinusOne, label %minusOne
notMinusOne:
    %result1 = sdiv i64 %op1, %op2
    ret i64 %result1
minusOne:
    %result2 = mul i64 %op1, %op2
    ret i64 %result2
zero:
    call void @_nvmBcThrowArithmeticException(%Env* %env)
    unreachable
}

define linkonce_odr i32 @irem(%Env* %env, i32 %op1, i32 %op2) alwaysinline {
    %condZero = icmp ne i32 %op2, 0
    br i1 %condZero, label %notZero, label %zero
notZero:
    %condNotMinusOne = icmp ne i32 %op2, -1
    br i1 %condNotMinusOne, label %notMinusOne, label %minusOne
notMinusOne:
    %result1 = srem i32 %op1, %op2
    ret i32 %result1
minusOne:
    ret i32 0
zero:
    call void @_nvmBcThrowArithmeticException(%Env* %env)
    unreachable
}

define linkonce_odr i64 @lrem(%Env* %env, i64 %op1, i64 %op2) alwaysinline {
    %condZero = icmp ne i64 %op2, 0
    br i1 %condZero, label %notZero, label %zero
notZero:
    %condNotMinusOne = icmp ne i64 %op2, -1
    br i1 %condNotMinusOne, label %notMinusOne, label %minusOne
notMinusOne:
    %result1 = srem i64 %op1, %op2
    ret i64 %result1
minusOne:
    ret i64 0
zero:
    call void @_nvmBcThrowArithmeticException(%Env* %env)
    unreachable
}

define linkonce_odr i32 @fcmpl(float %op1, float %op2) alwaysinline {
    %1 = fcmp ogt float %op1, %op2 ; 1 if op1 > op2
    %2 = fcmp ult float %op1, %op2 ; 1 if op1 < op2 or either is NaN
    %3 = zext i1 %1 to i32
    %4 = zext i1 %2 to i32
    %5 = sub i32 %3, %4
    ret i32 %5
}

define linkonce_odr i32 @fcmpg(float %op1, float %op2) alwaysinline {
    %1 = fcmp ugt float %op1, %op2 ; 1 if op1 > op2 or either is NaN
    %2 = fcmp olt float %op1, %op2 ; 1 if op1 < op2
    %3 = zext i1 %1 to i32
    %4 = zext i1 %2 to i32
    %5 = sub i32 %3, %4
    ret i32 %5
}

define linkonce_odr i32 @dcmpl(double %op1, double %op2) alwaysinline {
    %1 = fcmp ogt double %op1, %op2 ; 1 if op1 > op2
    %2 = fcmp ult double %op1, %op2 ; 1 if op1 < op2 or either is NaN
    %3 = zext i1 %1 to i32
    %4 = zext i1 %2 to i32
    %5 = sub i32 %3, %4
    ret i32 %5
}

define linkonce_odr i32 @dcmpg(double %op1, double %op2) alwaysinline {
    %1 = fcmp ugt double %op1, %op2 ; 1 if op1 > op2 or either is NaN
    %2 = fcmp olt double %op1, %op2 ; 1 if op1 < op2
    %3 = zext i1 %1 to i32
    %4 = zext i1 %2 to i32
    %5 = sub i32 %3, %4
    ret i32 %5
}
