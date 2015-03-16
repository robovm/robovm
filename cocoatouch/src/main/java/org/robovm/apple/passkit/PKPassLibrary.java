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
package org.robovm.apple.passkit;

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
import org.robovm.apple.addressbook.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("PassKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKPassLibrary/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        public static NSObject observeDidChange(final VoidBlock1<PKPassLibraryNotificationArgs> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke(new PKPassLibraryNotificationArgs(a));
                }
            });
        }
    }
    
    /*<ptr>*/public static class PKPassLibraryPtr extends Ptr<PKPassLibrary, PKPassLibraryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PKPassLibrary.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PKPassLibrary() {}
    protected PKPassLibrary(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="PKPassLibraryDidChangeNotification", optional=true)
    public static native NSString DidChangeNotification();
    
    @Method(selector = "passes")
    public native NSArray<PKPass> getPasses();
    @Method(selector = "passWithPassTypeIdentifier:serialNumber:")
    public native PKPass getPass(String identifier, String serialNumber);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "passesOfType:")
    public native NSArray<PKPass> getPassesOfType(PKPassType passType);
    @Method(selector = "removePass:")
    public native void removePass(PKPass pass);
    @Method(selector = "containsPass:")
    public native boolean containsPass(PKPass pass);
    @Method(selector = "replacePassWithPass:")
    public native boolean replacePass(PKPass pass);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "addPasses:withCompletionHandler:")
    public native void addPasses(NSArray<PKPass> passes, @Block VoidBlock1<PKPassLibraryAddPassesStatus> completion);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "activatePaymentPass:withActivationData:completion:")
    public native void activatePaymentPass(PKPaymentPass paymentPass, NSData activationData, @Block VoidBlock2<Boolean, NSError> completion);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "activatePaymentPass:withActivationCode:completion:")
    public native void activatePaymentPass(PKPaymentPass paymentPass, String activationCode, @Block VoidBlock2<Boolean, NSError> completion);
    @Method(selector = "isPassLibraryAvailable")
    public static native boolean isPassLibraryAvailable();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "isPaymentPassActivationAvailable")
    public static native boolean isPaymentPassActivationAvailable();
    /*</methods>*/
}
