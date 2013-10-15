/* -----------------------------------------------------------------------------
 * fast_primitive_arrays.i
 *
 * This is a RoboVM-specific patched verison of SWIG's original arrays_java.i file.
 * This doesn't do as much copying as the original code and should be more efficient.
 * Also it maps unsigned types to the signed Java type of the same bit length, e.g.
 * unsigned char is mapped to byte[] instead of short[] as arrays_java.i does.
 */

/* Primitive array support is a combination of SWIG macros and functions in order to reduce 
 * code bloat and aid maintainability. The SWIG preprocessor expands the macros into functions 
 * for inclusion in the generated code. */

/* Array support functions declarations macro */
%define JAVA_ARRAYS_DECL(CTYPE, JNITYPE, JAVATYPE, JFUNCNAME)
%{
static int SWIG_JavaArrayIn##JFUNCNAME (JNIEnv *jenv, JNITYPE **jarr, CTYPE **carr, JNITYPE##Array input);
static void SWIG_JavaArrayArgout##JFUNCNAME (JNIEnv *jenv, JNITYPE *jarr, CTYPE *carr, JNITYPE##Array input);
static JNITYPE##Array SWIG_JavaArrayOut##JFUNCNAME (JNIEnv *jenv, CTYPE *result, jsize sz);
%}
%enddef

/* Array support functions macro */
%define JAVA_ARRAYS_IMPL(CTYPE, JNITYPE, JAVATYPE, JFUNCNAME)
%{
/* CTYPE[] support */
static int SWIG_JavaArrayIn##JFUNCNAME (JNIEnv *jenv, JNITYPE **jarr, CTYPE **carr, JNITYPE##Array input) {
  int i;
  jsize sz;
  if (!input) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null array");
    return 0;
  }
  sz = JCALL1(GetArrayLength, jenv, input);
  *jarr = JCALL2(Get##JAVATYPE##ArrayElements, jenv, input, 0);
  if (!*jarr)
    return 0;
  *carr = (CTYPE*) *jarr;
  return 1;
}

static void SWIG_JavaArrayArgout##JFUNCNAME (JNIEnv *jenv, JNITYPE *jarr, CTYPE *carr, JNITYPE##Array input) {
  JCALL3(Release##JAVATYPE##ArrayElements, jenv, input, jarr, 0);
}

static JNITYPE##Array SWIG_JavaArrayOut##JFUNCNAME (JNIEnv *jenv, CTYPE *result, jsize sz) {
  JNITYPE *arr;
  int i;
  JNITYPE##Array jresult = JCALL1(New##JAVATYPE##Array, jenv, sz);
  if (!jresult)
    return NULL;
  JCALL4(Set##JAVATYPE##ArrayRegion, jenv, jresult, 0, sz, (JNITYPE *) result);
  return jresult;
}
%}
%enddef

%{
#if defined(SWIG_NOINCLUDE) || defined(SWIG_NOARRAYS)
%}

JAVA_ARRAYS_DECL(signed char, jbyte, Byte, Schar)     /* signed char[] */
JAVA_ARRAYS_DECL(unsigned char, jbyte, Byte, Uchar) /* unsigned char[] */
JAVA_ARRAYS_DECL(short, jshort, Short, Short)         /* short[] */
JAVA_ARRAYS_DECL(unsigned short, jchar, Char, Ushort)   /* unsigned short[] */
JAVA_ARRAYS_DECL(int, jint, Int, Int)                 /* int[] */
JAVA_ARRAYS_DECL(unsigned int, jint, Int, Uint)     /* unsigned int[] */
JAVA_ARRAYS_DECL(long, jint, Int, Long)               /* long[] */
JAVA_ARRAYS_DECL(unsigned long, jint, Int, Ulong)   /* unsigned long[] */
JAVA_ARRAYS_DECL(jlong, jlong, Long, Longlong)        /* long long[] */
JAVA_ARRAYS_DECL(float, jfloat, Float, Float)         /* float[] */
JAVA_ARRAYS_DECL(double, jdouble, Double, Double)     /* double[] */

%{
#else
%}

JAVA_ARRAYS_IMPL(signed char, jbyte, Byte, Schar)     /* signed char[] */
JAVA_ARRAYS_IMPL(unsigned char, jbyte, Byte, Uchar) /* unsigned char[] */
JAVA_ARRAYS_IMPL(short, jshort, Short, Short)         /* short[] */
JAVA_ARRAYS_IMPL(unsigned short, jchar, Char, Ushort)   /* unsigned short[] */
JAVA_ARRAYS_IMPL(int, jint, Int, Int)                 /* int[] */
JAVA_ARRAYS_IMPL(unsigned int, jint, Int, Uint)     /* unsigned int[] */
JAVA_ARRAYS_IMPL(long, jint, Int, Long)               /* long[] */
JAVA_ARRAYS_IMPL(unsigned long, jint, Int, Ulong)   /* unsigned long[] */
JAVA_ARRAYS_IMPL(jlong, jlong, Long, Longlong)        /* long long[] */
JAVA_ARRAYS_IMPL(float, jfloat, Float, Float)         /* float[] */
JAVA_ARRAYS_IMPL(double, jdouble, Double, Double)     /* double[] */

%{
#endif
%}


/* The rest of this file has the array typemaps */

/* Arrays of primitive types use the following macro. The array typemaps use support functions. */
%define JAVA_ARRAYS_TYPEMAPS(CTYPE, JTYPE, JNITYPE, JFUNCNAME, JNIDESC)

%typemap(jni) CTYPE[ANY], CTYPE[]               %{JNITYPE##Array%}
%typemap(jtype) CTYPE[ANY], CTYPE[]             %{JTYPE[]%}
%typemap(jstype) CTYPE[ANY], CTYPE[]            %{JTYPE[]%}

%typemap(in) CTYPE[] (JNITYPE *jarr)
%{  if (!SWIG_JavaArrayIn##JFUNCNAME(jenv, &jarr, (CTYPE **)&$1, $input)) return $null; %}
%typemap(in) CTYPE[ANY] (JNITYPE *jarr)
%{  if ($input && JCALL1(GetArrayLength, jenv, $input) != $1_size) {
    SWIG_JavaThrowException(jenv, SWIG_JavaIndexOutOfBoundsException, "incorrect array size");
    return $null;
  }
  if (!SWIG_JavaArrayIn##JFUNCNAME(jenv, &jarr, (CTYPE **)&$1, $input)) return $null; %}
%typemap(argout) CTYPE[ANY], CTYPE[] 
%{ SWIG_JavaArrayArgout##JFUNCNAME(jenv, jarr$argnum, (CTYPE *)$1, $input); %}
%typemap(out) CTYPE[ANY]
%{$result = SWIG_JavaArrayOut##JFUNCNAME(jenv, (CTYPE *)$1, $1_dim0); %}
%typemap(out) CTYPE[] 
%{$result = SWIG_JavaArrayOut##JFUNCNAME(jenv, (CTYPE *)$1, FillMeInAsSizeCannotBeDeterminedAutomatically); %}
%typemap(freearg) CTYPE[ANY], CTYPE[] 
%{ %}

%typemap(javain) CTYPE[ANY], CTYPE[] "$javainput"
%typemap(javaout) CTYPE[ANY], CTYPE[] {
    return $jnicall;
  }

%typemap(memberin) CTYPE[ANY], CTYPE[];
%typemap(globalin) CTYPE[ANY], CTYPE[];
%enddef

JAVA_ARRAYS_TYPEMAPS(signed char, byte, jbyte, Schar, "[B")     /* signed char[ANY] */
JAVA_ARRAYS_TYPEMAPS(unsigned char, byte, jbyte, Uchar, "[B") /* unsigned char[ANY] */
JAVA_ARRAYS_TYPEMAPS(short, short, jshort, Short, "[S")         /* short[ANY] */
JAVA_ARRAYS_TYPEMAPS(unsigned short, char, jchar, Ushort, "[C")   /* unsigned short[ANY] */
JAVA_ARRAYS_TYPEMAPS(int, int, jint, Int, "[I")                 /* int[ANY] */
JAVA_ARRAYS_TYPEMAPS(unsigned int, int, jint, Uint, "[I")     /* unsigned int[ANY] */
JAVA_ARRAYS_TYPEMAPS(long, int, jint, Long, "[I")               /* long[ANY] */
JAVA_ARRAYS_TYPEMAPS(unsigned long, int, jint, Ulong, "[I")   /* unsigned long[ANY] */
JAVA_ARRAYS_TYPEMAPS(long long, long, jlong, Longlong, "[J")    /* long long[ANY] */
JAVA_ARRAYS_TYPEMAPS(float, float, jfloat, Float, "[F")         /* float[ANY] */
JAVA_ARRAYS_TYPEMAPS(double, double, jdouble, Double, "[D")     /* double[ANY] */


%typecheck(SWIG_TYPECHECK_INT8_ARRAY) /* Java byte[] */
    signed char[ANY], signed char[]
    ""

%typecheck(SWIG_TYPECHECK_INT16_ARRAY) /* Java short[] */
    unsigned char[ANY], unsigned char[],
    short[ANY], short[]
    ""

%typecheck(SWIG_TYPECHECK_INT32_ARRAY) /* Java int[] */
    unsigned short[ANY], unsigned short[],
    int[ANY], int[],
    long[ANY], long[]
    ""

%typecheck(SWIG_TYPECHECK_INT64_ARRAY) /* Java long[] */
    unsigned int[ANY], unsigned int[],
    unsigned long[ANY], unsigned long[],
    long long[ANY], long long[]
    ""

%typecheck(SWIG_TYPECHECK_INT128_ARRAY) /* Java BigInteger[] */
    unsigned long long[ANY], unsigned long long[]
    ""

%typecheck(SWIG_TYPECHECK_FLOAT_ARRAY) /* Java float[] */
    float[ANY], float[]
    ""

%typecheck(SWIG_TYPECHECK_DOUBLE_ARRAY) /* Java double[] */
    double[ANY], double[]
    ""
