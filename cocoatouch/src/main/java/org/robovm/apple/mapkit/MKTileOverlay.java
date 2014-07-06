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
package org.robovm.apple.mapkit;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKTileOverlay/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MKOverlay/*</implements>*/ {

    /*<ptr>*/public static class MKTileOverlayPtr extends Ptr<MKTileOverlay, MKTileOverlayPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKTileOverlay.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKTileOverlay() {}
    protected MKTileOverlay(SkipInit skipInit) { super(skipInit); }
    public MKTileOverlay(String URLTemplate) { super((SkipInit) null); initObject(init(URLTemplate)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "tileSize")
    public native @ByVal CGSize getTileSize();
    @Property(selector = "setTileSize:")
    public native void setTileSize(@ByVal CGSize v);
    @Property(selector = "isGeometryFlipped")
    public native boolean isGeometryFlipped();
    @Property(selector = "setGeometryFlipped:")
    public native void setGeometryFlipped(boolean v);
    @Property(selector = "minimumZ")
    public native @MachineSizedSInt long getMinimumZ();
    @Property(selector = "setMinimumZ:")
    public native void setMinimumZ(@MachineSizedSInt long v);
    @Property(selector = "maximumZ")
    public native @MachineSizedSInt long getMaximumZ();
    @Property(selector = "setMaximumZ:")
    public native void setMaximumZ(@MachineSizedSInt long v);
    @Property(selector = "URLTemplate")
    public native String getURLTemplate();
    @Property(selector = "canReplaceMapContent")
    public native boolean isCanReplaceMapContent();
    @Property(selector = "setCanReplaceMapContent:")
    public native void setCanReplaceMapContent(boolean v);
    @Property(selector = "coordinate")
    public native @ByVal CLLocationCoordinate2D getCoordinate();
    @Property(selector = "boundingMapRect")
    public native @ByVal MKMapRect getBoundingMapRect();
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "subtitle")
    public native String getSubtitle();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithURLTemplate:")
    protected native @Pointer long init(String URLTemplate);
    @Method(selector = "URLForTilePath:")
    public native NSURL getURL(@ByVal MKTileOverlayPath path);
    @Method(selector = "loadTileAtPath:result:")
    public native void loadTile(@ByVal MKTileOverlayPath path, @Block VoidBlock2<NSData, NSError> result);
    @Method(selector = "intersectsMapRect:")
    public native boolean intersectsMapRect(@ByVal MKMapRect mapRect);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setCoordinate:")
    public native void setCoordinate(@ByVal CLLocationCoordinate2D newCoordinate);
    /*</methods>*/
    @Override
	@NotImplemented("canReplaceMapContent")
	public boolean canReplaceMapContent () {
		throw new UnsupportedOperationException();
	}
}
