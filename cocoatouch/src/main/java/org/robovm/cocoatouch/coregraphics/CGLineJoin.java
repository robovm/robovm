/**
 * 
 */
package org.robovm.cocoatouch.coregraphics;

import org.robovm.rt.bro.ValuedEnum;

/**
 * @author niklas
 *
 */
public enum CGLineJoin implements ValuedEnum {
    Miter(0),
    Round(1),
    Bevel(2);

    private final int n;

    private CGLineJoin(int n) { this.n = n; }
    public int value() { return n; }
}
