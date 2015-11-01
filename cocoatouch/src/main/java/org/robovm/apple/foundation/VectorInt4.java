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
/*<visibility>*/public/*</visibility>*/ class VectorInt4 
    extends /*<extends>*/Struct<VectorInt4>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class VectorInt4Ptr extends Ptr<VectorInt4, VectorInt4Ptr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public VectorInt4() {}
    public VectorInt4(int x, int y, int z, int w) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setW(w);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getX();
    @StructMember(0) public native VectorInt4 setX(int x);
    
    @StructMember(1) public native int getY();
    @StructMember(1) public native VectorInt4 setY(int y);
    
    @StructMember(2) public native int getZ();
    @StructMember(2) public native VectorInt4 setZ(int z);
    
    @StructMember(4) public native int getW();
    @StructMember(4) public native VectorInt4 setW(int w);
    /*</members>*/
    public void set(int x, int y, int z, int w) {
        setX(x);
        setY(y);
        setZ(z);
        setW(w);
    }
    /*<methods>*//*</methods>*/
}
