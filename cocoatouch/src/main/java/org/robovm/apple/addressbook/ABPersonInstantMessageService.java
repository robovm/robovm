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
package org.robovm.apple.addressbook;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AddressBook")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class ABPersonInstantMessageService 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABPersonInstantMessageService.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final ABPersonInstantMessageService Yahoo = new ABPersonInstantMessageService() {
        public NSString value() {
            return YahooValue();
        }
    };
    public static final ABPersonInstantMessageService Jabber = new ABPersonInstantMessageService() {
        public NSString value() {
            return JabberValue();
        }
    };
    public static final ABPersonInstantMessageService MSN = new ABPersonInstantMessageService() {
        public NSString value() {
            return MSNValue();
        }
    };
    public static final ABPersonInstantMessageService ICQ = new ABPersonInstantMessageService() {
        public NSString value() {
            return ICQValue();
        }
    };
    public static final ABPersonInstantMessageService AIM = new ABPersonInstantMessageService() {
        public NSString value() {
            return AIMValue();
        }
    };
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonInstantMessageService QQ = new ABPersonInstantMessageService() {
        public NSString value() {
            return QQValue();
        }
    };
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonInstantMessageService GoogleTalk = new ABPersonInstantMessageService() {
        public NSString value() {
            return GoogleTalkValue();
        }
    };
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonInstantMessageService Skype = new ABPersonInstantMessageService() {
        public NSString value() {
            return SkypeValue();
        }
    };
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonInstantMessageService Facebook = new ABPersonInstantMessageService() {
        public NSString value() {
            return FacebookValue();
        }
    };
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonInstantMessageService GaduGadu = new ABPersonInstantMessageService() {
        public NSString value() {
            return GaduGaduValue();
        }
    };
    private static ABPersonInstantMessageService[] values = new ABPersonInstantMessageService[] {Yahoo, Jabber, MSN, ICQ, AIM, QQ, GoogleTalk, Skype, Facebook, GaduGadu};

    private ABPersonInstantMessageService() {
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return null;
    }
    
    public static ABPersonInstantMessageService valueOf(NSString value) {
        for (ABPersonInstantMessageService v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ABPersonInstantMessageService/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kABPersonInstantMessageServiceYahoo", optional=true)
    protected static native NSString YahooValue();
    @GlobalValue(symbol="kABPersonInstantMessageServiceJabber", optional=true)
    protected static native NSString JabberValue();
    @GlobalValue(symbol="kABPersonInstantMessageServiceMSN", optional=true)
    protected static native NSString MSNValue();
    @GlobalValue(symbol="kABPersonInstantMessageServiceICQ", optional=true)
    protected static native NSString ICQValue();
    @GlobalValue(symbol="kABPersonInstantMessageServiceAIM", optional=true)
    protected static native NSString AIMValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonInstantMessageServiceQQ", optional=true)
    protected static native NSString QQValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonInstantMessageServiceGoogleTalk", optional=true)
    protected static native NSString GoogleTalkValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonInstantMessageServiceSkype", optional=true)
    protected static native NSString SkypeValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonInstantMessageServiceFacebook", optional=true)
    protected static native NSString FacebookValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonInstantMessageServiceGaduGadu", optional=true)
    protected static native NSString GaduGaduValue();
    /*</methods>*/
}
