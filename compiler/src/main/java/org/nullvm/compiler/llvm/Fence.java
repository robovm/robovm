/**
 * 
 */
package org.nullvm.compiler.llvm;

/**
 * @author niklas
 *
 */
public class Fence extends Instruction {
    private final Ordering ordering;

    public Fence(Ordering ordering) {
        this.ordering = ordering;
    }
    
    @Override
    public String toString() {
        return "fence " + ordering;
    }

}
