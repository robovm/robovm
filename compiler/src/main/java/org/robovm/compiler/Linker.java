/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.compiler;

import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

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
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.ClazzInfo;
import org.robovm.compiler.clazz.ClazzInfo.FieldInfo;
import org.robovm.compiler.clazz.ClazzInfo.MethodInfo;
import org.robovm.compiler.clazz.Clazzes;
import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.hash.HashTableGenerator;
import org.robovm.compiler.hash.ModifiedUtf8HashFunction;
import org.robovm.compiler.llvm.ArrayConstantBuilder;
import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.ConstantGetelementptr;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.trampoline.FieldAccessor;
import org.robovm.compiler.trampoline.Invoke;
import org.robovm.compiler.trampoline.LdcString;
import org.robovm.compiler.trampoline.Trampoline;

/**
 * @author niklas
 *
 */
public class Linker {
    private static final Pattern ANT_WILDCARDS = Pattern.compile("\\*\\*\\.?|\\*|\\?");
    
    /**
     * Patterns for root classes. Classes matching these patterns will always be linked in.
     */
    private static final String[] ROOT_CLASS_PATTERNS = {
        "java.lang.**.*",
        "org.apache.harmony.lang.annotation.*",
        "org.robovm.rt.*"
    };
    /**
     * Names of root classes. These classes will always be linked in. Most of these
     * are here because they are required by Android's libcore native code.
     */
    private static final String[] ROOT_CLASSES = {
        "java/io/FileDescriptor",
        "java/io/PrintWriter",
        "java/io/Serializable",
        "java/io/StringWriter",
        "java/math/BigDecimal",
        "java/net/Inet6Address",
        "java/net/InetAddress",
        "java/net/InetSocketAddress",
        "java/net/Socket",
        "java/net/SocketImpl",
        "java/nio/charset/CharsetICU",
        "java/text/Bidi$Run",
        "java/text/ParsePosition",
        "java/util/regex/PatternSyntaxException",
        "java/util/zip/Deflater",
        "java/util/zip/Inflater",
        "libcore/icu/LocaleData",
        "libcore/icu/NativeDecimalFormat$FieldPositionIterator",
        "libcore/io/ErrnoException",
        "libcore/io/GaiException",
        "libcore/io/StructAddrinfo",
        "libcore/io/StructFlock",
        "libcore/io/StructGroupReq",
        "libcore/io/StructLinger",
        "libcore/io/StructPasswd",
        "libcore/io/StructPollfd",
        "libcore/io/StructStat",
        "libcore/io/StructStatFs",
        "libcore/io/StructTimeval",
        "libcore/io/StructUtsname",
        "libcore/util/MutableInt",
        "libcore/util/MutableLong"
    };
    
    private final Config config;

    public Linker(Config config) {
        this.config = config;
    }
    
    private Pattern antPatternToRegexp(String pattern) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        Matcher matcher = ANT_WILDCARDS.matcher(pattern);
        while (matcher.find()) {
            if (matcher.start() - start > 0) {
                sb.append(Pattern.quote(pattern.substring(start, matcher.start())));
            }
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
        for (String rootClassPattern : ROOT_CLASS_PATTERNS) {
            classes.addAll(getMatchingClasses(rootClassPattern));            
        }
        for (String rootClassName : ROOT_CLASSES) {
            classes.add(config.getClazzes().load(rootClassName));            
        }
        
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
        mb.addGlobal(new Global("_bcBootClassesHash", new ConstantGetelementptr(mb.newGlobal(bcpHashGen.generate(), true).ref(), 0, 0)));
        mb.addGlobal(new Global("_bcClassesHash", new ConstantGetelementptr(mb.newGlobal(cpHashGen.generate(), true).ref(), 0, 0)));
        
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
        mb.addGlobal(new Global("_bcBootclasspath", new ConstantGetelementptr(mb.newGlobal(bootClasspathValues.build()).ref(), 0, 0)));
        mb.addGlobal(new Global("_bcClasspath", new ConstantGetelementptr(mb.newGlobal(classpathValues.build()).ref(), 0, 0)));

        if (config.getMainClass() != null) {
            mb.addGlobal(new Global("_bcMainClass", mb.getString(config.getMainClass())));
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
