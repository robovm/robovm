/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler.plugin.objc;

import static org.objectweb.asm.Opcodes.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.Clazzes;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.plugin.AbstractCompilerPlugin;
import org.robovm.compiler.plugin.CompilerPlugin;

import soot.SootClass;
import soot.SootResolver;

/**
 * {@link CompilerPlugin} which generates proxy classes for Objective-C protocol
 * interfaces. The proxy class implements all @Method and @Property methods of 
 * an interface and all its superinterfaces. We need these proxy classes as
 * peers for Objective-C protocols which don't have a dedicated Java peer
 * class (e.g. when the class implementing the Objective-C protocol isn't 
 * exposed).
 */
public class ObjCProtocolProxyPlugin extends AbstractCompilerPlugin {
    public static final String OBJC_PROTOCOL = "org.robovm.objc.ObjCProtocol";
    public static final String PROXY_CLASS_NAME_SUFFIX = "$ObjCProxy";

    private SootClass org_robovm_objc_ObjCProtocol = null;

    private boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        SootResolver r = SootResolver.v();
        org_robovm_objc_ObjCProtocol = r.makeClassRef(OBJC_PROTOCOL);
        initialized = true;
    }
    
    public static boolean isObjCProxy(SootClass cls) {
        return (cls.getModifiers() & Opcodes.ACC_SYNTHETIC) > 0
            && cls.getName().endsWith(PROXY_CLASS_NAME_SUFFIX);
    }
    
    private boolean isObjCProtocol(SootClass cls) {
        if (org_robovm_objc_ObjCProtocol.isPhantom() || !cls.isInterface()) {
            return false;
        }
        for (SootClass interfaze : cls.getInterfaces()) {
            if (interfaze == org_robovm_objc_ObjCProtocol || isObjCProtocol(interfaze)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns the class the proxy should have as superclass, either
     * <code>org.robovm.apple.foundation.NSObject</code>, 
     * <code>org.robovm.cocoatouch.foundation.NSObject</code> or
     * <code>org.robovm.objc.ObjCObject</code>.
     */
    private String getProxySuperclassInternalName(SootClass cls) {
        if (cls.getName().equals("org.robovm.apple.foundation.NSObjectProtocol")) {
            return "org/robovm/apple/foundation/NSObject";
        }
        if (cls.getName().equals("org.robovm.cocoatouch.foundation.NSObjectProtocol")) {
            return "org/robovm/cocoatouch/foundation/NSObject";
        }
        if (cls == org_robovm_objc_ObjCProtocol) {
            return "org/robovm/objc/ObjCObject";
        }
        for (SootClass interfaze : cls.getInterfaces()) {
            String name = getProxySuperclassInternalName(interfaze);
            if (name != null) {
                return name;
            }
        }
        return null;
    }
    
    private void collectProxyInterfaceInternalNames(SootClass cls, List<String> result) {
        if (result.isEmpty()) {
            result.add(cls.getName().replace('.', '/'));
        }
        for (SootClass interfaze : cls.getInterfaces()) {
            String internalName = interfaze.getName().replace('.', '/');
            if (!result.contains(internalName)) {
                result.add(internalName);
            }
        }
        for (SootClass interfaze : cls.getInterfaces()) {
            collectProxyInterfaceInternalNames(interfaze, result);
        }
    }
    
    private void generateProxyMethods(Config config, List<String> interfazes, 
            ClassWriter cw) throws IOException {
        
        Clazzes clazzes = config.getClazzes();
        
        final Set<String> addedMethods = new HashSet<>();
        for (String interfaze : interfazes) {
            Clazz clazz = clazzes.load(interfaze);
            if (clazz == null) {
                continue;
            }
            
            // Copy all abstract method (we skip default methods) to the proxy 
            // and make them native instead of abstract.
            
            ClassReader classReader = new ClassReader(clazz.getBytes());
            classReader.accept(new ClassVisitor(ASM4, cw) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                        String[] exceptions) {
                    
                    String key = name + desc;
                    if ((access & ACC_ABSTRACT) > 0 && !addedMethods.contains(key)) {
                        access &= ~ACC_ABSTRACT;
                        access |= ACC_NATIVE;
                        addedMethods.add(key);
                        return super.visitMethod(access, name, desc, signature, exceptions);
                    }
                    return null;
                }
                @Override
                public void visit(int version, int access, String name, String signature, String superName,
                        String[] interfaces) {
                    // Ignored
                }
                @Override
                public void visitEnd() {
                    // Ignored
                }
                @Override
                public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                    // Ignored
                    return null;
                }
                @Override
                public void visitAttribute(Attribute attr) {
                    // Ignored
                }
                @Override
                public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                    // Ignored
                    return null;
                }
                @Override
                public void visitInnerClass(String name, String outerName, String innerName, int access) {
                    // Ignored
                }
                @Override
                public void visitOuterClass(String owner, String name, String desc) {
                    // Ignored
                }
                @Override
                public void visitSource(String source, String debug) {
                    // Ignored
                }
            }, 0);
        }
    }
    
    @Override
    public void beforeClass(Config config, Clazz clazz) {
        init();
        SootClass sootClass = clazz.getSootClass();
        if (isObjCProtocol(sootClass)) {
            
            try {
                String proxyInternalName = clazz.getInternalName() + PROXY_CLASS_NAME_SUFFIX;
                ArrayList<String> interfazes = new ArrayList<>();
                collectProxyInterfaceInternalNames(sootClass, interfazes);
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                cw.visit(51, ACC_SUPER + ACC_FINAL + ACC_SYNTHETIC + ACC_PUBLIC,
                        proxyInternalName, null,
                        getProxySuperclassInternalName(sootClass), 
                        new String[] {clazz.getInternalName()});
                generateProxyMethods(config, interfazes, cw);
                cw.visitEnd();
                
                File f = clazz.getPath().getGeneratedClassFile(proxyInternalName);
                FileUtils.writeByteArrayToFile(f, cw.toByteArray());
                // The proxy class is created after the interface is compiled.
                // This prevents the triggering of a recompile of the interface.
                f.setLastModified(clazz.lastModified());

                // Add the proxy class as a dependency for the protocol interface.
                // Important! This must be done AFTER the class file has been written.
                clazz.getClazzInfo().addDependency(proxyInternalName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
}
