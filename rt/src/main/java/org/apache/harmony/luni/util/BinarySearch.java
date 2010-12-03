/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.luni.util;


public class BinarySearch {

	/**
	 * Search the sorted characters in the string and return an exact index or
	 * -1.
	 * 
	 * @param data
	 *            the String to search
	 * @param value
	 *            the character to search for
	 * @return the matching index, or -1
	 */
	public static int binarySearch(String data, char value) {
		int low = 0, high = data.length() - 1;
		while (low <= high) {
			int mid = (low + high) >> 1;
			char target = data.charAt(mid);
			if (value == target)
				return mid;
			else if (value < target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}

	/**
	 * Search the sorted characters in the string and return the nearest index.
	 * 
	 * @param data
	 *            the String to search
	 * @param c
	 *            the character to search for
	 * @return the nearest index
	 */
	public static int binarySearchRange(String data, char c) {
		char value = 0;
		int low = 0, mid = -1, high = data.length() - 1;
		while (low <= high) {
			mid = (low + high) >> 1;
			value = data.charAt(mid);
			if (c > value)
				low = mid + 1;
			else if (c == value)
				return mid;
			else
				high = mid - 1;
		}
		return mid - (c < value ? 1 : 0);
	}
}
