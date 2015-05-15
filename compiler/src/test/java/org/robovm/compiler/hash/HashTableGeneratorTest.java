/*
 * Copyright (C) 2012 RoboVM AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
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
        assertEquals("{i32 0, i16 4, i16 0, i16 0, i16 0, i16 0, i16 0}", result.toString());
    }

    @Test
    public void testNoRehashNoCollisions() {
        HashTableGenerator<Integer, Constant> gen = 
                new HashTableGenerator<Integer, Constant>(new IntegerHash(), 2);
        gen.put(0, new IntegerConstant(0));
        gen.put(1, new IntegerConstant(1));
        gen.put(2, new IntegerConstant(2));
        StructureConstant result = gen.generate();
        assertEquals("{i32 3, i16 4, i16 0, i16 1, i16 2, i16 3, i16 3, i32 0, i32 1, i32 2}", result.toString());
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
        assertEquals("{i32 4, i16 8, i16 0, i16 1, i16 2, i16 3, i16 4, i16 4, i16 4, i16 4, i16 4, i32 0, i32 1, i32 2, i32 3}", result.toString());
    }
    
    @Test
    public void testNoRehashCollisions() {
        HashTableGenerator<Integer, Constant> gen = 
                new HashTableGenerator<Integer, Constant>(new IntegerHash(), 2);
        gen.put(0, new IntegerConstant(0));
        gen.put(4, new IntegerConstant(4));
        gen.put(8, new IntegerConstant(8));
        StructureConstant result = gen.generate();
        assertEquals("{i32 3, i16 4, i16 0, i16 3, i16 3, i16 3, i16 3, i32 0, i32 4, i32 8}", result.toString());
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
        assertEquals("{i32 4, i16 8, i16 0, i16 2, i16 2, i16 2, i16 3, i16 4, i16 4, i16 4, i16 4, i32 0, i32 8, i32 3, i32 4}", result.toString());
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
