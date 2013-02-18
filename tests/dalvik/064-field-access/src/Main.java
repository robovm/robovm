/*
 * Copyright (C) 2008 The Android Open Source Project
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

import other.OtherPackage;

import java.lang.reflect.Field;

/*
 * Test field access through reflection.
 */
public class Main {
    public static void main(String[] args) {
        SubOther.main(null);

        try {
            GetNonexistent.main(null);
            System.err.println("Not expected to succeed");
        } catch (VerifyError fe) {
            // dalvik
            System.out.println("Got expected failure");
        } catch (NoSuchFieldError nsfe) {
            // reference
            System.out.println("Got expected failure");
        }
    }

    /*
     * Get the field specified by "field" from "obj".
     *
     * "type" determines which "get" call is made, e.g. 'B' turns into
     * field.getByte().
     *
     * The "expectedException" must match the class of the exception thrown,
     * or be null if no exception was expected.
     *
     * On success, the boxed value retrieved is returned.
     */
    public Object getValue(Field field, Object obj, char type,
            Class expectedException) {

        Object result = null;
        try {
            switch (type) {
            case 'Z':
                result = new Boolean(field.getBoolean(obj));
                break;
            case 'B':
                result = new Byte(field.getByte(obj));
                break;
            case 'S':
                result = new Short(field.getShort(obj));
                break;
            case 'C':
                result = new Character(field.getChar(obj));
                break;
            case 'I':
                result = new Integer(field.getInt(obj));
                break;
            case 'J':
                result = new Long(field.getLong(obj));
                break;
            case 'F':
                result = new Float(field.getFloat(obj));
                break;
            case 'D':
                result = new Double(field.getDouble(obj));
                break;
            case 'L':
                result = field.get(obj);
                break;
            default:
                throw new RuntimeException("bad type '" + type + "'");
            }

            /* success; expected? */
            if (expectedException != null) {
                Throwable th = new Throwable();
                System.err.println("ERROR: call succeeded, was expecting "
                    + expectedException);
                th.printStackTrace();
            }
        } catch (Exception ex) {
            if (expectedException == null) {
                System.err.println("ERROR: call failed unexpectedly: "
                    + ex.getClass());
                ex.printStackTrace();
            } else {
                if (!expectedException.equals(ex.getClass())) {
                    System.err.println("ERROR: incorrect exception: wanted "
                        + expectedException.getName() + ", got "
                        + ex.getClass());
                    ex.printStackTrace();
                }
            }
        }

        return result;
    }
}

/*
 * Local class with some fields.
 */
class SamePackage {
    public byte pubByteField;

    protected byte protByteField;
    protected Object protObjectField;

    private float privFloatField;
}

/*
 * This is a sub-class of OtherPackage, which should be allowed to access
 * the various protected fields.
 */
class SubOther extends OtherPackage {

    protected long protLongField = 0x1122334455667788L;

    /*
     * Perform the various tests.
     *
     * localInst.getValue() is performed using an instance of Main as the
     * source of the reflection call.  otherInst.getValue() uses a subclass
     * of OtherPackage as the source.
     */
    public static void main(String[] args) {
        SubOther subOther = new SubOther();
        subOther.doTests();
    }

    public void doTests() {
        Class localClass = SamePackage.class;
        Class otherClass = OtherPackage.class;
        Field localPubByteField, localProtByteField, localProtObjectField,
              localPrivFloatField;
        Field otherPubCharField, otherProtShortField, otherProtObjectField,
              otherPkgDoubleField;
        Field subProtLongField;
        Main localInst = new Main();
        SamePackage samePkgInst = new SamePackage();
        OtherPackage otherPkgInst = new OtherPackage();
        Object plainObj = new Object();

        /*
         * Locate the various fields.
         */
        try {
            localPubByteField = localClass.getDeclaredField("pubByteField");
            localProtByteField = localClass.getDeclaredField("protByteField");
            localProtObjectField = localClass.getDeclaredField("protObjectField");
            localPrivFloatField = localClass.getDeclaredField("privFloatField");

            otherPubCharField = otherClass.getDeclaredField("pubCharField");
            otherProtShortField = otherClass.getDeclaredField("protShortField");
            otherProtObjectField = otherClass.getDeclaredField("protObjectField");
            otherPkgDoubleField = otherClass.getDeclaredField("pkgDoubleField");

            subProtLongField = getClass().getDeclaredField("protLongField");
        } catch (NoSuchFieldException nsfe) {
            throw new RuntimeException(nsfe);
        }

        /*
         * Get a public field from a class in the same package.
         */
        localInst.getValue(localPubByteField, samePkgInst, 'B', null);

        /*
         * Get a protected field from a class in the same package.
         */
        this.getValue(localProtByteField, samePkgInst, 'B', null);

        /*
         * Get a private field from a class in the same package.
         */
        this.getValue(localPrivFloatField, samePkgInst, 'F',
            IllegalAccessException.class);

        /*
         * Get a protected field from otherInst's superclass.
         *
         * We can get at "this.protShortField" but not
         * "otherPkgInst.protShortField" because we can only access
         * protected fields in instances of our class -- being a subclass
         * of OtherPackage does not allow us to modify protected fields in
         * all other subclasses of OtherPackage.
         */
        this.getValue(otherProtShortField, this, 'S',
            null);
        this.getValue(otherProtShortField, otherPkgInst, 'S',
            IllegalAccessException.class);
        this.getValue(otherPkgDoubleField, otherPkgInst, 'D',
            IllegalAccessException.class);

        /*
         * Null object.  Different exceptions based on which package
         * we would be trying to access and whether or not our object
         * has the correct type.
         */
        localInst.getValue(localPubByteField, null, 'B',
            NullPointerException.class);

        this.getValue(subProtLongField, null, 'J',
            NullPointerException.class);

        this.getValue(localPrivFloatField, null, 'F',
            IllegalAccessException.class);

        localInst.getValue(otherProtShortField, null, 'S',
            IllegalAccessException.class);
        this.getValue(otherProtShortField, null, 'S',
            IllegalAccessException.class);
        this.getValue(otherPkgDoubleField, null, 'D',
            IllegalAccessException.class);

        localInst.getValue(otherProtShortField, null, 'Z',
            IllegalAccessException.class);
        /* -- Dalvik VM currently throws NPE
        this.getValue(subProtLongField, null, 'Z',
            IllegalArgumentException.class);
        */

        /*
         * Valid object, wrong field type.
         */
        this.getValue(subProtLongField, this, 'J',
            null);
        this.getValue(localProtByteField, samePkgInst, 'Z',
            IllegalArgumentException.class);
        this.getValue(subProtLongField, this, 'Z',
            IllegalArgumentException.class);
        this.getValue(localPrivFloatField, this, 'Z',
            IllegalAccessException.class);
        this.getValue(localPrivFloatField, this, 'Z',
            IllegalAccessException.class);
        localInst.getValue(otherProtShortField, otherPkgInst, 'Z',
            IllegalAccessException.class);
        this.getValue(otherProtShortField, otherPkgInst, 'Z',
            IllegalAccessException.class);

        /*
         * Wrong object.
         */
        this.getValue(subProtLongField, plainObj, 'J',
            IllegalArgumentException.class);

        /* wrong object + private field */
        this.getValue(localPrivFloatField, plainObj, 'F',
            IllegalAccessException.class);

        /* wrong object + wrong field type */
        this.getValue(subProtLongField, plainObj, 'Z',
            IllegalArgumentException.class);

        /* wrong object + invalid access */
        localInst.getValue(otherProtShortField, plainObj, 'S',
            IllegalAccessException.class);
        this.getValue(otherProtShortField, plainObj, 'S',
            IllegalAccessException.class);

        System.out.println("good");
    }

    /*
     * [this is a clone of Main.getValue() -- the class issuing the
     * reflection call is significant]
     */
    public Object getValue(Field field, Object obj, char type,
            Class expectedException) {

        Object result = null;
        try {
            switch (type) {
            case 'Z':
                result = new Boolean(field.getBoolean(obj));
                break;
            case 'B':
                result = new Byte(field.getByte(obj));
                break;
            case 'S':
                result = new Short(field.getShort(obj));
                break;
            case 'C':
                result = new Character(field.getChar(obj));
                break;
            case 'I':
                result = new Integer(field.getInt(obj));
                break;
            case 'J':
                result = new Long(field.getLong(obj));
                break;
            case 'F':
                result = new Float(field.getFloat(obj));
                break;
            case 'D':
                result = new Double(field.getDouble(obj));
                break;
            case 'L':
                result = field.get(obj);
                break;
            default:
                throw new RuntimeException("bad type '" + type + "'");
            }

            /* success; expected? */
            if (expectedException != null) {
                Throwable th = new Throwable();
                System.err.println("ERROR: call succeeded, was expecting "
                    + expectedException);
                th.printStackTrace();
            }
        } catch (Exception ex) {
            if (expectedException == null) {
                System.err.println("ERROR: call failed unexpectedly: "
                    + ex.getClass());
                ex.printStackTrace();
            } else {
                if (!expectedException.equals(ex.getClass())) {
                    System.err.println("ERROR: incorrect exception: wanted "
                        + expectedException.getName() + ", got "
                        + ex.getClass());
                    ex.printStackTrace();
                }
            }
        }

        return result;
    }

}
