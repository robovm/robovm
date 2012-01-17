/*
Abstract Property List Writer - LGPL licensed
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
package net.sf.plist.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.plist.NSObject;
import net.sf.plist.io.domxml.DOMXMLWriter;

/**
 * Serializes a tree consisting of {@link NSObject}s to a property list.
 */
public abstract class PropertyListWriter {

	/** Consists of possible output formats */
	public enum Format {
		/** Represents the ASCII property list format */
		TXT,
		/** Represents the XML property list format */
		XML,
		/** Represents the binary property list format */
		BIN
		}
	
	/** The format used when {@link #write(NSObject, File)} is called */
	public static Format defaultFormat = Format.XML;
	
	/** The root object of the tree */
	final protected NSObject root;
	
	/**
	 * Construct a new PropertyListWriter
	 * @param root the root of the tree
	 */
	public PropertyListWriter(NSObject root) {
		this.root = root;
	}
	
	/**
	 * Write the property list to a stream
	 * @param stream the stream to write the property list to
	 * @throws PropertyListException when generating the property list fails
	 * @throws IOException when writing to the stream fails
	 */
	public abstract void write(OutputStream stream) throws PropertyListException, IOException;
	
	/**
	 * Convert a tree to a property list and write it to a stream 
	 * @param root the root of the tree 
	 * @param stream the stream to write the property list to 
	 * @param format the format to use when writing
	 * @throws PropertyListException when generating the property list fails
	 * @throws ParserConfigurationException when unable to create an XML document
	 * @throws IOException when writing to the stream fails
	 */
	public static void write(NSObject root, OutputStream stream, Format format)
		throws PropertyListException, ParserConfigurationException, IOException
	{
		switch(format) {
			case BIN:throw new UnsupportedOperationException("Binary property list format is not supported yet.");
			case XML:new DOMXMLWriter(root).write(stream);break;
			case TXT:throw new UnsupportedOperationException("Text property list format is not supported yet.");
			default:throw new NullPointerException("format");
		}
	}
	/**
	 * Convert a tree to a property list and write it to a stream
	 * @see PropertyListWriter#write(NSObject, OutputStream, Format)
	 */
	public static void write(NSObject root, File file)
		throws PropertyListException, ParserConfigurationException, IOException
	{
		write(root, file, defaultFormat);
	}
	/**
	 * Convert a tree to a property list and write it to a file 
	 * @param root the root of the tree 
	 * @param file the file to write to 
	 * @param format the format to use when writing
	 * @throws PropertyListException when generating the property list fails
	 * @throws ParserConfigurationException when unable to create an XML document
	 * @throws IOException when writing to the stream fails
	 */
	public static void write(NSObject root, File file, Format format)
		throws PropertyListException, ParserConfigurationException, IOException
	{
		write(root, new FileOutputStream(file), format);
	}

}
