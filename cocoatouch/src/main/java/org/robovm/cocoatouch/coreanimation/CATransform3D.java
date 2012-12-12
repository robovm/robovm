/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.coreanimation;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 *
 */
public final class CATransform3D extends Struct<CATransform3D> {

    public CATransform3D() {}
    public CATransform3D(
    		float m11, float m12, float m13, float m14,
    		float m21, float m22, float m23, float m24,
    		float m31, float m32, float m33, float m34,
    		float m41, float m42, float m43, float m44) {
    	
    	m11(m11);
    	m12(m12);
    	m13(m13);
    	m14(m14);
    	m21(m21);
    	m22(m22);
    	m23(m23);
    	m24(m24);
    	m31(m31);
    	m32(m32);
    	m33(m33);
    	m34(m34);
    	m41(m41);
    	m42(m42);
    	m43(m43);
    	m44(m44);
    }
    
    @StructMember(0)
    public native float m11();
    @StructMember(0)
    public native CATransform3D m11(float f);
    @StructMember(1)
    public native float m12();
    @StructMember(1)
    public native CATransform3D m12(float f);
    @StructMember(2)
    public native float m13();
    @StructMember(2)
    public native CATransform3D m13(float f);
    @StructMember(3)
    public native float m14();
    @StructMember(3)
    public native CATransform3D m14(float f);
    
    @StructMember(4)
    public native float m21();
    @StructMember(4)
    public native CATransform3D m21(float f);
    @StructMember(5)
    public native float m22();
    @StructMember(5)
    public native CATransform3D m22(float f);
    @StructMember(6)
    public native float m23();
    @StructMember(6)
    public native CATransform3D m23(float f);
    @StructMember(7)
    public native float m24();
    @StructMember(7)
    public native CATransform3D m24(float f);

    @StructMember(8)
    public native float m31();
    @StructMember(8)
    public native CATransform3D m31(float f);
    @StructMember(9)
    public native float m32();
    @StructMember(9)
    public native CATransform3D m32(float f);
    @StructMember(10)
    public native float m33();
    @StructMember(10)
    public native CATransform3D m33(float f);
    @StructMember(11)
    public native float m34();
    @StructMember(11)
    public native CATransform3D m34(float f);
    
    @StructMember(12)
    public native float m41();
    @StructMember(12)
    public native CATransform3D m41(float f);
    @StructMember(13)
    public native float m42();
    @StructMember(13)
    public native CATransform3D m42(float f);
    @StructMember(14)
    public native float m43();
    @StructMember(14)
    public native CATransform3D m43(float f);
    @StructMember(15)
    public native float m44();
    @StructMember(15)
    public native CATransform3D m44(float f);
}
