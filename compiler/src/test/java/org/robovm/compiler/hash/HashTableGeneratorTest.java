/**
 * 
 */
package org.robovm.compiler.hash;

import static org.junit.Assert.*;

import org.junit.Test;
import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.StructureConstant;

/**
 * @author niklas
 *
 */
public class HashTableGeneratorTest {

    @Test
    public void testEmpty() {
        HashTableGenerator<String, Constant> gen = 
                new HashTableGenerator<String, Constant>(new StringHash(), 2);
        StructureConstant result = gen.generate();
        assertEquals("", result.toString());
    }

    @Test
    public void testNoRehashNoCollisions() {
        HashTableGenerator<Integer, Constant> gen = 
                new HashTableGenerator<Integer, Constant>(new IntegerHash(), 2);
        gen.put(0, new IntegerConstant(0));
        gen.put(1, new IntegerConstant(1));
        gen.put(2, new IntegerConstant(2));
        StructureConstant result = gen.generate();
        assertEquals("", result.toString());
    }
    
    @Test
    public void testRehashNoCollisions() {
        HashTableGenerator<Integer, Constant> gen = 
                new HashTableGenerator<Integer, Constant>(new IntegerHash(), 2);
        gen.put(0, new IntegerConstant(0));
        gen.put(1, new IntegerConstant(1));
        gen.put(2, new IntegerConstant(2));
        gen.put(3, new IntegerConstant(3));
        StructureConstant result = gen.generate();
        assertEquals("", result.toString());
    }
    
    @Test
    public void testNoRehashCollisions() {
        HashTableGenerator<Integer, Constant> gen = 
                new HashTableGenerator<Integer, Constant>(new IntegerHash(), 2);
        gen.put(0, new IntegerConstant(0));
        gen.put(4, new IntegerConstant(4));
        gen.put(8, new IntegerConstant(8));
        StructureConstant result = gen.generate();
        assertEquals("", result.toString());
    }
    
    @Test
    public void testRehashCollisions() {
        HashTableGenerator<Integer, Constant> gen = 
                new HashTableGenerator<Integer, Constant>(new IntegerHash(), 2);
        gen.put(0, new IntegerConstant(0));
        gen.put(4, new IntegerConstant(4));
        gen.put(8, new IntegerConstant(8));
        gen.put(3, new IntegerConstant(3));
        StructureConstant result = gen.generate();
        assertEquals("", result.toString());
    }
    
    private static class IntegerHash implements HashFunction<Integer> {
        @Override
        public int hash(Integer k) {
            return k;
        }
    }
    
    private static class StringHash implements HashFunction<String> {
        @Override
        public int hash(String k) {
            return k.hashCode();
        }
    }
}
