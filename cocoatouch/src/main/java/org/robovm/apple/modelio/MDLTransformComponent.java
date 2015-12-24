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
package org.robovm.apple.modelio;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/MDLTransformComponent/*</name>*/ 
    /*<implements>*/extends MDLComponent/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    @Property(selector = "matrix")
    MatrixFloat4x4 getMatrix();
    @Property(selector = "setMatrix:", strongRef = true)
    void setMatrix(MatrixFloat4x4 v);
    @Property(selector = "minimumTime")
    double getMinimumTime();
    @Property(selector = "maximumTime")
    double getMaximumTime();
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "setLocalTransform:forTime:")
    void setLocalTransform(MatrixFloat4x4 transform, double time);
    @Method(selector = "setLocalTransform:")
    void setLocalTransform(MatrixFloat4x4 transform);
    @Method(selector = "localTransformAtTime:")
    MatrixFloat4x4 getLocalTransform(double time);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
