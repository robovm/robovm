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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFetchedResultsControllerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSFetchedResultsControllerDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("controller:didChangeObject:atIndexPath:forChangeType:newIndexPath:")
    public void didChangeObject(NSFetchedResultsController controller, NSObject anObject, NSIndexPath indexPath, NSFetchedResultsChangeType type, NSIndexPath newIndexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("controller:didChangeSection:atIndex:forChangeType:")
    public void didChangeSection(NSFetchedResultsController controller, NSFetchedResultsSectionInfo sectionInfo, @MachineSizedUInt long sectionIndex, NSFetchedResultsChangeType type) { throw new UnsupportedOperationException(); }
    @NotImplemented("controllerWillChangeContent:")
    public void willChangeContent(NSFetchedResultsController controller) { throw new UnsupportedOperationException(); }
    @NotImplemented("controllerDidChangeContent:")
    public void didChangeContent(NSFetchedResultsController controller) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("controller:sectionIndexTitleForSectionName:")
    public String getSectionIndexTitle(NSFetchedResultsController controller, String sectionName) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
