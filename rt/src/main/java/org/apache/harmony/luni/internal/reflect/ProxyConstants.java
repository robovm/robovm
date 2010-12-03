/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.luni.internal.reflect;


interface ProxyConstants {
	final char[] CodeName = new char[] { 'C', 'o', 'd', 'e' };

	final char[] ExceptionsName = new char[] { 'E', 'x', 'c', 'e', 'p', 't',
			'i', 'o', 'n', 's' };

	final char[] Init = new char[] { '<', 'i', 'n', 'i', 't', '>' };

	final int AccPublic = 0x0001;

	final int AccPrivate = 0x0002;

	final int AccProtected = 0x0004;

	final int AccStatic = 0x0008;

	final int AccFinal = 0x0010;

	final int AccSuper = 0x0020;

	final int Utf8Tag = 1;

	final int IntegerTag = 3;

	final int FloatTag = 4;

	final int LongTag = 5;

	final int DoubleTag = 6;

	final int ClassTag = 7;

	final int StringTag = 8;

	final int FieldRefTag = 9;

	final int MethodRefTag = 10;

	final int InterfaceMethodRefTag = 11;

	final int NameAndTypeTag = 12;

	final int OPC_nop = 0;

	final int OPC_aconst_null = 1;

	final int OPC_iconst_m1 = 2;

	final int OPC_iconst_0 = 3;

	final int OPC_iconst_1 = 4;

	final int OPC_iconst_2 = 5;

	final int OPC_iconst_3 = 6;

	final int OPC_iconst_4 = 7;

	final int OPC_iconst_5 = 8;

	final int OPC_lconst_0 = 9;

	final int OPC_lconst_1 = 10;

	final int OPC_fconst_0 = 11;

	final int OPC_fconst_1 = 12;

	final int OPC_fconst_2 = 13;

	final int OPC_dconst_0 = 14;

	final int OPC_dconst_1 = 15;

	final int OPC_bipush = 16;

	final int OPC_sipush = 17;

	final int OPC_ldc = 18;

	final int OPC_ldc_w = 19;

	final int OPC_ldc2_w = 20;

	final int OPC_iload = 21;

	final int OPC_lload = 22;

	final int OPC_fload = 23;

	final int OPC_dload = 24;

	final int OPC_aload = 25;

	final int OPC_iload_0 = 26;

	final int OPC_iload_1 = 27;

	final int OPC_iload_2 = 28;

	final int OPC_iload_3 = 29;

	final int OPC_lload_0 = 30;

	final int OPC_lload_1 = 31;

	final int OPC_lload_2 = 32;

	final int OPC_lload_3 = 33;

	final int OPC_fload_0 = 34;

	final int OPC_fload_1 = 35;

	final int OPC_fload_2 = 36;

	final int OPC_fload_3 = 37;

	final int OPC_dload_0 = 38;

	final int OPC_dload_1 = 39;

	final int OPC_dload_2 = 40;

	final int OPC_dload_3 = 41;

	final int OPC_aload_0 = 42;

	final int OPC_aload_1 = 43;

	final int OPC_aload_2 = 44;

	final int OPC_aload_3 = 45;

	final int OPC_iaload = 46;

	final int OPC_laload = 47;

	final int OPC_faload = 48;

	final int OPC_daload = 49;

	final int OPC_aaload = 50;

	final int OPC_baload = 51;

	final int OPC_caload = 52;

	final int OPC_saload = 53;

	final int OPC_istore = 54;

	final int OPC_lstore = 55;

	final int OPC_fstore = 56;

	final int OPC_dstore = 57;

	final int OPC_astore = 58;

	final int OPC_istore_0 = 59;

	final int OPC_istore_1 = 60;

	final int OPC_istore_2 = 61;

	final int OPC_istore_3 = 62;

	final int OPC_lstore_0 = 63;

	final int OPC_lstore_1 = 64;

	final int OPC_lstore_2 = 65;

	final int OPC_lstore_3 = 66;

	final int OPC_fstore_0 = 67;

	final int OPC_fstore_1 = 68;

	final int OPC_fstore_2 = 69;

	final int OPC_fstore_3 = 70;

	final int OPC_dstore_0 = 71;

	final int OPC_dstore_1 = 72;

	final int OPC_dstore_2 = 73;

	final int OPC_dstore_3 = 74;

	final int OPC_astore_0 = 75;

	final int OPC_astore_1 = 76;

	final int OPC_astore_2 = 77;

	final int OPC_astore_3 = 78;

	final int OPC_iastore = 79;

	final int OPC_lastore = 80;

	final int OPC_fastore = 81;

	final int OPC_dastore = 82;

	final int OPC_aastore = 83;

	final int OPC_bastore = 84;

	final int OPC_castore = 85;

	final int OPC_sastore = 86;

	final int OPC_pop = 87;

	final int OPC_pop2 = 88;

	final int OPC_dup = 89;

	final int OPC_dup_x1 = 90;

	final int OPC_dup_x2 = 91;

	final int OPC_dup2 = 92;

	final int OPC_dup2_x1 = 93;

	final int OPC_dup2_x2 = 94;

	final int OPC_swap = 95;

	final int OPC_iadd = 96;

	final int OPC_ladd = 97;

	final int OPC_fadd = 98;

	final int OPC_dadd = 99;

	final int OPC_isub = 100;

	final int OPC_lsub = 101;

	final int OPC_fsub = 102;

	final int OPC_dsub = 103;

	final int OPC_imul = 104;

	final int OPC_lmul = 105;

	final int OPC_fmul = 106;

	final int OPC_dmul = 107;

	final int OPC_idiv = 108;

	final int OPC_ldiv = 109;

	final int OPC_fdiv = 110;

	final int OPC_ddiv = 111;

	final int OPC_irem = 112;

	final int OPC_lrem = 113;

	final int OPC_frem = 114;

	final int OPC_drem = 115;

	final int OPC_ineg = 116;

	final int OPC_lneg = 117;

	final int OPC_fneg = 118;

	final int OPC_dneg = 119;

	final int OPC_ishl = 120;

	final int OPC_lshl = 121;

	final int OPC_ishr = 122;

	final int OPC_lshr = 123;

	final int OPC_iushr = 124;

	final int OPC_lushr = 125;

	final int OPC_iand = 126;

	final int OPC_land = 127;

	final int OPC_ior = 128;

	final int OPC_lor = 129;

	final int OPC_ixor = 130;

	final int OPC_lxor = 131;

	final int OPC_iinc = 132;

	final int OPC_i2l = 133;

	final int OPC_i2f = 134;

	final int OPC_i2d = 135;

	final int OPC_l2i = 136;

	final int OPC_l2f = 137;

	final int OPC_l2d = 138;

	final int OPC_f2i = 139;

	final int OPC_f2l = 140;

	final int OPC_f2d = 141;

	final int OPC_d2i = 142;

	final int OPC_d2l = 143;

	final int OPC_d2f = 144;

	final int OPC_i2b = 145;

	final int OPC_i2c = 146;

	final int OPC_i2s = 147;

	final int OPC_lcmp = 148;

	final int OPC_fcmpl = 149;

	final int OPC_fcmpg = 150;

	final int OPC_dcmpl = 151;

	final int OPC_dcmpg = 152;

	final int OPC_ifeq = 153;

	final int OPC_ifne = 154;

	final int OPC_iflt = 155;

	final int OPC_ifge = 156;

	final int OPC_ifgt = 157;

	final int OPC_ifle = 158;

	final int OPC_if_icmpeq = 159;

	final int OPC_if_icmpne = 160;

	final int OPC_if_icmplt = 161;

	final int OPC_if_icmpge = 162;

	final int OPC_if_icmpgt = 163;

	final int OPC_if_icmple = 164;

	final int OPC_if_acmpeq = 165;

	final int OPC_if_acmpne = 166;

	final int OPC_goto = 167;

	final int OPC_jsr = 168;

	final int OPC_ret = 169;

	final int OPC_tableswitch = 170;

	final int OPC_lookupswitch = 171;

	final int OPC_ireturn = 172;

	final int OPC_lreturn = 173;

	final int OPC_freturn = 174;

	final int OPC_dreturn = 175;

	final int OPC_areturn = 176;

	final int OPC_return = 177;

	final int OPC_getstatic = 178;

	final int OPC_putstatic = 179;

	final int OPC_getfield = 180;

	final int OPC_putfield = 181;

	final int OPC_invokevirtual = 182;

	final int OPC_invokespecial = 183;

	final int OPC_invokestatic = 184;

	final int OPC_invokeinterface = 185;

	final int OPC_new = 187;

	final int OPC_newarray = 188;

	final int OPC_anewarray = 189;

	final int OPC_arraylength = 190;

	final int OPC_athrow = 191;

	final int OPC_checkcast = 192;

	final int OPC_instanceof = 193;

	final int OPC_monitorenter = 194;

	final int OPC_monitorexit = 195;

	final int OPC_wide = 196;

	final int OPC_multianewarray = 197;

	final int OPC_ifnull = 198;

	final int OPC_ifnonnull = 199;

	final int OPC_goto_w = 200;

	final int OPC_jsr_w = 201;
}
