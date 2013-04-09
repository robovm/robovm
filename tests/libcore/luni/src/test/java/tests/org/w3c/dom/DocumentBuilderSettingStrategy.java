package tests.org.w3c.dom;

import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilderFactory;

public abstract class DocumentBuilderSettingStrategy {
    protected DocumentBuilderSettingStrategy() {
    }

    private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    public boolean hasConflict(DocumentBuilderSettingStrategy other) {
        return (other == this);
    }

    public abstract void applySetting(DocumentBuilderFactory factory,
            boolean value);

    public abstract boolean hasSetting(DOMDocumentBuilderFactory factory);

    public static final DocumentBuilderSettingStrategy coalescing = new DocumentBuilderSettingStrategy() {
        public void applySetting(DocumentBuilderFactory factory,
                boolean value) {
            factory.setCoalescing(value);
        }

        public boolean hasSetting(DOMDocumentBuilderFactory factory) {
            return factory.isCoalescing();
        }

    };

    public static final DocumentBuilderSettingStrategy expandEntityReferences = new DocumentBuilderSettingStrategy() {
        public void applySetting(DocumentBuilderFactory factory, boolean value) {
            factory.setExpandEntityReferences(value);
        }

        public boolean hasSetting(DOMDocumentBuilderFactory factory) {
            return factory.isExpandEntityReferences();
        }
    };

    public static final DocumentBuilderSettingStrategy ignoringElementContentWhitespace = new DocumentBuilderSettingStrategy() {
        public void applySetting(DocumentBuilderFactory factory, boolean value) {
            factory.setIgnoringElementContentWhitespace(value);
        }

        public boolean hasSetting(DOMDocumentBuilderFactory factory) {
            return factory.isIgnoringElementContentWhitespace();
        }
    };

    public static final DocumentBuilderSettingStrategy ignoringComments = new DocumentBuilderSettingStrategy() {
        public void applySetting(DocumentBuilderFactory factory, boolean value) {
            if (value) {
                System.out.println("ignoreComments=true not supported");
            }
        }

        public boolean hasSetting(DOMDocumentBuilderFactory factory) {
            return false;
        }
    };

    public static final DocumentBuilderSettingStrategy namespaceAware = new DocumentBuilderSettingStrategy() {
        public void applySetting(DocumentBuilderFactory factory, boolean value)
                {
            factory.setNamespaceAware(value);
        }

        public boolean hasSetting(DOMDocumentBuilderFactory factory) {
            return factory.isNamespaceAware();
        }
    };

    public static final DocumentBuilderSettingStrategy validating = new DocumentBuilderSettingStrategy() {
        public void applySetting(DocumentBuilderFactory factory, boolean value)
                {
            factory.setValidating(value);
        }

        public boolean hasSetting(DOMDocumentBuilderFactory factory) {
            return factory.isValidating();
        }
    };

    public static final DocumentBuilderSettingStrategy signed = new DocumentBuilderSettingStrategy() {
        public void applySetting(DocumentBuilderFactory factory, boolean value)
                 {
            if (!value) {
                System.out.println("DocumentBuilderSetting.notSigned");
            }
        }

        public boolean hasSetting(DOMDocumentBuilderFactory factory) {
            return true;
        }
    };

    public static final DocumentBuilderSettingStrategy hasNullString = new DocumentBuilderSettingStrategy() {
        public void applySetting(DocumentBuilderFactory factory, boolean value)
                 {
            if (!value) {
                System.out.println("DocumentBuilderSetting.notHasNullString");
            }
        }

        public boolean hasSetting(DOMDocumentBuilderFactory factory) {
            return true;
        }
    };

    public static final DocumentBuilderSettingStrategy schemaValidating = new DocumentBuilderSettingStrategy() {
        public void applySetting(DocumentBuilderFactory factory, boolean value)
                 {
            if (value) {
                factory.setNamespaceAware(true);
                factory.setValidating(true);
                factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            } else {
                factory.setAttribute(JAXP_SCHEMA_LANGUAGE,
                        "http://www.w3.org/TR/REC-xml");
            }
        }

        public boolean hasSetting(DOMDocumentBuilderFactory factory) {
            try {
                if (factory.isValidating()) {
                    Method getAttrMethod = factory.getClass().getMethod(
                            "getAttribute", new Class[] { String.class });
                    String val = (String) getAttrMethod.invoke(factory,
                            new Object[] { JAXP_SCHEMA_LANGUAGE });
                    return W3C_XML_SCHEMA.equals(val);
                }
            } catch (Exception ex) {
            }
            return false;
        }

        //
        // schema validating conflicts with namespaceAware
        // and validating
        //
        public boolean hasConflict(DocumentBuilderSettingStrategy other) {
            if (other == this
                    || other == DocumentBuilderSettingStrategy.namespaceAware
                    || other == DocumentBuilderSettingStrategy.validating) {
                return true;
            }
            return false;
        }

    };

}
