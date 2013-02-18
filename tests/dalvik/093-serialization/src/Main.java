/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Exercise serialization.
 */
public class Main {

    public static void main(String[] args) {
        testObjectSerialization();
    }

    static void testObjectSerialization() {
        byte[] serialData;

        try {
            serialData = createStream();
            checkStream(serialData);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    static byte[] createStream() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objStream = new ObjectOutputStream(byteStream);

        Sub sub = new Sub('X');
        objStream.writeObject(sub);
        byte[] bytes = byteStream.toByteArray();

        objStream.close();
        byteStream.close();
        return bytes;
    }

    static void checkStream(byte[] input) throws IOException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(input);
        ObjectInputStream objStream = new ObjectInputStream(byteStream);

        Sub sub;
        try {
            sub = (Sub) objStream.readObject();
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }

        objStream.close();
        byteStream.close();

        sub.check();
    }
}

class Base implements Serializable {
    private static final long serialVersionUID = 12345;

    Boolean one;
    Integer two;
    String three;

    public Base() {
        one = true;
        two = Integer.valueOf(2);
        three = "three";
    }
}

class Sub extends Base {
    private static final long serialVersionUID = 54321;

    Double four;
    Float five;
    private Byte six = 26;
    Character seven = '7';
    Short eight;
    long nine;
    public Character thing;

    public Sub(char thing) {
        four = 4.0;
        five = 5.0f;
        six = 6;
        eight = 8;
        nine = 9;
        this.thing = thing;
    }

    public void check() {
        System.out.println("one=" + one + " two=" + two + " three=" + three
            + " four=" + four + " five=" + five + " six=" + six
            + " seven=" + seven + " eight=" + eight + " nine=" + nine
            + " thing=" + thing);
    }
}

