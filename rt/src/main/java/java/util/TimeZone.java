/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package java.util;

import java.io.Serializable;
import libcore.icu.TimeZones;
import libcore.util.ZoneInfoDB;

/**
 * {@code TimeZone} represents a time zone, primarily used for configuring a {@link Calendar} or
 * {@link java.text.SimpleDateFormat} instance.
 *
 * <p>Most applications will use {@link #getDefault} which returns a {@code TimeZone} based on
 * the time zone where the program is running.
 *
 * <p>You can also get a specific {@code TimeZone} {@link #getTimeZone by id}.
 *
 * <p>It is highly unlikely you'll ever want to use anything but the factory methods yourself.
 * Let classes like {@link Calendar} and {@link java.text.SimpleDateFormat} do the date
 * computations for you.
 *
 * <p>If you do need to do date computations manually, there are two common cases to take into
 * account:
 * <ul>
 * <li>Somewhere like California, where daylight time is used.
 * The {@link #useDaylightTime} method will always return true, and {@link #inDaylightTime}
 * must be used to determine whether or not daylight time applies to a given {@code Date}.
 * The {@link #getRawOffset} method will return a raw offset of (in this case) -8 hours from UTC,
 * which isn't usually very useful. More usefully, the {@link #getOffset} methods return the
 * actual offset from UTC <i>for a given point in time</i>; this is the raw offset plus (if the
 * point in time is {@link #inDaylightTime in daylight time}) the applicable
 * {@link #getDSTSavings DST savings} (usually, but not necessarily, 1 hour).
 * <li>Somewhere like Japan, where daylight time is not used.
 * The {@link #useDaylightTime} and {@link #inDaylightTime} methods both always return false,
 * and the raw and actual offsets will always be the same.
 * </ul>
 *
 * <p>Note the type returned by the factory methods {@link #getDefault} and {@link #getTimeZone} is
 * implementation dependent. This may introduce serialization incompatibility issues between
 * different implementations. Android returns instances of {@link SimpleTimeZone} so that
 * the bytes serialized by Android can be deserialized successfully on other
 * implementations, but the reverse compatibility cannot be guaranteed.
 *
 * @see Calendar
 * @see GregorianCalendar
 * @see SimpleDateFormat
 * @see SimpleTimeZone
 */
public abstract class TimeZone implements Serializable, Cloneable {
    private static final long serialVersionUID = 3581463369166924961L;

    /**
     * The short display name style, such as {@code PDT}. Requests for this
     * style may yield GMT offsets like {@code GMT-08:00}.
     */
    public static final int SHORT = 0;

    /**
     * The long display name style, such as {@code Pacific Daylight Time}.
     * Requests for this style may yield GMT offsets like {@code GMT-08:00}.
     */
    public static final int LONG = 1;

    static final TimeZone GMT = new SimpleTimeZone(0, "GMT"); // Greenwich Mean Time

    private static TimeZone defaultTimeZone;

    private String ID;

    public TimeZone() {}

    /**
     * Returns a new time zone with the same ID, raw offset, and daylight
     * savings time rules as this time zone.
     */
    @Override public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Returns the system's installed time zone IDs. Any of these IDs can be
     * passed to {@link #getTimeZone} to lookup the corresponding time zone
     * instance.
     */
    public static synchronized String[] getAvailableIDs() {
        return ZoneInfoDB.getAvailableIDs();
    }

    /**
     * Returns the IDs of the time zones whose offset from UTC is {@code
     * offsetMillis}. Any of these IDs can be passed to {@link #getTimeZone} to
     * lookup the corresponding time zone instance.
     *
     * @return a possibly-empty array.
     */
    public static synchronized String[] getAvailableIDs(int offsetMillis) {
        return ZoneInfoDB.getAvailableIDs(offsetMillis);
    }

    /**
     * Returns the user's preferred time zone. This may have been overridden for
     * this process with {@link #setDefault}.
     *
     * <p>Since the user's time zone changes dynamically, avoid caching this
     * value. Instead, use this method to look it up for each use.
     */
    public static synchronized TimeZone getDefault() {
        if (defaultTimeZone == null) {
            defaultTimeZone = ZoneInfoDB.getSystemDefault();
        }
        return (TimeZone) defaultTimeZone.clone();
    }

    /**
     * Equivalent to {@code getDisplayName(false, TimeZone.LONG, Locale.getDefault())}.
     * <a href="../util/Locale.html#default_locale">Be wary of the default locale</a>.
     */
    public final String getDisplayName() {
        return getDisplayName(false, LONG, Locale.getDefault());
    }

    /**
     * Equivalent to {@code getDisplayName(false, TimeZone.LONG, locale)}.
     */
    public final String getDisplayName(Locale locale) {
        return getDisplayName(false, LONG, locale);
    }

    /**
     * Equivalent to {@code getDisplayName(daylightTime, style, Locale.getDefault())}.
     * <a href="../util/Locale.html#default_locale">Be wary of the default locale</a>.
     */
    public final String getDisplayName(boolean daylightTime, int style) {
        return getDisplayName(daylightTime, style, Locale.getDefault());
    }

    /**
     * Returns the {@link #SHORT short} or {@link #LONG long} name of this time
     * zone with either standard or daylight time, as written in {@code locale}.
     * If the name is not available, the result is in the format
     * {@code GMT[+-]hh:mm}.
     *
     * @param daylightTime true for daylight time, false for standard time.
     * @param style either {@link TimeZone#LONG} or {@link TimeZone#SHORT}.
     * @param locale the display locale.
     */
    public String getDisplayName(boolean daylightTime, int style, Locale locale) {
        if (style != SHORT && style != LONG) {
            throw new IllegalArgumentException();
        }

        boolean useDaylight = daylightTime && useDaylightTime();

        String[][] zoneStrings = TimeZones.getZoneStrings(locale);
        String result = TimeZones.getDisplayName(zoneStrings, getID(), daylightTime, style);
        if (result != null) {
            return result;
        }

        // TODO: do we ever get here?

        int offset = getRawOffset();
        if (useDaylight && this instanceof SimpleTimeZone) {
            offset += getDSTSavings();
        }
        offset /= 60000;
        char sign = '+';
        if (offset < 0) {
            sign = '-';
            offset = -offset;
        }
        StringBuilder builder = new StringBuilder(9);
        builder.append("GMT");
        builder.append(sign);
        appendNumber(builder, 2, offset / 60);
        builder.append(':');
        appendNumber(builder, 2, offset % 60);
        return builder.toString();
    }

    private void appendNumber(StringBuilder builder, int count, int value) {
        String string = Integer.toString(value);
        for (int i = 0; i < count - string.length(); i++) {
            builder.append('0');
        }
        builder.append(string);
    }

    /**
     * Returns the ID of this {@code TimeZone}, such as
     * {@code America/Los_Angeles}, {@code GMT-08:00} or {@code UTC}.
     */
    public String getID() {
        return ID;
    }

    /**
     * Returns the daylight savings offset in milliseconds for this time zone.
     * The base implementation returns {@code 3600000} (1 hour) for time zones
     * that use daylight savings time and {@code 0} for timezones that do not.
     * Subclasses should override this method for other daylight savings
     * offsets.
     *
     * <p>Note that this method doesn't tell you whether or not to apply the
     * offset: you need to call {@code inDaylightTime} for the specific time
     * you're interested in. If this method returns a non-zero offset, that only
     * tells you that this {@code TimeZone} sometimes observes daylight savings.
     */
    public int getDSTSavings() {
        return useDaylightTime() ? 3600000 : 0;
    }

    /**
     * Returns the offset in milliseconds from UTC for this time zone at {@code
     * time}. The offset includes daylight savings time if the specified
     * date is within the daylight savings time period.
     *
     * @param time the date in milliseconds since January 1, 1970 00:00:00 UTC
     */
    public int getOffset(long time) {
        if (inDaylightTime(new Date(time))) {
            return getRawOffset() + getDSTSavings();
        }
        return getRawOffset();
    }

    /**
     * Returns this time zone's offset in milliseconds from UTC at the specified
     * date and time. The offset includes daylight savings time if the date
     * and time is within the daylight savings time period.
     *
     * <p>This method is intended to be used by {@link Calendar} to compute
     * {@link Calendar#DST_OFFSET} and {@link Calendar#ZONE_OFFSET}. Application
     * code should have no reason to call this method directly. Each parameter
     * is interpreted in the same way as the corresponding {@code Calendar}
     * field. Refer to {@link Calendar} for specific definitions of this
     * method's parameters.
     */
    public abstract int getOffset(int era, int year, int month, int day,
            int dayOfWeek, int timeOfDayMillis);

    /**
     * Returns the offset in milliseconds from UTC of this time zone's standard
     * time.
     */
    public abstract int getRawOffset();

    /**
     * Returns a {@code TimeZone} suitable for {@code id}, or {@code GMT} on failure.
     *
     * <p>An id can be an Olson name of the form <i>Area</i>/<i>Location</i>, such
     * as {@code America/Los_Angeles}. The {@link #getAvailableIDs} method returns
     * the supported names.
     *
     * <p>This method can also create a custom {@code TimeZone} using the following
     * syntax: {@code GMT[+|-]hh[[:]mm]}. For example, {@code TimeZone.getTimeZone("GMT+14:00")}
     * would return an object with a raw offset of +14 hours from UTC, and which does <i>not</i>
     * use daylight savings. These are rarely useful, because they don't correspond to time
     * zones actually in use.
     *
     * <p>Other than the special cases "UTC" and "GMT" (which are synonymous in this context,
     * both corresponding to UTC), Android does not support the deprecated three-letter time
     * zone IDs used in Java 1.1.
     */
    public static synchronized TimeZone getTimeZone(String id) {
        TimeZone zone = ZoneInfoDB.getTimeZone(id);
        if (zone != null) {
            return zone;
        }
        if (zone == null && id.length() > 3 && id.startsWith("GMT")) {
            zone = getCustomTimeZone(id);
        }
        if (zone == null) {
            zone = (TimeZone) GMT.clone();
        }
        return zone;
    }

    /**
     * Returns a new SimpleTimeZone for an id of the form "GMT[+|-]hh[[:]mm]", or null.
     */
    private static TimeZone getCustomTimeZone(String id) {
        char sign = id.charAt(3);
        if (sign != '+' && sign != '-') {
            return null;
        }
        int[] position = new int[1];
        String formattedName = formatTimeZoneName(id, 4);
        int hour = parseNumber(formattedName, 4, position);
        if (hour < 0 || hour > 23) {
            return null;
        }
        int index = position[0];
        if (index == -1) {
            return null;
        }
        int raw = hour * 3600000;
        if (index < formattedName.length() && formattedName.charAt(index) == ':') {
            int minute = parseNumber(formattedName, index + 1, position);
            if (position[0] == -1 || minute < 0 || minute > 59) {
                return null;
            }
            raw += minute * 60000;
        } else if (hour >= 30 || index > 6) {
            raw = (hour / 100 * 3600000) + (hour % 100 * 60000);
        }
        if (sign == '-') {
            raw = -raw;
        }
        return new SimpleTimeZone(raw, formattedName);
    }

    private static String formatTimeZoneName(String name, int offset) {
        StringBuilder buf = new StringBuilder();
        int index = offset, length = name.length();
        buf.append(name.substring(0, offset));

        while (index < length) {
            if (Character.digit(name.charAt(index), 10) != -1) {
                buf.append(name.charAt(index));
                if ((length - (index + 1)) == 2) {
                    buf.append(':');
                }
            } else if (name.charAt(index) == ':') {
                buf.append(':');
            }
            index++;
        }

        if (buf.toString().indexOf(":") == -1) {
            buf.append(':');
            buf.append("00");
        }

        if (buf.toString().indexOf(":") == 5) {
            buf.insert(4, '0');
        }

        return buf.toString();
    }

    /**
     * Returns true if {@code timeZone} has the same rules as this time zone.
     *
     * <p>The base implementation returns true if both time zones have the same
     * raw offset.
     */
    public boolean hasSameRules(TimeZone timeZone) {
        if (timeZone == null) {
            return false;
        }
        return getRawOffset() == timeZone.getRawOffset();
    }

    /**
     * Returns true if {@code time} is in a daylight savings time period for
     * this time zone.
     */
    public abstract boolean inDaylightTime(Date time);

    private static int parseNumber(String string, int offset, int[] position) {
        int index = offset, length = string.length(), digit, result = 0;
        while (index < length
                && (digit = Character.digit(string.charAt(index), 10)) != -1) {
            index++;
            result = result * 10 + digit;
        }
        position[0] = index == offset ? -1 : index;
        return result;
    }

    /**
     * Overrides the default time zone for the current process only.
     *
     * <p><strong>Warning</strong>: avoid using this method to use a custom time
     * zone in your process. This value may be cleared or overwritten at any
     * time, which can cause unexpected behavior. Instead, manually supply a
     * custom time zone as needed.
     *
     * @param timeZone a custom time zone, or {@code null} to set the default to
     *     the user's preferred value.
     */
    public static synchronized void setDefault(TimeZone timeZone) {
        defaultTimeZone = timeZone != null ? (TimeZone) timeZone.clone() : null;
    }

    /**
     * Sets the ID of this {@code TimeZone}.
     */
    public void setID(String id) {
        if (id == null) {
            throw new NullPointerException();
        }
        ID = id;
    }

    /**
     * Sets the offset in milliseconds from UTC of this time zone's standard
     * time.
     */
    public abstract void setRawOffset(int offsetMillis);

    /**
     * Returns true if this time zone has a future transition to or from
     * daylight savings time.
     *
     * <p><strong>Warning:</strong> this returns false for time zones like
     * {@code Asia/Kuala_Lumpur} that have previously used DST but do not
     * currently. A hypothetical country that has never observed daylight
     * savings before but plans to start next year would return true.
     *
     * <p><strong>Warning:</strong> this returns true for time zones that use
     * DST, even when it is not active.
     *
     * <p>Use {@link #inDaylightTime} to find out whether daylight savings is
     * in effect at a specific time.
     *
     * <p>Most applications should not use this method.
     */
    public abstract boolean useDaylightTime();
}
