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

package libcore.reflect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * The annotation implementation based on dynamically generated proxy instances.
 * It conforms to all requirements stated in public APIs, see in particular
 * {@link java.lang.reflect.AnnotatedElement java.lang.reflect.AnnotatedElement}
 * and {@link java.lang.annotation.Annotation java.lang.annotation.Annotation}.
 * Namely, annotation instances are immutable and serializable; they provide
 * conforming access to annotation member values and required implementations of
 * methods declared in Annotation interface.
 *
 * @see AnnotationMember
 * @see java.lang.annotation.Annotation
 *
 * @author Alexey V. Varlamov, Serguei S. Zapreyev
 * @version $Revision$
 */
@SuppressWarnings({"serial"})
public final class AnnotationFactory implements InvocationHandler, Serializable {

    private static final transient Map<Class<? extends Annotation>, AnnotationMember[]> cache =
            new WeakHashMap<Class<? extends Annotation>, AnnotationMember[]>();

    /**
     * Reflects specified annotation type and returns an array
     * of member element definitions with default values.
     */
    public static AnnotationMember[] getElementsDescription(Class<? extends Annotation> annotationType) {
        synchronized (cache) {
            AnnotationMember[] desc = cache.get(annotationType);
            if (desc != null) {
                return desc;
            }
        }
        if (!annotationType.isAnnotation()) {
            throw new IllegalArgumentException("Type is not annotation: " + annotationType.getName());
        }
        Method[] declaredMethods = annotationType.getDeclaredMethods();
        AnnotationMember[] desc = new AnnotationMember[declaredMethods.length];
        for (int i = 0; i < declaredMethods.length; ++i) {
            Method element = declaredMethods[i];
            String name = element.getName();
            Class<?> type = element.getReturnType();
            try {
                desc[i] = new AnnotationMember(name, element.getDefaultValue(), type, element);
            } catch (Throwable t) {
                desc[i] = new AnnotationMember(name, t, type, element);
            }
        }
        synchronized (cache) {
            cache.put(annotationType, desc);
        }
        return desc;
    }

    /**
     * Provides a new annotation instance.
     * @param annotationType the annotation type definition
     * @param elements name-value pairs representing elements of the annotation
     * @return a new annotation instance
     */
    public static <A extends Annotation> A createAnnotation(Class<? extends Annotation> annotationType,
                                                            AnnotationMember[] elements) {
        AnnotationFactory factory = new AnnotationFactory(annotationType, elements);
        return (A) Proxy.newProxyInstance(annotationType.getClassLoader(),
                                          new Class[]{annotationType}, factory);
    }

    private final Class<? extends Annotation> klazz;
    private AnnotationMember[] elements;

    /**
     * New instances should not be created directly, use factory method
     * {@link #createAnnotation(Class, AnnotationMember[]) createAnnotation()}
     * instead.
     *
     * @param klzz class defining the annotation type
     * @param values actual element values
     */
    private AnnotationFactory(Class<? extends Annotation> klzz, AnnotationMember[] values) {
        klazz = klzz;
        AnnotationMember[] defs = getElementsDescription(klazz);
        if (values == null) {
            elements = defs;
        } else {
            //merge default and actual values
            elements = new AnnotationMember[defs.length];
            next: for (int i = elements.length - 1; i >= 0; i--) {
                for (AnnotationMember val : values) {
                    if (val.name.equals(defs[i].name)) {
                        elements[i] = val.setDefinition(defs[i]);
                        continue next;
                    }
                }
                elements[i] = defs[i];
            }
        }
    }

    /**
     * Reads the object, obtains actual member definitions for the annotation type,
     * and merges deserialized values with the new definitions.
     */
    private void readObject(ObjectInputStream os) throws IOException, ClassNotFoundException {
        os.defaultReadObject();
        // Annotation type members can be changed arbitrarily
        // So there may be zombi elements from the previous life;
        // they hardly fit into this new annotation's incarnation,
        // as we have no defining methods for them.
        // Reasonably just drop such elements,
        // but seems better to keep them for compatibility
        AnnotationMember[] defs = getElementsDescription(klazz);
        AnnotationMember[] old = elements;
        List<AnnotationMember> merged = new ArrayList<AnnotationMember>(defs.length + old.length);
        nextOld: for (AnnotationMember el1 : old) {
            for (AnnotationMember el2 : defs) {
                if (el2.name.equals(el1.name)) {
                    continue nextOld;
                }
            }
            merged.add(el1); //phantom element
        }
        nextNew: for (AnnotationMember def : defs) {
            for (AnnotationMember val : old) {
                if (val.name.equals(def.name)) {
                    // nothing to do about cached errors (if any)
                    // anyway they remain relevant to values
                    merged.add(val.setDefinition(def));
                    continue nextNew;
                }
            }
            merged.add(def); // brand new element
        }
        elements = merged.toArray(new AnnotationMember[merged.size()]);
    }

    /**
     * Returns true if the specified object represents the same annotation instance.
     * That is, if it implements the same annotation type and
     * returns the same element values.
     * <br>Note, actual underlying implementation mechanism does not matter - it may
     * differ completely from this class.
     * @return true if the passed object is equivalent annotation instance,
     * false otherwise.
     * @see AnnotationMember#equals(Object)
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!klazz.isInstance(obj)) {
            return false;
        }
        Object handler = null;
        if (Proxy.isProxyClass(obj.getClass())
                && (handler = Proxy.getInvocationHandler(obj)) instanceof AnnotationFactory) {
            AnnotationFactory other = (AnnotationFactory) handler;
            if (elements.length != other.elements.length) {
                return false;
            }
            next: for (AnnotationMember el1 : elements) {
                for (AnnotationMember el2 : other.elements) {
                    if (el1.equals(el2)) {
                        continue next;
                    }
                }
                return false;
            }
            return true;
        } else {
            // encountered foreign annotation implementation
            // so have to obtain element values via invocation
            // of corresponding methods
            for (final AnnotationMember el : elements) {
                if (el.tag == AnnotationMember.ERROR) {
                    // undefined value is incomparable (transcendent)
                    return false;
                }
                try {
                    if (!el.definingMethod.isAccessible()) {
                        el.definingMethod.setAccessible(true);
                    }
                    Object otherValue = el.definingMethod.invoke(obj);
                    if (otherValue != null) {
                        if (el.tag == AnnotationMember.ARRAY) {
                            if (!el.equalArrayValue(otherValue)) {
                                return false;
                            }
                        } else {
                            if (!el.value.equals(otherValue)) {
                                return false;
                            }
                        }
                    } else if (el.value != AnnotationMember.NO_VALUE) {
                        return false;
                    }
                } catch (Throwable e) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Returns a hash code composed as a sum of hash codes of member elements,
     * including elements with default values.
     * @see AnnotationMember#hashCode()
     */
    public int hashCode() {
        int hash = 0;
        for (AnnotationMember element : elements) {
            hash += element.hashCode();
        }
        return hash;
    }

    /**
     * Provides detailed description of this annotation instance,
     * including all member name-values pairs.
     * @return string representation of this annotation
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('@');
        result.append(klazz.getName());
        result.append('(');
        for (int i = 0; i < elements.length; ++i) {
            if (i != 0) {
                result.append(", ");
            }
            result.append(elements[i]);
        }
        result.append(')');
        return result.toString();
    }

    /**
     * Processes a method invocation request to this annotation instance.
     * Recognizes the methods declared in the
     * {@link java.lang.annotation.Annotation java.lang.annotation.Annotation}
     * interface, and member-defining methods of the implemented annotation type.
     * @throws IllegalArgumentException If the specified method is none of the above
     * @return the invocation result
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        Class[] params = method.getParameterTypes();
        if (params.length == 0) {
            if ("annotationType".equals(name)) {
                return klazz;
            } else if ("toString".equals(name)) {
                return toString();
            } else if ("hashCode".equals(name)) {
                return hashCode();
            }

            // this must be element value request
            AnnotationMember element = null;
            for (AnnotationMember el : elements) {
                if (name.equals(el.name)) {
                    element = el;
                    break;
                }
            }
            if (element == null || !method.equals(element.definingMethod)) {
                throw new IllegalArgumentException(method.toString());
            } else {
                Object value = element.validateValue();
                if (value == null) {
                    throw new IncompleteAnnotationException(klazz, name);
                }
                return value;
            }
        } else if (params.length == 1 && params[0] == Object.class && "equals".equals(name)) {
            return Boolean.valueOf(equals(args[0]));
        }
        throw new IllegalArgumentException("Invalid method for annotation type: " + method);
    }
}
