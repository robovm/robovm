/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.Test;
import org.nullvm.compiler.tests.opcode.AbstractOpcodeTests;
import org.nullvm.compiler.tests.opcode.ArrayOpcodes;
import org.nullvm.compiler.tests.opcode.ArrayTests;
import org.nullvm.compiler.tests.opcode.DoubleOpcodes;
import org.nullvm.compiler.tests.opcode.DoubleTests;
import org.nullvm.compiler.tests.opcode.ExceptionOpcodes;
import org.nullvm.compiler.tests.opcode.ExceptionTests;
import org.nullvm.compiler.tests.opcode.FloatOpcodes;
import org.nullvm.compiler.tests.opcode.FloatTests;
import org.nullvm.compiler.tests.opcode.IntOpcodes;
import org.nullvm.compiler.tests.opcode.IntTests;
import org.nullvm.compiler.tests.opcode.JumpOpcodes;
import org.nullvm.compiler.tests.opcode.JumpTests;
import org.nullvm.compiler.tests.opcode.LongOpcodes;
import org.nullvm.compiler.tests.opcode.LongTests;
import org.nullvm.compiler.tests.opcode.ObjectOpcodes;
import org.nullvm.compiler.tests.opcode.ObjectTests;
import org.nullvm.compiler.tests.opcode.StackOpcodes;
import org.nullvm.compiler.tests.opcode.StackTests;
import org.objectweb.asm.Type;


/**
 *
 * @version $Id$
 */
public class OpcodeTests {

    @Test
    public void intTests() throws Exception {
        runTest(new IntTests(), IntOpcodes.class);
    }

    @Test
    public void longTests() throws Exception {
        runTest(new LongTests(), LongOpcodes.class);
    }
    
    @Test
    public void floatTests() throws Exception {
        runTest(new FloatTests(), FloatOpcodes.class);
    }
    
    @Test
    public void doubleTests() throws Exception {
        runTest(new DoubleTests(), DoubleOpcodes.class);
    }
    
    @Test
    public void arrayTests() throws Exception {
        runTest(new ArrayTests(), ArrayOpcodes.class);
    }
    
    @Test
    public void jumpTests() throws Exception {
        runTest(new JumpTests(), JumpOpcodes.class);
    }
    
    @Test
    public void stackTests() throws Exception {
        runTest(new StackTests(), StackOpcodes.class);
    }
    
    @Test
    public void exceptionTests() throws Exception {
        runTest(new ExceptionTests(), ExceptionOpcodes.class);
    }
    
    @Test
    public void objectTests() throws Exception {
        runTest(new ObjectTests(), ObjectOpcodes.class);
    }
    
    private void runTest(AbstractOpcodeTests test, Class<?> ... classes) throws Exception {
        String testName = test.getClass().getSimpleName();
        
        NullVMC nullvmc = new NullVMC();
        nullvmc.addInputs(
                new File("target/rt-test-classes"),
                new File("src/test/c/assert.c"),
                new File("src/test/c/native.c"),
                new File("src/test/c/" + testName + ".c")
        );
        for (Class<?> c : classes) {
            File f = new File("target/jasmin-classes/" + Type.getInternalName(c) + ".class");
            if (!f.exists()) {
                f = new File("target/test-classes/" + Type.getInternalName(c) + ".class");
            }
            nullvmc.addInput(f);
        }
        nullvmc.addLibDir(new File("../gc/lib"));
        nullvmc.addIncludeDir(new File("../gc/include"));
        nullvmc.setSkipRtLib(true);
        nullvmc.setClean(true);
        nullvmc.setWork(new File("target/" + testName + ".build"));
        nullvmc.setOutput(new File("target/" + testName));
        nullvmc.setSkipMainStub(true);
        nullvmc.run();
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        CommandLine commandLine = CommandLine.parse("target/" + testName);
        Executor executor = new DefaultExecutor();
        executor.setExitValue(0);
        executor.setStreamHandler(new PumpStreamHandler(out, System.err));
        executor.execute(commandLine);
        
        String actual = new String(out.toByteArray(), "UTF-8");
        String expected = test.run();
        
        assertEquals(expected, actual);
    }
    
}
