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
package org.robovm.apple.foundation;

/*<imports>*/
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class VectorInt3 
    extends /*<extends>*/Struct<VectorInt3>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class VectorInt3Ptr extends Ptr<VectorInt3, VectorInt3Ptr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public VectorInt3() {}
    public VectorInt3(int x, int y, int z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getX();
    @StructMember(0) public native VectorInt3 setX(int x);
    
    @StructMember(1) public native int getY();
    @StructMember(1) public native VectorInt3 setY(int y);
    
    @StructMember(2) public native int getZ();
    @StructMember(2) public native VectorInt3 setZ(int z);
    
    /*</members>*/
    public void set(int x, int y, int z) {
        setX(x);
        setY(y);
        setZ(z);
    }
    /*<methods>*//*</methods>*/
}
