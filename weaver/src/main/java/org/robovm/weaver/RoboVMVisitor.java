package org.robovm.weaver;

import org.apache.maven.plugin.logging.Log;
import org.objectweb.asm.*;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.BindSelector;
import org.robovm.objc.annotation.TypeEncoding;
import org.robovm.rt.bro.annotation.Callback;

import java.util.ArrayList;
import java.util.List;

public class RoboVMVisitor extends ClassVisitor {
    private String currentClassName;

    private List<PropertyDef> props = new ArrayList<PropertyDef>();
    private Log log;

    public RoboVMVisitor(ClassWriter cw, Log log) {
        super(Opcodes.ASM4, cw);
        this.log = log;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.currentClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public FieldVisitor visitField(final int access, final String fieldName, final String fieldDesc, String signature, Object value) {
        final FieldVisitor fieldVisitor = super.visitField(access, fieldName, fieldDesc, signature, value);
        return new FieldVisitor(Opcodes.ASM4, fieldVisitor) {
            @Override
            public AnnotationVisitor visitAnnotation(String annotationDesc, boolean visible) {
                final AnnotationVisitor annotationVisitor = super.visitAnnotation(annotationDesc, visible);
                if (Type.getType(annotationDesc).getClassName().equals("org.robovm.objc.annotation.IBOutlet")) {
                    final PropertyDef propDef = new PropertyDef();
                    propDef.fieldType = fieldDesc;
                    propDef.fieldName = fieldName;
                    props.add(propDef);
                    return new AnnotationVisitor(Opcodes.ASM4, annotationVisitor) {
                        @Override
                        public void visit(String name, Object value) {
                            if (name.equals("value")) {
                                propDef.propName = (String) value;
                            }
                            super.visit(name, value);
                        }
                    };
                } else {
                    return annotationVisitor;
                }
            }
        };
    }

    @Override
    public void visitEnd() {
        buildSetters();
        super.visitEnd();
    }

    private void buildSetters() {
        for (PropertyDef prop : props) {
            log.info("Processing field '" + prop.fieldName + "'");
            buildSetter(prop);
        }
    }

    private void buildSetter(PropertyDef prop) {
        String propName = prop.propName != null && !prop.propName.isEmpty() ? prop.propName : prop.fieldName;
        String setterJavaName = "set" + capitalize(prop.fieldName);
        String setterObjCName = "set" + capitalize(propName) + ":";

        int ARG_SELF = 0;
        int ARG_SELECTOR = 1;
        int ARG_VALUE = 2;
        final MethodVisitor mv = visitMethod(Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC, setterJavaName, "(L" + currentClassName + ";" + Type.getDescriptor(Selector.class) + prop.fieldType + ")V", null, null);
        AnnotationVisitor av0;
        {
            av0 = mv.visitAnnotation(Type.getDescriptor(Callback.class), true);
            av0.visitEnd();
        }
        {
            av0 = mv.visitAnnotation(Type.getDescriptor(BindSelector.class), true);
            av0.visit("value", setterObjCName);
            av0.visitEnd();
        }
        // FIXME: I think we don't need this anymore
        {
            av0 = mv.visitAnnotation(Type.getDescriptor(TypeEncoding.class), true);
            av0.visit("value", "v@:@");
            av0.visitEnd();
        }
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, ARG_SELF);
        mv.visitVarInsn(Opcodes.ALOAD, ARG_VALUE);
        mv.visitFieldInsn(Opcodes.PUTFIELD, currentClassName, prop.fieldName, prop.fieldType);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2, 3);
        mv.visitEnd();
    }

    private String capitalize(String varName) {
        return Character.toUpperCase(varName.charAt(0)) + varName.substring(1);
    }
}
