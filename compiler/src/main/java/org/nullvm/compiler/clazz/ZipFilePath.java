/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.clazz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

/**
 *
 * @version $Id$
 */
public class ZipFilePath extends AbstractPath {
    private final ZipFile zipFile;
    
    ZipFilePath(File f, Clazzes clazzes, int index) throws IOException {
        super(f, clazzes, index);
        this.zipFile = new ZipFile(f);
    }
    
    @Override
    protected Set<Clazz> doListClasses() {
        Set<Clazz> s = new HashSet<Clazz>();
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.getName().endsWith(".class")) {
                s.add(new ZipFilePathClazz(entry));
            }
        }
        return s;
    }
    
    public boolean hasChangedSince(long timestamp) {
        return file.lastModified() > timestamp;
    }    
    
    private class ZipFilePathClazz extends Clazz {
        private final ZipEntry entry;
        private byte[] bytes = null;
        
        ZipFilePathClazz(ZipEntry entry) {
            super(clazzes, entry.getName(), ZipFilePath.this);
            this.entry = entry;
        }

        public byte[] getBytes() throws IOException {
            if (bytes == null) {
                InputStream in = null;
                try {
                    in = zipFile.getInputStream(entry);
                    bytes = IOUtils.toByteArray(in);
                } finally {
                    IOUtils.closeQuietly(in);
                }
            }
            return bytes;
        }
        
        public long lastModified() {
            return entry.getTime() == -1 ? 0 : entry.getTime();
        }
    }
}
