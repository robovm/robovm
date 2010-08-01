%jclass = type opaque
%jobject = type opaque
%jarray = type {%jclass*, i32}

;define %jobject* @j_newarray_I(i32 %length) alwaysinline {
;}

define linkonce_odr i32 @j_arraylength(%jobject* %o) alwaysinline {
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %length = getelementptr %jarray* %array, i32 0, i32 1
    %res = load i32* %length
    ret i32 %res
}

define linkonce_odr i32 @j_iaload(%jobject* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to i32*
    %ptr = getelementptr i32* %base, i32 %index
    %value = load i32* %ptr
    ret i32 %value
}

define linkonce_odr void @j_iastore(%jobject* %o, i32 %index, i32 %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to i32*
    %ptr = getelementptr i32* %base, i32 %index
    store i32 %value, i32* %ptr
    ret void
}

define linkonce_odr i32 @j_baload(%jobject* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to i8*
    %ptr = getelementptr i8* %base, i32 %index
    %value_i8 = load i8* %ptr
    %value = sext i8 %value_i8 to i32
    ret i32 %value
}

define linkonce_odr void @j_bastore(%jobject* %o, i32 %index, i32 %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to i8*
    %ptr = getelementptr i8* %base, i32 %index
    %value_i8 = trunc i32 %value to i8
    %foo = trunc i32 %value to i8
    store i8 %foo, i8* %ptr
    ret void
}

define linkonce_odr i32 @j_saload(%jobject* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to i16*
    %ptr = getelementptr i16* %base, i32 %index
    %value_i16 = load i16* %ptr
    %value = sext i16 %value_i16 to i32
    ret i32 %value
}

define linkonce_odr void @j_sastore(%jobject* %o, i32 %index, i32 %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to i16*
    %ptr = getelementptr i16* %base, i32 %index
    %value_i16 = trunc i32 %value to i16
    store i16 %value_i16, i16* %ptr
    ret void
}

define linkonce_odr i32 @j_caload(%jobject* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to i16*
    %ptr = getelementptr i16* %base, i32 %index
    %value_i16 = load i16* %ptr
    %value = zext i16 %value_i16 to i32
    ret i32 %value
}

define linkonce_odr void @j_castore(%jobject* %o, i32 %index, i32 %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to i16*
    %ptr = getelementptr i16* %base, i32 %index
    %value_i16 = trunc i32 %value to i16
    store i16 %value_i16, i16* %ptr
    ret void
}

define linkonce_odr float @j_faload(%jobject* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to float*
    %ptr = getelementptr float* %base, i32 %index
    %value = load float* %ptr
    ret float %value
}

define linkonce_odr void @j_fastore(%jobject* %o, i32 %index, float %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to float*
    %ptr = getelementptr float* %base, i32 %index
    store float %value, float* %ptr
    ret void
}

define linkonce_odr i64 @j_laload(%jobject* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to i64*
    %ptr = getelementptr i64* %base, i32 %index
    %value = load i64* %ptr
    ret i64 %value
}

define linkonce_odr void @j_lastore(%jobject* %o, i32 %index, i64 %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to i64*
    %ptr = getelementptr i64* %base, i32 %index
    store i64 %value, i64* %ptr
    ret void
}

define linkonce_odr double @j_daload(%jobject* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to double*
    %ptr = getelementptr double* %base, i32 %index
    %value = load double* %ptr
    ret double %value
}

define linkonce_odr void @j_dastore(%jobject* %o, i32 %index, double %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to double*
    %ptr = getelementptr double* %base, i32 %index
    store double %value, double* %ptr
    ret void
}

define linkonce_odr %jobject* @j_aaload(%jobject* %o, i32 %index) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to %jobject**
    %ptr = getelementptr %jobject** %base, i32 %index
    %value = load %jobject** %ptr
    ret %jobject* %value
}

define linkonce_odr void @j_aastore(%jobject* %o, i32 %index, %jobject* %value) alwaysinline {
    ; TODO: check bounds
    ; TODO: throw NPE if array == null
    %array = bitcast %jobject* %o to %jarray*
    %tmp = getelementptr %jarray* %array, i32 1
    %base = bitcast %jarray* %tmp to %jobject**
    %ptr = getelementptr %jobject** %base, i32 %index
    store %jobject* %value, %jobject** %ptr
    ret void
}

