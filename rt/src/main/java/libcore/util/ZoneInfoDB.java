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

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import libcore.io.BufferIterator;
import libcore.io.ErrnoException;
import libcore.io.IoUtils;
import libcore.io.MemoryMappedFile;

// TODO: repackage this class, used by frameworks/base.
import org.apache.harmony.luni.internal.util.TimezoneGetter;

/**
 * A class used to initialize the time zone database.  This implementation uses the
 * 'zoneinfo' database as the source of time zone information.  However, to conserve
 * disk space the data for all time zones are concatenated into a single file, and a
 * second file is used to indicate the starting position of each time zone record.  A
 * third file indicates the version of the zoneinfo database used to generate the data.
 *
 * @hide - used to implement TimeZone
 */
public final class ZoneInfoDB {
    /**
     * The directory containing the time zone database files.
     */
    private static final String ZONE_DIRECTORY_NAME =
            System.getenv("ANDROID_ROOT") + "/usr/share/zoneinfo/";

    /**
     * The name of the file containing the concatenated time zone records.
     */
    private static final String ZONE_FILE_NAME = ZONE_DIRECTORY_NAME + "zoneinfo.dat";

    /**
     * The name of the file containing the index to each time zone record within
     * the zoneinfo.dat file.
     */
    private static final String INDEX_FILE_NAME = ZONE_DIRECTORY_NAME + "zoneinfo.idx";

    private static final Object LOCK = new Object();

    private static final String VERSION = readVersion();

    /**
     * Rather than open, read, and close the big data file each time we look up a time zone,
     * we map the big data file during startup, and then just use the MemoryMappedFile.
     *
     * At the moment, this "big" data file is about 500 KiB. At some point, that will be small
     * enough that we'll just keep the byte[] in memory.
     */
    private static final MemoryMappedFile ALL_ZONE_DATA = mapData();

    /**
     * The 'ids' array contains time zone ids sorted alphabetically, for binary searching.
     * The other two arrays are in the same order. 'byteOffsets' gives the byte offset
     * into "zoneinfo.dat" of each time zone, and 'rawUtcOffsets' gives the time zone's
     * raw UTC offset.
     */
    private static String[] ids;
    private static int[] byteOffsets;
    private static int[] rawUtcOffsets;
    static {
        readIndex();
    }

    private ZoneInfoDB() {
    }

    /**
     * Reads the file indicating the database version in use.
     */
    private static String readVersion() {
        try {
            byte[] bytes = IoUtils.readFileAsByteArray(ZONE_DIRECTORY_NAME + "zoneinfo.version");
            return new String(bytes, 0, bytes.length, Charsets.ISO_8859_1).trim();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static MemoryMappedFile mapData() {
        try {
            return MemoryMappedFile.mmapRO(ZONE_FILE_NAME);
        } catch (ErrnoException errnoException) {
            throw new AssertionError(errnoException);
        }
    }

    /**
     * Traditionally, Unix systems have one file per time zone. We have one big data file, which
     * is just a concatenation of regular time zone files. To allow random access into this big
     * data file, we also have an index. We read the index at startup, and keep it in memory so
     * we can binary search by id when we need time zone data.
     *
     * The format of this file is, I believe, Android's own, and undocumented.
     *
     * All this code assumes strings are US-ASCII.
     */
    private static void readIndex() {
        MemoryMappedFile mappedFile = null;
        try {
            mappedFile = MemoryMappedFile.mmapRO(INDEX_FILE_NAME);
            readIndex(mappedFile);
        } catch (Exception ex) {
            throw new AssertionError(ex);
        } finally {
            IoUtils.closeQuietly(mappedFile);
        }
    }

    private static void readIndex(MemoryMappedFile mappedFile) throws ErrnoException, IOException {
        BufferIterator it = mappedFile.bigEndianIterator();

        // The database reserves 40 bytes for each id.
        final int SIZEOF_TZNAME = 40;
        // The database uses 32-bit (4 byte) integers.
        final int SIZEOF_TZINT = 4;

        byte[] idBytes = new byte[SIZEOF_TZNAME];
        int numEntries = (int) mappedFile.size() / (SIZEOF_TZNAME + 3*SIZEOF_TZINT);

        char[] idChars = new char[numEntries * SIZEOF_TZNAME];
        int[] idEnd = new int[numEntries];
        int idOffset = 0;

        byteOffsets = new int[numEntries];
        rawUtcOffsets = new int[numEntries];

        for (int i = 0; i < numEntries; i++) {
            it.readByteArray(idBytes, 0, idBytes.length);
            byteOffsets[i] = it.readInt();
            int length = it.readInt();
            if (length < 44) {
                throw new AssertionError("length in index file < sizeof(tzhead)");
            }
            rawUtcOffsets[i] = it.readInt();

            // Don't include null chars in the String
            int len = idBytes.length;
            for (int j = 0; j < len; j++) {
                if (idBytes[j] == 0) {
                    break;
                }
                idChars[idOffset++] = (char) (idBytes[j] & 0xFF);
            }

            idEnd[i] = idOffset;
        }

        // We create one string containing all the ids, and then break that into substrings.
        // This way, all ids share a single char[] on the heap.
        String allIds = new String(idChars, 0, idOffset);
        ids = new String[numEntries];
        for (int i = 0; i < numEntries; i++) {
            ids[i] = allIds.substring(i == 0 ? 0 : idEnd[i - 1], idEnd[i]);
        }
    }

    private static TimeZone makeTimeZone(String id) throws IOException {
        // Work out where in the big data file this time zone is.
        int index = Arrays.binarySearch(ids, id);
        if (index < 0) {
            return null;
        }

        BufferIterator data = ALL_ZONE_DATA.bigEndianIterator();
        data.skip(byteOffsets[index]);

        // Variable names beginning tzh_ correspond to those in "tzfile.h".
        // Check tzh_magic.
        if (data.readInt() != 0x545a6966) { // "TZif"
            return null;
        }

        // Skip the uninteresting part of the header.
        data.skip(28);

        // Read the sizes of the arrays we're about to read.
        int tzh_timecnt = data.readInt();
        int tzh_typecnt = data.readInt();

        data.skip(4); // Skip tzh_charcnt.

        int[] transitions = new int[tzh_timecnt];
        data.readIntArray(transitions, 0, transitions.length);

        byte[] type = new byte[tzh_timecnt];
        data.readByteArray(type, 0, type.length);

        int[] gmtOffsets = new int[tzh_typecnt];
        byte[] isDsts = new byte[tzh_typecnt];
        for (int i = 0; i < tzh_typecnt; ++i) {
            gmtOffsets[i] = data.readInt();
            isDsts[i] = data.readByte();
            // We skip the abbreviation index. This would let us provide historically-accurate
            // time zone abbreviations (such as "AHST", "YST", and "AKST" for standard time in
            // America/Anchorage in 1982, 1983, and 1984 respectively). ICU only knows the current
            // names, though, so even if we did use this data to provide the correct abbreviations
            // for en_US, we wouldn't be able to provide correct abbreviations for other locales,
            // nor would we be able to provide correct long forms (such as "Yukon Standard Time")
            // for any locale. (The RI doesn't do any better than us here either.)
            data.skip(1);
        }

        return new ZoneInfo(id, transitions, type, gmtOffsets, isDsts);
    }

    public static String[] getAvailableIDs() {
        return ids.clone();
    }

    public static String[] getAvailableIDs(int rawOffset) {
        List<String> matches = new ArrayList<String>();
        for (int i = 0, end = rawUtcOffsets.length; i < end; i++) {
            if (rawUtcOffsets[i] == rawOffset) {
                matches.add(ids[i]);
            }
        }
        return matches.toArray(new String[matches.size()]);
    }

    public static TimeZone getSystemDefault() {
        synchronized (LOCK) {
            TimezoneGetter tzGetter = TimezoneGetter.getInstance();
            String zoneName = tzGetter != null ? tzGetter.getId() : null;
            if (zoneName != null) {
                zoneName = zoneName.trim();
            }
            if (zoneName == null || zoneName.isEmpty()) {
                // use localtime for the simulator
                // TODO: what does that correspond to?
                zoneName = "localtime";
            }
            return TimeZone.getTimeZone(zoneName);
        }
    }

    public static TimeZone getTimeZone(String id) {
        if (id == null) {
            return null;
        }
        try {
            return makeTimeZone(id);
        } catch (IOException ignored) {
            return null;
        }
    }

    public static String getVersion() {
        return VERSION;
    }
}
