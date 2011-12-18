/**
 * 
 */
package org.nullvm.compiler;

import java.io.File;

/**
 * @author niklas
 *
 */
public enum Arch {
    i386("x86"), 
    x86_64("x86-64"), 
    arm("arm");
    
    private final String llvmName;
    
    private Arch(String llvmName) {
        this.llvmName = llvmName;
    }
    
    public String getLlvmName() {
        return llvmName;
    }
    
    public static Arch getDefaultArch(File llvmHomeDir) {
        String host = OS.getHost(llvmHomeDir);
        if (host.matches("^(x86.64|amd64).*")) {
            return Arch.x86_64;
        }
        if (host.matches("^(x86|i\\d86).*")) {
            return Arch.i386;
        }
        if (host.matches("^arm.*")) {
            return Arch.arm;
        }
        throw new CompilerException("Unrecognized arch in Host string: " + host);
    }    
}
