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

import java.lang.reflect.Method;

import org.apache.harmony.luni.internal.nls.Messages;

class ProxyMethod {
    Method method;
    Class declaringClass;

    Class[] commonExceptions;

    ProxyMethod(Class declaringClass, Method method) {
    	this.declaringClass = declaringClass;
        this.method = method;
        this.commonExceptions = method.getExceptionTypes();
    }

    Class[] getCheckedExceptions() {
        Class[] newExceptions = commonExceptions.clone();
        int cLength = newExceptions.length;
        for (int c = 0, cL = cLength; c < cL; c++) {
            Class<?> ex = newExceptions[c];
            if (Throwable.class == ex) {
                // if Throwable is included then treat as if no exceptions are
                // checked
                return new Class[0];
            }
            if (Error.class.isAssignableFrom(ex) || RuntimeException.class.isAssignableFrom(ex)) {
                newExceptions[c] = null;
                cLength--;
            }
        }

        // All errors & runtime exceptions are passed back without being wrapped
        Class[] result = new Class[cLength + 2];
        int index = 0;
        result[index++] = Error.class;
        result[index++] = RuntimeException.class;
        for (Class<?> ex : newExceptions) {
            if (ex != null) {
                result[index++] = ex;
            }
        }
        return result;
    }

    boolean matchMethod(Method otherMethod) {
        if (!method.getName().equals(otherMethod.getName())) {
            return false;
        }

        Class[] params1 = method.getParameterTypes();
        Class[] params2 = otherMethod.getParameterTypes();
        int p = params1.length;
        if (p != params2.length) {
            return false;
        }
        while (--p >= 0) {
            if (params1[p] != params2[p]) {
                return false;
            }
        }

        Class<?> thisMethodReturnType = method.getReturnType();
        Class<?> otherMethodReturnType = otherMethod.getReturnType();
        if (!thisMethodReturnType.isAssignableFrom(otherMethodReturnType)) {
            if (otherMethodReturnType.isAssignableFrom(thisMethodReturnType)) {
                // substitute returnType of method with that of otherMethod
                method = otherMethod;
            } else {
                throw new IllegalArgumentException(Messages.getString("luni.19",
                        method.getName()));
            }
        }

        if (commonExceptions.length != 0) {
            Class[] otherExceptions = otherMethod.getExceptionTypes();
            if (otherExceptions.length == 0) {
                commonExceptions = otherExceptions;
            } else {
                int cLength = commonExceptions.length;
                nextException: for (int c = 0, cL = cLength, oL = otherExceptions.length; c < cL; c++) {
                    Class<?> cException = commonExceptions[c];
                    for (int o = 0; o < oL; o++) {
                        Class<?> oException = otherExceptions[o];
                        if (cException == oException) {
                            continue nextException;
                        }
                        if (oException.isAssignableFrom(cException)) {
                            continue nextException; // cException is a subclass
                        }
                        if (cException.isAssignableFrom(oException)) {
                            // oException is a subclass, keep it instead
                            if(!containsClass(commonExceptions, oException)){
                                //if exceptions in throw list have Parent-Child relationship just ignore it
                                //otherwise, keep the subclass
                                commonExceptions[c] = cException = oException;
                            }
                            continue nextException;
                        }
                    }
                    commonExceptions[c] = null;
                    cLength--;
                }
                if (cLength != commonExceptions.length) {
                    Class[] newExceptions = new Class[cLength];
                    for (int i = 0, j = 0, length = commonExceptions.length; i < length; i++) {
                        Class<?> ex = commonExceptions[i];
                        if (ex != null) {
                            newExceptions[j++] = ex;
                        }
                    }
                    commonExceptions = newExceptions;
                }
            }
        }
        return true;
    }

    private boolean containsClass(Class<?>[] classArray, Class<?> clazz) {
        for (Class<?> c : classArray) {
            if (c.equals(clazz)) {
                return true;
            }
        }
        return false;
    }

    Class getDeclaringClass() {
    	return declaringClass;
    }

}
