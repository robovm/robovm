// Copyright 2008 The Android Open Source Project

import java.lang.reflect.Field;

/**
 * Try some stuff with enumerations.
 */
public class Main {
    public enum Shubbery { GROUND, CRAWLING, HANGING }

    public static void main(String[] args) {
        Field field;
        try {
            field = Shubbery.class.getDeclaredField("CRAWLING");
        } catch (NoSuchFieldException nsfe) {
            throw new RuntimeException(nsfe);
        }

        System.out.println("found field " + field.getName());
        System.out.println("  synthetic? " + field.isSynthetic());
        System.out.println("  enum? " + field.isEnumConstant());
    }
}
