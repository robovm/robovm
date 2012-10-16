/**
 * 
 */
package org.robovm.cocoatouch.coregraphics;

import org.robovm.rt.bro.IntValuedEnum;

/**
 * @author niklas
 *
 */
public enum CGLineJoin implements IntValuedEnum {
    Miter(0),
    Round(1),
    Bevel(2);

    private final int n;

    private CGLineJoin(int n) { this.n = n; }
    public int value() { return n; }
}
