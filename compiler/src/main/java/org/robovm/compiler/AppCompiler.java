/*
 * Copyright (C) 2012 Trillian AB
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.Dependency;
import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.Config.TargetType;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.config.Resource;
import org.robovm.compiler.log.ConsoleLogger;
import org.robovm.compiler.target.LaunchParameters;
import org.robovm.compiler.target.ios.IOSSimulatorLaunchParameters;
import org.robovm.compiler.target.ios.IOSSimulatorLaunchParameters.Family;
import org.robovm.compiler.target.ios.IOSTarget;
import org.robovm.compiler.target.ios.ProvisioningProfile;
import org.robovm.compiler.target.ios.SigningIdentity;
import org.robovm.compiler.util.AntPathMatcher;

/**
 *
 * @version $Id$
 */
public class AppCompiler {

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

    private static final String TRUSTED_CERTIFICATE_STORE_CLASS = 
            "org/apache/harmony/xnet/provider/jsse/TrustedCertificateStore";
    
    private final Config config;
    private final ClassCompiler classCompiler;
    private final Linker linker;
    
    public AppCompiler(Config config) {
        this.config = config;
        this.classCompiler = new ClassCompiler(config);
        this.linker = new Linker(config);
    }
    
    /**
     * Returns all {@link Clazz}es in all {@link Path}s matching the specified ANT-style pattern.
     */
    private Collection<Clazz> getMatchingClasses(String pattern) {
        AntPathMatcher matcher = new AntPathMatcher(pattern, ".");
        Map<String, Clazz> matches = new HashMap<String, Clazz>();
        for (Path path : config.getClazzes().getPaths()) {
            for (Clazz clazz : path.listClasses()) {
                if (!matches.containsKey(clazz.getClassName()) 
                        && matcher.matches(clazz.getClassName())) {
                    
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
        
        if (config.getForceLinkClasses().isEmpty()) {
            if (config.getMainClass() == null) {
                classes.addAll(config.getClazzes().listClasses());
            }
        } else {
            for (String pattern : config.getForceLinkClasses()) {
                if (pattern.indexOf('*') == -1) {
                    Clazz clazz = config.getClazzes().load(pattern.replace('.', '/'));
                    if (clazz == null) {
                        throw new CompilerException("Root class " + pattern + " not found");
                    }
                    classes.add(clazz);
                } else {
                    Collection<Clazz> matches = getMatchingClasses(pattern);
                    if (matches.isEmpty()) {
                        config.getLogger().warn("Root pattern %s matches no classes", pattern);
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
        for (Dependency dep : clazz.getClazzInfo().getDependencies()) {
            Clazz depClazz = config.getClazzes().load(dep.getClassName());
            if (depClazz != null && !compiled.contains(depClazz)) {
                compileQueue.add(depClazz);
            }
        }
    }
    
    public void compile() throws IOException {
        updateCheck();
        
        TreeSet<Clazz> compileQueue = getRootClasses();
        Set<Clazz> linkClasses = new HashSet<Clazz>();
        while (!compileQueue.isEmpty() && !Thread.currentThread().isInterrupted()) {
            Clazz clazz = compileQueue.pollFirst();
            if (!linkClasses.contains(clazz)) {
                compile(clazz, compileQueue, linkClasses);
                linkClasses.add(clazz);
            }
        }
        if (Thread.currentThread().isInterrupted()) {
            return;
        }
        
        if (linkClasses.contains(config.getClazzes().load(TRUSTED_CERTIFICATE_STORE_CLASS))) {
            if (config.getCacerts() != null) {
                config.addResourcesPath(config.getClazzes().createResourcesBootclasspathPath(
                        config.getHome().getCacertsPath(config.getCacerts())));
            }
        }
        
        linker.link(linkClasses);
    }
        
    public static void main(String[] args) throws IOException {
        
        AppCompiler compiler = null;
        
        boolean verbose = false;
        boolean run = false;
        boolean createIpa = false;
        String dumpConfigFile = null;
        List<String> runArgs = new ArrayList<String>();
        List<String> launchArgs = new ArrayList<String>();
        try {
            Config.Builder builder = new Config.Builder();
            
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
                    builder.executableName(args[++i]);
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
                } else if ("-config".equals(args[i])) {
                    builder.read(new File(args[++i]));
                } else if ("-dumpconfig".equals(args[i])) {
                    dumpConfigFile = args[++i];
                } else if ("-properties".equals(args[i])) {
                    builder.addProperties(new File(args[++i]));
                } else if (args[i].startsWith("-P")) {
                    int index = args[i].indexOf('=');
                    if (index <= 0) {
                        throw new IllegalArgumentException("Malformed property: " + args[i]);
                    }
                    String name = args[i].substring(2, index);
                    String value = args[i].substring(index + 1);
                    builder.addProperty(name, value);
                } else if ("-debug".equals(args[i])) {
                    builder.debug(true);
                } else if ("-use-debug-libs".equals(args[i])) {
                    builder.useDebugLibs(true);
                } else if ("-dynamic-jni".equals(args[i])) {
                    builder.useDynamicJni(true);
                } else if ("-skiprt".equals(args[i])) {
                    builder.skipRuntimeLib(true);
                } else if ("-clean".equals(args[i])) {
                    builder.clean(true);
                } else if ("-help".equals(args[i]) || "-?".equals(args[i])) {
                    printUsageAndExit(null);
                } else if ("-version".equals(args[i])) {
                    printVersionAndExit();
                } else if ("-cc".equals(args[i])) {
                    builder.ccBinPath(new File(args[++i]));
                } else if ("-os".equals(args[i])) {
                    String s = args[++i];
                    builder.os("auto".equals(s) ? null : OS.valueOf(s));
                } else if ("-arch".equals(args[i])) {
                    String s = args[++i];
                    builder.arch("auto".equals(s) ? null : Arch.valueOf(s));
//                } else if ("-cpu".equals(args[i])) {
//                    builder.cpu(args[++i]);
                } else if ("-target".equals(args[i])) {
                    String s = args[++i];
                    builder.targetType("auto".equals(s) ? null : TargetType.valueOf(s));
                } else if ("-forcelinkclasses".equals(args[i])) {
                    for (String p : args[++i].split(":")) {
                        p = p.replace('#', '*');
                        builder.addForceLinkClass(p);
                    }
                } else if ("-libs".equals(args[i])) {
                    for (String p : args[++i].split(":")) {
                        builder.addLib(p);
                    }
                } else if ("-exportedsymbols".equals(args[i])) {
                    for (String p : args[++i].split(":")) {
                        builder.addExportedSymbol(p);
                    }
                } else if ("-frameworks".equals(args[i])) {
                    for (String p : args[++i].split(":")) {
                        builder.addFramework(p);
                    }
                } else if ("-weakframeworks".equals(args[i])) {
                    for (String p : args[++i].split(":")) {
                        builder.addWeakFramework(p);
                    }
                } else if ("-resources".equals(args[i])) {
                    for (String p : args[++i].split(":")) {
                        if (AntPathMatcher.isPattern(p)) {
                            File dir = new File(AntPathMatcher.rtrimWildcardTokens(p));
                            String pattern = AntPathMatcher.extractPattern(p);
                            builder.addResource(new Resource(dir, null).include(pattern));
                        } else {
                            builder.addResource(new Resource(new File(p)));
                        }
                    }
                } else if ("-cacerts".equals(args[i])) {
                    String name = args[++i];
                    Config.Cacerts cacerts = null;
                    if (!"none".equals(name)) {
                        try {
                            cacerts = Config.Cacerts.valueOf(name);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Illegal -cacerts value: " + name);
                        }
                    }
                    builder.cacerts(cacerts);
                } else if ("-plist".equals(args[i])) {
                    builder.iosInfoPList(new File(args[++i]));
                } else if ("-entitlements".equals(args[i])) {
                    builder.iosEntitlementsPList(new File(args[++i]));
                } else if ("-resourcerules".equals(args[i])) {
                    builder.iosResourceRulesPList(new File(args[++i]));
                } else if ("-signidentity".equals(args[i])) {
                    builder.iosSignIdentity(SigningIdentity.find(SigningIdentity.list(), args[++i]));
                } else if ("-provisioningprofile".equals(args[i])) {
                    builder.iosProvisioningProfile(ProvisioningProfile.find(ProvisioningProfile.list(), args[++i]));
                } else if ("-sdk".equals(args[i])) {
                    builder.iosSdkVersion(args[++i]);
                } else if ("-ios-sim-family".equals(args[i])) {
                    launchArgs.add(args[i++]);
                    launchArgs.add(args[i]);
                } else if ("-ios-sim-sdk".equals(args[i])) {
                    launchArgs.add(args[i++]);
                    launchArgs.add(args[i]);
                } else if ("-createipa".equals(args[i])) {
                    createIpa = true;
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
            
            if (createIpa && run) {
                throw new IllegalArgumentException("Specify either -run or -createipa, not both");
            }
            
            builder.logger(new ConsoleLogger(verbose));
            builder.skipInstall(run);
            
            if (dumpConfigFile != null) {
                if (dumpConfigFile.equals("-")) {
                    builder.write(new OutputStreamWriter(System.out), new File("."));
                } else {
                    File file = new File(dumpConfigFile);
                    if (file.exists()) {
                        throw new IllegalArgumentException("Cannot dump config to " + file.getAbsolutePath() 
                                + ". The file already exists.");
                    }
                    builder.write(file);
                }
                return;
            }
            
            compiler = new AppCompiler(builder.build());
            
            if (createIpa && (!(compiler.config.getTarget() instanceof IOSTarget) 
                    || compiler.config.getArch() != Arch.thumbv7 
                    || compiler.config.getOs() != OS.ios)) {
                
                throw new IllegalArgumentException("Must build for iOS thumbv7 when creating IPA");
            }
            
        } catch (Throwable t) {
            String message = t.getMessage();
            if (t instanceof ArrayIndexOutOfBoundsException) {
                message = "Missing argument";
            }
            if (t instanceof IndexOutOfBoundsException) {
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
                LaunchParameters launchParameters = compiler.config.getTarget().createLaunchParameters();
                for (int i = 0; i < launchArgs.size(); i++) {
                    String arg = launchArgs.get(i++);
                    if (arg.equals("-ios-sim-family")) {
                        if (launchParameters instanceof IOSSimulatorLaunchParameters) {
                            String name = launchArgs.get(i++);
                            try {
                                ((IOSSimulatorLaunchParameters) launchParameters).setFamily(Family.valueOf(name));
                            } catch (IllegalArgumentException e) {
                                throw new IllegalArgumentException("Illegal -ios-sim-family value: " + name);
                            }
                            continue;
                        }
                    }
                    if (arg.equals("-ios-sim-sdk")) {
                        if (launchParameters instanceof IOSSimulatorLaunchParameters) {
                            String value = launchArgs.get(i++);
                            if (!value.matches("\\d+\\.\\d+(\\.\\d+)")) {
                                throw new IllegalArgumentException("Illegal -ios-sim-sdk value: " + value);
                            }
                            ((IOSSimulatorLaunchParameters) launchParameters).setSdk(value);
                            continue;
                        }
                    }
                    throw new IllegalArgumentException("Unsupported launch argument for the specified target: " + arg);
                }
                launchParameters.setArguments(runArgs);
                Process process = compiler.config.getTarget().launch(launchParameters);
                process.waitFor();
            } else if (createIpa) {
                ((IOSTarget) compiler.config.getTarget()).createIpa();
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
    
    private static void printVersionAndExit() {
        System.err.println(Version.getVersion());
        System.exit(0);
    }
    
    private static void printUsageAndExit(String errorMessage) {
        if (errorMessage != null) {
            System.err.format("robovm: %s\n", errorMessage);
        }
        System.err.println("Usage: robovm [-options] class [run-args]");
        System.err.println("   or  robovm [-options] -jar jarfile [run-args]");
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
                         + "                        Default is <wd>/<class>. Ignored if -run is specified.");
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
        System.err.println("  -o <name>             The name of the target executable");
        System.err.println("  -os <name>            The name of the OS to build for. Allowed values are \n" 
                         + "                        'auto', 'linux', 'macosx' and 'ios'. Default is 'auto' which\n" 
                         + "                        means use the LLVM deafult.");
        System.err.println("  -arch <name>          The name of the LLVM arch to compile for. Allowed values\n" 
                         + "                        are 'auto', 'x86', 'thumbv7'. Default is 'auto' which means\n" 
                         + "                        use the LLVM default.");
        System.err.println("  -cpu <name>           The name of the LLVM cpu to compile for. The LLVM default\n" 
                         + "                        is used if not specified. Use llc to determine allowed values.");
        System.err.println("  -target <name>        The target to build for. Either 'auto', 'console' or 'ios'.\n" 
                         + "                        The default is 'auto' which means use -os to decide.");
        System.err.println("  -forcelinkclasses <list>\n" 
                         + "                        : separated list of class patterns matching\n" 
                         + "                        classes that must be linked in even if not referenced\n" 
                         + "                        (directly or indirectly) from the main class. If no main\n" 
                         + "                        class is specified all classes will be linked in unless this\n" 
                         + "                        option has been given. A pattern is an ANT style path pattern,\n" 
                         + "                        e.g. com.foo.**.bar.*.Main. An alternative syntax using # is\n" 
                         + "                        also supported, e.g. com.##.#.Main.");
        System.err.println("  -run                  Run the executable directly without installing it (-d is\n" 
                         + "                        ignored). The executable will be executed from the\n" 
                         + "                        temporary dir specified with -tmp.");
        System.err.println("  -debug                Generates debug information");
        System.err.println("  -use-debug-libs       Links against debug versions of the RoboVM VM libraries");
        System.err.println("  -dynamic-jni          Use dynamic JNI. Native methods will be dynamically\n" 
                         + "                        linked at runtime. Native methods in classes in the boot\n"
                         + "                        classpath will always use static JNI. On iOS only static\n" 
                         + "                        JNI is supported and this option is ignored.");
        System.err.println("  -libs <list>          : separated list of static library files (.a), object\n"
                         + "                        files (.o) and system libraries that should be included\n" 
                         + "                        when linking the final executable.");
        System.err.println("  -exportedsymbols <list>\n" 
                         + "                        : separated list of symbols that should be exported\n"
                         + "                        when linking the executable. This can be used when\n" 
                         + "                        linking in function which will be called using bro.\n" 
                         + "                        Wildcards can be used. * matches zero or more characters,\n" 
                         + "                        ? matches one character. [abc], [a-z] matches one character\n" 
                         + "                        from the specified set of characters.");
        System.err.println("  -frameworks <list>    : separated list of frameworks that should be included\n" 
                         + "                        when linking the final executable.");
        System.err.println("  -weakframeworks <list>\n" 
                         + "                        : separated list of frameworks that should be weakly linked\n" 
                         + "                        into the final executable.");
        System.err.println("  -resources <list>     : separated list of files and directories that should be\n"
                         + "                        copied to the install dir. Accepts Ant-style patterns.\n" 
                         + "                        If a pattern is specified the left-most path before the\n" 
                         + "                        first wildcard will be used as base directory and will not\n" 
                         + "                        be recreated in the install dir.");
        System.err.println("  -cacerts <value>      Use the specified cacerts file. Allowed value are 'none',\n" 
                         + "                        'full'. Default is 'full' but no cacerts will be included\n" 
                         + "                        unless the code actually needs them.");
        System.err.println("  -skiprt               Do not add default robovm-rt.jar to bootclasspath");
        System.err.println("  -config <file>        Reads the specified configuration XML file. Values set in\n" 
                         + "                        the file will override values set earlier in the command\n" 
                         + "                        line. Later options will override values set in the XML file.\n" 
                         + "                        Can be specified multiple times to read multiple config files.");
        System.err.println("  -dumpconfig <file>    Dumps a configuration XML file to the specified file. Specify\n" 
                         + "                        '-' to dump the config to stdout.");
        System.err.println("  -properties <file>    Reads a Java properties file which will be used when resolving\n" 
                         + "                        variables (enclosed in ${...}) in config XML files and\n" 
                         + "                        Info.plist files. Can be specified multiple times.");
        System.err.println("  -Pname=value          Sets a property value. See the -properties option.");
        System.err.println("  -verbose              Output messages about what the compiler is doing");
        System.err.println("  -version              Print the version of the compiler and exit");
        System.err.println("  -help, -?             Display this information");
        System.err.println("Target specific options:");
        System.err.println("  -createipa            (iOS) Create a .IPA file from the app bundle and place it in\n"
                         + "                        the install dir specified with -d.");
        System.err.println("  -plist <file>         (iOS) Info.plist file to be used by the app. If not specified\n"
                         + "                        a simple Info.plist will be generated with a CFBundleIdentifier\n" 
                         + "                        based on the main class name or executable file name.");
        System.err.println("  -entitlements <file>  (iOS) Property list (.plist) file containing entitlements\n" 
                         + "                        passed to codesign when signing the app.");
        System.err.println("  -resourcerules <file> (iOS) Property list (.plist) file containing resource rules\n" 
                         + "                        passed to codesign when signing the app.");
        System.err.println("  -signidentity <id>    (iOS) Sign using this identity. Default is 'iPhone Developer'.");
        System.err.println("  -provisioningprofile <file>\n" 
                         + "                        (iOS) Provisioning profile to use when building for a device.\n" 
                         + "                        Either a UUID, an app name or app id prefix. If not specified\n" 
                         + "                        a provisioning profile matching the signing identity and bundle\n" 
                         + "                        id from the Info.plist file will be used.");
        System.err.println("  -sdk <version>        (iOS) Version number of the iOS SDK to build against. If not\n" 
                         + "                        specified the latest SDK that can be found will be used.");
        System.err.println("iOS simulator launch options:");
        System.err.println("  -ios-sim-family <fam> The device type that should be simulated. Valid values are\n" 
                         + "                        'iphone' (default) and 'ipad'.");
        System.err.println("  -ios-sim-sdk <sdk>    The iOS SDK version to run the application on (defaults to\n" 
                         + "                        the latest).");
        
        System.exit(errorMessage != null ? 1 : 0);
    }
    
    private class UpdateChecker extends Thread {
        private final String address;
        private volatile JSONObject result;
        public UpdateChecker(String address) {
            this.address = address;
            setDaemon(true);
        }
        @Override
        public void run() {
            result = fetchJson(address);
        }
    }
    
    /**
     * Performs a an update check. If a newer version of RoboVM is available
     * a message will be printed to the log. The update check is also used to
     * gather some anonymous usage statistics.
     */
    private void updateCheck() {
        try {
            String uuid = getInstallUuid();
            if (uuid == null) {
                return;
            }
            long lastCheckTime = getLastUpdateCheckTime();
            if (System.currentTimeMillis() - lastCheckTime < 6 * 60 * 60 * 1000) {
                // Only check for an update once every 6 hours
                return;
            }
            updateLastUpdateCheckTime();
            String osName = System.getProperty("os.name", "Unknown");
            String osArch = System.getProperty("os.arch", "Unknown");
            String osVersion = System.getProperty("os.version", "Unknown");
            UpdateChecker t = new UpdateChecker("http://download.robovm.org/version?"
                    + "uuid=" + URLEncoder.encode(uuid, "UTF-8") + "&"
                    + "version=" + URLEncoder.encode(Version.getVersion(), "UTF-8") + "&"
                    + "osName=" + URLEncoder.encode(osName, "UTF-8") + "&"
                    + "osArch=" + URLEncoder.encode(osArch, "UTF-8") + "&"
                    + "osVersion=" + URLEncoder.encode(osVersion, "UTF-8"));
            t.start();
            t.join(5 * 1000); // Wait for a maximum of 5 seconds
            JSONObject result = t.result;
            if (result != null) {
                String version = result.optString("version", null);
                if (version != null && Version.isOlderThan(version)) {
                    config.getLogger().info("A new version of RoboVM is available. " 
                            + "Current version: %s. New version: %s.", Version.getVersion(), version);
                }
            }
        } catch (Throwable t) {
            if (config.getHome().isDev()) {
                t.printStackTrace();
            }
        }
    }

    private String getInstallUuid() throws IOException {
        File uuidFile = new File(new File(System.getProperty("user.home"), ".robovm"), "uuid");
        uuidFile.getParentFile().mkdirs();
        String uuid = uuidFile.exists() ? FileUtils.readFileToString(uuidFile, "UTF-8") : null;
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            FileUtils.writeStringToFile(uuidFile, uuid, "UTF-8");
        }
        uuid = uuid.trim();
        if (uuid.matches("[0-9a-fA-F-]{36}")) {
            return uuid;
        }
        return null;
    }
    
    private long getLastUpdateCheckTime() {
        try {
            File timeFile = new File(new File(System.getProperty("user.home"), ".robovm"), "last-update-check");
            timeFile.getParentFile().mkdirs();
            return timeFile.exists() ? Long.parseLong(FileUtils.readFileToString(timeFile, "UTF-8").trim()) : 0;
        } catch (IOException e) {
            return 0;
        }
    }

    private void updateLastUpdateCheckTime() throws IOException {
        File timeFile = new File(new File(System.getProperty("user.home"), ".robovm"), "last-update-check");
        timeFile.getParentFile().mkdirs();
        FileUtils.writeStringToFile(timeFile, String.valueOf(System.currentTimeMillis()), "UTF-8");
    }

    private JSONObject fetchJson(String address) {
        try {
            URL url = new URL(address);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(5 * 1000);
            try (InputStream in = new BufferedInputStream(conn.getInputStream())) {
                return new JSONObject(IOUtils.toString(in, "UTF-8"));
            }
        } catch (Exception e) {
            if (config.getHome().isDev()) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
