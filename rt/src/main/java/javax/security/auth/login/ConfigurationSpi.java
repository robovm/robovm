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

package javax.security.auth.login;


/**
 * Represents the Service Provider Interface (SPI) for 
 * javax.security.auth.login.Configuration class.
 * 
 * If there is any class that wants to provide a Configuration implementation, all
 * abstract methods in this SPI should be implemented.
 * 
 * The detailed implementations should offer a public constructor, in which a
 * Configuration.Paramters implementation acts as an input parameter. If the
 * Configuration.Paramters input cannot by understood by the constructor, an
 * IllegalArgumentException will be thrown.
 * 
 * @since 1.6
 */
public abstract class ConfigurationSpi {

	public ConfigurationSpi() {
	    //default constructor
	}
    
	/**
     * Get AppConfigurationEntries form the specified name
     * @param name
     *      The name to get the configuration
     * @return
     *      an array of AppConfigurationEntries
	 */
	protected abstract AppConfigurationEntry[] engineGetAppConfigurationEntry(
			String name);

    /**
     * Refreshes/reloads the configuration. The behavior of this method
     * depends on the implementation. For example, calling refresh on a
     * file-based configuration will cause the file to be re-read.
     * 
     * The default implementation of this method does nothing. This method
     * should be overridden if a refresh operation is supported by the 
     * implementation.
     * 
     */
	protected void engineRefresh() {
	    //do nothing in default implementation
	}
}
