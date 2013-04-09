/*
 * Copyright (C) 2011 The Android Open Source Project
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

package tests.io;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import libcore.io.ErrnoException;
import libcore.io.Libcore;
import libcore.io.Os;
import libcore.io.OsConstants;

/**
 * A mocking interceptor that wraps another {@link Os} to add faults. This can
 * be useful to test otherwise hard-to-test scenarios such as a full disk.
 */
public final class MockOs {
    private final InheritableThreadLocal<Map<String, Deque<InvocationHandler>>> handlers
            = new InheritableThreadLocal<Map<String, Deque<InvocationHandler>>>() {
        @Override protected Map<String, Deque<InvocationHandler>> initialValue() {
            return new HashMap<String, Deque<InvocationHandler>>();
        }
    };

    private Os delegate;
    private final InvocationHandler delegateHandler = new InvocationHandler() {
        @Override public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            try {
                return method.invoke(delegate, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
    };

    private final InvocationHandler invocationHandler = new InvocationHandler() {
        @Override public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            InvocationHandler handler = getHandlers(method.getName()).poll();
            if (handler == null) {
                handler = delegateHandler;
            }
            return handler.invoke(proxy, method, args);
        }
    };

    private final Os mockOs = (Os) Proxy.newProxyInstance(MockOs.class.getClassLoader(),
            new Class[] { Os.class }, invocationHandler);

    public void install() {
        if (delegate != null) {
            throw new IllegalStateException("MockOs already installed!");
        }
        delegate = Libcore.os;
        Libcore.os = mockOs;
    }

    public void uninstall() {
        if (delegate == null) {
            throw new IllegalStateException("MockOs not installed!");
        }
        Libcore.os = delegate;
    }

    /**
     * Returns the invocation handlers to handle upcoming invocations of
     * {@code methodName}. If empty, calls will be handled by the delegate.
     */
    public Deque<InvocationHandler> getHandlers(String methodName) {
        Map<String, Deque<InvocationHandler>> threadFaults = handlers.get();
        Deque<InvocationHandler> result = threadFaults.get(methodName);
        if (result == null) {
            result = new ArrayDeque<InvocationHandler>();
            threadFaults.put(methodName, result);
        }
        return result;
    }

    /**
     * Enqueues the specified number of normal operations. Useful to delay
     * faults.
     */
    public void enqueueNormal(String methodName, int count) {
        Deque<InvocationHandler> handlers = getHandlers(methodName);
        for (int i = 0; i < count; i++) {
            handlers.add(delegateHandler);
        }
    }

    public void enqueueFault(String methodName) {
        enqueueFault(methodName, OsConstants.EIO);
    }

    public void enqueueFault(String methodName, final int errno) {
        getHandlers(methodName).add(new InvocationHandler() {
            @Override public Object invoke(Object proxy, Method method, Object[] args) throws ErrnoException {
                throw new ErrnoException(method.getName(), errno);
            }
        });
    }
}
