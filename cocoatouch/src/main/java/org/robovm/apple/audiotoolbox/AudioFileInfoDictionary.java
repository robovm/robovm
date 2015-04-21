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
package org.robovm.apple.audiotoolbox;

import org.robovm.apple.corefoundation.CFDictionary;
import org.robovm.apple.corefoundation.CFDictionaryWrapper;
import org.robovm.apple.corefoundation.CFString;
import org.robovm.apple.corefoundation.CFType;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.MarshalsPointer;

@Marshaler(AudioFileInfoDictionary.Marshaler.class)
public class AudioFileInfoDictionary extends CFDictionaryWrapper {

    public static class Marshaler {
        @MarshalsPointer
        public static AudioFileInfoDictionary toObject(Class<AudioFileInfoDictionary> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AudioFileInfoDictionary(o);
        }
        @MarshalsPointer
        public static long toNative(AudioFileInfoDictionary o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    
    protected AudioFileInfoDictionary(CFDictionary data) {
        super(data);
    }
    
    public boolean has(CFString key) {
        return data.containsKey(key);
    }
    public <T extends NativeObject> T get(CFString key, Class<T> type) {
        if (has(key)) {
            return data.get(key, type);
        }
        return null;
    }
    public String getString(String key) {
        return getString(new CFString(key));
    }
    public String getString(CFString key) {
        CFString value = get(key, CFString.class);
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    
    public static class Keys {
        public static CFString Artist = new CFString("artist");
        public static CFString Album = new CFString("album");
        public static CFString Tempo = new CFString("tempo");
        public static CFString KeySignature = new CFString("key signature");
        public static CFString TimeSignature = new CFString("time signature");
        public static CFString TrackNumber = new CFString("track number");
        public static CFString Year = new CFString("year");
        public static CFString Composer = new CFString("composer");
        public static CFString Lyricist = new CFString("lyricist");
        public static CFString Genre = new CFString("genre");
        public static CFString Title = new CFString("title");
        public static CFString RecordedDate = new CFString("recorded date");
        public static CFString Comments = new CFString("comments");
        public static CFString Copyright = new CFString("copyright");
        public static CFString SourceEncoder = new CFString("source encoder");
        public static CFString EncodingApplication = new CFString("encoding application");
        public static CFString NominalBitRate = new CFString("nominal bit rate");
        public static CFString ChannelLayout = new CFString("channel layout");
        public static CFString ApproximateDurationInSeconds = new CFString("approximate duration in seconds");
        public static CFString SourceBitDepth = new CFString("source bit depth");
        public static CFString ISRC = new CFString("ISRC");
        public static CFString SubTitle = new CFString("subtitle");
    }
}
