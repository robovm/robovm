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
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNLabeledValue/*</name>*/ <T>
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CNLabeledValuePtr extends Ptr<CNLabeledValue, CNLabeledValuePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNLabeledValue.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNLabeledValue() {}
    protected CNLabeledValue(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    public CNLabeledValue(String label, T value) {
        super((SkipInit)null);
        initObject(init(label, convertValue(value)));
    }
    public CNLabeledValue(CNLabel label, T value) {
        super((SkipInit)null);
        initObject(init(label.value().toString(), convertValue(value)));
    }
    
    private static NSObject convertValue(Object value) {
        if (value == null) {
            throw new NullPointerException("value");
        }
        if (value instanceof NSObject) {
            return (NSObject) value;
        } else if (value instanceof String) {
            return new NSString((String) value);
        } else {
            throw new IllegalArgumentException("unsupported value type: " + value.getClass());
        }
    }

    public T getValue() {
        NSObject value = getValue0();
        if (value == null) {
            return null;
        } else if (value instanceof NSString) {
            return (T) value.toString();
        }
        return (T) value;
    }
    public CNLabeledValue<T> setValue(T value) {
        return setValue(convertValue(value));
    }
    public CNLabeledValue<T> setLabelAndValue(String label, T value) {
        return setLabelAndValue(label, convertValue(value));
    }
    
    public CNLabeledValue<T> setLabel(CNLabel label) {
        return setLabel(label.value().toString());
    }
    public CNLabeledValue<T> setLabelAndValue(CNLabel label, T value) {
        return setLabelAndValue(label.value().toString(), value);
    }
    public static String getLocalizedLabel(CNLabel label) {
        return getLocalizedLabel(label.value().toString());
    }
    /*<properties>*/
    @Property(selector = "identifier")
    public native String getIdentifier();
    @Property(selector = "label")
    public native String getLabel();
    @Property(selector = "value")
    private native NSObject getValue0();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithLabel:value:")
    private native @Pointer long init(String label, NSObject value);
    @Method(selector = "labeledValueBySettingLabel:")
    public native CNLabeledValue<T> setLabel(String label);
    @Method(selector = "labeledValueBySettingValue:")
    private native CNLabeledValue<T> setValue(NSObject value);
    @Method(selector = "labeledValueBySettingLabel:value:")
    private native CNLabeledValue<T> setLabelAndValue(String label, NSObject value);
    @Method(selector = "localizedStringForLabel:")
    public static native String getLocalizedLabel(String label);
    /*</methods>*/
}
