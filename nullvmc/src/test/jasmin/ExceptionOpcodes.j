.bytecode 49.0

.class public org/nullvm/compiler/tests/opcode/ExceptionOpcodes
.super java/lang/Object

.method public <init>()V
  .limit stack 1
  .limit locals 1
  aload 0
  invokespecial java/lang/Object.<init>()V
  return
.end method

.method public static _throw(I)I
  .limit stack 2
  .limit locals 2
  .catch java/lang/Throwable from L0 to L1 using L2
L0:
  iload 0
  ifne L3
  new java/lang/Throwable
  dup
  invokespecial java/lang/Throwable.<init>()V
  athrow
L3:
  iload 0
L1:
  ireturn
L2:
  iconst_m1
  ireturn
.end method

