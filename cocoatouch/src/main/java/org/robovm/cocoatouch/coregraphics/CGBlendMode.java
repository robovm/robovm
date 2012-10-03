/**
 * 
 */
package org.robovm.cocoatouch.coregraphics;

/**
 * @author niklas
 *
 */
public enum CGBlendMode {

    Normal(0),
    Multiply(1),
    Screen(2),
    Overlay(3),
    Darken(4),
    Lighten(5),
    ColorDodge(6),
    ColorBurn(7),
    SoftLight(8),
    HardLight(9),
    Difference(10),
    Exclusion(11),
    Hue(12),
    Saturation(13),
    Color(14),
    Luminosity(15),
    Clear(16),
    Copy(17),
    SourceIn(18),
    SourceOut(19),
    SourceAtop(20),
    DestinationOver(21),
    DestinationIn(22),
    DestinationOut(23),
    DestinationAtop(24),
    XOR(25),
    PlusDarker(26),
    PlusLighter(27);
    
    private final int n;

    private CGBlendMode(int n) { this.n = n; }
    public int value() { return n; }
}
