package tests.api.java.io;

public class SerializationTestClass implements java.io.Serializable {

    // Test class names
    public class TestClassName1 implements java.io.Serializable {
    }

    public class TestClassName2T_T$T implements java.io.Serializable {
    }

    // Test Modifiers
    public class TestClassModifierPublic implements java.io.Serializable {
    }

    interface TestClassModifierInterfaceHelper extends java.io.Serializable {
    }

    public class TestClassModifierInterface implements
            TestClassModifierInterfaceHelper {
    }

    final class TestClassModifierFinal implements java.io.Serializable {
    }

    abstract class TestClassModifierAbstractHelper implements
            java.io.Serializable {
    }

    public class TestClassModifierAbstract extends
            TestClassModifierAbstractHelper {
    }


    // TODO Arrays always are abstract

    // TODO Non public interface has no abstract modifier


    // Test interfaces
    interface A extends java.io.Serializable {
    }

    interface B extends java.io.Serializable {
    }

    interface C extends A {
    }

    public class TestIntefaces implements java.io.Serializable {
    }

    public class TestIntefacesA implements A {
    }

    public class TestIntefacesAB implements A, B {
    }

    public class TestIntefacesBA implements B, A {
    }

    public class TestIntefacesC implements C {
    }

    public class TestIntefacesAC implements A, C {
    }

    public class TestIntefacesCA implements C, A {
    }

    public class TestIntefacesABC implements A, B, C {
    }

    public class TestIntefacesACB implements A, C, B {
    }

    public class TestIntefacesBAC implements B, A, C {
    }

    public class TestIntefacesBCA implements B, C, A {
    }

    public class TestIntefacesCAB implements C, A, B {
    }

    public class TestIntefacesCBA implements C, B, A {
    }

    /**
     * Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED | Modifier.STATIC |
     * Modifier.FINAL | Modifier.VOLATILE | Modifier.TRANSIENT
     */
    // Test Fields
    public class TestFieldsNone implements java.io.Serializable {
    }

    public class TestFieldsOnePublic implements java.io.Serializable {
        public int one;
    }

    public class TestFieldsTwoPublic implements java.io.Serializable {
        public int one;
        public int two;
    }

    @SuppressWarnings("unused")
    public class TestFieldsOnePrivate implements java.io.Serializable {
        private int one;
    }

    @SuppressWarnings("unused")
    public class TestFieldsTwoPrivate implements java.io.Serializable {
        private int one;
        private int two;
    }

    public class TestFieldsOneProtected implements java.io.Serializable {
        protected int one;
    }

    public class TestFieldsTwoProtected implements java.io.Serializable {
        protected int one;
        protected int two;
    }

    public static class TestFieldsOneStatic implements java.io.Serializable {
        static int one;
    }

    public static class TestFieldsTwoStatic implements java.io.Serializable {
        static int one;
        static int two;
    }

    public class TestFieldsOneFinal implements java.io.Serializable {
        final int one = 0;
    }

    public class TestFieldsTwoFinal implements java.io.Serializable {
        final int one = 0;
        final int two = 0;
    }

    public class TestFieldsOneVolatile implements java.io.Serializable {
        volatile int one;
    }

    public class TestFieldsTwoVolatile implements java.io.Serializable {
        volatile int one;
        volatile int two;
    }

    public class TestFieldsOneTransient implements java.io.Serializable {
        transient int one;
    }

    public class TestFieldsTwoTransient implements java.io.Serializable {
        transient int one;
        transient int two;
    }

    public class TestFieldSignatures implements java.io.Serializable {
        Object l;
        int i;
        short s;
        long j;
        boolean z;
        char c;
        double d;
        float f;
        byte b;
    }


    // Test Constructors
    public class TestConstructorNone implements java.io.Serializable {
    }

    public class TestConstructorOne implements java.io.Serializable {
        public TestConstructorOne() {
        }
    }

    public class TestConstructorTwo implements java.io.Serializable {
        public TestConstructorTwo(byte b) {
        }

        public TestConstructorTwo(char c) {
        }
    }

    public class TestConstructorTwoReverse implements java.io.Serializable {
        public TestConstructorTwoReverse(char c) {
        }

        public TestConstructorTwoReverse(byte b) {
        }
    }


    // Test Constructor Modifiers
    public class TestConstructorPublic implements java.io.Serializable {
        public TestConstructorPublic() {
        }
    }

    public class TestConstructorPrivate implements java.io.Serializable {
        private TestConstructorPrivate() {
        }

        public TestConstructorPrivate(int i) {
            this();
        }
    }

    public class TestConstructorProtected implements java.io.Serializable {
        protected TestConstructorProtected() {
        }
    }
    // TODO constructor modifier strict?
    // TODO constructor modifier static?
    // TODO constructor modifier final?
    // TODO constructor modifier synchronized?
    // TODO constructor modifier native?
    // TODO constructor modifier abstract?


    // Test constructor signature
    public class TestConstructorSignature implements java.io.Serializable {
        public TestConstructorSignature(boolean z, byte b, char c, short s,
                int i, float f, double j, Object l) {
        }
    }


    // Test Method Modifiers
    public class TestMethodPublic implements java.io.Serializable {
        public void method() {
        }
    }

    @SuppressWarnings("unused")
    public class TestMehodPrivate implements java.io.Serializable {
        private void method() {
        }
    }

    public class TestMethodProtected implements java.io.Serializable {
        protected void method() {
        }
    }

    public class TestMethodStrict implements java.io.Serializable {
        strictfp void method() {
        }
    }

    public static class TestMethodStatic implements java.io.Serializable {
        static void method() {
        }
    }

    public class TestMethodFinal implements java.io.Serializable {
        final void method() {
        }
    }

    public class TestMethodSynchronized implements java.io.Serializable {
        synchronized void method() {
        }
    }

    public class TestMethodNative implements java.io.Serializable {
        native void method();
    }

    public abstract class TestMethodAbstractHelper implements
            java.io.Serializable {
        abstract void method();
    }

    public class TestMethodAbstract extends TestMethodAbstractHelper implements
            java.io.Serializable {
        @Override
        void method() {
        }
    }


    // Test method signature
    public class TestMethodSignature implements java.io.Serializable {
        public void method(boolean z, byte b, char c, short s, int i, float f,
                double j, Object l) {
        }
    }


    // Test method return signature
    public class TestMethodReturnSignature implements java.io.Serializable {
        public void methodV() {
        }

        public boolean methodZ() {
            return false;
        }

        public byte methodB() {
            return 0;
        }

        public char methodC() {
            return '0';
        }

        public short methodS() {
            return 0;
        }

        public int methodI() {
            return 0;
        }

        public float methodF() {
            return 0F;
        }

        public double methodD() {
            return 0D;
        }

        public Object methodL() {
            return null;
        }
    }
}
