/**
 * 
 */
package org.robovm.cocoatouch.coregraphics;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * @author niklas
 *
 */
public final class CGAffineTransform extends Struct<CGAffineTransform> {

    public CGAffineTransform() {}
    public CGAffineTransform(float a, float b, float c, float d, float tx, float ty) {
        a(a);
        b(b);
        c(c);
        d(d);
        tx(tx);
        ty(ty);
    }
    
    @StructMember(0)
    public native float a();
    @StructMember(0)
    public native CGAffineTransform a(float a);
    @StructMember(1)
    public native float b();
    @StructMember(1)
    public native CGAffineTransform b(float b);
    @StructMember(2)
    public native float c();
    @StructMember(2)
    public native CGAffineTransform c(float c);
    @StructMember(3)
    public native float d();
    @StructMember(3)
    public native CGAffineTransform d(float d);
    @StructMember(4)
    public native float tx();
    @StructMember(4)
    public native CGAffineTransform tx(float tx);
    @StructMember(5)
    public native float ty();
    @StructMember(5)
    public native CGAffineTransform ty(float ty);
    
}
