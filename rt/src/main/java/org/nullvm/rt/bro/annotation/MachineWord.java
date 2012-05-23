/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.rt.bro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @version $Id$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface MachineWord {
}
