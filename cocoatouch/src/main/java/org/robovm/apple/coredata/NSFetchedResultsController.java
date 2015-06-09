/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFetchedResultsController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSFetchedResultsControllerPtr extends Ptr<NSFetchedResultsController, NSFetchedResultsControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFetchedResultsController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFetchedResultsController() {}
    protected NSFetchedResultsController(SkipInit skipInit) { super(skipInit); }
    public NSFetchedResultsController(NSFetchRequest fetchRequest, NSManagedObjectContext context, String sectionNameKeyPath, String name) { super((SkipInit) null); initObject(init(fetchRequest, context, sectionNameKeyPath, name)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "fetchRequest")
    public native NSFetchRequest getFetchRequest();
    @Property(selector = "managedObjectContext")
    public native NSManagedObjectContext getManagedObjectContext();
    @Property(selector = "sectionNameKeyPath")
    public native String getSectionNameKeyPath();
    @Property(selector = "cacheName")
    public native String getCacheName();
    @Property(selector = "delegate")
    public native NSFetchedResultsControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSFetchedResultsControllerDelegate v);
    @Property(selector = "fetchedObjects")
    public native NSArray<NSManagedObject> getFetchedObjects();
    @Property(selector = "sectionIndexTitles")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSectionIndexTitles();
    @Property(selector = "sections")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<NSFetchedResultsSectionInfo> getSections();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithFetchRequest:managedObjectContext:sectionNameKeyPath:cacheName:")
    protected native @Pointer long init(NSFetchRequest fetchRequest, NSManagedObjectContext context, String sectionNameKeyPath, String name);
    public boolean performFetch() throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = performFetch(ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "performFetch:")
    private native boolean performFetch(NSError.NSErrorPtr error);
    @Method(selector = "objectAtIndexPath:")
    public native NSObject getObjectAtIndexPath(NSIndexPath indexPath);
    @Method(selector = "indexPathForObject:")
    public native NSIndexPath getIndexPathForObject(NSObject object);
    @Method(selector = "sectionIndexTitleForSectionName:")
    public native String getSectionIndexTitleForSectionName(String sectionName);
    @Method(selector = "sectionForSectionIndexTitle:atIndex:")
    public native @MachineSizedSInt long getSectionForIndexTitle(String title, @MachineSizedSInt long sectionIndex);
    @Method(selector = "deleteCacheWithName:")
    public static native void deleteCache(String name);
    /*</methods>*/
}
