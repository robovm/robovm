package android.test.anno;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface AnnoFancyMethod {
    enum AnnoFancyMethodEnum { FOO, BAR };
    boolean callMe() default false;
    boolean biteMe();
    AnnoFancyMethodEnum enumerated() default AnnoFancyMethodEnum.FOO;
    Class someClass() default SomeClass.class;
}
