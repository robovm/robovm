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

public class US_ASCII extends Charset {
	
	public US_ASCII(String csName, String[] aliases){
		super(csName, aliases);
	}

	/* (non-Javadoc)
	 * @see java.nio.charset.Charset#newEncoder()
	 */
	public CharsetEncoder newEncoder() {
		return new Encoder(this);
	}

	/* (non-Javadoc)
	 * @see java.nio.charset.Charset#newDecoder()
	 */
	public CharsetDecoder newDecoder() {
		return new Decoder(this);
	}

	/* (non-Javadoc)
	 * @see java.nio.charset.Charset#contains(java.nio.charset.Charset)
	 */
	public boolean contains(Charset cs) {
		return cs instanceof US_ASCII;
	}

	private final class Decoder extends CharsetDecoder{
		private Decoder(Charset cs){
			super(cs, 1, 1);
		}
		
		private native int nDecode(char[] array, int arrPosition, int remaining, long outAddr, int absolutePos);

		protected CoderResult decodeLoop(ByteBuffer bb, CharBuffer cb){
                    if(CharsetProviderImpl.hasLoadedNatives() && bb.isDirect() && bb.hasRemaining() && cb.hasArray()){
                        int toProceed = bb.remaining();
                        boolean throwOverflow = false; 
                        if( cb.remaining() < toProceed ) { 
                            toProceed = cb.remaining();
                            throwOverflow = true;
                        }
                        int res = nDecode(cb.array(), cb.arrayOffset()+cb.position(), toProceed, AddressUtil.getDirectBufferAddress(bb), bb.position());
                        if( res <= 0 ) {                                     
                            bb.position(bb.position()-res);            
                            cb.position(cb.position()-res);          
                            return CoderResult.unmappableForLength(1);
                        }else{                                 
                            cb.position(cb.position()+res);  
                            bb.position(bb.position()+res);
                            if(throwOverflow) return CoderResult.OVERFLOW;
                        } 
                    }else{
                        int cbRemaining = cb.remaining(); 
                        if(bb.hasArray() && cb.hasArray()) {
                            int rem = bb.remaining();
                            rem = cbRemaining >= rem ? rem : cbRemaining;
                            byte[] bArr = bb.array();
                            char[] cArr = cb.array();
                            int bStart = bb.position();
                            int cStart = cb.position();
                            int i;
                            for(i=bStart; i<bStart+rem; i++) {
                                byte b = bArr[i];
                                if( b < 0 ) {
                                    bb.position(i); cb.position(cStart);
                                    return CoderResult.malformedForLength(1); 
                                }
                                cArr[cStart++] = (char)b; 
                            }
                            bb.position(i); cb.position(cStart);
                            if(rem == cbRemaining && bb.hasRemaining()) return CoderResult.OVERFLOW;
                        } else {
                            while(bb.hasRemaining()){
                                if( cbRemaining == 0 ) return CoderResult.OVERFLOW;
                                byte b = bb.get(); 
                                if( b < 0 ) {
                                    bb.position(bb.position()-1);
                                    return CoderResult.malformedForLength(1); 
                                }
                                cb.put((char)b);
                                cbRemaining--;
                            }
                        }
                    }
                    return CoderResult.UNDERFLOW;
		}
	}
	
	private final class Encoder extends CharsetEncoder{

		private Encoder(Charset cs){
			super(cs, 1, 1);
		}
		
		private native void nEncode(long outAddr, int absolutePos, char[] array, int arrPosition, int[] res);

		protected CoderResult encodeLoop(CharBuffer cb, ByteBuffer bb){
                    int bbRemaining = bb.remaining();                                                                     
                    if(CharsetProviderImpl.hasLoadedNatives() && bb.isDirect() && cb.hasRemaining() && cb.hasArray()){                                                
                        int toProceed = cb.remaining();
                        boolean throwOverflow = false; 
                        int cbPos = cb.position();
                        int bbPos = bb.position();
                        if( bbRemaining < toProceed ) { 
                            toProceed = bbRemaining;
                            throwOverflow = true;
                        }
                        int[] res = {toProceed, 0};
                        nEncode(AddressUtil.getDirectBufferAddress(bb), bbPos, cb.array(), cb.arrayOffset()+cbPos, res);
                        if( res[0] <= 0 ) {                                                                                                                
                            bb.position(bbPos-res[0]);                                                                                        
                            cb.position(cbPos-res[0]);                                                                            
                            if(res[1]!=0) {
                                if(res[1] < 0)
                                    return CoderResult.malformedForLength(-res[1]);
                                else 
                                    return CoderResult.unmappableForLength(res[1]);
                            }
                        }else{                                          
                            bb.position(bbPos+res[0]);               
                            cb.position(cbPos+res[0]);                 
                            if(throwOverflow) return CoderResult.OVERFLOW;
                        }                                                     
                    }else{
                        if(bb.hasArray() && cb.hasArray()) {
                            byte[] byteArr = bb.array();
                            char[] charArr = cb.array();
                            int rem = cb.remaining();
                            int byteArrStart = bb.position();
                            rem = bbRemaining <= rem ? bbRemaining : rem;
                            int x;
                            for(x = cb.position(); x < cb.position()+rem; x++) {
                                char c = charArr[x];
                                if(c > Byte.MAX_VALUE){
                                    if (c >= 0xD800 && c <= 0xDFFF) {
                                        if(x+1 < cb.limit()) {
                                            char c1 = charArr[x+1];
                                            if(c1 >= 0xD800 && c1 <= 0xDFFF) {
                                                cb.position(x); bb.position(byteArrStart);
                                                return CoderResult.unmappableForLength(2);             
                                            } 
                                        } else {
                                            cb.position(x); bb.position(byteArrStart);
                                            return CoderResult.UNDERFLOW;             
                                        }
                                        cb.position(x); bb.position(byteArrStart);
                                        return CoderResult.malformedForLength(1);             
                                    }
                                    cb.position(x); bb.position(byteArrStart);
                                    return CoderResult.unmappableForLength(1);
                                }
                                byteArr[byteArrStart++] = (byte)c;
                            }
                            cb.position(x);
                            bb.position(byteArrStart);
                            if(rem == bbRemaining && cb.hasRemaining()) {
                                return CoderResult.OVERFLOW;
                            }
                        } else {
                            while(cb.hasRemaining()){
                                if( bbRemaining == 0 ) return CoderResult.OVERFLOW;
                                char c = cb.get(); 
                                if(c > Byte.MAX_VALUE){
                                    if (c >= 0xD800 && c <= 0xDFFF) {
                                        if(cb.hasRemaining()) {
                                            char c1 = cb.get();
                                            if(c1 >= 0xD800 && c1 <= 0xDFFF) {
                                                cb.position(cb.position()-2);
                                                return CoderResult.unmappableForLength(2);             
                                            } else {
                                            	cb.position(cb.position()-1);
                                            }
                                        } else {
                                            cb.position(cb.position()-1);
                                            return CoderResult.UNDERFLOW;             
                                        }
                                        cb.position(cb.position()-1);
                                        return CoderResult.malformedForLength(1);             
                                    }
                                    cb.position(cb.position()-1);
                                    return CoderResult.unmappableForLength(1);
                                }
                                bb.put((byte)c);
                                bbRemaining--;
                            }
                        }
                    }
                    return CoderResult.UNDERFLOW;
		}
	}
}
