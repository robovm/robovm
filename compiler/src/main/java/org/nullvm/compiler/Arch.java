/**
 * 
 */
package org.nullvm.compiler;

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
}
