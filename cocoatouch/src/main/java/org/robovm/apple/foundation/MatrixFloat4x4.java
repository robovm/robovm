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
/*<visibility>*/public/*</visibility>*/ class MatrixFloat4x4 
    extends /*<extends>*/Struct<MatrixFloat4x4>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MatrixFloat4x4Ptr extends Ptr<MatrixFloat4x4, MatrixFloat4x4Ptr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MatrixFloat4x4() {}
    public MatrixFloat4x4(VectorFloat4[] columns) {
        this.setColumns(columns);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @Array(4) VectorFloat4[] getColumns();
    @StructMember(0) public native MatrixFloat4x4 setColumns(@Array(4) VectorFloat4[] columns);
    /*</members>*/
    public void set(VectorFloat4[] columns) {
        setColumns(columns);
    }
    public void set(VectorFloat4 c1, VectorFloat4 c2, VectorFloat4 c3, VectorFloat4 c4) {
        setColumns(new VectorFloat4[] {c1, c2, c3, c4});
    }
    
    public VectorFloat4 getC1() {
        return getColumns()[0];
    }
    public VectorFloat4 getC2() {
        return getColumns()[1];
    }
    public VectorFloat4 getC3() {
        return getColumns()[2];
    }
    public VectorFloat4 getC4() {
        return getColumns()[3];
    }
    /*<methods>*//*</methods>*/
}
