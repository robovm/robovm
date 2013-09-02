%GatewayFrame = type {i8*, i8*, i8*}
%StackFrame = type {i8*, i8*}
%Thread = type {i32} ; Incomplete. Just enough to get threadId
%Env = type {i8*, i8*, i8*, %Thread*, i8*, i8*, %GatewayFrame*, i8*, i32}
%TypeInfo = type {i32, i32, i32, i32, i32, [0 x i32]}
%VITable = type {i16, [0 x i8*]}
%ITable = type {%TypeInfo*, %VITable}
%ITables = type {i16, %ITable*, [0 x %ITable*]}
; NOTE: The compiler assumes that %Class is a multiple of 8 in size (currently 84 bytes)
%Class = type {i8*, i8*, i8*, %TypeInfo*, %VITable*, %ITables*, i8*, i8*, i8*, i8*, i8*, i32, i8*, i8*, i8*, i8*, i8*, i32, i32, i32, i16, i16, i32}
%Method = type opaque
%Field = type opaque
%Object = type {%Class*, i8*}
; NOTE: The compiler assumes that %DataObject is a multiple of 8 in size (we don't need to pad it since it's already 8 bytes in size)
%DataObject = type {%Object}
%Array = type {%DataObject, i32}
%BooleanArray = type {%DataObject, i32, i8}
%ByteArray = type {%DataObject, i32, i8}
%CharArray = type {%DataObject, i32, i16}
%ShortArray = type {%DataObject, i32, i16}
%IntArray = type {%DataObject, i32, i32}
%LongArray = type {%DataObject, i32, i64}
%FloatArray = type {%DataObject, i32, float}
%DoubleArray = type {%DataObject, i32, double}
%ObjectArray = type {%DataObject, i32, %Object*}

@prim_Z = external global %Class*
@prim_B = external global %Class*
@prim_C = external global %Class*
@prim_S = external global %Class*
@prim_I = external global %Class*
@prim_J = external global %Class*
@prim_F = external global %Class*
@prim_D = external global %Class*

@array_Z = external global %Class*
@array_B = external global %Class*
@array_C = external global %Class*
@array_S = external global %Class*
@array_I = external global %Class*
@array_J = external global %Class*
@array_F = external global %Class*
@array_D = external global %Class*

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
declare void @_bcRegisterFinalizer(%Env* %env, %Object* %o)

declare i8* @_bcLookupVirtualMethod(%Env*, %Object*, i8*, i8*)
declare i8* @_bcLookupInterfaceMethod(%Env*, i8**, %Object*, i8*, i8*)
declare i8* @_bcLookupInterfaceMethodImpl(%Env*, i8**, %Object*, i32)
declare void @_bcAbstractMethodCalled(%Env*, %Object*)
declare void @_bcNonPublicMethodCalled(%Env*, %Object*)
declare void @_bcMoveMemory16(i8*, i8*, i64)
declare void @_bcMoveMemory32(i8*, i8*, i64)
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
declare void @_bcThrowClassCastException(%Env*, i8**, %Object*) noreturn
declare void @_bcThrowClassCastExceptionArray(%Env*, %Class*, %Object*) noreturn

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

declare %Object* @_bcLdcString(%Env*, %Object**, i8*, i32)
        
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
declare void @llvm.memcpy.p0i8.p0i8.i32(i8*, i8*, i32, i32, i1)
declare void @llvm.memmove.p0i8.p0i8.i64(i8*, i8*, i64, i32, i1)
declare double @llvm.sqrt.f64(double)
declare double @llvm.cos.f64(double)
declare double @llvm.sin.f64(double)

define private i32 @Thread_threadId(%Thread* %t) alwaysinline {
    %1 = getelementptr %Thread* %t, i32 0, i32 0 ; Thread->threadId
    %2 = load volatile i32* %1
    ret i32 %2
}

define private %Thread* @Env_currentThread(%Env* %env) alwaysinline {
    %1 = getelementptr %Env* %env, i32 0, i32 3 ; Env->currentThread
    %2 = load volatile %Thread** %1
    ret %Thread* %2
}

define private %GatewayFrame* @Env_gatewayFrames(%Env* %env) alwaysinline {
    %1 = getelementptr %Env* %env, i32 0, i32 6 ; Env->gatewayFrames
    %2 = load volatile %GatewayFrame** %1
    ret %GatewayFrame* %2
}

define private void @Env_gatewayFrames_store(%Env* %env, %GatewayFrame* %value) alwaysinline {
    %1 = getelementptr %Env* %env, i32 0, i32 6 ; Env->gatewayFrames
    store volatile %GatewayFrame* %value, %GatewayFrame** %1
    ret void
}

define private %Class* @Object_class(%Object* %o) alwaysinline {
    %1 = getelementptr %Object* %o, i32 0, i32 0
    %2 = load volatile %Class** %1
    ret %Class* %2
}

define private i32 @Object_lock(%Object* %o) alwaysinline {
    %1 = getelementptr %Object* %o, i32 0, i32 1 ; Object->lock
    %2 = bitcast i8** %1 to i32*
    %3 = load volatile i32* %2
    ret i32 %3
}

define private i32* @Object_lockPtr(%Object* %o) alwaysinline {
    %1 = getelementptr %Object* %o, i32 0, i32 1 ; Object->lock
    %2 = bitcast i8** %1 to i32*
    ret i32* %2
}

define private %TypeInfo* @Class_typeInfo(%Class* %c) alwaysinline {
    %1 = getelementptr %Class* %c, i32 0, i32 3 ; Class->typeInfo
    %2 = load volatile %TypeInfo** %1
    ret %TypeInfo* %2
}

define private %VITable* @Class_vitable(%Class* %c) alwaysinline {
    %1 = getelementptr %Class* %c, i32 0, i32 4 ; Class->vitable
    %2 = load volatile %VITable** %1
    ret %VITable* %2
}

define private %ITables* @Class_itables(%Class* %c) alwaysinline {
    %1 = getelementptr %Class* %c, i32 0, i32 5 ; Class->itables
    %2 = load volatile %ITables** %1
    ret %ITables* %2
}

define private %Class* @Class_superclass(%Class* %c) alwaysinline {
    %1 = getelementptr %Class* %c, i32 0, i32 8 ; Class->superclass
    %2 = load volatile i8** %1
    %3 = bitcast i8* %2 to %Class*
    ret %Class* %3
}

define private %Class* @Class_componentType(%Class* %c) alwaysinline {
    %1 = getelementptr %Class* %c, i32 0, i32 9 ; Class->componentType
    %2 = load volatile i8** %1
    %3 = bitcast i8* %2 to %Class*
    ret %Class* %3
}

define private i32 @Class_flags(%Class* %c) alwaysinline {
    %1 = getelementptr %Class* %c, i32 0, i32 11 ; Class->flags
    %2 = load volatile i32* %1
    ret i32 %2
}

define private i32 @TypeInfo_offset(%TypeInfo* %ti) alwaysinline {
    %1 = getelementptr %TypeInfo* %ti, i32 0, i32 1 ; TypeInfo->offset
    %2 = load volatile i32* %1
    ret i32 %2
}

define private i32 @TypeInfo_cache(%TypeInfo* %ti) alwaysinline {
    %1 = getelementptr %TypeInfo* %ti, i32 0, i32 2 ; TypeInfo->cache
    %2 = load volatile i32* %1
    ret i32 %2
}

define private void @TypeInfo_cache_store(%TypeInfo* %ti, i32 %value) alwaysinline {
    %1 = getelementptr %TypeInfo* %ti, i32 0, i32 2 ; TypeInfo->cache
    store volatile i32 %value, i32* %1
    ret void
}

define private i32 @TypeInfo_interfaceCount(%TypeInfo* %ti) alwaysinline {
    %1 = getelementptr %TypeInfo* %ti, i32 0, i32 4 ; TypeInfo->interfaceCount
    %2 = load volatile i32* %1
    ret i32 %2
}

define private %Object* @intrinsics.ldc_prim_Z(%Env* %env) alwaysinline {
    %1 = load volatile %Class** @prim_Z
    %2 = bitcast %Class* %1 to %Object*
    ret %Object* %2
}

define private %Object* @intrinsics.ldc_prim_B(%Env* %env) alwaysinline {
    %1 = load volatile %Class** @prim_B
    %2 = bitcast %Class* %1 to %Object*
    ret %Object* %2
}

define private %Object* @intrinsics.ldc_prim_C(%Env* %env) alwaysinline {
    %1 = load volatile %Class** @prim_C
    %2 = bitcast %Class* %1 to %Object*
    ret %Object* %2
}

define private %Object* @intrinsics.ldc_prim_S(%Env* %env) alwaysinline {
    %1 = load volatile %Class** @prim_S
    %2 = bitcast %Class* %1 to %Object*
    ret %Object* %2
}

define private %Object* @intrinsics.ldc_prim_I(%Env* %env) alwaysinline {
    %1 = load volatile %Class** @prim_I
    %2 = bitcast %Class* %1 to %Object*
    ret %Object* %2
}

define private %Object* @intrinsics.ldc_prim_J(%Env* %env) alwaysinline {
    %1 = load volatile %Class** @prim_J
    %2 = bitcast %Class* %1 to %Object*
    ret %Object* %2
}

define private %Object* @intrinsics.ldc_prim_F(%Env* %env) alwaysinline {
    %1 = load volatile %Class** @prim_F
    %2 = bitcast %Class* %1 to %Object*
    ret %Object* %2
}

define private %Object* @intrinsics.ldc_prim_D(%Env* %env) alwaysinline {
    %1 = load volatile %Class** @prim_D
    %2 = bitcast %Class* %1 to %Object*
    ret %Object* %2
}

define private %Object* @intrinsics.java_lang_Class_getSuperclass(%Env* %env, %Object* %o) alwaysinline {
    %c = bitcast %Object* %o to %Class*
    %1 = call %Class* @Class_superclass(%Class* %c)
    %res = bitcast %Class* %1 to %Object*
    ret %Object* %res
}

define private %Object* @intrinsics.java_lang_Class_getComponentType(%Env* %env, %Object* %o) alwaysinline {
    %c = bitcast %Object* %o to %Class*
    %1 = call %Class* @Class_componentType(%Class* %c)
    %res = bitcast %Class* %1 to %Object*
    ret %Object* %res
}

define private i8 @intrinsics.java_lang_Class_isArray(%Env* %env, %Object* %o) alwaysinline {
    %c = bitcast %Object* %o to %Class*
    %flags = call i32 @Class_flags(%Class* %c)
    %1 = and i32 %flags, 805306368       ; CLASS_TYPE_MASK = 0x30000000
    %isArray = icmp eq i32 %1, 536870912 ; CLASS_TYPE_ARRAY = 0x20000000
    %res = select i1 %isArray, i8 1, i8 0
    ret i8 %res
}

define private i8 @intrinsics.java_lang_Class_isPrimitive(%Env* %env, %Object* %o) alwaysinline {
    %c = bitcast %Object* %o to %Class*
    %flags = call i32 @Class_flags(%Class* %c)
    %1 = and i32 %flags, 805306368       ; CLASS_TYPE_MASK = 0x30000000
    %isPrim = icmp eq i32 %1, 268435456  ; CLASS_TYPE_PRIMITIVE = 0x10000000
    %res = select i1 %isPrim, i8 1, i8 0
    ret i8 %res
}

define private %Object* @intrinsics.java_lang_Object_getClass(%Env* %env, %Object* %o) alwaysinline {
    %c = call %Class* @Object_class(%Object* %o)
    %res = bitcast %Class* %c to %Object*
    ret %Object* %res
}

define private float @intrinsics.java_lang_Math_abs_F(%Env* %env, float %f) alwaysinline {
    %1 = bitcast float %f to i32
    %2 = and i32 %1, 2147483647 ; 0x7fffffff
    %3 = bitcast i32 %2 to float
    ret float %3
}

define private double @intrinsics.java_lang_Math_abs_D(%Env* %env, double %d) alwaysinline {
    %1 = bitcast double %d to i64
    %2 = and i64 %1, 9223372036854775807 ; 0x7fffffffffffffff
    %3 = bitcast i64 %2 to double
    ret double %3
}

define private double @intrinsics.java_lang_Math_sqrt(%Env* %env, double %d) alwaysinline {
    %1 = call double @llvm.sqrt.f64(double %d)
    ret double %1
}

define private double @intrinsics.java_lang_Math_cos(%Env* %env, double %d) alwaysinline {
    %1 = call double @llvm.cos.f64(double %d)
    ret double %1
}

define private double @intrinsics.java_lang_Math_sin(%Env* %env, double %d) alwaysinline {
    %1 = call double @llvm.sin.f64(double %d)
    ret double %1
}

define private void @intrinsics.java_lang_System_arraycopy_C(%Env* %env, %Object* %src, i32 %srcPos, %Object* %dst, i32 %dstPos, i32 %length) alwaysinline {
    %1 = bitcast %Object* %src to %CharArray*
    %2 = getelementptr %CharArray* %1, i32 0, i32 2
    %3 = getelementptr i16* %2, i32 %srcPos
    
    %4 = bitcast %Object* %dst to %CharArray*
    %5 = getelementptr %CharArray* %4, i32 0, i32 2
    %6 = getelementptr i16* %5, i32 %dstPos
    
    %s1 = bitcast i16* %6 to i8*
    %s2 = bitcast i16* %3 to i8*
    %n = sext i32 %length to i64
    
    call void @_bcMoveMemory16(i8* %s1, i8* %s2, i64 %n)
    ret void
}

define private i64 @intrinsics.org_robovm_rt_VM_getArrayValuesAddress(%Env* %env, %Object* %o) alwaysinline {
    %array = bitcast %Object* %o to %ObjectArray*
    %base = getelementptr %ObjectArray* %array, i32 0, i32 2
    %res = ptrtoint %Object** %base to i64
    ret i64 %res
}

define private void @intrinsics.org_robovm_rt_VM_memmove8(%Env* %env, i64 %s1, i64 %s2, i64 %n) alwaysinline {
    %dest = inttoptr i64 %s1 to i8*
    %src = inttoptr i64 %s2 to i8*
    call void @llvm.memmove.p0i8.p0i8.i64(i8* %dest, i8* %src, i64 %n, i32 1, i1 true)
    ret void
}

define private void @intrinsics.org_robovm_rt_VM_memmove16(%Env* %env, i64 %s1, i64 %s2, i64 %n) alwaysinline {
    %dest = inttoptr i64 %s1 to i8*
    %src = inttoptr i64 %s2 to i8*
    call void @_bcMoveMemory16(i8* %dest, i8* %src, i64 %n)
    ret void
}

define private void @intrinsics.org_robovm_rt_VM_memmove32(%Env* %env, i64 %s1, i64 %s2, i64 %n) alwaysinline {
    %dest = inttoptr i64 %s1 to i8*
    %src = inttoptr i64 %s2 to i8*
    call void @_bcMoveMemory32(i8* %dest, i8* %src, i64 %n)
    ret void
}

define private void @intrinsics.org_robovm_rt_VM_memmove64(%Env* %env, i64 %s1, i64 %s2, i64 %n) alwaysinline {
    %dest = inttoptr i64 %s1 to i8*
    %src = inttoptr i64 %s2 to i8*
    %n2 = shl i64 %n, 1
    call void @_bcMoveMemory32(i8* %dest, i8* %src, i64 %n2)
    ret void
}

define linkonce_odr i32 @arraylength(%Object* %o) alwaysinline {
    %array = bitcast %Object* %o to %Array*
    %length = getelementptr %Array* %array, i32 0, i32 1
    %res = load volatile i32* %length
    ret i32 %res
}

define linkonce_odr i32 @iaload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %IntArray*
    %base = getelementptr %IntArray* %array, i32 0, i32 2
    %ptr = getelementptr i32* %base, i32 %index
    %value = load volatile i32* %ptr
    ret i32 %value
}

define linkonce_odr void @iastore(%Object* %o, i32 %index, i32 %value) alwaysinline {
    %array = bitcast %Object* %o to %IntArray*
    %base = getelementptr %IntArray* %array, i32 0, i32 2
    %ptr = getelementptr i32* %base, i32 %index
    store volatile i32 %value, i32* %ptr
    ret void
}

define linkonce_odr i8 @baload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %ByteArray*
    %base = getelementptr %ByteArray* %array, i32 0, i32 2
    %ptr = getelementptr i8* %base, i32 %index
    %value = load volatile i8* %ptr
    ret i8 %value
}

define linkonce_odr void @bastore(%Object* %o, i32 %index, i8 %value) alwaysinline {
    %array = bitcast %Object* %o to %ByteArray*
    %base = getelementptr %ByteArray* %array, i32 0, i32 2
    %ptr = getelementptr i8* %base, i32 %index
    store volatile i8 %value, i8* %ptr
    ret void
}

define linkonce_odr i16 @saload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %ShortArray*
    %base = getelementptr %ShortArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    %value = load volatile i16* %ptr
    ret i16 %value
}

define linkonce_odr void @sastore(%Object* %o, i32 %index, i16 %value) alwaysinline {
    %array = bitcast %Object* %o to %ShortArray*
    %base = getelementptr %ShortArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    store volatile i16 %value, i16* %ptr
    ret void
}

define linkonce_odr i16 @caload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %CharArray*
    %base = getelementptr %CharArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    %value = load volatile i16* %ptr
    ret i16 %value
}

define linkonce_odr void @castore(%Object* %o, i32 %index, i16 %value) alwaysinline {
    %array = bitcast %Object* %o to %CharArray*
    %base = getelementptr %CharArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    store volatile i16 %value, i16* %ptr
    ret void
}

define linkonce_odr float @faload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %FloatArray*
    %base = getelementptr %FloatArray* %array, i32 0, i32 2
    %ptr = getelementptr float* %base, i32 %index
    %value = load volatile float* %ptr
    ret float %value
}

define linkonce_odr void @fastore(%Object* %o, i32 %index, float %value) alwaysinline {
    %array = bitcast %Object* %o to %FloatArray*
    %base = getelementptr %FloatArray* %array, i32 0, i32 2
    %ptr = getelementptr float* %base, i32 %index
    store volatile float %value, float* %ptr
    ret void
}

define linkonce_odr i64 @laload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %LongArray*
    %base = getelementptr %LongArray* %array, i32 0, i32 2
    %ptr = getelementptr i64* %base, i32 %index
    %value = load volatile i64* %ptr
    ret i64 %value
}

define linkonce_odr void @lastore(%Object* %o, i32 %index, i64 %value) alwaysinline {
    %array = bitcast %Object* %o to %LongArray*
    %base = getelementptr %LongArray* %array, i32 0, i32 2
    %ptr = getelementptr i64* %base, i32 %index
    store volatile i64 %value, i64* %ptr
    ret void
}

define linkonce_odr double @daload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %DoubleArray*
    %base = getelementptr %DoubleArray* %array, i32 0, i32 2
    %ptr = getelementptr double* %base, i32 %index
    %value = load volatile double* %ptr
    ret double %value
}

define linkonce_odr void @dastore(%Object* %o, i32 %index, double %value) alwaysinline {
    %array = bitcast %Object* %o to %DoubleArray*
    %base = getelementptr %DoubleArray* %array, i32 0, i32 2
    %ptr = getelementptr double* %base, i32 %index
    store volatile double %value, double* %ptr
    ret void
}

define linkonce_odr %Object* @aaload(%Object* %o, i32 %index) alwaysinline {
    %array = bitcast %Object* %o to %ObjectArray*
    %base = getelementptr %ObjectArray* %array, i32 0, i32 2
    %ptr = getelementptr %Object** %base, i32 %index
    %value = load volatile %Object** %ptr
    ret %Object* %value
}

define linkonce_odr void @aastore(%Object* %o, i32 %index, %Object* %value) alwaysinline {
    %array = bitcast %Object* %o to %ObjectArray*
    %base = getelementptr %ObjectArray* %array, i32 0, i32 2
    %ptr = getelementptr %Object** %base, i32 %index
    store volatile %Object* %value, %Object** %ptr
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

define linkonce_odr double @drem(%Env* %env, double %op1, double %op2) alwaysinline {
    %result = frem double %op1, %op2
    ret double %result
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
    %1 = load volatile i8** %header
    %2 = icmp ne i8* %1, null
    br i1 %2, label %loaded, label %notLoaded
loaded:
    %3 = bitcast i8* %1 to %Object*
    ret %Object* %3
notLoaded:
    %4 = call %Object* @_bcLdcClass(%Env* %env, i8** %header)
    ret %Object* %4
}

define private void @register_finalizable(%Env* %env, %Object* %o) alwaysinline {
    %1 = call %Class* @Object_class(%Object* %o)
    %2 = call i32 @Class_flags(%Class* %1)
    %3 = and i32 %2, 1048576  ; CLASS_FLAG_FINALIZABLE = 0x00100000
    %4 = icmp eq i32 %3, 0
    br i1 %4, label %done, label %register

register:
    call void @_bcRegisterFinalizer(%Env* %env, %Object* %o)
    br label %done

done:
    ret void
}

define private i1 @isinstance_class(%Object* %o, i32 %offset, i32 %id) alwaysinline {
    %c = call %Class* @Object_class(%Object* %o)
    %ti = call %TypeInfo* @Class_typeInfo(%Class* %c)
    %cachedId = call i32 @TypeInfo_cache(%TypeInfo* %ti)
    %isCachedEQ = icmp eq i32 %id, %cachedId
    br i1 %isCachedEQ, label %found, label %notInCache
notInCache:
    %otherOffset = call i32 @TypeInfo_offset(%TypeInfo* %ti)
    %isOffsetLE = icmp ule i32 %offset, %otherOffset
    br i1 %isOffsetLE, label %compareIds, label %notFound
compareIds:
    %1 = bitcast %TypeInfo* %ti to [0 x i8]*
    %2 = getelementptr [0 x i8]* %1, i32 0, i32 %offset
    %3 = bitcast i8* %2 to i32*
    %otherId = load volatile i32* %3
    %isIdEQ = icmp eq i32 %id, %otherId
    br i1 %isIdEQ, label %storeCache, label %notFound
storeCache:
    call void @TypeInfo_cache_store(%TypeInfo* %ti, i32 %id)
    br label %found
found:
    ret i1 1
notFound:
    ret i1 0
}

define private i1 @isinstance_interface(%Object* %o, i32 %id) alwaysinline {
    %c = call %Class* @Object_class(%Object* %o)
    %ti = call %TypeInfo* @Class_typeInfo(%Class* %c)
    %cachedId = call i32 @TypeInfo_cache(%TypeInfo* %ti)
    %isCachedEQ = icmp eq i32 %id, %cachedId
    br i1 %isCachedEQ, label %found, label %notInCache
notInCache:
    %ifCount = call i32 @TypeInfo_interfaceCount(%TypeInfo* %ti)
    %hasIfs = icmp ne i32 %ifCount, 0
    br i1 %hasIfs, label %computeBase, label %notFound
computeBase:
    %offset = call i32 @TypeInfo_offset(%TypeInfo* %ti)
    %ti_18p = bitcast %TypeInfo* %ti to [0 x i8]*
    %1 = getelementptr [0 x i8]* %ti_18p, i32 0, i32 %offset
    %2 = bitcast i8* %1 to [0 x i32]*
    %3 = getelementptr [0 x i32]* %2, i32 0, i32 1
    %base = bitcast i32* %3 to [0 x i32]* ; %base now points to the first interface id
    br label %loop
loop:
    %n_phi = phi i32 [0, %computeBase], [%n, %checkDone]
    %4 = getelementptr [0 x i32]* %base, i32 0, i32 %n_phi
    %n = add i32 %n_phi, 1
    %otherId = load volatile i32* %4
    %isIdEQ = icmp eq i32 %id, %otherId
    br i1 %isIdEQ, label %storeCache, label %checkDone
checkDone:
    %isDone = icmp eq i32 %n, %ifCount
    br i1 %isDone, label %notFound, label %loop
storeCache:
    call void @TypeInfo_cache_store(%TypeInfo* %ti, i32 %id)
    br label %found
found:
    ret i1 1
notFound:
    ret i1 0
}

define private %Object* @checkcast_class(%Env* %env, i8** %header, %Object* %o, i32 %offset, i32 %id) alwaysinline {
    %isNotNull = icmp ne %Object* %o, null
    br i1 %isNotNull, label %notNull, label %null
null:
    ret %Object* null
notNull:
    %isInstance = call i1 @isinstance_class(%Object* %o, i32 %offset, i32 %id)
    br i1 %isInstance, label %ok, label %throw
ok:
    ret %Object* %o
throw:
    call void @_bcThrowClassCastException(%Env* %env, i8** %header, %Object* %o)
    unreachable
}

define private %Object* @checkcast_interface(%Env* %env, i8** %header, %Object* %o, i32 %id) alwaysinline {
    %isNotNull = icmp ne %Object* %o, null
    br i1 %isNotNull, label %notNull, label %null
null:
    ret %Object* null
notNull:
    %isInstance = call i1 @isinstance_interface(%Object* %o, i32 %id)
    br i1 %isInstance, label %ok, label %throw
ok:
    ret %Object* %o
throw:
    call void @_bcThrowClassCastException(%Env* %env, i8** %header, %Object* %o)
    unreachable
}

define private i32 @instanceof_class(%Env* %env, i8** %header, %Object* %o, i32 %offset, i32 %id) alwaysinline {
    %isNotNull = icmp ne %Object* %o, null
    br i1 %isNotNull, label %notNull, label %false
notNull:
    %isInstance = call i1 @isinstance_class(%Object* %o, i32 %offset, i32 %id)
    br i1 %isInstance, label %true, label %false
true:
    ret i32 1
false:
    ret i32 0
}

define private i32 @instanceof_interface(%Env* %env, i8** %header, %Object* %o, i32 %id) alwaysinline {
    %isNotNull = icmp ne %Object* %o, null
    br i1 %isNotNull, label %notNull, label %false
notNull:
    %isInstance = call i1 @isinstance_interface(%Object* %o, i32 %id)
    br i1 %isInstance, label %true, label %false
true:
    ret i32 1
false:
    ret i32 0
}

define private %Object* @checkcast_prim_array(%Env* %env, %Class* %arrayClass, %Object* %o) alwaysinline {
    %isNotNull = icmp ne %Object* %o, null
    br i1 %isNotNull, label %notNull, label %dontThrow
notNull:
    %cls = call %Class* @Object_class(%Object* %o)
    %isSame = icmp eq %Class* %cls, %arrayClass
    br i1 %isSame, label %dontThrow, label %throw
dontThrow:
    ret %Object* %o;
throw:
    call void @_bcThrowClassCastExceptionArray(%Env* %env, %Class* %arrayClass, %Object* %o)
    unreachable
}

define private i32 @instanceof_prim_array(%Env* %env, %Class* %arrayClass, %Object* %o) alwaysinline {
    %isNotNull = icmp ne %Object* %o, null
    br i1 %isNotNull, label %notNull, label %false
notNull:
    %cls = call %Class* @Object_class(%Object* %o)
    %isSame = icmp eq %Class* %cls, %arrayClass
    br i1 %isSame, label %true, label %false
true:
    ret i32 1;
false:
    ret i32 0
}

define private i1 @atomic_cas(i32 %old, i32 %new, i32* %ptr) alwaysinline {
  %1 = cmpxchg i32* %ptr, i32 %old, i32 %new seq_cst
  %2 = icmp eq i32 %1, %old
  ret i1 %2
}

define private void @monitorenter(%Env* %env, %Object* %o) alwaysinline {
    ; Try the common case first before we call _bcMonitorEnter
    %thin = call i32 @Object_lock(%Object* %o)
    %thinBit = and i32 %thin, 1
    %isThin = icmp eq i32 %thinBit, 0
    br i1 %isThin, label %yesThin, label %callBc
yesThin:
    %1 = lshr i32 %thin, 3 ; LW_LOCK_OWNER_SHIFT = 3
    %owner = and i32 %1, 65535 ; LW_LOCK_OWNER_MASK = 0xffff
    %isUnowned = icmp eq i32 %owner, 0
    br i1 %isUnowned, label %tryLock, label %callBc
tryLock:
    %currentThread = call %Thread* @Env_currentThread(%Env* %env)
    %threadId = call i32 @Thread_threadId(%Thread* %currentThread)
    %2 = shl i32 %threadId, 3 ; LW_LOCK_OWNER_SHIFT = 3
    %newThin = or i32 %thin, %2
    %lockPtr = call i32* @Object_lockPtr(%Object* %o)
    %isSuccess = call i1 @atomic_cas(i32 %thin, i32 %newThin, i32* %lockPtr)
    br i1 %isSuccess, label %success, label %callBc
success:
    ret void
callBc:
    tail call void @_bcMonitorEnter(%Env* %env, %Object* %o)
    ret void
}

define private void @monitorexit(%Env* %env, %Object* %o) alwaysinline {
    ; Try the common case first before we call _bcMonitorExit
    %thin = call i32 @Object_lock(%Object* %o)
    %thinBit = and i32 %thin, 1
    %isThin = icmp eq i32 %thinBit, 0
    br i1 %isThin, label %yesThin, label %callBc
yesThin:
    %1 = lshr i32 %thin, 3 ; LW_LOCK_OWNER_SHIFT = 3
    %owner = and i32 %1, 65535 ; LW_LOCK_OWNER_MASK = 0xffff
    %currentThread = call %Thread* @Env_currentThread(%Env* %env)
    %threadId = call i32 @Thread_threadId(%Thread* %currentThread)
    %isOwner = icmp eq i32 %owner, %threadId
    br i1 %isOwner, label %maybeUnlock, label %callBc
maybeUnlock:
    %2 = lshr i32 %thin, 19 ; LW_LOCK_COUNT_SHIFT = 19
    %count = and i32 %2, 8191 ; LW_LOCK_COUNT_MASK = 0x1fff
    %lockPtr = call i32* @Object_lockPtr(%Object* %o)
    %isZero = icmp eq i32 %count, 0
    br i1 %isZero, label %unlock, label %callBc
unlock:
    %newThin = and i32 %thin, 6 ; LW_HASH_STATE_MASK << LW_HASH_STATE_SHIFT (0x3 << 1)
    fence seq_cst
    store volatile i32 %newThin, i32* %lockPtr
    ret void
callBc:
    tail call void @_bcMonitorExit(%Env* %env, %Object* %o)
    ret void
}

define private void @pushNativeFrame(%Env* %env) alwaysinline {
    ; Create a fake StackFrame
    %sf = alloca %StackFrame
    %sf_prev = getelementptr %StackFrame* %sf, i32 0, i32 0
    %sf_returnAddress = getelementptr %StackFrame* %sf, i32 0, i32 1
    %prevStackFrame = call i8* @llvm.frameaddress(i32 0)
    %pc = call i8* @getpc()
    store volatile i8* %prevStackFrame, i8** %sf_prev
    store volatile i8* %pc, i8** %sf_returnAddress
    
    %prev_gw = call %GatewayFrame* @Env_gatewayFrames(%Env* %env)
    %prev_gw_i8p = bitcast %GatewayFrame* %prev_gw to i8*
    
    ; Create the GatewayFrame
    %gw = alloca %GatewayFrame
    %gw_prev = getelementptr %GatewayFrame* %gw, i32 0, i32 0
    %gw_frameAddress = getelementptr %GatewayFrame* %gw, i32 0, i32 1
    %gw_proxyMethod = getelementptr %GatewayFrame* %gw, i32 0, i32 2
    store volatile i8* %prev_gw_i8p, i8** %gw_prev
    %sf_i8p = bitcast %StackFrame* %sf to i8*
    store volatile i8* %sf_i8p, i8** %gw_frameAddress
    store volatile i8* null, i8** %gw_proxyMethod
    
    call void @Env_gatewayFrames_store(%Env* %env, %GatewayFrame* %gw)

    ret void
}

define private void @popNativeFrame(%Env* %env) alwaysinline {
    %curr_gw = call %GatewayFrame* @Env_gatewayFrames(%Env* %env)
    %curr_gw_prev = getelementptr %GatewayFrame* %curr_gw, i32 0, i32 0
    %prev_gw_i8p = load volatile i8** %curr_gw_prev
    %prev_gw = bitcast i8* %prev_gw_i8p to %GatewayFrame*
    call void @Env_gatewayFrames_store(%Env* %env, %GatewayFrame* %prev_gw)
    ret void
}
