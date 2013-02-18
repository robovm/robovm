package android.test.anno;

@AnnoSimpleType
@AnnoSimpleType2
@AnnoSimpleTypeInvis
public class SimplyNoted {
    @AnnoSimpleField
    public int mFoo;

    @AnnoSimpleField
    public static int mOneFoo;

    @AnnoSimpleConstructor
    SimplyNoted() {
        mFoo = 0;
    }

    @AnnoSimpleConstructor
    SimplyNoted(@AnnoSimpleParameter int xyzzy) {
        mFoo = xyzzy;
    }

    @AnnoSimpleMethod
    public int foo() {
        @AnnoSimpleLocalVariable
        int bar = 5;

        return bar;
    }
}
