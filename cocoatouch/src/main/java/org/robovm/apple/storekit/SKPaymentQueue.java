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
package org.robovm.apple.storekit;

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
import org.robovm.apple.storekit.*;
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("StoreKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKPaymentQueue/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKPaymentQueuePtr extends Ptr<SKPaymentQueue, SKPaymentQueuePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKPaymentQueue.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKPaymentQueue() {}
    protected SKPaymentQueue(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "transactions")
    public native NSArray<SKPaymentTransaction> getTransactions();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "defaultQueue")
    public static native SKPaymentQueue getDefaultQueue();
    @Method(selector = "canMakePayments")
    public static native boolean canMakePayments();
    @Method(selector = "addPayment:")
    public native void addPayment(SKPayment payment);
    @Method(selector = "restoreCompletedTransactions")
    public native void restoreCompletedTransactions();
    @Method(selector = "restoreCompletedTransactionsWithApplicationUsername:")
    public native void restoreCompletedTransactions(String username);
    @Method(selector = "finishTransaction:")
    public native void finishTransaction(SKPaymentTransaction transaction);
    @Method(selector = "startDownloads:")
    public native void startDownloads(NSArray<SKDownload> downloads);
    @Method(selector = "pauseDownloads:")
    public native void pauseDownloads(NSArray<SKDownload> downloads);
    @Method(selector = "resumeDownloads:")
    public native void resumeDownloads(NSArray<SKDownload> downloads);
    @Method(selector = "cancelDownloads:")
    public native void cancelDownloads(NSArray<SKDownload> downloads);
    @Method(selector = "addTransactionObserver:")
    public native void addTransactionObserver(SKPaymentTransactionObserver observer);
    @Method(selector = "removeTransactionObserver:")
    public native void removeTransactionObserver(SKPaymentTransactionObserver observer);
    /*</methods>*/
}
