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

package libcore.java.lang.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import tests.api.java.lang.reflect.GenericReflectionTestsBase;

/**
 * Tests generic reflection in more complicated cases. In particular: Scoping of
 * type parameters, equality of type parameters, wildcard types, parameterized
 * types and multiple bounds.
 */
public class OldGenericReflectionCornerCases extends GenericReflectionTestsBase {

    static class Pair<T, S> {}

    static class WildcardEquality<T> {
        void wildcardEquality(Pair<? extends T, ? extends T> param) {}
    }
    @SuppressWarnings("unchecked")
    public void testWildcardEquality() throws Exception {
        Class<? extends WildcardEquality> clazz = WildcardEquality.class;

        Method method = clazz.getDeclaredMethod("wildcardEquality", Pair.class);
        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
        assertLenghtOne(typeParameters);
        TypeVariable<?> typeParameter = typeParameters[0];

        Type[] parameterTypes = method.getGenericParameterTypes();
        assertLenghtOne(parameterTypes);
        Type parameter = parameterTypes[0];
        assertInstanceOf(ParameterizedType.class, parameter);
        ParameterizedType paramType = (ParameterizedType) parameter;
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        assertEquals(2, actualTypeArguments.length);

        Type firstArgument = actualTypeArguments[0];
        assertInstanceOf(WildcardType.class, firstArgument);
        WildcardType firstWildcardArgument = (WildcardType) firstArgument;
        Type secondArgument = actualTypeArguments[1];
        assertInstanceOf(WildcardType.class, secondArgument);
        WildcardType secondWildcardArgument = (WildcardType) secondArgument;

        assertEquals(firstWildcardArgument, secondWildcardArgument);

        Type[] firstWildcardArgumentUpperBounds = firstWildcardArgument.getUpperBounds();
        assertLenghtOne(firstWildcardArgumentUpperBounds);
        Type firstWildcardArgumentUpperBoundsType = firstWildcardArgumentUpperBounds[0];

        Type[] secondWildcardArgumentUpperBounds = secondWildcardArgument.getUpperBounds();
        assertLenghtOne(secondWildcardArgumentUpperBounds);
        Type secondWildcardArgumentUpperBoundsType = secondWildcardArgumentUpperBounds[0];

        assertEquals(firstWildcardArgumentUpperBoundsType, secondWildcardArgumentUpperBoundsType);

        assertEquals(typeParameter, firstWildcardArgumentUpperBoundsType);
        assertEquals(typeParameter, secondWildcardArgumentUpperBoundsType);
    }

    static class WildcardUnEquality<T> {
        void wildcardUnEquality(Pair<? extends T, ? super T> param) {}
    }
    @SuppressWarnings("unchecked")
    public void testWildcardUnEquality() throws Exception {
        Class<? extends WildcardUnEquality> clazz = WildcardUnEquality.class;

        Method method = clazz.getDeclaredMethod("wildcardUnEquality", Pair.class);
        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
        assertLenghtOne(typeParameters);
        TypeVariable<?> typeParameter = typeParameters[0];

        Type[] parameterTypes = method.getGenericParameterTypes();
        assertLenghtOne(parameterTypes);
        Type parameter = parameterTypes[0];
        assertInstanceOf(ParameterizedType.class, parameter);
        ParameterizedType paramType = (ParameterizedType) parameter;
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        assertEquals(2, actualTypeArguments.length);

        Type firstArgument = actualTypeArguments[0];
        assertInstanceOf(WildcardType.class, firstArgument);
        WildcardType firstWildcardArgument = (WildcardType) firstArgument;
        Type secondArgument = actualTypeArguments[1];
        assertInstanceOf(WildcardType.class, secondArgument);
        WildcardType secondWildcardArgument = (WildcardType) secondArgument;

        assertNotEquals(firstWildcardArgument, secondWildcardArgument);

        Type[] firstWildcardArgumentUpperBounds = firstWildcardArgument.getUpperBounds();
        assertLenghtOne(firstWildcardArgumentUpperBounds);
        Type firstWildcardArgumentUpperBoundsType = firstWildcardArgumentUpperBounds[0];

        Type[] secondWildcardArgumentLowerBounds = secondWildcardArgument.getLowerBounds();
        assertLenghtOne(secondWildcardArgumentLowerBounds);
        Type secondWildcardArgumentLoweroundsType = secondWildcardArgumentLowerBounds[0];

        assertEquals(firstWildcardArgumentUpperBoundsType, secondWildcardArgumentLoweroundsType);
        assertEquals(typeParameter, firstWildcardArgumentUpperBoundsType);
        assertEquals(typeParameter, secondWildcardArgumentLoweroundsType);
    }

    static class MultipleBoundedWildcardUnEquality<T extends Object & Comparable<MultipleBoundedWildcardUnEquality<T>>> {
        void multipleBoundedWildcardUnEquality(Pair<? extends T, ? super T> param) {}
    }
    @SuppressWarnings("unchecked")
    public void testMultipleBoundedWildcardUnEquality() throws Exception {
        Class<? extends MultipleBoundedWildcardUnEquality> clazz = MultipleBoundedWildcardUnEquality.class;

        // new WildcardEquality<Object>().wildcardEquality(new Pair<String,
        // Integer>());

        Method method = clazz.getDeclaredMethod("multipleBoundedWildcardUnEquality", Pair.class);
        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
        assertLenghtOne(typeParameters);
        TypeVariable<?> typeParameter = typeParameters[0];
        Type[] typeParameterBounds = typeParameter.getBounds();
        assertEquals(2, typeParameterBounds.length);
        assertEquals(Object.class, typeParameterBounds[0]);
        assertInstanceOf(ParameterizedType.class, typeParameterBounds[1]);
        ParameterizedType parameterizedType = (ParameterizedType) typeParameterBounds[1];
        assertEquals(Comparable.class, parameterizedType.getRawType());
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        assertLenghtOne(typeArguments);
        assertInstanceOf(ParameterizedType.class, typeArguments[0]);
        ParameterizedType type = (ParameterizedType) typeArguments[0];
        assertEquals(typeParameter, type.getActualTypeArguments()[0]);
        assertEquals(MultipleBoundedWildcardUnEquality.class, type.getRawType());

        Type[] parameterTypes = method.getGenericParameterTypes();
        assertLenghtOne(parameterTypes);
        Type parameter = parameterTypes[0];
        assertInstanceOf(ParameterizedType.class, parameter);
        ParameterizedType paramType = (ParameterizedType) parameter;
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        assertEquals(2, actualTypeArguments.length);

        Type firstArgument = actualTypeArguments[0];
        assertInstanceOf(WildcardType.class, firstArgument);
        WildcardType firstWildcardArgument = (WildcardType) firstArgument;
        Type secondArgument = actualTypeArguments[1];
        assertInstanceOf(WildcardType.class, secondArgument);
        WildcardType secondWildcardArgument = (WildcardType) secondArgument;

        assertNotEquals(firstWildcardArgument, secondWildcardArgument);

        Type[] firstWildcardArgumentUpperBounds = firstWildcardArgument.getUpperBounds();
        assertLenghtOne(firstWildcardArgumentUpperBounds);
        Type firstWildcardArgumentUpperBoundsType = firstWildcardArgumentUpperBounds[0];

        Type[] secondWildcardArgumentLowerBounds = secondWildcardArgument.getLowerBounds();
        assertLenghtOne(secondWildcardArgumentLowerBounds);
        Type secondWildcardArgumentLoweroundsType = secondWildcardArgumentLowerBounds[0];

        assertEquals(firstWildcardArgumentUpperBoundsType, secondWildcardArgumentLoweroundsType);
    }

    static class MultipleBoundedWildcardEquality<T extends Object & Comparable<MultipleBoundedWildcardEquality<T>>> {
        void multipleBoundedWildcardEquality(Pair<? extends T, ? extends T> param) {}
    }
    @SuppressWarnings("unchecked")
    public void testMultipleBoundedWildcard() throws Exception {
        Class<? extends MultipleBoundedWildcardEquality> clazz = MultipleBoundedWildcardEquality.class;

        Method method = clazz.getDeclaredMethod("multipleBoundedWildcardEquality", Pair.class);
        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
        assertLenghtOne(typeParameters);
        TypeVariable<?> typeParameter = typeParameters[0];
        Type[] typeParameterBounds = typeParameter.getBounds();
        assertEquals(2, typeParameterBounds.length);
        assertEquals(Object.class, typeParameterBounds[0]);
        assertInstanceOf(ParameterizedType.class, typeParameterBounds[1]);
        ParameterizedType parameterizedType = (ParameterizedType) typeParameterBounds[1];
        assertEquals(Comparable.class, parameterizedType.getRawType());
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        assertLenghtOne(typeArguments);
        assertInstanceOf(ParameterizedType.class, typeArguments[0]);
        ParameterizedType type = (ParameterizedType) typeArguments[0];
        assertEquals(typeParameter, type.getActualTypeArguments()[0]);
        assertEquals(MultipleBoundedWildcardEquality.class, type.getRawType());

        Type[] parameterTypes = method.getGenericParameterTypes();
        assertLenghtOne(parameterTypes);
        Type parameter = parameterTypes[0];
        assertInstanceOf(ParameterizedType.class, parameter);
        ParameterizedType paramType = (ParameterizedType) parameter;
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        assertEquals(2, actualTypeArguments.length);

        Type firstArgument = actualTypeArguments[0];
        assertInstanceOf(WildcardType.class, firstArgument);
        WildcardType firstWildcardArgument = (WildcardType) firstArgument;
        Type secondArgument = actualTypeArguments[1];
        assertInstanceOf(WildcardType.class, secondArgument);
        WildcardType secondWildcardArgument = (WildcardType) secondArgument;

        assertEquals(firstWildcardArgument, secondWildcardArgument);

        Type[] firstWildcardArgumentUpperBounds = firstWildcardArgument.getUpperBounds();
        assertLenghtOne(firstWildcardArgumentUpperBounds);
        Type firstWildcardArgumentUpperBoundsType = firstWildcardArgumentUpperBounds[0];

        Type[] secondWildcardArgumentUpperBounds = secondWildcardArgument.getUpperBounds();
        assertLenghtOne(secondWildcardArgumentUpperBounds);
        Type secondWildcardArgumentLoweroundsType = secondWildcardArgumentUpperBounds[0];

        assertEquals(firstWildcardArgumentUpperBoundsType, secondWildcardArgumentLoweroundsType);
    }
}
