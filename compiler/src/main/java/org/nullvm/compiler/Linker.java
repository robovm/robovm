/**
 * 
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Functions.*;
import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Strings.*;
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
import java.util.TreeMap;
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
import org.nullvm.compiler.llvm.Bitcast;
import org.nullvm.compiler.llvm.Constant;
import org.nullvm.compiler.llvm.ConstantAdd;
import org.nullvm.compiler.llvm.ConstantBitcast;
import org.nullvm.compiler.llvm.ConstantGetelementptr;
import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionDeclaration;
import org.nullvm.compiler.llvm.FunctionRef;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Global;
import org.nullvm.compiler.llvm.GlobalRef;
import org.nullvm.compiler.llvm.IntegerConstant;
import org.nullvm.compiler.llvm.Linkage;
import org.nullvm.compiler.llvm.Load;
import org.nullvm.compiler.llvm.Module;
import org.nullvm.compiler.llvm.NullConstant;
import org.nullvm.compiler.llvm.Ret;
import org.nullvm.compiler.llvm.StringConstant;
import org.nullvm.compiler.llvm.StructureConstantBuilder;
import org.nullvm.compiler.llvm.StructureType;
import org.nullvm.compiler.llvm.Type;
import org.nullvm.compiler.llvm.Unreachable;
import org.nullvm.compiler.llvm.Value;
import org.nullvm.compiler.llvm.Variable;
import org.nullvm.compiler.trampoline.Anewarray;
import org.nullvm.compiler.trampoline.Checkcast;
import org.nullvm.compiler.trampoline.ExceptionMatch;
import org.nullvm.compiler.trampoline.FieldAccessor;
import org.nullvm.compiler.trampoline.GetField;
import org.nullvm.compiler.trampoline.Instanceof;
import org.nullvm.compiler.trampoline.Invoke;
import org.nullvm.compiler.trampoline.Invokeinterface;
import org.nullvm.compiler.trampoline.Invokespecial;
import org.nullvm.compiler.trampoline.Invokevirtual;
import org.nullvm.compiler.trampoline.LdcClass;
import org.nullvm.compiler.trampoline.LdcString;
import org.nullvm.compiler.trampoline.Multianewarray;
import org.nullvm.compiler.trampoline.NativeCall;
import org.nullvm.compiler.trampoline.New;
import org.nullvm.compiler.trampoline.PutField;
import org.nullvm.compiler.trampoline.Trampoline;

/**
 * @author niklas
 *
 */
public class Linker {
    private static final String ILLEGAL_ACCESS_ERROR_CLASS = "Attempt to access class %s from class %s";

    private static final Pattern ANT_WILDCARDS = Pattern.compile("\\*\\*\\.?|\\*|\\?");
    
    private final Config config;
    
    private Map<String, Global> strings;
    private Map<String, FunctionDeclaration> functionDeclarations;
    private Map<String, String> aliases;

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
        strings = new HashMap<String, Global>();
        functionDeclarations = new HashMap<String, FunctionDeclaration>();
        aliases = new TreeMap<String, String>();
        
        Set<Clazz> required = new TreeSet<Clazz>(findRequiredClasses());
        config.getLogger().info("Linking %d classes", required.size());

        Module module = new Module();
        module.addInclude(getClass().getClassLoader().getResource("header.ll"));
        
        HashTableGenerator<String, Constant> bcpHashGen = new HashTableGenerator<String, Constant>(new ModifiedUtf8HashFunction());
        HashTableGenerator<String, Constant> cpHashGen = new HashTableGenerator<String, Constant>(new ModifiedUtf8HashFunction());
        Set<Trampoline> trampolines = new HashSet<Trampoline>();
        for (Clazz clazz : required) {
            Global info = getClassInfoStruct(clazz);
            module.addGlobal(info);
            if (clazz.isInBootClasspath()) {
                bcpHashGen.put(clazz.getInternalName(), new ConstantBitcast(info.ref(), I8_PTR));
            } else {
                cpHashGen.put(clazz.getInternalName(), new ConstantBitcast(info.ref(), I8_PTR));
            }
            trampolines.addAll(clazz.getClazzInfo().getTrampolines());
            
            if (!clazz.getClazzInfo().isInterface()) {
                String superclassName = clazz.getClazzInfo().getSuperclass();
                if (superclassName != null && !"java/lang/Object".equals(superclassName)) {
                    // Assume that java.lang.Object has no instance fields
                    module.addGlobal(new Global(mangleClass(clazz.getInternalName()) + "_offset", 
                            alignedOffset(clazz), true));
                }
            }
        }
        module.addGlobal(new Global("_nvmBcBootClassesHash", new ConstantGetelementptr(module.newGlobal(bcpHashGen.generate(), true).ref(), 0, 0)));
        module.addGlobal(new Global("_nvmBcClassesHash", new ConstantGetelementptr(module.newGlobal(cpHashGen.generate(), true).ref(), 0, 0)));
        
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
                bootClasspathValues.add(getString(entryName));
            } else {
                classpathValues.add(getString(entryName));
            }
        }
        bootClasspathValues.add(new NullConstant(Type.I8_PTR));
        classpathValues.add(new NullConstant(Type.I8_PTR));
        module.newGlobal(bootClasspathValues.build());
        module.addGlobal(new Global("_nvmBcBootclasspath", new ConstantGetelementptr(module.newGlobal(bootClasspathValues.build()).ref(), 0, 0)));
        module.addGlobal(new Global("_nvmBcClasspath", new ConstantGetelementptr(module.newGlobal(classpathValues.build()).ref(), 0, 0)));

        if (config.getMainClass() != null) {
            module.addGlobal(new Global("_nvmBcMainClass", getString(config.getMainClass())));
        }        
        
        for (Trampoline t : trampolines) {
            createTrampoline(module, t);
        }
        
        for (Global g : strings.values()) {
            module.addGlobal(g);
        }
        
        for (FunctionDeclaration fd : functionDeclarations.values()) {
            module.addFunctionDeclaration(fd);
        }
        
        File linkerLl = new File(config.getTmpDir(), "linker.ll");
        FileUtils.writeStringToFile(linkerLl, module.toString(), "UTF-8");
        File linkerBc = new File(config.getTmpDir(), "linker.bc");
        CompilerUtil.opt(config, linkerLl, linkerBc, "-mem2reg", "-always-inline");
        File linkerS = new File(config.getTmpDir(), "linker.s");
        CompilerUtil.llc(config, linkerBc, linkerS);

        List<File> objectFiles = new ArrayList<File>();
        objectFiles.add(linkerS);
        
        for (Clazz clazz : required) {
            objectFiles.add(config.getOFile(clazz));
        }
        config.getTarget().build(objectFiles, aliases);
    }
    
    private void createTrampoline(Module module, Trampoline t) {
        if (t instanceof LdcString) {
            return;
        }

        /*
         * Check if the target class exists and is accessible. Also check that
         * field accesses and method calls are compatible with the target 
         * field/method and that the field/method is accessible to the caller.
         * If any of the tests fail the weak trampoline function created by the
         * ClassCompiler will be overridden with a function which throws an
         * appropriate exception.
         */
        Function f = new Function(Linkage.external, t.getFunctionRef());
        if (!checkClassExists(f, t) || !checkClassAccessible(f, t)) {
            module.addFunction(f);
            return;
        }
        if (t instanceof New) {
            Clazz target = config.getClazzes().load(t.getTarget());
            if (target.getClazzInfo().isAbstract() || target.getClazzInfo().isInterface()) {
                call(f, NVM_BC_THROW_INSTANTIATION_ERROR, f.getParameterRef(0), getString(t.getTarget()));
                f.add(new Unreachable());
                module.addFunction(f);
                return;
            }
            aliases.put(t.getFunctionName(), mangleClass(target.getInternalName()) + "_allocator_clinit");
        } else if (t instanceof ExceptionMatch) {
            String fnName = mangleClass(t.getTarget()) + "_exmatch";
            if (!module.hasFunctionDefined(fnName)) {
                Function fn = new Function(Linkage.external, t.getFunctionRef(), fnName);
                GlobalRef exHeader = new GlobalRef(mangleClass(t.getTarget()) + "_info", I8_PTR);
                Value result = call(fn, NVM_BC_EXCEPTION_MATCH, f.getParameterRef(0), 
                        exHeader);
                fn.add(new Ret(result));
                module.addFunction(fn);
            }
            aliases.put(t.getFunctionName(), fnName);
        } else if (t instanceof Instanceof) {
            if (isArray(t.getTarget())) {
                String fnName = "array_" + mangleClass(t.getTarget()) + "_instanceof";
                if (!module.hasFunctionDefined(fnName)) {
                    Function fn = new Function(Linkage.external, t.getFunctionRef(), fnName);
                    Value arrayClass = callLdcArray(module, fn, t.getTarget());
                    Value result = call(fn, NVM_BC_INSTANCEOF_ARRAY, fn.getParameterRef(0), arrayClass, fn.getParameterRef(1));
                    fn.add(new Ret(result));
                    module.addFunction(fn);
                }
                aliases.put(t.getFunctionName(), fnName);
            } else {
                aliases.put(t.getFunctionName(), mangleClass(t.getTarget()) + "_instanceof_clinit");
            }
        } else if (t instanceof Checkcast) {
            if (isArray(t.getTarget())) {
                String fnName = "array_" + mangleClass(t.getTarget()) + "_checkcast";
                if (!module.hasFunctionDefined(fnName)) {
                    Function fn = new Function(Linkage.external, t.getFunctionRef(), fnName);
                    Value arrayClass = callLdcArray(module, fn, t.getTarget());
                    Value result = call(fn, NVM_BC_CHECKCAST_ARRAY, fn.getParameterRef(0), arrayClass, fn.getParameterRef(1));
                    fn.add(new Ret(result));
                    module.addFunction(fn);
                }
                aliases.put(t.getFunctionName(), fnName);
            } else {
                aliases.put(t.getFunctionName(), mangleClass(t.getTarget()) + "_checkcast_clinit");
            }
        } else if (t instanceof LdcClass) {
            if (isArray(t.getTarget())) {
                FunctionRef fn = createLdcArray(module, t.getTarget());
                aliases.put(t.getFunctionName(), fn.getName());
            } else {
                Clazz target = config.getClazzes().load(t.getTarget());
                aliases.put(t.getFunctionName(), mangleClass(target.getInternalName()) + "_ldc_clinit");
            }
        } else if (t instanceof Anewarray) {
            String fnName = "array_" + mangleClass(t.getTarget()) + "_new";
            if (!module.hasFunctionDefined(fnName)) {
                Function fn = new Function(Linkage.external, t.getFunctionRef(), fnName);
                Value arrayClass = callLdcArray(module, fn, t.getTarget());
                Value result = call(fn, NVM_BC_NEW_OBJECT_ARRAY, fn.getParameterRef(0), 
                      fn.getParameterRef(1), arrayClass);
                fn.add(new Ret(result));
                module.addFunction(fn);
            }
            aliases.put(t.getFunctionName(), fnName);
        } else if (t instanceof Multianewarray) {
            String fnName = "array_" + mangleClass(t.getTarget()) + "_multi";
            if (!module.hasFunctionDefined(fnName)) {
                Function fn = new Function(Linkage.external, t.getFunctionRef(), fnName);
                Value arrayClass = callLdcArray(module, fn, t.getTarget());
                Value result = call(fn, NVM_BC_NEW_MULTI_ARRAY, fn.getParameterRef(0), 
                      fn.getParameterRef(1), fn.getParameterRef(2), arrayClass);
                fn.add(new Ret(result));
                module.addFunction(fn);
            }
            aliases.put(t.getFunctionName(), fnName);
        } else if (t instanceof NativeCall) {
            Clazz target = config.getClazzes().load(t.getTarget());
            NativeCall nc = (NativeCall) t;
            String shortName = mangleNativeMethod(target.getInternalName(), nc.getMethodName());
            String longName = mangleNativeMethod(target.getInternalName(), nc.getMethodName(), nc.getMethodDesc());
            if (target.isInBootClasspath()) {
                Function fnLong = new Function(Linkage.weak, longName, nc.getFunctionType());
                call(fnLong, NVM_BC_THROW_UNSATISIFED_LINK_ERROR, fnLong.getParameterRef(0));
                fnLong.add(new Unreachable());
                module.addFunction(fnLong);
                FunctionRef targetFn = fnLong.ref();
                if (!isLongNativeFunctionNameRequired(nc)) {
                    Function fnShort = new Function(Linkage.weak, shortName, nc.getFunctionType());
                    Value resultInner = call(fnShort, fnLong.ref(), fnShort.getParameterRefs());
                    fnShort.add(new Ret(resultInner));
                    module.addFunction(fnShort);
                    targetFn = fnShort.ref();
                }
                Function fn = new Function(Linkage.external, nc.getFunctionRef());
                Value result = call(fn, targetFn, fn.getParameterRefs());
                fn.add(new Ret(result));
                module.addFunction(fn);
            } else {
                Global g = new Global("native_" + mangleMethod(nc.getTarget(), 
                        nc.getMethodName(), nc.getMethodDesc()) + "_ptr", 
                        new NullConstant(I8_PTR));
                module.addGlobal(g);
                Function fn = new Function(Linkage.external, nc.getFunctionRef());
                Value implI8Ptr = call(fn, NVM_BC_RESOLVE_NATIVE, fn.getParameterRef(0), 
                      new GlobalRef(mangleClass(nc.getTarget()) + "_info", I8_PTR),
                      getString(nc.getMethodName()), getString(nc.getMethodDesc()),
                      getString(mangleNativeMethod(nc.getTarget(), nc.getMethodName())),
                      getString(mangleNativeMethod(nc.getTarget(), nc.getMethodName(), nc.getMethodDesc())),
                      g.ref());
                Variable impl = fn.newVariable(nc.getFunctionType());
                fn.add(new Bitcast(impl, implI8Ptr, impl.getType()));
                Value result = call(fn, impl.ref(), fn.getParameterRefs());
                fn.add(new Ret(result));
                module.addFunction(fn);
            }
        } else if (t instanceof FieldAccessor) {
            ResolvedField rf = resolveField(f, (FieldAccessor) t);
            if (rf == null || !checkAccessible(f, t, rf)) {
                module.addFunction(f);
                return;
            }
            Clazz caller = config.getClazzes().load(t.getCallingClass());
            Clazz target = config.getClazzes().load(t.getTarget());
            if (!((FieldAccessor) t).isGetter() &&  rf.field.isFinal() && caller != target) {
                // Only the class declaring a final field may write to it.
                // (Actually only <init>/<clinit> methods may write to it but we 
                // don't know which method is accessing the field at this point)
                throwIllegalAccessError(f, "Attempt to write to final field %s.%s " 
                        + "from class %s", target, rf.field.getName(), caller);
                module.addFunction(f);
                return;
            }
            createTrampolineAliasForField(module, (FieldAccessor) t, rf);
        } else if (t instanceof Invokeinterface) {
            ResolvedMethod rm = resolveInterfaceMethod(f, (Invokeinterface) t);
            if (rm == null || !checkAccessible(f, t, rm)) {
                module.addFunction(f);
                return;
            }
            createTrampolineAliasForMethod(module, (Invoke) t, rm);
        } else if (t instanceof Invoke) {
            ResolvedMethod rm = resolveMethod(f, (Invoke) t);
            if (rm == null || !checkAccessible(f, t, rm)) {
                module.addFunction(f);
                return;
            }
            if (t instanceof Invokespecial && rm.method.isAbstract()) {
                call(f, NVM_BC_THROW_ABSTRACT_METHOD_ERROR, f.getParameterRef(0), 
                        getString(String.format("%s.%s%s", 
                                rm.clazz.getClassName(), rm.method.getName(), 
                                rm.method.getDesc())));
                f.add(new Unreachable());
                module.addFunction(f);
                return;
            }
            createTrampolineAliasForMethod(module, (Invoke) t, rm);
        }
    }
    
    private boolean isLongNativeFunctionNameRequired(NativeCall nc) {
        if (nc.getMethodDesc().startsWith("()")) {
            // If the method takes no parameters the long and short names are the same
            return true;
        }
        Clazz target = config.getClazzes().load(nc.getTarget());
        List<MethodInfo> methods = target.getClazzInfo().getMethods(nc.getMethodName());
        int nativeCount = 0;
        for (MethodInfo mi : methods) {
            if (mi.isNative()) {
                nativeCount++;
            }
        }
        return nativeCount > 1;
    }
    
    private void createTrampolineAliasForField(Module module, FieldAccessor t, ResolvedField rf) {
        String fnName = mangleField(rf.clazz.getInternalName(), t.getFieldName(), t.getFieldDesc());
        fnName += t.isGetter() ? "_getter" : "_setter";
        if (t.isStatic()) {
            fnName += "_clinit";
        }
        aliases.put(t.getFunctionName(), fnName);
    }
    
    private void createTrampolineAliasForMethod(Module module, Invoke t, ResolvedMethod rm) {
        String fnName = mangleMethod(rm.clazz.getInternalName(), t.getMethodName(), t.getMethodDesc());
        if (t instanceof Invokeinterface) {
            fnName += "_lookup";
        } else if (t instanceof Invokevirtual && !rm.clazz.getClazzInfo().isFinal() && !rm.method.isFinal()) {
            fnName += "_lookup";
        } else if (rm.method.isSynchronized()) {
            fnName += "_synchronized";
        }
        if (t.isStatic()) {
            fnName += "_clinit";
        }
        aliases.put(t.getFunctionName(), fnName);
    }
    
    private Value callLdcArray(Module module, Function function, String targetClass) {
        FunctionRef fnRef = createLdcArray(module, targetClass);
        return call(function, fnRef, function.getParameterRef(0));
    }
    
    private FunctionRef createLdcArray(Module module, String targetClass) {
        String fnName = "array_" + mangleClass(targetClass) + "_ldc";
        FunctionRef fnRef = new FunctionRef(fnName, new FunctionType(OBJECT_PTR, ENV_PTR));
        if (!module.hasFunctionDefined(fnName)) {
            Function fn = new Function(Linkage.external, fnRef, fnName);
            Value arrayClass = null;
            if (isPrimitiveComponentType(targetClass)) {
                String primitiveDesc = targetClass.substring(1);
                Variable result = fn.newVariable(OBJECT_PTR);
                fn.add(new Load(result, new GlobalRef("array_" + primitiveDesc, OBJECT_PTR)));
                arrayClass = result.ref();
            } else {
                Global g = new Global("array_" + mangleClass(targetClass) + "_ptr", new NullConstant(OBJECT_PTR));
                if (!module.hasGlobalDefined(g.getName())) {
                    module.addGlobal(g);
                }
                FunctionRef ldcArrayClassFn = NVM_BC_LDC_ARRAY_BOOT_CLASS;
                if (!isPrimitiveBaseType(targetClass)) {
                    Clazz baseType = config.getClazzes().load(getBaseType(targetClass));
                    if (!baseType.isInBootClasspath()) {
                        ldcArrayClassFn = NVM_BC_LDC_ARRAY_CLASS;
                    }
                }
                arrayClass = call(fn, ldcArrayClassFn, fn.getParameterRef(0), g.ref(), getString(targetClass));
            }
            fn.add(new Ret(arrayClass));
            module.addFunction(fn);
        }
        return fnRef;
    }
    
    private Global getClassInfoStruct(Clazz clazz) {
        /*
         * Check that clazz can be loaded, i.e. that the superclass 
         * and interfaces of the class exist and are accessible to the
         * class. Also check that any exception the class uses in catch
         * clauses exist and is accessible to the class. If the class
         * cannot be loaded we override the ClassInfoHeader struct
         * produced by the ClassCompiler for the class with one which
         * tells the code in bc.c to throw an appropriate exception
         * whenever clazz is accessed.
         */
        
        int errorType = ClassCompiler.CI_ERROR_TYPE_NONE;
        String errorMessage = null;
        if (clazz.getClazzInfo().getSuperclass() != null) {
            // Check superclass
            Clazz superclazz = config.getClazzes().load(clazz.getClazzInfo().getSuperclass());
            if (superclazz == null) {
                errorType = ClassCompiler.CI_ERROR_TYPE_NO_CLASS_DEF_FOUND;
                errorMessage = clazz.getClazzInfo().getSuperclass();
            } else if (!checkClassAccessible(superclazz, clazz)) {
                errorType = ClassCompiler.CI_ERROR_TYPE_ILLEGAL_ACCESS;
                errorMessage = String.format(ILLEGAL_ACCESS_ERROR_CLASS, superclazz, clazz);
            } else if (superclazz.getClazzInfo().isInterface()) {
                errorType = ClassCompiler.CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE;
                errorMessage = String.format("class %s has interface %s as super class", clazz, superclazz);
            }
            // No need to check for ClassCircularityError. Soot doesn't handle 
            // such problems so the compilation will fail earlier.
        }
        
        if (errorType == ClassCompiler.CI_ERROR_TYPE_NONE) {
            // Check interfaces
            for (String interfaceName : clazz.getClazzInfo().getInterfaces()) {
                Clazz interfaze = config.getClazzes().load(interfaceName);
                if (interfaze == null) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_NO_CLASS_DEF_FOUND;
                    errorMessage = interfaceName;
                    break;
                } else if (!checkClassAccessible(interfaze, clazz)) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_ILLEGAL_ACCESS;
                    errorMessage = String.format(ILLEGAL_ACCESS_ERROR_CLASS, interfaze, clazz);
                    break;
                } else if (!interfaze.getClazzInfo().isInterface()) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE;
                    errorMessage = String.format("class %s tries to implement class %s as interface", 
                            clazz, interfaze);
                    break;
                }
            }
        }
        
        if (errorType == ClassCompiler.CI_ERROR_TYPE_NONE) {
            // Check exceptions used in catch clauses. I cannot find any info in
            // the VM spec specifying that this has to be done when the class is loaded.
            // However, this is how it's done in other VMs so we do it too.
            for (Trampoline t : clazz.getClazzInfo().getTrampolines()) {
                if (t instanceof ExceptionMatch) {
                    Clazz ex = config.getClazzes().load(t.getTarget());
                    if (ex == null) {
                        errorType = ClassCompiler.CI_ERROR_TYPE_NO_CLASS_DEF_FOUND;
                        errorMessage = t.getTarget();
                        break;
                    } else if (!checkClassAccessible(ex, clazz)) {
                        errorType = ClassCompiler.CI_ERROR_TYPE_ILLEGAL_ACCESS;
                        errorMessage = String.format(ILLEGAL_ACCESS_ERROR_CLASS, ex, clazz);
                        break;
                    }
                }
            }
        }
        
        String name = mangleClass(clazz.getInternalName()) + "_info";
        if (errorType != ClassCompiler.CI_ERROR_TYPE_NONE) {
            // Create a ClassInfoError struct and replace the _info symbol
            StructureConstantBuilder error = new StructureConstantBuilder();
            error.add(new NullConstant(I8_PTR)); // Points to the runtime Class struct
            error.add(new IntegerConstant(ClassCompiler.CI_ERROR));
            error.add(getString(clazz.getInternalName()));
            error.add(new IntegerConstant(errorType));
            error.add(getString(errorMessage));
            return new Global(name, error.build());
        }
        return new Global(name, external, I8_PTR, false);
    }
    
    private boolean checkClassExists(Function f, Trampoline t) {
        String targetClassName = t.getTarget();
        if (isArray(targetClassName)) {
            if (isPrimitiveBaseType(targetClassName)) {
                return true;
            }
            targetClassName = getBaseType(targetClassName);
        }
        
        Clazz target = config.getClazzes().load(targetClassName);
        if (target != null) {
            Clazz caller = config.getClazzes().load(t.getCallingClass());
            // If caller is in the bootclasspath it only sees classes in the bootclasspath
            if (!caller.isInBootClasspath() || target.isInBootClasspath()) {
                return true;
            }
        }
        
        call(f, NVM_BC_THROW_NO_CLASS_DEF_FOUND_ERROR, f.getParameterRef(0), 
                getString(t.getTarget()));
        f.add(new Unreachable());
        return false;
    }
    
    private boolean checkClassAccessible(Clazz target, Clazz caller) {
        if (caller == target) {
            return true; 
        }
        if (target.getClazzInfo().isPublic()) {
            return true; 
        }
        if (target.getPackageName().equals(caller.getPackageName())) {
            return true;
        }
        return false;
    }
    
    private boolean checkClassAccessible(Function f, Trampoline t) {
        Clazz caller = config.getClazzes().load(t.getCallingClass());
        
        String targetClassName = t.getTarget();
        if (isArray(targetClassName)) {
            if (isPrimitiveBaseType(targetClassName)) {
                return true;
            }
            targetClassName = getBaseType(targetClassName);
        }
        Clazz target = config.getClazzes().load(targetClassName);
        if (checkClassAccessible(target, caller)) {
            return true;
        }
        throwIllegalAccessError(f, ILLEGAL_ACCESS_ERROR_CLASS, 
                target, caller);
        f.add(new Unreachable());
        return false;

    }
    
    private boolean checkAccessible(Function f, Trampoline t, ResolvedAccessibleObject rao) {
        Clazz caller = config.getClazzes().load(t.getCallingClass());
        Clazz target = rao.getClazz();
        
        if (caller == target || rao.isPublic()) {
            return true;
        }
        
        if (!rao.isPrivate()) {
            // Package private or protected
            if (target.getPackageName().equals(caller.getPackageName())) {
                return true;
            }
            if (rao.isProtected()) {
                if (rao.isStatic()) {
                    if (isSubClassOrSame(target, caller)) {
                        return true;
                    }
                } else if (isSubClassOrSame(target, caller)) {
                    // Need to check that runtime class is a subclass of caller
                    String runtimeClassName = null;
                    runtimeClassName = t instanceof Invokevirtual 
                            ? ((Invokevirtual) t).getRuntimeClass() : runtimeClassName;
                    runtimeClassName = t instanceof Invokespecial 
                            ? ((Invokespecial) t).getRuntimeClass() : runtimeClassName;
                    runtimeClassName = t instanceof GetField 
                            ? ((GetField) t).getRuntimeClass() : runtimeClassName;
                    runtimeClassName = t instanceof PutField 
                            ? ((PutField) t).getRuntimeClass() : runtimeClassName;
                    if (runtimeClassName == null) {
                        // Call to non-static protected method or non-static 
                        // field access. This will generate an 
                        // IncompatibleClassChangeError by other checks so just 
                        // return true here.
                        return true;
                    }
                    if (isArray(runtimeClassName)) {
                        // Only protected method on array classes is clone()
                        // which is public at runtime so it's accessible.
                        return true;
                    }
                    Clazz runtimeClass = config.getClazzes().load(runtimeClassName);
                    if (runtimeClass == null) {
                        // runtimeClass not found. This will generate a
                        // NoClassDefFoundError before the call to the current
                        // trampoline. Just return true.
                        return true;
                    }
                    if (isSubClassOrSame(caller, runtimeClass)) {
                        return true;
                    }
                }
            }
        }
        
        if (rao instanceof ResolvedMethod) {
            ResolvedMethod rm = (ResolvedMethod) rao;
            throwIllegalAccessError(f, "Attempt to access method %s.%s%s from class %s", 
                    target.getClassName(), rm.method.getName(), 
                    rm.method.getDesc(), caller);
        } else {
            ResolvedField rf = (ResolvedField) rao;
            throwIllegalAccessError(f, "Attempt to access field %s.%s from class %s", 
                            target.getClassName(), rf.field.getName(), caller);
        }
        f.add(new Unreachable());
        return false;
    }
    
    private void throwNoSuchMethodError(Function f, Clazz target, Invoke invoke) {
        call(f, NVM_BC_THROW_NO_SUCH_METHOD_ERROR, f.getParameterRef(0), 
                getString(String.format("%s.%s%s", target.getClassName(), 
                        invoke.getMethodName(), invoke.getMethodDesc())));
        f.add(new Unreachable());
    }
    
    private void throwNoSuchFieldError(Function f, Clazz target, FieldAccessor accessor) {
        call(f, NVM_BC_THROW_NO_SUCH_FIELD_ERROR, f.getParameterRef(0), 
                getString(String.format("%s.%s", target.getClassName(), 
                        accessor.getFieldName())));
        f.add(new Unreachable());
    }
    
    private void throwIncompatibleChangeError(Function f, String message, Object ... args) {
        call(f, NVM_BC_THROW_INCOMPATIBLE_CLASS_CHANGE_ERROR, f.getParameterRef(0), 
                getString(String.format(message, args)));
        f.add(new Unreachable());
    }
    
    private void throwIllegalAccessError(Function f, String message, Object ... args) {
        call(f, NVM_BC_THROW_ILLEGAL_ACCESS_ERROR, f.getParameterRef(0), 
                getString(String.format(message, args)));
        f.add(new Unreachable());
    }
    
    private Constant getString(String string) {
        Global g = strings.get(string);
        if (g == null) {
            byte[] modUtf8 = stringToModifiedUtf8(string);
            g = new Global(getStringVarName(modUtf8), Linkage.linker_private_weak, 
                    new StringConstant(modUtf8), true);
            strings.put(string, g);
        }
        return new ConstantGetelementptr(new GlobalRef(g), 0, 0);
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
    
    private boolean isSubClassOrSame(Clazz superclass, Clazz clazz) {
        while (clazz != null && clazz != superclass) {
            clazz = config.getClazzes().load(clazz.getClazzInfo().getSuperclass());
        }
        return clazz == superclass;
    }

    private Constant alignedOffset(Clazz clazz) {
        Constant offset = new IntegerConstant(0);
        if (clazz.getClazzInfo().getSuperclass() != null) {
            Clazz zuper = config.getClazzes().load(clazz.getClazzInfo().getSuperclass());
            if (zuper != null) {
                List<Type> types = new ArrayList<Type>();
                for (FieldInfo f : zuper.getClazzInfo().getInstanceFields()) {
                    types.add(getType(f.getDesc()));
                }
                if (types.size() > 0) {
                    offset = alignedSizeof(new StructureType(types.toArray(new Type[types.size()])));
                }
                offset = new ConstantAdd(alignedOffset(zuper), offset);
            }
        }
        return offset;
    }

    
    private ResolvedField resolveField(Function f, FieldAccessor t) {
        Clazz target = config.getClazzes().load(t.getTarget());
        String name = t.getFieldName();
        String desc = t.getFieldDesc();
        ResolvedField rf = resolveField(target, name, desc);
        if (rf == null) {
            throwNoSuchFieldError(f, target, t);
            return null;
        }
        
        if (!rf.field.isStatic() && t.isStatic()) {
            throwIncompatibleChangeError(f, "Expected static field %s.%s", 
                    rf.clazz.getClassName(), t.getFieldName());
            return null;
        }
        if (rf.field.isStatic() && !t.isStatic()) {
            throwIncompatibleChangeError(f, "Expected non-static field %s.%s", 
                    rf.clazz.getClassName(), t.getFieldName());
            return null;
        }
        return rf;
    }
    
    private ResolvedField resolveField(Clazz clazz, String name, String desc) {
        if (clazz != null) {
            FieldInfo field = clazz.getClazzInfo().getField(name, desc);
            if (field != null) {
                return new ResolvedField(clazz, field);
            }
            for (String interfaze : clazz.getClazzInfo().getInterfaces()) {
                ResolvedField rf = resolveField(config.getClazzes().load(interfaze), name, desc);
                if (rf != null) {
                    return rf;
                }
            }
            String superclass = clazz.getClazzInfo().getSuperclass();
            if (superclass != null) {
                return resolveField(config.getClazzes().load(superclass), name, desc);
            }
        }
        return null;
    }
    
    private ResolvedMethod resolveMethod(Function f, Invoke t) {
        Clazz target = config.getClazzes().load(t.getTarget());
        String name = t.getMethodName();
        String desc = t.getMethodDesc();
        if (target.getClazzInfo().isInterface()) {
            throwIncompatibleChangeError(f, "Expected class but found interface %s", target);
            return null;
        }
        if ("<init>".equals(name) && t instanceof Invokespecial) {
            MethodInfo m = target.getClazzInfo().getMethod(name, desc);
            if (m != null) {
                return new ResolvedMethod(target, m);
            }
        }
        if ("<clinit>".equals(name) || "<init>".equals(name)) {
            // This is not part of method resolution but we 
            // need to handle it somehow.
            throwNoSuchMethodError(f, target, t);
            return null;
        }
        ResolvedMethod rm = resolveMethod(target, name, desc);
        if (rm == null) {
            throwNoSuchMethodError(f, target, t);
            return null;
        }
        if (t.isStatic() && !rm.method.isStatic()) {
            throwIncompatibleChangeError(f, "Expected static method %s.%s%s", 
                    target, name, desc);
            return null;
        } else if (!t.isStatic() && rm.method.isStatic()) {
            throwIncompatibleChangeError(f, "Expected non-static method %s.%s%s", 
                    target, name, desc);
            return null;
        }        
        return rm;
    }
    
    private ResolvedMethod resolveMethod(Clazz clazz, String name, String desc) {
        if (clazz != null) {
            MethodInfo method = clazz.getClazzInfo().getMethod(name, desc);
            if (method != null) {
                return new ResolvedMethod(clazz, method);
            }

            String superclass = clazz.getClazzInfo().getSuperclass();
            Clazz c = superclass != null ? config.getClazzes().load(superclass) : null;
            while (c != null) {
                method = c.getClazzInfo().getMethod(name, desc);
                if (method != null) {
                    return new ResolvedMethod(c, method);
                }
                superclass = c.getClazzInfo().getSuperclass();
                c = superclass != null ? config.getClazzes().load(superclass) : null;
            }

            c = clazz;
            while (c != null) {
                for (String interfaze : c.getClazzInfo().getInterfaces()) {
                    ResolvedMethod rm = resolveInterfaceMethod(config.getClazzes().load(interfaze), name, desc);
                    if (rm != null) {
                        return rm;
                    }
                }
                superclass = c.getClazzInfo().getSuperclass();
                c = superclass != null ? config.getClazzes().load(superclass) : null;
            }
        }
        return null;
    }
    
    private ResolvedMethod resolveInterfaceMethod(Function f, Invokeinterface t) {
        Clazz target = config.getClazzes().load(t.getTarget());
        String name = t.getMethodName();
        String desc = t.getMethodDesc();
        if (!target.getClazzInfo().isInterface()) {
            throwIncompatibleChangeError(f, "Expected interface but found class %s", target);
            return null;
        }
        if ("<clinit>".equals(name) || "<init>".equals(name)) {
            // This is not part of interface method resolution but we 
            // need to handle it somehow.
            throwNoSuchMethodError(f, target, t);
            return null;
        }
        ResolvedMethod rm = resolveInterfaceMethod(target, name, desc);
        if (rm == null) {
            Clazz javaLangObject = config.getClazzes().load("java/lang/Object");
            MethodInfo method = javaLangObject.getClazzInfo().getMethod(name, desc);
            if (method != null) {
                rm = new ResolvedMethod(javaLangObject, method);
            }
        }
        if (rm == null) {
            throwNoSuchMethodError(f, target, t);
            return null;
        }
        if (rm.method.isStatic()) {
            throwIncompatibleChangeError(f, "Expected non-static method %s.%s%s", 
                    target, name, desc);
            return null;
        }
        return rm;
    }
    
    private ResolvedMethod resolveInterfaceMethod(Clazz clazz, String name, String desc) {
        if (clazz != null) {
            MethodInfo method = clazz.getClazzInfo().getMethod(name, desc);
            if (method != null) {
                return new ResolvedMethod(clazz, method);
            }

            for (String interfaze : clazz.getClazzInfo().getInterfaces()) {
                ResolvedMethod rm = resolveInterfaceMethod(config.getClazzes().load(interfaze), name, desc);
                if (rm != null) {
                    return rm;
                }
            }
        }
        return null;
    }
    
    private interface ResolvedAccessibleObject {
        Clazz getClazz();
        boolean isPublic();
        boolean isProtected();
        boolean isPrivate();
        boolean isStatic();
    }
    
    private static class ResolvedField implements ResolvedAccessibleObject {
        private final Clazz clazz;
        private final FieldInfo field;
        public ResolvedField(Clazz clazz, FieldInfo field) {
            this.clazz = clazz;
            this.field = field;
        }
        public Clazz getClazz() {
            return clazz;
        }
        public boolean isPublic() {
            return field.isPublic();
        }
        public boolean isPrivate() {
            return field.isPrivate();
        }
        public boolean isProtected() {
            return field.isProtected();
        }
        public boolean isStatic() {
            return field.isStatic();
        }
    }
    
    private static class ResolvedMethod implements ResolvedAccessibleObject {
        private final Clazz clazz;
        private final MethodInfo method;
        public ResolvedMethod(Clazz clazz, MethodInfo method) {
            this.clazz = clazz;
            this.method = method;
        }
        public Clazz getClazz() {
            return clazz;
        }        
        public boolean isPublic() {
            return method.isPublic();
        }
        public boolean isPrivate() {
            return method.isPrivate();
        }
        public boolean isProtected() {
            return method.isProtected();
        }
        public boolean isStatic() {
            return method.isStatic();
        }
    }
}
