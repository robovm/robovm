/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.clazz;

import java.io.File;
import java.io.IOException;

/**
 *
 * @version $Id$
 */
public abstract class Clazz {
    private final String fileName;
    private final String className;
    private final String internalName;
    private final Clazzes clazzes;
    
    Clazz(String fileName, Clazzes clazzes) {
        this.fileName = fileName;
        this.className = fileName.replace(File.separatorChar, '.').substring(0, fileName.lastIndexOf(".class"));
        this.internalName = className.replace('.', '/');
        this.clazzes = clazzes;
    }
    
    public void verify() throws IOException {
        clazzes.verify(this);
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public String getClassName() {
        return className;
    }
    
    public String getInternalName() {
        return internalName;
    }
    
    @Override
    public int hashCode() {
        return fileName.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Clazz && ((Clazz) obj).fileName.equals(fileName);
    }
    
    public abstract byte[] getBytes() throws IOException;
}
