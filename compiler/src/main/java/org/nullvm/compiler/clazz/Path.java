/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.clazz;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public interface Path {
    int getIndex();
    File getFile();
    Set<Clazz> listClasses();
    Set<Package> listPackages();
    boolean hasChangedSince(long timestamp);
    boolean isInBootClasspath();
}
