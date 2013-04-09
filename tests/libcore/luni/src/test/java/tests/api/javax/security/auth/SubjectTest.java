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

package tests.api.javax.security.auth;

import junit.framework.TestCase;

import javax.security.auth.AuthPermission;
import javax.security.auth.PrivateCredentialPermission;
import javax.security.auth.Subject;

import java.util.Set;
import java.util.HashSet;
import java.security.Permission;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.ProtectionDomain;

/**
 * Tests for <code>Subject</code> class constructors and methods.
 *
 */
public class SubjectTest extends TestCase {

    /**
     * javax.security.auth.Subject#Subject()
     */
    public void test_Constructor_01() {
        try {
            Subject s = new Subject();
            assertNotNull("Null object returned", s);
            assertTrue("Set of principal is not empty", s.getPrincipals().isEmpty());
            assertTrue("Set of private credentials is not empty", s.getPrivateCredentials().isEmpty());
            assertTrue("Set of public credentials is not empty", s.getPublicCredentials().isEmpty());
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    /**
     * javax.security.auth.Subject#doAs(Subject subject, PrivilegedAction action)
     */
    public void test_doAs_01() {
        Subject subj = new Subject();
        PrivilegedAction<Object> pa = new myPrivilegedAction();
        PrivilegedAction<Object> paNull = null;

        try {
            Object obj = Subject.doAs(null, pa);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            Object obj = Subject.doAs(subj, pa);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            Object obj = Subject.doAs(subj, paNull);
            fail("NullPointerException wasn't thrown");
        } catch (NullPointerException npe) {
        }
    }

    /**
     * javax.security.auth.Subject#doAs(Subject subject, PrivilegedExceptionAction action)
     */
    public void test_doAs_02() {
        Subject subj = new Subject();
        PrivilegedExceptionAction<Object> pea = new myPrivilegedExceptionAction();
        PrivilegedExceptionAction<Object> peaNull = null;

        try {
            Object obj = Subject.doAs(null, pea);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            Object obj = Subject.doAs(subj, pea);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            Object obj = Subject.doAs(subj, peaNull);
            fail("NullPointerException wasn't thrown");
        } catch (NullPointerException npe) {
        } catch (Exception e) {
            fail(e + " was thrown instead of NullPointerException");
        }

        try {
            Subject.doAs(subj, new PrivilegedExceptionAction<Object>(){
                public Object run() throws PrivilegedActionException {
                    throw new PrivilegedActionException(null);
                }
            });
            fail("PrivilegedActionException wasn't thrown");
        } catch (PrivilegedActionException e) {
        }
    }

    /**
     * javax.security.auth.Subject#doAsPrivileged(Subject subject,
     *                                                   PrivilegedAction action,
     *                                                   AccessControlContext acc)
     */
    public void test_doAsPrivileged_01() {
        Subject subj = new Subject();
        PrivilegedAction<Object> pa = new myPrivilegedAction();
        PrivilegedAction<Object> paNull = null;
        AccessControlContext acc = AccessController.getContext();

        try {
            Object obj = Subject.doAsPrivileged(null, pa, acc);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            Object obj = Subject.doAsPrivileged(subj, pa, acc);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            Object obj = Subject.doAsPrivileged(subj, paNull, acc);
            fail("NullPointerException wasn't thrown");
        } catch (NullPointerException npe) {
        }
    }

    /**
     * javax.security.auth.Subject#doAsPrivileged(Subject subject,
     *                                                   PrivilegedExceptionAction action,
     *                                                   AccessControlContext acc)
     */
    public void test_doAsPrivileged_02() {
        Subject subj = new Subject();
        PrivilegedExceptionAction<Object> pea = new myPrivilegedExceptionAction();
        PrivilegedExceptionAction<Object> peaNull = null;
        AccessControlContext acc = AccessController.getContext();

        try {
            Object obj = Subject.doAsPrivileged(null, pea, acc);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            Object obj = Subject.doAsPrivileged(subj, pea, acc);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            Object obj = Subject.doAsPrivileged(subj, peaNull, acc);
            fail("NullPointerException wasn't thrown");
        } catch (NullPointerException npe) {
        } catch (Exception e) {
            fail(e + " was thrown instead of NullPointerException");
        }

        try {
            Subject.doAsPrivileged(subj, new PrivilegedExceptionAction<Object>(){
                public Object run() throws PrivilegedActionException {
                    throw new PrivilegedActionException(null);
                }
            }, acc);
            fail("PrivilegedActionException wasn't thrown");
        } catch (PrivilegedActionException e) {
        }
    }

    /**
     * javax.security.auth.Subject#getSubject(AccessControlContext acc)
     */
    public void test_getSubject() {
        Subject subj = new Subject();
        AccessControlContext acc = new AccessControlContext(new ProtectionDomain[0]);

        try {
            assertNull(Subject.getSubject(acc));
        } catch (Exception e) {
            fail("Unexpected exception " + e);
        }
    }

    /**
     * javax.security.auth.Subject#toString()
     */
    public void test_toString() {
        Subject subj = new Subject();

        try {
            assertNotNull("Null returned", subj.toString());
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    /**
     * javax.security.auth.Subject#hashCode()
     */
    public void test_hashCode() {
        Subject subj = new Subject();

        try {
            assertNotNull("Null returned", subj.hashCode());
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }
}


class myPrivilegedAction implements PrivilegedAction <Object> {
    myPrivilegedAction(){}
    public Object run() {
        return new Object();
    }
}

class myPrivilegedExceptionAction implements PrivilegedExceptionAction <Object> {
    myPrivilegedExceptionAction(){}
    public Object run() {
        return new Object();
    }
}
