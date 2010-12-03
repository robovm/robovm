/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.niochar.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import org.apache.harmony.nio.AddressUtil;
import org.apache.harmony.niochar.CharsetProviderImpl;


public class ISO_8859_1 extends Charset {

        public ISO_8859_1(String csName, String[] aliases) {
            super(csName, aliases);
        }

        public boolean contains(Charset cs) {
            return cs instanceof ISO_8859_1 || cs instanceof US_ASCII;
        }

        public CharsetDecoder newDecoder() {
            return new Decoder(this);
        }

        public CharsetEncoder newEncoder() {
            return new Encoder(this);
        }

	private final class Decoder extends CharsetDecoder{
		private Decoder(Charset cs){
			super(cs, 1, 1);

		}

		private native int nDecode(char[] array, int arrPosition, int remaining, long outAddr, int absolutePos);


		protected CoderResult decodeLoop(ByteBuffer bb, CharBuffer cb) {
			int bbRemaining = bb.remaining();
			if (bbRemaining == 0) {
				return CoderResult.UNDERFLOW;
			}
			int cbRemaining = cb.remaining();
			boolean cbHasArray = cb.hasArray();
			if (cbHasArray) {
				if (bb.hasArray()) {
					int rem = bbRemaining;
					rem = cbRemaining >= rem ? rem : cbRemaining;
					byte[] arr = bb.array();
					char[] cArr = cb.array();
					int bStart = bb.position();
					int cStart = cb.position();
					int i;
					for (i = bStart; i < bStart + rem; i++) {
						cArr[cStart++] = (char) ((int) arr[i] & 0xFF);
					}
					bb.position(i);
					cb.position(cStart);
					if (rem == cbRemaining && bb.hasRemaining()) {
						return CoderResult.OVERFLOW;
					}
					return CoderResult.UNDERFLOW;
				} else if (CharsetProviderImpl.hasLoadedNatives() && bb.isDirect()) {
					int toProceed = bbRemaining;
					boolean throwOverflow = false;
					int cbPos = cb.position();
					int bbPos = bb.position();
					if (cbRemaining < toProceed) {
						toProceed = cbRemaining;
						throwOverflow = true;
					}
					int res = nDecode(cb.array(), cb.arrayOffset() + cbPos,
							toProceed, AddressUtil.getDirectBufferAddress(bb),
							bbPos);
					cb.position(cbPos + res);
					bb.position(bbPos + res);
					if (throwOverflow) {
						return CoderResult.OVERFLOW;
					}
					return CoderResult.UNDERFLOW;
				}
			}
			int rem = bbRemaining;
			rem = cbRemaining >= rem ? rem : cbRemaining;
			byte[] arr = new byte[rem];
			bb.get(arr);
			char[] cArr = new char[rem];
			for (int i = 0; i < rem; i++) {
				cArr[i] = (char) ((int) arr[i] & 0xFF);
			}
			cb.put(cArr);
			if (cb.remaining() == 0) {
				return CoderResult.OVERFLOW;
			}
			return CoderResult.UNDERFLOW;
		}
	}

	private final class Encoder extends CharsetEncoder{
		private Encoder(Charset cs){
			super(cs, 1, 1);      
		}
                   
		private native void nEncode(long outAddr, int absolutePos, char[] array, int arrPosition, int[] res);
                                                                                                                          
		protected CoderResult encodeLoop(CharBuffer cb, ByteBuffer bb) {
			int cbRemaining = cb.remaining();
			if (cbRemaining == 0) {
				return CoderResult.UNDERFLOW;
			}
			int bbRemaining = bb.remaining();
			boolean cbHasArray = cb.hasArray();
			boolean bbHasArray = bb.hasArray();
			if (cbHasArray) {
				if (bbHasArray) {
					byte[] byteArr = bb.array();
					char[] charArr = cb.array();
					int byteArrStart = bb.position();
					int rem = bbRemaining <= cbRemaining ? bbRemaining
							: cbRemaining;
					int cbPos = cb.position();
					int x;
					int jchar = 0;
					for (x = cbPos; x < cbPos + rem; x++) {
						jchar = (int) charArr[x];
						if (jchar <= 0xFF) {
							byteArr[byteArrStart++] = (byte) jchar;
						} else {
							break;
						}
					}
					bb.position(byteArrStart);
					cb.position(x);
					if (x == cbPos + rem) {
						// everything is ok
						if (rem == bbRemaining && cb.hasRemaining()) {
							return CoderResult.OVERFLOW;
						}
						return CoderResult.UNDERFLOW;
					}
					// here is jchar >0xFF
					if (jchar >= 0xD800 && jchar <= 0xDFFF) {
						if (x + 1 < cb.limit()) {
							char c1 = charArr[x + 1];
							if (c1 >= 0xD800 && c1 <= 0xDFFF) {
								return CoderResult.unmappableForLength(2);
							}
						} else {
							return CoderResult.UNDERFLOW;
						}
						return CoderResult.malformedForLength(1);
					}
					return CoderResult.unmappableForLength(1);
				} else if (CharsetProviderImpl.hasLoadedNatives() && bb.isDirect()) {
					int toProceed = cbRemaining;
					boolean throwOverflow = false;
					int cbPos = cb.position();
					int bbPos = bb.position();
					if (bbRemaining < toProceed) {
						toProceed = bbRemaining;
						throwOverflow = true;
					}
					int[] res = { toProceed, 0 };
					nEncode(AddressUtil.getDirectBufferAddress(bb), bbPos, cb
							.array(), cb.arrayOffset() + cbPos, res);
					if (res[0] <= 0) {
						bb.position(bbPos - res[0]);
						cb.position(cbPos - res[0]);
						if (res[1] != 0) {
							if (res[1] < 0) {
								return CoderResult.malformedForLength(-res[1]);
							} else {
								return CoderResult.unmappableForLength(res[1]);
							}
						}
					} else {
						bb.position(bbPos + res[0]);
						cb.position(cbPos + res[0]);
						if (throwOverflow) {
							return CoderResult.OVERFLOW;
						}
					}
					return CoderResult.UNDERFLOW;
				}
			}
			while (cb.hasRemaining()) {
				if (bbRemaining == 0) {
					return CoderResult.OVERFLOW;
				}
				char c = cb.get();
				if (c > (char) 0x00FF) {
					if (c >= 0xD800 && c <= 0xDFFF) {
						if (cb.hasRemaining()) {
							char c1 = cb.get();
							if (c1 >= 0xD800 && c1 <= 0xDFFF) {
								cb.position(cb.position() - 2);
								return CoderResult.unmappableForLength(2);
							} else {
								cb.position(cb.position() - 1);
							}
						} else {
							cb.position(cb.position() - 1);
							return CoderResult.UNDERFLOW;
						}
						cb.position(cb.position() - 1);
						return CoderResult.malformedForLength(1);
					}
					cb.position(cb.position() - 1);
					return CoderResult.unmappableForLength(1);
				} else {
					bb.put((byte) c);
					bbRemaining--;
				}
			}
			return CoderResult.UNDERFLOW;
		}                                                                                     
                                                                                                      
	}         
}
