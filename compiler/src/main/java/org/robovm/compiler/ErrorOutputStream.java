/**
 * 
 */
package org.robovm.compiler;

/**
 * @author niklas
 *
 */
class ErrorOutputStream extends LoggerOutputStream {
    ErrorOutputStream(Logger logger) {
        super(logger);
    }
    @Override
    protected void log(byte[] message, int off, int length) {
        logger.error(new String(message, off, length).replace("%", "%%"));
    }
}
