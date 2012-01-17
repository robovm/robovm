/*
Property List Object - LGPL licensed
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
 * <p>Every Property List contains one or more {@link NSObject}s.</p>
 * <p>Every different extension holds a certain kind of value,
 * for example the {@link NSString} holds a {@link String} and the {@link NSDate} holds a {@link java.util.Date}.</p>
 * <p>Some {@link NSObject}s can hold other {@link NSObject}s, thus making a tree.<br />
 * Those are {@link NSDictionary} and {@link NSArray}.</p>
 * 
 * <p>The value of this object can be requested through the {@link #getValue()} method.</p>
 * 
 * <p>The {@link #hashCode()}, {@link #equals(Object)} and {@link #toString()} methods are overridden to use the respective functions of the value object.</p> 
 */
public abstract class NSObject {

	NSObject() {/*not directly extendable outside this package*/}
	
	/**
	 * Get the unmodifiable value of this object.
	 * @return the value of this object
	 */
	public abstract Object getValue();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
		return result;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		NSObject other = (NSObject) obj;
		if (this.getValue() == null) {
			if (other.getValue() != null)
				return false;
		} else if (!this.getValue().equals(other.getValue()))
			return false;
		return true;
	}
	/**
	 * Get the String representation of this objects value.
	 * @return the {@link java.lang.String} representation for this object
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		Object val = getValue();
		return val == null ? "null" : val.toString();
	}

}
