/**
 * 
 */
package org.robovm.compiler;

/**
 * @author niklas
 *
 */
class DebugOutputStream extends LoggerOutputStream {
    DebugOutputStream(Logger logger) {
        super(logger);
    }
    @Override
    protected void log(byte[] message, int off, int length) {
        logger.debug(new String(message, off, length).replace("%", "%%"));
    }
}
