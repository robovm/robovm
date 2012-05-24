/**
 * 
 */
package org.robovm.compiler;

import java.io.File;

/**
 * @author niklas
 *
 */
public enum Arch {
    x86("i386", "i386"), 
    armv6("armv6", "armv6"),
    armv7("armv7", "armv7"),
    thumbv6("thumbv6", "armv6"),
    thumbv7("thumbv7", "armv7");
    
    private final String llvmName;
    private final String clangName;
    
    private Arch(String llvmName, String clangName) {
        this.llvmName = llvmName;
        this.clangName = clangName;
    }
    
    public String getLlvmName() {
        return llvmName;
    }
    
    public String getClangName() {
        return clangName;
    }
    
    public boolean isArm() {
        switch (this) {
        case armv6:
        case armv7:
        case thumbv6:
        case thumbv7:
            return true;
        }
        return false;
    }
    
    public static Arch getDefaultArch(File llvmHomeDir) {
        String host = OS.getHost(llvmHomeDir);
        if (host.matches("^(x86|i\\d86).*")) {
            return Arch.x86;
        }
        if (host.matches("^armv7.*")) {
            return Arch.armv7;
        }
        if (host.matches("^arm.*")) {
            return Arch.armv6;
        }
        throw new CompilerException("Unrecognized arch in Host string: " + host);
    }    
}
