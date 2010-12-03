/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.text.spi;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.spi.LocaleServiceProvider;

/**
 * This abstract class should be extended by service provider which provides
 * instances of <code>DecimalFormatSymbols</code> class.
 */
public abstract class DecimalFormatSymbolsProvider extends
        LocaleServiceProvider {

    /**
     * The constructor
     *
     */
    protected DecimalFormatSymbolsProvider() {
        // Do nothing.
    }

    /**
     * Get an instance of <code>DecimalFormatSymbols</code> with specified
     * locale.
     * 
     * @param locale
     *            the specified locale
     * @return a <code>DecimalFormatSymbols</code> instance
     * @throws NullPointerException, if locale is null 
     * @throws IllegalArgumentException, if locale isn't one of the locales 
     *            returned from getAvailableLocales().
     */
    public abstract DecimalFormatSymbols getInstance(Locale locale);

}
