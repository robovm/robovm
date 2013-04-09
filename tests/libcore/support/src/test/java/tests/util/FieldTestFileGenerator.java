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

package tests.util;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import tests.support.Support_GetPutFields;
import tests.support.Support_GetPutFieldsDeprecated;
import tests.support.Support_GetPutFieldsDefaulted;

/**
 * Writes three test files that are used as reference in
 * {@code tests.api.java.io.ObjectInputStreamGetFieldTest} and
 * {@code tests.api.java.io.ObjectOutputStreamPutFieldTest}.
 * These files must be moved to
 * {@code $ANDROID_BUILD_TOP/dalvik/libcore/luni/src/test/resources/tests/api/java/io}
 * to be included at the correct location in the core tests package.
 * <p>
 * <strong>Important:</strong> Before executing this class, the contents of class
 * {@code tests.support.Support_GetPutFieldsDefaulted} must be commented out. See the
 * description there for further information.
 */
public class FieldTestFileGenerator {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Support_GetPutFields toSerialize = new Support_GetPutFields();
        Support_GetPutFieldsDeprecated toSerializeDeprecated =
                new Support_GetPutFieldsDeprecated();
        Support_GetPutFieldsDefaulted toSerializeDefaulted =
                new Support_GetPutFieldsDefaulted();
        boolean success = true;

        toSerialize.initTestValues();
        toSerializeDeprecated.initTestValues();
        toSerializeDefaulted.initTestValues();

        System.out.println("Trying to write the test file 'testFields.ser'...");
        try {
            fos = new FileOutputStream("testFields.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(toSerialize);
            oos.close();
        }
        catch (Exception e) {
            System.out.println("Exception occured while writing the file: " + e);
            success = false;
        }
        finally {
            if (fos != null) fos.close();
        }

        System.out.println("Trying to write the test file 'testFieldsDeprecated.ser'...");
        try {
            fos = new FileOutputStream("testFieldsDeprecated.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(toSerializeDeprecated);
            oos.close();
        }
        catch (Exception e) {
            System.out.println("Exception occured while writing the file: " + e);
            success = false;
        }
        finally {
            if (fos != null) fos.close();
        }

        System.out.println("Trying to write the test file 'testFieldsDefaulted.ser'...");
        try {
            fos = new FileOutputStream("testFieldsDefaulted.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(toSerializeDefaulted);
            oos.close();
        }
        catch (Exception e) {
            System.out.println("Exception occured while writing the file: " + e);
            success = false;
        }
        finally {
            if (fos != null) fos.close();
        }

        if (success) {
            System.out.println("Success!");
        } else {
            System.out.println("Failure!");
        }

    }
 }
