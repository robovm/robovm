/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.tests.exception;

import org.nullvm.compiler.Echo;

/**
 *
 * @version $Id$
 */
public class ExceptionTests {

    private static void testSimple(int n) {
        Echo.print(" testSimple(");
        Echo.print(n);
        Echo.println(")");
        try {
            Echo.println("  try");
            if (n == 0) {
                Echo.println("   throw Throwable");
                throw new Throwable();
            }
            Echo.println("   return");
            return;
        } catch (Throwable t) {
            Echo.println("  catch (Throwable)");
        } finally {
            Echo.println("  finally");
        }
        Echo.println("  return");
    }
    
    private static void tryFinallyInner(int n) throws Throwable {
        try {
            Echo.println("    try");
            if (n == 0) {
                Echo.println("     throw Throwable");
                throw new Throwable();
            }
            Echo.println("     return");
            return;
        } finally {
            Echo.println("    finally");
        }
    }
    
    private static void testTryFinally(int n) {
        Echo.print(" testTryFinally(");
        Echo.print(n);
        Echo.println(")");
        try {
            Echo.println("  try");
            Echo.println("   tryFinallyInner(...)");
            tryFinallyInner(n);
            Echo.println("   return");
            return;
        } catch (Throwable t) {
            Echo.println("  catch (Throwable)");
        } finally {
            Echo.println("  finally");
        }
        Echo.println("  return");
    }

    private static void testTryFinallyCatch(int n) {
        Echo.print(" testTryFinallyCatch(");
        Echo.print(n);
        Echo.println(")");
        try {
            // Don't echo anything to here to make the TRYCATCHBLOCKs below start at the same label
            try {
                Echo.println("   try");
                if (n == 0) {
                    Echo.println("    throw Throwable");
                    throw new RuntimeException();
                }
            } finally {
                Echo.println("   finally");
            }
            Echo.println("   return");
        } catch (RuntimeException t) {
            Echo.println("  catch (RuntimeException)");
        }
        Echo.println("  return");
    }
    
    private static void testMatch(int n) {
        Echo.print(" testMatch(");
        Echo.print(n);
        Echo.println(")");
        try {
            Echo.println("  try");
            if (n == 0) {
                Echo.println("   throw Throwable");
                throw new Throwable();
            }
            if (n == 1) {
                Echo.println("   throw Exception");
                throw new Exception();
            }
            if (n == 2) {
                Echo.println("   throw RuntimeException");
                throw new RuntimeException();
            }
            Echo.println("   return");
            return;
        } catch (RuntimeException e) {
            Echo.println("  catch (RuntimeException)");
        } catch (Exception e) {
            Echo.println("  catch (Exception)");
        } catch (Throwable t) {
            Echo.println("  catch (Throwable)");
        } finally {
            Echo.println("  finally");
        }
        Echo.println("  return");
    }
    
    private static void f(Throwable t) throws Throwable {
        throw t;
    }
    
    private static void testInvokeAndMatch(int n) {
        Echo.print(" testInvokeAndMatch(");
        Echo.print(n);
        Echo.println(")");
        try {
            Echo.println("  try");
            if (n == 0) {
                Echo.println("   f(Throwable)");
                f(new Throwable());
            }
            if (n == 1) {
                Echo.println("   f(Exception)");
                f(new Exception());
            }
            if (n == 2) {
                Echo.println("   f(RuntimeException)");
                f(new RuntimeException());
            }
            Echo.println("   return");
            return;
        } catch (RuntimeException e) {
            Echo.println("  catch (RuntimeException)");
        } catch (Exception e) {
            Echo.println("  catch (Exception)");
        } catch (Throwable t) {
            Echo.println("  catch (Throwable)");
        } finally {
            Echo.println("  finally");
        }
        Echo.println("  return");
    }
    
    private static void g(Throwable t) throws Throwable {
        try {
            Echo.println("    try");
            Echo.println("     h(...)");
            h(t);
            Echo.println("     return");
        } catch (Exception e) {
            Echo.println("    catch (Exception)");
        } finally {
            Echo.println("    finally");
        }
        Echo.println("    return");
    }
    
    private static void h(Throwable t) throws Throwable {
        try {
            Echo.println("      try");
            Echo.println("       f(...)");
            f(t);
            Echo.println("       return");
        } catch (RuntimeException e) {
            Echo.println("      catch (RuntimeException)");
        } finally {
            Echo.println("      finally");
        }
        Echo.println("      return");
    }
    
    private static void testInvokeAndMatchAndInvokeAndMatch(int n) {
        Echo.print(" testInvokeAndMatchAndInvokeAndMatch(");
        Echo.print(n);
        Echo.println(")");
        try {
            Echo.println("  try");
            if (n == 0) {
                Echo.println("   g(Throwable)");
                g(new Throwable());
            }
            if (n == 1) {
                Echo.println("   g(Exception)");
                g(new Exception());
            }
            if (n == 2) {
                Echo.println("   g(RuntimeException)");
                g(new RuntimeException());
            }
            Echo.println("   return");
            return;
        } catch (Exception e) {
            Echo.println("  catch (Exception)");
        } catch (Throwable t) {
            Echo.println("  catch (Throwable)");
        } finally {
            Echo.println("  finally");
        }
        Echo.println("  return");
    }
    
    private static void testNestedTryCatchBlocksAndRethrow(int n) {
        Echo.print(" testNestedTryCatchBlocks(");
        Echo.print(n);
        Echo.println(")");
        try {
            Echo.println("  try");
            if (n == 0) {
                Echo.println("   throw Throwable");
                throw new Throwable();
            }
            try {
                Echo.println("   try");
                if (n == 1) {
                    Echo.println("    throw Throwable");
                    throw new Throwable();
                }
                try {
                    Echo.println("    try");
                    if (n == 2) {
                        Echo.println("     throw Throwable");
                        throw new Throwable();
                    }
                } catch (Throwable t) {
                    Echo.println("    catch (Throwable)");
                    throw t;
                }
            } catch (Throwable t) {
                Echo.println("   catch (Throwable)");
                throw t;
            }
            Echo.println("   return");
            return;
        } catch (Throwable t) {
            Echo.println("  catch (Throwable)");
        } finally {
            Echo.println("  finally");
        }
        Echo.println("  return");
    }
    
    @SuppressWarnings("finally")
    private static int returnFromFinallyInner(int n) {
        try {
            Echo.println("   try");
            Echo.println("    return");
            return n;
        } finally {
            Echo.println("   finally");
            Echo.println("    return");
            return n + 1;
        }
    }
    
    private static void testReturnFromFinally(int n) {
        Echo.print(" testReturnFromFinally(");
        Echo.print(n);
        Echo.println(")");
        Echo.println("  returnFromFinallyInner(...)");
        int result = returnFromFinallyInner(n);
        Echo.print("   result = ");
        Echo.print(result);
        Echo.println();
        Echo.println("   return");
    }
    
    private static void testManyTryCatchBlocks(int n) {
        Echo.print(" testManyTryCatchBlocks(");
        Echo.print(n);
        Echo.println(")");
        try {
            Echo.println("  try");
            if (n == 0) {
                Echo.println("   throw RuntimeException");
                throw new RuntimeException();
            }
        } catch (RuntimeException t) {
            Echo.println("  catch (RuntimeException)");
        } finally {
            Echo.println("  finally");
        }
        try {
            Echo.println("  try");
            if (n == 1) {
                Echo.println("   throw Exception");
                throw new Exception();
            }
        } catch (Exception t) {
            Echo.println("  catch (Exception)");
        } finally {
            Echo.println("  finally");
        }
        try {
            Echo.println("  try");
            if (n == 2) {
                Echo.println("   throw Throwable");
                throw new Throwable();
            }
        } catch (Throwable t) {
            Echo.println("  catch (Throwable)");
        } finally {
            Echo.println("  finally");
        }
        Echo.println("  return");
    }
    
    private static void testClassCastException() {
        Echo.println(" testClassCastException()");
        try {
            Object o = new Object();
            @SuppressWarnings("unused")
            String s = (String) o;
            Echo.println("  ClassCastException expected");
        } catch (ClassCastException e) {
            Echo.println("  ClassCastException thrown as expected");
        }
    }
    
    @SuppressWarnings("null")
    private static void testNullPointerExceptionInvokeVirtual() {
        Echo.println(" testNullPointerExceptionInvokeVirtual()");
        try {
            Object o = null;
            o.hashCode();
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
    }
    
    private void x() {
    }
    
    @SuppressWarnings("null")
    private static void testNullPointerExceptionInvokeSpecial() {
        Echo.println(" testNullPointerExceptionInvokeSpecial()");
        try {
            ExceptionTests o = null;
            o.x();
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
    }
    
    @SuppressWarnings("null")
    private static void testNullPointerExceptionInvokeInterface() {
        Echo.println(" testNullPointerExceptionInvokeSpecial()");
        try {
            Comparable<String> o = null;
            o.compareTo("FOO");
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
    }
    
    @SuppressWarnings("null")
    private static void testNullPointerExceptionAccessField() {
        Echo.println(" testNullPointerExceptionAccessField()");
        try {
            Foo foo = null;
            int n = foo.bar;
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
    }
    
    @SuppressWarnings("null")
    private static void testNullPointerExceptionModifyField() {
        Echo.println(" testNullPointerExceptionModifyField()");
        try {
            Foo foo = null;
            foo.bar = 100;
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
    }
    
    @SuppressWarnings("null")
    private static void testNullPointerExceptionArrayLength() {
        Echo.println(" testNullPointerExceptionArrayLength()");
        try {
            Object[] array = null;
            int length = array.length;
            Echo.print(length);
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
        try {
            int[] array = null;
            int length = array.length;
            Echo.print(length);
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
    }
    
    @SuppressWarnings("null")
    private static void testNullPointerExceptionArrayAccess() {
        Echo.println(" testNullPointerExceptionArrayAccess()");
        try {
            Object[] array = null;
            Object o = array[1];
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
        try {
            int[] array = null;
            int i = array[1];
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
    }
    
    @SuppressWarnings("null")
    private static void testNullPointerExceptionArrayModification() {
        Echo.println(" testNullPointerExceptionArrayModification()");
        try {
            Object[] array = null;
            array[1] = null;
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
        try {
            int[] array = null;
            array[1] = 10;
            Echo.println("  NullPointerException expected");
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        }
    }
    
    @SuppressWarnings("null")
    private static void testNullPointerExceptionThrow() {
        Echo.println(" testNullPointerExceptionThrow()");
        try {
            Throwable t = null;
            throw t;
        } catch (NullPointerException e) {
            Echo.println("  NullPointerException thrown as expected");
        } catch (Throwable t) {
            Echo.println("  NullPointerException expected");
        }
    }
    
    private static void testArrayIndexOutOfBoundsException() {
        Echo.println(" testArrayIndexOutOfBoundsException()");
        try {
            Object[] array = new Object[10];
            array[0] = null;
            array[9] = null;
            Object a = array[0];
            Object b = array[9];
        } catch (ArrayIndexOutOfBoundsException e) {
            Echo.println("  Unexpected ArrayIndexOutOfBoundsException");
        }
        try {
            char[] array = new char[10];
            array[-1] = 'a';
            Echo.println("  ArrayIndexOutOfBoundsException expected");
        } catch (ArrayIndexOutOfBoundsException e) {
            Echo.println("  ArrayIndexOutOfBoundsException thrown as expected");
        }
        try {
            int[] array = new int[7];
            @SuppressWarnings("unused")
            int n = array[7];
            Echo.println("  ArrayIndexOutOfBoundsException expected");
        } catch (ArrayIndexOutOfBoundsException e) {
            Echo.println("  ArrayIndexOutOfBoundsException thrown as expected");
        }
    }
    
    private static void testNegativeArraySizeExceptionOnNewArray() {
        Echo.println(" testNegativeArraySizeExceptionOnNewArray()");
        try {
            @SuppressWarnings("unused")
            int[] array = new int[-1];
            Echo.println("  NegativeArraySizeException expected");
        } catch (NegativeArraySizeException e) {
            Echo.println("  NegativeArraySizeException thrown as expected");
        }
    }
    
    private static void testNegativeArraySizeExceptionOnANewArray() {
        Echo.println(" testNegativeArraySizeExceptionOnANewArray()");
        try {
            @SuppressWarnings("unused")
            Object[] array = new Object[-1];
            Echo.println("  NegativeArraySizeException expected");
        } catch (NegativeArraySizeException e) {
            Echo.println("  NegativeArraySizeException thrown as expected");
        }
    }
    
    private static void testNegativeArraySizeExceptionOnMultiANewArray() {
        Echo.println(" testNegativeArraySizeExceptionOnMultiANewArray()");
        try {
            @SuppressWarnings("unused")
            Object[][] array = new Object[-1][];
            Echo.println("  NegativeArraySizeException expected");
        } catch (NegativeArraySizeException e) {
            Echo.println("  NegativeArraySizeException thrown as expected");
        }
        try {
            @SuppressWarnings("unused")
            Object[][] array = new Object[2][-1];
            Echo.println("  NegativeArraySizeException expected");
        } catch (NegativeArraySizeException e) {
            Echo.println("  NegativeArraySizeException thrown as expected");
        }
        try {
            @SuppressWarnings("unused")
            Object[][] array = new Object[2][2][-1];
            Echo.println("  NegativeArraySizeException expected");
        } catch (NegativeArraySizeException e) {
            Echo.println("  NegativeArraySizeException thrown as expected");
        }
    }
    
    public static void main(String[] args) {
        Echo.println("Running ExceptionTests...");
        testSimple(-1);
        testSimple(0);
        testTryFinally(-1);
        testTryFinally(0);
        testTryFinallyCatch(-1);
        testTryFinallyCatch(0);
        testMatch(-1);
        testMatch(0);
        testMatch(1);
        testMatch(2);
        testInvokeAndMatch(-1);
        testInvokeAndMatch(0);
        testInvokeAndMatch(1);
        testInvokeAndMatch(2);
        testInvokeAndMatchAndInvokeAndMatch(-1);
        testInvokeAndMatchAndInvokeAndMatch(0);
        testInvokeAndMatchAndInvokeAndMatch(1);
        testInvokeAndMatchAndInvokeAndMatch(2);
        testManyTryCatchBlocks(-1);
        testManyTryCatchBlocks(0);
        testManyTryCatchBlocks(1);
        testManyTryCatchBlocks(2);
        testReturnFromFinally(10);
        testNestedTryCatchBlocksAndRethrow(-1);
        testNestedTryCatchBlocksAndRethrow(0);
        testNestedTryCatchBlocksAndRethrow(1);
        testNestedTryCatchBlocksAndRethrow(2);
        testClassCastException();
        testNullPointerExceptionInvokeVirtual();
        testNullPointerExceptionInvokeSpecial();
        testNullPointerExceptionInvokeInterface();
        testNullPointerExceptionAccessField();
        testNullPointerExceptionModifyField();
        testNullPointerExceptionArrayLength();
        testNullPointerExceptionThrow();
        testNullPointerExceptionArrayAccess();
        testNullPointerExceptionArrayModification();
        testArrayIndexOutOfBoundsException();
        testNegativeArraySizeExceptionOnNewArray();
        testNegativeArraySizeExceptionOnANewArray();
        testNegativeArraySizeExceptionOnMultiANewArray();
        Echo.println("ExceptionTests finished");
    }
    
    public static class Foo {
        public int bar;
    }
}
