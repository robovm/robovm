/*
Property List Integer number - LGPL licensed
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
 * <p>Represents an integer number between -2^63 and 2^63-1.</p>
 * 
 * <p>In this implementation, a primitive <code>long</code> is used to represent the {@link NSInteger}.</p>
 * @see Long
 */
public class NSInteger extends NSNumber {

	private final long theLong;
	
	/**
	 * Constructor.
	 * @param theLong value of the new object
	 */
	public NSInteger(long theLong) {
		this.theLong = theLong;
	}
	
	/**
	 * {@inheritDoc}
	 * @see #number()
	 */
	@Override
	public Long getValue() {
		return number();
	}
	/**
	 * Get the contents of this object as a <code>long</code>.
	 * @return the long
	 */
	public long getLong() {
		return theLong;
	}
	/** {@inheritDoc} */
	@Override
	public Long number() {
		return new Long(theLong);
	}

}
