/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.libimobiledevice.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.robovm.libimobiledevice.AfcClient;
import org.robovm.libimobiledevice.AfcClient.UploadProgressCallback;
import org.robovm.libimobiledevice.IDevice;
import org.robovm.libimobiledevice.IDeviceConnection;
import org.robovm.libimobiledevice.InstallationProxyClient;
import org.robovm.libimobiledevice.InstallationProxyClient.Options;
import org.robovm.libimobiledevice.InstallationProxyClient.Options.PackageType;
import org.robovm.libimobiledevice.InstallationProxyClient.StatusCallback;
import org.robovm.libimobiledevice.LibIMobileDeviceException;
import org.robovm.libimobiledevice.LockdowndClient;
import org.robovm.libimobiledevice.LockdowndServiceDescriptor;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSString;
import com.dd.plist.PropertyListParser;

/**
 * Launches an application on a device using the {@code com.apple.debuserver}
 * service. The app must have the {@code get-task-allow} entitlement set to 
 * {@code true} in order to be allowed to be launched by the debug server.
 */
public class AppLauncher {
    
    private static final String DEBUG_SERVER_SERVICE_NAME = "com.apple.debugserver";
    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    private static final int RECEIVE_TIMEOUT = 5000;
    private static final byte[] BREAK = new byte[] { 0x03 };

    private byte[] buffer = new byte[4096];
    private StringBuilder bufferedResponses = new StringBuilder(4096);
    
    private final IDevice device;
    private final String appId;
    private File localAppPath;
    private List<String> args = new ArrayList<>();
    private Map<String, String> env = new HashMap<String, String>();
    private OutputStream stdout = System.out;
    private boolean closeOutOnExit = false;
    private boolean debug = false;
    private volatile boolean killed = false;
    private StatusCallback installStatusCallback;
    private UploadProgressCallback uploadProgressCallback;
    
    /**
     * Creates a new {@link AppLauncher} which will launch an already installed
     * app with the specified id.
     * 
     * @param device the device to connect to.
     * @param appId the id (CFBundleIdentifier) of the app to run.
     */
    public AppLauncher(IDevice device, String appId) {
        this(device, appId, null);
    }

    /**
     * Creates a new {@link AppLauncher} which will install the app from the
     * specified IPA file or app bundle dir and launch it.
     * 
     * @param device the device to connect to.
     * @param localAppPath the IPA file of app bundle dir containing the app to 
     *        install and launch.
     */
    public AppLauncher(IDevice device, File localAppPath) throws IOException {
        this(device, getAppId(localAppPath), localAppPath);
    }

    private AppLauncher(IDevice device, String appId, File localAppPath) {
        if (device == null) {
            throw new NullPointerException("device");
        }
        if (appId == null) {
            throw new NullPointerException("appId");
        }
        this.device = device;
        this.appId = appId;
        this.localAppPath = localAppPath;
    }
    
    private static String getAppId(File f) throws IOException {
        if (f == null) {
            throw new NullPointerException("localAppPath");
        }
        if (!f.exists()) {
            throw new FileNotFoundException(f.getAbsolutePath());
        }
        NSDictionary infoPlistDict = null;
        if (f.getName().toLowerCase().endsWith(".ipa")) {
            try (ZipFile zipFile = new ZipFile(f)) {
                for (ZipEntry entry : Collections.list(zipFile.entries())) {
                    if (entry.getName().matches("Payload/[^/]+\\.app/Info\\.plist")) {
                        try (InputStream is = zipFile.getInputStream(entry)) {
                            try {
                                infoPlistDict = (NSDictionary) PropertyListParser.parse(is);
                            } catch (IOException e) {
                                throw e;
                            } catch (Exception e) {
                                throw new IOException(e);
                            }
                            break;
                        }
                    }
                }
            }
        } else if (f.isDirectory()) {
            File infoPlistFile = new File(f, "Info.plist");
            if (infoPlistFile.exists()) {
                try {
                    infoPlistDict = (NSDictionary) PropertyListParser.parse(infoPlistFile);
                } catch (IOException e) {
                    throw e;
                } catch (Exception e) {
                    throw new IOException(e);
                }
            }
        }
        
        if (infoPlistDict == null) {
            throw new IllegalArgumentException("Path " + f + " is neither a " 
                    + ".ipa file nor an iOS app bundle directory.");
        }
        
        NSString appId = (NSString) infoPlistDict.objectForKey("CFBundleIdentifier");
        if (appId == null) {
            throw new IllegalArgumentException("No CFBundleIdentifier found in " 
                    + "the Info.plist file in " + f);
        }
        
        return appId.toString();
    }
    
    /**
     * Sets an {@link UploadProgressCallback} which will be used to report the
     * progress when the app is uploaded to the device.
     * 
     * @param callback the callback.
     */
    public AppLauncher uploadProgressCallback(UploadProgressCallback callback) {
        this.uploadProgressCallback = callback;
        return this;
    }
    
    /**
     * Sets an {@link StatusCallback} which will be used to report the
     * progress when the app is installed on the device.
     * 
     * @param callback the callback.
     */
    public AppLauncher installStatusCallback(StatusCallback callback) {
        this.installStatusCallback = callback;
        return this;
    }
    
    /**
     * Adds command line arguments which will be passed to the app on launch.
     * 
     * @param args the arguments to be added.
     */
    public AppLauncher args(String ... args) {
        this.args.addAll(Arrays.asList(args));
        return this;
    }
    
    /**
     * Sets an {@link OutputStream} which all console output (stdout and stderr)
     * of the app will be written to when the app is launched. By default all
     * output will be written to {@link System#out}
     * 
     * @param stdout the {@link OutputStream}.
     */
    public AppLauncher stdout(OutputStream stdout) {
        if (stdout == null) {
            throw new NullPointerException("stdout");
        }
        this.stdout = stdout;
        return this;
    }
    
    /**
     * Sets whether the stdout stream should be closed once the app has 
     * terminated.
     * 
     * @param closeOutOnExit <code>true</code> or <code>false</code>.
     */
    public AppLauncher closeOutOnExit(boolean closeOutOnExit) {
        this.closeOutOnExit = closeOutOnExit;
        return this;
    }
    
    /**
     * Adds an environment variable which will be set when launching the app.
     * 
     * @param name the variable name.
     * @param value the variable value.
     */
    public AppLauncher env(String name, String value) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        if (value == null) {
            throw new NullPointerException("value");
        }
        this.env.put(name, value);
        return this;
    }
    
    /**
     * Adds environment variables which will be set when launching the app.
     * 
     * @param env the variables.
     */
    public AppLauncher env(Map<String, String> env) {
        if (env == null) {
            throw new NullPointerException("env");
        }
        this.env.putAll(env);
        return this;
    }
    
    /**
     * Sets whether GDB protocol packets should be logged to {@link System#err}.
     * Disabled by default.
     * 
     * @param debug <code>true</code> to enabled debug logging.
     */
    public AppLauncher debug(boolean debug) {
        this.debug = debug;
        return this;
    }
    
    public void kill() {
        killed = true;
    }
    
    private static String toHex(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 2);
        byte[] bytes;
        try {
            bytes = s.getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
        for (int i = 0; i < bytes.length; i++) {
            int c = bytes[i] & 0xff;
            sb.append(HEX_CHARS[c >> 4]);
            sb.append(HEX_CHARS[c & 0xf]);
        }
        return sb.toString();
    }
    
    private static byte fromHex(char c1, char c2) {
        int d = 0;
        if (c1 <= '9') {
            d = c1 - '0';
        } else {
            d = c1 - 'a' + 10;
        }
        d <<= 4;
        if (c2 <= '9') {
            d |= c2 - '0';
        } else {
            d |= c2 - 'a' + 10;
        }
        return (byte) d;
    }
    
    private static byte[] fromHex(String s) {
        int length = s.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < (length >> 1); i++) {
            data[i] = fromHex(s.charAt(i * 2), s.charAt(i * 2 + 1));
        }
        return data;
    }
    
    private String encode(String cmd) {
        int checksum = 0;
        for (int i = 0; i < cmd.length(); i++) {
            checksum += cmd.charAt(i);
        }
        return String.format("$%s#%02x", cmd, checksum & 0xff);
    }

    private String decode(String packet) {
        int start = 1;
        if (packet.charAt(0) == '+' || packet.charAt(0) == '-') {
            start = 2;
        }
        int end = packet.lastIndexOf('#');
        return packet.substring(start, end);
    }

    private void debug(String s) {
        if (debug) {
            System.err.println(s);
        }
    }

    private void debug(String s, Object ... args) {
        if (debug) {
            System.err.format(s, args);
            System.err.println();
        }
    }

    private void sendGdbPacket(IDeviceConnection conn, String packet) throws IOException {
        debug("Sending packet: " + packet);
        byte[] data = packet.getBytes("ASCII");
        while (true) {
            int sentBytes = conn.send(data, 0, data.length);
            if (sentBytes == data.length) {
                break;
            }
            data = Arrays.copyOfRange(data, sentBytes, data.length);
        }
    }
    
    private String receiveGdbPacket(IDeviceConnection conn) throws IOException, TimeoutException {
        return receiveGdbPacket(conn, Integer.MAX_VALUE);
    }
    
    private String receiveGdbPacket(IDeviceConnection conn, long timeout) throws IOException, TimeoutException {
        int packetEnd = bufferedResponses.indexOf("#");
        if (packetEnd != -1 && bufferedResponses.length() - packetEnd > 2) {
            String packet = bufferedResponses.substring(0, packetEnd + 3);
            bufferedResponses.delete(0, packetEnd + 3);
            debug("Received packet: " + packet);
            return packet;
        }
        
        long deadline = System.currentTimeMillis() + timeout;
        while (true) {
            if (killed || Thread.currentThread().isInterrupted()) {
                killed = true;
                throw new InterruptedIOException();
            }
            int receivedBytes = conn.receive(buffer, 0, buffer.length, 500);
            if (receivedBytes > 0) {
                bufferedResponses.append(new String(buffer, 0, receivedBytes, "ASCII"));
                packetEnd = bufferedResponses.indexOf("#");
                if (packetEnd != -1 && bufferedResponses.length() - packetEnd > 2) {
                    String packet = bufferedResponses.substring(0, packetEnd + 3);
                    bufferedResponses.delete(0, packetEnd + 3);
                    debug("Received packet: " + packet);
                    return packet;
                }
            }
            if (System.currentTimeMillis() > deadline) {
                throw new TimeoutException();
            }
        }
    }
    
    private boolean receiveGdbAck(IDeviceConnection conn) throws IOException {
        if (bufferedResponses.length() > 0) {
            char c = bufferedResponses.charAt(0);
            bufferedResponses.delete(0, 1);
            return c == '+';
        }
        
        byte[] buffer = new byte[1];
        conn.receive(buffer, 0, buffer.length, RECEIVE_TIMEOUT);
        debug("Received ack: " + (char) buffer[0]);
        return buffer[0] == '+';
    }
    
    private void sendReceivePacket(IDeviceConnection conn, String packet, 
            String expectedResponse, boolean ackMode) throws IOException, TimeoutException {
        
        sendGdbPacket(conn, packet);
        if (ackMode) {
            receiveGdbAck(conn);
        }
        String response = decode(receiveGdbPacket(conn, RECEIVE_TIMEOUT));
        if (!expectedResponse.equals(response)) {
            if (response.startsWith("E")) {
                throw new RuntimeException("Launch failed: " + response.substring(1));
            }
            throw new RuntimeException("Launch failed: Unexpected response '" 
                    + response + "' to command '" + decode(packet) + "'");
        }
    }
    
    private void kill(IDeviceConnection conn) throws IOException, TimeoutException {
        // We're killed. Try to shutdown nicely.
        killed = false;
        Thread.interrupted();
        debug("Sending break");
        conn.send(BREAK, 0, BREAK.length);
        receiveGdbPacket(conn, RECEIVE_TIMEOUT);
        sendGdbPacket(conn, encode("k"));
    }
    
    private String encodeArgs(String appPath) {
        StringBuilder sb = new StringBuilder();
        String hex = toHex(appPath);
        sb.append(String.format("%d,0,%s", hex.length(), hex));
        for (int i = 0; i < args.size(); i++) {
            hex = toHex(args.get(i));
            sb.append(String.format(",%d,%d,%s", hex.length(), i + 1, hex));
        }
        return sb.toString();
    }
    
    private String getAppPath(LockdowndClient lockdowndClient, String appId) throws IOException {
        LockdowndServiceDescriptor instService = lockdowndClient.startService(InstallationProxyClient.SERVICE_NAME);
        InstallationProxyClient instClient = new InstallationProxyClient(device, instService);
        try {
            NSArray apps = instClient.browse();
            for (int i = 0; i < apps.count(); i++) {
                NSDictionary appInfo = (NSDictionary) apps.objectAtIndex(i);
                NSString bundleId = (NSString) appInfo.objectForKey("CFBundleIdentifier");
                if (bundleId != null && appId.equals(bundleId.toString())) {
                    NSString path = (NSString) appInfo.objectForKey("Path");
                    NSDictionary entitlements = (NSDictionary) appInfo.objectForKey("Entitlements");
                    if (entitlements == null || entitlements.objectForKey("get-task-allow") == null
                            || !entitlements.objectForKey("get-task-allow").equals(new NSNumber(true))) {
                        throw new RuntimeException("App with id '" + appId + "' does not " 
                            + "have the 'get-task-allow' entitlement and cannot be debugged");
                    }
                    if (path == null) {
                        throw new RuntimeException("Path for app with id '" + appId + "' not found");
                    }
                    return path.toString();
                }
            }
            throw new RuntimeException("No app with id '" + appId + "' found on device");
        } finally {
            instClient.dispose();
        }
    }
    
    public void install() throws IOException {
        if (localAppPath != null) {
            try (LockdowndClient lockdowndClient = new LockdowndClient(device, getClass().getSimpleName(), true)) {
                uploadInternal();
                debug("[ 50%] Upload done. Installing app...");
                installInternal();
                localAppPath = null;
            } catch (IOException e) {
                throw e;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
    
    private int launchInternal() throws Exception {
        install();
        
        IDeviceConnection conn = null;
        String appPath = null;
        
        try (LockdowndClient lockdowndClient = new LockdowndClient(device, getClass().getSimpleName(), true)) {
            appPath = getAppPath(lockdowndClient, appId);
            LockdowndServiceDescriptor debugService = lockdowndClient.startService(DEBUG_SERVER_SERVICE_NAME);
            conn = device.connect(debugService.getPort());
        }

        try {
            // Talk to the debugserver using the GDB remote protocol.
            // See https://sourceware.org/gdb/onlinedocs/gdb/Remote-Protocol.html.
            // This process has been determined by observing how Xcode talks to
            // the debugserver. To enable GDB remote protocol logging in Xcode
            // write the following to ~/.lldbinit:
            //   log enable -v -f /tmp/gdb-remote.log gdb-remote all

            // Disable ack mode
            sendGdbPacket(conn, "+");
            sendReceivePacket(conn, encode("QStartNoAckMode"), "OK", true);
            sendGdbPacket(conn, "+");

            // Disable buffered IO. Xcode does it so we do it too.
            sendReceivePacket(conn, encode("QEnvironment:NSUnbufferedIO=YES"), "OK", false);
            // Set environment variables
            for (Entry<String, String> entry : env.entrySet()) {
                String cmd = String.format("QEnvironment:%s=%s", entry.getKey(), entry.getValue());
                sendReceivePacket(conn, encode(cmd), "OK", false);
            }
            // Tell the debuserver to send threads:xxx,yyy,... in stop replies
            sendReceivePacket(conn, encode("QListThreadsInStopReply"), "OK", false);
            // Initialize argv with the app path and args
            sendReceivePacket(conn, encode("A" + encodeArgs(appPath)), "OK", false);
            // Make sure the launch was successful
            sendReceivePacket(conn, encode("qLaunchSuccess"), "OK", false);
            // Continue
            sendGdbPacket(conn, encode("c"));
            
            boolean wasInterrupted = false;
            try {
                while (true) {
                    try {
                        String response = receiveGdbPacket(conn);
                        String payload = decode(response);
                        if (payload.charAt(0) == 'W') {
                            // The app exited. The number following W is the exit code.
                            int exitCode = Integer.parseInt(payload.substring(1), 16);
                            return exitCode;
                        } else if (payload.charAt(0) == 'O') {
                            // Console output encoded as hex.
                            stdout.write(fromHex(payload.substring(1)));
                        } else if (payload.charAt(0) == 'T') {
                            // Signal received. Just continue.
                            // The Continue packet looks like this (thread 0x2403 was interrupted by signal 0x0b):
                            //   $vCont;c:2603;c:2703;c:2803;c:2903;c:2a03;c:2b03;c:2c03;c:2d03;C0b:2403#ed
                            String signal = payload.substring(1, 3);
                            String data = payload.substring(3);
                            String threadId = data.replaceAll(".*thread:([0-9a-fA-F]+).*", "$1");
                            String allThreadIds = data.replaceAll(".*threads:([0-9a-fA-F,]+).*", "$1");
                            Set<String> ids = new TreeSet<>(Arrays.asList(allThreadIds.split(",")));
                            ids.remove(threadId);
                            StringBuilder sb = new StringBuilder("vCont;");
                            for (String id : ids) {
                                sb.append("c:").append(id).append(';');
                            }
                            sb.append('C').append(signal).append(':').append(threadId);
                            sendGdbPacket(conn, encode(sb.toString()));
                        } else {
                            throw new RuntimeException("Unexpected response " 
                                    + "from debugserver: " + response);
                        }
                    } catch (InterruptedIOException e) {
                        // Remember whether we were interrupted. kill() clears
                        // the thread's interrupted state and we want to reset it
                        // when we exit.
                        wasInterrupted = Thread.currentThread().isInterrupted();
                        kill(conn);
                    }
                }
            } finally {
                if (wasInterrupted) {
                    Thread.currentThread().interrupt();
                }
            }
            
        } finally {
            conn.dispose();
        }
    }

    private void installInternal() throws Exception {
        try (LockdowndClient lockdowndClient = new LockdowndClient(device, getClass().getSimpleName(), true)) {
            final LibIMobileDeviceException[] ex = new LibIMobileDeviceException[1];
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            LockdowndServiceDescriptor instproxyService = lockdowndClient.startService(InstallationProxyClient.SERVICE_NAME);
            try (InstallationProxyClient instClient = new InstallationProxyClient(device, instproxyService)) {
                instClient.upgrade("/PublicStaging/" + localAppPath.getName(), 
                        new Options().packageType(localAppPath.isDirectory() ? PackageType.Developer : null), 
                        new StatusCallback() {
                    
                    @Override
                    public void progress(String status, int percentComplete) {
                        debug("[%3d%%] %s", 50 + percentComplete / 2, status);
                        if (installStatusCallback != null) {
                            installStatusCallback.progress(status, percentComplete);
                        }
                    }
                    @Override
                    public void success() {
                        try {
                            debug("[100%] Installation complete");
                            if (installStatusCallback != null) {
                                installStatusCallback.success();
                            }
                        } finally {
                            countDownLatch.countDown();
                        }
                    }
                    @Override
                    public void error(String message) {
                        try {
                            ex[0] = new LibIMobileDeviceException(message);
                            debug("Error: %s", message);
                            if (installStatusCallback != null) {
                                installStatusCallback.error(message);
                            }
                        } finally {
                            countDownLatch.countDown();
                        }
                    }
                });
                countDownLatch.await();
            }
            
            if (ex[0] != null) {
                throw ex[0];
            }
        }
    }

    private void uploadInternal() throws Exception {
        try (LockdowndClient lockdowndClient = new LockdowndClient(device, getClass().getSimpleName(), true)) {
            LockdowndServiceDescriptor afcService = lockdowndClient.startService(AfcClient.SERVICE_NAME);
            try (AfcClient afcClient = new AfcClient(device, afcService)) {
                afcClient.upload(localAppPath, "/PublicStaging", new UploadProgressCallback() {
                    public void progress(File path, int percentComplete) {
                        debug("[%3d%%] Uploading %s", percentComplete / 2, path);
                        if (uploadProgressCallback != null) {
                            uploadProgressCallback.progress(path, percentComplete);
                        }
                    }
                    public void success() {
                        if (uploadProgressCallback != null) {
                            uploadProgressCallback.success();
                        }
                    }
                    public void error(String message) {
                        debug("Error: %s", message);
                        if (uploadProgressCallback != null) {
                            uploadProgressCallback.error(message);
                        }
                    }
                });
            }
        }
    }
    
    public int launch() throws IOException {
        try {
            return launchInternal();
        } catch (IOException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            if (closeOutOnExit) {
                try {
                    stdout.close();
                } catch (Throwable t) {
                    // Ignore
                }
            }
        }
    }
    
    private static void printUsageAndExit() {
        System.err.println(AppLauncher.class.getName() + " ...");
        System.err.println("  -appid    the id (CFBundleIdentifier) of the app to launch.");
        System.err.println("  -b path   to app bundle directory or IPA containing the app to launch.");
        System.err.println("  -udid     id of the device to launch on. If not specified the first device will be used.");
        System.err.println("  -debug    enable debug output.");
        System.err.println("  -env name=value\n" 
                         + "            adds an environment variable with the specified name and value.");
        System.err.println("  -args ... the rest of the command line will be passed on as args to the app.");
        System.exit(0);
    }
    
    public static void main(String[] args) throws Exception {
        String appId = null;
        File localAppPath = null;
        String[] arguments = new String[0];
        Map<String, String> env = new HashMap<>();
        boolean debug = false;
        String deviceId = null;
        
        int i = 0;
        loop: while (i < args.length) {
            switch (args[i++]) {
            case "-h":
            case "-help":
                printUsageAndExit();
                break;
            case "-appid":
                appId = args[i++];
                break;
            case "-b":
                localAppPath = new File(args[i++]);
                break;
            case "-udid":
                deviceId = args[i++];
                break;
            case "-env":
                String[] parts = args[i++].split("=", 2);
                env.put(parts[0], parts[1]);
                break;
            case "-debug":
                debug = true;
                break;
            case "-args":
                arguments = Arrays.copyOfRange(args, i, args.length);
                break loop;
            }
        }
        
        if (appId == null && localAppPath == null) {
            printUsageAndExit();
        }
        
        if (deviceId == null) {
            String[] udids = IDevice.listUdids();
            if (udids.length == 0) {
                System.err.println("No device connected");
                return;
            }
            if (udids.length > 1) {
                System.err.println("More than 1 device connected (" 
                        + Arrays.asList(udids) + "). Using " + udids[0]);
            }
            deviceId = udids[0];
        }
        
        IDevice device = new IDevice(deviceId);
        
        AppLauncher launcher = null;
        if (localAppPath != null) {
            launcher = new AppLauncher(device, localAppPath);
        } else {
            launcher = new AppLauncher(device, appId);
        }
        
        System.exit(launcher
                .args(arguments)
                .env(env)
                .debug(debug)
                .launch());
    }
}
