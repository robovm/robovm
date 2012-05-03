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

/**
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package org.apache.harmony.security.fortress;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * This class contains information about all registered providers and preferred
 * implementations for all "serviceName.algName".
 *
 */

public class Services {

    // The HashMap that contains information about preferred implementations for
    // all serviceName.algName in the registered providers.
    // Set the initial size to 600 so we don't grow to 1024 by default because
    // initialization adds a few entries more than the growth threshold.
    private static final Map<String, Provider.Service> services
            = new HashMap<String, Provider.Service>(600);
    // Save default SecureRandom service as well.
    // Avoids similar provider/services iteration in SecureRandom constructor
    private static Provider.Service secureRandom;

    // Need refresh flag
    private static boolean needRefresh; // = false;

    /**
     * Refresh number
     */
    static int refreshNumber = 1;

    // Registered providers
    private static final List<Provider> providers = new ArrayList<Provider>(20);

    // Hash for quick provider access by name
    private static final Map<String, Provider> providersNames = new HashMap<String, Provider>(20);
    static {
        loadProviders();
    }

    // Load statically registered providers and init Services Info
    private static void loadProviders() {
        String providerClassName = null;
        int i = 1;
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Provider p;

        while ((providerClassName = Security.getProperty("security.provider."
                + i++)) != null) {
            try {
                p = (Provider) Class
                        .forName(providerClassName.trim(), true, cl)
                        .newInstance();
                providers.add(p);
                providersNames.put(p.getName(), p);
                initServiceInfo(p);
            } catch (ClassNotFoundException e) { // ignore Exceptions
            } catch (IllegalAccessException e) {
            } catch (InstantiationException e) {
            }
        }
        Engine.door.renumProviders();
    }

    /**
     * Returns registered providers
     *
     * @return
     */
    public static Provider[] getProviders() {
        return providers.toArray(new Provider[providers.size()]);
    }

    /**
     * Returns registered providers as List
     *
     * @return
     */
    public static List<Provider> getProvidersList() {
        return new ArrayList<Provider>(providers);
    }

    /**
     * Returns the provider with the specified name
     *
     * @param name
     * @return
     */
    public static Provider getProvider(String name) {
        if (name == null) {
            return null;
        }
        return providersNames.get(name);
    }

    /**
     * Inserts a provider at a specified position
     *
     * @param provider
     * @param position
     * @return
     */
    public static int insertProviderAt(Provider provider, int position) {
        int size = providers.size();
        if ((position < 1) || (position > size)) {
            position = size + 1;
        }
        providers.add(position - 1, provider);
        providersNames.put(provider.getName(), provider);
        setNeedRefresh();
        return position;
    }

    /**
     * Removes the provider
     *
     * @param providerNumber
     */
    public static void removeProvider(int providerNumber) {
        Provider p = providers.remove(providerNumber - 1);
        providersNames.remove(p.getName());
        setNeedRefresh();
    }

    /**
     *
     * Adds information about provider services into HashMap.
     *
     * @param p
     */
    public static void initServiceInfo(Provider p) {
        for (Provider.Service serv : p.getServices()) {
            String type = serv.getType();
            if (secureRandom == null && type.equals("SecureRandom")) {
                secureRandom = serv;
            }
            String key = type + "." + serv.getAlgorithm().toUpperCase(Locale.US);
            if (!services.containsKey(key)) {
                services.put(key, serv);
            }
            for (String alias : Engine.door.getAliases(serv)) {
                key = type + "." + alias.toUpperCase(Locale.US);
                if (!services.containsKey(key)) {
                    services.put(key, serv);
                }
            }
        }
    }

    /**
     *
     * Updates services hashtable for all registered providers
     *
     */
    public static void updateServiceInfo() {
        services.clear();
        secureRandom = null;
        for (Provider p : providers) {
            initServiceInfo(p);
        }
        needRefresh = false;
    }

    /**
     * Returns true if services contain any provider information
     * @return
     */
    public static boolean isEmpty() {
        return services.isEmpty();
    }

    /**
     * Returns service description.
     * Call refresh() before.
     *
     * @param key in the format TYPE.ALGORITHM
     * @return
     */
    public static Provider.Service getService(String key) {
        return services.get(key);
    }

    /**
     * Returns the default SecureRandom service description.
     * Call refresh() before.
     */
    public static Provider.Service getSecureRandomService() {
        return secureRandom;
    }

    /**
     * Set flag needRefresh
     *
     */
    public static void setNeedRefresh() {
        needRefresh = true;
    }

    /**
     * Refresh services info
     *
     */
    public static void refresh() {
        if (needRefresh) {
            refreshNumber++;
            updateServiceInfo();
        }
    }
}
