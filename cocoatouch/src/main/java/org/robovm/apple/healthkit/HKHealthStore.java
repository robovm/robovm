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
package org.robovm.apple.healthkit;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("HealthKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKHealthStore/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 8.2 and later.
         */
        public static NSObject observeUserPreferencesDidChange(HKHealthStore object, final VoidBlock1<HKHealthStore> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UserPreferencesDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((HKHealthStore) a.getObject());
                }
            });
        }
    }
    
    /*<ptr>*/public static class HKHealthStorePtr extends Ptr<HKHealthStore, HKHealthStorePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(HKHealthStore.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public HKHealthStore() {}
    protected HKHealthStore(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @return
     * @throws NSErrorException
     */
    public NSDate getDateOfBirth() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSDate result = getDateOfBirth(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @return
     * @throws NSErrorException
     */
    public HKBiologicalSexObject getBiologicalSex() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        HKBiologicalSexObject result = getBiologicalSex(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @return
     * @throws NSErrorException
     */
    public HKBloodTypeObject getBloodType() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        HKBloodTypeObject result = getBloodType(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.2 and later.
     */
    @GlobalValue(symbol="HKUserPreferencesDidChangeNotification", optional=true)
    public static native NSString UserPreferencesDidChangeNotification();
    
    @Method(selector = "authorizationStatusForType:")
    public native HKAuthorizationStatus getAuthorizationStatusForType(HKObjectType type);
    @Method(selector = "requestAuthorizationToShareTypes:readTypes:completion:")
    public native void requestAuthorizationToTypes(NSSet<HKSampleType> typesToShare, NSSet<HKObjectType> typesToRead, @Block VoidBlock2<Boolean, NSError> completion);
    @Method(selector = "saveObject:withCompletion:")
    public native void saveObject(HKObject object, @Block VoidBlock2<Boolean, NSError> completion);
    @Method(selector = "saveObjects:withCompletion:")
    public native void saveObjects(NSArray<HKObject> objects, @Block VoidBlock2<Boolean, NSError> completion);
    @Method(selector = "deleteObject:withCompletion:")
    public native void deleteObject(HKObject object, @Block VoidBlock2<Boolean, NSError> completion);
    @Method(selector = "executeQuery:")
    public native void executeQuery(HKQuery query);
    @Method(selector = "stopQuery:")
    public native void stopQuery(HKQuery query);
    @Method(selector = "dateOfBirthWithError:")
    protected native NSDate getDateOfBirth(NSError.NSErrorPtr error);
    @Method(selector = "biologicalSexWithError:")
    protected native HKBiologicalSexObject getBiologicalSex(NSError.NSErrorPtr error);
    @Method(selector = "bloodTypeWithError:")
    protected native HKBloodTypeObject getBloodType(NSError.NSErrorPtr error);
    @Method(selector = "isHealthDataAvailable")
    public static native boolean isHealthDataAvailable();
    @Method(selector = "addSamples:toWorkout:completion:")
    public native void addSamplesToWorkout(NSArray<HKSample> samples, HKWorkout workout, @Block VoidBlock2<Boolean, NSError> completion);
    @Method(selector = "enableBackgroundDeliveryForType:frequency:withCompletion:")
    public native void enableBackgroundDeliveryForType(HKObjectType type, HKUpdateFrequency frequency, @Block VoidBlock2<Boolean, NSError> completion);
    @Method(selector = "disableBackgroundDeliveryForType:withCompletion:")
    public native void disableBackgroundDeliveryForType(HKObjectType type, @Block VoidBlock2<Boolean, NSError> completion);
    @Method(selector = "disableAllBackgroundDeliveryWithCompletion:")
    public native void disableAllBackgroundDelivery(@Block VoidBlock2<Boolean, NSError> completion);
    /**
     * @since Available in iOS 8.2 and later.
     */
    @Method(selector = "preferredUnitsForQuantityTypes:completion:")
    public native void getPreferredUnitsForQuantityTypes(NSSet<HKQuantityType> quantityTypes, @Block VoidBlock2<NSDictionary<HKQuantityType, HKUnit>, NSError> completion);
    /*</methods>*/
}
