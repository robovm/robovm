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
package org.robovm.apple.contacts;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CNLabelContactRelation/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNLabelContactRelation/*</name>*/ 
    extends /*<extends>*/CNLabel/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CNLabelContactRelation/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CNLabelContactRelation toObject(Class<CNLabelContactRelation> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CNLabelContactRelation.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CNLabelContactRelation o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CNLabelContactRelation> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CNLabelContactRelation> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CNLabelContactRelation.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CNLabelContactRelation> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CNLabelContactRelation o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Father = new CNLabelContactRelation("Father");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Mother = new CNLabelContactRelation("Mother");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Parent = new CNLabelContactRelation("Parent");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Brother = new CNLabelContactRelation("Brother");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Sister = new CNLabelContactRelation("Sister");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Child = new CNLabelContactRelation("Child");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Friend = new CNLabelContactRelation("Friend");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Spouse = new CNLabelContactRelation("Spouse");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Partner = new CNLabelContactRelation("Partner");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Assistant = new CNLabelContactRelation("Assistant");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabelContactRelation Manager = new CNLabelContactRelation("Manager");
    /*</constants>*/
    
    private static /*<name>*/CNLabelContactRelation/*</name>*/[] values = new /*<name>*/CNLabelContactRelation/*</name>*/[] {/*<value_list>*/Father, Mother, Parent, Brother, Sister, Child, Friend, Spouse, Partner, Assistant, Manager/*</value_list>*/};
    
    private CNLabelContactRelation(String getterName) {
        super(getterName);
    }
    
    public static /*<name>*/CNLabelContactRelation/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CNLabelContactRelation/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CNLabelContactRelation/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationFather", optional=true)
        public static native NSString Father();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationMother", optional=true)
        public static native NSString Mother();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationParent", optional=true)
        public static native NSString Parent();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationBrother", optional=true)
        public static native NSString Brother();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationSister", optional=true)
        public static native NSString Sister();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationChild", optional=true)
        public static native NSString Child();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationFriend", optional=true)
        public static native NSString Friend();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationSpouse", optional=true)
        public static native NSString Spouse();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationPartner", optional=true)
        public static native NSString Partner();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationAssistant", optional=true)
        public static native NSString Assistant();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelContactRelationManager", optional=true)
        public static native NSString Manager();
        /*</values>*/
    }
}
