/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
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
    
    DirectoryPath(File dir, Clazzes clazzes, int index) {
        super(dir, clazzes, index);
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
            super(clazzes, 
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
