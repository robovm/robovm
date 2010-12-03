// NewInstance.java - create a new instance of a class by name.
// http://www.saxproject.org
// Written by Edwin Goei, edwingo@apache.org
// and by David Brownell, dbrownell@users.sourceforge.net
// NO WARRANTY!  This class is in the Public Domain.
// $Id: NewInstance.java 670295 2008-06-22 01:46:43Z mrglavas $

package org.xml.sax.helpers;

/**
 * Create a new instance of a class by name.
 *
 * <blockquote>
 * <em>This module, both source code and documentation, is in the
 * Public Domain, and comes with <strong>NO WARRANTY</strong>.</em>
 * See <a href='http://www.saxproject.org'>http://www.saxproject.org</a>
 * for further information.
 * </blockquote>
 *
 * <p>This class contains a static method for creating an instance of a
 * class from an explicit class name.  It tries to use the thread's context
 * ClassLoader if possible and falls back to using
 * Class.forName(String).  It also takes into account JDK 1.2+'s
 * AccessController mechanism for performing its actions.  </p>
 *
 * <p>This code is designed to compile and run on JDK version 1.1 and later
 * including versions of Java 2.</p>
 *
 * <p>This is <strong>not</strong> the NewInstance accompanying SAX 2.0.2; it
 * represents many fixes to that code.
 * 
 * @author Edwin Goei, David Brownell
 * @version 2.0.1 (sax2r2)
 */
class NewInstance {
    
    // constants

    // governs whether, if we fail in finding a class even
    // when given a classloader, we'll make a last-ditch attempt
    // to use the current classloader.  
    private static final boolean DO_FALLBACK = true;

    /**
     * Creates a new instance of the specified class name
     *
     * Package private so this code is not exposed at the API level.
     */
    static Object newInstance (ClassLoader classLoader, String className)
        throws ClassNotFoundException, IllegalAccessException,
            InstantiationException
    {
        Class driverClass;
        if (classLoader == null) {
            // XXX Use the bootstrap ClassLoader.  There is no way to
            // load a class using the bootstrap ClassLoader that works
            // in both JDK 1.1 and Java 2.  However, this should still
            // work b/c the following should be true:
            //
            // (cl == null) iff current ClassLoader == null
            //
            // Thus Class.forName(String) will use the current
            // ClassLoader which will be the bootstrap ClassLoader.
            driverClass = Class.forName(className);
        } else {
            try {
                driverClass = classLoader.loadClass(className);
            } catch (ClassNotFoundException x) {
                if (DO_FALLBACK) {
                    // Fall back to current classloader
                    classLoader = NewInstance.class.getClassLoader();
                    if (classLoader != null) {
                        driverClass = classLoader.loadClass(className);
                    }
                    else {
                        driverClass = Class.forName(className);
                    }
                } else {
                    throw x;
                }
            }
        }
        Object instance = driverClass.newInstance();
        return instance;
    }

    /**
     * Figure out which ClassLoader to use.  For JDK 1.2 and later use
     * the context ClassLoader.
     */           
    static ClassLoader getClassLoader ()
    {
        // Figure out which ClassLoader to use for loading the provider
        // class.  If there is a Context ClassLoader then use it.
        ClassLoader cl = SecuritySupport.getContextClassLoader();
        if (cl == null) {
            // Assert: we are on JDK 1.1 or we have no Context ClassLoader
            // so use the current ClassLoader
            cl = NewInstance.class.getClassLoader();
        }
        return cl;
    }
}
