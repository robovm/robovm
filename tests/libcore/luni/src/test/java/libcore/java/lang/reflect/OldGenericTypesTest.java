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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import tests.api.java.lang.reflect.GenericReflectionTestsBase;


/**
 * Tests type parameters declared on classes.
 */
public class OldGenericTypesTest extends GenericReflectionTestsBase {

    static class GenericType<T>{
        T methodGenericType(T t){ return t;}
        @SuppressWarnings("hiding")
        <T> T hidingMethodGenericType(T t){ return t;}
        static <T> T staticMethodGenericType(T t){ return t;}
    }

    static class MultipleBoundedGenericTypes<T,S extends T>{
        void multipleBoundedGenericTypesTS(T t, S s){}
    }

    static class SimpleInheritance <T> extends GenericType<T>{}

    static class ConstructorGenericType<T>{
        ConstructorGenericType(T t){}
    }

    static class InnerClassTest<T>{
        class InnerClass {
            InnerClass(T t) {}
            void innerMethod(T t){}
        }
    }

    static class ExceptionTest<T extends Exception>{
        void exceptionTest() throws T{}
        class InnerClass{
            void innerExceptionTest() throws T{}
        }
    }

    static interface InterfaceTest<T>{}
    @SuppressWarnings("unchecked")
    public void testConstructorGenericType() throws Exception {
        Class<? extends ConstructorGenericType> clazz = ConstructorGenericType.class;
        TypeVariable<Class> typeVariable = getTypeParameter(clazz);
        Constructor<?> constructor = clazz.getDeclaredConstructor(Object.class);
        Type[] genericParameterTypes = constructor.getGenericParameterTypes();
        assertLenghtOne(genericParameterTypes);
        Type parameterType = genericParameterTypes[0];

        assertEquals(typeVariable, parameterType);
    }
    @SuppressWarnings("unchecked")
    public void testStaticMethodGenericType() throws Exception {
        Class<? extends GenericType> clazz = GenericType.class;
        TypeVariable<Class> typeVariable = getTypeParameter(clazz);

        Method method = clazz.getDeclaredMethod("staticMethodGenericType", Object.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        assertLenghtOne(genericParameterTypes);
        Type parameterType = genericParameterTypes[0];
        assertNotEquals(typeVariable, parameterType);
        assertInstanceOf(TypeVariable.class, parameterType);
        assertEquals(method, ((TypeVariable)parameterType).getGenericDeclaration());
    }
    @SuppressWarnings("unchecked")
    public void testHidingMethodGenericType() throws Exception {
        Class<? extends GenericType> clazz = GenericType.class;
        TypeVariable<Class> typeVariable = getTypeParameter(clazz);

        Method method = clazz.getDeclaredMethod("hidingMethodGenericType",  Object.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        assertLenghtOne(genericParameterTypes);
        Type parameterType = genericParameterTypes[0];
        assertNotEquals(typeVariable, parameterType);
        assertInstanceOf(TypeVariable.class, parameterType);
        assertEquals(method, ((TypeVariable)parameterType).getGenericDeclaration());
    }

    static class MultipleGenericTypes<T,S>{
        void multipleGenericTypesT(T t){}
        void multipleGenericTypesS(S s){}
        void multipleGenericTypesTS(T t, S s){}
    }
    @SuppressWarnings("unchecked")
    public void testMultipleGenericTypes() throws Exception {
        //Type parameters
        Class<? extends MultipleGenericTypes> clazz = MultipleGenericTypes.class;
        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
        assertEquals(2, typeParameters.length);
        TypeVariable<?> typeVariableT = typeParameters[0];
        assertEquals(clazz, typeVariableT.getGenericDeclaration());
        assertEquals("T", typeVariableT.getName());
        TypeVariable<?> typeVariableS = typeParameters[1];
        assertEquals("S", typeVariableS.getName());
        assertEquals(clazz, typeVariableS.getGenericDeclaration());

        // multipleGenericTypesT
        Method multipleGenericTypesT = clazz.getDeclaredMethod("multipleGenericTypesT", new Class[]{Object.class});
        Type[] multipleGenericTypesTTypes = multipleGenericTypesT.getGenericParameterTypes();
        assertLenghtOne(multipleGenericTypesTTypes);
        Type multipleGenericTypesTType = multipleGenericTypesTTypes[0];
        assertEquals(typeVariableT, multipleGenericTypesTType);

        // multipleGenericTypesS
        Method multipleGenericTypesS = clazz.getDeclaredMethod("multipleGenericTypesS", new Class[]{Object.class});
        Type[] multipleGenericTypesSTypes = multipleGenericTypesS.getGenericParameterTypes();
        assertLenghtOne(multipleGenericTypesSTypes);
        Type multipleGenericTypesSType = multipleGenericTypesSTypes[0];
        assertEquals(typeVariableS, multipleGenericTypesSType);

        // multipleGenericTypesS
        Method multipleGenericTypesTS = clazz.getDeclaredMethod("multipleGenericTypesTS", new Class[]{Object.class, Object.class});
        Type[] multipleGenericTypesTSTypes = multipleGenericTypesTS.getGenericParameterTypes();
        assertEquals(2, multipleGenericTypesTSTypes.length);
        Type multipleGenericTypesTSTypeT = multipleGenericTypesTSTypes[0];
        assertEquals(typeVariableT, multipleGenericTypesTSTypeT);
        Type multipleGenericTypesTSTypeS = multipleGenericTypesTSTypes[1];
        assertEquals(typeVariableS, multipleGenericTypesTSTypeS);
    }

    @SuppressWarnings("unchecked")
    public void testMultipleBoundedGenericTypes() throws Exception {
        //Type parameters
        Class<? extends MultipleBoundedGenericTypes> clazz = MultipleBoundedGenericTypes.class;
        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
        assertEquals(2, typeParameters.length);
        TypeVariable<?> typeVariableT = typeParameters[0];
        assertEquals(clazz, typeVariableT.getGenericDeclaration());
        assertEquals("T", typeVariableT.getName());
        TypeVariable<?> typeVariableS = typeParameters[1];
        assertEquals("S", typeVariableS.getName());
        assertEquals(clazz, typeVariableS.getGenericDeclaration());
        Type[] boundsS = typeVariableS.getBounds();
        assertLenghtOne(boundsS);
        Type boundS = boundsS[0];
        assertEquals(typeVariableT, boundS);
    }
    @SuppressWarnings("unchecked")
    public void testSimpleInheritance() throws Exception {
        Class<? extends SimpleInheritance> clazz = SimpleInheritance.class;
        TypeVariable<Class> subTypeVariable = getTypeParameter(clazz);

        assertInstanceOf(ParameterizedType.class, clazz.getGenericSuperclass());
        ParameterizedType parameterizedSuperType = (ParameterizedType) clazz.getGenericSuperclass();
        assertInstanceOf(Class.class, parameterizedSuperType.getRawType());

        TypeVariable<Class> superTypeParameter = getTypeParameter((Class<?>)parameterizedSuperType.getRawType());
        TypeVariable<Class> typeParameter = getTypeParameter(GenericType.class);
        assertEquals(superTypeParameter, typeParameter);

        assertNotEquals(subTypeVariable, superTypeParameter);

        Type[] actualTypeArguments = parameterizedSuperType.getActualTypeArguments();
        assertLenghtOne(actualTypeArguments);
        assertInstanceOf(TypeVariable.class, actualTypeArguments[0]);
        TypeVariable actualSuperTypeVariable = (TypeVariable) actualTypeArguments[0];
        assertEquals(subTypeVariable, actualSuperTypeVariable);
    }
    @SuppressWarnings("unchecked")
    public void testInnerClassTest() throws Exception {
        Class<? extends InnerClassTest> clazz =InnerClassTest.class;
        TypeVariable<Class> typeVariable = getTypeParameter(clazz);

        Class<?>[] declaredClasses = clazz.getDeclaredClasses();
        assertLenghtOne(declaredClasses);
        Class<?> innerClazz = declaredClasses[0];
        assertEquals(InnerClassTest.InnerClass.class, innerClazz);

        //constructor
        Constructor<?>[] declaredConstructors = innerClazz.getDeclaredConstructors();
        assertLenghtOne(declaredConstructors);
        Constructor<?> declaredConstructor = declaredConstructors[0];
        Type[] genericParameterTypes = declaredConstructor.getGenericParameterTypes();
        assertLenghtOne(genericParameterTypes);
        assertEquals(typeVariable, genericParameterTypes[0]);
        assertInstanceOf(TypeVariable.class, genericParameterTypes[0]);
        TypeVariable<?> constructorTypeVariable = (TypeVariable<?>) genericParameterTypes[0];
        assertEquals(clazz ,constructorTypeVariable.getGenericDeclaration());

        //method
        Method declaredMethods = innerClazz.getDeclaredMethod("innerMethod", Object.class);
        Type[] methodParameterTypes = declaredMethods.getGenericParameterTypes();
        assertLenghtOne(methodParameterTypes);
        assertEquals(typeVariable, methodParameterTypes[0]);
        assertInstanceOf(TypeVariable.class, methodParameterTypes[0]);
        TypeVariable<?> methodTypeVariable = (TypeVariable<?>) methodParameterTypes[0];
        assertEquals(clazz, methodTypeVariable.getGenericDeclaration());
    }
    @SuppressWarnings("unchecked")
    public void testException() throws Exception {
        Class<? extends ExceptionTest> clazz = ExceptionTest.class;
        TypeVariable<Class> typeVariable = getTypeParameter(clazz);
        Method method = clazz.getDeclaredMethod("exceptionTest");
        Type[] genericExceptionTypes = method.getGenericExceptionTypes();
        assertLenghtOne(genericExceptionTypes);
        assertEquals(typeVariable, genericExceptionTypes[0]);

        Class<?>[] declaredClasses = clazz.getDeclaredClasses();
        assertLenghtOne(declaredClasses);
        Class<?> innerClazz = declaredClasses[0];
        assertEquals(ExceptionTest.InnerClass.class, innerClazz);

        //method
        Method declaredMethods = innerClazz.getDeclaredMethod("innerExceptionTest");
        Type[] exceptionTypes = declaredMethods.getGenericExceptionTypes();
        assertLenghtOne(exceptionTypes);
        assertEquals(typeVariable, exceptionTypes[0]);
        assertInstanceOf(TypeVariable.class, exceptionTypes[0]);
        TypeVariable<?> methodTypeVariable = (TypeVariable<?>) exceptionTypes[0];
        assertEquals(clazz, methodTypeVariable.getGenericDeclaration());
    }
}
