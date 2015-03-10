/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.watchkit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.mapkit.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("WatchKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WKInterfaceTable/*</name>*/ 
    extends /*<extends>*/WKInterfaceObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class WKInterfaceTablePtr extends Ptr<WKInterfaceTable, WKInterfaceTablePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(WKInterfaceTable.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public WKInterfaceTable() {}
    protected WKInterfaceTable(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "numberOfRows")
    public native @MachineSizedSInt long getNumberOfRows();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setRowTypes:")
    public native void setRowTypes(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> rowTypes);
    @Method(selector = "setNumberOfRows:withRowType:")
    public native void setNumberOfRows(@MachineSizedSInt long numberOfRows, String rowType);
    @Method(selector = "rowControllerAtIndex:")
    public native NSObject getRowController(@MachineSizedSInt long index);
    @Method(selector = "insertRowsAtIndexes:withRowType:")
    public native void insertRows(NSIndexSet rows, String rowType);
    @Method(selector = "removeRowsAtIndexes:")
    public native void removeRows(NSIndexSet rows);
    @Method(selector = "scrollToRowAtIndex:")
    public native void scrollToRow(@MachineSizedSInt long index);
    /*</methods>*/
}
