/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package libcore.java.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;

public class OldCharset_MultiByte_EUC_JP extends OldCharset_AbstractTest {
  @Override protected void setUp() throws Exception {
    charsetName = "EUC-JP";
    testChars = "東京 とうきょう トウキョウ Tokyo 123".toCharArray();
    testBytes = theseBytes(0xc5, 0xec, 0xb5, 0xfe, ' ',
                           0xa4, 0xc8, 0xa4, 0xa6, 0xa4, 0xad, 0xa4, 0xe7, 0xa4, 0xa6, ' ',
                           0xa5, 0xc8, 0xa5, 0xa6, 0xa5, 0xad, 0xa5, 0xe7, 0xa5, 0xa6, ' ',
                           'T', 'o', 'k', 'y', 'o', ' ', '1', '2', '3');
    super.setUp();
  }

  @Override public void test_CodecDynamic() throws CharacterCodingException {
    encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
    decoder.onMalformedInput(CodingErrorAction.REPORT);
    CharBuffer inputCB = CharBuffer.allocate(65536);
    for (char codePoint = 0; codePoint <= 0xfffe; ++codePoint) {
      if (encoder.canEncode(codePoint)) {
        inputCB.put(codePoint);
      }
    }
    inputCB.rewind();
    ByteBuffer intermediateBB = encoder.encode(inputCB);
    inputCB.rewind();
    intermediateBB.rewind();
    CharBuffer outputCB = decoder.decode(intermediateBB);
    outputCB.rewind();
    assertEqualCBs("decode(encode(A)) must be identical with A!", inputCB, outputCB);
  }
}
