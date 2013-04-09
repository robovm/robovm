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

package libcore.java.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import junit.framework.TestCase;
import tests.support.Support_GetPutFields;
import tests.support.Support_GetPutFieldsDeprecated;

/**
 * Tests the methods of {@code ObjectOutputStream.PutField}. Three things make
 * this class somewhat difficult to test:
 * <ol>
 * <li>It is a completely abstract class; none of the methods is implemented in
 * {@code ObjectOutputStream.PutField}.</li>
 * <li>There is no public class that implements
 * {@code ObjectOutputStream.PutField}. The only way to get an implementation
 * is by calling {@code ObjectOutputStream.putFields()}.</li>
 * <li>Invoking the methods of {@code ObjectOutputStream.PutField} only works
 * from within the private {@code writeObject(ObjectOutputStream)} method of a
 * class that implements {@code Serializable}; an exception is thrown
 * otherwise.</li>
 * </ol>
 * <p>
 * Given these restrictions, an indirect approach is used to test
 * {@code ObjectOutputStream.PutField}: The serializable helper class
 * {@code tests.support.Support_GetPutFields} implements
 * {@code writeObject(ObjectOutputStream)} and uses all {@code putX} methods in
 * {@code PutField} to write data to the output stream. A second helper class,
 * {@code tests.support.Support_GetPutFieldsDeprecated}, also uses the
 * deprecated {@code ObjectOutputStream.PutField.write(ObjectOutput)}.
 * {@code tests.util.FieldTestFileGenerator} can then be used on a reference
 * platform to write these two classes to the file {@code testFields.ser} and
 * {@code testFieldsDeprecated.ser} respectively.
 * </p>
 * <p>
 * The test methods in this class expect to find {@code testFields.ser} and
 * {@code testFieldsDeprecated.ser} as a resource stored at
 * {@code tests/api/java/io}. The contents of these files is compared to what
 * is written when {@code Support_GetPutFields.writeObject(ObjectOutputStream)}
 * and {@code Support_GetPutFieldsDeprecated.writeObject(ObjectOutputStream)} is
 * called by the test methods.
 * </p>
 */
public class OldObjectOutputStreamPutFieldTest extends TestCase {

    private final String FILENAME =
        "/tests/api/java/io/testFields.ser";
    private final String DEPRECATED_FILENAME =
        "/tests/api/java/io/testFieldsDeprecated.ser";

    public void test_put() throws Exception {
        Support_GetPutFields toSerialize = new Support_GetPutFields();
        byte[] content;
        byte[] refContent;
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos;

        toSerialize.initTestValues();

        try {
            refContent = getRefContent(FILENAME);

            baos = new ByteArrayOutputStream(refContent.length);
            oos = new ObjectOutputStream(baos);

            oos.writeObject(toSerialize);
            content = baos.toByteArray();
            assertTrue("Serialization is not equal to reference platform.",
                        Arrays.equals(content, refContent));
        }
        finally {
            if (oos != null) oos.close();
        }
    }

    public void test_writeLjava_io_ObjectOutputStream() throws Exception {
        Support_GetPutFieldsDeprecated toSerialize = new Support_GetPutFieldsDeprecated();
        byte[] content;
        byte[] refContent;
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos;

        toSerialize.initTestValues();

        try {
            refContent = getRefContent(DEPRECATED_FILENAME);

            baos = new ByteArrayOutputStream(refContent.length);
            oos = new ObjectOutputStream(baos);

            oos.writeObject(toSerialize);
            content = baos.toByteArray();
            assertTrue("Serialization is not equal to reference platform.",
                        Arrays.equals(content, refContent));
        }
        finally {
            if (oos != null) oos.close();
        }
    }

    private byte[] getRefContent(String path) throws Exception {
        int bytesRead;
        byte[] refContent;
        byte[] streamContent = new byte[2000];
        InputStream refStream = null;

        try {
            refStream = getClass().getResourceAsStream(path);
            bytesRead = refStream.read(streamContent);
            assertTrue("Test case implementation error: The byte array to " +
                       "store the reference file is too small.",
                       (refStream.read() == -1));
            refContent = new byte[bytesRead];
            System.arraycopy(streamContent, 0, refContent, 0, bytesRead);
        }
        finally {
            if (refStream != null) refStream.close();
        }
        return refContent;
    }
}
