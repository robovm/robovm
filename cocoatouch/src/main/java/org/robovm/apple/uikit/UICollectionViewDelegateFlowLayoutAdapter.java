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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollectionViewDelegateFlowLayoutAdapter/*</name>*/ 
    extends /*<extends>*/UICollectionViewDelegateAdapter/*</extends>*/ 
    /*<implements>*/implements UICollectionViewDelegateFlowLayout/*</implements>*/ {

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
    @NotImplemented("collectionView:layout:sizeForItemAtIndexPath:")
    public @ByVal CGSize getItemSize(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, NSIndexPath indexPath) { return null; }
    @NotImplemented("collectionView:layout:insetForSectionAtIndex:")
    public @ByVal UIEdgeInsets getSectionInset(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, @MachineSizedSInt long section) { return null; }
    @NotImplemented("collectionView:layout:minimumLineSpacingForSectionAtIndex:")
    public @MachineSizedFloat double getSectionMinimumLineSpacing(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, @MachineSizedSInt long section) { return 0; }
    @NotImplemented("collectionView:layout:minimumInteritemSpacingForSectionAtIndex:")
    public @MachineSizedFloat double getSectionMinimumInteritemSpacing(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, @MachineSizedSInt long section) { return 0; }
    @NotImplemented("collectionView:layout:referenceSizeForHeaderInSection:")
    public @ByVal CGSize getSectionHeaderReferenceSize(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, @MachineSizedSInt long section) { return null; }
    @NotImplemented("collectionView:layout:referenceSizeForFooterInSection:")
    public @ByVal CGSize getSectionFooterReferenceSize(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, @MachineSizedSInt long section) { return null; }
    /*</methods>*/
}
