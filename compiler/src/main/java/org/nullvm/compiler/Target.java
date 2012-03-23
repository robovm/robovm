package org.nullvm.compiler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.nullvm.compiler.clazz.Path;

public interface Target {

    String getInstallRelativeArchivePath(Path path);

    boolean canLaunchInPlace();

    void build(List<File> objectFiles, Map<String, String> aliases) throws IOException;

    void install() throws IOException;

    int launch(List<String> runArgs) throws IOException;

    Map<String, String> modifyEnv(Map<String, String> defaults);

    String[] generateCommandLine(List<String> runArgs);

    public interface Builder {
        void setup(Config.Builder configBuilder);
        Target build(Config config);
    }
}