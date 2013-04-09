/*
 * Copyright (C) 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package tests.api.org.w3c.dom;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.w3c.domts.DOMTestDocumentBuilderFactory;
import org.w3c.domts.DOMTestSuite;
import org.w3c.domts.JAXPDOMTestDocumentBuilderFactory;
import org.w3c.domts.JUnitTestSuiteAdapter;

/**
 * Listing of all the tests that are to be run.
 */
public class AllTests_Level2 {
    /*public static void run() {
        TestRunner.main(new String[] {Main_AllTests.class.getName()});
    }*/

    public static final Test suite() {
        // from http://www.w3.org/DOM/Test/
        // Level 2 Core tests
        DOMTestSuite domSuite;
        try {
            DOMTestDocumentBuilderFactory factory1 =
                new JAXPDOMTestDocumentBuilderFactory(null,
                JAXPDOMTestDocumentBuilderFactory.getConfiguration1());
            
                // android: coalescing, epand, ignorewhitespace, namespace, NOT validation

            domSuite = new org.w3c.domts.level2.core.alltests(factory1);
        } catch (Exception e) {
            throw new RuntimeException("problem creating dom test suite", e);
        }
        TestSuite suite = new JUnitTestSuiteAdapter(domSuite);
        return suite;
    }
}
