/*
 * Copyright (C) 2013 Trillian Mobile AB
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
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Tests {@link Module}.
 */
public class ModuleTest {

    @Test
    public void testParseIRString() {
        try (Context context = new Context()) {
            try (Module m = Module.parseIR(context, 
                      "define private i32 @foo() alwaysinline {\n"
                    + "  ret i32 5\n"
                    + "}\n"
                    + "define private i32 @bar() optsize noinline nounwind {\n"
                    + "  %a = call i32 @foo()\n"
                    + "  ret i32 %a\n"
                    + "}\n", "Foo")) {
                try (PassManager passManager = new PassManager()) {
                    try (PassManagerBuilder builder = new PassManagerBuilder()) {
                        builder.setSetOptLevel(2);
                        builder.setDisableTailCalls(true);
                        builder.useAlwaysInliner(true);
                        builder.populateModulePassManager(passManager);
                    }
                    passManager.run(m);
                    m.writeBitcode(new File("/tmp/test.bc"));
                }
            }
        }
    }
    
    @Test
    public void testParseClangFile() throws Exception {
        String c = 
                "extern void printf(const char*, ...);\n" +
                "int main() {\n" +
                "    printf(\"Hello world!\");\n" +
                "}\n";
        try (Context context = new Context()) {
            try (Module m = Module.parseClangString(context, c, "test.c", "arm64-unknown-ios")) {
                Set<String> functionNames = new TreeSet<>();
                for (Function f : m.getFunctions()) {
                    functionNames.add(f.getName());
                }
                assertEquals(2, functionNames.size());
                Iterator<String> it = functionNames.iterator();
                assertEquals("main", it.next());
                assertEquals("printf", it.next());
            }
        }
    }

}
