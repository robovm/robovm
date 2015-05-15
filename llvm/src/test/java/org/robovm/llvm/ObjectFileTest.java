/*
 * Copyright (C) 2014 RoboVM AB
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
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
                    assertEquals(1, symbols.size());
                    assertEquals("_foo", symbols.get(0).getName());
                    assertTrue(symbols.get(0).getAddress() == 0);
                    assertTrue(symbols.get(0).getSize() > 0);
                }
            }
        }
    }

    @Test
    public void testReadSections() throws Exception {
        try (Context context = new Context()) {
            try (TargetMachine tm = Target.getTarget("x86").createTargetMachine("i386-unknown-macosx")) {
                Module module = Module.parseIR(context, "define external i32 @foo() {\n ret i32 5\n }\n", "foo.c");
                File oFile = File.createTempFile(getClass().getSimpleName(), ".o");
                try (FileOutputStream out = new FileOutputStream(oFile)) {
                    tm.emit(module, out, CodeGenFileType.ObjectFile);
                }
                try (ObjectFile objectFile = ObjectFile.load(oFile)) {
                    TreeSet<String> sections = new TreeSet<>();
                    for (SectionIterator it = objectFile.getSectionIterator(); it.hasNext(); it.next()) {
                        sections.add(it.getName());
                        byte[] contents = new byte[(int) it.getSize()];
                        assertEquals(it.getSize(), it.copyContents(contents));
                        long sum = 0;
                        for (int i = 0; i < contents.length; i++) {
                            sum += contents[i];
                        }
                        assertTrue(sum != 0);
                        
                    }
                    assertTrue(sections.contains("__text"));
                }
            }
        }
    }
    
    @Test
    @Ignore
    public void testMemoryLeaks() throws Exception {
        // Enable this test, set -Xmx64M and watch the memory usage while it's running.
        for (int i = 0; i < 100000; i++) {
            testLoadFromFile();
            if (i % 100 == 0) {
                System.out.println(i);
            }
        }
    }

    @Test
    public void testLineNumberInfo() throws Exception {
        String cc = "gcc";
        String symbolPrefix = "";
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            cc = "clang";
            symbolPrefix = "_";
        }
        
        File cFile = File.createTempFile(getClass().getSimpleName(), ".c");
        FileUtils.writeStringToFile(cFile, 
                "int main() {\n" 
              + "  return 0;\n"
              + "}\n");
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(cFile.getParentFile());
        executor.execute(new CommandLine(cc)
            .addArgument("-g")
            .addArgument("-c")
            .addArgument(cFile.getAbsolutePath()));
        
        List<LineInfo> mainLineInfos = null;
        File oFile = new File(cFile.getParentFile(), cFile.getName().substring(0, cFile.getName().lastIndexOf('.')) + ".o");
        try (ObjectFile objectFile = ObjectFile.load(oFile)) {
            for (Symbol symbol : objectFile.getSymbols()) {
                if (symbol.getSize() > 0) {
                    List<LineInfo> lineInfos = objectFile.getLineInfos(symbol);
                    if (!lineInfos.isEmpty() && symbol.getName().equals(symbolPrefix + "main")) {
                        mainLineInfos = lineInfos;
                        break;
                    }
                }
            }
        }
        
        assertNotNull(mainLineInfos);
        // Assert that the info for main() contains lines 1 and 2 and possibly 3
        Set<Integer> lineNumbers = new HashSet<>();
        for (LineInfo lineInfo : mainLineInfos) {
            lineNumbers.add(lineInfo.getLineNumber());
        }
        assertTrue(lineNumbers.size() >= 2 && lineNumbers.size() <= 3);
        assertTrue(lineNumbers.contains(1));
        assertTrue(lineNumbers.contains(2));
        if (lineNumbers.size() == 3) {
            assertTrue(lineNumbers.contains(3));
        }
    }
}
