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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSKeyedUnarchiverDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSKeyedUnarchiverDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("unarchiver:cannotDecodeObjectOfClassName:originalClasses:")
    public ObjCClass unarchiver$cannotDecodeObjectOfClassName$originalClasses$(NSKeyedUnarchiver unarchiver, String name, NSArray<?> classNames) { throw new UnsupportedOperationException(); }
    @NotImplemented("unarchiver:didDecodeObject:")
    public NSObject unarchiver$didDecodeObject$(NSKeyedUnarchiver unarchiver, NSObject object) { throw new UnsupportedOperationException(); }
    @NotImplemented("unarchiver:willReplaceObject:withObject:")
    public void unarchiver$willReplaceObject$withObject$(NSKeyedUnarchiver unarchiver, NSObject object, NSObject newObject) { throw new UnsupportedOperationException(); }
    @NotImplemented("unarchiverWillFinish:")
    public void unarchiverWillFinish$(NSKeyedUnarchiver unarchiver) { throw new UnsupportedOperationException(); }
    @NotImplemented("unarchiverDidFinish:")
    public void unarchiverDidFinish$(NSKeyedUnarchiver unarchiver) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
