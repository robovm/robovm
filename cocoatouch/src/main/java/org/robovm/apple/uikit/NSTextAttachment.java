/*
 * Copyright (C) 2014 Trillian AB
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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTextAttachment/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSTextAttachmentContainer/*</implements>*/ {

    /*<ptr>*/public static class NSTextAttachmentPtr extends Ptr<NSTextAttachment, NSTextAttachmentPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSTextAttachment.class); }/*</bind>*/
    /*<constants>*/
    public static final int AttachmentCharacter = 65532;
    /*</constants>*/
    /*<constructors>*/
    public NSTextAttachment() {}
    protected NSTextAttachment(SkipInit skipInit) { super(skipInit); }
    public NSTextAttachment(NSData contentData, String uti) { super((SkipInit) null); initObject(initWithData$ofType$(contentData, uti)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "contents")
    public native NSData getContents();
    @Property(selector = "setContents:")
    public native void setContents(NSData v);
    @Property(selector = "fileType")
    public native String getFileType();
    @Property(selector = "setFileType:")
    public native void setFileType(String v);
    @Property(selector = "fileWrapper")
    public native NSFileWrapper getFileWrapper();
    @Property(selector = "setFileWrapper:")
    public native void setFileWrapper(NSFileWrapper v);
    @Property(selector = "image")
    public native UIImage getImage();
    @Property(selector = "setImage:")
    public native void setImage(UIImage v);
    @Property(selector = "bounds")
    public native @ByVal CGRect getBounds();
    @Property(selector = "setBounds:")
    public native void setBounds(@ByVal CGRect v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithData:ofType:")
    protected native @Pointer long initWithData$ofType$(NSData contentData, String uti);
    @Method(selector = "imageForBounds:textContainer:characterIndex:")
    public native UIImage imageForBounds$textContainer$characterIndex$(@ByVal CGRect imageBounds, NSTextContainer textContainer, @MachineSizedUInt long charIndex);
    @Method(selector = "attachmentBoundsForTextContainer:proposedLineFragment:glyphPosition:characterIndex:")
    public native @ByVal CGRect attachmentBoundsForTextContainer$proposedLineFragment$glyphPosition$characterIndex$(NSTextContainer textContainer, @ByVal CGRect lineFrag, @ByVal CGPoint position, @MachineSizedUInt long charIndex);
    /*</methods>*/
}
