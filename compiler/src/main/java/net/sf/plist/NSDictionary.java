/*
Property List Dictionary - LGPL licensed
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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * <p>A key/value store,
 * with {@link String} as keys and {@link NSObject} as values.<br />
 * Usually, the root node of a Property List is a {@link NSDictionary}.</p> 
 * 
 * <p>In this implementation, a {@link Map} is used to represent the {@link NSDictionary}.</p>
 * @see Map
 */
public class NSDictionary extends NSObject {

	private final Map<String,NSObject> theDictionary;
	
	/**
	 * Constructor .
	 * @param theMap value of the new object
	 */
	public NSDictionary(Map<String,NSObject> theMap) {
		this.theDictionary = Collections.unmodifiableMap(theMap);
	}
	/**
	 * Get the {@link NSObject} associated with <code>key</code>.
	 * @param key The key to retrieve
	 * @return The {@link NSObject} associated with <code>key</code>
	 * @see Map#get(Object)
	 */
	public NSObject get(String key) {
		return map().get(key);
	}
	/**
	 * Get the entryset for this {@link NSObject}.
	 * @return the entryset
	 * @see Map#entrySet()
	 */
	public Set<Entry<String, NSObject>> entrySet() {
		return map().entrySet();
	}
	
	/**
	 * {@inheritDoc}
	 * @see #map()
	 */
	@Override
	public Map<String,NSObject> getValue() {
		return map();
	}
	/**
	 * Get the contents of this object as a {@link Map}.
	 * @return the {@link Map}
	 * @see Map
	 */
	public Map<String, NSObject> map() {
		return theDictionary;
	}

}
