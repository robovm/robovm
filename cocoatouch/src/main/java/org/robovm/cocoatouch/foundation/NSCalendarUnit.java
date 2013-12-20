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
package org.robovm.cocoatouch.foundation;

import org.robovm.rt.bro.ValuedEnum;

/**
 * 
 */
public enum NSCalendarUnit implements ValuedEnum {
    Era(1 << 1),
    Year(1 << 2),
    Month(1 << 3),
    Day(1 << 4),
    Hour(1 << 5),
    Minute(1 << 6),
    Second(1 << 7),
    Week(1 << 8),
    Weekday(1 << 9),
    WeekdayOrdinal(1 << 10),
    Quarter(1 << 11),
    WeekOfMonth(1 << 12),
    WeekOfYear(1 << 13),
    YearForWeekOfYear(1 << 14),
    Calendar(1 << 20),
    TimeZone(1 << 21);
    
    private final long n;

    private NSCalendarUnit(long n) { this.n = n; }
    public long value() { return n; }
}
