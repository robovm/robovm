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

package org.apache.harmony.luni.internal.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class ProxyConstantPool implements ProxyConstants {
    public static final int UTF8_INITIAL_SIZE = 50;

    public static final int STRING_INITIAL_SIZE = 21;

    public static final int FIELD_INITIAL_SIZE = 7;

    public static final int METHOD_INITIAL_SIZE = 21;

    public static final int INTERFACE_INITIAL_SIZE = 21;

    public static final int CLASS_INITIAL_SIZE = 21;

    public static final int NAMEANDTYPE_INITIAL_SIZE = 21;

    public static final int CONSTANTPOOL_INITIAL_SIZE = 500;

    public static final int CONSTANTPOOL_GROW_SIZE = 1000;

    ProxyCharArrayCache UTF8Cache;

    ProxyCharArrayCache stringCache;

    ProxyCharArrayCache classNameCache;

    ProxyObjectCache fieldCache;

    ProxyObjectCache methodCache;

    ProxyObjectCache interfaceMethodCache;

    ProxyNameAndTypeCache nameAndTypeCache;

    byte[] poolContent;

    int currentIndex;

    int currentOffset;

    ProxyConstantPool(ProxyClassFile classFile) {
        UTF8Cache = new ProxyCharArrayCache(UTF8_INITIAL_SIZE);
        stringCache = new ProxyCharArrayCache(STRING_INITIAL_SIZE);
        classNameCache = new ProxyCharArrayCache(CLASS_INITIAL_SIZE);
        fieldCache = new ProxyObjectCache(FIELD_INITIAL_SIZE);
        methodCache = new ProxyObjectCache(METHOD_INITIAL_SIZE);
        interfaceMethodCache = new ProxyObjectCache(INTERFACE_INITIAL_SIZE);
        nameAndTypeCache = new ProxyNameAndTypeCache(NAMEANDTYPE_INITIAL_SIZE);
        poolContent = classFile.header;
        currentOffset = classFile.headerOffset;
        currentIndex = 1;
    }

    int literalIndex(char[] utf8Constant) {
        int index;
        if ((index = UTF8Cache.get(utf8Constant)) < 0) {
            writeU1(Utf8Tag);
            int savedCurrentOffset = currentOffset;
            if (currentOffset + 2 >= poolContent.length) {
                int length = poolContent.length;
                System.arraycopy(poolContent, 0, (poolContent = new byte[length
                        + CONSTANTPOOL_GROW_SIZE]), 0, length);
            }
            currentOffset += 2;
            int length = 0;
            for (int i = 0; i < utf8Constant.length; i++) {
                char current = utf8Constant[i];
                if ((current >= 0x0001) && (current <= 0x007F)) {
                    // we only need one byte: ASCII table
                    writeU1(current);
                    length++;
                } else if (current > 0x07FF) {
                    // we need 3 bytes
                    length += 3;
                    writeU1(0xE0 | ((current >> 12) & 0x0F)); // 0xE0 = 1110
                                                                // 0000
                    writeU1(0x80 | ((current >> 6) & 0x3F)); // 0x80 = 1000
                                                                // 0000
                    writeU1(0x80 | (current & 0x3F)); // 0x80 = 1000 0000
                } else {
                    // we can be 0 or between 0x0080 and 0x07FF
                    // In that case we only need 2 bytes
                    length += 2;
                    writeU1(0xC0 | ((current >> 6) & 0x1F)); // 0xC0 = 1100
                                                                // 0000
                    writeU1(0x80 | (current & 0x3F)); // 0x80 = 1000 0000
                }
            }
            if (length >= 65535) {
                currentOffset = savedCurrentOffset - 1;
                return -1;
            }
            index = UTF8Cache.put(utf8Constant, currentIndex++);
            // Now we know the length that we have to write in the constant pool
            // we use savedCurrentOffset to do that
            poolContent[savedCurrentOffset] = (byte) (length >> 8);
            poolContent[savedCurrentOffset + 1] = (byte) length;
        }
        return index;
    }
    
        int literalIndex(Field aField) {    
            return literalIndex(aField.getDeclaringClass().getName(), aField.getName(), aField.getType());
    }
    
    int literalIndex(String declaringClass, String name, Class clazz) {
        int index;
        String key = declaringClass + "." + name;
        if ((index = fieldCache.get(key)) < 0) {
            int classIndex = typeIndex(declaringClass);    
            int nameAndTypeIndex = literalIndexForNameAndType(
                    literalIndex(name.toCharArray()),
                    literalIndex(ProxyClassFile.getConstantPoolName(clazz)));
            index = fieldCache.put(key, currentIndex++);
            writeU1(FieldRefTag);
            writeU2(classIndex);
            writeU2(nameAndTypeIndex);
        }
        return index;
    }     
    
    int literalIndex(Constructor<?> aMethod) {
        int index;
        if ((index = methodCache.get(aMethod)) < 0) {
            int classIndex = typeIndex(aMethod.getDeclaringClass().getName());
            int nameAndTypeIndex = literalIndexForNameAndType(
                    literalIndex(Init), literalIndex(ProxyClassFile
                            .getConstantPoolName(aMethod)));
            index = methodCache.put(aMethod, currentIndex++);
            writeU1(MethodRefTag);
            writeU2(classIndex);
            writeU2(nameAndTypeIndex);
        }
        return index;
    }

    int literalIndex(Method aMethod) {
        int index;
        if (aMethod.getDeclaringClass().isInterface()) {
            if ((index = interfaceMethodCache.get(aMethod)) < 0) {
                int classIndex = typeIndex(aMethod.getDeclaringClass()
                        .getName());
                int nameAndTypeIndex = literalIndexForNameAndType(
                        literalIndex(aMethod.getName().toCharArray()),
                        literalIndex(ProxyClassFile
                                .getConstantPoolName(aMethod)));
                index = interfaceMethodCache.put(aMethod, currentIndex++);
                writeU1(InterfaceMethodRefTag);
                writeU2(classIndex);
                writeU2(nameAndTypeIndex);
            }
        } else if ((index = methodCache.get(aMethod)) < 0) {
            int classIndex = typeIndex(aMethod.getDeclaringClass().getName());
            int nameAndTypeIndex = literalIndexForNameAndType(
                    literalIndex(aMethod.getName().toCharArray()),
                    literalIndex(ProxyClassFile.getConstantPoolName(aMethod)));
            index = methodCache.put(aMethod, currentIndex++);
            writeU1(MethodRefTag);
            writeU2(classIndex);
            writeU2(nameAndTypeIndex);
        }
        return index;
    }

    int literalIndex(String stringConstant) {
        int index;
        char[] stringCharArray = stringConstant.toCharArray();
        if ((index = stringCache.get(stringCharArray)) < 0) {
            int stringIndex = literalIndex(stringCharArray);
            index = stringCache.put(stringCharArray, currentIndex++);
            writeU1(StringTag);
            writeU2(stringIndex);
        }
        return index;
    }

    int literalIndexForLdc(char[] stringCharArray) {
        int index;
        if ((index = stringCache.get(stringCharArray)) < 0) {
            int stringIndex;
            if ((stringIndex = UTF8Cache.get(stringCharArray)) < 0) {
                writeU1(Utf8Tag);
                int savedCurrentOffset = currentOffset;
                if (currentOffset + 2 >= poolContent.length) {
                    int length = poolContent.length;
                    System.arraycopy(poolContent, 0,
                            (poolContent = new byte[length
                                    + CONSTANTPOOL_GROW_SIZE]), 0, length);
                }
                currentOffset += 2;
                int length = 0;
                for (int i = 0; i < stringCharArray.length; i++) {
                    char current = stringCharArray[i];
                    if ((current >= 0x0001) && (current <= 0x007F)) {
                        // we only need one byte: ASCII table
                        writeU1(current);
                        length++;
                    } else if (current > 0x07FF) {
                        // we need 3 bytes
                        length += 3;
                        writeU1(0xE0 | ((current >> 12) & 0x0F)); // 0xE0 =
                                                                    // 1110 0000
                        writeU1(0x80 | ((current >> 6) & 0x3F)); // 0x80 =
                                                                    // 1000 0000
                        writeU1(0x80 | (current & 0x3F)); // 0x80 = 1000 0000
                    } else {
                        // we can be 0 or between 0x0080 and 0x07FF
                        // In that case we only need 2 bytes
                        length += 2;
                        writeU1(0xC0 | ((current >> 6) & 0x1F)); // 0xC0 =
                                                                    // 1100 0000
                        writeU1(0x80 | (current & 0x3F)); // 0x80 = 1000 0000
                    }
                }
                if (length >= 65535) {
                    currentOffset = savedCurrentOffset - 1;
                    return -1;
                }
                stringIndex = UTF8Cache.put(stringCharArray, currentIndex++);
                // Now we know the length that we have to write in the constant
                // pool
                // we use savedCurrentOffset to do that
                if (length > 65535)
                    return 0;
                poolContent[savedCurrentOffset] = (byte) (length >> 8);
                poolContent[savedCurrentOffset + 1] = (byte) length;
            }
            index = stringCache.put(stringCharArray, currentIndex++);
            writeU1(StringTag);
            writeU2(stringIndex);
        }
        return index;
    }

     int literalIndexForNameAndType(int nameIndex, int typeIndex) {
        int index;
        int[] key = new int[] { nameIndex, typeIndex };
        if ((index = nameAndTypeCache.get(key)) == -1) {
            index = nameAndTypeCache.put(key, currentIndex++);
            writeU1(NameAndTypeTag);
            writeU2(nameIndex);
            writeU2(typeIndex);
        }
        return index;
    }

    int typeIndex(String typeName) {
        int index;
        if (typeName.indexOf('.') != -1)
            typeName = typeName.replace('.', '/');
        char[] charArray = typeName.toCharArray();
        if ((index = classNameCache.get(charArray)) < 0) {
            int nameIndex = literalIndex(charArray);
            index = classNameCache.put(charArray, currentIndex++);
            writeU1(ClassTag);
            writeU2(nameIndex);
        }
        return index;
    }

    private final void writeU1(int value) {
        try {
            poolContent[currentOffset++] = (byte) value;
        } catch (IndexOutOfBoundsException e) {
            // currentOffset has been ++ already (see the -1)
            int length = poolContent.length;
            System.arraycopy(poolContent, 0, (poolContent = new byte[length
                    + CONSTANTPOOL_GROW_SIZE]), 0, length);
            poolContent[currentOffset - 1] = (byte) value;
        }
    }

    private final void writeU2(int value) {
        try {
            poolContent[currentOffset++] = (byte) (value >> 8);
        } catch (IndexOutOfBoundsException e) {
            // currentOffset has been ++ already (see the -1)
            int length = poolContent.length;
            System.arraycopy(poolContent, 0, (poolContent = new byte[length
                    + CONSTANTPOOL_GROW_SIZE]), 0, length);
            poolContent[currentOffset - 1] = (byte) (value >> 8);
        }
        try {
            poolContent[currentOffset++] = (byte) value;
        } catch (IndexOutOfBoundsException e) {
            // currentOffset has been ++ already (see the -1)
            int length = poolContent.length;
            System.arraycopy(poolContent, 0, (poolContent = new byte[length
                    + CONSTANTPOOL_GROW_SIZE]), 0, length);
            poolContent[currentOffset - 1] = (byte) value;
        }
    }
}
