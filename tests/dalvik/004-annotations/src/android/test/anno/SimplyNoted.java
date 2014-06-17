package android.test.anno;

@AnnoSimpleType
@AnnoSimpleType2
@AnnoSimpleTypeInvis
@MissingAnnotation
public class SimplyNoted {
    @AnnoSimpleField
    @MissingAnnotation
    public int mFoo;

    @AnnoSimpleField
    @MissingAnnotation
    public static int mOneFoo;

    @AnnoSimpleConstructor
    @MissingAnnotation
    SimplyNoted() {
        mFoo = 0;
    }

    @AnnoSimpleConstructor
    @MissingAnnotation
    SimplyNoted(@AnnoSimpleParameter int xyzzy) {
        mFoo = xyzzy;
    }

    @AnnoSimpleMethod
    @MissingAnnotation
    public int foo() {
        @AnnoSimpleLocalVariable
        int bar = 5;

        return bar;
    }
}
