/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.Test;
import org.nullvm.compiler.tests.exception.ExceptionTests;
import org.nullvm.compiler.tests.opcode.AbstractOpcodeTests;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

/**
 *
 * @version $Id$
 */
public class FooTests {

    @Test
    public void exceptionTests() throws Exception {
        runTest(ExceptionTests.class, ExceptionTests.Foo.class);
    }

    private void runTest(Class<?> testClass, Class<?> ... classes) throws Exception {
        String testName = testClass.getName();
        
        NullVMC nullvmc = new NullVMC();
        nullvmc.addInput(new File("src/test/c/launcher.c"));
        nullvmc.addInput(new File("src/test/c/native.c"));
        nullvmc.addInput(new File("target/rt-test-classes"));
        nullvmc.addInput(new File("target/test-classes/" + Type.getInternalName(testClass) + ".class"));
        nullvmc.addInput(new File("target/test-classes/" + Type.getInternalName(Echo.class) + ".class"));
        for (Class<?> c : classes) {
            nullvmc.addInput(new File("target/test-classes/" + Type.getInternalName(c) + ".class"));
        }
        nullvmc.addLibDir(new File("../gc/lib"));
        nullvmc.addIncludeDir(new File("../gc/include"));
        nullvmc.setSkipRtLib(true);
        nullvmc.setClean(true);
        nullvmc.setWork(new File("target/" + testName + ".build"));
        nullvmc.setOutput(new File("target/" + testName));
        nullvmc.run();
        
        String actual = exec("target/" + testName, Type.getInternalName(testClass), LlvmUtil.mangleString(Type.getInternalName(testClass)));
        String expected = exec("java", "-cp", "target/classes:target/test-classes", testClass.getName());
        
        assertEquals(expected, actual);
    }

    private String exec(String cmd, String ... args) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CommandLine commandLine = CommandLine.parse(cmd);
        commandLine.addArguments(args);
        System.out.println(commandLine.toString());
        Executor executor = new DefaultExecutor();
        executor.setExitValue(0);
        executor.setStreamHandler(new PumpStreamHandler(out, System.err));
        executor.execute(commandLine);
        return new String(out.toByteArray(), "UTF-8");
    }
    
}
