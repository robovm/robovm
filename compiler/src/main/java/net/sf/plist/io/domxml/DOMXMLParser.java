/*
Property List DOM XML Parser - LGPL licensed
Copyright (C) 2011  Yørn de Jong

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

This file was obtained from http://plist.sf.net/
*/
package net.sf.plist.io.domxml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.plist.*;
import net.sf.plist.io.PropertyListException;
import net.sf.plist.io.PropertyListParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import util.Base64;

/**
 * Parses XML property list files to a tree consisting of {@link NSObject}s
 */
public final class DOMXMLParser extends PropertyListParser implements EntityResolver {

	/** The XML document */
	protected Document doc;
	/** The DocumentBuilderFactory */
	final static protected DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	/** The DocumentBuilder */
	final protected DocumentBuilder db;
	
	/** DTD obtained from http://www.apple.com/DTDs/PropertyList-1.0.dtd, with comments removed */
	public static final String DTD = "<!ENTITY % plistObject \"(array | data | date | dict | real | integer | string | true | false )\" >"
	                        + "\n" + "<!ELEMENT plist %plistObject;>"
	                        + "\n" + "<!ATTLIST plist version CDATA \"1.0\" >"
	                        + "\n" + "<!ELEMENT array (%plistObject;)*>"
	                        + "\n" + "<!ELEMENT dict (key, %plistObject;)*>"
	                        + "\n" + "<!ELEMENT key (#PCDATA)>"
	                        + "\n" + "<!ELEMENT string (#PCDATA)>"
	                        + "\n" + "<!ELEMENT data (#PCDATA)>"
	                        + "\n" + "<!ELEMENT date (#PCDATA)>"
	                        + "\n" + "<!ELEMENT true EMPTY>"
	                        + "\n" + "<!ELEMENT false EMPTY>"
	                        + "\n" + "<!ELEMENT real (#PCDATA)>"
	                        + "\n" + "<!ELEMENT integer (#PCDATA)>";
	                               	
	/** @see PropertyListParser#PropertyListParser(File) */
	public DOMXMLParser(File file) throws IOException, PropertyListException {
		this(file, new FileInputStream(file));
	}
	/** @see PropertyListParser#PropertyListParser(InputStream) */
	public DOMXMLParser(InputStream input) throws IOException, PropertyListException {
		this(null, input);
	}
	private DOMXMLParser(File file, InputStream input) throws IOException, PropertyListException {
		super(file, input);
		try {
			db = dbf.newDocumentBuilder();
			db.setEntityResolver(this);
		} catch (ParserConfigurationException e) {
			// This happens when the configuration of DocumentBuilderFactory is incorrect.
			// Since we're not changing this configuration,
			// i'll assume that it isn't our fault.
			// If you think it is, please report it.
			// https://sourceforge.net/p/plist/tickets/
			throw new UnsupportedOperationException(e);
		}
		try {
			doc = file==null ? db.parse(input) : db.parse(file);
		} catch (SAXException e) {
			throw new PropertyListException("The property list is not a valid XML document.", e);
		}
	}
	
	/** {@inheritDoc} */
	public InputSource resolveEntity(String publicId, String systemId) {
		return new InputSource(new ByteArrayInputStream(DTD.getBytes()));
	}
	
	/** {@inheritDoc} */
	@Override
	public NSObject parse() throws PropertyListException {
		final Element root = doc.getDocumentElement();
		final NodeList children = root.getChildNodes();
		Node childNode = null;
		for(int i=0;i<children.getLength();i++) {
			if (children.item(i).getNodeType() == Node.TEXT_NODE) {
				if (children.item(i).getTextContent().trim().length() > 0)
					throw new PropertyListException("Unexpected text content in root PList node.");
			} else {
				if (childNode != null)
					throw new PropertyListException("The property list appears to contain more than one root NSObject.");
				childNode = children.item(i);
			}
		}
		return parseNode(childNode);
	}
	
	/**
	 * Convert a node to an NSObject
	 * @param node the node to parse
	 * @return the NSObject
	 * @throws PropertyListException when parsing fails
	 */
	protected static NSObject parseNode(Node node) throws PropertyListException {
		final String nodeName = node.getNodeName().toLowerCase();
		if ("string".equals(nodeName))
			return parseString(node);
		if ("integer".equals(nodeName) || "real".equals(nodeName))
			return parseNumber(node);
		if ("date".equals(nodeName))
			return parseDate(node);
		if ("data".equals(nodeName))
			return parseData(node);
		if ("true".equals(nodeName) || "false".equals(nodeName))
			return parseBoolean(node);
		if ("dict".equals(nodeName))
			return parseDictionary(node);
		if ("array".equals(nodeName))
			return parseArray(node);
		throw new PropertyListException("Unexpected node: "+node.getNodeName());
	}
	
	/**
	 * Convert a node to an NSArray
	 * @param node the node to parse
	 * @return the NSArray
	 * @throws PropertyListException when parsing fails
	 */
	protected static NSArray parseArray(Node node) throws PropertyListException {
		final NodeList children = node.getChildNodes();
		final ArrayList<NSObject> result = new ArrayList<NSObject>();
		for(int i=0;i<children.getLength();i++) {
			if (children.item(i).getNodeType() == Node.TEXT_NODE) {
				if (children.item(i).getTextContent().trim().length() > 0)
					throw new PropertyListException("Unexpected text content in NSArray node.");
			} else {
				result.add(parseNode(children.item(i)));
			}
		}
		return new NSArray(result);
	}
	
	/**
	 * Convert a node to an NSDictionary
	 * @param node the node to parse
	 * @return the NSDictionary
	 * @throws PropertyListException when parsing fails
	 */
	protected static NSDictionary parseDictionary(Node node) throws PropertyListException {
		final NodeList children = node.getChildNodes();
		final HashMap<String,NSObject> result = new HashMap<String,NSObject>();
		String key = null;
		for(int i=0;i<children.getLength();i++) {
			if (children.item(i).getNodeType() == Node.TEXT_NODE) {
				if (children.item(i).getTextContent().trim().length() > 0)
						throw new PropertyListException("Unexpected text content in NSDictionary node.");
			} else {
				if (key == null)
					key = parseKey(children.item(i));
				else {
					result.put(key, parseNode(children.item(i)));
					key = null;
				}
			}
		}
		return new NSDictionary(result);
	}
	
	/**
	 * Convert a node to an NSDictionary
	 * @param node the node to parse
	 * @return the NSDictionary
	 * @throws PropertyListException when parsing fails
	 */
	protected static NSBoolean parseBoolean(Node node) throws PropertyListException {
		if (node.getChildNodes().getLength() > 0)
			throw new PropertyListException("Unexpected child nodes in NSBoolean node.");
		if (node.getNodeName().toLowerCase().equals("true"))
			return NSBoolean.TRUE;
		if (node.getNodeName().toLowerCase().equals("false"))
			return NSBoolean.FALSE;
		throw new PropertyListException("Expected true or false, got "+node.getNodeName());
	}
	
	/**
	 * Convert a node to an NSDictionary
	 * @param node the node to parse
	 * @return the NSDictionary
	 * @throws PropertyListException when parsing fails
	 */
	protected static NSData parseData(Node node) throws PropertyListException {
		return new NSData(Base64.decode(node.getTextContent()));
	}
	
	/**
	 * Convert a node to an NSDictionary
	 * @param node the node to parse
	 * @return the NSDictionary
	 * @throws PropertyListException when parsing fails
	 */
	protected static NSDate parseDate(Node node) throws PropertyListException {
		try {
			return new NSDate(NSDate.getFormatter().parse(node.getTextContent()));
		} catch (ParseException e) {
			throw new PropertyListException("Invalid date", e);
		}
	}
	
	/**
	 * Convert a node to an NSNumber
	 * @param node the node to parse
	 * @return the NSNumber
	 * @throws PropertyListException when parsing fails
	 */
	protected static NSNumber parseNumber(Node node) throws PropertyListException {
		final String number = node.getTextContent();
		try {
			return NSNumber.createInstance(NumberFormat.getNumberInstance().parse(number));
		} catch (ParseException pe) {
			throw new PropertyListException("Expected number but got "+number, pe);
		}
	}
	
	/**
	 * Convert a node to a key
	 * @param node the node to parse
	 * @return the key name
	 * @throws PropertyListException when parsing fails
	 */
	protected static String parseKey(Node node) throws PropertyListException {
		if (node.getChildNodes().getLength() != 1)
			throw new PropertyListException("Key got "+node.getChildNodes().getLength()+" children, expecting 1.");
		return node.getTextContent();
	}
	
	/**
	 * Convert a node to an NSString
	 * @param node the node to parse
	 * @return the NSString
	 * @throws PropertyListException when parsing fails
	 */
	protected static NSString parseString(Node node) {
		return new NSString(node.getTextContent());
	}

}
