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
package org.robovm.apple.mediaplayer;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMediaPlaylistProperty/*</name>*/ 
    extends /*<extends>*/MPMediaEntityProperty/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(MPMediaPlaylistProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final MPMediaPlaylistProperty PlaylistPersistendID = new MPMediaPlaylistProperty("PlaylistPersistendIDValue", true);
    public static final MPMediaPlaylistProperty Name = new MPMediaPlaylistProperty("NameValue", true);
    public static final MPMediaPlaylistProperty PlaylistAttributes = new MPMediaPlaylistProperty("PlaylistAttributesValue", true);
    public static final MPMediaPlaylistProperty SeedItems = new MPMediaPlaylistProperty("SeedItemsValue", false);

    private static MPMediaPlaylistProperty[] values = new MPMediaPlaylistProperty[] {};
    
    private MPMediaPlaylistProperty(String getterName, boolean filterable) {
        super(getterName, filterable);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    protected static MPMediaPlaylistProperty findValue(NSString value) {
        for (MPMediaPlaylistProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
    }
    /*<methods>*/
    @GlobalValue(symbol="MPMediaPlaylistPropertyPersistentID", optional=true)
    protected static native NSString PlaylistPersistendIDValue();
    @GlobalValue(symbol="MPMediaPlaylistPropertyName", optional=true)
    protected static native NSString NameValue();
    @GlobalValue(symbol="MPMediaPlaylistPropertyPlaylistAttributes", optional=true)
    protected static native NSString PlaylistAttributesValue();
    @GlobalValue(symbol="MPMediaPlaylistPropertySeedItems", optional=true)
    protected static native NSString SeedItemsValue();
    /*</methods>*/
}
