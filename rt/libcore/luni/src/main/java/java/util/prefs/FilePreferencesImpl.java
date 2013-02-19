/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.prefs;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * The default implementation of <code>AbstractPreferences</code> for the Linux
 * platform, using the file system as its back end.
 *
 * TODO some sync mechanism with backend, Performance - check file edit date
 *
 * @since 1.4
 */
class FilePreferencesImpl extends AbstractPreferences {

    //prefs file name
    private static final String PREFS_FILE_NAME = "prefs.xml";

    //home directory for user prefs
    private static String USER_HOME = System.getProperty("user.home") + "/.java/.userPrefs";

    //home directory for system prefs
    private static String SYSTEM_HOME = System.getProperty("java.home") + "/.systemPrefs";

    //file path for this preferences node
    private String path;

    //internal cache for prefs key-value pair
    private Properties prefs;

    //file represents this preferences node
    private File prefsFile;

    //parent dir for this preferences node
    private File dir;

    //cache for removed prefs key-value pair
    private Set<String> removed = new HashSet<String>();

    //cache for updated prefs key-value pair
    private Set<String> updated = new HashSet<String>();

    /*
     * --------------------------------------------------------------
     * Constructors
     * --------------------------------------------------------------
     */

    /**
     * Construct root <code>FilePreferencesImpl</code> instance, construct
     * user root if userNode is true, system root otherwise
     */
    FilePreferencesImpl(boolean userNode) {
        super(null, "");
        this.userNode = userNode;
        path = userNode ? USER_HOME : SYSTEM_HOME;
        initPrefs();
    }

    /**
     * Construct a prefs using given parent and given name
     */
    private FilePreferencesImpl(AbstractPreferences parent, String name) {
        super(parent, name);
        path = ((FilePreferencesImpl) parent).path + File.separator + name;
        initPrefs();
    }

    private void initPrefs() {
        dir = new File(path);
        newNode = !dir.exists();
        prefsFile = new File(path + File.separator + PREFS_FILE_NAME);
        prefs = XMLParser.readXmlPreferences(prefsFile);
    }

    @Override
    protected String[] childrenNamesSpi() throws BackingStoreException {
        String[] names = dir.list(new FilenameFilter() {
            public boolean accept(File parent, String name) {
                return new File(path + File.separator + name).isDirectory();
            }
        });
        if (names == null) {// file is not a directory, exception case
            throw new BackingStoreException("Cannot get child names for " + toString()
                    + " (path is " + path + ")");
        }
        return names;
    }

    @Override
    protected AbstractPreferences childSpi(String name) {
        FilePreferencesImpl child = new FilePreferencesImpl(this, name);
        return child;
    }

    @Override
    protected void flushSpi() throws BackingStoreException {
        try {
            //if removed, return
            if(isRemoved()){
                return;
            }
            // reload
            Properties currentPrefs = XMLParser.readXmlPreferences(prefsFile);
            // merge
            Iterator<String> it = removed.iterator();
            while (it.hasNext()) {
                currentPrefs.remove(it.next());
            }
            removed.clear();
            it = updated.iterator();
            while (it.hasNext()) {
                Object key = it.next();
                currentPrefs.put(key, prefs.get(key));
            }
            updated.clear();
            // flush
            prefs = currentPrefs;
            XMLParser.writeXmlPreferences(prefsFile, prefs);
        } catch (Exception e) {
            throw new BackingStoreException(e);
        }
    }

    @Override
    protected String getSpi(String key) {
        try {
            if (prefs == null) {
                prefs = XMLParser.readXmlPreferences(prefsFile);
            }
            return prefs.getProperty(key);
        } catch (Exception e) {
            // if Exception happened, return null
            return null;
        }
    }

    @Override
    protected String[] keysSpi() throws BackingStoreException {
        final Set<Object> ks = prefs.keySet();
        return ks.toArray(new String[ks.size()]);
    }

    @Override
    protected void putSpi(String name, String value) {
        prefs.setProperty(name, value);
        updated.add(name);
    }

    @Override
    protected void removeNodeSpi() throws BackingStoreException {
        prefsFile.delete();
        boolean removeSucceed = dir.delete();
        if (!removeSucceed) {
            throw new BackingStoreException("Cannot remove " + toString());
        }
    }

    @Override
    protected void removeSpi(String key) {
        prefs.remove(key);
        updated.remove(key);
        removed.add(key);
    }

    @Override
    protected void syncSpi() throws BackingStoreException {
        flushSpi();
    }
}
