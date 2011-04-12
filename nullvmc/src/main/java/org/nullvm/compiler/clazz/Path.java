/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.clazz;

import java.io.File;
import java.util.List;

/**
 *
 * @version $Id$
 */
public interface Path {
    int getIndex();
    File getFile();
    List<Clazz> list();
}
