/*
 * Copyright (C) 2014 RoboVM
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
package org.robovm.objc;

/**
 * Base class for all Objective-C extension classes (categories). Note that
 * it is not possible to create new categories in Java which adds methods to
 * Objective-C classes. 
 */
public abstract class ObjCExtensions {

    protected static void initObject(ObjCObject o, long handle) {
        o.initObject(handle);
    }

}
