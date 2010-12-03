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

import java.security.AccessController;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;

import javax.security.auth.AuthPermission;

import org.apache.harmony.security.fortress.PolicyUtils;

public abstract class Configuration {

	// the current configuration 
	private static Configuration configuration;

	// creates a AuthPermission object with a specify property
	private static final AuthPermission GET_LOGIN_CONFIGURATION = new AuthPermission(
			"getLoginConfiguration"); //$NON-NLS-1$

	// creates a AuthPermission object with a specify property
	private static final AuthPermission SET_LOGIN_CONFIGURATION = new AuthPermission(
			"setLoginConfiguration"); //$NON-NLS-1$

	// Key to security properties, defining default configuration provider.
	private static final String LOGIN_CONFIGURATION_PROVIDER = "login.configuration.provider"; //$NON-NLS-1$

	/**
	 * A marker interface for Configuration parameters.
	 * 
	 * @since 1.6
	 */
	public static interface Parameters {
		// a marker interface    	
	}

	protected Configuration() {
		super();
	}

	public static Configuration getConfiguration() {
		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(GET_LOGIN_CONFIGURATION);
		}
		return getAccessibleConfiguration();
	}

	/**
	 * Reads name of default configuration provider from security.properties,
	 * loads the class and instantiates the provider.<br> In case of any
	 * exception, wraps it with SecurityException and throws further.
	 */
	private static final Configuration getDefaultProvider() {
		return AccessController
				.doPrivileged(new PolicyUtils.ProviderLoader<Configuration>(
						LOGIN_CONFIGURATION_PROVIDER, Configuration.class));
	}

	/**
	 * Shortcut accessor for friendly classes, to skip security checks.
	 * If active configuration was set to <code>null</code>, tries to load a default 
	 * provider, so this method never returns <code>null</code>. <br>
	 * This method is synchronized with setConfiguration()
	 */
	static Configuration getAccessibleConfiguration() {
		Configuration current = configuration;
		if (current == null) {
			synchronized (Configuration.class) {
				if (configuration == null) {
					configuration = getDefaultProvider();
				}
				return configuration;
			}
		}
		return current;
	}

	public static void setConfiguration(Configuration configuration) {
		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(SET_LOGIN_CONFIGURATION);
		}
		Configuration.configuration = configuration;
	}

	public abstract AppConfigurationEntry[] getAppConfigurationEntry(
			String applicationName);

    /**
     * refresh and reload the configuration
     * @throws SecurityException
     *      if does not have the permission to refresh the Configuration
     */
	public void refresh() {
        return;
    }
    
    /**
     * return the type of this Configuration
     * @return
     *      the type the type of this Configuration
     */
    public String getType(){
        return null;
    }
    
    /**
     * return the provider of this Configuration
     * @return
     *      the type the provider of this Configuration
     */
    public Provider getProvider(){
        return null;
    }
    
    /**
     * return the parameter of this Configuration
     * @return
     *      the type the parameter of this Configuration
     */
    public Parameters getParameters(){
        return null;
    }
        
    private static class InnerConfiguration extends Configuration {
        private ConfigurationSpi configurationSpi;

        private Provider provider;

        private String type;

        private Configuration.Parameters parameters;

        public InnerConfiguration(ConfigurationSpi configurationSpi,
                Provider provider, String type,
                Configuration.Parameters parameters) {
            this.configurationSpi = configurationSpi;
            this.provider = provider;
            this.type = type;
            this.parameters = parameters;
        }

        @Override
        public Configuration.Parameters getParameters() {
            return parameters;
        }

        @Override
        public Provider getProvider() {
            return provider;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public AppConfigurationEntry[] getAppConfigurationEntry(String applicationName) {
            return configurationSpi.engineGetAppConfigurationEntry(applicationName);
        }

        @Override
        public void refresh() {
            configurationSpi.engineRefresh(); 
        }
    }
    
    /**
     * Answers a Configuration object with the specified type and the specified
     * parameter.
     * 
     * A new Configuration object encapsulating the ConfigurationSpi
     * implementation from the first Provider that supports the specified type
     * is returned.
     * 
     * @param type
     *      the specified Configuration type.
     * @param params
     *      parameters for the Configuration, which may be null.
     * @param provider
     *      provider which will provide Configuration
     * @return the new Configuration object.
     * @throws NoSuchAlgorithmException
     *      if no Provider supports a ConfigurationSpi implementation for the
     *      specified type.
     * @throws SecurityException
     *      if the caller does not have permission to get a Configuration
     *      instance for the specified type.
     * @throws NullPointerException
     *      if the specified type is null.
     * @throws IllegalArgumentException
     *      if the specified parameters' type are not allowed by the
     *      ConfigurationSpi implementation from the selected Provider.
     * 
     * @since 1.6
     */
    public static Configuration getInstance(String type,
            Configuration.Parameters params, Provider provider)
            throws NoSuchAlgorithmException {
        if (type == null) {
            throw new NullPointerException();
        }
        if(provider == null){
            throw new IllegalArgumentException();
        }
        Provider.Service service = provider.getService("Configuration", type);
        if(service == null){
            throw new NoSuchAlgorithmException(type);
        }
        ConfigurationSpi configurationSpi;
        configurationSpi = (ConfigurationSpi) service.newInstance(params);
        return new InnerConfiguration(configurationSpi, provider, type, params);
    }
    
    /**
     * Answers a Configuration object with the specified type and the specified
     * parameter.
     * 
     * A new Configuration object encapsulating the ConfigurationSpi
     * implementation from the first Provider that supports the specified type
     * is returned.
     * 
     * @param type
     *      the specified Configuration type.
     * @param params
     *      parameters for the Configuration, which may be null.
     * @param provider
     *      provider which will provide Configuration
     * @return the new Configuration object.
     * @throws NoSuchAlgorithmException
     *      if no Provider supports a ConfigurationSpi implementation for the
     *      specified type.
     * @throws SecurityException
     *      if the caller does not have permission to get a Configuration
     *      instance for the specified type.
     * @throws NullPointerException
     *      if the specified type is null.
     * @throws IllegalArgumentException
     *      if the specified parameters' type are not allowed by the
     *      ConfigurationSpi implementation from the selected Provider.
     * @throws NoSuchProviderException
     *      if the specified provider is not registered in the provider list
     * 
     * @since 1.6
     */
    public static Configuration getInstance(String type, Configuration.Parameters params, String provider) throws NoSuchAlgorithmException, NoSuchProviderException{
        Provider pro = Security.getProvider(provider);
        if(provider == null || provider.length() == 0){
            throw new IllegalArgumentException();
        }
        if(pro == null){
            throw new NoSuchProviderException();
        }
        return getInstance(type, params, pro);
    }
    
    /**
     * Answers a Configuration object with the specified type and the specified
     * parameter.
     * 
     * Traverses the list of registered security providers, beginning with the
     * most preferred Provider. A new Configuration object encapsulating the ConfigurationSpi
     * implementation from the first Provider that supports the specified type
     * is returned.
     * 
     * Note that the list of registered providers may be retrieved via the
     * Security.getProviders() method.
     * 
     * @param type
     *      the specified Configuration type.
     * @param params
     *      parameters for the Configuration, which may be null.
     * @return the new Configuration object.
     * @throws NoSuchAlgorithmException
     *      if no Provider supports a ConfigurationSpi implementation for the
     *      specified type.
     * @throws SecurityException
     *      if the caller does not have permission to get a Configuration
     *      instance for the specified type.
     * @throws NullPointerException
     *      if the specified type is null.
     * @throws IllegalArgumentException
     *      if the specified parameters' type are not allowed by the
     *      ConfigurationSpi implementation from the selected Provider.
     * 
     * @since 1.6
     */
    public static Configuration getInstance(String type, Configuration.Parameters params) throws NoSuchAlgorithmException{
        Configuration configuration = null;
        for(Provider provider: Security.getProviders()){
            try
            {
                configuration = getInstance(type, params, provider);
                break;
            }
            catch(NoSuchAlgorithmException e){
                
            }
        }
        if(configuration == null){
            throw new NoSuchAlgorithmException();
        }
        return configuration;
    }
}
