/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.robovm.compiler.util.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ListOfTypes {
    public static final ListOfTypes EMPTY = new ListOfTypes(0);

    private final ArrayList<Type> types;
    private Type[] resolvedTypes;

    public ListOfTypes(int capacity) {
        types = new ArrayList<Type>(capacity);
    }

    public ListOfTypes(Type[] types) {
        this.types = new ArrayList<Type>(types.length);
        for (Type type : types) {
            this.types.add(type);
        }
    }

    public void add(Type type) {
        if (type == null) {
            throw new NullPointerException("type == null");
        }
        types.add(type);
    }

    public int length() {
        return types.size();
    }

    public Type[] getResolvedTypes() {
        Type[] result = resolvedTypes;
        return result != null ? result : (resolvedTypes = resolveTypes(types));
    }

    private Type[] resolveTypes(List<Type> unresolved) {
        Type[] result = new Type[unresolved.size()];
        for (int i = 0; i < unresolved.size(); i++) {
            Type type = unresolved.get(i);
            try {
                result[i] = ((ImplForType) type).getResolvedType();
            } catch (ClassCastException e) {
                result[i] = type;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListOfTypes)) {
            return false;
        }
        ListOfTypes that = (ListOfTypes) obj;
        return Arrays.equals(this.getResolvedTypes(), that.getResolvedTypes());
    }
    
    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < types.size(); i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(types.get(i));
        }
        return result.toString();
    }
}
