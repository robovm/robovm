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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/NSXMLParserDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "parserDidStartDocument:")
    void parserDidStartDocument$(NSXMLParser parser);
    @Method(selector = "parserDidEndDocument:")
    void parserDidEndDocument$(NSXMLParser parser);
    @Method(selector = "parser:foundNotationDeclarationWithName:publicID:systemID:")
    void parser$foundNotationDeclarationWithName$publicID$systemID$(NSXMLParser parser, String name, String publicID, String systemID);
    @Method(selector = "parser:foundUnparsedEntityDeclarationWithName:publicID:systemID:notationName:")
    void parser$foundUnparsedEntityDeclarationWithName$publicID$systemID$notationName$(NSXMLParser parser, String name, String publicID, String systemID, String notationName);
    @Method(selector = "parser:foundAttributeDeclarationWithName:forElement:type:defaultValue:")
    void parser$foundAttributeDeclarationWithName$forElement$type$defaultValue$(NSXMLParser parser, String attributeName, String elementName, String type, String defaultValue);
    @Method(selector = "parser:foundElementDeclarationWithName:model:")
    void parser$foundElementDeclarationWithName$model$(NSXMLParser parser, String elementName, String model);
    @Method(selector = "parser:foundInternalEntityDeclarationWithName:value:")
    void parser$foundInternalEntityDeclarationWithName$value$(NSXMLParser parser, String name, String value);
    @Method(selector = "parser:foundExternalEntityDeclarationWithName:publicID:systemID:")
    void parser$foundExternalEntityDeclarationWithName$publicID$systemID$(NSXMLParser parser, String name, String publicID, String systemID);
    @Method(selector = "parser:didStartElement:namespaceURI:qualifiedName:attributes:")
    void parser$didStartElement$namespaceURI$qualifiedName$attributes$(NSXMLParser parser, String elementName, String namespaceURI, String qName, NSDictionary<?, ?> attributeDict);
    @Method(selector = "parser:didEndElement:namespaceURI:qualifiedName:")
    void parser$didEndElement$namespaceURI$qualifiedName$(NSXMLParser parser, String elementName, String namespaceURI, String qName);
    @Method(selector = "parser:didStartMappingPrefix:toURI:")
    void parser$didStartMappingPrefix$toURI$(NSXMLParser parser, String prefix, String namespaceURI);
    @Method(selector = "parser:didEndMappingPrefix:")
    void parser$didEndMappingPrefix$(NSXMLParser parser, String prefix);
    @Method(selector = "parser:foundCharacters:")
    void parser$foundCharacters$(NSXMLParser parser, String string);
    @Method(selector = "parser:foundIgnorableWhitespace:")
    void parser$foundIgnorableWhitespace$(NSXMLParser parser, String whitespaceString);
    @Method(selector = "parser:foundProcessingInstructionWithTarget:data:")
    void parser$foundProcessingInstructionWithTarget$data$(NSXMLParser parser, String target, String data);
    @Method(selector = "parser:foundComment:")
    void parser$foundComment$(NSXMLParser parser, String comment);
    @Method(selector = "parser:foundCDATA:")
    void parser$foundCDATA$(NSXMLParser parser, NSData CDATABlock);
    @Method(selector = "parser:resolveExternalEntityName:systemID:")
    NSData parser$resolveExternalEntityName$systemID$(NSXMLParser parser, String name, String systemID);
    @Method(selector = "parser:parseErrorOccurred:")
    void parser$parseErrorOccurred$(NSXMLParser parser, NSError parseError);
    @Method(selector = "parser:validationErrorOccurred:")
    void parser$validationErrorOccurred$(NSXMLParser parser, NSError validationError);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
