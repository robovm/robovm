/*
 * Copyright (C) 2011 The Android Open Source Project
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

package libcore.javax.security.auth.x500;

import org.apache.harmony.security.x509.GeneralName;
import junit.framework.TestCase;

public final class GeneralNameTest extends TestCase {
    // http://code.google.com/p/android/issues/detail?id=21311
    public void testWildcardsInDnsName() throws Exception {
        // examples of potential DNS wildcard locations from RFC 6125 section 7.2
        new GeneralName(GeneralName.DNS_NAME, "*.example.com");
        new GeneralName(GeneralName.DNS_NAME, "fo*.example.com");
        new GeneralName(GeneralName.DNS_NAME, "f*o.example.com");
        new GeneralName(GeneralName.DNS_NAME, "*oo.example.com");
        new GeneralName(GeneralName.DNS_NAME, "www.*.example.com");
        new GeneralName(GeneralName.DNS_NAME, "www.foo*.example.com");
        new GeneralName(GeneralName.DNS_NAME, "*.co.uk");
        new GeneralName(GeneralName.DNS_NAME, "*.com");
        new GeneralName(GeneralName.DNS_NAME, "f*b*r.example.com");
        new GeneralName(GeneralName.DNS_NAME, "*.*.example.com");
    }
}
