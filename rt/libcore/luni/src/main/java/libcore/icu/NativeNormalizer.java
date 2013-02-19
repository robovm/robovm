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

import java.text.Normalizer.Form;

public final class NativeNormalizer {
    public static boolean isNormalized(CharSequence src, Form form) {
        return isNormalizedImpl(src.toString(), toUNormalizationMode(form));
    }

    public static String normalize(CharSequence src, Form form) {
        return normalizeImpl(src.toString(), toUNormalizationMode(form));
    }

    private static int toUNormalizationMode(Form form) {
        // Translates Java enum constants to ICU int constants.
        // See UNormalizationMode in "unicode/unorm.h". Stable API since ICU 2.0.
        switch (form) {
        case NFC: return 4;
        case NFD: return 2;
        case NFKC: return 5;
        case NFKD: return 3;
        }
        throw new AssertionError("unknown Normalizer.Form " + form);
    }

    private static native String normalizeImpl(String src, int form);

    private static native boolean isNormalizedImpl(String src, int form);

    private NativeNormalizer() {}
}
