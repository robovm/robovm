/*
 * Copyright (C) 2012 RoboVM AB
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
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.Clazzes;
import org.robovm.compiler.clazz.Dependency;
import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.Config.TargetType;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.config.Resource;
import org.robovm.compiler.log.ConsoleLogger;
import org.robovm.compiler.plugin.LaunchPlugin;
import org.robovm.compiler.plugin.Plugin;
import org.robovm.compiler.plugin.PluginArgument;
import org.robovm.compiler.target.LaunchParameters;
import org.robovm.compiler.target.ios.DeviceType;
import org.robovm.compiler.target.ios.IOSSimulatorLaunchParameters;
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
     * Patterns for root classes. Classes matching these patterns will always be
     * linked in.
     */
    private static final String[] ROOT_CLASS_PATTERNS = {
        "java.lang.**.*",
        "org.robovm.rt.**.*"
    };
    /**
     * Names of root classes. These classes will always be linked in. Most of
     * these are here because they are required by Android's libcore native
     * code.
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
        "java/net/InetUnixAddress",
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
        "libcore/io/StructStatVfs",
        "libcore/io/StructTimeval",
        "libcore/io/StructUtsname",
        "libcore/util/MutableInt",
        "libcore/util/MutableLong"
    };

    private static final String TRUSTED_CERTIFICATE_STORE_CLASS =
            "com/android/org/conscrypt/TrustedCertificateStore";

    /**
     * An {@link Executor} which runs tasks immediately without creating a
     * separate thread.
     */
    static final Executor SAME_THREAD_EXECUTOR = new Executor() {
        public void execute(Runnable r) {
            r.run();
        }
    };

    private final Config config;
    private final ClassCompiler classCompiler;
    private final Linker linker;

    public AppCompiler(Config config) {
        this.config = config;
        this.classCompiler = new ClassCompiler(config);
        this.linker = new Linker(config);
    }

    public Config getConfig() {
        return config;
    }

    /**
     * Returns all {@link Clazz}es in all {@link Path}s matching the specified
     * ANT-style pattern.
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
     * Returns all root classes. These are the minimum set of classes that needs
     * to be compiled and linked. The compiler will use this set to determine
     * which classes need to be recompiled and linked in through the root
     * classes' dependencies.
     * 
     * The classes matching {@link #ROOT_CLASS_PATTERNS} and
     * {@link #ROOT_CLASSES} will always be included. If a main class has been
     * specified it will also become a root. Any root class pattern specified on
     * the command line (as returned by {@link Config#getRoots()} will also be
     * used to find root classes. If no main class has been specified and
     * {@link Config#getRoots()} returns an empty set all classes available on
     * the bootclasspath and the classpath will become roots.
     */
    private TreeSet<Clazz> getRootClasses() {
        TreeSet<Clazz> classes = new TreeSet<Clazz>();
        for (String rootClassPattern : ROOT_CLASS_PATTERNS) {
            classes.addAll(getMatchingClasses(rootClassPattern));
        }
        for (String rootClassName : ROOT_CLASSES) {
            Clazz clazz = config.getClazzes().load(rootClassName);
            if (clazz == null) {
                throw new CompilerException("Root class " + rootClassName + " not found");
            }
            classes.add(clazz);
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
                if (pattern == null || pattern.trim().isEmpty()) {
                    continue;
                }
                pattern = pattern.trim();
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

    private boolean compile(Executor executor, ClassCompilerListener listener,
            Clazz clazz, Set<Clazz> compileQueue, Set<Clazz> compiled,
            boolean compileDependencies) throws IOException {

        boolean result = false;
        if (config.isClean() || classCompiler.mustCompile(clazz)) {
            classCompiler.compile(clazz, executor, listener);
            result = true;
        }
        if (compileDependencies) {
            for (Dependency dep : clazz.getClazzInfo().getDependencies()) {
                Clazz depClazz = config.getClazzes().load(dep.getClassName());
                if (depClazz != null && !compiled.contains(depClazz)) {
                    compileQueue.add(depClazz);
                }
            }
            addMetaInfImplementations(config.getClazzes(), clazz, compiled, compileQueue);
        }
        return result;
    }

    static void addMetaInfImplementations(Clazzes clazzes, Clazz clazz, Set<Clazz> compiled, Set<Clazz> compileQueue)
            throws IOException {
        String metaInfName = "META-INF/services/" + clazz.getClassName();
        IOException throwLater = null;
        for (InputStream is : clazzes.loadResources(metaInfName)) {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(is, "UTF8"))) {
                for (;;) {
                    String line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    if (line.startsWith("#")) {
                        continue;
                    }
                    String implClazzName = line.replace('.', '/');
                    Clazz implClazz = clazzes.load(implClazzName);
                    if (implClazz != null && !compiled.contains(implClazz)) {
                        compileQueue.add(implClazz);
                    }
                }
            } catch (IOException ex) {
                throwLater = ex;
            }
        }
        if (throwLater != null) {
            throw throwLater;
        }
    }

    public Set<Clazz> compile(Collection<Clazz> rootClasses, boolean compileDependencies,
            final ClassCompilerListener listener) throws IOException {

        config.getLogger().debug("Compiling classes using %d threads", config.getThreads());

        final Executor executor = (config.getThreads() <= 1)
                ? SAME_THREAD_EXECUTOR
                : new ThreadPoolExecutor(config.getThreads() - 1, config.getThreads() - 1,
                        0L, TimeUnit.MILLISECONDS,
                        // Use a bounded queue to avoid memory problems if the
                        // worker threads are slower than the enqueuing thread.
                        // The optimal thread pool size and queue size have been
                        // determined by trial and error.
                        new ArrayBlockingQueue<Runnable>((config.getThreads() - 1) * 20));
        class HandleFailureListener implements ClassCompilerListener {
            volatile Throwable t;

            @Override
            public void success(Clazz clazz) {
                if (listener != null) {
                    listener.success(clazz);
                }
            }

            @Override
            public void failure(Clazz clazz, Throwable t) {
                // Compilation failed. Save the error and stop the executor.
                this.t = t;
                if (executor instanceof ExecutorService) {
                    ((ExecutorService) executor).shutdown();
                }
                if (listener != null) {
                    listener.failure(clazz, t);
                }
            }
        };
        HandleFailureListener listenerWrapper = new HandleFailureListener();

        TreeSet<Clazz> compileQueue = new TreeSet<>(rootClasses);
        long start = System.currentTimeMillis();
        Set<Clazz> linkClasses = new HashSet<Clazz>();
        int compiledCount = 0;
        while (!compileQueue.isEmpty() && !Thread.currentThread().isInterrupted()) {
            Clazz clazz = compileQueue.pollFirst();
            if (!linkClasses.contains(clazz)) {
                if (compile(executor, listenerWrapper, clazz, compileQueue, linkClasses, compileDependencies)) {
                    compiledCount++;
                    if (listenerWrapper.t != null) {
                        // We have a failed compilation. Stop compiling.
                        break;
                    }
                }
                linkClasses.add(clazz);
            }
        }

        // Shutdown the executor and wait for running tasks to complete.
        if (executor instanceof ExecutorService) {
            ExecutorService executorService = (ExecutorService) executor;
            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            } catch (InterruptedException e) {
            }
        }

        if (listenerWrapper.t != null) {
            // The compilation failed. Rethrow the exception in the callback.
            if (listenerWrapper.t instanceof IOException) {
                throw (IOException) listenerWrapper.t;
            }
            if (listenerWrapper.t instanceof RuntimeException) {
                throw (RuntimeException) listenerWrapper.t;
            }
            if (listenerWrapper.t instanceof Error) {
                throw (Error) listenerWrapper.t;
            }
            throw new CompilerException(listenerWrapper.t);
        }

        long duration = System.currentTimeMillis() - start;
        config.getLogger().debug("Compiled %d classes in %.2f seconds", compiledCount, duration / 1000.0);

        return linkClasses;
    }

    public void compile() throws IOException {
        updateCheck();

        Set<Clazz> linkClasses = compile(getRootClasses(), true, null);

        if (Thread.currentThread().isInterrupted()) {
            return;
        }

        if (linkClasses.contains(config.getClazzes().load(TRUSTED_CERTIFICATE_STORE_CLASS))) {
            if (config.getCacerts() != null) {
                config.addResourcesPath(config.getClazzes().createResourcesBootclasspathPath(
                        config.getHome().getCacertsPath(config.getCacerts())));
            }
        }

        long start = System.currentTimeMillis();
        linker.link(linkClasses);
        long duration = System.currentTimeMillis() - start;
        config.getLogger().debug("Linked %d classes in %.2f seconds", linkClasses.size(), duration / 1000.0);
    }

    public static void main(String[] args) throws IOException {

        AppCompiler compiler = null;
        Config.Builder builder = null;

        boolean verbose = false;
        boolean run = false;
        boolean createIpa = false;
        List<Arch> ipaArchs = new ArrayList<>();
        String dumpConfigFile = null;
        List<String> runArgs = new ArrayList<String>();
        try {
            builder = new Config.Builder();
            Map<String, PluginArgument> pluginArguments = builder.fetchPluginArguments();

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
                } else if ("-threads".equals(args[i])) {
                    String s = args[++i];
                    try {
                        int n = Integer.parseInt(s);
                        // Make sure n > 0 and cap at 128 threads.
                        n = Math.max(n, 1);
                        n = Math.min(n, 128);
                        builder.threads(n);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Unparsable thread count: " + s);
                    }
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
                } else if ("-dump-intermediates".equals(args[i])) {
                    builder.dumpIntermediates(true);
                } else if ("-dynamic-jni".equals(args[i])) {
                    builder.useDynamicJni(true);
                } else if ("-skiprt".equals(args[i])) {
                    builder.skipRuntimeLib(true);
                } else if ("-skipsign".equals(args[i])) {
                    builder.iosSkipSigning(true);
                } else if ("-clean".equals(args[i])) {
                    builder.clean(true);
                } else if ("-help".equals(args[i]) || "-?".equals(args[i])) {
                    printUsageAndExit(null, builder.getPlugins());
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
                        builder.addLib(new Config.Lib(p, true));
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
                } else if ("-printdevicetypes".equals(args[i])) {
                    printDeviceTypesAndExit();
                } else if ("-devicetype".equals(args[i])) {
                    builder.iosDeviceType(args[++i]);
                } else if ("-createipa".equals(args[i])) {
                    createIpa = true;
                } else if ("-ipaarchs".equals(args[i])) {
                    for (String s : args[++i].split(":")) {
                        ipaArchs.add(Arch.valueOf(s));
                    }
                } else if (args[i].startsWith("-D")) {
                } else if (args[i].startsWith("-X")) {
                } else if (args[i].startsWith("-rvm:")) {
                    runArgs.add(args[i]);
                } else if (args[i].startsWith("-")) {
                    String argName = args[i].substring(1, args[i].length());
                    if (argName.contains("=")) {
                        argName = argName.substring(0, argName.indexOf('='));
                    }
                    PluginArgument arg = pluginArguments.get(argName);
                    if (arg != null) {
                        builder.addPluginArgument(args[i].substring(1));
                    } else {
                        throw new IllegalArgumentException("Unrecognized option: " + args[i]);
                    }
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
                    || !(compiler.config.getArch() == Arch.thumbv7 || compiler.config.getArch() == Arch.arm64)
                    || compiler.config.getOs() != OS.ios)) {

                throw new IllegalArgumentException("Must build for iOS thumbv7/arm64 when creating IPA");
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
            printUsageAndExit(message, builder != null ? builder.getPlugins() : null);
        }

        try {
            if (createIpa) {
                compiler.createIpa(ipaArchs);
            } else {
                compiler.compile();
                if (run) {
                    LaunchParameters launchParameters = compiler.config.getTarget().createLaunchParameters();
                    if (launchParameters instanceof IOSSimulatorLaunchParameters) {
                        IOSSimulatorLaunchParameters simParams = (IOSSimulatorLaunchParameters) launchParameters;
                        String deviceName = null;
                        String sdkVersion = null;
                        if (compiler.config.getIosDeviceType() != null) {
                            String[] parts = compiler.config.getIosDeviceType().split("[:;, ]+");
                            deviceName = parts[0].trim();
                            sdkVersion = parts.length > 1 ? parts[1].trim() : null;
                        }
                        DeviceType type = DeviceType.getBestDeviceType(
                                compiler.config.getArch(), null, deviceName, sdkVersion);
                        simParams.setDeviceType(type);
                    }
                    launchParameters.setArguments(runArgs);
                    compiler.launch(launchParameters);
                } else {
                    compiler.config.getTarget().install();
                }
            }
        } catch (Throwable t) {
            String message = t.getMessage();
            if (verbose && !(t instanceof ExecuteException)) {
                t.printStackTrace();
            }
            printUsageAndExit(message, builder.getPlugins());
        }
    }

    /**
     * Creates an IPA with a single {@link Arch} as specified in
     * {@link Config#getArch()}.
     */
    public void createIpa() throws IOException {
        createIpa(new ArrayList<Arch>());
    }

    /**
     * Creates an IPA with a fat binary containing one slice for each of the
     * specified {@link Arch}s.
     */
    public void createIpa(List<Arch> archs) throws IOException {
        if (archs.isEmpty()) {
            archs.add(this.config.getArch());
        }
        List<File> slices = new ArrayList<>();
        for (Arch arch : archs) {
            this.config.getLogger().info("Creating %s slice for IPA", arch);
            Config sliceConfig = this.config.builder()
                    .arch(arch)
                    .tmpDir(new File(this.config.getTmpDir(), arch.toString()))
                    .build();
            new AppCompiler(sliceConfig).compile();
            slices.add(new File(sliceConfig.getTmpDir(), sliceConfig.getExecutableName()));
            for (Path path : sliceConfig.getResourcesPaths()) {
                if (!this.config.getResourcesPaths().contains(path)) {
                    this.config.addResourcesPath(path);
                }
            }
        }
        ((IOSTarget) this.config.getTarget()).createIpa(slices);
    }

    public int launch(LaunchParameters launchParameters) throws Throwable {
        return launch(launchParameters, null);
    }

    public int launch(LaunchParameters launchParameters, InputStream inputStream) throws Throwable {
        try {
            return launchAsync(launchParameters, inputStream).waitFor();
        } finally {
            launchAsyncCleanup();
        }
    }

    public Process launchAsync(LaunchParameters launchParameters) throws Throwable {
        return launchAsync(launchParameters, null);
    }

    public Process launchAsync(LaunchParameters launchParameters, InputStream inputStream) throws Throwable {
        for (LaunchPlugin plugin : config.getLaunchPlugins()) {
            plugin.beforeLaunch(config, launchParameters);
        }
        try {
            Process process = config.getTarget().launch(launchParameters);
            for (LaunchPlugin plugin : config.getLaunchPlugins()) {
                plugin.afterLaunch(config, launchParameters, process);
            }
            return process;
        } catch (Throwable e) {
            for (LaunchPlugin plugin : config.getLaunchPlugins()) {
                plugin.launchFailed(config, launchParameters);
            }
            throw e;
        }
    }

    public void launchAsyncCleanup() {
        for (LaunchPlugin plugin : config.getLaunchPlugins()) {
            plugin.cleanup();
        }
    }

    private static void printDeviceTypesAndExit() throws IOException {
        List<DeviceType> types = DeviceType.listDeviceTypes();
        for (DeviceType type : types) {
            System.out.println(type.getSimpleDeviceTypeId());
        }
        System.exit(0);
    }

    private static void printVersionAndExit() {
        System.err.println(Version.getVersion());
        System.exit(0);
    }

    private static void printUsageAndExit(String errorMessage, List<Plugin> plugins) {
        if (errorMessage != null) {
            System.err.format("robovm: %s\n", errorMessage);
        }
        // @formatter:off 
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
                         + "                        Default is <wd>/<executableName>. Ignored if -run is specified.");
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
                         + "                        are 'auto', 'x86', 'x86_64', 'thumbv7', 'arm64'. Default is\n" 
                         + "                        'auto' which means use the LLVM default.");
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
        System.err.println("  -threads <n>          The number of threads to use during class compilation. By\n" 
                         + "                        default the number returned by Runtime.availableProcessors()\n" 
                         + "                        will be used (" + Runtime.getRuntime().availableProcessors() + " on this host).");
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
        System.err.println("  -frameworkpaths <list>\n" 
                         + "                        : separated list of framework search paths used when searching\n" 
                         + "                        for custom frameworks.");
        System.err.println("  -resources <list>     : separated list of files and directories that should be\n"
                         + "                        copied to the install dir. Accepts Ant-style patterns.\n" 
                         + "                        If a pattern is specified the longest non-pattern path before\n" 
                         + "                        the first wildcard will be used as base directory and will\n" 
                         + "                        not be recreated in the install dir.");
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
        System.err.println("  -ipaarchs             (iOS) : separated list of architectures to include in the IPA.\n" 
                         + "                        Either thumbv7 or arm64 or both.");
        System.err.println("  -plist <file>         (iOS) Info.plist file to be used by the app. If not specified\n"
                         + "                        a simple Info.plist will be generated with a CFBundleIdentifier\n" 
                         + "                        based on the main class name or executable file name.");
        System.err.println("  -entitlements <file>  (iOS) Property list (.plist) file containing entitlements\n" 
                         + "                        passed to codesign when signing the app.");
        System.err.println("  -resourcerules <file> (iOS) Property list (.plist) file containing resource rules\n" 
                         + "                        passed to codesign when signing the app.");
        System.err.println("  -signidentity <id>    (iOS) Sign using this identity. Default is to look for an\n" 
                         + "                        identity starting with 'iPhone Developer' or 'iOS Development'.\n" 
                         + "                        Enclose in '/' to search by regexp, e.g. '/foo|bar/'");
        System.err.println("  -skipsign             (iOS) Skips signing of the compiled Application. Can be used\n"
                         + "                        to create unsigned packages for testing on a jailbroken device.");
        System.err.println("  -provisioningprofile <file>\n" 
                         + "                        (iOS) Provisioning profile to use when building for a device.\n" 
                         + "                        Either a UUID, an app name or app id prefix. If not specified\n" 
                         + "                        a provisioning profile matching the signing identity and bundle\n" 
                         + "                        id from the Info.plist file will be used.");
        System.err.println("  -sdk <version>        (iOS) Version number of the iOS SDK to build against. If not\n" 
                         + "                        specified the latest SDK that can be found will be used.");
        System.err.println("iOS simulator launch options:");
        System.err.println("  -printdevicetypes     The device type ids that can be used to launch a specific\n"
                         + "                        simulator via the -devicetype flag.");
        System.err.println("  -devicetype <type>    The device type to use to launch the simulator e.g. \"iPhone-6, 8.0\"\n"
                         + "                        (defaults to an iPhone simulator using the latest SDK).");
        
        if(plugins != null) {
            for(Plugin plugin: plugins) {
                if(plugin.getArguments().getArguments().size() > 0) {
                    System.err.println(plugin.getClass().getSimpleName() + " options:");
                    for(PluginArgument arg: plugin.getArguments().getArguments()) {
                        String argString = "  -" + plugin.getArguments().getPrefix() + ":" + arg.getName() + (arg.hasValue()? "=" + arg.getValueName(): "");
                        int whitespace = Math.max(1, 24 - argString.length());
                        System.err.println(argString + repeat(" ", whitespace) + arg.getDescription());
                    }
                }
            }
        }
        System.exit(errorMessage != null ? 1 : 0);
        // @formatter:on
    }

    private static String repeat(String s, int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(s);
        }
        return builder.toString();
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
     * Performs an update check. If a newer version of RoboVM is available a
     * message will be printed to the log. The update check is also used to
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
                String version = (String) result.get("version");
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
                return (JSONObject) JSONValue.parseWithException(IOUtils.toString(in, "UTF-8"));
            }
        } catch (Exception e) {
            if (config.getHome().isDev()) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
