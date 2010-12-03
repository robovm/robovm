/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// $Id: XMLStreamConstants.java 670269 2008-06-21 23:27:48Z mrglavas $

package javax.xml.stream;

public interface XMLStreamConstants {
    
    public static final int START_ELEMENT = 1;
    public static final int END_ELEMENT = 2;
    public static final int PROCESSING_INSTRUCTION = 3;
    public static final int CHARACTERS = 4;
    public static final int COMMENT = 5;
    public static final int SPACE = 6;
    public static final int START_DOCUMENT = 7;
    public static final int END_DOCUMENT = 8;
    public static final int ENTITY_REFERENCE = 9;
    public static final int ATTRIBUTE = 10;
    public static final int DTD = 11;
    public static final int CDATA = 12;
    public static final int NAMESPACE = 13;
    public static final int NOTATION_DECLARATION = 14;
    public static final int ENTITY_DECLARATION = 15;
    
}