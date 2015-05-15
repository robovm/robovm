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
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMediaQuery/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MPMediaQueryPtr extends Ptr<MPMediaQuery, MPMediaQueryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MPMediaQuery.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected MPMediaQuery(SkipInit skipInit) { super(skipInit); }
    public MPMediaQuery(NSSet<MPMediaPredicate> filterPredicates) { super((SkipInit) null); initObject(init(filterPredicates)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "filterPredicates")
    public native NSSet<MPMediaPredicate> getFilterPredicates();
    @Property(selector = "setFilterPredicates:")
    public native void setFilterPredicates(NSSet<MPMediaPredicate> v);
    @Property(selector = "items")
    public native NSArray<MPMediaItem> getItems();
    @Property(selector = "collections")
    public native NSArray<MPMediaItemCollection> getCollections();
    @Property(selector = "groupingType")
    public native MPMediaGrouping getGroupingType();
    @Property(selector = "setGroupingType:")
    public native void setGroupingType(MPMediaGrouping v);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Property(selector = "itemSections")
    public native NSArray<MPMediaQuerySection> getItemSections();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Property(selector = "collectionSections")
    public native NSArray<MPMediaQuerySection> getCollectionSections();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithFilterPredicates:")
    protected native @Pointer long init(NSSet<MPMediaPredicate> filterPredicates);
    @Method(selector = "addFilterPredicate:")
    public native void addFilterPredicate(MPMediaPredicate predicate);
    @Method(selector = "removeFilterPredicate:")
    public native void removeFilterPredicate(MPMediaPredicate predicate);
    @Method(selector = "albumsQuery")
    public static native MPMediaQuery createAlbumsQuery();
    @Method(selector = "artistsQuery")
    public static native MPMediaQuery createArtistsQuery();
    @Method(selector = "songsQuery")
    public static native MPMediaQuery createSongsQuery();
    @Method(selector = "playlistsQuery")
    public static native MPMediaQuery createPlaylistsQuery();
    @Method(selector = "podcastsQuery")
    public static native MPMediaQuery createPodcastsQuery();
    @Method(selector = "audiobooksQuery")
    public static native MPMediaQuery createAudiobooksQuery();
    @Method(selector = "compilationsQuery")
    public static native MPMediaQuery createCompilationsQuery();
    @Method(selector = "composersQuery")
    public static native MPMediaQuery createComposersQuery();
    @Method(selector = "genresQuery")
    public static native MPMediaQuery createGenresQuery();
    /*</methods>*/
}
