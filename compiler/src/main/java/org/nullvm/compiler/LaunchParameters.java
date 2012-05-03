/**
 * 
 */
package org.nullvm.compiler;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author niklas
 *
 */
public class LaunchParameters {
    private List<String> arguments = Collections.emptyList();
    private Map<String, String> environment = null;
    private File workingDirectory = new File(".");
    private boolean redirectStreamsToLogger = false;
    private File stdoutFifo = null;
    private File stderrFifo = null;
    
    public List<String> getArguments() {
        return arguments;
    }
    
    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }
    
    public Map<String, String> getEnvironment() {
        return environment;
    }
    
    public void setEnvironment(Map<String, String> environment) {
        this.environment = environment;
    }
    
    public File getWorkingDirectory() {
        return workingDirectory;
    }
    
    public void setWorkingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }
    
    public boolean isRedirectStreamsToLogger() {
        return redirectStreamsToLogger;
    }
    
    public void setRedirectStreamsToLogger(boolean redirectStreamsToLogger) {
        this.redirectStreamsToLogger = redirectStreamsToLogger;
    }
    
    public File getStdoutFifo() {
        return stdoutFifo;
    }
    
    public void setStdoutFifo(File stdoutFifo) {
        this.stdoutFifo = stdoutFifo;
    }
    
    public File getStderrFifo() {
        return stderrFifo;
    }
    
    public void setStderrFifo(File stderrFifo) {
        this.stderrFifo = stderrFifo;
    }
}
