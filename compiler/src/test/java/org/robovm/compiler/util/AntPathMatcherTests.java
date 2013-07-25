/*
 * Copyright 2002-2013 the original author or authors.
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

package org.robovm.compiler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * RoboVM note: This test class was copied from Spring Framework.
 * 
 * @author Alef Arendsen
 * @author Seth Ladd
 * @author Juergen Hoeller
 * @author Arjen Poutsma
 * @author Rossen Stoyanchev
 */
public class AntPathMatcherTests {

    static class TestMatcher {
        String pathSeparator = AntPathMatcher.DEFAULT_PATH_SEPARATOR;
        boolean match(String pattern, String path) {
            return new AntPathMatcher(pattern, pathSeparator).matches(path);
        }
        public void setPathSeparator(String pathSeparator) {
            this.pathSeparator = pathSeparator;
        }
    }
    
    private TestMatcher pathMatcher;

    @Before
    public void createMatcher() {
        pathMatcher = new TestMatcher();
    }

    @Test
    public void match() {
        // test exact matching
        assertTrue(pathMatcher.match("test", "test"));
        assertTrue(pathMatcher.match("/test", "/test"));
        assertFalse(pathMatcher.match("/test.jpg", "test.jpg"));
        assertFalse(pathMatcher.match("test", "/test"));
        assertFalse(pathMatcher.match("/test", "test"));

        // test matching with ?'s
        assertTrue(pathMatcher.match("t?st", "test"));
        assertTrue(pathMatcher.match("??st", "test"));
        assertTrue(pathMatcher.match("tes?", "test"));
        assertTrue(pathMatcher.match("te??", "test"));
        assertTrue(pathMatcher.match("?es?", "test"));
        assertFalse(pathMatcher.match("tes?", "tes"));
        assertFalse(pathMatcher.match("tes?", "testt"));
        assertFalse(pathMatcher.match("tes?", "tsst"));

        // test matchin with *'s
        assertTrue(pathMatcher.match("*", "test"));
        assertTrue(pathMatcher.match("test*", "test"));
        assertTrue(pathMatcher.match("test*", "testTest"));
        assertTrue(pathMatcher.match("test/*", "test/Test"));
        assertTrue(pathMatcher.match("test/*", "test/t"));
        assertTrue(pathMatcher.match("test/*", "test/"));
        assertTrue(pathMatcher.match("*test*", "AnothertestTest"));
        assertTrue(pathMatcher.match("*test", "Anothertest"));
        assertTrue(pathMatcher.match("*.*", "test."));
        assertTrue(pathMatcher.match("*.*", "test.test"));
        assertTrue(pathMatcher.match("*.*", "test.test.test"));
        assertTrue(pathMatcher.match("test*aaa", "testblaaaa"));
        assertFalse(pathMatcher.match("test*", "tst"));
        assertFalse(pathMatcher.match("test*", "tsttest"));
        assertFalse(pathMatcher.match("test*", "test/"));
        assertFalse(pathMatcher.match("test*", "test/t"));
        assertFalse(pathMatcher.match("test/*", "test"));
        assertFalse(pathMatcher.match("*test*", "tsttst"));
        assertFalse(pathMatcher.match("*test", "tsttst"));
        assertFalse(pathMatcher.match("*.*", "tsttst"));
        assertFalse(pathMatcher.match("test*aaa", "test"));
        assertFalse(pathMatcher.match("test*aaa", "testblaaab"));

        // test matching with ?'s and /'s
        assertTrue(pathMatcher.match("/?", "/a"));
        assertTrue(pathMatcher.match("/?/a", "/a/a"));
        assertTrue(pathMatcher.match("/a/?", "/a/b"));
        assertTrue(pathMatcher.match("/??/a", "/aa/a"));
        assertTrue(pathMatcher.match("/a/??", "/a/bb"));
        assertTrue(pathMatcher.match("/?", "/a"));

        // test matching with **'s
        assertTrue(pathMatcher.match("/**", "/testing/testing"));
        assertTrue(pathMatcher.match("/*/**", "/testing/testing"));
        assertTrue(pathMatcher.match("/**/*", "/testing/testing"));
        assertTrue(pathMatcher.match("/bla/**/bla", "/bla/testing/testing/bla"));
        assertTrue(pathMatcher.match("/bla/**/bla", "/bla/testing/testing/bla/bla"));
        assertTrue(pathMatcher.match("/**/test", "/bla/bla/test"));
        assertTrue(pathMatcher.match("/bla/**/**/bla", "/bla/bla/bla/bla/bla/bla"));
        assertTrue(pathMatcher.match("/bla*bla/test", "/blaXXXbla/test"));
        assertTrue(pathMatcher.match("/*bla/test", "/XXXbla/test"));
        assertFalse(pathMatcher.match("/bla*bla/test", "/blaXXXbl/test"));
        assertFalse(pathMatcher.match("/*bla/test", "XXXblab/test"));
        assertFalse(pathMatcher.match("/*bla/test", "XXXbl/test"));

        assertFalse(pathMatcher.match("/????", "/bala/bla"));
        assertFalse(pathMatcher.match("/**/*bla", "/bla/bla/bla/bbb"));

        assertTrue(pathMatcher.match("/*bla*/**/bla/**", "/XXXblaXXXX/testing/testing/bla/testing/testing/"));
        assertTrue(pathMatcher.match("/*bla*/**/bla/*", "/XXXblaXXXX/testing/testing/bla/testing"));
        assertTrue(pathMatcher.match("/*bla*/**/bla/**", "/XXXblaXXXX/testing/testing/bla/testing/testing"));
        assertTrue(pathMatcher.match("/*bla*/**/bla/**", "/XXXblaXXXX/testing/testing/bla/testing/testing.jpg"));

        assertTrue(pathMatcher.match("*bla*/**/bla/**", "XXXblaXXXX/testing/testing/bla/testing/testing/"));
        assertTrue(pathMatcher.match("*bla*/**/bla/*", "XXXblaXXXX/testing/testing/bla/testing"));
        assertTrue(pathMatcher.match("*bla*/**/bla/**", "XXXblaXXXX/testing/testing/bla/testing/testing"));
        assertFalse(pathMatcher.match("*bla*/**/bla/*", "XXXblaXXXX/testing/testing/bla/testing/testing"));

        assertFalse(pathMatcher.match("/x/x/**/bla", "/x/x/x/"));

        assertTrue(pathMatcher.match("", ""));

//        assertTrue(pathMatcher.match("/{bla}.*", "/testing.html"));
    }

    @Test
    public void uniqueDeliminator() {
        pathMatcher.setPathSeparator(".");

        // test exact matching
        assertTrue(pathMatcher.match("test", "test"));
        assertTrue(pathMatcher.match(".test", ".test"));
        assertFalse(pathMatcher.match(".test/jpg", "test/jpg"));
        assertFalse(pathMatcher.match("test", ".test"));
        assertFalse(pathMatcher.match(".test", "test"));

        // test matching with ?'s
        assertTrue(pathMatcher.match("t?st", "test"));
        assertTrue(pathMatcher.match("??st", "test"));
        assertTrue(pathMatcher.match("tes?", "test"));
        assertTrue(pathMatcher.match("te??", "test"));
        assertTrue(pathMatcher.match("?es?", "test"));
        assertFalse(pathMatcher.match("tes?", "tes"));
        assertFalse(pathMatcher.match("tes?", "testt"));
        assertFalse(pathMatcher.match("tes?", "tsst"));

        // test matchin with *'s
        assertTrue(pathMatcher.match("*", "test"));
        assertTrue(pathMatcher.match("test*", "test"));
        assertTrue(pathMatcher.match("test*", "testTest"));
        assertTrue(pathMatcher.match("*test*", "AnothertestTest"));
        assertTrue(pathMatcher.match("*test", "Anothertest"));
        assertTrue(pathMatcher.match("*/*", "test/"));
        assertTrue(pathMatcher.match("*/*", "test/test"));
        assertTrue(pathMatcher.match("*/*", "test/test/test"));
        assertTrue(pathMatcher.match("test*aaa", "testblaaaa"));
        assertFalse(pathMatcher.match("test*", "tst"));
        assertFalse(pathMatcher.match("test*", "tsttest"));
        assertFalse(pathMatcher.match("*test*", "tsttst"));
        assertFalse(pathMatcher.match("*test", "tsttst"));
        assertFalse(pathMatcher.match("*/*", "tsttst"));
        assertFalse(pathMatcher.match("test*aaa", "test"));
        assertFalse(pathMatcher.match("test*aaa", "testblaaab"));

        // test matching with ?'s and .'s
        assertTrue(pathMatcher.match(".?", ".a"));
        assertTrue(pathMatcher.match(".?.a", ".a.a"));
        assertTrue(pathMatcher.match(".a.?", ".a.b"));
        assertTrue(pathMatcher.match(".??.a", ".aa.a"));
        assertTrue(pathMatcher.match(".a.??", ".a.bb"));
        assertTrue(pathMatcher.match(".?", ".a"));

        // test matching with **'s
        assertTrue(pathMatcher.match(".**", ".testing.testing"));
        assertTrue(pathMatcher.match(".*.**", ".testing.testing"));
        assertTrue(pathMatcher.match(".**.*", ".testing.testing"));
        assertTrue(pathMatcher.match(".bla.**.bla", ".bla.testing.testing.bla"));
        assertTrue(pathMatcher.match(".bla.**.bla", ".bla.testing.testing.bla.bla"));
        assertTrue(pathMatcher.match(".**.test", ".bla.bla.test"));
        assertTrue(pathMatcher.match(".bla.**.**.bla", ".bla.bla.bla.bla.bla.bla"));
        assertTrue(pathMatcher.match(".bla*bla.test", ".blaXXXbla.test"));
        assertTrue(pathMatcher.match(".*bla.test", ".XXXbla.test"));
        assertFalse(pathMatcher.match(".bla*bla.test", ".blaXXXbl.test"));
        assertFalse(pathMatcher.match(".*bla.test", "XXXblab.test"));
        assertFalse(pathMatcher.match(".*bla.test", "XXXbl.test"));
    }

}
