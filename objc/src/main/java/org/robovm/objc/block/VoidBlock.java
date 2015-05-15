/*
 * Copyright (C) 2013 RoboVM AB
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
package org.robovm.objc.block;

import org.robovm.objc.ObjCBlock;
import org.robovm.objc.ObjCBlock.Wrapper;
import org.robovm.rt.bro.annotation.Callback;

/**
 * Block which takes no arguments and returns no value. This is used to map the
 * Objective-C {@code void (^)(void)} block type.
 * 
 * @deprecated Use {@link Runnable} instead.
 */
@Deprecated
public interface VoidBlock {

    /**
     * Invokes this block.
     */
    void invoke();
    
    static class Callbacks {
        @Callback static void run(ObjCBlock block) {
            ((VoidBlock) block.object()).invoke();
        }
    }
    
    static class Marshaler {
        private static final Wrapper WRAPPER = new Wrapper(Callbacks.class);
        public static ObjCBlock toObjCBlock(VoidBlock o) {
            return WRAPPER.toObjCBlock(o);
        }
    }
}
