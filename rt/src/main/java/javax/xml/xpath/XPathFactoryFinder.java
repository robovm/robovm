/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
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
// $Id: XPathFactoryFinder.java 670432 2008-06-23 02:02:08Z mrglavas $

package javax.xml.xpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;

import javax.xml.validation.SchemaFactory;

/**
 * Implementation of {@link XPathFactory#newInstance(String)}.
 * 
 * @author <a href="Kohsuke.Kawaguchi@Sun.com">Kohsuke Kawaguchi</a>
 * @version $Revision: 670432 $, $Date: 2008-06-22 22:02:08 -0400 (Sun, 22 Jun 2008) $
 * @since 1.5
 */
final class XPathFactoryFinder {
    
    /** debug support code. */
    private static boolean debug = false;
    
    /**
     * Default columns per line.
     */
    private static final int DEFAULT_LINE_LENGTH = 80;
    
    static {
        // Use try/catch block to support applets
        try {
            String val = SecuritySupport.getSystemProperty("jaxp.debug");
            // Allow simply setting the prop to turn on debug
            debug = val != null && (! "false".equals(val));
        } catch (Exception _) {
            debug = false;
        }
    }

    /**
     * <p>Cache properties for performance.</p>
     */
	private static Properties cacheProps = new Properties();
    
	/**
	 * <p>First time requires initialization overhead.</p>
	 */
	private static boolean firstTime = true;
    
    /**
     * <p>Conditional debug printing.</p>
     * 
     * @param msg to print
     */
    private static void debugPrintln(String msg) {
        if (debug) {
            System.err.println("JAXP: " + msg);
        }
    }
    
    /**
     * <p><code>ClassLoader</code> to use to find <code>SchemaFactory</code>.</p>
     */
    private final ClassLoader classLoader;
    
    /**
     * <p>Constructor that specifies <code>ClassLoader</code> to use
     * to find <code>SchemaFactory</code>.</p>
     * 
     * @param loader
     *      to be used to load resource, {@link SchemaFactory}, and
     *      {@link SchemaFactoryLoader} implementations during
     *      the resolution process.
     *      If this parameter is null, the default system class loader
     *      will be used.
     */
    public XPathFactoryFinder(ClassLoader loader) {
        this.classLoader = loader;
        if( debug ) {
            debugDisplayClassLoader();
        }
    }
    
    private void debugDisplayClassLoader() {
        try {
            if( classLoader == SecuritySupport.getContextClassLoader() ) {
                debugPrintln("using thread context class loader ("+classLoader+") for search");
                return;
            }
        }
        // The VM ran out of memory or there was some other serious problem. Re-throw.
        catch (VirtualMachineError vme) {
            throw vme;
        }
        // ThreadDeath should always be re-thrown
        catch (ThreadDeath td) {
            throw td;
        }
        catch (Throwable _) {
            ; // getContextClassLoader() undefined in JDK1.1 
        }
        
        if( classLoader==ClassLoader.getSystemClassLoader() ) {
            debugPrintln("using system class loader ("+classLoader+") for search");
            return;
        }

        debugPrintln("using class loader ("+classLoader+") for search");
    }
    
    /**
     * <p>Creates a new {@link XPathFactory} object for the specified
     * schema language.</p>
     * 
     * @param uri
     *       Identifies the underlying object model.
     * 
     * @return <code>null</code> if the callee fails to create one.
     * 
     * @throws NullPointerException
     *      If the parameter is null.
     */
    public XPathFactory newFactory(String uri) {
        if(uri==null)        throw new NullPointerException();
        XPathFactory f = _newFactory(uri);
        if (debug) {
            if (f != null) {
                debugPrintln("factory '" + f.getClass().getName() + "' was found for " + uri);
            } else {
                debugPrintln("unable to find a factory for " + uri);
            }
        }
        return f;
    }
    
    /**
     * <p>Lookup a {@link XPathFactory} for the given object model.</p>
     * 
     * @param uri identifies the object model.
     *  
     * @return {@link XPathFactory} for the given object model.
     */
    private XPathFactory _newFactory(String uri) {
        XPathFactory xpf;
        
        String propertyName = SERVICE_CLASS.getName() + ":" + uri;
        
        // system property look up
        try {
            if (debug) debugPrintln("Looking up system property '"+propertyName+"'" );
            String r = SecuritySupport.getSystemProperty(propertyName);
            if (r != null && r.length() > 0) {
                if (debug) debugPrintln("The value is '"+r+"'");
                xpf = createInstance(r);
                if(xpf!=null)    return xpf;
            } 
            else if (debug) {
                debugPrintln("The property is undefined.");
            }
        }
        // The VM ran out of memory or there was some other serious problem. Re-throw.
        catch (VirtualMachineError vme) {
            throw vme;
        }
        // ThreadDeath should always be re-thrown
        catch (ThreadDeath td) {
            throw td;
        }
        catch (Throwable t) {
            if( debug ) {
                debugPrintln("failed to look up system property '"+propertyName+"'" );
                t.printStackTrace();
            }
        }
        
        String javah = SecuritySupport.getSystemProperty( "java.home" );
        String configFile = javah + File.separator +
        "lib" + File.separator + "jaxp.properties";

        String factoryClassName = null ;

        // try to read from $java.home/lib/jaxp.properties
        try {
            if(firstTime){
                synchronized(cacheProps){
                    if(firstTime){
                        File f=new File( configFile );
                        firstTime = false;
                        if(SecuritySupport.doesFileExist(f)){
                            if (debug) debugPrintln("Read properties file " + f);                                
                            cacheProps.load(SecuritySupport.getFileInputStream(f));
                        }
                    }
                }
            }
            factoryClassName = cacheProps.getProperty(propertyName);            
            if (debug) debugPrintln("found " + factoryClassName + " in $java.home/jaxp.properties"); 

            if (factoryClassName != null) {
                xpf = createInstance(factoryClassName);
                if(xpf != null){
                    return xpf;
                }
            }
        } catch (Exception ex) {
            if (debug) {
                ex.printStackTrace();
            } 
        }
                    
        // try META-INF/services files
        Iterator sitr = createServiceFileIterator();
        while(sitr.hasNext()) {
            URL resource = (URL)sitr.next();
            if (debug) debugPrintln("looking into " + resource);
            try {
                xpf = loadFromServicesFile(uri, resource.toExternalForm(), SecuritySupport.getURLInputStream(resource));
                if(xpf!=null)    return xpf;
            } catch(IOException e) {
                if( debug ) {
                    debugPrintln("failed to read "+resource);
                    e.printStackTrace();
                }
            }
        }
        
        // platform default
        if(uri.equals(XPathFactory.DEFAULT_OBJECT_MODEL_URI)) {
            if (debug) debugPrintln("attempting to use the platform default W3C DOM XPath lib");
            return createInstance("org.apache.xpath.jaxp.XPathFactoryImpl");
        }
        
        if (debug) debugPrintln("all things were tried, but none was found. bailing out.");
        return null;
    }
    
    /**
     * <p>Creates an instance of the specified and returns it.</p>
     * 
     * @param className
     *      fully qualified class name to be instanciated.
     * 
     * @return null
     *      if it fails. Error messages will be printed by this method. 
     */
    XPathFactory createInstance( String className ) {
        try {
            if (debug) debugPrintln("instanciating "+className);
            Class clazz;
            if( classLoader!=null )
                clazz = classLoader.loadClass(className);
            else
                clazz = Class.forName(className);
            if(debug)       debugPrintln("loaded it from "+which(clazz));
            Object o = clazz.newInstance();
            
            if( o instanceof XPathFactory )
                return (XPathFactory)o;
            
            if (debug) debugPrintln(className+" is not assignable to "+SERVICE_CLASS.getName());
        }
        // The VM ran out of memory or there was some other serious problem. Re-throw.
        catch (VirtualMachineError vme) {
            throw vme;
        }
        // ThreadDeath should always be re-thrown
        catch (ThreadDeath td) {
            throw td;
        }
        catch (Throwable t) {
            if (debug) {
                debugPrintln("failed to instanciate "+className);
                t.printStackTrace();
            }
        }
        return null;
    }
    
    /** Iterator that lazily computes one value and returns it. */
    private static abstract class SingleIterator implements Iterator {
        private boolean seen = false;
        
        public final void remove() { throw new UnsupportedOperationException(); }
        public final boolean hasNext() { return !seen; }
        public final Object next() {
            if(seen)    throw new NoSuchElementException();
            seen = true;
            return value();
        }
        
        protected abstract Object value();
    }
    
    /** Searches for a XPathFactory for a given uri in a META-INF/services file. */
    private XPathFactory loadFromServicesFile(String uri, String resourceName, InputStream in) {

        if (debug) debugPrintln("Reading " + resourceName );
        
        BufferedReader rd;
        try {
            rd = new BufferedReader(new InputStreamReader(in, "UTF-8"), DEFAULT_LINE_LENGTH);
        } catch (java.io.UnsupportedEncodingException e) {
            rd = new BufferedReader(new InputStreamReader(in), DEFAULT_LINE_LENGTH);
        }
        
        String factoryClassName = null;
        XPathFactory resultFactory = null;
        // See spec for provider-configuration files: http://java.sun.com/j2se/1.5.0/docs/guide/jar/jar.html#Provider%20Configuration%20File
        while (true) {
            try {
                factoryClassName = rd.readLine();   
            } catch (IOException x) {
                // No provider found
                break;
            }
            if (factoryClassName != null) {
                // Ignore comments in the provider-configuration file
                int hashIndex = factoryClassName.indexOf('#');
                if (hashIndex != -1) {
                    factoryClassName = factoryClassName.substring(0, hashIndex);
                }
                
                // Ignore leading and trailing whitespace
                factoryClassName = factoryClassName.trim();
                
                // If there's no text left or if this was a blank line, go to the next one.
                if (factoryClassName.length() == 0) {
                    continue;
                }
                
                try {
                    // Found the right XPathFactory if its isObjectModelSupported(String uri) method returns true.
                    XPathFactory foundFactory = (XPathFactory) createInstance(factoryClassName);
                    if (foundFactory.isObjectModelSupported(uri)) {
                        resultFactory = foundFactory;
                        break;
                    }
                }
                catch (Exception e) {}
            }
            else {
                break;
            }
        }
        
        try {
            // try to close the reader.
            rd.close();
        }
        // Ignore the exception.
        catch (IOException exc) {}
        
        return resultFactory;
    }
        
    /**
     * Returns an {@link Iterator} that enumerates all 
     * the META-INF/services files that we care.
     */
    private Iterator createServiceFileIterator() {
        if (classLoader == null) {
            return new SingleIterator() {
                protected Object value() {
                    ClassLoader classLoader = XPathFactoryFinder.class.getClassLoader();
                    return SecuritySupport.getResourceAsURL(classLoader, SERVICE_ID);
                    //return (ClassLoader.getSystemResource( SERVICE_ID ));
                }
            };
        } else {
            try {
                //final Enumeration e = classLoader.getResources(SERVICE_ID);
                final Enumeration e = SecuritySupport.getResources(classLoader, SERVICE_ID);
                if (debug && !e.hasMoreElements()) {
                    debugPrintln("no "+SERVICE_ID+" file was found");
                }
                
                // wrap it into an Iterator.
                return new Iterator() {
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                    public boolean hasNext() {
                        return e.hasMoreElements();
                    }

                    public Object next() {
                        return e.nextElement();
                    }
                };
            } catch (IOException e) {
                if (debug) {
                    debugPrintln("failed to enumerate resources "+SERVICE_ID);
                    e.printStackTrace();
                }
                return new ArrayList().iterator();  // empty iterator
            }
        }
    }
    
    private static final Class SERVICE_CLASS = XPathFactory.class;
    private static final String SERVICE_ID = "META-INF/services/" + SERVICE_CLASS.getName();
    
    
    
    private static String which( Class clazz ) {
        return which( clazz.getName(), clazz.getClassLoader() );
    }

    /**
     * <p>Search the specified classloader for the given classname.</p>
     *
     * @param classname the fully qualified name of the class to search for
     * @param loader the classloader to search
     * 
     * @return the source location of the resource, or null if it wasn't found
     */
    private static String which(String classname, ClassLoader loader) {

        String classnameAsResource = classname.replace('.', '/') + ".class";
        
        if( loader==null )  loader = ClassLoader.getSystemClassLoader();
        
        //URL it = loader.getResource(classnameAsResource);
        URL it = SecuritySupport.getResourceAsURL(loader, classnameAsResource);
        if (it != null) {
            return it.toString();
        } else {
            return null;
        }
    }
}
