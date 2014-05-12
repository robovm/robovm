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

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/NSFetchedResultsControllerDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "controller:didChangeObject:atIndexPath:forChangeType:newIndexPath:")
    void controller$didChangeObject$atIndexPath$forChangeType$newIndexPath$(NSFetchedResultsController controller, NSObject anObject, NSIndexPath indexPath, NSFetchedResultsChangeType type, NSIndexPath newIndexPath);
    @Method(selector = "controller:didChangeSection:atIndex:forChangeType:")
    void controller$didChangeSection$atIndex$forChangeType$(NSFetchedResultsController controller, NSFetchedResultsSectionInfo sectionInfo, @MachineSizedUInt long sectionIndex, NSFetchedResultsChangeType type);
    @Method(selector = "controllerWillChangeContent:")
    void controllerWillChangeContent$(NSFetchedResultsController controller);
    @Method(selector = "controllerDidChangeContent:")
    void controllerDidChangeContent$(NSFetchedResultsController controller);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "controller:sectionIndexTitleForSectionName:")
    String controller$sectionIndexTitleForSectionName$(NSFetchedResultsController controller, String sectionName);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
