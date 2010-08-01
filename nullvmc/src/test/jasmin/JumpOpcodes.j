.bytecode 49.0

.class public org/nullvm/compiler/tests/opcode/JumpOpcodes
.super java/lang/Object

.method public <init>()V
  .limit stack 1
  .limit locals 1
  aload 0
  invokespecial java/lang/Object.<init>()V
  return
.end method

.method public static _goto()I
  .limit stack 2
  .limit locals 0
  iconst_5
  goto end
  pop
  iconst_4
end:
  ireturn
.end method

.method public static ifeq(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  ifeq match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static ifne(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  ifne match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static iflt(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  iflt match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static ifgt(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  ifgt match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static ifle(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  ifle match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static ifge(I)I
  .limit stack 1
  .limit locals 1
  iload 0
  ifge match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static if_icmpeq(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  if_icmpeq match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static if_icmpne(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  if_icmpne match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static if_icmplt(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  if_icmplt match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static if_icmpgt(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  if_icmpgt match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static if_icmple(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  if_icmple match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static if_icmpge(II)I
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  if_icmpge match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static ifnull(Ljava/lang/Object;)I
  .limit stack 1
  .limit locals 1
  aload 0
  ifnull match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static ifnonnull(Ljava/lang/Object;)I
  .limit stack 1
  .limit locals 1
  aload 0
  ifnonnull match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static if_acmpeq(Ljava/lang/Object;Ljava/lang/Object;)I
  .limit stack 2
  .limit locals 2
  aload 0
  aload 1
  if_acmpeq match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static if_acmpne(Ljava/lang/Object;Ljava/lang/Object;)I
  .limit stack 2
  .limit locals 2
  aload 0
  aload 1
  if_acmpne match
  iconst_0
  ireturn
match:
  iconst_1
  ireturn
.end method

.method public static tableswitch(I)I
  .limit stack 2
  .limit locals 2
  iload 0
  tableswitch 0 2
      zero
      one
      two
    default: def
zero:
  ldc 100
  ireturn
one:
  ldc 200
  ireturn
two:
  ldc 300
  ireturn
def:
  iconst_0
  ireturn
.end method

.method public static lookupswitch(I)I
  .limit stack 2
  .limit locals 2
  iload 0
  lookupswitch
      1: one
      10: ten
      default: def
one:
  ldc 100
  ireturn
ten:
  ldc 200
  ireturn
def:
  iconst_0
  ireturn
.end method

.method public static jsr()I
  .limit stack 3
  .limit locals 1
  iconst_0
  jsr sub1
  jsr sub2
  ireturn
sub1:
  astore 0
  iconst_1
  ret 0
sub2:
  astore 0
  iconst_2
  ret 0
.end method

