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

import java.io.File;

import org.junit.Test;

/**
 * Tests {@link Module}.
 */
public class ModuleTest {

    @Test
    public void testParseIRString() {
        Context context = new Context();
        Module m = Module.parseIR(context, "define private i32 @foo() alwaysinline {\n ret i32 5\n }\n define private i32 @bar() optsize noinline nounwind {\n %a = call i32 @foo()\n ret i32 %a\n }\n", "Foo");
        PassManager passManager = new PassManager();
        passManager.addAlwaysInlinerPass();
        passManager.addPromoteMemoryToRegisterPass();
        passManager.run(m);
        m.writeBitcode(new File("/tmp/test.bc"));
        passManager.dispose();
        m.dispose();
        context.dispose();
    }

}
