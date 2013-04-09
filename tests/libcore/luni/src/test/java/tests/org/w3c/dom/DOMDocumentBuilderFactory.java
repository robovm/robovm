package tests.org.w3c.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DOMDocumentBuilderFactory {
    /**
     * Parser configuration
     */
    private DocumentBuilderSetting[] settings = null;

    private DocumentBuilder builder = null;

    private DocumentBuilderFactory factory = null;

    public DOMDocumentBuilderFactory(DocumentBuilderSetting[] settings) {
        if (settings == null) {
            settings = new DocumentBuilderSetting[0];
        } else {
            settings = (DocumentBuilderSetting[]) settings.clone();
        }

        factory = DocumentBuilderFactory.newInstance();

        if (factory == null) {
            throw new RuntimeException("DocumentBuilderFactory must not be null");
        }

        if (settings != null) {
            for (int i = 0; i < settings.length; i++) {
                settings[i].applySetting(factory);
            }
        }
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        if (builder == null) {
            throw new RuntimeException("DocumentBuilder must not be null");
        }

    }

    public DocumentBuilder getBuilder() {
        return builder;
    }

    public boolean hasFeature(String feature, String version) {
        return builder.getDOMImplementation().hasFeature(feature, version);
    }

    public boolean isCoalescing() {
        return factory.isCoalescing();
    }

    public boolean isExpandEntityReferences() {
        return factory.isExpandEntityReferences();
    }

    public boolean isIgnoringElementContentWhitespace() {
        return factory.isIgnoringElementContentWhitespace();
    }

    public boolean isNamespaceAware() {
        return factory.isNamespaceAware();
    }

    public boolean isValidating() {
        return factory.isValidating();
    }

    public static DocumentBuilderSetting[] getConfiguration1() {
        return new DocumentBuilderSetting[] {
                DocumentBuilderSetting.notCoalescing,
                DocumentBuilderSetting.notExpandEntityReferences,
                DocumentBuilderSetting.notIgnoringElementContentWhitespace,
                DocumentBuilderSetting.notNamespaceAware,
                DocumentBuilderSetting.notValidating };
    }

    public static DocumentBuilderSetting[] getConfiguration2() {
        return new DocumentBuilderSetting[] {
                DocumentBuilderSetting.notCoalescing,
                DocumentBuilderSetting.notExpandEntityReferences,
                DocumentBuilderSetting.notIgnoringElementContentWhitespace,
                DocumentBuilderSetting.namespaceAware,
                DocumentBuilderSetting.notValidating };

    }

}
