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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
@ForceLinkClass(NSXMLParserError.class)
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/NSXMLParserErrorCode/*</name>*/ implements NSErrorCode {
    /*<values>*/
    Internal(1L),
    OutOfMemory(2L),
    DocumentStart(3L),
    EmptyDocument(4L),
    PrematureDocumentEnd(5L),
    InvalidHexCharacterRef(6L),
    InvalidDecimalCharacterRef(7L),
    InvalidCharacterRef(8L),
    InvalidCharacter(9L),
    CharacterRefAtEOF(10L),
    CharacterRefInProlog(11L),
    CharacterRefInEpilog(12L),
    CharacterRefInDTD(13L),
    EntityRefAtEOF(14L),
    EntityRefInProlog(15L),
    EntityRefInEpilog(16L),
    EntityRefInDTD(17L),
    ParsedEntityRefAtEOF(18L),
    ParsedEntityRefInProlog(19L),
    ParsedEntityRefInEpilog(20L),
    ParsedEntityRefInInternalSubset(21L),
    EntityReferenceWithoutName(22L),
    EntityReferenceMissingSemi(23L),
    ParsedEntityRefNoName(24L),
    ParsedEntityRefMissingSemi(25L),
    UndeclaredEntity(26L),
    UnparsedEntity(28L),
    EntityIsExternal(29L),
    EntityIsParameter(30L),
    UnknownEncoding(31L),
    EncodingNotSupported(32L),
    StringNotStarted(33L),
    StringNotClosed(34L),
    NamespaceDeclaration(35L),
    EntityNotStarted(36L),
    EntityNotFinished(37L),
    LessThanSymbolInAttribute(38L),
    AttributeNotStarted(39L),
    AttributeNotFinished(40L),
    AttributeHasNoValue(41L),
    AttributeRedefined(42L),
    LiteralNotStarted(43L),
    LiteralNotFinished(44L),
    CommentNotFinished(45L),
    ProcessingInstructionNotStarted(46L),
    ProcessingInstructionNotFinished(47L),
    NotationNotStarted(48L),
    NotationNotFinished(49L),
    AttributeListNotStarted(50L),
    AttributeListNotFinished(51L),
    MixedContentDeclNotStarted(52L),
    MixedContentDeclNotFinished(53L),
    ElementContentDeclNotStarted(54L),
    ElementContentDeclNotFinished(55L),
    XMLDeclNotStarted(56L),
    XMLDeclNotFinished(57L),
    ConditionalSectionNotStarted(58L),
    ConditionalSectionNotFinished(59L),
    ExternalSubsetNotFinished(60L),
    DOCTYPEDeclNotFinished(61L),
    MisplacedCDATAEndString(62L),
    CDATANotFinished(63L),
    MisplacedXMLDeclaration(64L),
    SpaceRequired(65L),
    SeparatorRequired(66L),
    NMTOKENRequired(67L),
    NAMERequired(68L),
    PCDATARequired(69L),
    URIRequired(70L),
    PublicIdentifierRequired(71L),
    LTRequired(72L),
    GTRequired(73L),
    LTSlashRequired(74L),
    EqualExpected(75L),
    TagNameMismatch(76L),
    UnfinishedTag(77L),
    StandaloneValue(78L),
    InvalidEncodingName(79L),
    CommentContainsDoubleHyphen(80L),
    InvalidEncoding(81L),
    ExternalStandaloneEntity(82L),
    InvalidConditionalSection(83L),
    EntityValueRequired(84L),
    NotWellBalanced(85L),
    ExtraContent(86L),
    InvalidCharacterInEntity(87L),
    ParsedEntityRefInInternal(88L),
    EntityRefLoop(89L),
    EntityBoundary(90L),
    InvalidURI(91L),
    URIFragment(92L),
    NoDTD(94L),
    DelegateAbortedParse(512L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/NSXMLParserErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSXMLParserErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSXMLParserErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/NSXMLParserErrorCode/*</name>*/.class.getName());
    }
}
