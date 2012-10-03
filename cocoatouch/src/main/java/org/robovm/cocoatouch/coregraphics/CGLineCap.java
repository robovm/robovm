/**
 * 
 */
package org.robovm.cocoatouch.coregraphics;

/**
 * @author niklas
 *
 */
public enum CGLineCap {
    Butt(0),
    Round(1),
    Square(2);
    
    private final int n;

    private CGLineCap(int n) { this.n = n; }
    public int value() { return n; }
    
}
