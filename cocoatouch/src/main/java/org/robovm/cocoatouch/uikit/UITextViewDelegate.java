/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

@Protocol
public interface /*<name>*/ UITextViewDelegate /*</name>*/ /*<implements>*/ extends UIScrollViewDelegate /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("textViewDidBeginEditing:") @Type("void") void didBeginEditing(@Type("UITextView *") UITextView textView);
    @Bind("textViewDidChange:") @Type("void") void didChange(@Type("UITextView *") UITextView textView);
    @Bind("textViewDidChangeSelection:") @Type("void") void didChangeSelection(@Type("UITextView *") UITextView textView);
    @Bind("textViewDidEndEditing:") @Type("void") void didEndEditing(@Type("UITextView *") UITextView textView);
    @Bind("textViewShouldBeginEditing:") @Type("BOOL") boolean shouldBeginEditing(@Type("UITextView *") UITextView textView);
    @Bind("textView:shouldChangeTextInRange:replacementText:") @Type("BOOL") boolean shouldChangeCharacters(@Type("UITextView *") UITextView textView, @Type("NSRange") NSRange range, @Type("NSString *") String text);
    @Bind("textViewShouldEndEditing:") @Type("BOOL") boolean shouldEndEditing(@Type("UITextView *") UITextView textView);
    /*</methods>*/

}
