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
package org.robovm.apple.avfoundation;

import org.robovm.apple.coremedia.CMTimeRange;

public class AVTimeRamp<T> {
    private CMTimeRange timeRange;
    private T start;
    private T end;
    
    protected AVTimeRamp(T start, T end, CMTimeRange timeRange) {
        this.start = start;
        this.end = end;
        this.timeRange = timeRange;
    }
    
    public CMTimeRange getTimeRange() {
        return timeRange;
    }
    
    public T getStart() {
        return start;
    }
    
    public T getEnd() {
        return end;
    }
}
