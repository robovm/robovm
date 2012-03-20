/**
 * 
 */
package org.nullvm.compiler;

/**
 * @author niklas
 *
 */
public interface Logger {

    public static final Logger NULL_LOGGER = new Logger() {
        public void debug(String format, Object... args) {
        }
        public void info(String format, Object... args) {
        }
        public void warn(String format, Object... args) {
        }
        public void error(String format, Object... args) {
        }
    };
    
    void debug(String format, Object ... args);
    void info(String format, Object ... args);
    void warn(String format, Object ... args);
    void error(String format, Object ... args);
    
}
