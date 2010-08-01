.bytecode 49.0

.class public org/nullvm/compiler/tests/opcode/ObjectOpcodes
.super java/lang/Object

.field public static staticBooleanField Z
.field public static staticByteField B
.field public static staticIntField I
.field public static staticLongField J

.field public instanceBooleanField Z
.field public instanceByteField B
.field public instanceIntField I
.field public instanceLongField J

.method public <init>()V
  .limit stack 1
  .limit locals 1
  aload 0
  invokespecial java/lang/Object.<init>()V
  return
.end method

.method public <init>(ZBIJ)V
  .limit stack 3
  .limit locals 6
  aload 0
  invokespecial java/lang/Object.<init>()V
  aload 0
  iload 1
  putfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceBooleanField Z
  aload 0
  iload 2
  putfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceByteField B
  aload 0
  iload 3
  putfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceIntField I
  aload 0
  lload 4
  putfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceLongField J
  return
.end method

.method public static staticMethod(BSIJ)J
  .limit stack 8
  .limit locals 5
  lload 3
  iload 2
  i2l
  iload 1
  i2l
  iload 0
  i2l
  lmul
  ladd
  ldiv
  lreturn
.end method

.method public instanceMethod(BSIJ)J
  .limit stack 8
  .limit locals 6
  lload 4
  iload 3
  i2l
  iload 2
  i2l
  iload 1
  i2l
  lmul
  lsub
  ldiv
  lreturn
.end method

.method public static aconst_null()Ljava/lang/Object;
  .limit stack 1
  aconst_null
  areturn
.end method

.method public static invokestatic(BSIJ)J
  .limit stack 5
  .limit locals 6
  iload 0
  iload 1
  iload 2
  lload 3
  invokestatic org/nullvm/compiler/tests/opcode/ObjectOpcodes/staticMethod(BSIJ)J
  lreturn
.end method

.method public static putstatic_boolean(Z)V
  .limit stack 1
  .limit locals 1
  iload 0
  putstatic org/nullvm/compiler/tests/opcode/ObjectOpcodes/staticBooleanField Z
  return
.end method

.method public static getstatic_boolean()Z
  .limit stack 1
  .limit locals 1
  getstatic org/nullvm/compiler/tests/opcode/ObjectOpcodes/staticBooleanField Z
  ireturn
.end method

.method public static putstatic_byte(B)V
  .limit stack 1
  .limit locals 1
  iload 0
  putstatic org/nullvm/compiler/tests/opcode/ObjectOpcodes/staticByteField B
  return
.end method

.method public static getstatic_byte()B
  .limit stack 1
  .limit locals 1
  getstatic org/nullvm/compiler/tests/opcode/ObjectOpcodes/staticByteField B
  ireturn
.end method

.method public static putstatic_int(I)V
  .limit stack 1
  .limit locals 1
  iload 0
  putstatic org/nullvm/compiler/tests/opcode/ObjectOpcodes/staticIntField I
  return
.end method

.method public static getstatic_int()I
  .limit stack 1
  .limit locals 1
  getstatic org/nullvm/compiler/tests/opcode/ObjectOpcodes/staticIntField I
  ireturn
.end method

.method public static putstatic_long(J)V
  .limit stack 2
  .limit locals 2
  lload 0
  putstatic org/nullvm/compiler/tests/opcode/ObjectOpcodes/staticLongField J
  return
.end method

.method public static getstatic_long()J
  .limit stack 2
  .limit locals 2
  getstatic org/nullvm/compiler/tests/opcode/ObjectOpcodes/staticLongField J
  lreturn
.end method

.method public static new_object()Ljava/lang/Object;
  .limit stack 2
  .limit locals 0
  new java/lang/Object
  dup
  invokespecial java/lang/Object/<init>()V
  areturn
.end method

.method public static new_ObjectOpcodes(ZBIJ)Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;
  .limit stack 7
  .limit locals 5
  new org/nullvm/compiler/tests/opcode/ObjectOpcodes
  dup
  iload 0
  iload 1
  iload 2
  lload 3
  invokespecial org/nullvm/compiler/tests/opcode/ObjectOpcodes/<init>(ZBIJ)V
  areturn
.end method

.method public static putfield_boolean(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;Z)V
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  putfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceBooleanField Z
  return
.end method

.method public static getfield_boolean(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)Z
  .limit stack 1
  .limit locals 1
  aload 0
  getfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceBooleanField Z
  ireturn
.end method

.method public static putfield_byte(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;B)V
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  putfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceByteField B
  return
.end method

.method public static getfield_byte(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)B
  .limit stack 1
  .limit locals 1
  aload 0
  getfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceByteField B
  ireturn
.end method

.method public static putfield_int(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;I)V
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  putfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceIntField I
  return
.end method

.method public static getfield_int(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)I
  .limit stack 1
  .limit locals 1
  aload 0
  getfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceIntField I
  ireturn
.end method

.method public static putfield_long(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;J)V
  .limit stack 3
  .limit locals 3
  aload 0
  lload 1
  putfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceLongField J
  return
.end method

.method public static getfield_long(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)J
  .limit stack 2
  .limit locals 2
  aload 0
  getfield org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceLongField J
  lreturn
.end method

.method public static invokevirtual(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;BSIJ)J
  .limit stack 6
  .limit locals 6
  aload 0
  iload 1
  iload 2
  iload 3
  lload 4
  invokevirtual org/nullvm/compiler/tests/opcode/ObjectOpcodes/instanceMethod(BSIJ)J
  lreturn
.end method

