/**
 * 
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Types.*;
import static org.nullvm.compiler.llvm.Linkage.*;
import static org.nullvm.compiler.llvm.Type.*;

import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.nullvm.compiler.clazz.Clazz;
import org.nullvm.compiler.clazz.ClazzInfo;
import org.nullvm.compiler.clazz.ClazzInfo.FieldInfo;
import org.nullvm.compiler.clazz.ClazzInfo.MethodInfo;
import org.nullvm.compiler.clazz.Clazzes;
import org.nullvm.compiler.clazz.Path;
import org.nullvm.compiler.hash.HashTableGenerator;
import org.nullvm.compiler.hash.ModifiedUtf8HashFunction;
import org.nullvm.compiler.llvm.ArrayConstantBuilder;
import org.nullvm.compiler.llvm.Constant;
import org.nullvm.compiler.llvm.ConstantBitcast;
import org.nullvm.compiler.llvm.ConstantGetelementptr;
import org.nullvm.compiler.llvm.Global;
import org.nullvm.compiler.llvm.NullConstant;
import org.nullvm.compiler.llvm.Type;
import org.nullvm.compiler.trampoline.FieldAccessor;
import org.nullvm.compiler.trampoline.Invoke;
import org.nullvm.compiler.trampoline.LdcString;
import org.nullvm.compiler.trampoline.Trampoline;

/**
 * @author niklas
 *
 */
public class Linker {
    private static final Pattern ANT_WILDCARDS = Pattern.compile("\\*\\*\\.?|\\*|\\?");
    
    private final Config config;

    public Linker(Config config) {
        this.config = config;
    }
    
    private Pattern antPatternToRegexp(String pattern) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        Matcher matcher = ANT_WILDCARDS.matcher(pattern);
        while (matcher.find()) {
            sb.append(Pattern.quote(pattern.substring(start, matcher.start())));
            if ("**".equals(matcher.group()) || "**.".equals(matcher.group())) {
                sb.append(".*");
            } else if ("*".equals(matcher.group())) {
                sb.append("[^.]+");
            } else if ("?".equals(matcher.group())) {
                sb.append(".");
            }
            start = matcher.end();
        }
        if (start < pattern.length()) {
            sb.append(Pattern.quote(pattern.substring(start)));
        }
        return Pattern.compile(sb.toString());
    }
    
    private Collection<Clazz> getMatchingClasses(String pattern) {
        Pattern regexp = antPatternToRegexp(pattern);
        Map<String, Clazz> matches = new HashMap<String, Clazz>();
        for (Path path : config.getClazzes().getPaths()) {
            for (Clazz clazz : path.listClasses()) {
                if (!matches.containsKey(clazz.getClassName()) 
                        && regexp.matcher(clazz.getClassName()).matches()) {
                    
                    matches.put(clazz.getClassName(), clazz);
                }
            }
        }
        return matches.values();
    }
    
    private Set<Clazz> getRootClasses() {
        Set<Clazz> classes = new TreeSet<Clazz>();
        classes.addAll(getMatchingClasses("java.lang.**.*"));
        classes.addAll(getMatchingClasses("org.apache.harmony.lang.annotation.*"));
        classes.addAll(getMatchingClasses("org.apache.harmony.niochar.*"));
        classes.addAll(getMatchingClasses("org.apache.harmony.niochar.charset.*"));
        classes.addAll(getMatchingClasses("org.nullvm.rt.*"));
        classes.add(config.getClazzes().load("java/io/Serializable"));
        if (config.getRoots().isEmpty()) {
            if (config.getMainClass() != null) {
                Clazz clazz = config.getClazzes().load(config.getMainClass().replace('.', '/'));
                if (clazz == null) {
                    throw new CompilerException("Main class " + config.getMainClass() + " not found");
                }
                classes.add(clazz);
            } else {
                classes.addAll(config.getClazzes().listClasses());
            }
        } else {
            for (String root : config.getRoots()) {
                if (root.indexOf('*') == -1) {
                    Clazz clazz = config.getClazzes().load(root.replace('.', '/'));
                    if (clazz == null) {
                        throw new CompilerException("Root class " + root + " not found");
                    }
                    classes.add(clazz);
                } else {
                    Collection<Clazz> matches = getMatchingClasses(root);
                    if (matches.isEmpty()) {
                        config.getLogger().warn("Root pattern %s matches no classes", root);
                    } else {
                        classes.addAll(matches);
                    }
                }
            }
        }
        return classes;
    }
    
    private Set<Clazz> findRequiredClasses() {
        Clazzes clazzes = config.getClazzes();
        LinkedList<Clazz> queue = new LinkedList<Clazz>(getRootClasses());
        Set<Clazz> required = new HashSet<Clazz>();
        
        while (!queue.isEmpty()) {
            Clazz clazz = queue.remove();
            if (required.contains(clazz)) {
                continue;
            }
            required.add(clazz);
            
            ClazzInfo clazzInfo = clazz.getClazzInfo();
            Set<String> deps = new HashSet<String>();
            if (clazzInfo.getSuperclass() != null) {
                deps.add(clazzInfo.getSuperclass());
            }
            deps.addAll(clazzInfo.getInterfaces());
            deps.addAll(clazzInfo.getAttributeDependencies());
            for (FieldInfo field : clazzInfo.getFields()) {
                deps.addAll(getDependencies(field.getDesc()));
            }
            for (MethodInfo method : clazzInfo.getMethods()) {
                deps.addAll(getDependencies(method.getDesc()));
            }
            
            for (Trampoline t : clazz.getClazzInfo().getTrampolines()) {
                if (!(t instanceof LdcString)) {
                    String targetClass = t.getTarget();
                    if (isArray(targetClass)) {
                        deps.addAll(getDependencies(targetClass));                        
                    } else {
                        deps.add(targetClass);                        
                    }
                }
                if (t instanceof FieldAccessor) {
                    deps.addAll(getDependencies(((FieldAccessor) t).getFieldDesc()));
                } else if (t instanceof Invoke) {
                    deps.addAll(getDependencies(((Invoke) t).getMethodDesc()));
                }
            }
            
            for (String s : deps) {
                Clazz c = clazzes.load(s);
                if (c == null) {
                    config.getLogger().warn("Class %s depends on non existing class %s", 
                            clazz, s.replace('/', '.'));
                } else if (!required.contains(c)) {
                    queue.add(c);
                }
            }
        }
        
        return new TreeSet<Clazz>(required);
    }
    
    public void link() throws IOException {
        Set<Clazz> required = new TreeSet<Clazz>(findRequiredClasses());
        config.getLogger().info("Linking %d classes", required.size());

        ModuleBuilder mb = new ModuleBuilder();
        mb.addInclude(getClass().getClassLoader().getResource("header.ll"));
        
        HashTableGenerator<String, Constant> bcpHashGen = new HashTableGenerator<String, Constant>(new ModifiedUtf8HashFunction());
        HashTableGenerator<String, Constant> cpHashGen = new HashTableGenerator<String, Constant>(new ModifiedUtf8HashFunction());
        Set<Trampoline> trampolines = new HashSet<Trampoline>();
        for (Clazz clazz : required) {
            Global info = new Global(mangleClass(clazz.getInternalName()) + "_info_struct", external, I8_PTR, false);
            mb.addGlobal(info);
            if (clazz.isInBootClasspath()) {
                bcpHashGen.put(clazz.getInternalName(), new ConstantBitcast(info.ref(), I8_PTR));
            } else {
                cpHashGen.put(clazz.getInternalName(), new ConstantBitcast(info.ref(), I8_PTR));
            }
            trampolines.addAll(clazz.getClazzInfo().getTrampolines());
        }
        mb.addGlobal(new Global("_nvmBcBootClassesHash", new ConstantGetelementptr(mb.newGlobal(bcpHashGen.generate(), true).ref(), 0, 0)));
        mb.addGlobal(new Global("_nvmBcClassesHash", new ConstantGetelementptr(mb.newGlobal(cpHashGen.generate(), true).ref(), 0, 0)));
        
        ArrayConstantBuilder bootClasspathValues = new ArrayConstantBuilder(I8_PTR);
        ArrayConstantBuilder classpathValues = new ArrayConstantBuilder(I8_PTR);
        for (Path path : config.getClazzes().getPaths()) {
            String entryName = null;
            if (config.isSkipInstall() && config.getTarget().canLaunchInPlace()) {
                entryName = path.getFile().getAbsolutePath();
            } else {
                entryName = config.getTarget().getInstallRelativeArchivePath(path);
            }
            if (path.isInBootClasspath()) {
                bootClasspathValues.add(mb.getString(entryName));
            } else {
                classpathValues.add(mb.getString(entryName));
            }
        }
        bootClasspathValues.add(new NullConstant(Type.I8_PTR));
        classpathValues.add(new NullConstant(Type.I8_PTR));
        mb.addGlobal(new Global("_nvmBcBootclasspath", new ConstantGetelementptr(mb.newGlobal(bootClasspathValues.build()).ref(), 0, 0)));
        mb.addGlobal(new Global("_nvmBcClasspath", new ConstantGetelementptr(mb.newGlobal(classpathValues.build()).ref(), 0, 0)));

        if (config.getMainClass() != null) {
            mb.addGlobal(new Global("_nvmBcMainClass", mb.getString(config.getMainClass())));
        }        
        
        File linkerLl = new File(config.getTmpDir(), "linker.ll");
        FileUtils.writeStringToFile(linkerLl, mb.build().toString(), "UTF-8");
        File linkerBc = new File(config.getTmpDir(), "linker.bc");
        CompilerUtil.opt(config, linkerLl, linkerBc, "-mem2reg", "-always-inline");
        File linkerS = new File(config.getTmpDir(), "linker.s");
        CompilerUtil.llc(config, linkerBc, linkerS);
        File linkerO = new File(config.getTmpDir(), "linker.o");
        CompilerUtil.assemble(config, linkerS, linkerO);

        List<File> objectFiles = new ArrayList<File>();
        objectFiles.add(linkerO);
        
        for (Clazz clazz : required) {
            objectFiles.add(config.getOFile(clazz));
        }
        config.getTarget().build(objectFiles);
    }
    
    private Set<String> getDependencies(String desc) {
        return getDependencies(CharBuffer.wrap(desc));
    }
    
    private Set<String> getDependencies(CharBuffer cb) {
        Set<String> result = null;
        while (cb.hasRemaining()) {
            while (cb.hasRemaining() && cb.get() != 'L') {
            }
            if (cb.hasRemaining()) {
                int start = cb.position();
                while (cb.get() != ';') {
                }
                int end = cb.position();
                if (result == null) {
                    result = new HashSet<String>();
                }
                int limit = cb.limit();
                cb.position(start);
                cb.limit(end - 1);
                result.add(cb.toString());
                if (cb.hasRemaining()) {
                    cb.limit(limit);
                    cb.position(end);
                }
            }
        }
        return result != null ? result : Collections.<String>emptySet();
    }
    
}
