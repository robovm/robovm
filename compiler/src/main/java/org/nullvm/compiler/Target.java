package org.nullvm.compiler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.nullvm.compiler.clazz.Path;

public interface Target {

    String getInstallRelativeArchivePath(Path path);

    boolean canLaunchInPlace();

    void build(List<File> objectFiles) throws IOException;

    void install() throws IOException;

    Process launch(LaunchParameters launchParameters) throws IOException;

    public interface Builder {
        void setup(Config.Builder configBuilder);
        Target build(Config config);
    }
}