
package android.test.anno;

import java.io.IOException;
import java.io.EOFException;

@AnnoFancyType(num=5, name="full")
public class FullyNoted {
    @AnnoFancyField(nombre="fubar")
    int mBar;

    @AnnoFancyConstructor(numArgs=1)
    FullyNoted(@AnnoFancyParameter(factor=0.5) int bar)
    {
        mBar = bar;
    }

    @AnnoFancyMethod(callMe=true, biteMe=false)
    public int bar(
        @AnnoSimpleParameter int one,
        @AnnoFancyParameter(factor=3.7879912899761) long two)
        throws IOException, EOFException {

        return 0;
    }

    @AnnoFancyMethod(biteMe=true, enumerated=AnnoFancyMethod.AnnoFancyMethodEnum.BAR)
    public int bar1(
        @AnnoSimpleParameter int one,
        @AnnoFancyParameter(factor=3.7879912899761) long two)
        throws IOException {

        return 0;
    }

    public int notAnnotated() {
        return 0;
    }
}
