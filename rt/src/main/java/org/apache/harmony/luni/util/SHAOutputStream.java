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

package org.apache.harmony.luni.util;


import java.io.OutputStream;

/**
 * This class implements the Secure Hash Algorithm, SHA-1. The specification can
 * be found at http://csrc.ncsl.nist.gov/fips/fip180-1.txt
 */
public class SHAOutputStream extends OutputStream implements Cloneable {

	/* Constants as in the specification */

	// K in iterations 0..19, from spec
	private static final int K0_19 = 0x5a827999;

	// K in iterations 20..39, from spec
	private static final int K20_39 = 0x6ed9eba1;

	// K in iterations 40..59, from spec
	private static final int K40_59 = 0x8f1bbcdc;

	// K in iterations 60..79, from spec
	private static final int K60_79 = 0xca62c1d6;

	// H0, from spec
	private static final int H0 = 0x67452301;

	// H1, from spec
	private static final int H1 = 0xefcdab89;

	// H2, from spec
	private static final int H2 = 0x98badcfe;

	// H3, from spec
	private static final int H3 = 0x10325476;

	// H4, from spec
	private static final int H4 = 0xc3d2e1f0;

	private static final int HConstantsSize = 5;

	private static final int HashSizeInBytes = 20;

	// 16 words
	private static final int BlockSizeInBytes = 16 * 4;

	// 80 words
	private static final int WArraySize = 80;
    
    // 5-word Array. Starts with well-known constants, ends with SHA
    private int[] HConstants;

    // 80-word Array.
    private int[] WArray;

    // 16-word Array. Input bit stream M is divided in chunks of MArray
    private byte[] MArray;

    // Number of bytes of input already processed towards SHA result
    private long bytesProcessed;

    // Number of bytes in WArray not processed yet
    private int bytesToProcess;

    // Optimization, for write
    private byte[] oneByte = new byte[1];

	/**
	 * Constructs a new SHAOutputStream.
	 */
	public SHAOutputStream() {
		super();
		initialize();
		reset();
	}

	/**
     * Constructs a new MD5OutputStream with the given initial state.
     * 
     * @param state The initial state of the output stream. This is what will be
     *        returned by getHash() if write() is never called.
     * 
     * @throws IllegalArgumentException if state.length is less than 16.
     */
	public SHAOutputStream(byte[] state) {
		this();

		if (state.length < HashSizeInBytes) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < 4; i++) {
			HConstants[i] = 0;
			for (int j = 0; j < 4; j++) {
				HConstants[i] += (state[4 * i + j] & 0xFF) << 8 * (3 - j);
			}
		}
	}

	/**
     * Answers a new instance of the same class as the receiver, whose slots
     * have been filled in with the values in the slots of the receiver.
     * <p>
     * Classes which wish to support cloning must specify that they implement
     * the Cloneable interface, since the native implementation checks for this.
     * 
     * @return a complete copy of this object
     * @throws CloneNotSupportedException if the component does not implement
     *         the interface Cloneable
     */
	@Override
    public Object clone() throws CloneNotSupportedException {
		// Calling super takes care of primitive type slots
		SHAOutputStream result = (SHAOutputStream) super.clone();
		result.HConstants = this.HConstants.clone();
		result.WArray = this.WArray.clone();
		result.MArray = this.MArray.clone();
		result.oneByte = this.oneByte.clone();
		return result;
	}

	/**
     * Copies a byte array into the receiver's internal buffer, making the
     * adjustments as necessary and keeping the receiver in a consistent state.
     * 
     * @param buffer byte array representation of the bytes
     * @param off offset into the source buffer where to start the copying
     * @param len how many bytes in the source byte array to copy
     * 
     */
	private void copyToInternalBuffer(byte[] buffer, int off, int len) {
		int index;
		index = off;
		for (int i = bytesToProcess; i < bytesToProcess + len; i++) {
			MArray[i] = buffer[index];
			index++;
		}
		bytesToProcess = bytesToProcess + len;
	}

	/**
     * Returns an int array (length = 5) with the SHA value of the bytes written
     * to the receiver.
     * 
     * @return The 5 ints that form the SHA value of the bytes written to
     *         the receiver
     */
	public int[] getHash() {
		this.padBuffer();
		this.processBuffer();
		int[] result = HConstants.clone();
		// After the user asks for the hash value, the stream is put back to the
		// initial state
		reset();
		return result;
	}

	/**
	 * Returns a byte array (length = 20) with the SHA value of the bytes
	 * written to the receiver.
	 * 
	 * @return The bytes that form the SHA value of the bytes written to
	 *         the receiver
	 */
	public byte[] getHashAsBytes() {
		byte[] hash = new byte[HashSizeInBytes];
		this.padBuffer();
		this.processBuffer();

		// We need to return HConstants (modified by the loop) as an array of
		// bytes. A memcopy would be the fastest.
		for (int i = 0; i < (HashSizeInBytes / 4); ++i) {
			hash[i * 4] = (byte) (HConstants[i] >>> 24 & 0xff);
			hash[i * 4 + 1] = (byte) (HConstants[i] >>> 16 & 0xff);
			hash[i * 4 + 2] = (byte) (HConstants[i] >>> 8 & 0xff);
			hash[i * 4 + 3] = (byte) (HConstants[i] & 0xff);
		}
		// After the user asks for the hash value, the stream is put back to the
		// initial state
		reset();
		return hash;
	}

	/**
	 * Returns a byte array (length = 20) with the SHA value of the bytes
	 * written to the receiver.
	 * 
	 * @return The bytes that form the SHA value of the bytes written to
	 *         the receiver
	 */
	public byte[] getHashAsBytes(boolean pad) {
		byte[] hash = new byte[HashSizeInBytes];
		if (pad) {
			this.padBuffer();
			this.processBuffer();
		}

		// Convert HConstants to an array of bytes
		for (int i = 0; i < (HashSizeInBytes / 4); i++) {
			hash[i * 4] = (byte) (HConstants[i] >>> 24 & 0xff);
			hash[i * 4 + 1] = (byte) (HConstants[i] >>> 16 & 0xff);
			hash[i * 4 + 2] = (byte) (HConstants[i] >>> 8 & 0xff);
			hash[i * 4 + 3] = (byte) (HConstants[i] & 0xff);
		}
		// After the user asks for the hash value, the stream is put back to the
		// initial state
		reset();
		return hash;
	}

	/**
	 * Initializes the receiver.
	 */
	private void initialize() {
		HConstants = new int[HConstantsSize];
		MArray = new byte[BlockSizeInBytes];
		WArray = new int[WArraySize];
	}

	/**
	 * Adds extra bytes to the bit stream as required (see the SHA
	 * specification).
	 */
	private void padBuffer() {
		long lengthInBits;
		MArray[bytesToProcess] = (byte) 0x80;
		for (int i = bytesToProcess + 1; i < BlockSizeInBytes; i++) {
			MArray[i] = (byte) 0;
		}
		// Get length now because there might be extra padding (length in bits)
		lengthInBits = (bytesToProcess + bytesProcessed) * 8;

		// 9 extra bytes are needed: 1 for 1000... and 8 for length (long)
		if ((bytesToProcess + 9) > BlockSizeInBytes) {
			// Not enough space to append length. We need another block for
			// padding
			// Padding in this buffer only includes 1000000....
			this.processBuffer();
			// Now put 0's in all the buffer. memfill would be faster
			for (int i = 0; i < BlockSizeInBytes; i++) {
				MArray[i] = (byte) 0;
			}
		}

		for (int i = 1; i < 9; i++) {
			MArray[BlockSizeInBytes - i] = (byte) (lengthInBits & 0xff);
			lengthInBits = lengthInBits >>> 8;
		}
	}

	/**
	 * Core SHA code. Processes the receiver's buffer of bits, computing the
	 * values towards the final SHA
	 */
	private void processBuffer() {
		int A; // A variable, from spec
		int B; // B variable, from spec
		int C; // C variable, from spec
		int D; // D variable, from spec
		int E; // E variable, from spec
		int temp; // TEMP, from spec
		int t; // t, for iteration, from spec

		for (t = 0; t <= 15; t++) { // step a, page 7 of spec. Here we convert 4
			// bytes into 1 word, 16 times
			WArray[t] = (MArray[4 * t] & 0xff) << 24
					| ((MArray[4 * t + 1] & 0xff) << 16)
					| ((MArray[4 * t + 2] & 0xff) << 8)
					| (MArray[4 * t + 3] & 0xff);
		}
		for (t = 16; t <= 79; t++) { // step b, page 7 of spec
			temp = (WArray[t - 3] ^ WArray[t - 8] ^ WArray[t - 14] ^ WArray[t - 16]);
			temp = (temp << 1) | (temp >>> (32 - 1)); // element , Circular
			// Shift Left by 1
			WArray[t] = temp;
		}

		// step c, page 7 of spec
		A = HConstants[0];
		B = HConstants[1];
		C = HConstants[2];
		D = HConstants[3];
		E = HConstants[4];

		// step d, page 8 of spec
		for (t = 0; t <= 19; t++) {
			temp = (A << 5) | (A >>> (32 - 5)); // A , Circular Shift Left by 5
			temp = temp + E + WArray[t] + K0_19;
			temp = temp + ((B & C) | (~B & D));
			E = D;
			D = C;
			C = (B << 30) | (B >>> (32 - 30)); // B , Circular Shift Left by 30
			B = A;
			A = temp;
		}
		for (t = 20; t <= 39; t++) {
			temp = (A << 5) | (A >>> (32 - 5)); // A , Circular Shift Left by 5
			temp = temp + E + WArray[t] + K20_39;
			temp = temp + (B ^ C ^ D);
			E = D;
			D = C;
			C = (B << 30) | (B >>> (32 - 30)); // B , Circular Shift Left by 30
			B = A;
			A = temp;
		}
		for (t = 40; t <= 59; t++) {
			temp = (A << 5) | (A >>> (32 - 5)); // A , Circular Shift Left by 5
			temp = temp + E + WArray[t] + K40_59;
			temp = temp + ((B & C) | (B & D) | (C & D));
			E = D;
			D = C;
			C = (B << 30) | (B >>> (32 - 30)); // B , Circular Shift Left by 30
			B = A;
			A = temp;
		}
		for (t = 60; t <= 79; t++) {
			temp = (A << 5) | (A >>> (32 - 5)); // A , Circular Shift Left by 5
			temp = temp + E + WArray[t] + K60_79;
			temp = temp + (B ^ C ^ D);
			E = D;
			D = C;
			C = (B << 30) | (B >>> (32 - 30)); // B , Circular Shift Left by 30
			B = A;
			A = temp;
		}

		// step e, page 8 of spec
		HConstants[0] = HConstants[0] + A;
		HConstants[1] = HConstants[1] + B;
		HConstants[2] = HConstants[2] + C;
		HConstants[3] = HConstants[3] + D;
		HConstants[4] = HConstants[4] + E;
		// Update number of bytes actually processed
		bytesProcessed = bytesProcessed + BlockSizeInBytes;
		bytesToProcess = 0; // No pending bytes in the block

	}

	/**
	 * Reset this SHAOutputStream to the state it was before any byte was
	 * written to it.
	 */
	public void reset() {
		HConstants[0] = H0;
		HConstants[1] = H1;
		HConstants[2] = H2;
		HConstants[3] = H3;
		HConstants[4] = H4;
		bytesProcessed = 0;
		bytesToProcess = 0;
	}

	@Override
    public String toString() {
        return this.getClass().getName() + ':' + toStringBlock(getHashAsBytes());
    }

	/**
	 * Converts a block to a String representation.
	 * 
	 * @param block
	 *            byte array representation of the bytes
	 */
	private static String toStringBlock(byte[] block) {
		return toStringBlock(block, 0, block.length);
	}

	/**
	 * Converts a block to a String representation.
	 * 
	 * @param block
	 *            byte array representation of the bytes
	 * @param off
	 *            offset into the block where to start the conversion
	 * @param len
	 *            how many bytes in the byte array to convert to a printable
	 *            representation
	 */
	private static String toStringBlock(byte[] block, int off, int len) {
		String hexdigits = "0123456789ABCDEF";
		StringBuilder buf = new StringBuilder();
        buf.append('[');
		for (int i = off; i < off + len; ++i) {
			buf.append(hexdigits.charAt((block[i] >>> 4) & 0xf));
			buf.append(hexdigits.charAt(block[i] & 0xf));
		}
        buf.append(']');
		return buf.toString();
	}

	/**
	 * Writes <code>len</code> <code>bytes</code> from this byte array
	 * <code>buffer</code> starting at offset <code>off</code> to the
	 * SHAOutputStream. The internal buffer used to compute SHA is updated, and
	 * the incremental computation of SHA is also performed.
	 * 
	 * @param buffer
	 *            the buffer to be written
	 * @param off
	 *            offset in buffer to get bytes
	 * @param len
	 *            number of bytes in buffer to write
	 */
	@Override
    public void write(byte[] buffer, int off, int len) {
		int spaceLeft;
		int start;
		int bytesLeft;
		spaceLeft = BlockSizeInBytes - bytesToProcess;
		if (len < spaceLeft) { // Extra bytes are not enough to fill buffer
			this.copyToInternalBuffer(buffer, off, len);
			return;
		}
		// Extra bytes are bigger than space in buffer. Process buffer multiple
		// times
		this.copyToInternalBuffer(buffer, off, spaceLeft);
		bytesLeft = len - spaceLeft;
		this.processBuffer();
		start = off + spaceLeft;
		while (bytesLeft >= BlockSizeInBytes) {
			this.copyToInternalBuffer(buffer, start, BlockSizeInBytes);
			bytesLeft = bytesLeft - BlockSizeInBytes;
			this.processBuffer();
			start = start + BlockSizeInBytes;
		}
		if (bytesLeft > 0) {
			// Extra bytes at the end, not enough to fill buffer
			this.copyToInternalBuffer(buffer, start, bytesLeft);
		}
	}

	/**
	 * Writes the specified byte <code>b</code> to this OutputStream. Only the
	 * low order byte of <code>b</code> is written.
	 * 
	 * @param b
	 *            the byte to be written
	 */
	@Override
    public void write(int b) {
		// Not thread-safe because we use a shared one-byte buffer
		oneByte[0] = (byte) b;
		write(oneByte, 0, 1);
	}
}
