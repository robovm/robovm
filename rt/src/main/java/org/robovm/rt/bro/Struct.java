/*
 * Copyright (C) 2012 RoboVM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.rt.bro;

import org.robovm.rt.VM;

/**
 *
 * @version $Id$
 */
public abstract class Struct {

    private final long handle;

    protected Struct() {
        this.handle = VM.allocateMemory(_sizeOf());
    }
    
    protected Struct(long handle) {
        this.handle = handle;
    }
    
    public final long getHandle() {
        return handle;
    }
    
    protected int _sizeOf() {
        return 0;
    }
    
    public static int sizeOf() {
        return 0;
    }
    
    public static int offsetOf(int index) {
        return 0;
    }
}
