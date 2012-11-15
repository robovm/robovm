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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.exec.ExecuteException;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.Dependency;
import org.robovm.compiler.clazz.Path;

/**
 *
 * @version $Id$
 */
public class AppCompiler {
    /**
     * {@link Pattern} used to convert an ANT-style pattern into a regular expression.
     */
    private static final Pattern ANT_WILDCARDS = Pattern.compile("\\*\\*\\.?|\\*|\\?");
    
    /**
     * Patterns for root classes. Classes matching these patterns will always be linked in.
     */
    private static final String[] ROOT_CLASS_PATTERNS = {
        "java.lang.**.*",
        "org.apache.harmony.lang.annotation.*",
        "org.robovm.rt.**.*"
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
    private final ClassCompiler classCompiler;
    private final Linker linker;
    
    public AppCompiler(Config config) {
        this.config = config;
        this.classCompiler = new ClassCompiler(config);
        this.linker = new Linker(config);
    }
    
    /**
     * Converts an ANT-style pattern into a regular expression.
     */
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
    
    /**
     * Returns all {@link Clazz}es in all {@link Path}s matching the specified ANT-style pattern.
     */
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
    
    /**
     * Returns all root classes. These are the minimum set of classes that needs to be compiled
     * and linked. The compiler will use this set to determine which classes need to be recompiled
     * and linked in through the root classes' dependencies.
     * 
     * The classes matching {@link #ROOT_CLASS_PATTERNS} and {@link #ROOT_CLASSES} will always be 
     * included. If a main class has been specified it will also become a root. Any root class 
     * pattern specified on the command line (as returned by {@link Config#getRoots()} will also be 
     * used to find root classes. If no main class has been specified and {@link Config#getRoots()} 
     * returns an empty set all classes available on the bootclasspath and the classpath will become 
     * roots.
     */
    private TreeSet<Clazz> getRootClasses() {
        TreeSet<Clazz> classes = new TreeSet<Clazz>();
        for (String rootClassPattern : ROOT_CLASS_PATTERNS) {
            classes.addAll(getMatchingClasses(rootClassPattern));            
        }
        for (String rootClassName : ROOT_CLASSES) {
            classes.add(config.getClazzes().load(rootClassName));            
        }

        if (config.getMainClass() != null) {
            Clazz clazz = config.getClazzes().load(config.getMainClass().replace('.', '/'));
            if (clazz == null) {
                throw new CompilerException("Main class " + config.getMainClass() + " not found");
            }
            classes.add(clazz);
        }
        
        if (config.getRoots().isEmpty()) {
            if (config.getMainClass() == null) {
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
    
    private void compile(Clazz clazz, Set<Clazz> compileQueue, Set<Clazz> compiled) throws IOException {
        if (config.isClean() || classCompiler.mustCompile(clazz)) {
            classCompiler.compile(clazz);
        }
        for (Dependency dep : clazz.getDependencies()) {
            Clazz depClazz = config.getClazzes().load(dep.getClassName());
            if (depClazz != null && !compiled.contains(depClazz)) {
                compileQueue.add(depClazz);
            }
        }
    }
    
    public void compile() throws IOException {
        TreeSet<Clazz> compileQueue = getRootClasses();
        Set<Clazz> linkClasses = new HashSet<Clazz>();
        while (!compileQueue.isEmpty()) {
            Clazz clazz = compileQueue.pollFirst();
            if (!linkClasses.contains(clazz)) {
                compile(clazz, compileQueue, linkClasses);
                linkClasses.add(clazz);
            }
        }
        
        if (!config.isSkipLinking()) {
            linker.link(linkClasses);
        }
    }
        
    public static void main(String[] args) throws IOException {
        
        AppCompiler compiler = null;
        
        boolean verbose = false;
        boolean run = false;
        List<String> runArgs = new ArrayList<String>();
        try {
            Config.Builder builder = new Config.Builder();
            OS os = null;
            Arch arch = null;
            
            int i = 0;
            while (i < args.length) {
                if ("-cp".equals(args[i]) || "-classpath".equals(args[i])) {
                    for (String p : args[++i].split(File.pathSeparator)) {
                        builder.addClasspathEntry(new File(p));
                    }
                } else if ("-bcp".equals(args[i]) || "-bootcp".equals(args[i]) || "-bootclasspath".equals(args[i])) {
                    for (String p : args[++i].split(File.pathSeparator)) {
                        builder.addBootClasspathEntry(new File(p));
                    }
                } else if ("-jar".equals(args[i])) {
                    builder.mainJar(new File(args[++i]));
                } else if ("-o".equals(args[i])) {
                    builder.executable(args[++i]);
                } else if ("-d".equals(args[i])) {
                    builder.installDir(new File(args[++i]));
                } else if ("-cache".equals(args[i])) {
                    builder.cacheDir(new File(args[++i]));
                } else if ("-home".equals(args[i])) {
                    builder.home(new Config.Home(new File(args[++i])));
                } else if ("-tmp".equals(args[i])) {
                    builder.tmpDir(new File(args[++i]));
                } else if ("-run".equals(args[i])) {
                    run = true;
                } else if ("-verbose".equals(args[i])) {
                    verbose = true;
                } else if ("-debug".equals(args[i])) {
                    builder.debug(true);
                } else if ("-skiprt".equals(args[i])) {
                    builder.skipRuntimeLib(true);
                } else if ("-skiplink".equals(args[i])) {
                    builder.skipLinking(true);
                } else if ("-clean".equals(args[i])) {
                    builder.clean(true);
                } else if ("-help".equals(args[i]) || "-?".equals(args[i])) {
                    printUsageAndExit(null);
                } else if ("-cc-bin".equals(args[i])) {
                    builder.ccBinPath(new File(args[++i]));
                } else if ("-llvm-home".equals(args[i])) {
                    builder.llvmHomeDir(new File(args[++i]));
                } else if ("-os".equals(args[i])) {
                    String s = args[++i];
                    if (!"auto".equals(s)) {
                        os = OS.valueOf(s);
                        builder.os(os);
                    }
                } else if ("-arch".equals(args[i])) {
                    String s = args[++i];
                    if (!"auto".equals(s)) {
                        arch = Arch.valueOf(s);
                        builder.arch(arch);
                    }
                } else if ("-cpu".equals(args[i])) {
                    builder.cpu(args[++i]);
                } else if ("-roots".equals(args[i])) {
                    for (String p : args[++i].split(":")) {
                        builder.addRoot(p);
                    }
                } else if (args[i].startsWith("-D")) {
                } else if (args[i].startsWith("-X")) {
                } else if (args[i].startsWith("-rvm:")) {
                    runArgs.add(args[i]);
                } else if (args[i].startsWith("-")) {
                    throw new IllegalArgumentException("Unrecognized option: " + args[i]);
                } else {
                    builder.mainClass(args[i++]);
                    break;
                }
                i++;
            }
            
            while (i < args.length) {
                runArgs.add(args[i++]);
            }
            
            if (verbose) {
                builder.logger(new Logger() {
                    public void info(String format, Object... args) {
                        System.out.format(format, args);
                        System.out.println();
                    }
                    public void error(String format, Object... args) {
                        System.err.format(format, args);
                        System.err.println();
                    }
                    public void warn(String format, Object... args) {
                        System.out.format(format, args);
                        System.out.println();
                    }
                    public void debug(String format, Object... args) {
                        System.out.format(format, args);
                        System.out.println();
                    }
                });
            }
            
            builder.skipInstall(run);

            if (os == OS.ios) {
                if (arch != null && arch.isArm()) {
                    builder.targetBuilder(new IOSDeviceTarget.Builder());
                } else {
                    builder.targetBuilder(new IOSSimulatorTarget.Builder());
                }
            }
            
            compiler = new AppCompiler(builder.build());
        } catch (Throwable t) {
            String message = t.getMessage();
            if (t instanceof StringIndexOutOfBoundsException) {
                message = "Missing argument";
            }
            if (verbose && !(t instanceof StringIndexOutOfBoundsException) && !(t instanceof IllegalArgumentException)) {
                t.printStackTrace();
            }
            printUsageAndExit(message);            
        }
        
        try {
            compiler.compile();
            if (run) {
                LaunchParameters launchParameters = new LaunchParameters();
                launchParameters.setArguments(runArgs);
                Process process = compiler.config.getTarget().launch(launchParameters);
                process.waitFor();
            } else {
                compiler.config.getTarget().install();
            }
        } catch (Throwable t) {
            String message = t.getMessage();
            if (verbose && !(t instanceof ExecuteException)) {
                t.printStackTrace();
            }
            printUsageAndExit(message);
        }
    }
    
    private static void printUsageAndExit(String errorMessage) {
        if (errorMessage != null) {
            System.err.format("robovm: %s\n", errorMessage);
        }
        System.err.println("Usage: robovm [-options] class [run-args]");
        System.err.println("   or  robovm [-options] -jar jarfile [run-args]");
        System.err.println("   or  robovm [-options] -skiplink");
        System.err.println("Options:");
        
        System.err.println("  -bootclasspath <list> ");
        System.err.println("  -bootcp <list>        ");
        System.err.println("  -bcp <list>           : separated list of directories, JAR archives, and ZIP \n" 
                         + "                        archives to search for class files. Used to locate the \n" 
                         + "                        java.* and javax.* classes. Default is \n"
                         + "                        <robovm-home>/lib/robovm-rt.jar.");
        System.err.println("  -cp <list>            ");
        System.err.println("  -classpath <list>     : separated list of directories, JAR archives, and ZIP \n" 
                         + "                        archives to search for class files.");
        System.err.println("  -cache <dir>          Directory where cached compiled class files will be placed.\n" 
                         + "                        Default is ~/.robovm/cache");
        System.err.println("  -clean                Compile class files even if a compiled version already \n" 
                         + "                        exists in the cache.");
        System.err.println("  -d <dir>              Install the generated executable and other files in <dir>.\n" 
                         + "                        Default is <wd>/<class>");
        System.err.println("  -cc <path>            Path to the c compiler binary. gcc and clang are supported.");
        System.err.println("  -home <dir>           Directory where RoboVM runtime has been installed.\n"
                         + "                        Default is $ROBOVM_HOME. If not set the following paths\n" 
                         + "                        will be searched: ~/Applications/robovm/, ~/.robovm/home/,\n" 
                         + "                        /usr/local/lib/robovm/, /opt/robovm/, /usr/lib/robovm/.");
        System.err.println("  -tmp <dir>            Directory where temporary files will be placed during\n"
                         + "                        compilation. By default a new dir will be created under\n" 
                         + "                        ${java.io.tmpdir}.");
        System.err.println("  -jar <path>           Use main class as specified by the manifest in this JAR \n" 
                         + "                        archive.");
        System.err.println("  -llvm-home <path>     Path where LLVM has been installed");
        System.err.println("  -o <name>             The name of the target executable or library");
        System.err.println("  -os <name>            The name of the OS to build for. Allowed values are \n" 
                         + "                        'auto', 'linux', 'macosx' and 'ios'. Default is 'auto' which\n" 
                         + "                        means autodetect.");
        System.err.println("  -arch <name>          The name of the LLVM arch to compile for. Allowed values\n" 
                         + "                        are 'auto', 'x86', 'armv6', 'armv7', 'thumbv6',\n" 
                         + "                        'thumbv7' Default is 'auto' which means autodetect.");
        System.err.println("  -cpu <name>           The name of the LLVM cpu to compile for. The LLVM default\n" 
                         + "                        is used by default. Use llc to determine allowed values.");
        System.err.println("  -roots <list>         : separated list of class patterns matching\n" 
                         + "                        classes that must be included when determinig the required\n" 
                         + "                        classes. If a main class is specified it will automatically\n" 
                         + "                        become a root. If no main class is specified and no roots\n" 
                         + "                        all classes will be included. A pattern is an ANT style\n" 
                         + "                        path pattern, e.g. com.foo.**.bar.*.Main.");
        System.err.println("  -debug                Generates debug information");
        System.err.println("  -skiprt               Do not add default robovm-rt.jar to bootclasspath");
        System.err.println("  -skiplink             Do not link the final executable");
        System.err.println("  -verbose              Output messages about what the compiler is doing");
        System.err.println("  -help, -?             Display this information");
        
        System.exit(errorMessage != null ? 1 : 0);
    }
    
}
