/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.apple.coretext;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CTFontTableTag/*</name>*/ implements ValuedEnum {
    /*<values>*/
    BASE(1111577413L),
    CFF(1128678944L),
    DSIG(1146308935L),
    EBDT(1161970772L),
    EBLC(1161972803L),
    EBSC(1161974595L),
    GDEF(1195656518L),
    GPOS(1196445523L),
    GSUB(1196643650L),
    JSTF(1246975046L),
    LTSH(1280594760L),
    OS2(1330851634L),
    PCLT(1346587732L),
    VDMX(1447316824L),
    VORG(1448038983L),
    Zapf(1516335206L),
    Acnt(1633906292L),
    Ankr(1634626418L),
    Avar(1635148146L),
    Bdat(1650745716L),
    Bhed(1651008868L),
    Bloc(1651273571L),
    Bsln(1651731566L),
    Cmap(1668112752L),
    Cvar(1668702578L),
    Cvt(1668707360L),
    Fdsc(1717859171L),
    Feat(1717920116L),
    Fmtx(1718449272L),
    Fpgm(1718642541L),
    Fvar(1719034226L),
    Gasp(1734439792L),
    Glyf(1735162214L),
    Gvar(1735811442L),
    Hdmx(1751412088L),
    Head(1751474532L),
    Hhea(1751672161L),
    Hmtx(1752003704L),
    Hsty(1752396921L),
    Just(1786082164L),
    Kern(1801810542L),
    Kerx(1801810552L),
    Lcar(1818452338L),
    Ltag(1819566439L),
    Loca(1819239265L),
    Maxp(1835104368L),
    Mort(1836020340L),
    Morx(1836020344L),
    Name(1851878757L),
    Opbd(1869636196L),
    Post(1886352244L),
    Prep(1886545264L),
    Prop(1886547824L),
    Sbit(1935829364L),
    Sbix(1935829368L),
    Trak(1953653099L),
    Vhea(1986553185L),
    Vmtx(1986884728L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CTFontTableTag/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CTFontTableTag/*</name>*/ valueOf(long n) {
        for (/*<name>*/CTFontTableTag/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CTFontTableTag/*</name>*/.class.getName());
    }
}
