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
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.robovm.libimobiledevice.binding.AfcClientRef;
import org.robovm.libimobiledevice.binding.AfcClientRefOut;
import org.robovm.libimobiledevice.binding.AfcFileMode;
import org.robovm.libimobiledevice.binding.AfcLinkType;
import org.robovm.libimobiledevice.binding.IntOut;
import org.robovm.libimobiledevice.binding.LibIMobileDevice;
import org.robovm.libimobiledevice.binding.LibIMobileDeviceConstants;
import org.robovm.libimobiledevice.binding.LockdowndServiceDescriptorStruct;
import org.robovm.libimobiledevice.binding.LongOut;
import org.robovm.libimobiledevice.binding.StringArray;
import org.robovm.libimobiledevice.binding.StringArrayOut;

/**
 * Provides access to the device filesystem.
 */
public class AfcClient implements AutoCloseable {
    
    public static final String SERVICE_NAME = "com.apple.afc";
    
    public static final String DEVICE_INFO_KEY_FS_TOTAL_BYTES = "FSTotalBytes";
    public static final String DEVICE_INFO_KEY_FS_FREE_BYTES = "FSFreeBytes";
    public static final String DEVICE_INFO_KEY_FS_BLOCK_SIZE = "FSBlockSize";
    public static final String DEVICE_INFO_KEY_MODEL = "Model";
    /**
     * Creation time in nanos.
     */
    public static final String FILE_INFO_KEY_ST_BIRTHTIME = "st_birthtime";
    /**
     * Last modification time in nanos.
     */
    public static final String FILE_INFO_KEY_ST_MTIME = "st_mtime";
    /**
     * Number of blocks allocated for a file.
     */
    public static final String FILE_INFO_KEY_ST_BLOCKS = "st_blocks";
    /**
     * Number of hard links.
     */
    public static final String FILE_INFO_KEY_ST_NLINK = "st_nlink";
    /**
     * File size in bytes.
     */
    public static final String FILE_INFO_KEY_ST_SIZE = "st_size";
    /**
     * File type. {@code S_IFREG} for regular files. {@code S_IFDIR} for 
     * directories. {@code S_IFLNK} for links.
     */
    public static final String FILE_INFO_KEY_ST_IFMT = "st_ifmt";
    /**
     * Link target.
     */
    public static final String FILE_INFO_KEY_LINK_TARGET = "LinkTarget";
    
    protected AfcClientRef ref;

    AfcClient(AfcClientRef ref) {
        this.ref = ref;
    }
    
    /**
     * Creates a new {@link AfcClient} and makes a connection to the 
     * {@code com.apple.afc} service on the device.
     * 
     * @param device the device to connect to.
     * @param service the service descriptor returned by {@link LockdowndClient#startService(String)}.
     */
    public AfcClient(IDevice device, LockdowndServiceDescriptor service) {
        if (device == null) {
            throw new NullPointerException("device");
        }
        if (service == null) {
            throw new NullPointerException("service");
        }
        AfcClientRefOut refOut = new AfcClientRefOut();
        LockdowndServiceDescriptorStruct serviceStruct = new LockdowndServiceDescriptorStruct();
        serviceStruct.setPort((short) service.getPort());
        serviceStruct.setSslEnabled(service.isSslEnabled());
        try {
            checkResult(LibIMobileDevice.afc_client_new(device.getRef(), serviceStruct, refOut));
            this.ref = refOut.getValue();
        } finally {
            serviceStruct.delete();
            refOut.delete();
        }
    }
    
    /**
     * Returns a directory listing of the specified directory.
     *
     * @param dir the directory to list. Must be a fully-qualified path.
     * @return the list of files in the specified directory.
     */
    public String[] readDirectory(String dir) {
        if (dir == null) {
            throw new NullPointerException("dir");
        }
        StringArrayOut listOut = new StringArrayOut();
        try {
            checkResult(LibIMobileDevice.afc_read_directory(getRef(), dir, listOut));
            StringArray list = listOut.getValue();
            ArrayList<String> result = new ArrayList<String>();
            if (list != null) {
                for (int i = 0;; i++) {
                    String s = list.get(i);
                    if (s == null) {
                        break;
                    }
                    result.add(s);
                }
            }
            return result.toArray(new String[result.size()]);
        } finally {
            LibIMobileDevice.delete_StringArray_values_z(listOut.getValue());
            listOut.delete();
        }
    }
    
    /**
     * Retrieves device information. The information returned is the device 
     * model as well as the free space, the total capacity and blocksize on the 
     * accessed disk partition.
     * 
     * @return the device info as key-value pairs. Possible keys are:
     * {@link #DEVICE_INFO_KEY_MODEL}, {@link #DEVICE_INFO_KEY_FS_FREE_BYTES},
     * {@link #DEVICE_INFO_KEY_FS_TOTAL_BYTES}, 
     * {@link #DEVICE_INFO_KEY_FS_BLOCK_SIZE}.
     */
    public Map<String, String> getDeviceInfo() {
        StringArrayOut infosOut = new StringArrayOut();
        try {
            checkResult(LibIMobileDevice.afc_get_device_info(getRef(), infosOut));
            StringArray list = infosOut.getValue();
            Map<String, String> result = new TreeMap<String, String>();
            if (list != null) {
                int i = 0;
                while (true) {
                    String key = list.get(i++);
                    if (key == null) {
                        break;
                    }
                    String value = list.get(i++);
                    if (value == null) {
                        break;
                    }
                    result.put(key, value);
                }
            }
            return result;
        } finally {
            LibIMobileDevice.delete_StringArray_values_z(infosOut.getValue());
            infosOut.delete();
        }
    }
    
    /**
     * Returns the disk partition blocksize.
     * 
     * @return the blocksize.
     * @see #getDeviceInfo()
     */
    public int getBlockSize() {
        return Integer.parseInt(getDeviceInfo().get(DEVICE_INFO_KEY_FS_BLOCK_SIZE));
    }

    /**
     * Returns the free space on the device in bytes.
     * 
     * @return the free space in bytes.
     * @see #getDeviceInfo()
     */
    public long getFreeBytes() {
        return Long.parseLong(getDeviceInfo().get(DEVICE_INFO_KEY_FS_FREE_BYTES));
    }

    /**
     * Returns the total size of the device in bytes.
     * 
     * @return the total size in bytes.
     * @see #getDeviceInfo()
     */
    public long getTotalBytes() {
        return Long.parseLong(getDeviceInfo().get(DEVICE_INFO_KEY_FS_TOTAL_BYTES));
    }
    
    /**
     * Returns the name of the device model.
     * 
     * @return the device model name.
     * @see #getDeviceInfo()
     */
    public String getModel() {
        return getDeviceInfo().get(DEVICE_INFO_KEY_MODEL);
    }
    
    /**
     * Retrieves information for a specific file or directory.
     * 
     * @param path the path of the file or directory.
     * @return the file information as key-value pairs. Possible keys are:
     * {@link #FILE_INFO_KEY_ST_BIRTHTIME}, {@link #FILE_INFO_KEY_ST_BLOCKS},
     * {@link #FILE_INFO_KEY_ST_IFMT}, {@link #FILE_INFO_KEY_ST_MTIME},
     * {@link #FILE_INFO_KEY_ST_NLINK}, {@link #FILE_INFO_KEY_ST_SIZE}.
     */
    public Map<String, String> getFileInfo(String path) {
        StringArrayOut infolistOut = new StringArrayOut();
        try {
            checkResult(LibIMobileDevice.afc_get_file_info(getRef(), path, infolistOut));
            StringArray list = infolistOut.getValue();
            Map<String, String> result = new TreeMap<String, String>();
            if (list != null) {
                int i = 0;
                while (true) {
                    String key = list.get(i++);
                    if (key == null) {
                        break;
                    }
                    String value = list.get(i++);
                    if (value == null) {
                        break;
                    }
                    result.put(key, value);
                }
            }
            return result;
        } finally {
            LibIMobileDevice.delete_StringArray_values_z(infolistOut.getValue());
            infolistOut.delete();
        }
    }
    
    /**
     * Opens a file on the device.
     * 
     * @param path the fully-qualified path of the file to open.
     * @param mode the mode to use to open the file.
     * @return the handle to the open file.
     */
    public long fileOpen(String path, AfcFileMode mode) {
        LongOut handleOut = new LongOut();
        try {
            checkResult(LibIMobileDevice.afc_file_open(getRef(), path, mode, handleOut));
            return handleOut.getValue();
        } finally {
            handleOut.delete();
        }
    }
    
    /**
     * Closes a file on the device.
     * 
     * @param handle file handle of a previously opened file.
     */
    public void fileClose(long handle) {
        checkResult(LibIMobileDevice.afc_file_close(getRef(), handle));
    }
    
    /**
     * Attempts to the read the given number of bytes from the given file.
     * 
     * @param handle file handle of a previously opened file
     * @param buffer the byte array in which to store the bytes read.
     * @param offset the initial position in {@code buffer} to store the bytes
     *               read from the file.
     * @param count the maximum number of bytes to store in {@code buffer}.
     * @return the number of bytes actually read or -1 if the end of the stream
     *         has been reached.
     */
    public int fileRead(long handle, byte[] buffer, int offset, int count) {
        if ((offset | count) < 0 || offset > buffer.length || buffer.length - offset < count) {
            throw new ArrayIndexOutOfBoundsException("length=" + buffer.length 
                    + "; regionStart=" + offset + "; regionLength=" + count);
        }
        
        if (count == 0) {
            return 0;
        }
        
        byte[] data = buffer;
        if (offset > 0) {
            data = new byte[count];
        }
        IntOut bytesReadOut = new IntOut();
        try {
            checkResult(LibIMobileDevice.afc_file_read(getRef(), handle, data, count, bytesReadOut));
            int bytesRead = bytesReadOut.getValue();
            if (bytesRead == 0) {
                // Assume EOF reached.
                return -1;
            }
            if (data != buffer) {
                System.arraycopy(data, 0, buffer, offset, bytesRead);
            }
            return bytesRead;
        } finally {
            bytesReadOut.delete();
        }
    }
    
    /**
     * Writes a given number of bytes to a file.
     * 
     * @param handle file handle of previously opened file. 
     * @param buffer the buffer to be written.
     * @param offset the start position in {@code buffer} from where to get bytes.
     * @param count the number of bytes from {@code buffer} to write to the file.
     * @return the number of bytes actually written to the file.
     */
    public int fileWrite(long handle, byte[] buffer, int offset, int count) {
        if ((offset | count) < 0 || offset > buffer.length || buffer.length - offset < count) {
            throw new ArrayIndexOutOfBoundsException("length=" + buffer.length 
                    + "; regionStart=" + offset + "; regionLength=" + count);
        }
        
        if (count == 0) {
            return 0;
        }
        
        byte[] data = buffer;
        if (offset > 0) {
            data = new byte[count];
            System.arraycopy(buffer, offset, data, 0, count);
        }
        IntOut bytesWrittenOut = new IntOut();
        try {
            checkResult(LibIMobileDevice.afc_file_write(getRef(), handle, data, count, bytesWrittenOut));
            int bytesWritten = bytesWrittenOut.getValue();
            return bytesWritten;
        } finally {
            bytesWrittenOut.delete();
        }
    }

    /**
     * Deletes a file or an empty directory.
     * 
     * @param path the fully-qualified path to delete.
     */
    public void removePath(String path) {
        removePath(path, false);
    }
    
    /**
     * Deletes a file or a directory hierarchy.
     * 
     * @param path the fully-qualified path to delete.
     * @param recurse if <code>true</code> non-empty directories will be 
     *        deleted recursively.
     */
    public void removePath(String path, boolean recurse) {
        if (!recurse) {
            checkResult(LibIMobileDevice.afc_remove_path(getRef(), path));
        } else {
            int rc = LibIMobileDevice.afc_remove_path(getRef(), path);
            if (rc == LibIMobileDeviceConstants.AFC_E_DIR_NOT_EMPTY) {
                for (String child : readDirectory(path)) {
                    if (".".equals(child) || "..".equals(child)) {
                        continue;
                    }
                    removePath(stripDirSep(path) + "/" + child, true);
                }
                rc = LibIMobileDevice.afc_remove_path(getRef(), path);
            }
            checkResult(rc);
        }
    }

    /**
     * Renames a file or directory on the device.
     * 
     * @param from the fully-qualified path of the file or directory to rename.
     * @param to the fully-qualified path the file or directory should be 
     *           renamed to.
     */
    public void renamePath(String from, String to) {
        checkResult(LibIMobileDevice.afc_rename_path(getRef(), from, to));
    }

    /**
     * Creates a directory on the device. Does nothing if the directory already
     * exists. Also creates parent directories recursively.
     * 
     * @param dir the fully-qualified path of the directory to create.
     */
    public void makeDirectory(String dir) {
        checkResult(LibIMobileDevice.afc_make_directory(getRef(), dir));
    }

    /**
     * Creates a hard link or symbolic link on the device. 
     * 
     * @param type the type of link to create.
     * @param target the absolute or relative path of the link target.
     * @param source the fully-qualified path where the link will be created.
     */
    public void makeLink(AfcLinkType type, String target, String source) {
        checkResult(LibIMobileDevice.afc_make_link(getRef(), type, target, source));
    }

    private String stripDirSep(String s) {
        int end = s.length();
        while (end > 0 && s.charAt(end - 1) == '/') {
            end--;
        }
        return s.substring(0, end);
    }
    
    private String toAbsoluteDevicePath(String root, Path path) {
        String child = toRelativeDevicePath(path);
        return stripDirSep(root) + (child.length() > 0 ? "/" + toRelativeDevicePath(path) : "");
    }

    private String toRelativeDevicePath(Path path) {
        StringBuilder sb = new StringBuilder();
        int count = path.getNameCount();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                sb.append('/');
            }
            sb.append(path.getName(i));
        }
        return sb.toString();
    }

    /**
     * Uploads a local file or directory to the device.
     * 
     * @param localFile the file or directory to upload.
     * @param targetPath the path of the directory on the device where to place 
     *                   the uploaded files.
     */
    public void upload(File localFile, final String targetPath) throws IOException {
        upload(localFile, targetPath, null);
    }
    
    /**
     * Uploads a local file or directory to the device.
     * 
     * @param localFile the file or directory to upload.
     * @param targetPath the path of the directory on the device where to place 
     *                   the uploaded files.
     * @param callback callback which will receive progress and status updates.
     *                 If <code>null</code> no progress will be reported.
     */
    public void upload(File localFile, final String targetPath, 
            final UploadProgressCallback callback) throws IOException {
        
        makeDirectory(targetPath);
        final Path root = localFile.toPath().getParent();

        // 64k seems to be a good buffer size. If smaller we will not get
        // acceptable write speeds.
        final byte[] buffer = new byte[64 * 1024];

        class FileCounterVisitor extends SimpleFileVisitor<Path> {
            int count;
            @Override
            public FileVisitResult preVisitDirectory(Path dir,
                    BasicFileAttributes attrs) throws IOException {
                count++;
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFile(Path file,
                    BasicFileAttributes attrs) throws IOException {
                count++;
                return FileVisitResult.CONTINUE;
            }
        }
        FileCounterVisitor visitor = new FileCounterVisitor();
        if (callback != null) {
            Files.walkFileTree(localFile.toPath(), visitor);
        }
        
        try {
            final int fileCount = visitor.count;
            Files.walkFileTree(localFile.toPath(), new SimpleFileVisitor<Path>() {
                int filesUploaded = 0;
                private void reportProgress(Path path) {
                    if (callback != null) {
                        callback.progress(path.toFile(), 100 * filesUploaded / fileCount);                    
                    }
                    filesUploaded++;
                }
                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                        BasicFileAttributes attrs) throws IOException {
    
                    reportProgress(dir);
                    String deviceDir = toAbsoluteDevicePath(targetPath, root.relativize(dir));
                    makeDirectory(deviceDir);
                    
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFile(Path file,
                        BasicFileAttributes attrs) throws IOException {
    
                    reportProgress(file);
                    String deviceFile = toAbsoluteDevicePath(targetPath, root.relativize(file));
                    if (Files.isSymbolicLink(file)) {
                        Path linkTargetPath = Files.readSymbolicLink(file);
                        makeLink(AfcLinkType.AFC_SYMLINK, toRelativeDevicePath(linkTargetPath), deviceFile);
                    } else if (Files.isRegularFile(file, LinkOption.NOFOLLOW_LINKS)) {
                        long fd = fileOpen(deviceFile, AfcFileMode.AFC_FOPEN_WRONLY);
                        try (InputStream is = Files.newInputStream(file)) {
                            int n = 0;
                            while ((n = is.read(buffer)) != -1) {
                                fileWrite(fd, buffer, 0, n);
                            }
                        } finally {
                            fileClose(fd);
                        }
                    }
                    
                    return FileVisitResult.CONTINUE;
                }
            });
            
            if (callback != null) {
                callback.success();
            }
        } catch (IOException e) {
            if (callback != null) {
                callback.error(e.getMessage());
            }
            throw e;
        } catch (LibIMobileDeviceException e) {
            if (callback != null) {
                callback.error(e.getMessage());
            }
            throw e;
        }
    }
    
    protected AfcClientRef getRef() {
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
        LibIMobileDevice.afc_client_free(ref);
        ref = null;
    }
    
    @Override
    public void close() throws Exception {
        dispose();
    }
    
    private static void checkResult(int result) {
        switch (result) {
        case AFC_E_SUCCESS: return;
        case AFC_E_UNKNOWN_ERROR: throw new LibIMobileDeviceException(result, "AFC_E_UNKNOWN_ERROR");
        case AFC_E_OP_HEADER_INVALID: throw new LibIMobileDeviceException(result, "AFC_E_OP_HEADER_INVALID");
        case AFC_E_NO_RESOURCES: throw new LibIMobileDeviceException(result, "AFC_E_NO_RESOURCES");
        case AFC_E_READ_ERROR: throw new LibIMobileDeviceException(result, "AFC_E_READ_ERROR");
        case AFC_E_WRITE_ERROR: throw new LibIMobileDeviceException(result, "AFC_E_WRITE_ERROR");
        case AFC_E_UNKNOWN_PACKET_TYPE: throw new LibIMobileDeviceException(result, "AFC_E_UNKNOWN_PACKET_TYPE");
        case AFC_E_INVALID_ARG: throw new LibIMobileDeviceException(result, "AFC_E_INVALID_ARG");
        case AFC_E_OBJECT_NOT_FOUND: throw new LibIMobileDeviceException(result, "AFC_E_OBJECT_NOT_FOUND");
        case AFC_E_OBJECT_IS_DIR: throw new LibIMobileDeviceException(result, "AFC_E_OBJECT_IS_DIR");
        case AFC_E_PERM_DENIED: throw new LibIMobileDeviceException(result, "AFC_E_PERM_DENIED");
        case AFC_E_SERVICE_NOT_CONNECTED: throw new LibIMobileDeviceException(result, "AFC_E_SERVICE_NOT_CONNECTED");
        case AFC_E_OP_TIMEOUT: throw new LibIMobileDeviceException(result, "AFC_E_OP_TIMEOUT");
        case AFC_E_TOO_MUCH_DATA: throw new LibIMobileDeviceException(result, "AFC_E_TOO_MUCH_DATA");
        case AFC_E_END_OF_DATA: throw new LibIMobileDeviceException(result, "AFC_E_END_OF_DATA");
        case AFC_E_OP_NOT_SUPPORTED: throw new LibIMobileDeviceException(result, "AFC_E_OP_NOT_SUPPORTED");
        case AFC_E_OBJECT_EXISTS: throw new LibIMobileDeviceException(result, "AFC_E_OBJECT_EXISTS");
        case AFC_E_OBJECT_BUSY: throw new LibIMobileDeviceException(result, "AFC_E_OBJECT_BUSY");
        case AFC_E_NO_SPACE_LEFT: throw new LibIMobileDeviceException(result, "AFC_E_NO_SPACE_LEFT");
        case AFC_E_OP_WOULD_BLOCK: throw new LibIMobileDeviceException(result, "AFC_E_OP_WOULD_BLOCK");
        case AFC_E_IO_ERROR: throw new LibIMobileDeviceException(result, "AFC_E_IO_ERROR");
        case AFC_E_OP_INTERRUPTED: throw new LibIMobileDeviceException(result, "AFC_E_OP_INTERRUPTED");
        case AFC_E_OP_IN_PROGRESS: throw new LibIMobileDeviceException(result, "AFC_E_OP_IN_PROGRESS");
        case AFC_E_INTERNAL_ERROR: throw new LibIMobileDeviceException(result, "AFC_E_INTERNAL_ERROR");
        case AFC_E_MUX_ERROR: throw new LibIMobileDeviceException(result, "AFC_E_MUX_ERROR");
        case AFC_E_NO_MEM: throw new LibIMobileDeviceException(result, "AFC_E_NO_MEM");
        case AFC_E_NOT_ENOUGH_DATA: throw new LibIMobileDeviceException(result, "AFC_E_NOT_ENOUGH_DATA");
        case AFC_E_DIR_NOT_EMPTY: throw new LibIMobileDeviceException(result, "AFC_E_DIR_NOT_EMPTY");
        default: throw new LibIMobileDeviceException(result);
        }
    }
    
    public interface UploadProgressCallback {
        /**
         * Reports the progress of an upload to a device.
         * 
         * @param path the path currently being uploaded.
         * @param percentComplete the progress in percent.
         */
        void progress(File path, int percentComplete);
        /**
         * Called once the upload has been completed successfully.
         */
        void success();
        /**
         * Called if the upload fails.
         * 
         * @param message the error message.
         */
        void error(String message);
    }
    
    private void list(String path, boolean recurse) {
        list(path, stripDirSep(path).replaceAll(".*?([^/]+)$", "$1"), "", recurse, new PrintWriter(System.out));
    }

    private void list(String path, String filename, String indent, boolean recurse, PrintWriter out) {
        Map<String, String> info = getFileInfo(path);
        DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
        long birthTime = Long.parseLong(info.get(FILE_INFO_KEY_ST_BIRTHTIME)) / 1000 / 1000;
        long mtime = Long.parseLong(info.get(FILE_INFO_KEY_ST_MTIME)) / 1000 / 1000;
        long size = Long.parseLong(info.get(AfcClient.FILE_INFO_KEY_ST_SIZE));
        if ("S_IFDIR".equals(info.get(AfcClient.FILE_INFO_KEY_ST_IFMT))) {
            out.format("%s %s %9d (%s)\t%s%s/\n", 
                    df.format(new Date(birthTime)),
                    df.format(new Date(mtime)),
                    size,
                    info.get(AfcClient.FILE_INFO_KEY_ST_IFMT),
                    indent, filename);
            out.flush();
            for (String f : readDirectory(path)) {
                if (f.equals("..") || f.equals(".")) {
                    continue;
                }
                if (recurse) {
                    String childPath = path + "/" + f;
                    list(childPath, f, indent  + "  ", recurse, out);
                }
            }
        } else if ("S_IFLNK".equals(info.get(AfcClient.FILE_INFO_KEY_ST_IFMT))) {
            out.format("%s %s %9d (%s)\t%s%s -> %s\n", 
                    df.format(new Date(birthTime)),
                    df.format(new Date(mtime)),
                    size,
                    info.get(AfcClient.FILE_INFO_KEY_ST_IFMT),
                    indent, filename, info.get(AfcClient.FILE_INFO_KEY_LINK_TARGET));
            out.flush();
        } else {
            out.format("%s %s %9d (%s)\t%s%s\n", 
                    df.format(new Date(birthTime)),
                    df.format(new Date(mtime)),
                    size,
                    info.get(AfcClient.FILE_INFO_KEY_ST_IFMT),
                    indent, filename);
            out.flush();
        }
    }
    
    private static void printUsageAndExit() {
        System.err.println(AfcClient.class.getName() + " [deviceid] <action> ...");
        System.err.println("  Actions:");
        System.err.println("    deviceinfo       Prints device file system information.");
        System.err.println("    rm [-f] <path>   Deletes <path> from the device. Deletes non-empty dirs if -f is specified.");
        System.err.println("    ls [-r] <path>   Lists the contents of the specified dir.");
        System.err.println("    mkdir <dir>      Creates the <dir> on the device.");
        System.err.println("    mv <from> <to>   Moves (renames) the remote path <from> to <to>.");
        System.err.println("    upload <localpath> <remotedir>\n" 
                         + "                   Uploads the local file or dir at <localpath> to the remote dir <remotedir>.");
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
        
            if (!action.matches("deviceinfo|rm|ls|mkdir|mv|upload")) {
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
                    try (AfcClient client = new AfcClient(device, service)) {
                        boolean recurse = false;
                        switch (action) {
                        case "deviceinfo":
                            System.out.println(client.getDeviceInfo());
                            break;
                        case "rm":
                            if ("-r".equals(args[index])) {
                                recurse = true;
                                index++;
                            }
                            client.removePath(args[index], recurse);
                            break;
                        case "ls":
                            if ("-r".equals(args[index])) {
                                recurse = true;
                                index++;
                            }
                            client.list(args[index], recurse);
                            break;
                        case "mkdir":
                            client.makeDirectory(args[index]);
                            break;
                        case "mv":
                            client.renamePath(args[index++], args[index]);
                            break;
                        case "upload":
                            client.upload(new File(args[index++]), args[index], new UploadProgressCallback() {
                                public void progress(File path, int percentComplete) {
                                    System.out.format("[%3d%%] Uploading %s\n", percentComplete, path);
                                }
                                public void success() {
                                    System.out.format("[100%%] Upload done!\n");
                                }
                                public void error(String message) {
                                    System.out.format("Error: %s\n", message);
                                }
                            });
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
