/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSXMLParserDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSXMLParserDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("parserDidStartDocument:")
    public void didStartDocument(NSXMLParser parser) { throw new UnsupportedOperationException(); }
    @NotImplemented("parserDidEndDocument:")
    public void didEndDocument(NSXMLParser parser) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundNotationDeclarationWithName:publicID:systemID:")
    public void foundNotationDeclaration(NSXMLParser parser, String name, String publicID, String systemID) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundUnparsedEntityDeclarationWithName:publicID:systemID:notationName:")
    public void foundUnparsedEntityDeclaration(NSXMLParser parser, String name, String publicID, String systemID, String notationName) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundAttributeDeclarationWithName:forElement:type:defaultValue:")
    public void foundAttributeDeclaration(NSXMLParser parser, String attributeName, String elementName, String type, String defaultValue) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundElementDeclarationWithName:model:")
    public void foundElementDeclaration(NSXMLParser parser, String elementName, String model) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundInternalEntityDeclarationWithName:value:")
    public void foundInternalEntityDeclaration(NSXMLParser parser, String name, String value) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundExternalEntityDeclarationWithName:publicID:systemID:")
    public void foundExternalEntityDeclaration(NSXMLParser parser, String name, String publicID, String systemID) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:didStartElement:namespaceURI:qualifiedName:attributes:")
    public void didStartElement(NSXMLParser parser, String elementName, String namespaceURI, String qName, @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSObject> attributeDict) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:didEndElement:namespaceURI:qualifiedName:")
    public void didEndElement(NSXMLParser parser, String elementName, String namespaceURI, String qName) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:didStartMappingPrefix:toURI:")
    public void didStartMappingPrefix(NSXMLParser parser, String prefix, String namespaceURI) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:didEndMappingPrefix:")
    public void didEndMappingPrefix(NSXMLParser parser, String prefix) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundCharacters:")
    public void foundCharacters(NSXMLParser parser, String string) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundIgnorableWhitespace:")
    public void foundIgnorableWhitespace(NSXMLParser parser, String whitespaceString) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundProcessingInstructionWithTarget:data:")
    public void foundProcessingInstruction(NSXMLParser parser, String target, String data) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundComment:")
    public void foundComment(NSXMLParser parser, String comment) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:foundCDATA:")
    public void foundCDATA(NSXMLParser parser, NSData CDATABlock) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:resolveExternalEntityName:systemID:")
    public NSData resolveExternalEntityName(NSXMLParser parser, String name, String systemID) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:parseErrorOccurred:")
    public void parseErrorOccurred(NSXMLParser parser, NSError parseError) { throw new UnsupportedOperationException(); }
    @NotImplemented("parser:validationErrorOccurred:")
    public void validationErrorOccurred(NSXMLParser parser, NSError validationError) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
