/*
Property List Number - LGPL licensed
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

import java.math.BigInteger;

/**
 * <p>Represents a number.<br />
 * The {@link NSNumber} is either an {@link NSInteger} or a {@link NSReal}.</p>
 * @see Number
 */
public abstract class NSNumber extends NSObject {
	
	/**
	 * {@inheritDoc}
	 * @see #number()
	 */
	@Override
	public Number getValue() {
		return number();
	}
	/**
	 * Get the contents of this object as a {@link Number}.
	 * @return the {@link Number}
	 */
	public abstract Number number();

	/**
	 * Construct a {@link NSInteger} or {@link NSReal}.
	 * @param number The number.
	 */
	public static NSNumber createInstance(Number number) {
		if (number instanceof BigInteger
			|| number instanceof Byte
			|| number instanceof Integer
			|| number instanceof Long
			|| number instanceof Short)
			return new NSInteger(number.longValue());
		return new NSReal(number.doubleValue());
	}
	
}
