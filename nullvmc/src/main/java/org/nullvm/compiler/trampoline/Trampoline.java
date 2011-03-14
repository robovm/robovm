/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.trampoline;


/**
 *
 * @version $Id$
 */
public abstract class Trampoline implements Comparable<Trampoline> {
    protected final String targetClass;

    protected Trampoline(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getTargetClass() {
        return targetClass;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((targetClass == null) ? 0 : targetClass.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Trampoline other = (Trampoline) obj;
        if (targetClass == null) {
            if (other.targetClass != null) {
                return false;
            }
        } else if (!targetClass.equals(other.targetClass)) {
            return false;
        }
        return true;
    }

    public int compareTo(Trampoline o) {
        int c = this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
        if (c != 0) {
            return c;
        }
        return targetClass.compareTo(o.targetClass);
    }
}
