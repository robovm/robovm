package android.test.anno;

@AnnoFancyType(num=5)       // first occurrence of AnnoFancyType
                            // we inherit @AnnoSimpleType
@AnnoSimpleType2            // AnnoSimpleType2 here *and* inherited from parent
public class SubNoted extends SimplyNoted implements INoted {
    int mBar;

    public int bar() {
        return 0;
    }
}
