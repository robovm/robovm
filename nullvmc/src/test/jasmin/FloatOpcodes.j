.bytecode 49.0

.class public org/nullvm/compiler/tests/opcode/FloatOpcodes
.super java/lang/Object

.method public <init>()V
  .limit stack 1
  .limit locals 1
  aload 0
  invokespecial java/lang/Object.<init>()V
  return
.end method

.method public static freturn(F)F
  .limit stack 1
  .limit locals 1
  fload 0
  freturn
.end method

.method public static fstore(F)F
  .limit stack 1
  .limit locals 2
  fload 0
  fstore 1
  fload 1
  freturn
.end method

.method public static fconst_0()F
  .limit stack 1
  .limit locals 0
  fconst_0
  freturn
.end method

.method public static fconst_1()F
  .limit stack 1
  .limit locals 0
  fconst_1
  freturn
.end method

.method public static fconst_2()F
  .limit stack 1
  .limit locals 0
  fconst_2
  freturn
.end method

.method public static fadd(FF)F
  .limit stack 2
  .limit locals 2
  fload 0
  fload 1
  fadd
  freturn
.end method

.method public static fsub(FF)F
  .limit stack 2
  .limit locals 2
  fload 0
  fload 1
  fsub
  freturn
.end method

.method public static fmul(FF)F
  .limit stack 2
  .limit locals 2
  fload 0
  fload 1
  fmul
  freturn
.end method

.method public static fdiv(FF)F
  .limit stack 2
  .limit locals 2
  fload 0
  fload 1
  fdiv
  freturn
.end method

.method public static frem(FF)F
  .limit stack 2
  .limit locals 2
  fload 0
  fload 1
  frem
  freturn
.end method

.method public static fneg(F)F
  .limit stack 1
  .limit locals 1
  fload 0
  fneg
  freturn
.end method

.method public static ldc_min_float()F
  .limit stack 1
  .limit locals 0
  ldc 1.4e-45f
  freturn
.end method

.method public static ldc_max_float()F
  .limit stack 1
  .limit locals 0
  ldc 3.4028235e+38f
  freturn
.end method

.method public static i2f(I)F
  .limit stack 1
  .limit locals 1
  iload 0
  i2f
  freturn
.end method

.method public static f2i(F)I
  .limit stack 1
  .limit locals 1
  fload 0
  f2i
  ireturn
.end method

.method public static l2f(J)F
  .limit stack 2
  .limit locals 2
  lload 0
  l2f
  freturn
.end method

.method public static f2l(F)J
  .limit stack 2
  .limit locals 1
  fload 0
  f2l
  lreturn
.end method

.method public static fcmpl(FF)I
  .limit stack 2
  .limit locals 2
  fload 0
  fload 1
  fcmpl
  ireturn
.end method

.method public static fcmpg(FF)I
  .limit stack 2
  .limit locals 2
  fload 0
  fload 1
  fcmpg
  ireturn
.end method

