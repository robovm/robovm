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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;

/**
 *
 * @version $Id$
 */
public class DirectoryPath extends AbstractPath {
    
    DirectoryPath(File dir, Clazzes clazzes, int index, boolean inBootclasspath) {
        super(dir, clazzes, index, inBootclasspath);
    }

    @Override
    protected Set<Clazz> doListClasses() {
        Set<Clazz> s = new TreeSet<Clazz>();
        for (File f : listClassFiles()) {
            s.add(new DirectoryPathClazz(f));
        }
        return s;
    }
    
    @SuppressWarnings("unchecked")
    private List<File> listClassFiles() {
        List<File> files = new ArrayList<File>(
                (Collection<File>) FileUtils.listFiles(file, new String[] {"class"}, true));
        Collections.sort(files);
        return files;
    }
    
    private boolean hasChangedSince(File dir, long timestamp) {
        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                if (f.lastModified() > timestamp) {
                    return true;
                }
            } else {
                if (hasChangedSince(f, timestamp)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean hasChangedSince(long timestamp) {
        return hasChangedSince(file, timestamp);
    }
    
    private class DirectoryPathClazz extends Clazz {
        private final File f;
        private byte[] bytes = null;
        
        DirectoryPathClazz(File f) {
            super(DirectoryPath.this.clazzes, 
                    f.getAbsolutePath().substring(file.getAbsolutePath().length() + 1), 
                    DirectoryPath.this);
            this.f = f;
        }

        public byte[] getBytes() throws IOException {
            if (bytes == null) {
                bytes = FileUtils.readFileToByteArray(f);
            }
            return bytes;
        }
        
        public long lastModified() {
            return f.lastModified();
        }
    }
}
