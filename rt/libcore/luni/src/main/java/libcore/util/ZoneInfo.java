/*
 * Copyright (C) 2007 The Android Open Source Project
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

package libcore.util;

import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import libcore.io.BufferIterator;

/**
 * Our concrete TimeZone implementation, backed by zoneinfo data.
 *
 * @hide - used to implement TimeZone
 */
public final class ZoneInfo extends TimeZone {
    private static final long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000;
    private static final long MILLISECONDS_PER_400_YEARS =
            MILLISECONDS_PER_DAY * (400 * 365 + 100 - 3);

    private static final long UNIX_OFFSET = 62167219200000L;

    private static final int[] NORMAL = new int[] {
        0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334,
    };

    private static final int[] LEAP = new int[] {
        0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335,
    };

    private int mRawOffset;
    private final int mEarliestRawOffset;
    private final boolean mUseDst;
    private final int mDstSavings; // Implements TimeZone.getDSTSavings.

    private final int[] mTransitions;
    private final int[] mOffsets;
    private final byte[] mTypes;
    private final byte[] mIsDsts;

    public static TimeZone makeTimeZone(String id, BufferIterator it) {
        // Variable names beginning tzh_ correspond to those in "tzfile.h".

        // Check tzh_magic.
        if (it.readInt() != 0x545a6966) { // "TZif"
            return null;
        }

        // Skip the uninteresting part of the header.
        it.skip(28);

        // Read the sizes of the arrays we're about to read.
        int tzh_timecnt = it.readInt();
        int tzh_typecnt = it.readInt();

        it.skip(4); // Skip tzh_charcnt.

        int[] transitions = new int[tzh_timecnt];
        it.readIntArray(transitions, 0, transitions.length);

        byte[] type = new byte[tzh_timecnt];
        it.readByteArray(type, 0, type.length);

        int[] gmtOffsets = new int[tzh_typecnt];
        byte[] isDsts = new byte[tzh_typecnt];
        for (int i = 0; i < tzh_typecnt; ++i) {
            gmtOffsets[i] = it.readInt();
            isDsts[i] = it.readByte();
            // We skip the abbreviation index. This would let us provide historically-accurate
            // time zone abbreviations (such as "AHST", "YST", and "AKST" for standard time in
            // America/Anchorage in 1982, 1983, and 1984 respectively). ICU only knows the current
            // names, though, so even if we did use this data to provide the correct abbreviations
            // for en_US, we wouldn't be able to provide correct abbreviations for other locales,
            // nor would we be able to provide correct long forms (such as "Yukon Standard Time")
            // for any locale. (The RI doesn't do any better than us here either.)
            it.skip(1);
        }

        return new ZoneInfo(id, transitions, type, gmtOffsets, isDsts);
    }

    private ZoneInfo(String name, int[] transitions, byte[] types, int[] gmtOffsets, byte[] isDsts) {
        mTransitions = transitions;
        mTypes = types;
        mIsDsts = isDsts;
        setID(name);

        // Find the latest daylight and standard offsets (if any).
        int lastStd = 0;
        boolean haveStd = false;
        int lastDst = 0;
        boolean haveDst = false;
        for (int i = mTransitions.length - 1; (!haveStd || !haveDst) && i >= 0; --i) {
            int type = mTypes[i] & 0xff;
            if (!haveStd && mIsDsts[type] == 0) {
                haveStd = true;
                lastStd = i;
            }
            if (!haveDst && mIsDsts[type] != 0) {
                haveDst = true;
                lastDst = i;
            }
        }

        // Use the latest non-daylight offset (if any) as the raw offset.
        if (lastStd >= mTypes.length) {
            mRawOffset = gmtOffsets[0];
        } else {
            mRawOffset = gmtOffsets[mTypes[lastStd] & 0xff];
        }

        // Use the latest transition's pair of offsets to compute the DST savings.
        // This isn't generally useful, but it's exposed by TimeZone.getDSTSavings.
        if (lastDst >= mTypes.length) {
            mDstSavings = 0;
        } else {
            mDstSavings = Math.abs(gmtOffsets[mTypes[lastStd] & 0xff] - gmtOffsets[mTypes[lastDst] & 0xff]) * 1000;
        }

        // Cache the oldest known raw offset, in case we're asked about times that predate our
        // transition data.
        int firstStd = -1;
        for (int i = 0; i < mTransitions.length; ++i) {
            if (mIsDsts[mTypes[i] & 0xff] == 0) {
                firstStd = i;
                break;
            }
        }
        int earliestRawOffset = (firstStd != -1) ? gmtOffsets[mTypes[firstStd] & 0xff] : mRawOffset;

        // Rather than keep offsets from UTC, we use offsets from local time, so the raw offset
        // can be changed and automatically affect all the offsets.
        mOffsets = gmtOffsets;
        for (int i = 0; i < mOffsets.length; i++) {
            mOffsets[i] -= mRawOffset;
        }

        // Is this zone still observing DST?
        // We don't care if they've historically used it: most places have at least once.
        // We want to know whether the last "schedule info" (the unix times in the mTransitions
        // array) is in the future. If it is, DST is still relevant.
        // See http://code.google.com/p/android/issues/detail?id=877.
        // This test means that for somewhere like Morocco, which tried DST in 2009 but has
        // no future plans (and thus no future schedule info) will report "true" from
        // useDaylightTime at the start of 2009 but "false" at the end. This seems appropriate.
        boolean usesDst = false;
        long currentUnixTime = System.currentTimeMillis() / 1000;
        if (mTransitions.length > 0) {
            // (We're really dealing with uint32_t values, so long is most convenient in Java.)
            long latestScheduleTime = ((long) mTransitions[mTransitions.length - 1]) & 0xffffffff;
            if (currentUnixTime < latestScheduleTime) {
                usesDst = true;
            }
        }
        mUseDst = usesDst;

        // tzdata uses seconds, but Java uses milliseconds.
        mRawOffset *= 1000;
        mEarliestRawOffset = earliestRawOffset * 1000;
    }

    @Override
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int millis) {
        // XXX This assumes Gregorian always; Calendar switches from
        // Julian to Gregorian in 1582.  What calendar system are the
        // arguments supposed to come from?

        long calc = (year / 400) * MILLISECONDS_PER_400_YEARS;
        year %= 400;

        calc += year * (365 * MILLISECONDS_PER_DAY);
        calc += ((year + 3) / 4) * MILLISECONDS_PER_DAY;

        if (year > 0) {
            calc -= ((year - 1) / 100) * MILLISECONDS_PER_DAY;
        }

        boolean isLeap = (year == 0 || (year % 4 == 0 && year % 100 != 0));
        int[] mlen = isLeap ? LEAP : NORMAL;

        calc += mlen[month] * MILLISECONDS_PER_DAY;
        calc += (day - 1) * MILLISECONDS_PER_DAY;
        calc += millis;

        calc -= mRawOffset;
        calc -= UNIX_OFFSET;

        return getOffset(calc);
    }

    @Override
    public int getOffset(long when) {
        int unix = (int) (when / 1000);
        int transition = Arrays.binarySearch(mTransitions, unix);
        if (transition < 0) {
            transition = ~transition - 1;
            if (transition < 0) {
                // Assume that all times before our first transition correspond to the
                // oldest-known non-daylight offset. The obvious alternative would be to
                // use the current raw offset, but that seems like a greater leap of faith.
                return mEarliestRawOffset;
            }
        }
        return mRawOffset + mOffsets[mTypes[transition] & 0xff] * 1000;
    }

    @Override public boolean inDaylightTime(Date time) {
        long when = time.getTime();
        int unix = (int) (when / 1000);
        int transition = Arrays.binarySearch(mTransitions, unix);
        if (transition < 0) {
            transition = ~transition - 1;
            if (transition < 0) {
                // Assume that all times before our first transition are non-daylight.
                // Transition data tends to start with a transition to daylight, so just
                // copying the first transition would assume the opposite.
                // http://code.google.com/p/android/issues/detail?id=14395
                return false;
            }
        }
        return mIsDsts[mTypes[transition] & 0xff] == 1;
    }

    @Override public int getRawOffset() {
        return mRawOffset;
    }

    @Override public void setRawOffset(int off) {
        mRawOffset = off;
    }

    @Override public int getDSTSavings() {
        return mUseDst ? mDstSavings: 0;
    }

    @Override public boolean useDaylightTime() {
        return mUseDst;
    }

    @Override public boolean hasSameRules(TimeZone timeZone) {
        if (!(timeZone instanceof ZoneInfo)) {
            return false;
        }
        ZoneInfo other = (ZoneInfo) timeZone;
        if (mUseDst != other.mUseDst) {
            return false;
        }
        if (!mUseDst) {
            return mRawOffset == other.mRawOffset;
        }
        return mRawOffset == other.mRawOffset
                // Arrays.equals returns true if both arrays are null
                && Arrays.equals(mOffsets, other.mOffsets)
                && Arrays.equals(mIsDsts, other.mIsDsts)
                && Arrays.equals(mTypes, other.mTypes)
                && Arrays.equals(mTransitions, other.mTransitions);
    }

    @Override public boolean equals(Object obj) {
        if (!(obj instanceof ZoneInfo)) {
            return false;
        }
        ZoneInfo other = (ZoneInfo) obj;
        return getID().equals(other.getID()) && hasSameRules(other);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getID().hashCode();
        result = prime * result + Arrays.hashCode(mOffsets);
        result = prime * result + Arrays.hashCode(mIsDsts);
        result = prime * result + mRawOffset;
        result = prime * result + Arrays.hashCode(mTransitions);
        result = prime * result + Arrays.hashCode(mTypes);
        result = prime * result + (mUseDst ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[id=\"" + getID() + "\"" +
            ",mRawOffset=" + mRawOffset +
            ",mEarliestRawOffset=" + mEarliestRawOffset +
            ",mUseDst=" + mUseDst +
            ",mDstSavings=" + mDstSavings +
            ",transitions=" + mTransitions.length +
            "]";
    }
}
