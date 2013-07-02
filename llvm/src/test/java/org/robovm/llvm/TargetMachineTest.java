/*
 * Copyright (C) 2013 Trillian AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.llvm;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.robovm.llvm.binding.CodeGenFileType;

/**
 * Tests {@link TargetMachine}.
 */
public class TargetMachineTest {

    @Test
    public void testEmitToFile() throws Exception {
        TargetMachine tm = null;
        Context context = new Context();
        try {
            tm = Target.getTarget("thumb").createTargetMachine("thumbv7-unknown-ios");
            Module module = Module.parseIR(context, "define private i32 @foo() {\n ret i32 5\n }\n", "foo.c");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            tm.emit(module, out, CodeGenFileType.AssemblyFile);
            String asm = new String(out.toByteArray(), "utf-8");
            assertTrue(asm.contains("L_foo"));
        } finally {
            if (tm != null) {
                tm.dispose();
            }
            context.dispose();
        }
    }

    @Test
    public void testAssemble() throws Exception {
        TargetMachine tm = null;
        Context context = new Context();
        try {
            tm = Target.getTarget("thumb").createTargetMachine("thumbv7-unknown-ios");
            Module module = Module.parseIR(context, "define private i32 @foo() {\n ret i32 5\n }\n", "foo.c");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            tm.emit(module, out, CodeGenFileType.AssemblyFile);
            String asm = new String(out.toByteArray(), "utf-8");
            out = new ByteArrayOutputStream();
            tm.assemble(asm.getBytes(), "foo.s", out);
            byte[] data = out.toByteArray();
            assertTrue(data.length > 0);
        } finally {
            if (tm != null) {
                tm.dispose();
            }
            context.dispose();
        }
    }
    
}
