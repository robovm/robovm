/**
 * 
 */
package org.nullvm.compiler;

import soot.ClassMember;
import soot.SootClass;

/**
 * @author niklas
 *
 */
public class Access {
    public static final String ILLEGAL_ACCESS_ERROR_FIELD = "Attempt to access field %s.%s from class %s";
    public static final String ILLEGAL_ACCESS_ERROR_METHOD = "Attempt to access method %s.%s%s from class %s";
    public static final String ILLEGAL_ACCESS_ERROR_CLASS = "Attempt to access class %s from class %s";

    public static boolean checkClassAccessible(SootClass target, SootClass caller) {
        if (caller == target) {
            return true; 
        }
        if (target.isPublic()) {
            return true; 
        }
        if (target.getPackageName().equals(caller.getPackageName())) {
            return true;
        }
        return false;
    }
    
    public static boolean checkMemberAccessible(ClassMember member, SootClass caller, 
            SootClass runtimeClass) {
        
        SootClass target = member.getDeclaringClass();
        
        if (caller == target || member.isPublic()) {
            return true;
        }
        
        if (!member.isPrivate()) {
            // Package private or protected
            if (target.getPackageName().equals(caller.getPackageName())) {
                return true;
            }
            if (member.isProtected()) {
                if (member.isStatic()) {
                    if (isSubClassOrSame(target, caller)) {
                        return true;
                    }
                } else if (isSubClassOrSame(target, caller)) {
                    // Need to check that runtime class is a subclass of caller
                    if (runtimeClass == null) {
                        // Either the runtime class is an array or invokestatic 
                        // or getstatic/putstatic is used to call a non-static 
                        // protected method or access a non-static field. We just
                        // return true here and assume that other code will 
                        // check these things.
                        return true;
                    }
                    if (isSubClassOrSame(caller, runtimeClass)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    
    public static boolean isSubClassOrSame(SootClass superclass, SootClass clazz) {
        while (clazz != null && !clazz.isPhantom() && clazz.hasSuperclass() && clazz != superclass) {
            clazz = clazz.getSuperclass();
        }
        return clazz == superclass;
    }
}
