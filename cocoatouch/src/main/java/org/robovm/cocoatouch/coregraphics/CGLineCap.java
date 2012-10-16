/**
 * 
 */
package org.robovm.cocoatouch.coregraphics;

import org.robovm.rt.bro.IntValuedEnum;

/**
 * @author niklas
 *
 */
public enum CGLineCap implements IntValuedEnum {
    Butt(0),
    Round(1),
    Square(2);
    
    private final int n;

    private CGLineCap(int n) { this.n = n; }
    public int value() { return n; }
    
}
