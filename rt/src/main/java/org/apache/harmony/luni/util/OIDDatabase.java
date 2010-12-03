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


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class OIDDatabase {

	private static OIDDatabase instance = new OIDDatabase();

	private Set<DBEntry> oids = new HashSet<DBEntry>();

	private Set<DBEntry> algorithms = new HashSet<DBEntry>();

	/**
	 * Private constructor to enforce singleton pattern
	 */
	private OIDDatabase() {
		// First, encryption algorithms...

		// MD2withRSA
		DBEntry oid = new DBEntry("1.2.840.113549.1.1.2");
		DBEntry alg = new DBEntry("MD2withRSA");
		wireTogether(oid, alg);

		// MD5withRSA
		oid = new DBEntry("1.2.840.113549.1.1.4");
		alg = new DBEntry("MD5withRSA");
		wireTogether(oid, alg);

		// SHA1withRSA
		oid = new DBEntry("1.2.840.113549.1.1.5");
		alg = new DBEntry("SHA1withRSA");
		wireTogether(oid, alg);

		// SHA1withDSA
		oid = new DBEntry("1.2.840.10040.4.3");
		alg = new DBEntry("SHA1withDSA");
		wireTogether(oid, alg);

		// message digest algorithms

		// SHA and SHA-1
		oid = new DBEntry("1.3.14.3.2.26");
		alg = new DBEntry("SHA");
		DBEntry alg2 = new DBEntry("SHA-1");
		wireTogether(oid, alg);
		wireTogether(oid, alg2);

		// MD5
		oid = new DBEntry("1.2.840.113549.2.5");
		alg = new DBEntry("MD5");
		wireTogether(oid, alg);

		// key factories

		// RSA
		oid = new DBEntry("1.2.840.113549.1.1.1");
		alg = new DBEntry("RSA");
		wireTogether(oid, alg);

		// DSA
		oid = new DBEntry("1.2.840.10040.4.1");
		DBEntry oid2 = new DBEntry("1.3.14.3.2.12");
		alg = new DBEntry("DSA");
		wireTogether(oid, alg);
		wireTogether(oid2, alg);

		// DiffieHellman
		oid = new DBEntry("1.2.840.10046.2.1");
		alg = new DBEntry("DiffieHellman");
		wireTogether(oid, alg);
	}

	private void wireTogether(DBEntry oidValue, DBEntry algorithmValue) {
		oids.add(oidValue);
		algorithms.add(algorithmValue);
		oidValue.addEquivalent(algorithmValue);
		algorithmValue.addEquivalent(oidValue);
	}

	public static OIDDatabase getInstance() {
		return instance;
	}

	public String getFirstAlgorithmForOID(String oid) {
		String result = null;
		Iterator<String> it = this.getAllAlgorithmsForOID(oid).iterator();
		if (it.hasNext()) {
			result = (it.next());
		}
		return result;
	}

	public Set<String> getAllAlgorithmsForOID(String oid) {
		Set<String> result = null;
		Iterator<DBEntry> it = this.oids.iterator();
		result = getAllEquivalents(oid, it);
		if (result == null) {
			throw new IllegalArgumentException("Unknown OID : " + oid);
		}
		return result;
	}

	public String getFirstOIDForAlgorithm(String algorithm) {
		String result = null;
		Iterator<String> it = this.getAllOIDsForAlgorithm(algorithm).iterator();
		if (it.hasNext()) {
			result = (it.next());
		}
		return result;
	}

	public Set<String> getAllOIDsForAlgorithm(String algorithm) {
		Set<String> result = null;
		Iterator<DBEntry> it = this.algorithms.iterator();
		result = getAllEquivalents(algorithm, it);
		if (result == null) {
			throw new IllegalArgumentException("Unsupported algorithm : "
					+ algorithm);
		}
		return result;
	}

	private Set<String> getAllEquivalents(String value, Iterator<DBEntry> it) {
		Set<String> result = null;
		while (it.hasNext()) {
			DBEntry element = it.next();
			if (element.getValue().equals(value)) {
				Set<DBEntry> allMatchingDBEntries = element.getAllEquivalents();
				result = new HashSet<String>();
				Iterator<DBEntry> dbIt = allMatchingDBEntries.iterator();
				while (dbIt.hasNext()) {
					DBEntry matchingEntry = dbIt.next();
					result.add(matchingEntry.getValue());
				}
			}
		}
		return result;
	}

	static class DBEntry {
		private final List<DBEntry> equivalents = new LinkedList<DBEntry>();

		private final String value;

		DBEntry(String value) {
			this.value = value;
		}

		void addEquivalent(DBEntry entry) {
			this.equivalents.add(entry);
		}

		String getValue() {
			return this.value;
		}

		Set<DBEntry> getAllEquivalents() {
			return new HashSet<DBEntry>(this.equivalents);
		}
	}
}
