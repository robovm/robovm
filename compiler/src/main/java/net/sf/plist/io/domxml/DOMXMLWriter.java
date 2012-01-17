/*
Property List DOm XML Writer - LGPL licensed
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

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.plist.*;
import net.sf.plist.io.PropertyListException;
import net.sf.plist.io.PropertyListWriter;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import util.Base64;

/**
 * Serializes a tree consisting of {@link NSObject}s to an XML property list
 */
public class DOMXMLWriter extends PropertyListWriter {

	/** The XML document */
	final protected Document doc;
	/** The DocumentBuilderFactory */
	final protected static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	/** The DocumentBuilder */
	final protected DocumentBuilder db;
	
	/** The public declaration of the document type */
	public final static String DOCTYPE_PUBLIC = "-//Apple//DTD PLIST 1.0//EN";
	/** The URL of the document type */
	public final static String DOCTYPE_SYSTEM = "http://www.apple.com/DTDs/PropertyList-1.0.dtd";
	/** Version of this XML format */
	public static final String VERSION = "1.0";
	
	/** @see PropertyListWriter#PropertyListWriter(NSObject) */
	public DOMXMLWriter(NSObject root) throws ParserConfigurationException {
		super(root);
		db = dbf.newDocumentBuilder();
		doc = db.newDocument();
	}
	
	/** {@inheritDoc} */
	@Override
	public void write(OutputStream stream) throws DOMException, PropertyListException, IOException {
		write(stream,true);
	}
	/**
	 * Convert a tree to a XML property list and write it to a stream
	 * @param stream the stream to write the property list to 
	 * @param indent whether the resulting XML file should be indented
	 * @throws DOMException when an error occurred generating the XML document
	 * @throws PropertyListException when generating the property list fails
	 * @throws IOException if an I/O error occurs
	 */
	public void write(OutputStream stream, boolean indent) throws DOMException, PropertyListException, IOException {
		Element rootNode = doc.createElement("plist");
		rootNode.setAttribute("version", VERSION);
		rootNode.appendChild(generateNode(root));

		doc.appendChild(rootNode);
		TransformerFactory transformerFactory = 
		TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result =  new StreamResult(stream);
			transformer.setOutputProperty(OutputKeys.INDENT, indent?"yes":"no");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, DOCTYPE_PUBLIC);
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, DOCTYPE_SYSTEM);
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stream.close();
	}
	
	/**
	 * Convert an NSObject to an XML element
	 * @param obj the NSObject to convert
	 * @return the XML element
	 * @throws PropertyListException when generating the property list fails
	 */
	protected Element generateNode(NSObject obj) throws PropertyListException {
		if (obj instanceof NSArray)
			return generateArray((NSArray) obj);
		else if (obj instanceof NSBoolean)
			return generateBoolean((NSBoolean) obj);
		else if (obj instanceof NSData)
			return generateData((NSData) obj);
		else if (obj instanceof NSDate)
			return generateDate((NSDate) obj);
		else if (obj instanceof NSDictionary)
			return generateDictionary((NSDictionary) obj);
		else if (obj instanceof NSInteger)
			return generateInteger((NSInteger) obj);
		else if (obj instanceof NSNumber)
			return generateReal((NSReal) obj);
		else if (obj instanceof NSString)
			return generateString((NSString) obj);
		else throw new PropertyListException("Unknown NSObjecttype; "+obj.getClass().getSimpleName());
	}
	
	/**
	 * Convert an NSArray to an XML element
	 * @param array the NSArray to convert
	 * @return the XML element
	 * @throws PropertyListException when generating the property list fails
	 */
	protected Element generateArray(NSArray array) throws DOMException, PropertyListException {
		Element root = doc.createElement("array");
		for(NSObject obj : array.list())
			root.appendChild(generateNode(obj));
		return root;
	}
	
	/**
	 * Convert an NSBoolean to an XML element
	 * @param bool the NSBoolean to convert
	 * @return the XML element
	 * @throws PropertyListException when generating the property list fails
	 */
	protected Element generateBoolean(NSBoolean bool) {
		return doc.createElement(bool.isTrue()?"true":"false");
	}
	
	/**
	 * Convert an NSData to an XML element
	 * @param data the NSData to convert
	 * @return the XML element
	 * @throws PropertyListException when generating the property list fails
	 */
	protected Element generateData(NSData data) {
		Element root = doc.createElement("data");
		root.appendChild(doc.createTextNode(Base64.encodeToString(data.getValue(), true)));
		return root;
	}
	
	/**
	 * Convert an NSDate to an XML element
	 * @param date the NSDate to convert
	 * @return the XML element
	 * @throws PropertyListException when generating the property list fails
	 */
	protected Element generateDate(NSDate date) {
		Element root = doc.createElement("date");
		root.appendChild(doc.createTextNode(NSDate.getFormatter().format(date.getValue())));
		return root;
	}
	
	/**
	 * Convert an NSDictionary to an XML element
	 * @param dictionary the NSDictionary to convert
	 * @return the XML element
	 * @throws PropertyListException when generating the property list fails
	 */
	protected Element generateDictionary(NSDictionary dictionary) throws DOMException, PropertyListException {
		Element root = doc.createElement("dict");
		for(Entry<String, NSObject> e : dictionary.entrySet()) {
			root.appendChild(generateKey(e.getKey()));
			root.appendChild(generateNode(e.getValue()));
		}
		return root;
	}
	
	/**
	 * Convert an NSReal to an XML element
	 * @param real the NSReal to convert
	 * @return the XML element
	 * @throws PropertyListException when generating the property list fails
	 */
	protected Element generateReal(NSReal real) {
		Element root = doc.createElement("real");
		root.appendChild(doc.createTextNode(real.getValue().toString()));
		return root;
	}
	
	/**
	 * Convert an NSInteger to an XML element
	 * @param integer the NSInteger to convert
	 * @return the XML element
	 * @throws PropertyListException when generating the property list fails
	 */
	protected Element generateInteger(NSInteger integer) {
		Element root = doc.createElement("integer");
		root.appendChild(doc.createTextNode(integer.getValue().toString()));
		return root;
	}
	
	/**
	 * Convert an NSString to an XML element
	 * @param string the NSString to convert
	 * @return the XML element
	 * @throws PropertyListException when generating the property list fails
	 */
	protected Element generateString(NSString string) {
		Element root = doc.createElement("string");
		root.appendChild(doc.createTextNode(string.getValue()));
		return root;
	}
	
	/**
	 * Creates a key XML element
	 * @param name the name of the key
	 * @return the XML element
	 * @throws PropertyListException when generating the property list fails
	 */
	protected Element generateKey(String name) {
		Element root = doc.createElement("key");
		root.appendChild(doc.createTextNode(name));
		return root;
	}

}
