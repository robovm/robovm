/*
 * Copyright (C) 2010 The Android Open Source Project
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

package libcore.icu;

import java.util.Locale;

/**
 * Provides access to ICU's
 * <a href="http://icu-project.org/apiref/icu4c/classPluralRules.html">PluralRules</a> class.
 * This is not necessary for Java API, but is used by frameworks/base's resources system to
 * ease localization of strings to languages with complex grammatical rules regarding number.
 */
public final class NativePluralRules {
    public static final int ZERO  = 0;
    public static final int ONE   = 1;
    public static final int TWO   = 2;
    public static final int FEW   = 3;
    public static final int MANY  = 4;
    public static final int OTHER = 5;

    private final int address;

    private NativePluralRules(int address) {
        this.address = address;
    }

    @Override protected void finalize() throws Throwable {
        try {
            finalizeImpl(address);
        } finally {
            super.finalize();
        }
    }

    public static NativePluralRules forLocale(Locale locale) {
        return new NativePluralRules(forLocaleImpl(locale.toString()));
    }

    /**
     * Returns the constant defined in this class corresponding
     * to the first rule that matches the given value.
     */
    public int quantityForInt(int value) {
        return quantityForIntImpl(address, value);
    }

    private static native void finalizeImpl(int address);
    private static native int forLocaleImpl(String localeName);
    private static native int quantityForIntImpl(int address, int value);
}
