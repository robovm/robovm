/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.cocoatouch.coredata.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorControllerDelegate_ProtocolReference/Reference/Reference.html">UIVideoEditorControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 3.1 and later.
 * </div>
 */
public interface /*<name>*/ UIVideoEditorControllerDelegate /*</name>*/ /*<implements>*/ extends UINavigationControllerDelegate, NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorControllerDelegate_ProtocolReference/Reference/Reference.html#//apple_ref/occ/intfm/UIVideoEditorControllerDelegate/videoEditorControllerDidCancel:">- (void)videoEditorControllerDidCancel:(UIVideoEditorController *)editor</a>
     * @since Available in iOS 3.1 and later.
     */
    void didCancel(UIVideoEditorController editor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorControllerDelegate_ProtocolReference/Reference/Reference.html#//apple_ref/occ/intfm/UIVideoEditorControllerDelegate/videoEditorController:didFailWithError:">- (void)videoEditorController:(UIVideoEditorController *)editor didFailWithError:(NSError *)error</a>
     * @since Available in iOS 3.1 and later.
     */
    void didFail(UIVideoEditorController editor, NSError error);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorControllerDelegate_ProtocolReference/Reference/Reference.html#//apple_ref/occ/intfm/UIVideoEditorControllerDelegate/videoEditorController:didSaveEditedVideoToPath:">- (void)videoEditorController:(UIVideoEditorController *)editor
    didSaveEditedVideoToPath:(NSString *)editedVideoPath</a>
     * @since Available in iOS 3.1 and later.
     */
    void didSave(UIVideoEditorController editor, String editedVideoPath);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends UINavigationControllerDelegate.Adapter implements UIVideoEditorControllerDelegate {
        @NotImplemented("videoEditorControllerDidCancel:") public void didCancel(UIVideoEditorController editor) { throw new UnsupportedOperationException(); }
        @NotImplemented("videoEditorController:didFailWithError:") public void didFail(UIVideoEditorController editor, NSError error) { throw new UnsupportedOperationException(); }
        @NotImplemented("videoEditorController:didSaveEditedVideoToPath:") public void didSave(UIVideoEditorController editor, String editedVideoPath) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("videoEditorControllerDidCancel:") public static void didCancel(UIVideoEditorControllerDelegate __self__, Selector __cmd__, UIVideoEditorController editor) { __self__.didCancel(editor); }
        @Callback @BindSelector("videoEditorController:didFailWithError:") public static void didFail(UIVideoEditorControllerDelegate __self__, Selector __cmd__, UIVideoEditorController editor, NSError error) { __self__.didFail(editor, error); }
        @Callback @BindSelector("videoEditorController:didSaveEditedVideoToPath:") public static void didSave(UIVideoEditorControllerDelegate __self__, Selector __cmd__, UIVideoEditorController editor, String editedVideoPath) { __self__.didSave(editor, editedVideoPath); }
    }
    /*</callbacks>*/

}
