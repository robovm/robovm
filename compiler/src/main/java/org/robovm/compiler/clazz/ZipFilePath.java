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
package org.robovm.compiler.clazz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

/**
 *
 * @version $Id$
 */
public class ZipFilePath extends AbstractPath {
    private final ZipFile zipFile;
    
    ZipFilePath(File f, Clazzes clazzes, int index, boolean inBootclasspath) throws IOException {
        super(f, clazzes, index, inBootclasspath);
        this.zipFile = new ZipFile(f);
    }
    
    @Override
    protected Set<Clazz> doListClasses() {
        Set<Clazz> s = new TreeSet<Clazz>();
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
            super(ZipFilePath.this.clazzes, entry.getName(), ZipFilePath.this);
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
