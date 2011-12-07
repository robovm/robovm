/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.clazz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

/**
 *
 * @version $Id$
 */
public class ZipFilePath implements Path {
    private final File file;
    private final Clazzes clazzes;
    private final ZipFile zipFile;
    private final int index;
    private List<Clazz> clazzList = null;
    
    ZipFilePath(File f, Clazzes clazzes, int index) throws IOException {
        this.file = f;
        this.clazzes = clazzes;
        this.zipFile = new ZipFile(f);
        this.index = index;
    }
    
    public boolean isInBootClasspath() {
        return clazzes.isInBootClasspath(this);
    }
    
    public int getIndex() {
        return index;
    }
    
    public File getFile() {
        return file;
    }
    
    private Clazz createClazz(final ZipEntry entry) {
        return new Clazz(entry.getName(), this) {
            byte[] bytes = null;
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
        };
    }
    
    public List<Clazz> list() {
        if (clazzList == null) {
            clazzList = new LinkedList<Clazz>();
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    clazzList.add(createClazz(entry));
                }
            }
        }                
        return Collections.unmodifiableList(clazzList);
    }
    
    public boolean hasChangedSince(long timestamp) {
        return file.lastModified() > timestamp;
    }    
    
    @Override
    public String toString() {
        return file.toString();
    }
}
