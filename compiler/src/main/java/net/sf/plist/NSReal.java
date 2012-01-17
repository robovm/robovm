/*
Property List Real number - LGPL licensed
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
 * <p>Represents an real number of 64 bits in size.</p>
 * 
 * <p>In this implementation, a primitive <code>double</code> is used to represent the {@link NSReal}.</p>
 * @see Double
 */
public class NSReal extends NSNumber{

	private final double theDouble;
	
	/**
	 * Constructor.
	 * @param theDouble value of the new object
	 */
	public NSReal(double theDouble) {
		this.theDouble = theDouble;
	}
	
	/**
	 * {@inheritDoc}
	 * @see #getDouble()
	 */
	@Override
	public Double getValue() {
		return number();
	}
	/**
	 * Get the contents of this object as a <code>double</code>.
	 * @return the double
	 */
	public double getDouble() {
		return theDouble;
	}
	/** {@inheritDoc} */
	@Override
	public Double number() {
		return new Double(theDouble);
	}

}
