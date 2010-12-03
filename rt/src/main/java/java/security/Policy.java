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

import java.util.Enumeration;

import org.apache.harmony.security.fortress.DefaultPolicy;
import org.apache.harmony.security.fortress.Engine;
import org.apache.harmony.security.fortress.PolicyUtils;
import org.apache.harmony.security.internal.nls.Messages;


/**
 * {@code Policy} is the common super type of classes which represent a system
 * security policy. The {@code Policy} specifies which permissions apply to
 * which code sources.
 * <p>
 * The system policy can be changed by setting the {@code 'policy.provider'}
 * property in the file named {@code JAVA_HOME/lib/security/java.security} to
 * the fully qualified class name of the desired {@code Policy}.
 * <p>
 * Only one instance of a {@code Policy} is active at any time.
 */
public abstract class Policy {
    
    // Key to security properties, defining default policy provider.
    private static final String POLICY_PROVIDER = "policy.provider"; //$NON-NLS-1$

    // The SecurityPermission required to set custom Policy.
    private static final SecurityPermission SET_POLICY = new SecurityPermission(
            "setPolicy"); //$NON-NLS-1$

    // The SecurityPermission required to get current Policy.
    static final SecurityPermission GET_POLICY = new SecurityPermission(
            "getPolicy"); //$NON-NLS-1$

    // The policy currently in effect. 
    private static Policy activePolicy;

	// Store spi implementation service name
	private static final String POLICYSERVICE = "Policy"; //$NON-NLS-1$

	// Used to access common engine functionality
	private static Engine engine = new Engine(POLICYSERVICE);

	private String type;

	private Policy.Parameters params;

	private Provider provider;

	// Store used spi implementation
	private PolicySpi spiImpl;
	
	private static final String CREATE_POLICY = "createPolicy."; //$NON-NLS-1$

	public Policy() {
		// default constructor
	}

	private Policy(PolicySpi spi, Provider p, String t, Policy.Parameters para) {
		this.spiImpl = spi;
		this.provider = p;
		this.type = t;
		this.params = para;
	}

	private static class PolicyDelegate extends Policy {

		public PolicyDelegate(PolicySpi spi, Provider p, String t,
				Policy.Parameters para) {
			super(spi, p, t, para);
		}
	}

	/**
	 * Answers a Policy object with the specified type and the specified
	 * parameter.
	 * 
	 * Traverses the list of registered security providers, beginning with the
	 * most preferred Provider. A new Policy object encapsulating the PolicySpi
	 * implementation from the first Provider that supports the specified type
	 * is returned.
	 * 
	 * Note that the list of registered providers may be retrieved via the
	 * Security.getProviders() method.
	 * 
	 * @param type -
	 *            the specified Policy type. See Appendix A in the Java
	 *            Cryptography Architecture API Specification & Reference for a
	 *            list of standard Policy types.
	 * @param params -
	 *            parameters for the Policy, which may be null.
	 * @return the new Policy object.
	 * @throws NoSuchAlgorithmException -
	 *             if no Provider supports a PolicySpi implementation for the
	 *             specified type.
	 * @throws SecurityException -
	 *             if the caller does not have permission to get a Policy
	 *             instance for the specified type.
	 * @throws NullPointerException -
	 *             if the specified type is null.
	 * @throws IllegalArgumentException -
	 *             if the specified parameters' type are not allowed by the
	 *             PolicySpi implementation from the selected Provider.
	 * 
	 * @since 1.6
	 */
	public static Policy getInstance(String type, Policy.Parameters params)
			throws NoSuchAlgorithmException {
		checkSecurityPermission(new SecurityPermission(CREATE_POLICY + type));
		
		if (type == null) {
			throw new NullPointerException();
		}

		try {
            synchronized (engine) {
                engine.getInstance(type, params);
                return new PolicyDelegate((PolicySpi) engine.spi,
                        engine.provider, type, params);
            }

        } catch (NoSuchAlgorithmException e) {
            if (e.getCause() == null) {
                throw e;
            }
            throw new IllegalArgumentException(Messages.getString(
                    "security.1A7", params.toString())); //$NON-NLS-1$
        }
	}

	/**
	 * Answers a Policy object of the specified type.
	 * 
	 * A new Policy object encapsulating the PolicySpi implementation from the
	 * specified provider is returned. The specified provider must be registered
	 * in the provider list via the Security.getProviders() method, otherwise
	 * NoSuchProviderException will be thrown.
	 * 
	 * @param type -
	 *            the specified Policy type. So far in Java 6, only 'JavaPolicy'
	 *            supported.
	 * @param params -
	 *            the Policy.Parameter object, which may be null.
	 * @param provider -
	 *            the provider.
	 * @return the new Policy object.
	 * 
	 * @throws NoSuchProviderException -
	 *             if the specified provider is not registered in the security
	 *             provider list.
	 * @throws NoSuchAlgorithmException -
	 *             if the specified provider does not support a PolicySpi
	 *             implementation for the specified type.
	 * @throws SecurityException -
	 *             if the caller does not have permission to get a Policy
	 *             instance for the specified type.
	 * @throws NullPointerException -
	 *             if the specified type is null.
	 * @throws IllegalArgumentException -
	 *             if the specified Provider is null, or if the specified
	 *             parameters' type are not allowed by the PolicySpi
	 *             implementation from the specified Provider.
	 * 
	 * @since 1.6
	 */
	public static Policy getInstance(String type, Policy.Parameters params,
			String provider) throws NoSuchProviderException,
			NoSuchAlgorithmException {
		if ((provider == null) || provider.isEmpty()) {
			throw new IllegalArgumentException(Messages
					.getString("security.02")); //$NON-NLS-1$
		}
		checkSecurityPermission(new SecurityPermission(CREATE_POLICY + type));
		
		Provider impProvider = Security.getProvider(provider);
		if (impProvider == null) {
			throw new NoSuchProviderException(Messages.getString("security.03", //$NON-NLS-1$
					provider));
		}
		
		return getInstanceImpl(type, params, impProvider);
	}

	/**
	 * Answers a Policy object of the specified type.
	 * 
	 * A new Policy object encapsulating the PolicySpi implementation from the
	 * specified Provider object is returned. Note that the specified Provider
	 * object does not have to be registered in the provider list.
	 * 
	 * @param type -
	 *            the specified Policy type. So far in Java 6, only 'JavaPolicy'
	 *            supported.
	 * @param params -
	 *            the Policy.Parameter object, which may be null.
	 * @param provider -
	 *            the Policy service Provider.
	 * @return the new Policy object.
	 * 
	 * @throws NoSuchAlgorithmException -
	 *             if the specified Provider does not support a PolicySpi
	 *             implementation for the specified type.
	 * @throws IllegalArgumentException -
	 *             if the specified Provider is null, or if the specified
	 *             parameters' type are not allowed by the PolicySpi
	 *             implementation from the specified Provider.
	 * @throws NullPointerException -
	 *             if the specified type is null.
	 * @throws SecurityException -
	 *             if the caller does not have permission to get a Policy
	 *             instance for the specified type.
	 * @since 1.6
	 */
	public static Policy getInstance(String type, Policy.Parameters params,
			Provider provider) throws NoSuchAlgorithmException {
		if (provider == null) {
			throw new IllegalArgumentException("security.04"); //$NON-NLS-1$
		}
		checkSecurityPermission(new SecurityPermission(CREATE_POLICY + type));

		return getInstanceImpl(type, params, provider);
	}

	private static void checkSecurityPermission(SecurityPermission permission) {
		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(permission); 
		}
	}

	private static Policy getInstanceImpl(String type, Policy.Parameters params, Provider provider) throws NoSuchAlgorithmException {
		if (type == null) {
			throw new NullPointerException();
		}

		try {
            synchronized (engine) {
                engine.getInstance(type, provider, params);
                return new PolicyDelegate((PolicySpi) engine.spi, provider,
                        type, params);
            }
        } catch (NoSuchAlgorithmException e) {
            if (e.getCause() == null) {
                throw e;
            }
            throw new IllegalArgumentException(Messages.getString(
                    "security.1A7", params.toString())); //$NON-NLS-1$
        }
	}

	/**
	 * Answers Policy parameters.
	 * 
	 * This method will only answer non-null parameters if it was obtained via a
	 * call to Policy.getInstance. Otherwise this method returns null.
	 * 
	 * @return Policy parameters, or null.
	 * 
	 * @since 1.6
	 */
	public Policy.Parameters getParameters() {
		return params;
	}

	/**
	 * Answers the Provider of this Policy.
	 * 
	 * This method will only answer non-null Provider if it was obtained via a
	 * call to Policy.getInstance. Otherwise this method returns null.
	 * 
	 * @return the Provider of this Policy, or null.
	 * 
	 * @since 1.6
	 */
	public Provider getProvider() {
		return provider;
	}

	/**
	 * Answers the type of this Policy.
	 * 
	 * This method will only answer non-null type if it was obtained via a call
	 * to Policy.getInstance. Otherwise this method returns null.
	 * 
	 * @return the type of this Policy, or null.
	 * 
	 * @since 1.6
	 */
	public String getType() {
		return type;
	}

	/**
	 * A read-only empty PermissionCollection instance.
	 * 
	 * @since 1.6
	 */
	public static final PermissionCollection UNSUPPORTED_EMPTY_COLLECTION = new PermissionCollection() {

		private static final long serialVersionUID = 1L;

		@Override
		public void add(Permission permission) {
			throw new SecurityException(Messages.getString("security.1A5")); //$NON-NLS-1$
		}

		@Override
		public Enumeration<Permission> elements() {
			return new Permissions().elements();
		}

		@Override
		public boolean implies(Permission permission) {
			if (permission == null) {
				throw new NullPointerException();
			}
			return false;
		}

		@Override
		public boolean isReadOnly() {
			// always returns true since it is a read-only instance.
			// RI does not override this method.
			return true;
		}
	};

	/**
	 * A marker interface for Policy parameters.
	 * 
	 * @since 1.6
	 */
	public static interface Parameters {
		// a marker interface
	}

    /**
     * Returns a {@code PermissionCollection} describing what permissions are
     * allowed for the specified {@code CodeSource} based on the current
     * security policy.
     * <p>
     * Note that this method is not called for classes which are in the system
     * domain (i.e. system classes). System classes are always given
     * full permissions (i.e. AllPermission). This can not be changed by
     * installing a new policy.
     *
     * @param cs
     *            the {@code CodeSource} to compute the permissions for.
     * @return the permissions that are granted to the specified {@code
     *         CodeSource}.
     */
	public PermissionCollection getPermissions(CodeSource cs) {
		return spiImpl == null ? Policy.UNSUPPORTED_EMPTY_COLLECTION : spiImpl
				.engineGetPermissions(cs);
	}

    /**
     * Reloads the policy configuration for this {@code Policy} instance.
     */
	public void refresh() {
		if (spiImpl != null) {
			spiImpl.engineRefresh();			
		}
	}

    /**
     * Returns a {@code PermissionCollection} describing what permissions are
     * allowed for the specified {@code ProtectionDomain} (more specifically,
     * its {@code CodeSource}) based on the current security policy.
     * <p>
     * Note that this method is not< called for classes which are in the
     * system domain (i.e. system classes). System classes are always
     * given full permissions (i.e. AllPermission). This can not be changed by
     * installing a new policy.
     *
     * @param domain
     *            the {@code ProtectionDomain} to compute the permissions for.
     * @return the permissions that are granted to the specified {@code
     *         CodeSource}.
     */
	public PermissionCollection getPermissions(ProtectionDomain domain) {		
		Permissions permissions = new Permissions();
		if (domain != null) {
			try {
				PermissionCollection cds = getPermissions(domain
						.getCodeSource());
				if (cds != Policy.UNSUPPORTED_EMPTY_COLLECTION) {
					Enumeration<Permission> elements = cds.elements();
					while (elements.hasMoreElements()) {
						permissions.add(elements.nextElement());
					}
				}
			} catch (NullPointerException e) {
				// ignore the exception, just add nothing to the result set
			}

			PermissionCollection pds = domain.getPermissions();
			if (pds != null) {
				Enumeration<Permission> pdElements = pds.elements();
				while (pdElements.hasMoreElements()) {
					permissions.add(pdElements.nextElement());
				}
			}
		}
		return permissions;		
	}

    /**
     * Indicates whether the specified {@code Permission} is implied by the
     * {@code PermissionCollection} of the specified {@code ProtectionDomain}.
     *
     * @param domain
     *            the {@code ProtectionDomain} for which the permission should
     *            be granted.
     * @param permission
     *            the {@code Permission} for which authorization is to be
     *            verified.
     * @return {@code true} if the {@code Permission} is implied by the {@code
     *         ProtectionDomain}, {@code false} otherwise.
     */
	public boolean implies(ProtectionDomain domain, Permission permission) {
		return spiImpl == null ? defaultImplies(domain, permission) : spiImpl
				.engineImplies(domain, permission);
	}

	private boolean defaultImplies(ProtectionDomain domain, Permission permission) {
		if (domain == null && permission == null) {
			throw new NullPointerException();
		}
		boolean implies = false;
		if (domain != null) {
			PermissionCollection total = getPermissions(domain);
			PermissionCollection inherent = domain.getPermissions();
			if (inherent != null) {
				Enumeration<Permission> en = inherent.elements();
				while (en.hasMoreElements()) {
					total.add(en.nextElement());
				}
			}
			try {
				implies = total.implies(permission);
			} catch (NullPointerException e) {
				// return false instead of throwing the NullPointerException
				implies = false;
			}
		}
		return implies;
	}

    /**
     * Returns the current system security policy. If no policy has been
     * instantiated then this is done using the security property {@code
     * "policy.provider"}.
     * <p>
     * If a {@code SecurityManager} is installed, code calling this method needs
     * the {@code SecurityPermission} {@code getPolicy} to be granted, otherwise
     * a {@code SecurityException} will be thrown.
     *
     * @return the current system security policy.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
	public static Policy getPolicy() {
		checkSecurityPermission(GET_POLICY);		
		return getAccessiblePolicy();
	}

	// Reads name of default policy provider from security.properties,
	// loads the class and instantiates the provider.<br>
	// In case of any error, including undefined provider name,
	// returns new instance of org.apache.harmony.security.FilePolicy provider.
	private static Policy getDefaultProvider() {
		final String defaultClass = AccessController
				.doPrivileged(new PolicyUtils.SecurityPropertyAccessor(
						POLICY_PROVIDER));
		if (defaultClass == null) {
			// TODO log warning
			// System.err.println("No policy provider specified. Loading the "
			// + DefaultPolicy.class.getName());
			return new DefaultPolicy();
		}

        // TODO accurate classloading
        return AccessController.doPrivileged(new PrivilegedAction<Policy>() {

            public Policy run() {
                try {
                    return (Policy) Class.forName(defaultClass, true,
                            ClassLoader.getSystemClassLoader()).newInstance();
                }
                catch (Exception e) {
                    //TODO log error 
                    //System.err.println("Error loading policy provider <" 
                    //                 + defaultClass + "> : " + e 
                    //                 + "\nSwitching to the default " 
                    //                 + DefaultPolicy.class.getName());
                    return new DefaultPolicy();
                }
            }
        });

    }
    
    /**
     * Returns {@code true} if system policy provider is instantiated.
     */
    static boolean isSet() {
        return activePolicy != null;
    }

    /**
     * Shortcut accessor for friendly classes, to skip security checks.
     * If active policy was set to <code>null</code>, loads default provider, 
     * so this method never returns <code>null</code>. <br>
     * This method is synchronized with setPolicy()
     */
	static synchronized Policy getAccessiblePolicy() {
        if (activePolicy == null) {
            activePolicy = getDefaultProvider();
        }
        return activePolicy;
    }

    /**
     * Sets the system wide policy.
     * <p>
     * If a {@code SecurityManager} is installed, code calling this method needs
     * the {@code SecurityPermission} {@code setPolicy} to be granted, otherwise
     * a {@code SecurityException} will be thrown.
     *
     * @param policy
     *            the {@code Policy} to set.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
	public static void setPolicy(Policy policy) {
		checkSecurityPermission(SET_POLICY);		
		synchronized (Policy.class) {
			activePolicy = policy;
		}
	}
}
