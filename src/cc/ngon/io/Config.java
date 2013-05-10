package cc.ngon.io;

/**
 *
 * @author Ben Cochrane
 */
import java.io.File;
import java.io.IOException;

import javax.xml.xpath.*;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public final class Config {

    private Config() {
    }

    public static boolean LoadConfig(String config) {
        try {
            configLocation = config;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            Config.config = builder.parse(new File(config));
            return true;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            L.ex(ex);
        }
        return false;
    }

    public static String getProperty(String name) {
        return getProperty("global", name);
    }

    public static String getProperty(String group, String name) {
        if (Config.config == null) {
            return "";
        } else {
            XPathFactory xfac = XPathFactory.newInstance();
            XPathExpression xpr = null;
            XPath xpath = xfac.newXPath();
            try {
                xpr = xpath.compile("//propertyGroup[@name='" + group + "']/property[@name='" + name + "']/text()");
                return (String) xpr.evaluate(Config.config, XPathConstants.STRING);
            } catch (XPathExpressionException ex) {
                L.ex(ex);
                return null;
            }
        }
    }

    public static boolean getBoolean(String name) {
        return Boolean.parseBoolean(getProperty(name));
    }

    public static boolean getBoolean(String group, String name) {
        return Boolean.parseBoolean(getProperty(group, name));
    }

    public static int getInt(String name) {
        return Integer.parseInt(getProperty(name));
    }

    public static int getInt(String group, String name) {
        return Integer.parseInt(getProperty(group, name));
    }

    public static double getDouble(String name) {
        return Double.parseDouble(getProperty(name));
    }

    public static double getDouble(String group, String name) {
        return Double.parseDouble(getProperty(group, name));
    }

    public static boolean setProperty(String name, String value) {
        return setProperty("global", name, value);
    }

    public static boolean setProperty(String group, String name, String value) {
        XPathFactory xfac = XPathFactory.newInstance();
        XPathExpression xpr = null;
        XPath xpath = xfac.newXPath();
        try {
            xpr = xpath.compile("//propertyGroup[@name='" + group + "']/property[@name='" + name + "']/text()");
            Node n = (Node) xpr.evaluate(Config.config, XPathConstants.NODE);
            n.setNodeValue(value);
            return XmlDocumentWriter.write(config);
        } catch (XPathExpressionException ex) {
            L.ex(ex);
            return false;
        }
    }
    private static Document config;
    private static String configLocation;
    
    private static class XmlDocumentWriter {
        public static boolean write(Document doc) {
            // TODO: Implement this writer.
            return false;
        }
    }
}