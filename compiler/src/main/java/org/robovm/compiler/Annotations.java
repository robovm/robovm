/**
 * 
 */
package org.robovm.compiler;

import java.util.List;

import soot.SootMethod;
import soot.tagkit.AnnotationTag;
import soot.tagkit.Host;
import soot.tagkit.VisibilityAnnotationTag;
import soot.tagkit.VisibilityParameterAnnotationTag;

/**
 * @author niklas
 *
 */
public class Annotations {
    public static final String BRIDGE = "Lorg/robovm/rt/bro/annotation/Bridge;";
    public static final String CALLBACK = "Lorg/robovm/rt/bro/annotation/Callback;";
    public static final String STRUCT_MEMBER = "Lorg/robovm/rt/bro/annotation/StructMember;";
    public static final String STRUCT_RET = "Lorg/robovm/rt/bro/annotation/StructRet;";
    public static final String POINTER = "Lorg/robovm/rt/bro/annotation/Pointer;";

    public static boolean hasAnnotation(Host host, String annotationType) {
        VisibilityAnnotationTag vatag = (VisibilityAnnotationTag) host.getTag("VisibilityAnnotationTag");
        if (vatag != null) {
            for (AnnotationTag tag : vatag.getAnnotations()) {
                if (annotationType.equals(tag.getType())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean hasParameterAnnotation(SootMethod method, int paramIndex, String annotationType) {
        VisibilityParameterAnnotationTag vpatag = (VisibilityParameterAnnotationTag) method.getTag("VisibilityParameterAnnotationTag");
        if (vpatag != null) {
            List<VisibilityAnnotationTag> tags = vpatag.getVisibilityAnnotations();
            List<AnnotationTag> annotations = tags.get(paramIndex).getAnnotations();
            if (annotations != null) {
                for (AnnotationTag tag : annotations) {
                    if (annotationType.equals(tag.getType())) {
                        return true;
                    }                    
                }
            }
        }
        return false;
    }
    
    public static boolean hasBridgeAnnotation(SootMethod method) {
        return hasAnnotation(method, BRIDGE);
    }
    
    public static boolean hasCallbackAnnotation(SootMethod method) {
        return hasAnnotation(method, CALLBACK);
    }
    
    public static boolean hasStructMemberAnnotation(SootMethod method) {
        return hasAnnotation(method, STRUCT_MEMBER);
    }
    
    public static boolean hasPointerAnnotation(SootMethod method) {
        return hasAnnotation(method, POINTER);
    }
    
    public static boolean hasStructRetAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, STRUCT_RET);
    }
    
    public static boolean hasPointerAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, POINTER);
    }
}
