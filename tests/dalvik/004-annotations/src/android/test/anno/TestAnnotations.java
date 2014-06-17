package android.test.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class TestAnnotations {

    // RoboVM note: Reflection in Dalvik and RoboVM returns annotations, constructors, methods and 
    // fields in different orders. This test has been changed so that the ordering is ignored.
    
    // Start RoboVM changes 
    static private String annotationMemberToString(Annotation a, Method member) {
        String name = member.getName();
        Object value;
        try {
            value = member.invoke(a);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (member.getReturnType().isArray()) {
            StringBuilder sb = new StringBuilder(80);
            sb.append(name).append("=[");
            int len = Array.getLength(value);
            for (int i = 0; i < len; i++) {
                if (i != 0) sb.append(", ");
                sb.append(Array.get(value, i));
            }
            return sb.append("]").toString();
        } else {
            return name+ "=" +value;
        }
    }
    
    static private String annotationToString(Annotation a) {
        StringBuilder result = new StringBuilder();
        result.append('@');
        result.append(a.annotationType().getName());
        result.append('(');
        TreeMap<String, Method> sorted =
                new TreeMap<String, Method>();
        for (Method method : a.annotationType().getDeclaredMethods()) {
            String name = method.getName();
            if (method.getParameterTypes().length == 0) {
                if (!"toString".equals(name) && !"hashCode".equals(name)) {
                    sorted.put(method.getName(), method);
                }
            }
        }
        Method[] methods = sorted.values().toArray(new Method[0]);
        for (int i = 0; i < methods.length; ++i) {
            if (i != 0) {
                result.append(", ");
            }
            result.append(annotationMemberToString(a, methods[i]));
        }
        result.append(')');
        return result.toString();
    }
    // End RoboVM changes 
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    static private List sortMembers(Object[] members) {
        List l = new ArrayList(Arrays.asList(members));
        Collections.sort(l, new Comparator<Member>() {
            @Override
            public int compare(Member m1, Member m2) {
                return m1.toString().compareTo(m2.toString());
            }
        });
        return l;
    }
    
    /**
     * Print the annotations in sorted order, so as to avoid
     * any (legitimate) non-determinism with regard to the iteration order.
     */
    static private void printAnnotationArray(String prefix, Annotation[] arr) {
        TreeMap<String, Annotation> sorted =
            new TreeMap<String, Annotation>();

        for (Annotation a : arr) {
            sorted.put(a.annotationType().getName(), a);
        }

        for (Annotation a : sorted.values()) {
            System.out.println(prefix + "  " + annotationToString(a));
            System.out.println(prefix + "    " + a.annotationType());
        }
    }

    static void printAnnotations(Class clazz) {
        Annotation[] annos;
        Annotation[][] parAnnos;

        annos = clazz.getAnnotations();
        System.out.println("annotations on TYPE " + clazz +
            "(" + annos.length + "):");
        printAnnotationArray("", annos);
        System.out.println();

        for (Constructor c: (List<Constructor>) sortMembers(clazz.getDeclaredConstructors())) {
            annos = c.getDeclaredAnnotations();
            System.out.println("  annotations on CTOR " + c + ":");
            printAnnotationArray("  ", annos);

            System.out.println("    constructor parameter annotations:");
            for (Annotation[] pannos: c.getParameterAnnotations()) {
                printAnnotationArray("    ", pannos);
            }
        }

        for (Method m: (List<Method>) sortMembers(clazz.getDeclaredMethods())) {
            annos = m.getDeclaredAnnotations();
            System.out.println("  annotations on METH " + m + ":");
            printAnnotationArray("  ", annos);

            System.out.println("    method parameter annotations:");
            for (Annotation[] pannos: m.getParameterAnnotations()) {
                printAnnotationArray("    ", pannos);
            }
        }

        for (Field f: (List<Field>) sortMembers(clazz.getDeclaredFields())) {
            annos = f.getDeclaredAnnotations();
            System.out.println("  annotations on FIELD " + f + ":");
            printAnnotationArray("  ", annos);

            AnnoFancyField aff;
            aff = (AnnoFancyField) f.getAnnotation(AnnoFancyField.class);
            if (aff != null) {
                System.out.println("    aff: " + aff + " / " + aff.getClass());
                System.out.println("    --> nombre is '" + aff.nombre() + "'");
            }
        }
        System.out.println();
    }


    @ExportedProperty(mapping = {
        @IntToString(from = 0, to = "NORMAL_FOCUS"),
        @IntToString(from = 2, to = "WEAK_FOCUS")
    })
    public int getFocusType() {
        return 2;
    }


    @AnnoArrayField
    String thing1;

    @AnnoArrayField(
            zz = {true,false,true},
            bb = {-1,0,1},
            cc = {'Q'},
            ss = {12,13,14,15,16,17},
            ii = {1,2,3,4},
            ff = {1.1f,1.2f,1.3f},
            jj = {-5,0,5},
            dd = {0.3,0.6,0.9},
            str = {"hickory","dickory","dock"}
            )
    String thing2;

    public static void testArrays() {
        TestAnnotations ta = new TestAnnotations();
        Field field;
        Annotation[] annotations;

        try {
            field = TestAnnotations.class.getDeclaredField("thing1");
            annotations = field.getAnnotations();
            System.out.println(field + ": " + annotationToString(annotations[0]));

            field = TestAnnotations.class.getDeclaredField("thing2");
            annotations = field.getAnnotations();
            System.out.println(field + ": " + annotationToString(annotations[0]));
        } catch (NoSuchFieldException nsfe) {
            throw new RuntimeException(nsfe);
        }
    }

    public static void testArrayProblem() {
        Method meth;
        ExportedProperty property;
        final IntToString[] mapping;

        try {
            meth = TestAnnotations.class.getMethod("getFocusType",
                    (Class[])null);
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
        property = meth.getAnnotation(ExportedProperty.class);
        mapping = property.mapping();

        System.out.println("mapping is " + mapping.getClass() +
            "\n  0='" + annotationToString(mapping[0]) + "'\n  1='" + annotationToString(mapping[1]) + "'");

        /* while we're here, check isAnnotationPresent on Method */
        System.out.println("present(getFocusType, ExportedProperty): " +
            meth.isAnnotationPresent(ExportedProperty.class));
        System.out.println("present(getFocusType, AnnoSimpleType): " +
            meth.isAnnotationPresent(AnnoSimpleType.class));

        System.out.println("");
    }



    public static void main(String[] args) {
        System.out.println("TestAnnotations...");

        testArrays();
        testArrayProblem();
        //System.exit(0);

        System.out.println(
            "AnnoSimpleField " + AnnoSimpleField.class.isAnnotation() +
            ", SimplyNoted " + SimplyNoted.class.isAnnotation());

        Class clazz;
        clazz = SimplyNoted.class;
        printAnnotations(clazz);
        clazz = INoted.class;
        printAnnotations(clazz);
        clazz = SubNoted.class;
        printAnnotations(clazz);
        clazz = FullyNoted.class;
        printAnnotations(clazz);

        Annotation anno;

        // this is expected to be non-null
        anno = SimplyNoted.class.getAnnotation(AnnoSimpleType.class);
        System.out.println("SimplyNoted.get(AnnoSimpleType) = " + anno);
        // this is non-null if the @Inherited tag is present
        anno = SubNoted.class.getAnnotation(AnnoSimpleType.class);
        System.out.println("SubNoted.get(AnnoSimpleType) = " + anno);

        System.out.println();

        // Package annotations aren't inherited, so getAnnotations and getDeclaredAnnotations are
        // the same.
        System.out.println("Package annotations:");
        printAnnotationArray("    ", TestAnnotations.class.getPackage().getAnnotations());
        System.out.println("Package declared annotations:");
        printAnnotationArray("    ", TestAnnotations.class.getPackage().getDeclaredAnnotations());
    }
}
