/**
 * 
 */
package org.nullvm.compiler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.nullvm.compiler.clazz.Path;

/**
 * @author niklas
 *
 */
public interface App {

    void install() throws IOException;
    int launch(List<String> runArgs) throws IOException;
    String[] generateCommandLine(List<String> runArgs);
    Map<String, String> modifyEnv(Map<String, String> defaults);
    
    boolean canLaunchInPlace();
    String getInstallRelativeArchivePath(Path path);
    
    public interface Builder {
        void setup(Config.Builder configBuilder);
        App build(Config config);
    }
}
