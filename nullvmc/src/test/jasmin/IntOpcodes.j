.bytecode 49.0

.class public org/nullvm/compiler/tests/opcode/IntOpcodes
.super java/lang/Object

.method public <init>()V
  .limit stack 1
  .limit locals 1
  aload 0
  invokespecial java/lang/Object.<init>()V
  return
.end method

.method public static ireturn(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  ireturn
.end method

.method public static istore(I)I
  .limit stack 1
  .limit locals 2
  iload 0
  istore 1
  iload 1
  ireturn
.end method

.method public static iconst_m1()I
  .limit stack 1
  .limit locals 0
  iconst_m1
  ireturn
.end method

.method public static iconst_0()I
  .limit stack 1
  .limit locals 0
  iconst_0
  ireturn
.end method

.method public static iconst_1()I
  .limit stack 1
  .limit locals 0
  iconst_1
  ireturn
.end method

.method public static iconst_2()I
  .limit stack 1
  .limit locals 0
  iconst_2
  ireturn
.end method

.method public static iconst_3()I
  .limit stack 1
  .limit locals 0
  iconst_3
  ireturn
.end method

.method public static iconst_4()I
  .limit stack 1
  .limit locals 0
  iconst_4
  ireturn
.end method

.method public static iconst_5()I
  .limit stack 1
  .limit locals 0
  iconst_5
  ireturn
.end method

.method public static iadd(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  iadd
  ireturn
.end method

.method public static isub(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  isub
  ireturn
.end method

.method public static imul(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  imul
  ireturn
.end method

.method public static idiv(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  idiv
  ireturn
.end method

.method public static irem(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  irem
  ireturn
.end method

.method public static ineg(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  ineg
  ireturn
.end method

.method public static ishl(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  ishl
  ireturn
.end method

.method public static ishr(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  ishr
  ireturn
.end method

.method public static iushr(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  iushr
  ireturn
.end method

.method public static iand(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  iand
  ireturn
.end method

.method public static ior(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  ior
  ireturn
.end method

.method public static ixor(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  ixor
  ireturn
.end method

.method public static i2b(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  i2b
  ireturn
.end method

.method public static i2c(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  i2c
  ireturn
.end method

.method public static i2s(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  i2s
  ireturn
.end method

.method public static bipush_m1()I
  .limit stack 1
  .limit locals 0
  bipush -1
  ireturn
.end method

.method public static bipush_127()I
  .limit stack 1
  .limit locals 0
  bipush 127
  ireturn
.end method

.method public static sipush_m1()I
  .limit stack 1
  .limit locals 0
  sipush -1
  ireturn
.end method

.method public static sipush_32767()I
  .limit stack 1
  .limit locals 0
  sipush 32767
  ireturn
.end method

.method public static ldc_min_int()I
  .limit stack 1
  .limit locals 0
  ldc 0x80000000
  ireturn
.end method

.method public static ldc_max_int()I
  .limit stack 1
  .limit locals 0
  ldc 0x7fffffff
  ireturn
.end method

.method public static iinc_m128(I)I
  .limit stack 1
  .limit locals 1
  iinc 0 -128
  iload 0
  ireturn
.end method

.method public static iinc_127(I)I
  .limit stack 1
  .limit locals 1
  iinc 0 127
  iload 0
  ireturn
.end method

