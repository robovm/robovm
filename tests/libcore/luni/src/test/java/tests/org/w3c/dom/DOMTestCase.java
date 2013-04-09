package tests.org.w3c.dom;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import junit.framework.TestCase;

public class DOMTestCase extends TestCase {

    public Document load(String docURI, DocumentBuilder builder) {
        Document doc = load(resolveURI(docURI), builder);
        return doc;
    }

    public Document load(URL url, DocumentBuilder builder) {
        Document doc = null;
        Exception parseException = null;
        try {
            LoadErrorHandler errorHandler = new LoadErrorHandler();
            builder.setErrorHandler(errorHandler);
            doc = builder.parse(url.openStream());
            parseException = errorHandler.getFirstException();
        } catch (Exception ex) {
            parseException = ex;
        }
        builder.setErrorHandler(null);
        if (parseException != null) {
            // fail("Unexpected exception " + parseException.getMessage());
            throw new RuntimeException("Unexpected exception " + parseException.getMessage(), parseException);
        }
        return doc;
    }

    public void preload(String contentType, String docURI,
            boolean willBeModified) {
        if ("text/html".equals(contentType)
                || "application/xhtml+xml".equals(contentType)) {
            if (docURI.startsWith("staff")
                    || docURI.equals("datatype_normalization")) {

            }
        }
    }

    private URL resolveURI(String baseURI) {
        String docURI = baseURI + ".xml";

        URL resolvedURI = null;
        try {
            resolvedURI = new URL(docURI);
            if (resolvedURI.getProtocol() != null) {
                return resolvedURI;
            }
        } catch (MalformedURLException ex) {
            // fail("Unexpected exception " + ex.getMessage());
        }
        //
        // build a URL for a test file in the JAR
        //
        resolvedURI = getClass().getResource("/" + docURI);
        if (resolvedURI == null) {
            //
            // see if it is an absolute URI
            //
            int firstSlash = docURI.indexOf('/');
            try {
                if (firstSlash == 0
                        || (firstSlash >= 1 && docURI.charAt(firstSlash - 1) == ':')) {
                    resolvedURI = new URL(docURI);
                } else {
                    //
                    // try the files/level?/spec directory
                    //
                    String filename = getClass().getPackage().getName();
                    filename = "tests/"
                            + filename.substring(14).replace('.', '/')
                            + "/files/" + docURI;
                    resolvedURI = new java.io.File(filename).toURL();
                }
            } catch (MalformedURLException ex) {
                fail("Unexpected exception " + ex.getMessage());
            }
        }

        if (resolvedURI == null) {
            fail("resolvedURI is null ");
        }
        return resolvedURI;
    }


    public String getContentType() {
        return "xml";
    }

    public void assertURIEquals(String assertID, String scheme, String path,
            String host, String file, String name, String query,
            String fragment, Boolean isAbsolute, String actual) {
        //
        // URI must be non-null
        assertNotNull(assertID, actual);

        String uri = actual;

        int lastPound = actual.lastIndexOf("#");
        String actualFragment = "";
        if (lastPound != -1) {
            //
            // substring before pound
            //
            uri = actual.substring(0, lastPound);
            actualFragment = actual.substring(lastPound + 1);
        }
        if (fragment != null) {
            assertEquals(assertID, fragment, actualFragment);

        }
        int lastQuestion = uri.lastIndexOf("?");
        String actualQuery = "";
        if (lastQuestion != -1) {
            //
            // substring before pound
            //
            uri = actual.substring(0, lastQuestion);
            actualQuery = actual.substring(lastQuestion + 1);
        }
        if (query != null) {
            assertEquals(assertID, query, actualQuery);

        }
        int firstColon = uri.indexOf(":");
        int firstSlash = uri.indexOf("/");
        String actualPath = uri;
        String actualScheme = "";
        if (firstColon != -1 && firstColon < firstSlash) {
            actualScheme = uri.substring(0, firstColon);
            actualPath = uri.substring(firstColon + 1);
        }

        if (scheme != null) {
            assertEquals(assertID, scheme, actualScheme);
        }

        if (path != null) {
            assertEquals(assertID, path, actualPath);
        }

        if (host != null) {
            String actualHost = "";
            if (actualPath.startsWith("//")) {
                int termSlash = actualPath.indexOf("/", 2);
                actualHost = actualPath.substring(0, termSlash);
            }
            assertEquals(assertID, host, actualHost);
        }

        String actualFile = actualPath;
        if (file != null || name != null) {
            int finalSlash = actualPath.lastIndexOf("/");
            if (finalSlash != -1) {
                actualFile = actualPath.substring(finalSlash + 1);
            }
            if (file != null) {
                assertEquals(assertID, file, actualFile);
            }
        }

        if (name != null) {
            String actualName = actualFile;
            int finalPeriod = actualFile.lastIndexOf(".");
            if (finalPeriod != -1) {
                actualName = actualFile.substring(0, finalPeriod);
            }
            assertEquals(assertID, name, actualName);
        }

        if (isAbsolute != null) {
            //
            // Jar URL's will have any actual path like file:/c:/somedrive...
            assertEquals(assertID, isAbsolute.booleanValue(), actualPath
                    .startsWith("/")
                    || actualPath.startsWith("file:/"));
        }
    }


    private class LoadErrorHandler implements org.xml.sax.ErrorHandler {
        private SAXException parseException;

        private int errorCount;

        private int warningCount;

        public LoadErrorHandler() {
            parseException = null;
            errorCount = 0;
            warningCount = 0;
        }

        public void error(SAXParseException ex) {
            errorCount++;
            if (parseException == null) {
                parseException = ex;
            }
        }

        public void warning(SAXParseException ex) {
            warningCount++;
        }

        public void fatalError(SAXParseException ex) {
            if (parseException == null) {
                parseException = ex;
            }
        }

        public SAXException getFirstException() {
            return parseException;
        }
    }
}
