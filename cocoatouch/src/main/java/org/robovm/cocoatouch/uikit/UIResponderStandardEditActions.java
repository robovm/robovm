/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

@Protocol
public interface /*<name>*/ UIResponderStandardEditActions /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("copy:") @Type("void") void copy(@Type("id") NSObject sender);
    @Bind("cut:") @Type("void") void cut(@Type("id") NSObject sender);
    @Bind("delete:") @Type("void") void delete(@Type("id") NSObject sender);
    @Bind("makeTextWritingDirectionLeftToRight:") @Type("void") void makeTextWritingDirectionLeftToRight(@Type("id") NSObject sender);
    @Bind("makeTextWritingDirectionRightToLeft:") @Type("void") void makeTextWritingDirectionRightToLeft(@Type("id") NSObject sender);
    @Bind("paste:") @Type("void") void paste(@Type("id") NSObject sender);
    @Bind("select:") @Type("void") void select(@Type("id") NSObject sender);
    @Bind("selectAll:") @Type("void") void selectAll(@Type("id") NSObject sender);
    @Bind("toggleBoldface:") @Type("void") void toggleBoldface(@Type("id") NSObject sender);
    @Bind("toggleItalics:") @Type("void") void toggleItalics(@Type("id") NSObject sender);
    @Bind("toggleUnderline:") @Type("void") void toggleUnderline(@Type("id") NSObject sender);
    /*</methods>*/

}
