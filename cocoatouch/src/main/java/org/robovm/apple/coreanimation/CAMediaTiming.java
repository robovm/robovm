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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/CAMediaTiming/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    @Property(selector = "beginTime")
    double getBeginTime();
    @Property(selector = "setBeginTime:")
    void setBeginTime(double v);
    @Property(selector = "duration")
    double getDuration();
    @Property(selector = "setDuration:")
    void setDuration(double v);
    @Property(selector = "speed")
    float getSpeed();
    @Property(selector = "setSpeed:")
    void setSpeed(float v);
    @Property(selector = "timeOffset")
    double getTimeOffset();
    @Property(selector = "setTimeOffset:")
    void setTimeOffset(double v);
    @Property(selector = "repeatCount")
    float getRepeatCount();
    @Property(selector = "setRepeatCount:")
    void setRepeatCount(float v);
    @Property(selector = "repeatDuration")
    double getRepeatDuration();
    @Property(selector = "setRepeatDuration:")
    void setRepeatDuration(double v);
    @Property(selector = "autoreverses")
    boolean isAutoreverses();
    @Property(selector = "setAutoreverses:")
    void setAutoreverses(boolean v);
    @Property(selector = "fillMode")
    String getFillMode();
    @Property(selector = "setFillMode:")
    void setFillMode(String v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
