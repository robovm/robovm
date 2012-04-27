/**
 * 
 */
package org.nullvm.compiler.llvm;

/**
 * @author niklas
 *
 */
public enum Ordering {
    unordered, monotonic, acquire, release, acq_rel, seq_cst
}
