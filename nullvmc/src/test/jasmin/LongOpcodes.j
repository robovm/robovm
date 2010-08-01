.bytecode 49.0

.class public org/nullvm/compiler/tests/opcode/LongOpcodes
.super java/lang/Object

.method public <init>()V
  .limit stack 1
  .limit locals 1
  aload 0
  invokespecial java/lang/Object.<init>()V
  return
.end method

.method public static lreturn(J)J
  .limit stack 2
  .limit locals 2
  lload 0
  lreturn
.end method

.method public static lstore(J)J
  .limit stack 2
  .limit locals 4
  lload 0
  lstore 2
  lload 2
  lreturn
.end method

.method public static lconst_0()J
  .limit stack 2
  .limit locals 0
  lconst_0
  lreturn
.end method

.method public static lconst_1()J
  .limit stack 2
  .limit locals 0
  lconst_1
  lreturn
.end method

.method public static ladd(JJ)J
  .limit stack 4
  .limit locals 4
  lload 0
  lload 2
  ladd
  lreturn
.end method

.method public static lsub(JJ)J
  .limit stack 4
  .limit locals 4
  lload 0
  lload 2
  lsub
  lreturn
.end method

.method public static lmul(JJ)J
  .limit stack 4
  .limit locals 4
  lload 0
  lload 2
  lmul
  lreturn
.end method

.method public static ldiv(JJ)J
  .limit stack 4
  .limit locals 4
  lload 0
  lload 2
  ldiv
  lreturn
.end method

.method public static lrem(JJ)J
  .limit stack 4
  .limit locals 4
  lload 0
  lload 2
  lrem
  lreturn
.end method

.method public static lneg(J)J
  .limit stack 2
  .limit locals 2
  lload 0
  lneg
  lreturn
.end method

.method public static lshl(JI)J
  .limit stack 3
  .limit locals 3
  lload 0
  iload 2
  lshl
  lreturn
.end method

.method public static lshr(JI)J
  .limit stack 3
  .limit locals 3
  lload 0
  iload 2
  lshr
  lreturn
.end method

.method public static lushr(JI)J
  .limit stack 3
  .limit locals 3
  lload 0
  iload 2
  lushr
  lreturn
.end method

.method public static land(JJ)J
  .limit stack 4
  .limit locals 4
  lload 0
  lload 2
  land
  lreturn
.end method

.method public static lor(JJ)J
  .limit stack 4
  .limit locals 4
  lload 0
  lload 2
  lor
  lreturn
.end method

.method public static lxor(JJ)J
  .limit stack 4
  .limit locals 4
  lload 0
  lload 2
  lxor
  lreturn
.end method

.method public static i2l(I)J
  .limit stack 2
  .limit locals 1
  iload 0
  i2l
  lreturn
.end method

.method public static l2i(J)I
  .limit stack 2
  .limit locals 2
  lload 0
  l2i
  ireturn
.end method

.method public static lcmp(JJ)I
  .limit stack 4
  .limit locals 4
  lload 0
  lload 2
  lcmp
  ireturn
.end method

.method public static ldc_min_long()J
  .limit stack 2
  .limit locals 0
  ldc2_w -9223372036854775808
  lreturn
.end method

.method public static ldc_max_long()J
  .limit stack 2
  .limit locals 0
  ldc2_w 9223372036854775807
  lreturn
.end method

