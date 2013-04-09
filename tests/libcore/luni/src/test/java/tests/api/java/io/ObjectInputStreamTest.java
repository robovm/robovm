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

package tests.api.java.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.harmony.testframework.serialization.SerializationTest;
import org.apache.harmony.testframework.serialization.SerializationTest.SerializableAssert;
import tests.support.Support_ASimpleInputStream;

public class ObjectInputStreamTest extends junit.framework.TestCase implements
        Serializable {

    static final long serialVersionUID = 1L;

    ObjectInputStream ois;

    ObjectOutputStream oos;

    ByteArrayOutputStream bao;

    boolean readStreamHeaderCalled;

    private final String testString = "Lorem ipsum...";

    private final int testLength = testString.length();

    public void test_ConstructorLjava_io_InputStream_IOException() throws IOException {
        oos.writeObject(testString);
        oos.close();

        Support_ASimpleInputStream sis = new Support_ASimpleInputStream(bao.toByteArray());
        sis.throwExceptionOnNextUse = true;
        try {
            ois = new ObjectInputStream(sis);
            fail("Test 1: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        sis.throwExceptionOnNextUse = false;
    }

    public void test_ClassDescriptor() throws IOException,
            ClassNotFoundException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStreamWithWriteDesc oos = new ObjectOutputStreamWithWriteDesc(
                baos);
        oos.writeObject(String.class);
        oos.close();
        Class<?> cls = TestClassForSerialization.class;
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStreamWithReadDesc ois = new ObjectInputStreamWithReadDesc(
                bais, cls);
        Object obj = ois.readObject();
        ois.close();
        assertEquals(cls, obj);
    }

    public void test_available() throws IOException {
        // Test for method int java.io.ObjectInputStream.available()
        oos.writeBytes(testString);
        oos.close();

        Support_ASimpleInputStream sis = new Support_ASimpleInputStream(bao.toByteArray());
        ois = new ObjectInputStream(sis);
        assertEquals("Test 1: Incorrect number of bytes;", testLength, ois.available());
        ois.close();
    }

    public void test_available_IOException() throws IOException {
        oos.writeObject(testString);
        oos.close();

        Support_ASimpleInputStream sis = new Support_ASimpleInputStream(bao.toByteArray());
        ois = new ObjectInputStream(sis);
        sis.throwExceptionOnNextUse = true;
        try {
            ois.available();
            fail("Test 1: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        sis.throwExceptionOnNextUse = false;
        ois.close();
    }

    public void test_close() throws Exception {
        // Test for method void java.io.ObjectInputStream.close()
        oos.writeObject(testString);
        oos.close();

        Support_ASimpleInputStream sis = new Support_ASimpleInputStream(bao.toByteArray());
        ois = new ObjectInputStream(sis);
        sis.throwExceptionOnNextUse = true;
        try {
            ois.close();
            fail("Test 1: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        sis.throwExceptionOnNextUse = false;
        ois.close();
    }

    public void test_enableResolveObjectB() throws IOException {
        // Start testing without a SecurityManager.
        BasicObjectInputStream bois = new BasicObjectInputStream();
        assertFalse("Test 1: Object resolving must be disabled by default.",
                bois.enableResolveObject(true));

        assertTrue("Test 2: enableResolveObject did not return the previous value.",
                bois.enableResolveObject(false));
    }

    public void test_read_IOException() throws IOException {
        oos.writeObject(testString);
        oos.close();

        Support_ASimpleInputStream sis = new Support_ASimpleInputStream(bao.toByteArray());
        ois = new ObjectInputStream(sis);
        sis.throwExceptionOnNextUse = true;
        try {
            ois.read();
            fail("Test 1: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        sis.throwExceptionOnNextUse = false;
        ois.close();
    }

    public void test_read$BII() throws IOException {
        // Test for method int java.io.ObjectInputStream.read(byte [], int, int)
        byte[] buf = new byte[testLength];
        oos.writeBytes(testString);
        oos.close();
        ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        ois.read(buf, 0, testLength);
        ois.close();
        assertEquals("Read incorrect bytes", testString, new String(buf));
    }

    public void test_read$BII_Exception() throws IOException {
        byte[] buf = new byte[testLength];
        oos.writeObject(testString);
        oos.close();

        ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        try {
            ois.read(buf, 0, -1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
        try {
            ois.read(buf, -1,1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
        try {
            ois.read(buf, testLength, 1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
        ois.close();


        Support_ASimpleInputStream sis = new Support_ASimpleInputStream(bao.toByteArray());
        ois = new ObjectInputStream(sis);
        sis.throwExceptionOnNextUse = true;
        try {
            ois.read(buf, 0, testLength);
            fail("Test 1: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        sis.throwExceptionOnNextUse = false;
        ois.close();
    }

    public void test_readFully$B() throws IOException {
        byte[] buf = new byte[testLength];
        oos.writeBytes(testString);
        oos.close();
        ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        ois.readFully(buf);
        assertEquals("Test 1: Incorrect bytes read;",
                testString, new String(buf));
        ois.close();

        ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        ois.read();
        try {
            ois.readFully(buf);
            fail("Test 2: EOFException expected.");
        } catch (EOFException e) {
            // Expected.
        }
    }

    public void test_readFully$B_Exception() throws IOException {
        byte[] buf = new byte[testLength];
        oos.writeObject(testString);
        oos.close();

        Support_ASimpleInputStream sis = new Support_ASimpleInputStream(bao.toByteArray());
        ois = new ObjectInputStream(sis);
        sis.throwExceptionOnNextUse = true;
        try {
            ois.readFully(buf);
            fail("Test 1: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        sis.throwExceptionOnNextUse = false;
        ois.close();
    }

    public void test_readFully$BII() throws IOException {
        // Test for method void java.io.ObjectInputStream.readFully(byte [],
        // int, int)
        byte[] buf = new byte[testLength];
        oos.writeBytes(testString);
        oos.close();
        ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        ois.readFully(buf, 0, testLength);
        assertEquals("Read incorrect bytes", testString, new String(buf));
        ois.close();

        ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        ois.read();
        try {
            ois.readFully(buf);
            fail("Test 2: EOFException expected.");
        } catch (EOFException e) {
            // Expected.
        }
    }

    public void test_readFully$BII_Exception() throws IOException {
        byte[] buf = new byte[testLength];
        oos.writeObject(testString);
        oos.close();

        ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        try {
            ois.readFully(buf, 0, -1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
        try {
            ois.readFully(buf, -1,1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
        try {
            ois.readFully(buf, testLength, 1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
        ois.close();

        Support_ASimpleInputStream sis = new Support_ASimpleInputStream(bao.toByteArray());
        ois = new ObjectInputStream(sis);
        sis.throwExceptionOnNextUse = true;
        try {
            ois.readFully(buf, 0, 1);
            fail("Test 1: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        sis.throwExceptionOnNextUse = false;
        ois.close();
    }

    @SuppressWarnings("deprecation")
    public void test_readLine() throws IOException {
        String line;
        oos.writeBytes("Lorem\nipsum\rdolor sit amet...");
        oos.close();

        ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        line = ois.readLine();
        assertTrue("Test 1: Incorrect line written or read: " + line,
                line.equals("Lorem"));
        line = ois.readLine();
        assertTrue("Test 2: Incorrect line written or read: " + line,
                line.equals("ipsum"));
        line = ois.readLine();
        assertTrue("Test 3: Incorrect line written or read: " + line,
                line.equals("dolor sit amet..."));
        ois.close();
    }

    public void test_readLine_IOException() throws IOException {
        oos.writeObject(testString);
        oos.close();

        Support_ASimpleInputStream sis = new Support_ASimpleInputStream(bao.toByteArray());
        ois = new ObjectInputStream(sis);
        sis.throwExceptionOnNextUse = true;
        try {
            ois.readLine();
            fail("Test 1: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        sis.throwExceptionOnNextUse = false;
        ois.close();
    }

    private void fillStreamHeader(byte[] buffer) {
        short magic = java.io.ObjectStreamConstants.STREAM_MAGIC;
        short version = java.io.ObjectStreamConstants.STREAM_VERSION;

        if (buffer.length < 4) {
            throw new IllegalArgumentException("The buffer's minimal length must be 4.");
        }

        // Initialize the buffer with the correct header for object streams
        buffer[0] = (byte) (magic >> 8);
        buffer[1] = (byte) magic;
        buffer[2] = (byte) (version >> 8);
        buffer[3] = (byte) (version);
    }

    public void test_readObjectOverride() throws Exception {
        byte[] buffer = new byte[4];

        // Initialize the buffer with the correct header for object streams
        fillStreamHeader(buffer);

        // Test 1: Check that readObjectOverride() returns null if there
        // is no input stream.
        BasicObjectInputStream bois = new BasicObjectInputStream();
        assertNull("Test 1:", bois.readObjectOverride());

        // Test 2: Check that readObjectOverride() throws an IOException
        // if there is an input stream.
        bois = new BasicObjectInputStream(new ByteArrayInputStream(buffer));
        try {
            bois.readObjectOverride();
            fail("Test 2: IOException expected.");
        } catch (IOException e) {}

        bois.close();
    }

    public void test_readObjectMissingClasses() throws Exception {
        SerializationTest.verifySelf(new A1(), new SerializableAssert() {
            public void assertDeserialized(Serializable initial,
                    Serializable deserialized) {
                assertEquals(5, ((A1) deserialized).b1.i);
            }
        });
    }

    public void test_readObjectCorrupt() {
        byte[] bytes = { 00, 00, 00, 0x64, 0x43, 0x48, (byte) 0xFD, 0x71, 00,
                00, 0x0B, (byte) 0xB8, 0x4D, 0x65 };
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        boolean exception = false;
        try {
            ObjectInputStream in = new ObjectInputStream(bin);
            in.readObject();
            fail("Unexpected read of corrupted stream");
        } catch (StreamCorruptedException e) {
            exception = true;
        } catch (IOException e) {
            fail("Unexpected: " + e);
        } catch (ClassNotFoundException e) {
            fail("Unexpected: " + e);
        }
        assertTrue("Expected StreamCorruptedException", exception);
    }

    public void test_readStreamHeader() throws IOException {
        String testString = "Lorem ipsum";
        BasicObjectInputStream bois;
        short magic = java.io.ObjectStreamConstants.STREAM_MAGIC;
        short version = java.io.ObjectStreamConstants.STREAM_VERSION;
        byte[] buffer = new byte[20];

        // Initialize the buffer with the correct header for object streams
        fillStreamHeader(buffer);
        System.arraycopy(testString.getBytes(), 0, buffer, 4, testString.length());

        // Test 1: readStreamHeader should not throw a StreamCorruptedException.
        // It should get called by the ObjectInputStream constructor.
        try {
            readStreamHeaderCalled = false;
            bois = new BasicObjectInputStream(new ByteArrayInputStream(buffer));
            bois.close();
        } catch (StreamCorruptedException e) {
            fail("Test 1: Unexpected StreamCorruptedException.");
        }
        assertTrue("Test 1: readStreamHeader() has not been called.",
                    readStreamHeaderCalled);

        // Test 2: Make the stream magic number invalid and check that
        // readStreamHeader() throws an exception.
        buffer[0] = (byte)magic;
        buffer[1] = (byte)(magic >> 8);
        try {
            readStreamHeaderCalled = false;
            bois = new BasicObjectInputStream(new ByteArrayInputStream(buffer));
            fail("Test 2: StreamCorruptedException expected.");
            bois.close();
        } catch (StreamCorruptedException e) {
        }
        assertTrue("Test 2: readStreamHeader() has not been called.",
                    readStreamHeaderCalled);

        // Test 3: Make the stream version invalid and check that
        // readStreamHeader() throws an exception.
        buffer[0] = (byte)(magic >> 8);
        buffer[1] = (byte)magic;
        buffer[2] = (byte)(version);
        buffer[3] = (byte)(version >> 8);
        try {
            readStreamHeaderCalled = false;
            bois = new BasicObjectInputStream(new ByteArrayInputStream(buffer));
            fail("Test 3: StreamCorruptedException expected.");
            bois.close();
        } catch (StreamCorruptedException e) {
        }
        assertTrue("Test 3: readStreamHeader() has not been called.",
                    readStreamHeaderCalled);
    }

    public void test_readUnsignedByte() throws IOException {
        oos.writeByte(-1);
        oos.close();

        ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        assertEquals("Test 1: Incorrect unsigned byte written or read.",
                255, ois.readUnsignedByte());

        try {
            ois.readUnsignedByte();
            fail("Test 2: EOFException expected.");
        } catch (EOFException e) {
            // Expected.
        }

        ois.close();
        try {
            ois.readUnsignedByte();
            fail("Test 3: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
    }

    public void test_readUnsignedShort() throws IOException {
        // Test for method int java.io.ObjectInputStream.readUnsignedShort()
        oos.writeShort(-1);
        oos.close();

        ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        assertEquals("Test 1: Incorrect unsigned short written or read.",
                65535, ois.readUnsignedShort());

        try {
            ois.readUnsignedShort();
            fail("Test 2: EOFException expected.");
        } catch (EOFException e) {
            // Expected.
        }

        ois.close();
        try {
            ois.readUnsignedShort();
            fail("Test 3: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
    }

    public void test_resolveProxyClass() throws IOException {
        BasicObjectInputStream bois;
        byte[] buffer = new byte[10];

        // Initialize the buffer with the header for object streams
        fillStreamHeader(buffer);
        bois = new BasicObjectInputStream(new ByteArrayInputStream(buffer));

        // Test 1: Check that a NullPointerException is thrown
        // if null is passed to the method.
        try {
            bois.resolveProxyClass(null);
            fail("Test 1: NullPointerException expected.");
        }
        catch (NullPointerException npe) {
        }
        catch (ClassNotFoundException cnfe) {
            fail("Test 1: Unexpected ClassNotFoundException.");
        }

        // Test 2: Check that visible interfaces are found.
        try {
            String[] interfaces = { "java.io.Closeable",
                                    "java.lang.Cloneable" };
            bois.resolveProxyClass(interfaces);
        }
        catch (ClassNotFoundException cnfe) {
            fail("Test 2: Unexpected ClassNotFoundException.");
        }

        // Test 3: Check that a ClassNotFoundException is thrown if the
        // array of interfaces is not valid.
        try {
            String[] interfaces = { "java.io.Closeable",
                                    "java.io.Closeable" };
            bois.resolveProxyClass(interfaces);
            fail ("Test 3: ClassNotFoundException expected.");
        }
        catch (ClassNotFoundException cnfe) {
        }

        bois.close();
    }

    public void test_skipBytesI_IOException() throws IOException {
        oos.writeObject(testString);
        oos.close();

        Support_ASimpleInputStream sis = new Support_ASimpleInputStream(bao.toByteArray());
        ois = new ObjectInputStream(sis);
        sis.throwExceptionOnNextUse = true;
        try {
            ois.skipBytes(5);
            fail("Test 1: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        sis.throwExceptionOnNextUse = false;
        ois.close();
    }

    public static class A implements Serializable {

        private static final long serialVersionUID = 11L;

        public String name = "name";
    }

    public static class B extends A {}

    public static class C extends B {

        private static final long serialVersionUID = 33L;
    }

    public static class A1 implements Serializable {

        static final long serialVersionUID = 5942584913446079661L;

        B1 b1 = new B1();

        B1 b2 = b1;

        Vector v = new Vector();
    }

    public static class B1 implements Serializable {

        int i = 5;

        Hashtable h = new Hashtable();
    }

    class BasicObjectInputStream extends ObjectInputStream {
        public BasicObjectInputStream() throws IOException, SecurityException {
            super();
        }

        public BasicObjectInputStream(InputStream input) throws IOException {
            super(input);
        }

        public boolean enableResolveObject(boolean enable)
                throws SecurityException {
            return super.enableResolveObject(enable);
        }

        public Object readObjectOverride() throws ClassNotFoundException, IOException {
            return super.readObjectOverride();
        }

        public void readStreamHeader() throws IOException {
            readStreamHeaderCalled = true;
            super.readStreamHeader();
        }

        public Class<?> resolveProxyClass(String[] interfaceNames)
                throws IOException, ClassNotFoundException {
            return super.resolveProxyClass(interfaceNames);
        }
    }

    //Regression Test for JIRA-2249
    public static class ObjectOutputStreamWithWriteDesc extends
            ObjectOutputStream {
        public ObjectOutputStreamWithWriteDesc(OutputStream os)
                throws IOException {
            super(os);
        }

        public void writeClassDescriptor(ObjectStreamClass desc)
                throws IOException {
        }
    }

    public static class ObjectInputStreamWithReadDesc extends
            ObjectInputStream {
        private Class returnClass;

        public ObjectInputStreamWithReadDesc(InputStream is, Class returnClass)
                throws IOException {
            super(is);
            this.returnClass = returnClass;
        }

        public ObjectStreamClass readClassDescriptor() throws IOException,
                ClassNotFoundException {
            return ObjectStreamClass.lookup(returnClass);

        }
    }

    static class TestClassForSerialization implements Serializable {
        private static final long serialVersionUID = 1L;
    }

    protected void setUp() throws Exception {
        super.setUp();
        oos = new ObjectOutputStream(bao = new ByteArrayOutputStream());
    }
}

class Test implements Serializable {
    private static final long serialVersionUID = 1L;

    Class<?> classes[] = new Class[] { byte.class, short.class, int.class,
            long.class, boolean.class, char.class, float.class, double.class };

    public boolean equals(Object o) {
        if (!(o instanceof Test)) {
            return false;
        }
        return Arrays.equals(classes, ((Test) o).classes);
    }
}
