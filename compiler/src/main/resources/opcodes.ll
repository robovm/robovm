%Class = type opaque
%Object = type opaque
%Array = type {%Class*, i32}
%BooleanArray = type {%Class*, i32, i8}
%ByteArray = type {%Class*, i32, i8}
%CharArray = type {%Class*, i32, i16}
%ShortArray = type {%Class*, i32, i16}
%IntegerArray = type {%Class*, i32, i32}
%LongArray = type {%Class*, i32, i64}
%FloatArray = type {%Class*, i32, float}
%DoubleArray = type {%Class*, i32, double}
%ObjectArray = type {%Class*, i32, %Object*}

define linkonce_odr i32 @j_arraylength(%Object* %o) alwaysinline {
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %Array*
    %length = getelementptr %Array* %array, i32 0, i32 1
    %res = load i32* %length
    ret i32 %res
}

define linkonce_odr i32 @j_iaload(%Object* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %IntegerArray*
    %base = getelementptr %IntegerArray* %array, i32 0, i32 2
    %ptr = getelementptr i32* %base, i32 %index
    %value = load i32* %ptr
    ret i32 %value
}

define linkonce_odr void @j_iastore(%Object* %o, i32 %index, i32 %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %IntegerArray*
    %base = getelementptr %IntegerArray* %array, i32 0, i32 2
    %ptr = getelementptr i32* %base, i32 %index
    store i32 %value, i32* %ptr
    ret void
}

define linkonce_odr i32 @j_baload(%Object* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %ByteArray*
    %base = getelementptr %ByteArray* %array, i32 0, i32 2
    %ptr = getelementptr i8* %base, i32 %index
    %value_i8 = load i8* %ptr
    %value = sext i8 %value_i8 to i32
    ret i32 %value
}

define linkonce_odr void @j_bastore(%Object* %o, i32 %index, i32 %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %ByteArray*
    %base = getelementptr %ByteArray* %array, i32 0, i32 2
    %ptr = getelementptr i8* %base, i32 %index
    %value_i8 = trunc i32 %value to i8
    %foo = trunc i32 %value to i8
    store i8 %foo, i8* %ptr
    ret void
}

define linkonce_odr i32 @j_saload(%Object* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %ShortArray*
    %base = getelementptr %ShortArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    %value_i16 = load i16* %ptr
    %value = sext i16 %value_i16 to i32
    ret i32 %value
}

define linkonce_odr void @j_sastore(%Object* %o, i32 %index, i32 %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %ShortArray*
    %base = getelementptr %ShortArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    %value_i16 = trunc i32 %value to i16
    store i16 %value_i16, i16* %ptr
    ret void
}

define linkonce_odr i32 @j_caload(%Object* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %CharArray*
    %base = getelementptr %CharArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    %value_i16 = load i16* %ptr
    %value = zext i16 %value_i16 to i32
    ret i32 %value
}

define linkonce_odr void @j_castore(%Object* %o, i32 %index, i32 %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %CharArray*
    %base = getelementptr %CharArray* %array, i32 0, i32 2
    %ptr = getelementptr i16* %base, i32 %index
    %value_i16 = trunc i32 %value to i16
    store i16 %value_i16, i16* %ptr
    ret void
}

define linkonce_odr float @j_faload(%Object* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %FloatArray*
    %base = getelementptr %FloatArray* %array, i32 0, i32 2
    %ptr = getelementptr float* %base, i32 %index
    %value = load float* %ptr
    ret float %value
}

define linkonce_odr void @j_fastore(%Object* %o, i32 %index, float %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %FloatArray*
    %base = getelementptr %FloatArray* %array, i32 0, i32 2
    %ptr = getelementptr float* %base, i32 %index
    store float %value, float* %ptr
    ret void
}

define linkonce_odr i64 @j_laload(%Object* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %LongArray*
    %base = getelementptr %LongArray* %array, i32 0, i32 2
    %ptr = getelementptr i64* %base, i32 %index
    %value = load i64* %ptr
    ret i64 %value
}

define linkonce_odr void @j_lastore(%Object* %o, i32 %index, i64 %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %LongArray*
    %base = getelementptr %LongArray* %array, i32 0, i32 2
    %ptr = getelementptr i64* %base, i32 %index
    store i64 %value, i64* %ptr
    ret void
}

define linkonce_odr double @j_daload(%Object* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %DoubleArray*
    %base = getelementptr %DoubleArray* %array, i32 0, i32 2
    %ptr = getelementptr double* %base, i32 %index
    %value = load double* %ptr
    ret double %value
}

define linkonce_odr void @j_dastore(%Object* %o, i32 %index, double %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %DoubleArray*
    %base = getelementptr %DoubleArray* %array, i32 0, i32 2
    %ptr = getelementptr double* %base, i32 %index
    store double %value, double* %ptr
    ret void
}

define linkonce_odr %Object* @j_aaload(%Object* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %ObjectArray*
    %base = getelementptr %ObjectArray* %array, i32 0, i32 2
    %ptr = getelementptr %Object** %base, i32 %index
    %value = load %Object** %ptr
    ret %Object* %value
}

define linkonce_odr void @j_aastore(%Object* %o, i32 %index, %Object* %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %Object* %o to %ObjectArray*
    %base = getelementptr %ObjectArray* %array, i32 0, i32 2
    %ptr = getelementptr %Object** %base, i32 %index
    store %Object* %value, %Object** %ptr
    ret void
}

