.bytecode 49.0

.class public org/nullvm/compiler/tests/opcode/ArrayOpcodes
.super java/lang/Object

.method public <init>()V
  .limit stack 1
  .limit locals 1
  aload 0
  invokespecial java/lang/Object.<init>()V
  return
.end method

.method public static newarray_int(I)[I
  .limit stack 1
  .limit locals 1
  iload 0
  newarray int
  areturn
.end method

.method public static newarray_byte(I)[B
  .limit stack 1
  .limit locals 1
  iload 0
  newarray byte
  areturn
.end method

.method public static newarray_short(I)[S
  .limit stack 1
  .limit locals 1
  iload 0
  newarray short
  areturn
.end method

.method public static newarray_char(I)[C
  .limit stack 1
  .limit locals 1
  iload 0
  newarray char
  areturn
.end method

.method public static newarray_boolean(I)[Z
  .limit stack 1
  .limit locals 1
  iload 0
  newarray boolean
  areturn
.end method

.method public static newarray_float(I)[F
  .limit stack 1
  .limit locals 1
  iload 0
  newarray float
  areturn
.end method

.method public static newarray_long(I)[J
  .limit stack 1
  .limit locals 1
  iload 0
  newarray long
  areturn
.end method

.method public static newarray_double(I)[D
  .limit stack 1
  .limit locals 1
  iload 0
  newarray double
  areturn
.end method

.method public static newarray_object(I)[Ljava/lang/Object;
  .limit stack 1
  .limit locals 1
  iload 0
  anewarray java/lang/Object
  areturn
.end method

.method public static arraylength_int([I)I
  .limit stack 1
  .limit locals 1
  aload 0
  arraylength
  ireturn
.end method

.method public static arraylength_byte([B)I
  .limit stack 1
  .limit locals 1
  aload 0
  arraylength
  ireturn
.end method

.method public static arraylength_short([S)I
  .limit stack 1
  .limit locals 1
  aload 0
  arraylength
  ireturn
.end method

.method public static arraylength_char([C)I
  .limit stack 1
  .limit locals 1
  aload 0
  arraylength
  ireturn
.end method

.method public static arraylength_boolean([Z)I
  .limit stack 1
  .limit locals 1
  aload 0
  arraylength
  ireturn
.end method

.method public static arraylength_float([F)I
  .limit stack 1
  .limit locals 1
  aload 0
  arraylength
  ireturn
.end method

.method public static arraylength_long([J)I
  .limit stack 1
  .limit locals 1
  aload 0
  arraylength
  ireturn
.end method

.method public static arraylength_double([D)I
  .limit stack 1
  .limit locals 1
  aload 0
  arraylength
  ireturn
.end method

.method public static arraylength_object([Ljava/lang/Object;)I
  .limit stack 1
  .limit locals 1
  aload 0
  arraylength
  ireturn
.end method

.method public static iaload([II)I
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  iaload
  ireturn
.end method

.method public static iastore([III)V
  .limit stack 3
  .limit locals 3
  aload 0
  iload 1
  iload 2
  iastore
  return
.end method

.method public static baload_byte([BI)B
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  baload
  ireturn
.end method

.method public static bastore_byte([BIB)V
  .limit stack 3
  .limit locals 3
  aload 0
  iload 1
  iload 2
  bastore
  return
.end method

.method public static baload_boolean([ZI)Z
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  baload
  ireturn
.end method

.method public static bastore_boolean([ZIZ)V
  .limit stack 3
  .limit locals 3
  aload 0
  iload 1
  iload 2
  bastore
  return
.end method

.method public static saload([SI)S
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  saload
  ireturn
.end method

.method public static sastore([SIS)V
  .limit stack 3
  .limit locals 3
  aload 0
  iload 1
  iload 2
  sastore
  return
.end method

.method public static caload([CI)C
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  caload
  ireturn
.end method

.method public static castore([CIC)V
  .limit stack 3
  .limit locals 3
  aload 0
  iload 1
  iload 2
  castore
  return
.end method

.method public static faload([FI)F
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  faload
  freturn
.end method

.method public static fastore([FIF)V
  .limit stack 3
  .limit locals 3
  aload 0
  iload 1
  fload 2
  fastore
  return
.end method

.method public static laload([JI)J
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  laload
  lreturn
.end method

.method public static lastore([JIJ)V
  .limit stack 4
  .limit locals 4
  aload 0
  iload 1
  lload 2
  lastore
  return
.end method

.method public static daload([DI)D
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  daload
  dreturn
.end method

.method public static dastore([DID)V
  .limit stack 4
  .limit locals 4
  aload 0
  iload 1
  dload 2
  dastore
  return
.end method

.method public static aaload([Ljava/lang/Object;I)Ljava/lang/Object;
  .limit stack 2
  .limit locals 2
  aload 0
  iload 1
  aaload
  areturn
.end method

.method public static aastore([Ljava/lang/Object;ILjava/lang/Object;)V
  .limit stack 3
  .limit locals 3
  aload 0
  iload 1
  aload 2
  aastore
  return
.end method

.method public static multianewarray_int(III)[[[I
  .limit stack 3
  .limit locals 3
  iload 0
  iload 1
  iload 2
  multianewarray [[[I 3
  areturn
.end method

.method public static multianewarray_int_partial(II)[[[I
  .limit stack 3
  .limit locals 3
  iload 0
  iload 1
  multianewarray [[[I 2
  areturn
.end method

.method public static multianewarray_byte(II)[[B
  .limit stack 2
  .limit locals 2
  iload 0
  iload 1
  multianewarray [[B 2
  areturn
.end method

.method public static multianewarray_object(III)[[[Ljava/lang/Object;
  .limit stack 3
  .limit locals 3
  iload 0
  iload 1
  iload 2
  multianewarray [[[Ljava/lang/Object; 3
  areturn
.end method
