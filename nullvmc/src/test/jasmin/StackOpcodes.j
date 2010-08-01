.bytecode 49.0

.class public org/nullvm/compiler/tests/opcode/StackOpcodes
.super java/lang/Object

.method public <init>()V
  .limit stack 1
  .limit locals 1
  aload 0
  invokespecial java/lang/Object.<init>()V
  return
.end method

.method public static swap_int(II)I
  .limit stack 4
  .limit locals 2
  iload 0
  iload 1
  swap
  isub
  ireturn
.end method

.method public static dup_int(I)I
  .limit stack 2
  .limit locals 1
  iload 0
  dup
  imul
  ireturn
.end method

.method public static dup2_int(I)I
  .limit stack 4
  .limit locals 1
  iload 0
  iload 0
  dup2
  imul
  imul
  imul
  ireturn
.end method

.method public static dup2_double(D)D
  .limit stack 4
  .limit locals 2
  dload 0
  dup2
  dmul
  dreturn
.end method

.method public static dup2_long(J)J
  .limit stack 4
  .limit locals 2
  lload 0
  dup2
  lmul
  lreturn
.end method

.method public static dup_x1(II)I
  .limit stack 3
  .limit locals 2
  iload 0
  iload 1
  dup_x1
  imul
  isub
  ireturn
.end method

.method public static dup_x2(III)I
  .limit stack 4
  .limit locals 3
  iload 0
  iload 1
  iload 2
  dup_x2
  imul
  isub
  iadd
  ireturn
.end method

.method public static dup_x2(JI)I
  .limit stack 5
  .limit locals 3
  lload 0
  iload 2
  dup_x2
  i2l
  lmul
  l2i
  isub
  ireturn
.end method

.method public static dup2_x1(III)I
  .limit stack 5
  .limit locals 3
  iload 0
  iload 1
  iload 2
  dup2_x1
  idiv
  isub
  iadd
  imul
  ireturn
.end method

.method public static dup2_x1(IJ)I
  .limit stack 5
  .limit locals 3
  iload 0
  lload 1
  dup2_x1
  l2i
  imul
  i2l
  lsub
  l2i
  ireturn
.end method

.method public static dup2_x2(IIII)I
  .limit stack 6
  .limit locals 4
  iload 0
  iload 1
  iload 2
  iload 3
  dup2_x2
  idiv
  isub
  iadd
  imul
  iadd
  ireturn
.end method

.method public static dup2_x2(DD)D
  .limit stack 6
  .limit locals 4
  dload 0
  dload 2
  dup2_x2
  dmul
  dsub
  dreturn
.end method

.method public static dup2_x2(IIJ)I
  .limit stack 6
  .limit locals 4
  iload 0
  iload 1
  lload 2
  dup2_x2
  l2i
  imul
  iadd
  i2l
  lsub
  l2i
  ireturn
.end method

.method public static dup2_x2(JII)I
  .limit stack 6
  .limit locals 4
  lload 0
  iload 2
  iload 3
  dup2_x2
  imul
  i2l
  ladd
  l2i
  isub
  imul
  ireturn
.end method

