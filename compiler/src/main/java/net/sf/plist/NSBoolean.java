/*
Property List Boolean - LGPL licensed
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

/**
 * <p>Represents a value of either {@link Boolean#TRUE} or {@link Boolean#FALSE}.</p>
 * 
 * <p>In this implementation, a primitive <code>boolean</code> is used to represent the {@link NSBoolean}.</p>
 * @see Boolean
 */
public class NSBoolean extends NSObject {

	/** Represents {@link Boolean#TRUE} */
	public static final NSBoolean TRUE = new NSBoolean(true);
	/** Represents {@link Boolean#FALSE} */
	public static final NSBoolean FALSE = new NSBoolean(false);
	
	private final boolean theBoolean;
	
	/**
	 * Constructor.
	 * @param theBoolean value of the new object
	 */
	public NSBoolean(boolean theBoolean) {
		this.theBoolean = theBoolean;
	}
	
	/**
	 * {@inheritDoc}
	 * @see #bool()
	 * @see net.sf.plist.NSBoolean#bool()
	 */
	@Override
	public Boolean getValue() {
		return bool() ? Boolean.TRUE : Boolean.FALSE;
	}
	/** @see net.sf.plist.NSBoolean#bool() */
	public boolean isTrue() {
		return bool();
	}
	/**
	 * Get the boolean represented by this object.
	 * @return the boolean
	 */
	public boolean bool() {
		return theBoolean;
	}

}
