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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import org.robovm.libimobiledevice.binding.LibIMobileDevice;
import org.robovm.libimobiledevice.binding.LockdowndServiceDescriptorStruct;
import org.robovm.libimobiledevice.binding.MobileImageMounterClientRef;
import org.robovm.libimobiledevice.binding.MobileImageMounterClientRefOut;
import org.robovm.libimobiledevice.binding.PlistRef;
import org.robovm.libimobiledevice.binding.PlistRefOut;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;

/**
 * Mounts developer/debug disk images on the device.
 */
public class MobileImageMounterClient implements AutoCloseable {

    public static final String SERVICE_NAME = "com.apple.mobile.mobile_image_mounter";
    
    /**
     * The AFC jail prefix path. Has to be added to image paths before mounting.
     */
    private static final String PRIVATE_MOUNT_PREFIX = "/private/var/mobile/Media";
    
    protected MobileImageMounterClientRef ref;

    MobileImageMounterClient(MobileImageMounterClientRef ref) {
        this.ref = ref;
    }
    
    /**
     * Creates a new {@link MobileImageMounterClient} and makes a connection to 
     * the {@code com.apple.mobile.mobile_image_mounter} service on the device.
     * 
     * @param device the device to connect to.
     * @param service the service descriptor returned by {@link LockdowndClient#startService(String)}.
     */
    public MobileImageMounterClient(IDevice device, LockdowndServiceDescriptor service) {
        if (device == null) {
            throw new NullPointerException("device");
        }
        if (service == null) {
            throw new NullPointerException("service");
        }
        MobileImageMounterClientRefOut refOut = new MobileImageMounterClientRefOut();
        LockdowndServiceDescriptorStruct serviceStruct = new LockdowndServiceDescriptorStruct();
        serviceStruct.setPort((short) service.getPort());
        serviceStruct.setSslEnabled(service.isSslEnabled());
        try {
            checkResult(LibIMobileDevice.mobile_image_mounter_new(device.getRef(), serviceStruct, refOut));
            this.ref = refOut.getValue();
        } finally {
            serviceStruct.delete();
            refOut.delete();
        }
    }
    
    /**
     * Mounts an image on the device. After an image has been mounted it will
     * remain mounted until the device is rebooted. Returns an 
     * {@link NSDictionary} with {@code Status=Complete} on success. On error
     * the {@link NSDictionary} will contain {@code Error=ImageMountFailed}.
     * 
     * @param imagePath the path of the image to be mounted. Should be an 
     *        absolute path inside the AFC jail on the device.
     * @param imageSignature the image's signature data.
     * @param imageType type of the image. If <code>null</code> is passed
     *        {@code Developer} will be used.
     * @return the result of the mount.
     */
    public NSDictionary mountImage(String imagePath, byte[] imageSignature, String imageType) throws IOException {
        if (imagePath == null) {
            throw new NullPointerException("imagePath");
        }
        if (imageSignature == null) {
            throw new NullPointerException("imageSignature");
        }
        if (imageType == null) {
            imageType = "Developer";
        }
        if (!imagePath.startsWith("/")) {
            imagePath = "/" + imagePath;
        }
        imagePath = PRIVATE_MOUNT_PREFIX + imagePath;
        PlistRefOut plistOut = new PlistRefOut();
        try {
            checkResult(LibIMobileDevice.mobile_image_mounter_mount_image(getRef(), 
                    imagePath, imageSignature, (short) imageSignature.length, imageType, plistOut));
            PlistRef plist = plistOut.getValue();
            return (NSDictionary) PlistUtil.toJavaPlist(plist);
        } finally {
            plistOut.delete();
        }
    }
    
    /**
     * Checks if an image of the specified type has already been mounted. This
     * method returns a plist similar to this when an image has been mounted:
     * <pre>
     * &lt;plist version="1.0"&gt;
     *   &lt;dict&gt;
     *     &lt;key&gt;ImageDigest&lt;/key&gt;
     *     &lt;data&gt;rBSGlwMv4yovqGM7sOk44vrE6xI=&lt;/data&gt;
     *     &lt;key&gt;ImagePresent&lt;/key&gt;
     *     &lt;true/&gt;
     *     &lt;key&gt;Status&lt;/key&gt;
     *     &lt;string&gt;Complete&lt;/string&gt;
     *   &lt;/dict&gt;
     * &lt;/plist&gt;
     * </pre>
     * The {@code ImageDigest} value is the SHA-1 digest if the image file.
     * <p>
     * If no image has been mounted {@code ImagePresent=false} and there will be
     * no {@code ImageDigest} in the response.
     * <p>
     * NOTE! It seems like this only returns {@code ImagePresent=true} the first
     * time it is called after an image has been mounted. On subsequent calls
     * it returns {@code ImagePresent=false} even if the image is still mounted.
     * 
     * @param imageType type of the image to look for. If <code>null</code> is 
     *        passed {@code Developer} will be used.
     * @return the result of the lookup.
     */
    public NSDictionary lookupImage(String imageType) throws IOException {
        if (imageType == null) {
            imageType = "Developer";
        }
        PlistRefOut plistOut = new PlistRefOut();
        try {
            checkResult(LibIMobileDevice.mobile_image_mounter_lookup_image(getRef(), imageType, plistOut));
            PlistRef plist = plistOut.getValue();
            return (NSDictionary) PlistUtil.toJavaPlist(plist);
        } finally {
            plistOut.delete();
        }
    }
    
    protected MobileImageMounterClientRef getRef() {
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
        LibIMobileDevice.mobile_image_mounter_hangup(ref);
        LibIMobileDevice.mobile_image_mounter_free(ref);
        ref = null;
    }
    
    @Override
    public void close() throws Exception {
        dispose();
    }

    private static void checkResult(int result) {
        switch (result) {
        case MOBILE_IMAGE_MOUNTER_E_SUCCESS: return;
        case MOBILE_IMAGE_MOUNTER_E_CONN_FAILED: throw new LibIMobileDeviceException(result, "MOBILE_IMAGE_MOUNTER_E_CONN_FAILED");
        case MOBILE_IMAGE_MOUNTER_E_INVALID_ARG: throw new LibIMobileDeviceException(result, "MOBILE_IMAGE_MOUNTER_E_INVALID_ARG");
        case MOBILE_IMAGE_MOUNTER_E_PLIST_ERROR: throw new LibIMobileDeviceException(result, "MOBILE_IMAGE_MOUNTER_E_PLIST_ERROR");
        case MOBILE_IMAGE_MOUNTER_E_UNKNOWN_ERROR: throw new LibIMobileDeviceException(result, "MOBILE_IMAGE_MOUNTER_E_UNKNOWN_ERROR");
        default: throw new LibIMobileDeviceException(result);
        }
    }
    

    private static void printUsageAndExit() {
        System.err.println(InstallationProxyClient.class.getName() + " [deviceid] <action> ...");
        System.err.println("  Actions:");
        System.err.println("    lookup [type]       Looks for a mounted image of the specified type or 'Developer' if not specified.");
        System.err.println("    mount <remotepath> <sigpath> [type]\n" 
                         + "                        Mounts an image uploaded to the device. <sigpath> is the path to a local .signature file.\n" 
                         + "                        type is the type of the image to mount. If not specified 'Developer' will be used.");
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
        
            if (!action.matches("lookup|mount")) {
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
                try (LockdowndClient lockdowndClient = new LockdowndClient(device, MobileImageMounterClient.class.getSimpleName(), true)) {
                    LockdowndServiceDescriptor afcService = lockdowndClient.startService(AfcClient.SERVICE_NAME);
                    try (AfcClient afcClient = new AfcClient(device, afcService)) {
                        LockdowndServiceDescriptor mimService = lockdowndClient.startService(SERVICE_NAME);
                        try (MobileImageMounterClient mimClient = new MobileImageMounterClient(device, mimService)) {
                            NSObject result = null;
                            String imageType = null;
                            switch (action) {
                            case "lookup":
                                if (args.length < index) {
                                    imageType = args[index];
                                }
                                result = mimClient.lookupImage(imageType);
                                break;
                            case "mount":
                                String imagePath = args[index++];
                                String sigPath = args[index++];
                                byte[] sig = Files.readAllBytes(new File(sigPath).toPath());
                                if (args.length < index) {
                                    imageType = args[index];
                                }
                                afcClient.makeDirectory("/PublicStaging");
                                afcClient.fileCopy(new File(imagePath), "/PublicStaging/staging.dimage");
                                result = mimClient.mountImage("/PublicStaging/staging.dimage", sig, imageType);
                                break;
                            }
                            System.out.println(result.toXMLPropertyList());
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            printUsageAndExit();
        }
    }
}
