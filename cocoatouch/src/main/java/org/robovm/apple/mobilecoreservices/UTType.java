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
package org.robovm.apple.mobilecoreservices;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MobileCoreServices")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UTType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UTType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Library("MobileCoreServices")
    public static class AbstractBase {
        static { Bro.bind(AbstractBase.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeItem", optional=true)
        public static native String Item();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeContent", optional=true)
        public static native String Content();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeCompositeContent", optional=true)
        public static native String CompositeContent();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeMessage", optional=true)
        public static native String Message();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeContact", optional=true)
        public static native String Contact();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeArchive", optional=true)
        public static native String Archive();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeDiskImage", optional=true)
        public static native String DiskImage();
    }

    @Library("MobileCoreServices")
    public static class ConcreteBase {
        static { Bro.bind(ConcreteBase.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeData", optional=true)
        public static native String Data();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeDirectory", optional=true)
        public static native String Directory();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeResolvable", optional=true)
        public static native String Resolvable();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeSymLink", optional=true)
        public static native String SymLink();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeExecutable", optional=true)
        public static native String Executable();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeMountPoint", optional=true)
        public static native String MountPoint();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAliasFile", optional=true)
        public static native String AliasFile();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAliasRecord", optional=true)
        public static native String AliasRecord();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeURLBookmarkData", optional=true)
        public static native String URLBookmarkData();
    }

    @Library("MobileCoreServices")
    public static class URL {
        static { Bro.bind(URL.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeURL", optional=true)
        public static native String URL();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeFileURL", optional=true)
        public static native String FileURL();
    }

    @Library("MobileCoreServices")
    public static class Text {
        static { Bro.bind(Text.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeText", optional=true)
        public static native String Text();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypePlainText", optional=true)
        public static native String PlainText();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeUTF8PlainText", optional=true)
        public static native String UTF8PlainText();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeUTF16ExternalPlainText", optional=true)
        public static native String UTF16ExternalPlainText();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeUTF16PlainText", optional=true)
        public static native String UTF16PlainText();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeDelimitedText", optional=true)
        public static native String DelimitedText();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeCommaSeparatedText", optional=true)
        public static native String CommaSeparatedText();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeTabSeparatedText", optional=true)
        public static native String TabSeparatedText();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeUTF8TabSeparatedText", optional=true)
        public static native String UTF8TabSeparatedText();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeRTF", optional=true)
        public static native String RTF();
    }

    @Library("MobileCoreServices")
    public static class MarkupLanguage {
        static { Bro.bind(MarkupLanguage.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeHTML", optional=true)
        public static native String HTML();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeXML", optional=true)
        public static native String XML();
    }

    @Library("MobileCoreServices")
    public static class ProgrammingLanguage {
        static { Bro.bind(ProgrammingLanguage.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeSourceCode", optional=true)
        public static native String SourceCode();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAssemblyLanguageSource", optional=true)
        public static native String AssemblyLanguageSource();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeCSource", optional=true)
        public static native String CSource();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeObjectiveCSource", optional=true)
        public static native String ObjectiveCSource();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeCPlusPlusSource", optional=true)
        public static native String CPlusPlusSource();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeObjectiveCPlusPlusSource", optional=true)
        public static native String ObjectiveCPlusPlusSource();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeCHeader", optional=true)
        public static native String CHeader();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeCPlusPlusHeader", optional=true)
        public static native String CPlusPlusHeader();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeJavaSource", optional=true)
        public static native String JavaSource();
    }

    @Library("MobileCoreServices")
    public static class ScriptingLanguage {
        static { Bro.bind(ScriptingLanguage.class); }

        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeScript", optional=true)
        public static native String Script();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAppleScript", optional=true)
        public static native String AppleScript();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeOSAScript", optional=true)
        public static native String OSAScript();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeOSAScriptBundle", optional=true)
        public static native String OSAScriptBundle();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeJavaScript", optional=true)
        public static native String JavaScript();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeShellScript", optional=true)
        public static native String ShellScript();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypePerlScript", optional=true)
        public static native String PerlScript();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypePythonScript", optional=true)
        public static native String PythonScript();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeRubyScript", optional=true)
        public static native String RubyScript();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypePHPScript", optional=true)
        public static native String PHPScript();
    }

    @Library("MobileCoreServices")
    public static class SerializedData {
        static { Bro.bind(SerializedData.class); }

        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeJSON", optional=true)
        public static native String JSON();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypePropertyList", optional=true)
        public static native String PropertyList();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeXMLPropertyList", optional=true)
        public static native String XMLPropertyList();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeBinaryPropertyList", optional=true)
        public static native String BinaryPropertyList();
    }

    @Library("MobileCoreServices")
    public static class CompositeContent {
        static { Bro.bind(CompositeContent.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypePDF", optional=true)
        public static native String PDF();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeRTFD", optional=true)
        public static native String RTFD();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeFlatRTFD", optional=true)
        public static native String FlatRTFD();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeTXNTextAndMultimediaData", optional=true)
        public static native String TXNTextAndMultimediaData();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeWebArchive", optional=true)
        public static native String WebArchive();
    }

    @Library("MobileCoreServices")
    public static class ImageContent {
        static { Bro.bind(ImageContent.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeImage", optional=true)
        public static native String Image();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeJPEG", optional=true)
        public static native String JPEG();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeJPEG2000", optional=true)
        public static native String JPEG2000();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeTIFF", optional=true)
        public static native String TIFF();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypePICT", optional=true)
        public static native String PICT();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeGIF", optional=true)
        public static native String GIF();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypePNG", optional=true)
        public static native String PNG();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeQuickTimeImage", optional=true)
        public static native String QuickTimeImage();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAppleICNS", optional=true)
        public static native String AppleICNS();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeBMP", optional=true)
        public static native String BMP();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeICO", optional=true)
        public static native String ICO();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeRawImage", optional=true)
        public static native String RawImage();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeScalableVectorGraphics", optional=true)
        public static native String ScalableVectorGraphics();
    }

    @Library("MobileCoreServices")
    public static class AudiovisualContent {
        static { Bro.bind(AudiovisualContent.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAudiovisualContent", optional=true)
        public static native String AudiovisualContent();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeMovie", optional=true)
        public static native String Movie();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeVideo", optional=true)
        public static native String Video();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAudio", optional=true)
        public static native String Audio();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeQuickTimeMovie", optional=true)
        public static native String QuickTimeMovie();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeMPEG", optional=true)
        public static native String MPEG();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeMPEG2Video", optional=true)
        public static native String MPEG2Video();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeMPEG2TransportStream", optional=true)
        public static native String MPEG2TransportStream();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeMP3", optional=true)
        public static native String MP3();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeMPEG4", optional=true)
        public static native String MPEG4();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeMPEG4Audio", optional=true)
        public static native String MPEG4Audio();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAppleProtectedMPEG4Audio", optional=true)
        public static native String AppleProtectedMPEG4Audio();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAppleProtectedMPEG4Video", optional=true)
        public static native String AppleProtectedMPEG4Video();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAVIMovie", optional=true)
        public static native String AVIMovie();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeAudioInterchangeFileFormat", optional=true)
        public static native String AudioInterchangeFileFormat();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeWaveformAudio", optional=true)
        public static native String WaveformAudio();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeMIDIAudio", optional=true)
        public static native String MIDIAudio();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypePlaylist", optional=true)
        public static native String Playlist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeM3UPlaylist", optional=true)
        public static native String M3UPlaylist();
    }

    @Library("MobileCoreServices")
    public static class Directory {
        static { Bro.bind(Directory.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeFolder", optional=true)
        public static native String Folder();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeVolume", optional=true)
        public static native String Volume();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypePackage", optional=true)
        public static native String Package();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeBundle", optional=true)
        public static native String Bundle();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypePluginBundle", optional=true)
        public static native String PluginBundle();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeSpotlightImporter", optional=true)
        public static native String SpotlightImporter();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeQuickLookGenerator", optional=true)
        public static native String QuickLookGenerator();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeXPCService", optional=true)
        public static native String XPCService();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeFramework", optional=true)
        public static native String Framework();
    }

    @Library("MobileCoreServices")
    public static class ApplicationAndExecutable {
        static { Bro.bind(ApplicationAndExecutable.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeApplication", optional=true)
        public static native String Application();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeApplicationBundle", optional=true)
        public static native String ApplicationBundle();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeApplicationFile", optional=true)
        public static native String ApplicationFile();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeUnixExecutable", optional=true)
        public static native String UnixExecutable();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeWindowsExecutable", optional=true)
        public static native String WindowsExecutable();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeJavaClass", optional=true)
        public static native String JavaClass();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeJavaArchive", optional=true)
        public static native String JavaArchive();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeSystemPreferencesPane", optional=true)
        public static native String SystemPreferencesPane();
    }

    @Library("MobileCoreServices")
    public static class Archive {
        static { Bro.bind(Archive.class); }

        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeGNUZipArchive", optional=true)
        public static native String GNUZipArchive();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeBzip2Archive", optional=true)
        public static native String Bzip2Archive();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeZipArchive", optional=true)
        public static native String ZipArchive();
    }

    @Library("MobileCoreServices")
    public static class Document {
        static { Bro.bind(Document.class); }

        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeSpreadsheet", optional=true)
        public static native String Spreadsheet();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypePresentation", optional=true)
        public static native String Presentation();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeDatabase", optional=true)
        public static native String Database();
    }

    @Library("MobileCoreServices")
    public static class Contact {
        static { Bro.bind(Contact.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeVCard", optional=true)
        public static native String VCard();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeToDoItem", optional=true)
        public static native String ToDoItem();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeCalendarEvent", optional=true)
        public static native String CalendarEvent();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeEmailMessage", optional=true)
        public static native String EmailMessage();
    }

    @Library("MobileCoreServices")
    public static class Internet {
        static { Bro.bind(Internet.class); }

        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeInternetLocation", optional=true)
        public static native String InternetLocation();
    }

    @Library("MobileCoreServices")
    public static class Miscellaneous {
        static { Bro.bind(Miscellaneous.class); }

        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeInkText", optional=true)
        public static native String InkText();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeFont", optional=true)
        public static native String Font();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeBookmark", optional=true)
        public static native String Bookmark();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTType3DContent", optional=true)
        public static native String _3DContent();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypePKCS12", optional=true)
        public static native String PKCS12();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeX509Certificate", optional=true)
        public static native String X509Certificate();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeElectronicPublication", optional=true)
        public static native String ElectronicPublication();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kUTTypeLog", optional=true)
        public static native String Log();
    }
    
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="UTTypeCreatePreferredIdentifierForTag", optional=true)
    public static native String createPreferredIdentifierForTag(UTTagClass inTagClass, String inTag, String inConformingToUTI);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="UTTypeCreateAllIdentifiersForTag", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> createAllIdentifiersForTag(UTTagClass inTagClass, String inTag, String inConformingToUTI);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="UTTypeCopyPreferredTagWithClass", optional=true)
    public static native String getPreferredTagWithClass(String inUTI, UTTagClass inTagClass);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UTTypeCopyAllTagsWithClass", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getAllTagsWithClass(String inUTI, UTTagClass inTagClass);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="UTTypeEqual", optional=true)
    public static native boolean typeEqualsTo(String inUTI1, String inUTI2);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="UTTypeConformsTo", optional=true)
    public static native boolean typeConformsTo(String inUTI, String inConformsToUTI);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="UTTypeCopyDescription", optional=true)
    public static native String getDescriptionForType(String inUTI);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UTTypeIsDeclared", optional=true)
    public static native boolean typeIsDeclared(String inUTI);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UTTypeIsDynamic", optional=true)
    public static native boolean typeIsDynamic(String inUTI);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="UTTypeCopyDeclaration", optional=true)
    public static native UTTypeDeclaration getDeclarationForType(String inUTI);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="UTTypeCopyDeclaringBundleURL", optional=true)
    public static native NSURL getDeclaringBundleURLForType(String inUTI);
    /*</methods>*/
}
