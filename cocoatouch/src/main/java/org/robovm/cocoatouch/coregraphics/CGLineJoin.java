/**
 * 
 */
package org.robovm.cocoatouch.coregraphics;

/**
 * @author niklas
 *
 */
public enum CGLineJoin {
    Miter(0),
    Round(1),
    Bevel(2);

    private final int n;

    private CGLineJoin(int n) { this.n = n; }
    public int value() { return n; }
}
