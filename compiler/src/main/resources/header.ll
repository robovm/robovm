%GatewayFrame = type {i8*, i8*, i8*}
%Env = type {i8*, i8*, i8*, i8*, i8*, i8*, i8*, i32}
%Class = type {i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i32, i8*, i8*, i8*, i8*, i32, i32, i32}
%Method = type opaque
%Field = type opaque
%Object = type {%Class*, i8*}
%Array = type {%Object, i32}
%BooleanArray = type {%Object, i32, i8}
%ByteArray = type {%Object, i32, i8}
%CharArray = type {%Object, i32, i16}
%ShortArray = type {%Object, i32, i16}
%IntArray = type {%Object, i32, i32}
%LongArray = type {%Object, i32, i64}
%FloatArray = type {%Object, i32, float}
%DoubleArray = type {%Object, i32, double}
%ObjectArray = type {%Object, i32, %Object*}

@array_Z = external global %Object*
@array_B = external global %Object*
@array_C = external global %Object*
@array_S = external global %Object*
@array_I = external global %Object*
@array_J = external global %Object*
@array_F = external global %Object*
@array_D = external global %Object*

declare void @_bcInitializeClass(%Env*, i8**)
declare %Object* @_bcAllocate(%Env*, i8**)
declare %Object* @_bcLdcArrayBootClass(%Env*, %Object**, i8*)
declare %Object* @_bcLdcArrayClass(%Env*, %Object**, i8*)
declare %Object* @_bcLdcClass(%Env*, i8**)
declare %Object* @_bcNewObjectArray(%Env*, i32, %Object*)
declare %Object* @_bcCheckcast(%Env*, i8**, %Object*)
declare %Object* @_bcCheckcastArray(%Env*, %Object*, %Object*)
declare i32 @_bcInstanceof(%Env*, i8**, %Object*)
declare i32 @_bcInstanceofArray(%Env*, %Object*, %Object*)

declare i8* @_bcLookupVirtualMethod(%Env*, %Object*, i8*, i8*)
declare i8* @_bcLookupInterfaceMethod(%Env*, i8**, %Object*, i8*, i8*)
declare void @_bcThrow(%Env*, %Object*) noreturn
declare void @_bcThrowIfExceptionOccurred(%Env*)
declare %Object* @_bcExceptionClear(%Env*)
declare i32 @rvmTrycatchEnter(%Env*, %TrycatchContext*) returns_twice
declare void @_bcTrycatchLeave(%Env*)
declare void @_bcThrowNullPointerException(%Env*) noreturn
declare void @_bcThrowArrayIndexOutOfBoundsException(%Env*, i32, i32) noreturn
declare void @_bcThrowArithmeticException(%Env*) noreturn
declare void @_bcThrowUnsatisfiedLinkError(%Env*, i8*) noreturn
declare void @_bcThrowNoClassDefFoundError(%Env*, i8*) noreturn
declare void @_bcThrowNoSuchFieldError(%Env*, i8*) noreturn
declare void @_bcThrowNoSuchMethodError(%Env*, i8*) noreturn
declare void @_bcThrowIllegalAccessError(%Env*, i8*) noreturn
declare void @_bcThrowInstantiationError(%Env*, i8*) noreturn
declare void @_bcThrowIncompatibleClassChangeError(%Env*, i8*) noreturn
declare void @_bcThrowAbstractMethodError(%Env*, i8*) noreturn

declare %Object* @_bcNew(%Env*, i8*)
declare %Object* @_bcNewBooleanArray(%Env*, i32)
declare %Object* @_bcNewByteArray(%Env*, i32)
declare %Object* @_bcNewCharArray(%Env*, i32)
declare %Object* @_bcNewShortArray(%Env*, i32)
declare %Object* @_bcNewIntArray(%Env*, i32)
declare %Object* @_bcNewLongArray(%Env*, i32)
declare %Object* @_bcNewFloatArray(%Env*, i32)
declare %Object* @_bcNewDoubleArray(%Env*, i32)
declare %Object* @_bcNewMultiArray(%Env*, i32, i32*, %Object*)
declare void @_bcSetObjectArrayElement(%Env*, %Object*, i32, %Object*)

declare %Object* @_bcLdcString(%Env*, i8*)
        
declare void @_bcMonitorEnter(%Env*, %Object*)
declare void @_bcMonitorExit(%Env*, %Object*)

declare i8* @_bcResolveNative(%Env*, %Object*, i8*, i8*, i8*, i8*, i8**)

declare void @_bcPushNativeFrame(%Env*, %GatewayFrame*, i8*)
declare void @_bcPopNativeFrame(%Env*)

declare void @_bcPushCallbackFrame(%Env*, %GatewayFrame*, i8*)
declare void @_bcPopCallbackFrame(%Env*)

declare %Env* @_bcAttachThreadFromCallback()
declare void @_bcDetachThreadFromCallback(%Env*)

declare %Object* @_bcNewStruct(%Env*, i8*, %Class*, i8*)
declare i8* @_bcGetStructHandle(%Env*, %Object*)
declare void @_bcSetStructHandle(%Env*, %Object*, i8*)
declare i8* @_bcByValueGetStructHandle(%Env*, %Object*)
declare void @_bcCopyStruct(%Env*, %Object*, i8*, i32)

declare i8* @llvm.frameaddress(i32) nounwind readnone


define linkonce_odr i32 @arraylength(%Object* %o) alwaysinline {
    %array = bitcast %Object* %o to %Array*
    %length = getelementptr %Array* %array, i32 0, i32 1
    %res = load i32* %length
    ret i32 %res
}

define linkonce_odr i32 @iaload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %IntArray*
    %base = getelementptr %IntArray* %array, i32 0, i32 2
    %ptr = getelementptr i32* %base, i32 %index
    %value = load i32* %ptr
    ret i32 %value
}

define linkonce_odr void @iastore(%Object* %o, i32 %index, i32 %value) alwaysinline {
    %array = bitcast %Object* %o to %IntArray*
    %base = getelementptr %IntArray* %array, i32 0, i32 2
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

define linkonce_odr i8 @checknull(%Env* %env, %Object* %o) alwaysinline {
    %p = bitcast %Object* %o to i8*
    %i = load volatile i8* %p
    ret i8 %i
}

define linkonce_odr void @checklower(%Env* %env, %Object* %o, i32 %index) alwaysinline {
    %cond = icmp sge i32 %index, 0
    br i1 %cond, label %success, label %failure
success:
    ret void
failure:
    %length = call i32 @arraylength(%Object* %o)
    call void @_bcThrowArrayIndexOutOfBoundsException(%Env* %env, i32 %length, i32 %index)
    unreachable
}

define linkonce_odr void @checkupper(%Env* %env, %Object* %o, i32 %index) alwaysinline {
    %length = call i32 @arraylength(%Object* %o)
    %cond = icmp slt i32 %index, %length
    br i1 %cond, label %success, label %failure
success:
    ret void
failure:
    call void @_bcThrowArrayIndexOutOfBoundsException(%Env* %env, i32 %length, i32 %index)
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
    %2 = fcmp oge double %op, bitcast (i64 4890909195324358656 to double)
    %3 = fcmp ole double %op, bitcast (i64 -4332462841530417152 to double)
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
    call void @_bcThrowArithmeticException(%Env* %env)
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
    call void @_bcThrowArithmeticException(%Env* %env)
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
    call void @_bcThrowArithmeticException(%Env* %env)
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
    call void @_bcThrowArithmeticException(%Env* %env)
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

define private %Object* @ldcClassWrapper(%Env* %env, i8** %header) alwaysinline {
    %1 = load i8** %header
    %2 = icmp ne i8* %1, null
    br i1 %2, label %loaded, label %notLoaded
loaded:
    %3 = bitcast i8* %1 to %Object*
    ret %Object* %3
notLoaded:
    %4 = call %Object* @_bcLdcClass(%Env* %env, i8** %header)
    ret %Object* %4
}
