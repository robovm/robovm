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
package java.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Formatter;


/**
 * Provider to read and write message to char-based console device. Any Console
 * instance should be obtained via System.console(). If the standard input and
 * output stream has been redirected, the System.console() will return null.
 * 
 * @since 1.6
 */
public final class Console implements Flushable {

    private static Console console;

    private PrintWriter writer;

    private ConsoleReader reader;

    private Formatter formatter;

    static final Object CONSOLE_LOCK = new Object();
    
    // Called by System.console
    static Console getConsole() {   
       if (hasStdInImpl() && hasStdOutImpl()) {
			console = new Console(System.in, System.out);
		}
		return console;
    }

    private Console(InputStream stdin, OutputStream stdout) {
        reader = new ConsoleReader(stdin);
        writer = new ConsoleWriter(stdout);
    }

    private Formatter getFormatter() {
        if (null == formatter) {
            formatter = new Formatter(writer);
        }
        return formatter;
    }

    public void flush() {
        writer.flush();
    }

    /**
	 * Returns a formatted string to this console instance's output stream using
	 * the specified format string and arguments.
	 * 
	 * @param fmt -
	 *            the formatted string.
	 * @param args -
	 *            the arguments used by the formatter.
	 * @return - the console instance.
	 */
    public Console format(String fmt, Object... args) {
        Formatter fm = getFormatter();
        fm.format(fmt, args);
        fm.flush();
        return this;
    }
    
    /**
	 * A conveinece method to write a formatted string to the console. It is
	 * equal to format(fmt, args).
	 * 
	 * @param fmt -
	 *            the formatted string.
	 * @param args -
	 *            the arguments used by the formatter.
	 * @return - the console instance.
	 */
    public Console printf(String fmt, Object... args) {
    	return format(fmt, args);
	}

    /**
	 * Answers the unique Reader object associated with this console.
	 * 
	 * @return - the specified reader related to the console.
	 */
    public Reader reader() {
        return reader;
    }

    /**
	 * Reads a line of string from this console.
	 * 
	 * @return - the string input, null if the end of the input stream has been
	 *         reached.
	 */
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new IOError(e);
        }

    }

    /**
	 * Reads a line of string from this console, with a specified string to
	 * prompt.
	 * 
	 * @return - the string input, null if the end of the input stream has been
	 *         reached.
	 * @param fmt -
	 *            the formatted string.
	 * @param args -
	 *            the arguments used by the formatter.
	 * @return - the string input, null if the end of the input stream has been
	 *         reached.
	 */
    public String readLine(String fmt, Object... args) {
        synchronized (CONSOLE_LOCK) {
            format(fmt, args);
            return readLine();
        }
    }

    /**
	 * Reads a password string from this console, which will not display the
	 * string on the screen.
	 * 
	 * @return - A character array containing the password, null if the end of
	 *         the input stream has been reached.
	 */
    public char[] readPassword() {
        String password;
        synchronized (CONSOLE_LOCK) {
            setEchoOffImpl();
            password = readLine();
            setEchoOnImpl();
        }
        return null == password ? null : password.toCharArray();
    }

    /**
	 * Reads a password string from this console, which does not display the
	 * string on the screen. A formatted prompt is also displayed.
	 * 
	 * @param fmt -
	 *            the formatted string.
	 * @param args -
	 *            the arguments used by the formatter.
	 * @return - A character array containing the password, null if the end of
	 *         the input stream has been reached.
	 */
    public char[] readPassword(String fmt, Object... args) {
        synchronized (CONSOLE_LOCK) {
            format(fmt, args);
            return readPassword();
        }
    }

    /**
	 * Answers the unique Writer object associated with this console.
	 * 
	 * @return - the specified writer related to the console.
	 */
    public PrintWriter writer() {
        return writer;
    }

    private static class ConsoleReader extends InputStreamReader {

        private static final int END_OF_STREAM = -1;

		private static final int DEFAULT_BUFFER_SZIE = 2048;

		private CharBuffer line;

		private InputStream in;

		private static final byte[] CR = System
				.getProperty("line.separator").getBytes(); //$NON-NLS-1$

		private static final Charset charSet = Charset.forName(System
				.getProperty("file.encoding")); //$NON-NLS-1$
        
        public ConsoleReader(InputStream in) {
            super(in);
            lock = CONSOLE_LOCK;
            this.in = in;
        }

        @Override
        // Console.reader cannot be closed.
        public void close() {
            // do nothing
        }
        
        public String readLine() throws IOException {
            if (null == line || !line.hasRemaining()) {
				getNextLine();
			}
            if (null == line) {
            	return null;
            }
            String result = line.toString();
            line = null;
            return result;
        }
                
        private void getNextLine() throws IOException {
			int count = 0;
			byte[] byteBufferArray = new byte[DEFAULT_BUFFER_SZIE];
			
			while (!hasGotLine(byteBufferArray, count)) {
				byte b = (byte) in.read();	
				if (b == END_OF_STREAM) {
					return ;
				}
				
				byteBufferArray[count++] = b;
								
				if (count >= byteBufferArray.length) {
					byte[] tmpBuffer = byteBufferArray;
					byteBufferArray = new byte[byteBufferArray.length * 2];
					System.arraycopy(tmpBuffer, 0, byteBufferArray, 0, tmpBuffer.length);					
				}
			}
			
			ByteBuffer byteBuffer = ByteBuffer.allocate(count - CR.length);
			byteBuffer.clear();
			byteBuffer.put(byteBufferArray, 0, count - CR.length);
			byteBuffer.flip();
			line = charSet.decode(byteBuffer);
		}
        
        private boolean hasGotLine(byte[] byteBufferArray, int pos) {
            boolean gotLine = false;
            if (pos >= CR.length) {
                gotLine = true;
                for (int i = 0; i < CR.length; i++) {
                    if (byteBufferArray[pos - CR.length + i] != CR[i]) {
                        gotLine = false;
                        break;
                    }
                }
            }
            return gotLine;
        }
    }

    private static class ConsoleWriter extends PrintWriter {

        public ConsoleWriter(OutputStream out) {
            super(out, true);
            lock = CONSOLE_LOCK;
        }

        @Override
        // Console.writer cannot be closed.
        public void close() {
            flush();
        }
    }
    
    private static native boolean hasStdInImpl();

    private static native boolean hasStdOutImpl();

    private native void setEchoOffImpl();

    private native void setEchoOnImpl();
}
