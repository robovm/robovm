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
package org.robovm.apple.audiounit;

import org.robovm.apple.coreaudio.AudioBufferList;
import org.robovm.apple.coreaudio.AudioTimeStamp;
import org.robovm.apple.corefoundation.OSStatus;
import org.robovm.apple.corefoundation.OSStatusException;

public interface AURenderCallback {
    void onRender(AUMutableRenderActionFlags actionFlags, AudioTimeStamp timeStamp, 
            int busNumber, int numberFrames, AudioBufferList data) throws OSStatusException;
}
