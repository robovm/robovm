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
package libcore.util;

import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.TreeSet;

import libcore.io.BufferIterator;
import libcore.io.ErrnoException;
import libcore.io.HeapBufferIterator;
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
            (System.getenv("ANDROID_ROOT") == null ? "" : System.getenv("ANDROID_ROOT")) + "/usr/share/zoneinfo/";

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

    /**
     * <code>true</code> if there's an Android style single file timezone database file. If <code>false</code> we
     * assume that the {@link #ZONE_DIRECTORY_NAME} dir contains a traditional multi file timezone database.
     * This was added in RoboVM.
     */
    private static final boolean SINGLE_FILE_DB = hasSingleFileDB();
    
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
     * Deprecated TimeZone aliases for JDK 1.1.x compatibility. We use the same mappings as used by GCJ.
     * This was added in RoboVM.
     */
    private static final Map<String, String> deprecatedAliases;
    
    /**
     * Cached {@link ZoneInfo}s. The first time a ZoneInfo is loaded we store it in this array at the same
     * index as the index of the timezone's id in ids. Subsequent calls for the same TimeZone will return a clone
     * of the cached ZoneInfo.
     * This was added in RoboVM.
     */
    private static ZoneInfo[] zoneInfos;
    
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
        deprecatedAliases = new HashMap<String, String>();
        deprecatedAliases.put("ACT", "Australia/Darwin");
        deprecatedAliases.put("AET", "Australia/Sydney");
        deprecatedAliases.put("AGT", "America/Argentina/Buenos_Aires");
        deprecatedAliases.put("ART", "Africa/Cairo");
        deprecatedAliases.put("AST", "America/Juneau");
        deprecatedAliases.put("BET", "Etc/GMT-11");
        deprecatedAliases.put("BST", "Asia/Colombo");
        deprecatedAliases.put("CAT", "Africa/Gaborone");
        deprecatedAliases.put("CNT", "America/St_Johns");
        deprecatedAliases.put("CST", "CST6CDT");
        deprecatedAliases.put("CTT", "Asia/Brunei");
        deprecatedAliases.put("EAT", "Indian/Comoro");
        deprecatedAliases.put("ECT", "CET");
        deprecatedAliases.put("EST", "EST5EDT");
        deprecatedAliases.put("EST5", "EST5EDT");
        deprecatedAliases.put("IET", "EST5EDT");
        deprecatedAliases.put("IST", "Asia/Calcutta");
        deprecatedAliases.put("JST", "Asia/Seoul");
        deprecatedAliases.put("MIT", "Pacific/Niue");
        deprecatedAliases.put("MST", "MST7MDT");
        deprecatedAliases.put("MST7", "MST7MDT");
        deprecatedAliases.put("NET", "Indian/Mauritius");
        deprecatedAliases.put("NST", "Pacific/Auckland");
        deprecatedAliases.put("PLT", "Indian/Kerguelen");
        deprecatedAliases.put("PNT", "MST7MDT");
        deprecatedAliases.put("PRT", "America/Anguilla");
        deprecatedAliases.put("PST", "PST8PDT");
        deprecatedAliases.put("SST", "Pacific/Ponape");
        deprecatedAliases.put("VST", "Asia/Bangkok");        
        
        readIndex();
    }

    private ZoneInfoDB() {
    }

    private static boolean hasSingleFileDB() {
        File f = new File(ZONE_FILE_NAME);
        return f.exists() && f.isFile();
    }
    
    /**
     * Reads the file indicating the database version in use.
     */
    private static String readVersion() {
        if (SINGLE_FILE_DB) {
            try {
                byte[] bytes = IoUtils.readFileAsByteArray(ZONE_DIRECTORY_NAME + "zoneinfo.version");
                return new String(bytes, 0, bytes.length, Charsets.ISO_8859_1).trim();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            // Mac OS X / iOS have a +VERSION file in /usr/share/zoneinfo containing the version number
            try {
                byte[] bytes = IoUtils.readFileAsByteArray(ZONE_DIRECTORY_NAME + "+VERSION");
                return new String(bytes, 0, bytes.length, Charsets.ISO_8859_1).trim();
            } catch (IOException ex) {
                // Linux (at least Ubuntu) doesn't have a +VERSION file. Just return unknown.
                return "unknown";
            }            
        }
    }

    private static MemoryMappedFile mapData() {
        if (SINGLE_FILE_DB) {
            try {
                return MemoryMappedFile.mmapRO(ZONE_FILE_NAME);
            } catch (ErrnoException errnoException) {
                throw new AssertionError(errnoException);
            }
        } else {
            return null;
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
        if (SINGLE_FILE_DB) {
            MemoryMappedFile mappedFile = null;
            try {
                mappedFile = MemoryMappedFile.mmapRO(INDEX_FILE_NAME);
                readIndex(mappedFile);
            } catch (Exception ex) {
                throw new AssertionError(ex);
            } finally {
                IoUtils.closeQuietly(mappedFile);
            }
        } else {
            try {
                readIndexMulti();
            } catch (Exception ex) {
                throw new AssertionError(ex);
            }
        }
        
        zoneInfos = new ZoneInfo[ids.length];
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

    /**
     * Populates the ids array when using a multi file timezone database. Reads the contents of 
     * {@link #ZONE_DIRECTORY_NAME}. We assume that all files beginning with an upper case letter at depth 0 to 2 are
     * proper TZ files. Fragile but known to work on Ubuntu 12.04, Mac OS X 10.7 and iOS 6.0.
     * This was added in RoboVM.
     */
    private static void readIndexMulti() throws IOException {
        TreeSet<String> set = new TreeSet<String>();
        
        for (File f1 : new File(ZONE_DIRECTORY_NAME).listFiles()) {
            String name1 = f1.getName();
            if ("Factory".equals(name1)) {
                // Skip
                continue;
            }
            if (name1.charAt(0) < 'A' || name1.charAt(0) > 'Z') {
                // Must start with upper case character. Skip.
                continue;
            }
            if (f1.isDirectory()) {
                for (File f2 : f1.listFiles()) {
                    String name2 = f2.getName();
                    if (name2.charAt(0) < 'A' || name2.charAt(0) > 'Z') {
                        // Must start with upper case character. Skip.
                        continue;
                    }
                    if (f2.isDirectory()) {
                        for (File f3 : f2.listFiles()) {
                            String name3 = f3.getName();
                            if (name3.charAt(0) < 'A' || name3.charAt(0) > 'Z') {
                                // Must start with upper case character. Skip.
                                continue;
                            }
                            if (!f3.isDirectory()) {
                                set.add(name1 + "/" + name2 + "/" + name3);
                            }
                        }
                    } else {
                        set.add(name1 + "/" + name2);                                
                    }
                }
            } else {
                set.add(name1);
            }
        }
        
        for (Entry<String, String> alias : deprecatedAliases.entrySet()) {
            if (set.contains(alias.getValue())) {
                set.add(alias.getKey());
            }
        }

        ids = set.toArray(new String[set.size()]);
    }
    
    private static TimeZone makeTimeZone(String id, boolean clone) throws IOException {
        // Check the aliases first
        String realId = deprecatedAliases.get(id);
        if (realId != null) {
            return makeTimeZone(realId, clone);
        }
        
        // Work out where in the big data file this time zone is.
        int index = Arrays.binarySearch(ids, id);
        if (index < 0) {
            return null;
        }

        ZoneInfo zoneInfo = zoneInfos[index];
        if (zoneInfo != null) {
            return clone ? (TimeZone) zoneInfo.clone() : zoneInfo;
        }
        
        BufferIterator data = null;
        if (SINGLE_FILE_DB) {
            data = ALL_ZONE_DATA.bigEndianIterator();
            data.skip(byteOffsets[index]);
        } else {
            byte[] bytes = IoUtils.readFileAsByteArray(ZONE_DIRECTORY_NAME + id);
            data = HeapBufferIterator.iterator(bytes, 0, bytes.length, ByteOrder.BIG_ENDIAN);
        }

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

        zoneInfo = new ZoneInfo(id, transitions, type, gmtOffsets, isDsts);
        zoneInfos[index] = zoneInfo;
        return clone ? (TimeZone) zoneInfo.clone() : zoneInfo;
    }

    public static String[] getAvailableIDs() {
        return ids.clone();
    }

    public static String[] getAvailableIDs(int rawOffset) {
        List<String> matches = new ArrayList<String>();
        if (SINGLE_FILE_DB) {
            for (int i = 0, end = rawUtcOffsets.length; i < end; i++) {
                if (rawUtcOffsets[i] == rawOffset) {
                    matches.add(ids[i]);
                }
            }
        } else {
            // Unfortunately we need load each ZoneInfo to determine the offset
            for (String id : ids) {
                try {
                    TimeZone timeZone = makeTimeZone(id, false);
                    if (timeZone.getRawOffset() == rawOffset) {
                        matches.add(id);
                    }
                } catch (IOException e) {
                }
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
            // In RoboVM we check the files /etc/timezone, /etc/localtime and /private/var/db/timezone/localtime.
            // The first file is a text file containing the id of the current time zone, e.g. 'Europe/Stockholm'
            // The two other files should be symlinks into the /usr/share/zoneinfo dir pointing out the current
            // time zone.
            if (zoneName == null || zoneName.isEmpty()) {
                try {
                    // Ubuntu places the time zone in /etc/timezone
                    zoneName = IoUtils.readFileAsString("/etc/timezone").trim();
                } catch (IOException e) {
                }
            }
            if (zoneName == null || zoneName.isEmpty()) {
                // Mac OS X symlinks /etc/localtime to the current time zone file in /usr/share/zoneinfo
                File link = new File("/etc/localtime");
                if (!link.exists()) {
                    // iOS uses the link /private/var/db/timezone/localtime instead
                    link = new File("/private/var/db/timezone/localtime");
                }
                try {
                    String path = link.getCanonicalPath(); // Resolve the link
                    if (path.startsWith(ZONE_DIRECTORY_NAME)) {
                        zoneName = path.substring(ZONE_DIRECTORY_NAME.length());
                    }
                } catch (IOException e) {
                }
            }
            if (zoneName == null || zoneName.isEmpty()) {
                zoneName = "GMT";
            }
            return TimeZone.getTimeZone(zoneName);
        }
    }

    public static TimeZone getTimeZone(String id) {
        if (id == null) {
            return null;
        }
        try {
            return makeTimeZone(id, true);
        } catch (IOException ignored) {
            return null;
        }
    }

    public static String getVersion() {
        return VERSION;
    }
}
