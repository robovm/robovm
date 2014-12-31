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
package org.robovm.apple.cloudkit;

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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKError/*</name>*/ 
    extends /*<extends>*/NSError/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    protected CKError(SkipInit skipInit) {
        super(skipInit);
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CKError.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public CKErrorCode getErrorCode() {
        return CKErrorCode.valueOf(getCode());
    }
    
    /* Convenience methods */
    public NSDictionary<?, ?> getPartialErrors() {
        NSErrorUserInfo data = getUserInfo();
        if (data.contains(CKErrorUserInfoKey.PartialErrorsByItemID)) {
            NSDictionary<?, ?> val = (NSDictionary<?, ?>) data.get(CKErrorUserInfoKey.PartialErrorsByItemID);
            return val;
        }
        return null;
    }
    
    public CKRecord getAncestorRecord() {
        NSErrorUserInfo data = getUserInfo();
        if (data.contains(CKErrorUserInfoKey.AncestorRecord)) {
            CKRecord val = (CKRecord) data.get(CKErrorUserInfoKey.AncestorRecord);
            return val;
        }
        return null;
    }
    public CKRecord getServerRecord() {
        NSErrorUserInfo data = getUserInfo();
        if (data.contains(CKErrorUserInfoKey.ServerRecord)) {
            CKRecord val = (CKRecord) data.get(CKErrorUserInfoKey.ServerRecord);
            return val;
        }
        return null;
    }
    public CKRecord getClientRecord() {
        NSErrorUserInfo data = getUserInfo();
        if (data.contains(CKErrorUserInfoKey.ClientRecord)) {
            CKRecord val = (CKRecord) data.get(CKErrorUserInfoKey.ClientRecord);
            return val;
        }
        return null;
    }
    
    public double getRetryAfterTime() {
        NSErrorUserInfo data = getUserInfo();
        if (data.contains(CKErrorUserInfoKey.RetryAfter)) {
            NSNumber val = (NSNumber) data.get(CKErrorUserInfoKey.RetryAfter);
            return val.doubleValue();
        }
        return -1;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CKErrorDomain", optional=true)
    public static native String getClassDomain();
    /*</methods>*/
}
