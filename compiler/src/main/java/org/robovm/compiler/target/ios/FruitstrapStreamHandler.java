/*
 * Copyright (C) 2012 Trillian AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler.target.ios;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;

import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.io.IOUtils;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MICommand;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MICommandInjector;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MIProxy;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MIRecord;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MIUserInteraction;

class FruitstrapStreamHandler implements ExecuteStreamHandler {
    private PrintStream processIn;
    private InputStream processErr;
    private InputStream processOut;
    private Thread errThread;
    private Thread outThread;
    private final OutputStream out;
    private final OutputStream err;
    private final OutputStream fruitstrapOut;
    private final OutputStream fruitstrapErr;

    FruitstrapStreamHandler(OutputStream out, OutputStream err, OutputStream fruitstrapOut, OutputStream fruitstrapErr) {
        this.out = out;
        this.err = err;
        this.fruitstrapOut = fruitstrapOut;
        this.fruitstrapErr = fruitstrapErr;
    }
    
    @Override
    public void setProcessInputStream(OutputStream os) throws IOException {
        this.processIn = new PrintStream(os);
    }

    @Override
    public void setProcessErrorStream(InputStream is) throws IOException {
        this.processErr = is;
    }

    @Override
    public void setProcessOutputStream(InputStream is) throws IOException {
        this.processOut = is;
    }

    private String unescape(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        boolean escaped = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (escaped) {
                switch (c) {
                case 'b':
                    sb.append('\b');
                    break;
                case 'n':
                    sb.append('\n');
                    break;
                case 't':
                    sb.append('\t');
                    break;
                case 'f':
                    sb.append('\f');
                    break;
                case 'r':
                    sb.append('\r');
                    break;
                default:
                    sb.append(c);
                    break;
                }
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
        
    private void startGdbProcessor(InputStream gdbStream) throws IOException {
        
        MICommandInjector injector = new MICommandInjector() {
            public void log(String data) {
            }
            public void inject(String data) {
                processIn.print(data);
                processIn.flush();
            }
        };

        MIProxy proxy = new MIProxy(injector, "(gdb)", System.getProperty("file.encoding")) {
            boolean started = false;
            @Override
            protected void targetStreamOutput(MIRecord record) {
                String s = unescape(record.stream());
                try {
                    out.write(s.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
//                @Override
//                protected void consoleStreamOutput(MIRecord record) {
//                    targetStreamOutput(record);
//                }
//                @Override
//                protected void logStreamOutput(MIRecord record) {
//                    consoleStreamOutput(record);
//                }
                @Override
            protected void prompt() {
                if (!started) {
                    started = true;
                    send(new SimpleMICommand(0, "-interpreter-exec console \"handle all nostop noprint\"") {
                        protected void onDone(MIRecord record) {
                            send(new SimpleMICommand(0, "-exec-continue") {
                                @Override
                                protected void onStopped(MIRecord record) {
                                    send(new SimpleMICommand(0, "-gdb-exit"));
                                }
                            });
                        }
                    });
                }
            }
        };
        
        BufferedReader gdbReader = new BufferedReader(new InputStreamReader(gdbStream));
        String line = null;
        while ((line = gdbReader.readLine()) != null) {
            proxy.processLine(line);
        }
    }
        
    @Override
    public void start() throws IOException {
        if (processErr != null) {
            errThread = new Thread() {
                @Override
                public void run() {
                    try {
                        byte[] buf = new byte[4096];
                        int n;
                        while ((n = processErr.read(buf)) > 0) {
                            fruitstrapErr.write(buf, 0, n);
                        }
                    } catch (IOException e) {
                    }
                    
                    IOUtils.closeQuietly(processErr);
                    IOUtils.closeQuietly(fruitstrapErr);
                    IOUtils.closeQuietly(err);
                }
            };
            errThread.start();
        }
        if (processOut != null) {
            outThread = new Thread() {
                @Override
                public void run() {
                    try {
                        byte[] buf = new byte[4096];
                        int n = 0;
                        boolean gdb = false;
                        int dashes = 0;
                        outer: while ((n = processOut.read(buf)) > 0) {
                            for (int i = 0; i < n; i++) {
                                if (buf[i] == '-') {
                                    dashes++;
                                } else if (dashes == 25 && buf[i] == '\n') {
                                    // gdb started
                                    fruitstrapOut.write(buf, 0, i + 1);
                                    n -= i + 1;
                                    if (n > 0) {
                                        System.arraycopy(buf, i + 1, buf, 0, n);
                                    }
                                    gdb = true;
                                    break outer;
                                } else {
                                   dashes = 0;
                                }
                            }
                            fruitstrapOut.write(buf, 0, n);
                        }

                        if (gdb) {
                            startGdbProcessor(new SequenceInputStream(new ByteArrayInputStream(buf, 0, n), processOut));
                        }
                    
                    } catch (IOException e) {
                    }
                    
                    IOUtils.closeQuietly(processOut);
                    IOUtils.closeQuietly(fruitstrapOut);
                    IOUtils.closeQuietly(out);
                }
            };
            outThread.start();
        }
    }

    @Override
    public void stop() {
        if (errThread != null) {
            errThread.interrupt();
            try {
                errThread.join();
            } catch (InterruptedException e) {
            }
        }
        if (outThread != null) {
            outThread.interrupt();
            try {
                outThread.join();
            } catch (InterruptedException e) {
            }
        }
    }
        
    private static class SimpleMICommand extends MICommand {

        public SimpleMICommand(int routingToken, String command) {
            super(routingToken, command);
        }

        @Override
        protected void onDone(MIRecord record) {
        }

        @Override
        protected void onRunning(MIRecord record) {
        }

        @Override
        protected void onError(MIRecord record) {
        }

        @Override
        protected void onExit(MIRecord record) {
        }

        @Override
        protected void onStopped(MIRecord record) {
        }

        @Override
        protected void onOther(MIRecord record) {
        }

        @Override
        protected void onUserInteraction(MIUserInteraction ui) {
        }
        
    }
}
