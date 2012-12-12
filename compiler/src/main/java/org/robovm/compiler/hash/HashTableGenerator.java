/*
 * Copyright (C) 2012 Trillian AB
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

import java.util.LinkedList;
import java.util.List;

import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.IntegerType;
import org.robovm.compiler.llvm.StructureConstant;
import org.robovm.compiler.llvm.StructureConstantBuilder;
import org.robovm.compiler.llvm.Type;

/**
 * @author niklas
 *
 */
public class HashTableGenerator<K, V extends Constant> {
    private final HashFunction<K> function;
    private List<Entry<K, V>>[] table;
    private int tableSize;
    private int count = 0;
    private IntegerType indexType = Type.I16;
    private double loadFactor;
    
    public HashTableGenerator(HashFunction<K> function) {
        this(function, 4, 0.75);
    }
    
    public HashTableGenerator(HashFunction<K> function, int tableSize) {
        this(function, tableSize, 0.75);
    }
    
    public HashTableGenerator(HashFunction<K> function, int tableSize, double loadFactor) {
        this.function = function;
        this.tableSize = tableSize;
        this.loadFactor = loadFactor;
        allocateTable();
    }
    
    @SuppressWarnings("unchecked")
    private void allocateTable() {
        this.table = new List[1 << tableSize];
    }
    
    public void put(K k, V v) {
        int h = function.hash(k);
        putNoRehash(new Entry<K, V>(h, k, v));
        if (count > loadFactor * table.length) {
            rehash();
        }
    }
    
    private void rehash() {
        List<Entry<K, V>>[] oldTable = table;
        tableSize++;
        count = 0;
        allocateTable();
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                for (Entry<K, V> entry : oldTable[i]) {
                    putNoRehash(entry);
                }
            }
        }
    }
    
    private void putNoRehash(Entry<K, V> entry) {
        int idx = entry.h & ((1 << tableSize) - 1);
        if (table[idx] == null) {
            table[idx] = new LinkedList<Entry<K, V>>();
        }
        for (Entry<K, V> e : table[idx]) {
            if (e.h == entry.h && (e.k.equals(entry.k))) {
                e.v = entry.v;
                return;
            }
        }
        table[idx].add(entry);
        count++;
    }
    
    public StructureConstant generate() {
        StructureConstantBuilder builder = new StructureConstantBuilder();
        int start = 0;
        builder.add(new IntegerConstant(count));
        builder.add(new IntegerConstant(table.length, indexType));
        builder.add(new IntegerConstant(start, indexType));
        for (int i = 1; i <= table.length; i++) {
            if (table[i - 1] == null) {
                builder.add(new IntegerConstant(start, indexType));                
            } else {
                start += table[i - 1].size();
                builder.add(new IntegerConstant(start, indexType));
            }
        }
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i]) {
                    builder.add(entry.v);
                }
            }
        }
        return builder.build();
    }
    
    private static class Entry<K, V> {
        int h;
        K k;
        V v;
        Entry(int h, K k, V v) {
            this.h = h;
            this.k = k;
            this.v = v;
        }
        @Override
        public int hashCode() {
            return h;
        }
        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            Entry<K, V> e = (Entry<K, V>) obj;
            return this.h == e.h 
                    && (this.k == e.k || this.k.equals(e.k))
                    && (this.v == e.v || this.v.equals(e.v));
        }
    }
}
