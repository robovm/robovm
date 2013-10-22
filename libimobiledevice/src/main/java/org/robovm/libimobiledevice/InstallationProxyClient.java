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
package org.robovm.libimobiledevice;

import static org.robovm.libimobiledevice.binding.LibIMobileDeviceConstants.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import org.robovm.libimobiledevice.InstallationProxyClient.Options.ApplicationType;
import org.robovm.libimobiledevice.InstallationProxyClient.Options.PackageType;
import org.robovm.libimobiledevice.binding.InstproxyClientRef;
import org.robovm.libimobiledevice.binding.InstproxyClientRefOut;
import org.robovm.libimobiledevice.binding.LibIMobileDevice;
import org.robovm.libimobiledevice.binding.LockdowndServiceDescriptorStruct;
import org.robovm.libimobiledevice.binding.PlistRef;
import org.robovm.libimobiledevice.binding.PlistRefOut;

import com.dd.plist.BinaryPropertyListWriter;
import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;

/**
 * Manages applications on a device.
 */
public class InstallationProxyClient implements AutoCloseable {
    
    public static final String SERVICE_NAME = "com.apple.mobile.installation_proxy";
    
    protected InstproxyClientRef ref;

    InstallationProxyClient(InstproxyClientRef ref) {
        this.ref = ref;
    }
    
    /**
     * Creates a new {@link InstallationProxyClient} and makes a connection to 
     * the {@code com.apple.mobile.installation_proxy} service on the device.
     * 
     * @param device the device to connect to.
     * @param service the service descriptor returned by {@link LockdowndClient#startService(String)}.
     */
    public InstallationProxyClient(IDevice device, LockdowndServiceDescriptor service) {
        if (device == null) {
            throw new NullPointerException("device");
        }
        if (service == null) {
            throw new NullPointerException("service");
        }
        InstproxyClientRefOut refOut = new InstproxyClientRefOut();
        LockdowndServiceDescriptorStruct serviceStruct = new LockdowndServiceDescriptorStruct();
        serviceStruct.setPort((short) service.getPort());
        serviceStruct.setSslEnabled(service.isSslEnabled());
        try {
            checkResult(LibIMobileDevice.instproxy_client_new(device.getRef(), serviceStruct, refOut));
            this.ref = refOut.getValue();
        } finally {
            serviceStruct.delete();
            refOut.delete();
        }
    }

    /**
     * Lists all installed applications.
     * 
     * @return an {@link NSArray} of {@link NSDictionary}s holding information
     *         about the installed applications.
     */
    public NSArray browse() throws IOException {
        return browse(null);
    }
    
    /**
     * Lists installed applications of the specified type.
     *
     * @param type the type of applications to list. Either 
     *        {@link ApplicationType#User} or {@link ApplicationType#System}
     *        applications. Pass <code>null</code> to list all.
     * @return an {@link NSArray} of {@link NSDictionary}s holding information
     *         about the installed applications.
     */
    public NSArray browse(Options.ApplicationType type) throws IOException {
        PlistRefOut plistOut = new PlistRefOut();
        PlistRef clientOpts = new Options().applicationType(type).toPlistRef();
        try {
            checkResult(LibIMobileDevice.instproxy_browse(getRef(), clientOpts, plistOut));
            PlistRef plist = plistOut.getValue();
            return (NSArray) PlistUtil.toJavaPlist(plist);
        } finally {
            plistOut.delete();
            LibIMobileDevice.plist_free(clientOpts);
        }
    }
    
    /**
     * Installs an application on the device.
     *
     * @param pkgPath path of the installation package or app bundle dir inside 
     *        the AFC jail.
     * @param options the client options to use or <code>null</code>.
     *        Recognized options are {@link Options#iTunesMetadata(NSObject)},
     *        {@link Options#applicationSINF(NSObject)} and 
     *        {@link Options#packageType(PackageType)}.
     *        If {@code PackageType} is {@link PackageType#Developer} then 
     *        {@code pkgPath} points to an app bundle (.app directory) instead 
     *        of an install package.
     * @param callback callback which will be called for progress and status 
     *        information. If <code>null</code> is passed, this function will 
     *        run synchronously.
     */
    public void install(String pkgPath, Options options, StatusCallback callback) throws IOException {
        installOrUpgrade(pkgPath, options, callback, false);
    }
    
    /**
     * Upgrade an application on the device. This function is nearly the same as
     * {@link #install(String, Options, StatusCallback)}; the difference is that 
     * the installation progress on the device is faster if the application is 
     * already installed.     
     * 
     * @param pkgPath path of the installation package or app bundle dir inside 
     *        the AFC jail.
     * @param options the client options to use or <code>null</code>.
     *        Recognized options are {@link Options#iTunesMetadata(NSObject)},
     *        {@link Options#applicationSINF(NSObject)} and 
     *        {@link Options#packageType(PackageType)}.
     *        If {@code PackageType} is {@link PackageType#Developer} then 
     *        {@code pkgPath} points to an app bundle (.app directory) instead 
     *        of an install package.
     * @param callback callback which will be called for progress and status 
     *        information. If <code>null</code> is passed, this function will 
     *        run synchronously.
     */
    public void upgrade(String pkgPath, Options options, StatusCallback callback) throws IOException {
        installOrUpgrade(pkgPath, options, callback, true);
    }
    
    private void installOrUpgrade(String pkgPath, Options options, StatusCallback callback, boolean upgrade) throws IOException {
        PlistRef clientOpts = options.toPlistRef();
        try {
            long cb = 0L;
            int cbId = 0;
            if (callback != null) {
                cb = LibIMobileDevice.get_global_instproxy_status_cb();
                cbId = Callbacks.registerInstproxyCallback(callback);
            }
            if (upgrade) {
                checkResult(LibIMobileDevice.instproxy_upgrade(getRef(), pkgPath, 
                        clientOpts, cb, cbId));
            } else {
                checkResult(LibIMobileDevice.instproxy_install(getRef(), pkgPath, 
                        clientOpts, cb, cbId));
            }
        } finally {
            LibIMobileDevice.plist_free(clientOpts);
        }
    }
    
    protected InstproxyClientRef getRef() {
        checkDisposed();
        return ref;
    }
    
    protected final void checkDisposed() {
        if (ref == null) {
            throw new LibIMobileDeviceException("Already disposed");
        }
    }
    
    public synchronized void dispose() {
        checkDisposed();
        LibIMobileDevice.instproxy_client_free(ref);
        ref = null;
    }
    
    @Override
    public void close() throws Exception {
        dispose();
    }
    
    private static void checkResult(int result) {
        switch (result) {
        case INSTPROXY_E_SUCCESS: return;
        case INSTPROXY_E_INVALID_ARG: throw new LibIMobileDeviceException(result, "INSTPROXY_E_INVALID_ARG");
        case INSTPROXY_E_PLIST_ERROR: throw new LibIMobileDeviceException(result, "INSTPROXY_E_PLIST_ERROR");
        case INSTPROXY_E_CONN_FAILED: throw new LibIMobileDeviceException(result, "INSTPROXY_E_CONN_FAILED");
        case INSTPROXY_E_OP_IN_PROGRESS: throw new LibIMobileDeviceException(result, "INSTPROXY_E_OP_IN_PROGRESS");
        case INSTPROXY_E_OP_FAILED: throw new LibIMobileDeviceException(result, "INSTPROXY_E_OP_FAILED");
        case INSTPROXY_E_UNKNOWN_ERROR: throw new LibIMobileDeviceException(result, "INSTPROXY_E_UNKNOWN_ERROR");
        default: throw new LibIMobileDeviceException(result);
        }
    }
    
    /**
     * Options used passed to the various {@link InstallationProxyClient}
     * methods.
     */
    public static class Options {
        
        public enum ApplicationType { User, System }
        public enum PackageType { CarrierBundle, Developer }
        public enum ArchiveType { ApplicationOnly, DocumentsOnly }
        
        private Boolean skipUninstall;
        private ApplicationType applicationType;
        private PackageType packageType;
        private ArchiveType archiveType;
        private NSObject applicationSINF;
        private NSObject iTunesMetadata;
        private NSObject returnAttributes;
        
        public Options skipUninstall(boolean skipUninstall) {
            this.skipUninstall = skipUninstall;
            return this;
        }
        public Options applicationType(ApplicationType applicationType) {
            this.applicationType = applicationType;
            return this;
        }
        public Options packageType(PackageType packageType) {
            this.packageType = packageType;
            return this;
        }
        public Options archiveType(ArchiveType archiveType) {
            this.archiveType = archiveType;
            return this;
        }
        public Options applicationSINF(NSObject applicationSINF) {
            this.applicationSINF = applicationSINF;
            return this;
        }
        public Options iTunesMetadata(NSObject iTunesMetadata) {
            this.iTunesMetadata = iTunesMetadata;
            return this;
        }
        public Options returnAttributes(NSObject returnAttributes) {
            this.returnAttributes = returnAttributes;
            return this;
        }
        
        PlistRef toPlistRef() throws IOException {
            NSDictionary dict = new NSDictionary();
            if (skipUninstall != null) {
                dict.put("SkipUninstall", skipUninstall.booleanValue());
            }
            if (applicationType != null) {
                dict.put("ApplicationType", applicationType.toString());
            }
            if (packageType != null) {
                dict.put("PackageType", packageType.toString());
            }
            if (archiveType != null) {
                dict.put("ArchiveType", archiveType.toString());
            }
            if (applicationSINF != null) {
                dict.put("ApplicationSINF", applicationSINF);
            }
            if (iTunesMetadata != null) {
                dict.put("iTunesMetadata", iTunesMetadata);
            }
            if (returnAttributes != null) {
                dict.put("ReturnAttributes", returnAttributes);
            }
            
            byte[] bytes = BinaryPropertyListWriter.writeToArray(dict);
            PlistRefOut plistOut = new PlistRefOut();
            LibIMobileDevice.plist_from_bin(bytes, bytes.length, plistOut);
            PlistRef plist = plistOut.getValue();
            plistOut.delete();
            
            return plist;
        }
        
    }
    
    /**
     * Callback which receives status information on an ongoing operation.
     */
    public interface StatusCallback {
        /**
         * Reports the progress of the operation.
         * 
         * @param status the current status.
         * @param percentComplete the progress in percent.
         */
        void progress(String status, int percentComplete);
        /**
         * Called once the operation has been completed successfully.
         */
        void success();
        /**
         * Called if the operation fails.
         * 
         * @param message the error message.
         */
        void error(String message);
    }
    
    private static void printUsageAndExit() {
        System.err.println(InstallationProxyClient.class.getName() + " [deviceid] <action> ...");
        System.err.println("  Actions:");
        System.err.println("    install <remotepath>   Performs an install using the package in the specified path.");
        System.err.println("    upgrade <remotepath>   Performs an upgrade using the package in the specified path.");
        System.exit(0);
    }
    
    public static void main(String[] args) throws Exception {
        String deviceId = null;
        String action = null;

        int index = 0;
        try {
            action = args[index++];
            if (action.matches("[0-9a-f]{40}")) {
                deviceId = action;
                action = args[index++];
            }
        
            if (!action.matches("install|upgrade")) {
                System.err.println("Unknown action: " + action);
                printUsageAndExit();
            }
            
            if (deviceId == null) {
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
            }
            
            try (IDevice device = new IDevice(deviceId)) {
                try (LockdowndClient lockdowndClient = new LockdowndClient(device, AfcClient.class.getSimpleName(), true)) {
                    LockdowndServiceDescriptor service = lockdowndClient.startService(SERVICE_NAME);
                    try (InstallationProxyClient client = new InstallationProxyClient(device, service)) {
                        String path = args[index];
                        Options options = new Options();
                        if (path.toLowerCase().endsWith(".app") || path.endsWith("/")) {
                            options.packageType(PackageType.Developer);
                        }
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        StatusCallback callback = new StatusCallback() {
                            public void progress(String status, int percentComplete) {
                                System.out.format("[%3d%%] %s\n", percentComplete, status);
                            }
                            public void success() {
                                System.out.format("[100%%] Success!\n");
                                countDownLatch.countDown();
                            }
                            @Override
                            public void error(String message) {
                                System.out.format("Error: %s\n", message);
                                countDownLatch.countDown();
                            }
                        };
                        switch (action) {
                        case "install":
                            client.install(path, options, callback);
                            countDownLatch.await();
                            break;
                        case "upgrade":
                            client.upgrade(path, options, callback);
                            countDownLatch.await();
                            break;
                        }                    
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            printUsageAndExit();
        }
    }
}
