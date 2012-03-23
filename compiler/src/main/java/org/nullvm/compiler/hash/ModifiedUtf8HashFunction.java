/**
 * 
 */
package org.nullvm.compiler.hash;

import org.nullvm.compiler.Strings;

/**
 * @author niklas
 *
 */
public class ModifiedUtf8HashFunction implements HashFunction<String> {
    @Override
    public int hash(String k) {
        byte[] data = Strings.stringToModifiedUtf8(k);
        return MurmurHash3.murmurhash3_x86_32(data, 0, data.length, 0x1ce79e5c);
    }
}
