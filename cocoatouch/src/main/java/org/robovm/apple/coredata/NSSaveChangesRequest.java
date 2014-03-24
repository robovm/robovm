/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.coredata;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSSaveChangesRequest/*</name>*/ 
    extends /*<extends>*/NSPersistentStoreRequest/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSSaveChangesRequestPtr extends Ptr<NSSaveChangesRequest, NSSaveChangesRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSSaveChangesRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSSaveChangesRequest() {}
    protected NSSaveChangesRequest(SkipInit skipInit) { super(skipInit); }
    public NSSaveChangesRequest(NSSet<?> insertedObjects, NSSet<?> updatedObjects, NSSet<?> deletedObjects, NSSet<?> lockedObjects) { super((SkipInit) null); initObject(initWithInsertedObjects$updatedObjects$deletedObjects$lockedObjects$(insertedObjects, updatedObjects, deletedObjects, lockedObjects)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithInsertedObjects:updatedObjects:deletedObjects:lockedObjects:")
    protected native @Pointer long initWithInsertedObjects$updatedObjects$deletedObjects$lockedObjects$(NSSet<?> insertedObjects, NSSet<?> updatedObjects, NSSet<?> deletedObjects, NSSet<?> lockedObjects);
    @Method(selector = "insertedObjects")
    public native NSSet<?> insertedObjects();
    @Method(selector = "updatedObjects")
    public native NSSet<?> updatedObjects();
    @Method(selector = "deletedObjects")
    public native NSSet<?> deletedObjects();
    @Method(selector = "lockedObjects")
    public native NSSet<?> lockedObjects();
    /*</methods>*/
}
