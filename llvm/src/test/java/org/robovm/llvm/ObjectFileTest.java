/*
 * Copyright (C) 2014 Trillian Mobile AB
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

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.junit.Test;
import org.robovm.llvm.binding.CodeGenFileType;

/**
 * Tests {@link ObjectFile}.
 */
public class ObjectFileTest {

    @Test
    public void testLoadFromFile() throws Exception {
        try (Context context = new Context()) {
            try (TargetMachine tm = Target.getTarget("x86").createTargetMachine("i386-unknown-macosx")) {
                Module module = Module.parseIR(context, "define external i32 @foo() {\n ret i32 5\n }\n", "foo.c");
                File oFile = File.createTempFile(getClass().getSimpleName(), ".o");
                try (FileOutputStream out = new FileOutputStream(oFile)) {
                    tm.emit(module, out, CodeGenFileType.ObjectFile);
                }
                try (ObjectFile objectFile = ObjectFile.load(oFile)) {
                    List<Symbol> symbols = objectFile.getSymbols();
                    assertEquals(3, symbols.size());
                    assertEquals("EH_frame0", symbols.get(0).getName());
                    assertTrue(symbols.get(0).getAddress() > 0);
                    assertTrue(symbols.get(0).getSize() > 0);
                    assertTrue(symbols.get(0).getFileOffset() > 0);
                    assertEquals("_foo", symbols.get(1).getName());
                    assertTrue(symbols.get(1).getAddress() == 0);
                    assertTrue(symbols.get(1).getSize() > 0);
                    assertTrue(symbols.get(1).getFileOffset() > 0);
                    assertEquals("_foo.eh", symbols.get(2).getName());
                    assertTrue(symbols.get(2).getAddress() > 0);
                    assertTrue(symbols.get(2).getSize() > 0);
                    assertTrue(symbols.get(2).getFileOffset() > 0);
                    System.out.println(symbols);
                }
            }
        }
    }
    
}
