/*
 *  InsnNames.java
 *
 *  Copyright (C) 1999 by Kresten Krab Thorup <krab@daimi.au.dk>
 *
 *  This file is part of "Kresten's Verifier for Java Byte Codes"
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Library General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Library General Public License for more details.
 *
 *  You should have received a copy of the GNU Library General Public
 *  License along with this library; if not, write to the Free
 *  Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 *  MA 02111-1307, USA
 *
 */

//
//  This is just a place-holder for the definition of opcodes.
//

package verify.bytecode;

public interface InsnNames {

  static final int op_nop = 0x00;
  static final int op_aconst_null = 0x01;
  static final int op_iconst_m1 = 0x02;
  static final int op_iconst_0 = 0x03;
  static final int op_iconst_1 = 0x04;
  static final int op_iconst_2 = 0x05;
  static final int op_iconst_3 = 0x06;
  static final int op_iconst_4 = 0x07;
  static final int op_iconst_5 = 0x08;
  static final int op_lconst_0 = 0x09;
  static final int op_lconst_1 = 0x0a;
  static final int op_fconst_0 = 0x0b;
  static final int op_fconst_1 = 0x0c;
  static final int op_fconst_2 = 0x0d;
  static final int op_dconst_0 = 0x0e;
  static final int op_dconst_1 = 0x0f;
  static final int op_bipush = 0x10;
  static final int op_sipush = 0x11;
  static final int op_ldc = 0x12;
  static final int op_ldc_w = 0x13;
  static final int op_ldc2_w = 0x14;
  static final int op_iload = 0x15;
  static final int op_lload = 0x16;
  static final int op_fload = 0x17;
  static final int op_dload = 0x18;
  static final int op_aload = 0x19;
  static final int op_iload_0 = 0x1a;
  static final int op_iload_1 = 0x1b;
  static final int op_iload_2 = 0x1c;
  static final int op_iload_3 = 0x1d;
  static final int op_lload_0 = 0x1e;
  static final int op_lload_1 = 0x1f;
  static final int op_lload_2 = 0x20;
  static final int op_lload_3 = 0x21;
  static final int op_fload_0 = 0x22;
  static final int op_fload_1 = 0x23;
  static final int op_fload_2 = 0x24;
  static final int op_fload_3 = 0x25;
  static final int op_dload_0 = 0x26;
  static final int op_dload_1 = 0x27;
  static final int op_dload_2 = 0x28;
  static final int op_dload_3 = 0x29;
  static final int op_aload_0 = 0x2a;
  static final int op_aload_1 = 0x2b;
  static final int op_aload_2 = 0x2c;
  static final int op_aload_3 = 0x2d;
  static final int op_iaload = 0x2e;
  static final int op_laload = 0x2f;
  static final int op_faload = 0x30;
  static final int op_daload = 0x31;
  static final int op_aaload = 0x32;
  static final int op_baload = 0x33;
  static final int op_caload = 0x34;
  static final int op_saload = 0x35;
  static final int op_istore = 0x36;
  static final int op_lstore = 0x37;
  static final int op_fstore = 0x38;
  static final int op_dstore = 0x39;
  static final int op_astore = 0x3a;
  static final int op_istore_0 = 0x3b;
  static final int op_istore_1 = 0x3c;
  static final int op_istore_2 = 0x3d;
  static final int op_istore_3 = 0x3e;
  static final int op_lstore_0 = 0x3f;
  static final int op_lstore_1 = 0x40;
  static final int op_lstore_2 = 0x41;
  static final int op_lstore_3 = 0x42;
  static final int op_fstore_0 = 0x43;
  static final int op_fstore_1 = 0x44;
  static final int op_fstore_2 = 0x45;
  static final int op_fstore_3 = 0x46;
  static final int op_dstore_0 = 0x47;
  static final int op_dstore_1 = 0x48;
  static final int op_dstore_2 = 0x49;
  static final int op_dstore_3 = 0x4a;
  static final int op_astore_0 = 0x4b;
  static final int op_astore_1 = 0x4c;
  static final int op_astore_2 = 0x4d;
  static final int op_astore_3 = 0x4e;
  static final int op_iastore = 0x4f;
  static final int op_lastore = 0x50;
  static final int op_fastore = 0x51;
  static final int op_dastore = 0x52;
  static final int op_aastore = 0x53;
  static final int op_bastore = 0x54;
  static final int op_castore = 0x55;
  static final int op_sastore = 0x56;
  static final int op_pop = 0x57;
  static final int op_pop2 = 0x58;
  static final int op_dup = 0x59;
  static final int op_dup_x1 = 0x5a;
  static final int op_dup_x2 = 0x5b;
  static final int op_dup2 = 0x5c;
  static final int op_dup2_x1 = 0x5d;
  static final int op_dup2_x2 = 0x5e;
  static final int op_swap = 0x5f;
  static final int op_iadd = 0x60;
  static final int op_ladd = 0x61;
  static final int op_fadd = 0x62;
  static final int op_dadd = 0x63;
  static final int op_isub = 0x64;
  static final int op_lsub = 0x65;
  static final int op_fsub = 0x66;
  static final int op_dsub = 0x67;
  static final int op_imul = 0x68;
  static final int op_lmul = 0x69;
  static final int op_fmul = 0x6a;
  static final int op_dmul = 0x6b;
  static final int op_idiv = 0x6c;
  static final int op_ldiv = 0x6d;
  static final int op_fdiv = 0x6e;
  static final int op_ddiv = 0x6f;
  static final int op_irem = 0x70;
  static final int op_lrem = 0x71;
  static final int op_frem = 0x72;
  static final int op_drem = 0x73;
  static final int op_ineg = 0x74;
  static final int op_lneg = 0x75;
  static final int op_fneg = 0x76;
  static final int op_dneg = 0x77;
  static final int op_ishl = 0x78;
  static final int op_lshl = 0x79;
  static final int op_ishr = 0x7a;
  static final int op_lshr = 0x7b;
  static final int op_iushr = 0x7c;
  static final int op_lushr = 0x7d;
  static final int op_iand = 0x7e;
  static final int op_land = 0x7f;
  static final int op_ior = 0x80;
  static final int op_lor = 0x81;
  static final int op_ixor = 0x82;
  static final int op_lxor = 0x83;
  static final int op_iinc = 0x84;
  static final int op_i2l = 0x85;
  static final int op_i2f = 0x86;
  static final int op_i2d = 0x87;
  static final int op_l2i = 0x88;
  static final int op_l2f = 0x89;
  static final int op_l2d = 0x8a;
  static final int op_f2i = 0x8b;
  static final int op_f2l = 0x8c;
  static final int op_f2d = 0x8d;
  static final int op_d2i = 0x8e;
  static final int op_d2l = 0x8f;
  static final int op_d2f = 0x90;
  static final int op_i2b = 0x91;
  static final int op_i2c = 0x92;
  static final int op_i2s = 0x93;
  static final int op_lcmp = 0x94;
  static final int op_fcmpl = 0x95;
  static final int op_fcmpg = 0x96;
  static final int op_dcmpl = 0x97;
  static final int op_dcmpg = 0x98;
  static final int op_ifeq = 0x99;
  static final int op_ifne = 0x9a;
  static final int op_iflt = 0x9b;
  static final int op_ifge = 0x9c;
  static final int op_ifgt = 0x9d;
  static final int op_ifle = 0x9e;
  static final int op_if_icmpeq = 0x9f;
  static final int op_if_icmpne = 0xa0;
  static final int op_if_icmplt = 0xa1;
  static final int op_if_icmpge = 0xa2;
  static final int op_if_icmpgt = 0xa3;
  static final int op_if_icmple = 0xa4;
  static final int op_if_acmpeq = 0xa5;
  static final int op_if_acmpne = 0xa6;
  static final int op_goto = 0xa7; 
  static final int op_jsr = 0xa8;
  static final int op_ret = 0xa9;
  static final int op_tableswitch = 0xaa;
  static final int op_lookupswitch = 0xab;
  static final int op_ireturn = 0xac;
  static final int op_lreturn = 0xad;
  static final int op_freturn = 0xae;
  static final int op_dreturn = 0xaf;
  static final int op_areturn = 0xb0;
  static final int op_return = 0xb1;
  static final int op_getstatic = 0xb2;
  static final int op_putstatic = 0xb3;
  static final int op_getfield = 0xb4;
  static final int op_putfield = 0xb5;
  static final int op_invokevirtual = 0xb6;
  static final int op_invokespecial = 0xb7;
  static final int op_invokestatic = 0xb8;
  static final int op_invokeinterface = 0xb9;
  static final int op_xxxunusedxxx1 = 0xba;
  static final int op_new = 0xbb;
  static final int op_newarray = 0xbc;
  static final int op_anewarray = 0xbd;
  static final int op_arraylength = 0xbe;
  static final int op_athrow = 0xbf;
  static final int op_checkcast = 0xc0;
  static final int op_instanceof = 0xc1;
  static final int op_monitorenter = 0xc2;
  static final int op_monitorexit = 0xc3;
  static final int op_wide = 0xc4;
  static final int op_multianewarray = 0xc5;
  static final int op_ifnull = 0xc6;
  static final int op_ifnonnull = 0xc7;
  static final int op_goto_w = 0xc8;
  static final int op_jsr_w = 0xc9;

  static final String[] insn_name = {
    "nop",
    "aconst_null",
    "iconst_m1",
    "iconst_0",
    "iconst_1",
    "iconst_2",
    "iconst_3",
    "iconst_4",
    "iconst_5",
    "lconst_0",
    "lconst_1",
    "fconst_0",
    "fconst_1",
    "fconst_2",
    "dconst_0",
    "dconst_1",
    "bipush",
    "sipush",
    "ldc",
    "ldc_w",
    "ldc2_w",
    "iload",
    "lload",
    "fload",
    "dload",
    "aload",
    "iload_0",
    "iload_1",
    "iload_2",
    "iload_3",
    "lload_0",
    "lload_1",
    "lload_2",
    "lload_3",
    "fload_0",
    "fload_1",
    "fload_2",
    "fload_3",
    "dload_0",
    "dload_1",
    "dload_2",
    "dload_3",
    "aload_0",
    "aload_1",
    "aload_2",
    "aload_3",
    "iaload",
    "laload",
    "faload",
    "daload",
    "aaload",
    "baload",
    "caload",
    "saload",
    "istore",
    "lstore",
    "fstore",
    "dstore",
    "astore",
    "istore_0",
    "istore_1",
    "istore_2",
    "istore_3",
    "lstore_0",
    "lstore_1",
    "lstore_2",
    "lstore_3",
    "fstore_0",
    "fstore_1",
    "fstore_2",
    "fstore_3",
    "dstore_0",
    "dstore_1",
    "dstore_2",
    "dstore_3",
    "astore_0",
    "astore_1",
    "astore_2",
    "astore_3",
    "iastore",
    "lastore",
    "fastore",
    "dastore",
    "aastore",
    "bastore",
    "castore",
    "sastore",
    "pop",
    "pop2",
    "dup",
    "dup_x1",
    "dup_x2",
    "dup2",
    "dup2_x1",
    "dup2_x2",
    "swap",
    "iadd",
    "ladd",
    "fadd",
    "dadd",
    "isub",
    "lsub",
    "fsub",
    "dsub",
    "imul",
    "lmul",
    "fmul",
    "dmul",
    "idiv",
    "ldiv",
    "fdiv",
    "ddiv",
    "irem",
    "lrem",
    "frem",
    "drem",
    "ineg",
    "lneg",
    "fneg",
    "dneg",
    "ishl",
    "lshl",
    "ishr",
    "lshr",
    "iushr",
    "lushr",
    "iand",
    "land",
    "ior",
    "lor",
    "ixor",
    "lxor",
    "iinc",
    "i2l",
    "i2f",
    "i2d",
    "l2i",
    "l2f",
    "l2d",
    "f2i",
    "f2l",
    "f2d",
    "d2i",
    "d2l",
    "d2f",
    "i2b",
    "i2c",
    "i2s",
    "lcmp",
    "fcmpl",
    "fcmpg",
    "dcmpl",
    "dcmpg",
    "ifeq",
    "ifne",
    "iflt",
    "ifge",
    "ifgt",
    "ifle",
    "if_icmpeq",
    "if_icmpne",
    "if_icmplt",
    "if_icmpge",
    "if_icmpgt",
    "if_icmple",
    "if_acmpeq",
    "if_acmpne",
    "goto ",
    "jsr",
    "ret",
    "tableswitch",
    "lookupswitch",
    "ireturn",
    "lreturn",
    "freturn",
    "dreturn",
    "areturn",
    "return",
    "getstatic",
    "putstatic",
    "getfield",
    "putfield",
    "invokevirtual",
    "invokespecial",
    "invokestatic",
    "invokeinterface",
    "xxxunusedxxx1",
    "new",
    "newarray",
    "anewarray",
    "arraylength",
    "athrow",
    "checkcast",
    "instanceof",
    "monitorenter",
    "monitorexit",
    "wide",
    "multianewarray",
    "ifnull",
    "ifnonnull",
    "goto_w",
    "jsr_w" 
  };


}
