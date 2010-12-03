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

package java.security;

import java.net.URI;
import java.security.Policy;
import javax.security.auth.login.Configuration;

import org.apache.harmony.security.internal.nls.Messages;

/**
 * This class includes a URI which refers to data for a PolicySpi or
 * ConfigurationSpi implementation.
 * 
 * @since 1.6
 */
public class URIParameter implements Policy.Parameters,
		Configuration.Parameters {
	private URI uri = null;
	
	/**
	 * Constructs an instance with the URI pointing to data intended for
	 * PolicySpi or ConfigurationSpi implementation.
	 * 
	 * @param u -
	 *            the URI pointing to the data.
	 * 
	 * @throws NullPointerException -
	 *             if the URI is null.
	 */
	public URIParameter(URI u)  {
		if (u == null) {
			throw new NullPointerException(Messages.getString("security.1A4")); //$NON-NLS-1$
		}
		uri = u;
	}
	
	/**
	 * Answers the specified uri.
	 * 
	 * @return - the uri.
	 */
	public URI getURI() {
		return uri;
	}
}
