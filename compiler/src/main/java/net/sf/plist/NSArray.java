/*
Property List Array - LGPL licensed
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
package net.sf.plist;

import java.util.Collections;
import java.util.List;

/**
 * <p>Ordered list of {@link NSObject}s.</p>
 * 
 * <p>In this implementation, a {@link List} is used to represent the {@link NSArray}.</p>
 * @see List
 */
public class NSArray extends NSObject {

	private final List<NSObject> theList;
	
	/**
	 * Constructor.
	 * @param theList the contents of new object
	 */
	public NSArray(List<NSObject> theList) {
		this.theList = Collections.unmodifiableList(theList);
	}
	
	/**
	 * Get {@link NSObject} corresponding to index from this object.
	 * @param index index of object to retrieve
	 */
	public NSObject get(int index) {
		return getValue().get(index);
	}
	/**
	 * Get an unmodifiable {@link List} containing all values of this object.
	 * @return the {@link List}
	 */
	public List<NSObject> list() {
		return theList;
	}
	/**
	 * <p>Get an array containing all values of this object.
	 * Changes made in the array will not affect this object.</p>
	 * 
	 * <p>When iterating through all items in this object,
	 * the {@link #list()} method is a better choice for performance reasons.</p> 
	 * @return the array
	 */
	public NSObject[] array() {
		return theList.toArray(new NSObject[0]);
	}
	/**
	 * {@inheritDoc}
	 * @see #list()
	 */
	@Override
	public List<NSObject> getValue() {
		return list();
	}

}
