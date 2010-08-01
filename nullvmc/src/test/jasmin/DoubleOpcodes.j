.bytecode 49.0

.class public org/nullvm/compiler/tests/opcode/DoubleOpcodes
.super java/lang/Object

.method public <init>()V
  .limit stack 1
  .limit locals 1
  aload 0
  invokespecial java/lang/Object.<init>()V
  return
.end method

.method public static dreturn(D)D
  .limit stack 2
  .limit locals 2
  dload 0
  dreturn
.end method

.method public static dstore(D)D
  .limit stack 2
  .limit locals 4
  dload 0
  dstore 2
  dload 2
  dreturn
.end method

.method public static dconst_0()D
  .limit stack 2
  .limit locals 0
  dconst_0
  dreturn
.end method

.method public static dconst_1()D
  .limit stack 2
  .limit locals 0
  dconst_1
  dreturn
.end method

.method public static dadd(DD)D
  .limit stack 4
  .limit locals 4
  dload 0
  dload 2
  dadd
  dreturn
.end method

.method public static dsub(DD)D
  .limit stack 4
  .limit locals 4
  dload 0
  dload 2
  dsub
  dreturn
.end method

.method public static dmul(DD)D
  .limit stack 4
  .limit locals 4
  dload 0
  dload 2
  dmul
  dreturn
.end method

.method public static ddiv(DD)D
  .limit stack 4
  .limit locals 4
  dload 0
  dload 2
  ddiv
  dreturn
.end method

.method public static drem(DD)D
  .limit stack 4
  .limit locals 4
  dload 0
  dload 2
  drem
  dreturn
.end method

.method public static dneg(D)D
  .limit stack 2
  .limit locals 2
  dload 0
  dneg
  dreturn
.end method

.method public static ldc_min_double()D
  .limit stack 2
  .limit locals 0
  ldc2_w 4.9e-324
  dreturn
.end method

.method public static ldc_max_double()D
  .limit stack 2
  .limit locals 0
  ldc2_w 1.7976931348623157e+308
  dreturn
.end method

.method public static i2d(I)D
  .limit stack 2
  .limit locals 1
  iload 0
  i2d
  dreturn
.end method

.method public static l2d(J)D
  .limit stack 2
  .limit locals 2
  lload 0
  l2d
  dreturn
.end method

.method public static f2d(F)D
  .limit stack 2
  .limit locals 1
  fload 0
  f2d
  dreturn
.end method

.method public static d2i(D)I
  .limit stack 2
  .limit locals 2
  dload 0
  d2i
  ireturn
.end method

.method public static d2l(D)J
  .limit stack 2
  .limit locals 2
  dload 0
  d2l
  lreturn
.end method

.method public static d2f(D)F
  .limit stack 2
  .limit locals 2
  dload 0
  d2f
  freturn
.end method

.method public static dcmpl(DD)I
  .limit stack 4
  .limit locals 4
  dload 0
  dload 2
  dcmpl
  ireturn
.end method

.method public static dcmpg(DD)I
  .limit stack 4
  .limit locals 4
  dload 0
  dload 2
  dcmpg
  ireturn
.end method

