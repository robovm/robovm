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

package java.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import org.apache.harmony.security.fortress.PolicyUtils;
import org.apache.harmony.security.internal.nls.Messages;

/**
 * An {@code UnresolvedPermission} represents a {@code Permission} whose type
 * should be resolved lazy and not during initialization time of the {@code
 * Policy}. {@code UnresolvedPermission}s contain all information to be replaced
 * by a concrete typed {@code Permission} right before the access checks are
 * performed.
 */
public final class UnresolvedPermission extends Permission
    implements Serializable {

    private static final long serialVersionUID = -4821973115467008846L;

    private String type;    
    
    private String name;
    
    private String actions;

    // The signer certificates 
    private transient Certificate[] targetCerts;

    // Cached hash value
    private transient int hash;

    /**
     * Constructs a new instance of {@code UnresolvedPermission}. The supplied
     * parameters are used when this instance is resolved to the concrete
     * {@code Permission}.
     *
     * @param type
     *            the fully qualified class name of the permission this class is
     *            resolved to.
     * @param name
     *            the name of the permission this class is resolved to, maybe
     *            {@code null}.
     * @param actions
     *            the actions of the permission this class is resolved to, maybe
     *            {@code null}.
     * @param certs
     *            the certificates of the permission this class is resolved to,
     *            maybe {@code null}.
     * @throws NullPointerException
     *             if type is {@code null}.
     */
    public UnresolvedPermission(String type, String name, String actions,
                                Certificate[] certs) {
        super(type);
        checkType(type);
        this.type = type;
        this.name = name;
        this.actions = actions;
        if (certs != null) {
            this.targetCerts = new Certificate[certs.length];
            System.arraycopy(certs, 0, targetCerts, 0, certs.length);
        }
        hash = 0;
    }

    // Check type parameter
    private final void checkType(String type) {
        if (type == null) {
            throw new NullPointerException(Messages.getString("security.2F")); //$NON-NLS-1$
        }

        // type is the class name of the Permission class.
        // Empty string is inappropriate for class name.
        // But this check is commented out for compatibility with RI.
        // see JIRA issue HARMONY-733
        // if (type.length() == 0) {
        //     throw new IllegalArgumentException("type cannot be empty");
        // }
    }

    /**
     * Compares the specified object with this {@code UnresolvedPermission} for
     * equality and returns {@code true} if the specified object is equal,
     * {@code false} otherwise. To be equal, the specified object needs to be an
     * instance of {@code UnresolvedPermission}, the two {@code
     * UnresolvedPermission}s must refer to the same type and must have the same
     * name, the same actions and certificates.
     *
     * @param obj
     *            object to be compared for equality with this {@code
     *            UnresolvedPermission}.
     * @return {@code true} if the specified object is equal to this {@code
     *         UnresolvedPermission}, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof UnresolvedPermission) {
            UnresolvedPermission that = (UnresolvedPermission) obj;
            if (getName().equals(that.getName())
                    && (name == null ? that.name == null : name
                            .equals(that.name))
                    && (actions == null ? that.actions == null : actions
                            .equals(that.actions))
                    && equalsCertificates(this.targetCerts, that.targetCerts)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check whether given array of certificates are equivalent
     */
    private boolean equalsCertificates(Certificate[] certs1,
            Certificate[] certs2) {
        if (certs1 == null || certs2 == null) {
            return certs1 == certs2;
        }

        int length = certs1.length;
        if (length != certs2.length) {
            return false;
        }

        if (length > 0) {
            boolean found;
            for (int i = 0; i < length; i++) {
            	// Skip the checking for null
            	if(certs1[i] == null){
            		continue;
            	}
                found = false;
                for (int j = 0; j < length; j++) {
                    if (certs1[i].equals(certs2[j])) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    return false;
                }
            }

            for (int i = 0; i < length; i++) {
            	if(certs2[i] == null){
            		continue;
            	}
                found = false;
                for (int j = 0; j < length; j++) {
                    if (certs2[i].equals(certs1[j])) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the hash code value for this {@code UnresolvedPermission}.
     * Returns the same hash code for {@code UnresolvedPermission}s that are
     * equal to each other as required by the general contract of
     * {@link Object#hashCode}.
     *
     * @return the hash code value for this {@code UnresolvedPermission}.
     * @see Object#equals(Object)
     * @see UnresolvedPermission#equals(Object)
     */
    @Override
    public int hashCode() {
        if (hash == 0) {
            hash = getName().hashCode();
            if (name != null) {
                hash ^= name.hashCode();
            }
            if (actions != null) {
                hash ^= actions.hashCode();
            }
        }
        return hash;
    }

    /**
     * Returns an empty string since there are no actions allowed for {@code
     * UnresolvedPermission}. The actions, specified in the constructor, are
     * used when the concrete permission is resolved and created.
     *
     * @return an empty string, indicating that there are no actions.
     */
    @Override
    public String getActions() {
        return ""; //$NON-NLS-1$
    }

    /**
     * Returns the name of the permission this {@code UnresolvedPermission} is
     * resolved to.
     *
     * @return the name of the permission this {@code UnresolvedPermission} is
     *         resolved to.
     */
    public String getUnresolvedName() {
        return name;
    }

    /**
     * Returns the actions of the permission this {@code UnresolvedPermission}
     * is resolved to.
     *
     * @return the actions of the permission this {@code UnresolvedPermission}
     *         is resolved to.
     */
    public String getUnresolvedActions() {
        return actions;
    }

    /**
     * Returns the fully qualified class name of the permission this {@code
     * UnresolvedPermission} is resolved to.
     *
     * @return the fully qualified class name of the permission this {@code
     *         UnresolvedPermission} is resolved to.
     */
    public String getUnresolvedType() {
        return super.getName();
    }

    /**
     * Returns the certificates of the permission this {@code
     * UnresolvedPermission} is resolved to.
     *
     * @return the certificates of the permission this {@code
     *         UnresolvedPermission} is resolved to.
     */
    public Certificate[] getUnresolvedCerts() {
        if (targetCerts != null) {
            Certificate[] certs = new Certificate[targetCerts.length];
            System.arraycopy(targetCerts, 0, certs, 0, certs.length);
            return certs;
        }
        return null;
    }

    /**
     * Indicates whether the specified permission is implied by this {@code
     * UnresolvedPermission}. {@code UnresolvedPermission} objects imply nothing
     * since nothing is known about them yet.
     * <p>
     * Before actual implication checking, this method tries to resolve
     * UnresolvedPermissions (if any) against the passed instance. Successfully
     * resolved permissions (if any) are taken into account during further
     * processing.
     *
     * @param permission
     *            the permission to check.
     * @return always {@code false}
     */
    @Override
    public boolean implies(Permission permission) {
        return false;
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * {@code UnresolvedPermission} including its target name and its target
     * actions.
     *
     * @return a printable representation for this {@code UnresolvedPermission}.
     */
    @Override
    public String toString() {
        return "(unresolved " + type + " " + name + " " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            + actions + ")"; //$NON-NLS-1$
    }

    /**
     * Returns a new {@code PermissionCollection} for holding {@code
     * UnresolvedPermission} objects.
     *
     * @return a new PermissionCollection for holding {@code
     *         UnresolvedPermission} objects.
     */
    @Override
    public PermissionCollection newPermissionCollection() {
        return new UnresolvedPermissionCollection();
    }

    /**
     * Tries to resolve this permission into the specified class.
     * <p>
     * It is assumed that the class has a proper name (as returned by {@code
     * getName()} of this unresolved permission), so no check is performed to
     * verify this. However, the class must have all required certificates (as
     * per {@code getUnresolvedCerts()}) among the passed collection of signers.
     * If it does, a zero, one, and/or two-argument constructor is tried to
     * instantiate a new permission, which is then returned.
     * <p>
     * If an appropriate constructor is not available or the class is improperly
     * signed, {@code null} is returned.
     *
     * @param targetType
     *            - a target class instance, must not be {@code null}
     * @return resolved permission or null
     */
    Permission resolve(Class targetType) {
        // check signers at first
        if (PolicyUtils.matchSubset(targetCerts, targetType.getSigners())) {
            try {
                return PolicyUtils.instantiatePermission(targetType,
                                                         name,
                                                         actions);
            } catch (Exception ignore) {
                //TODO log warning?
            }
        }
        return null;
    }

    /**
     * Outputs {@code type},{@code name},{@code actions}
     * fields via default mechanism; next manually writes certificates in the
     * following format: <br>
     *
     * <ol>
     * <li> int : number of certs or zero </li>
     * <li> each cert in the following format
     *     <ol>
     *     <li> String : certificate type </li>
     *     <li> int : length in bytes of certificate </li>
     *     <li> byte[] : certificate encoding </li>
     *     </ol>
     * </li>
     * </ol>
     *
     *  @see  <a href="http://java.sun.com/j2se/1.5.0/docs/api/serialized-form.html#java.security.UnresolvedPermission">Java Spec</a>
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (targetCerts == null) {
            out.writeInt(0);
        } else {
            out.writeInt(targetCerts.length);
            for (int i = 0; i < targetCerts.length; i++) {
                try {
                    byte[] enc = targetCerts[i].getEncoded();
                    out.writeUTF(targetCerts[i].getType());
                    out.writeInt(enc.length);
                    out.write(enc);
                } catch (CertificateEncodingException cee) {
                    throw ((IOException)new NotSerializableException(
                        Messages.getString("security.30",  //$NON-NLS-1$
                        targetCerts[i])).initCause(cee));
                }
            }
        }
    }

    /** 
     * Reads the object from stream and checks target type for validity. 
     */
    private void readObject(ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        in.defaultReadObject();        
        checkType(getUnresolvedType());      
        int certNumber = in.readInt();
        if (certNumber != 0) {
            targetCerts = new Certificate[certNumber];
            for (int i = 0; i < certNumber; i++) {
                try {
                    String type = in.readUTF();
                    int length = in.readInt();
                    byte[] enc = new byte[length];
                    in.readFully(enc, 0, length);
                    targetCerts[i] = CertificateFactory.getInstance(type)
                        .generateCertificate(new ByteArrayInputStream(enc));
                } catch (CertificateException cee) {
                    throw ((IOException)new IOException(
                        Messages.getString("security.32")).initCause(cee)); //$NON-NLS-1$
                }
            }
        }
    }
}
