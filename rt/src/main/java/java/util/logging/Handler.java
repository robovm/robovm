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

package java.util.logging;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import org.apache.harmony.logging.internal.nls.Messages;

/**
 * A {@code Handler} object accepts a logging request and exports the desired
 * messages to a target, for example, a file, the console, etc. It can be
 * disabled by setting its logging level to {@code Level.OFF}.
 */
public abstract class Handler {

    private static final Level DEFAULT_LEVEL = Level.ALL;

    // the error manager to report errors during logging
    private ErrorManager errorMan;

    // the character encoding used by this handler
    private String encoding;

    // the logging level
    private Level level;

    // the formatter used to export messages
    private Formatter formatter;

    // the filter used to filter undesired messages
    private Filter filter;

    // class name, used for property reading
    private String prefix;

    /**
     * Constructs a {@code Handler} object with a default error manager instance
     * {@code ErrorManager}, the default encoding, and the default logging
     * level {@code Level.ALL}. It has no filter and no formatter.
     */
    protected Handler() {
        this.errorMan = new ErrorManager();
        this.level = DEFAULT_LEVEL;
        this.encoding = null;
        this.filter = null;
        this.formatter = null;
        this.prefix = this.getClass().getName();
    }

    // get a instance from given class name, using Class.forName()
    private Object getDefaultInstance(String className) {
        Object result = null;
        if (null == className) {
            return result;
        }
        try {
            result = Class.forName(className).newInstance();
        } catch (Exception e) {
            // ignore
        }
        return result;
    }

    // get a instance from given class name, using context classloader
    private Object getCustomizeInstance(final String className)
            throws Exception {
        Class<?> c = AccessController
                .doPrivileged(new PrivilegedExceptionAction<Class<?>>() {
                    public Class<?> run() throws Exception {
                        ClassLoader loader = Thread.currentThread()
                                .getContextClassLoader();
                        if (null == loader) {
                            loader = ClassLoader.getSystemClassLoader();
                        }
                        return loader.loadClass(className);
                    }
                });
        return c.newInstance();
    }

    // print error message in some format
    void printInvalidPropMessage(String key, String value, Exception e) {
        // logging.12=Invalid property value for
        String msg = new StringBuilder().append(
                Messages.getString("logging.12")) //$NON-NLS-1$
                .append(prefix).append(":").append(key).append("/").append( //$NON-NLS-1$//$NON-NLS-2$
                        value).toString();
        errorMan.error(msg, e, ErrorManager.GENERIC_FAILURE);
    }

    /**
     * init the common properties, including filter, level, formatter, and
     * encoding
     */
    void initProperties(String defaultLevel, String defaultFilter,
            String defaultFormatter, String defaultEncoding) {
        LogManager manager = LogManager.getLogManager();

        // set filter
        final String filterName = manager.getProperty(prefix + ".filter"); //$NON-NLS-1$
        if (null != filterName) {
            try {
                filter = (Filter) getCustomizeInstance(filterName);
            } catch (Exception e1) {
                printInvalidPropMessage("filter", filterName, e1); //$NON-NLS-1$
                filter = (Filter) getDefaultInstance(defaultFilter);
            }
        } else {
            filter = (Filter) getDefaultInstance(defaultFilter);
        }

        // set level
        String levelName = manager.getProperty(prefix + ".level"); //$NON-NLS-1$
        if (null != levelName) {
            try {
                level = Level.parse(levelName);
            } catch (Exception e) {
                printInvalidPropMessage("level", levelName, e); //$NON-NLS-1$
                level = Level.parse(defaultLevel);
            }
        } else {
            level = Level.parse(defaultLevel);
        }

        // set formatter
        final String formatterName = manager.getProperty(prefix + ".formatter"); //$NON-NLS-1$
        if (null != formatterName) {
            try {
                formatter = (Formatter) getCustomizeInstance(formatterName);
            } catch (Exception e) {
                printInvalidPropMessage("formatter", formatterName, e); //$NON-NLS-1$
                formatter = (Formatter) getDefaultInstance(defaultFormatter);
            }
        } else {
            formatter = (Formatter) getDefaultInstance(defaultFormatter);
        }

        // set encoding
        final String encodingName = manager.getProperty(prefix + ".encoding"); //$NON-NLS-1$
        try {
            internalSetEncoding(encodingName);
        } catch (UnsupportedEncodingException e) {
            printInvalidPropMessage("encoding", encodingName, e); //$NON-NLS-1$
        }
    }

    /**
     * Closes this handler. A flush operation will be performed and all the
     * associated resources will be freed. Client applications should not use
     * this handler after closing it.
     * 
     * @throws SecurityException
     *             if a security manager determines that the caller does not
     *             have the required permission.
     */
    public abstract void close();

    /**
     * Flushes any buffered output.
     */
    public abstract void flush();

    /**
     * Accepts a logging request and sends it to the the target.
     * 
     * @param record
     *            the log record to be logged; {@code null} records are ignored.
     */
    public abstract void publish(LogRecord record);

    /**
     * Gets the character encoding used by this handler, {@code null} for
     * default encoding.
     * 
     * @return the character encoding used by this handler.
     */
    public String getEncoding() {
        return this.encoding;
    }

    /**
     * Gets the error manager used by this handler to report errors during
     * logging.
     * 
     * @return the error manager used by this handler.
     * @throws SecurityException
     *             if a security manager determines that the caller does not
     *             have the required permission.
     */
    public ErrorManager getErrorManager() {
        LogManager.getLogManager().checkAccess();
        return this.errorMan;
    }

    /**
     * Gets the filter used by this handler.
     * 
     * @return the filter used by this handler (possibly {@code null}).
     */
    public Filter getFilter() {
        return this.filter;
    }

    /**
     * Gets the formatter used by this handler to format the logging messages.
     * 
     * @return the formatter used by this handler (possibly {@code null}).
     */
    public Formatter getFormatter() {
        return this.formatter;
    }

    /**
     * Gets the logging level of this handler, records with levels lower than
     * this value will be dropped.
     * 
     * @return the logging level of this handler.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Determines whether the supplied log record needs to be logged. The
     * logging levels will be checked as well as the filter.
     * 
     * @param record
     *            the log record to be checked.
     * @return {@code true} if the supplied log record needs to be logged,
     *         otherwise {@code false}.
     */
    public boolean isLoggable(LogRecord record) {
        if (null == record) {
            throw new NullPointerException();
        }
        if (this.level.intValue() == Level.OFF.intValue()) {
            return false;
        } else if (record.getLevel().intValue() >= this.level.intValue()) {
            return null == this.filter || this.filter.isLoggable(record);
        }
        return false;
    }

    /**
     * Reports an error to the error manager associated with this handler,
     * {@code ErrorManager} is used for that purpose. No security checks are
     * done, therefore this is compatible with environments where the caller
     * is non-privileged.
     *
     * @param msg
     *            the error message, may be {@code null}.
     * @param ex
     *            the associated exception, may be {@code null}.
     * @param code
     *            an {@code ErrorManager} error code.
     */
    protected void reportError(String msg, Exception ex, int code) {
        this.errorMan.error(msg, ex, code);
    }

    /**
     * Sets the character encoding used by this handler. A {@code null} value
     * indicates the use of the default encoding. This internal method does
     * not check security.
     * 
     * @param newEncoding
     *            the character encoding to set.
     * @throws UnsupportedEncodingException
     *             if the specified encoding is not supported by the runtime.
     */
    void internalSetEncoding(String newEncoding)
            throws UnsupportedEncodingException {
        // accepts "null" because it indicates using default encoding
        if (null == newEncoding) {
            this.encoding = null;
        } else {
            if (Charset.isSupported(newEncoding)) {
                this.encoding = newEncoding;
            } else {
                // logging.13=The encoding "{0}" is not supported.
                throw new UnsupportedEncodingException(Messages.getString(
                        "logging.13", //$NON-NLS-1$
                        newEncoding));
            }

        }
    }

    /**
     * Sets the character encoding used by this handler, {@code null} indicates
     * a default encoding.
     * 
     * @param encoding
     *            the character encoding to set.
     * @throws SecurityException
     *             if a security manager determines that the caller does not
     *             have the required permission.
     * @throws UnsupportedEncodingException
     *             if the specified encoding is not supported by the runtime.
     */
    public void setEncoding(String encoding) throws SecurityException,
            UnsupportedEncodingException {
        LogManager.getLogManager().checkAccess();
        internalSetEncoding(encoding);
    }

    /**
     * Sets the error manager for this handler.
     * 
     * @param em
     *            the error manager to set.
     * @throws NullPointerException
     *             if {@code em} is {@code null}.
     * @throws SecurityException
     *             if a security manager determines that the caller does not
     *             have the required permission.
     */
    public void setErrorManager(ErrorManager em) {
        LogManager.getLogManager().checkAccess();
        if (null == em) {
            throw new NullPointerException();
        }
        this.errorMan = em;
    }

    /**
     * Sets the filter to be used by this handler.
     * 
     * @param newFilter
     *            the filter to set, may be {@code null}.
     * @throws SecurityException
     *             if a security manager determines that the caller does not
     *             have the required permission.
     */
    public void setFilter(Filter newFilter) {
        LogManager.getLogManager().checkAccess();
        this.filter = newFilter;
    }

    /**
     * Sets the formatter to be used by this handler. This internal method does
     * not check security.
     * 
     * @param newFormatter
     *            the formatter to set.
     */
    void internalSetFormatter(Formatter newFormatter) {
        if (null == newFormatter) {
            throw new NullPointerException();
        }
        this.formatter = newFormatter;
    }

    /**
     * Sets the formatter to be used by this handler.
     * 
     * @param newFormatter
     *            the formatter to set.
     * @throws NullPointerException
     *             if {@code newFormatter} is {@code null}.
     * @throws SecurityException
     *             if a security manager determines that the caller does not
     *             have the required permission.
     */
    public void setFormatter(Formatter newFormatter) {
        LogManager.getLogManager().checkAccess();
        internalSetFormatter(newFormatter);
    }

    /**
     * Sets the logging level of the messages logged by this handler, levels
     * lower than this value will be dropped.
     * 
     * @param newLevel
     *            the logging level to set.
     * @throws NullPointerException
     *             if {@code newLevel} is {@code null}.
     * @throws SecurityException
     *             if a security manager determines that the caller does not
     *             have the required permission.
     */
    public void setLevel(Level newLevel) {
        if (null == newLevel) {
            throw new NullPointerException();
        }
        LogManager.getLogManager().checkAccess();
        this.level = newLevel;
    }
}
