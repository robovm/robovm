package org.apache.harmony.luni.tests.java.net;

import dalvik.annotation.SideEffect;

import junit.framework.TestCase;

import tests.support.Support_Configuration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.ContentHandler;
import java.net.ContentHandlerFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ContentHandlerFactoryTest extends TestCase {

    ContentHandlerFactory oldFactory = null;
    Field factoryField = null;

    boolean isTestable = false;

    boolean isGetContentCalled = false;
    boolean isCreateContentHandlerCalled = false;

    @SideEffect("This test affects tests that are run after this one." +
            " The reason are side effects due to caching in URLConnection." +
            " Maybe this test needs to be run in isolation.")
    public void test_createContentHandler() throws IOException {

        TestContentHandlerFactory factory =  new TestContentHandlerFactory();

        if(isTestable) {

            assertFalse(isCreateContentHandlerCalled);

            URL url = new URL("http://" +
                    Support_Configuration.SpecialInetTestAddress);

            URLConnection.setContentHandlerFactory(factory);

            URLConnection con = url.openConnection();

            try {
                con.getContent();
                assertTrue(isCreateContentHandlerCalled);
                assertTrue(isGetContentCalled);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            isGetContentCalled = false;

            try {
                con.getContent(new Class[] {});
                assertTrue(isGetContentCalled);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try {
                con.setContentHandlerFactory(factory);
                fail("java.lang.Error was not thrown.");
            } catch(java.lang.Error e) {
                //expected
            }

            try {
                con.setContentHandlerFactory(null);
                fail("java.lang.Error was not thrown.");
            } catch(java.lang.Error e) {
                //expected
            }

        } else {
            ContentHandler ch = factory.createContentHandler("text/plain");
            URL url;
            try {
                url = new URL("http://" +
                        Support_Configuration.SpecialInetTestAddress);
                assertNotNull(ch.getContent(url.openConnection()));
            } catch (MalformedURLException e) {
                fail("MalformedURLException was thrown: " + e.getMessage());
            } catch (IOException e) {
                fail("IOException was thrown.");
            }
        }
    }

    public void setUp() {
        Field [] fields = URLConnection.class.getDeclaredFields();
        int counter = 0;
        for (Field field : fields) {
            if (ContentHandlerFactory.class.equals(field.getType())) {
                counter++;
                factoryField = field;
            }
        }

        if(counter == 1) {

            isTestable = true;

            factoryField.setAccessible(true);
            try {
                oldFactory = (ContentHandlerFactory) factoryField.get(null);
            } catch (IllegalArgumentException e) {
                fail("IllegalArgumentException was thrown during setUp: "
                        + e.getMessage());
            } catch (IllegalAccessException e) {
                fail("IllegalAccessException was thrown during setUp: "
                        + e.getMessage());
            }
        }
    }

    public void tearDown() {
        if(isTestable) {
            try {
                factoryField.set(null, oldFactory);
            } catch (IllegalArgumentException e) {
                fail("IllegalArgumentException was thrown during tearDown: "
                        + e.getMessage());
            } catch (IllegalAccessException e) {
                fail("IllegalAccessException was thrown during tearDown: "
                        + e.getMessage());
            }
        }
    }

    public class TestContentHandler extends ContentHandler {

        public Object getContent(URLConnection u) {
            isGetContentCalled = true;
            return null;
        }
    }

    public class TestContentHandlerFactory implements ContentHandlerFactory {

        public ContentHandler createContentHandler(String mimetype) {
            isCreateContentHandlerCalled = true;
            return new TestContentHandler();
        }
    }
}
