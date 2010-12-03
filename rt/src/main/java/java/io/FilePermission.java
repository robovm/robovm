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

package java.io;

import java.security.AccessController;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;

import org.apache.harmony.luni.internal.nls.Messages;

/**
 * A permission for accessing a file or directory. The FilePermission is made up
 * of a pathname and a set of actions which are valid for the pathname.
 * <p>
 * The {@code File.separatorChar} must be used in all pathnames when
 * constructing a FilePermission. The following descriptions will assume the
 * char is {@code /}. A pathname that ends in {@code /*} includes all the files
 * and directories contained in that directory. If the pathname
 * ends in {@code /-}, it includes all the files and directories in that
 * directory <i>recursively</i>. The following pathnames have a special meaning:
 * <ul>
 *   <li>
 *     "*": all files in the current directory;
 *   </li>
 *   <li>
 *     "-": recursively all files and directories in the current directory;
 *   </li>
 *   <li>
 *     "&lt;&lt;ALL FILES&gt;&gt;": any file and directory in the file system.
 *   </li>
 * </ul>
 */
public final class FilePermission extends Permission implements Serializable {
    
    private static final long serialVersionUID = 7930732926638008763L;

    // canonical path of this permission
    private transient String canonPath;

    // list of actions permitted for socket permission in order
    private static final String[] actionList = { "read", "write", "execute", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "delete" }; //$NON-NLS-1$

    // "canonicalized" action list
    private String actions;

    // the numeric representation of this action list
    // for implies() to check if one action list is the subset of another.
    transient int mask = -1;

    // global include all permission?
    private transient boolean includeAll = false;

    private transient boolean allDir = false;

    private transient boolean allSubdir = false;

    /**
     * Constructs a new FilePermission with the path and actions specified.
     * 
     * @param path
     *            the pathname of the file or directory to apply the actions to.
     * @param actions
     *            the actions for the {@code path}. May be any combination of
     *            "read", "write", "execute" and "delete".
     * @throws IllegalArgumentException
     *             if {@code actions} is {@code null} or an empty string, or if
     *             it contains a string other than "read", "write", "execute"
     *             and "delete".
     * @throws NullPointerException
     *             if {@code path} is {@code null}.
     */
    public FilePermission(String path, String actions) {
        super(path);
        init(path, actions);
    }

    private void init(final String path, String pathActions) {
        if (pathActions == null || pathActions.equals("")) { //$NON-NLS-1$
            throw new IllegalArgumentException(Messages.getString("luni.B7")); //$NON-NLS-1$
        }
        this.actions = toCanonicalActionString(pathActions);

        if (path == null) {
            throw new NullPointerException(Messages.getString("luni.B8")); //$NON-NLS-1$
        }
        if (path.equals("<<ALL FILES>>")) { //$NON-NLS-1$
            includeAll = true;
        } else {
            canonPath = AccessController
                    .doPrivileged(new PrivilegedAction<String>() {
                        public String run() {
                            try {
                                return new File(path).getCanonicalPath();
                            } catch (IOException e) {
                                return path;
                            }
                        }
                    });
            if (path.equals("*") || path.endsWith(File.separator + "*")) { //$NON-NLS-1$ //$NON-NLS-2$
                allDir = true;
            }
            if (path.equals("-") || path.endsWith(File.separator + "-")) { //$NON-NLS-1$ //$NON-NLS-2$
                allSubdir = true;
            }
        }
    }

    /**
     * Returns the string representing this permission's actions. It must be of
     * the form "read,write,execute,delete", all lower case and in the correct
     * order if there is more than one action.
     * 
     * @param action
     *            the action name
     * @return the string representing this permission's actions
     */
    private String toCanonicalActionString(String action) {
        actions = action.trim().toLowerCase();

        // get the numerical representation of the action list
        mask = getMask(actions);

        // convert the mask to a canonical action list.
        int len = actionList.length;
        // the test mask - shift the 1 to the leftmost position of the
        // actionList
        int highestBitMask = 1 << (len - 1);

        // if a bit of mask is set, append the corresponding action to result
        StringBuilder result = new StringBuilder();
        boolean addedItem = false;
        for (int i = 0; i < len; i++) {
            if ((highestBitMask & mask) != 0) {
                if (addedItem) {
                    result.append(","); //$NON-NLS-1$
                }
                result.append(actionList[i]);
                addedItem = true;
            }
            highestBitMask = highestBitMask >> 1;
        }
        return result.toString();
    }

    /**
     * Returns the numerical representation of the argument.
     * 
     * @param actionNames
     *            the action names
     * @return the action mask
     */
    private int getMask(String actionNames) {
        int actionInt = 0, head = 0, tail = 0;
        do {
            tail = actionNames.indexOf(",", head); //$NON-NLS-1$
            String action = tail > 0 ? actionNames.substring(head, tail).trim()
                    : actionNames.substring(head).trim();
            if (action.equals("read")) { //$NON-NLS-1$
                actionInt |= 8;
            } else if (action.equals("write")) { //$NON-NLS-1$
                actionInt |= 4;
            } else if (action.equals("execute")) { //$NON-NLS-1$
                actionInt |= 2;
            } else if (action.equals("delete")) { //$NON-NLS-1$
                actionInt |= 1;
            } else {
                throw new IllegalArgumentException(Messages.getString(
                        "luni.B9", action)); //$NON-NLS-1$
            }
            head = tail + 1;
        } while (tail > 0);
        return actionInt;
    }

    /**
     * Returns the actions associated with this file permission.
     * 
     * @return the actions associated with this file permission.
     */
    @Override
    public String getActions() {
        return actions;
    }

    /**
     * Indicates if this file permission is equal to another. The two are equal
     * if {@code obj} is a FilePermission, they have the same path, and they
     * have the same actions.
     * 
     * @param obj
     *            the object to check equality with.
     * @return {@code true} if this file permission is equal to {@code obj},
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FilePermission) {
            FilePermission fp = (FilePermission) obj;
            if (fp.actions != actions) {
                if (fp.actions == null || !fp.actions.equals(actions)) {
                    return false;
                }
            }

            /* Matching actions and both are <<ALL FILES>> ? */
            if (fp.includeAll || includeAll) {
                return fp.includeAll == includeAll;
            }
            return fp.canonPath.equals(canonPath);
        }
        return false;
    }

    /**
     * Indicates whether the permission {@code p} is implied by this file
     * permission. This is the case if {@code p} is an instance of
     * {@code FilePermission}, if {@code p}'s actions are a subset of this
     * file permission's actions and if {@code p}'s path is implied by this
     * file permission's path.
     * 
     * @param p
     *            the permission to check.
     * @return {@code true} if the argument permission is implied by the
     *         receiver, and {@code false} if it is not.
     */
    @Override
    public boolean implies(Permission p) {
        int match = impliesMask(p);
        return match != 0 && match == ((FilePermission) p).mask;
    }

    /**
     * Returns an int describing what masks are implied by a specific
     * permission.
     * 
     * @param p
     *            the permission
     * @return the mask applied to the given permission
     */
    int impliesMask(Permission p) {
        if (!(p instanceof FilePermission)) {
            return 0;
        }
        FilePermission fp = (FilePermission) p;
        int matchedMask = mask & fp.mask;
        // Can't match any bits?
        if (matchedMask == 0) {
            return 0;
        }

        // Is this permission <<ALL FILES>>
        if (includeAll) {
            return matchedMask;
        }

        // We can't imply all files
        if (fp.includeAll) {
            return 0;
        }

        // Scan the length of p checking all match possibilities
        // \- implies everything except \
        int thisLength = canonPath.length();
        if (allSubdir && thisLength == 2
                && !fp.canonPath.equals(File.separator)) {
            return matchedMask;
        }
        // need /- to imply /-
        if (fp.allSubdir && !allSubdir) {
            return 0;
        }
        // need /- or /* to imply /*
        if (fp.allDir && !allSubdir && !allDir) {
            return 0;
        }

        boolean includeDir = false;
        int pLength = fp.canonPath.length();
        // do not compare the * or -
        if (allDir || allSubdir) {
            thisLength--;
        }
        if (fp.allDir || fp.allSubdir) {
            pLength--;
        }
        for (int i = 0; i < pLength; i++) {
            char pChar = fp.canonPath.charAt(i);
            // Is p longer than this permissions canonLength?
            if (i >= thisLength) {
                if (i == thisLength) {
                    // Is this permission include all? (must have matched up
                    // until this point).
                    if (allSubdir) {
                        return matchedMask;
                    }
                    // Is this permission include a dir? Continue the check
                    // afterwards.
                    if (allDir) {
                        includeDir = true;
                    }
                }
                // If not includeDir then is has to be a mismatch.
                if (!includeDir) {
                    return 0;
                }
                /**
                 * If we have * for this and find a separator it is invalid. IE:
                 * this is '/a/*' and p is '/a/b/c' we should fail on the
                 * separator after the b. Except for root, canonical paths do
                 * not end in a separator.
                 */
                if (pChar == File.separatorChar) {
                    return 0;
                }
            } else {
                // Are the characters matched?
                if (canonPath.charAt(i) != pChar) {
                    return 0;
                }
            }
        }
        // Must have matched up to this point or it's a valid file in an include
        // all directory
        if (pLength == thisLength) {
            if (allSubdir) {
                // /- implies /- or /*
                return fp.allSubdir || fp.allDir ? matchedMask : 0;
            }
            return allDir == fp.allDir ? matchedMask : 0;
        }
        return includeDir ? matchedMask : 0;
    }

    /**
     * Returns a new PermissionCollection in which to place FilePermission
     * objects.
     * 
     * @return A new PermissionCollection object suitable for storing
     *         FilePermission objects.
     */
    @Override
    public PermissionCollection newPermissionCollection() {
        return new FilePermissionCollection();
    }

    /**
     * Calculates the hash code value for this file permission.
     * 
     * @return the hash code value for this file permission.
     */
    @Override
    public int hashCode() {
        return (canonPath == null ? getName().hashCode() : canonPath.hashCode())
                + mask;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        stream.defaultReadObject();
        init(getName(), actions);
    }
}
