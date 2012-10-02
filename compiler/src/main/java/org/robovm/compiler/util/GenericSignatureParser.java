/*
 * Bytecode Analysis Framework
 * Copyright (C) 2004, University of Maryland
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.robovm.compiler.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple class to parse method signatures that include generic information.
 * <p>
 * 
 * Modified from edu.umd.cs.findbugs.ba.SignatureParser
 * <p>
 * RoboVM note: Copied and slightly modified from findbugs
 * 
 * @author Nat Ayewah
 */
public class GenericSignatureParser {
    private class ParameterSignatureIterator implements Iterator<String> {
        private int index = 1;

        public boolean hasNext() {
            return index < signature.length() && signature.charAt(index) != ')' && signature.charAt(index) != '^';
        }

        public String next() {
            if (!hasNext())
                throw new NoSuchElementException();
            StringBuilder result = new StringBuilder();
            boolean done;
            do {
                done = true;
                char ch = signature.charAt(index);
                switch (ch) {
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'I':
                case 'J':
                case 'S':
                case 'Z':
                case '*': // wildcard
                    result.append(signature.charAt(index));
                    ++index;
                    break;

                case 'L':
                case 'T':
                    String tmp = "";
                    int startsemi = index;
                    int leftCount = 0;
                    int i = startsemi + 1;
                    loop: while (true) {
                        char c = signature.charAt(i);
                        switch (c) {
                        case ';':
                            if (leftCount == 0)
                                break loop;
                            break;
                        case '<':
                            leftCount++;
                            break;
                        case '>':
                            leftCount--;
                            break;
                        }
                        i++;

                    }
                    String foo = signature.substring(startsemi, i + 1);
                    result.append(foo);
                    index = i + 1;
                    break;

                case '[':
                case '+':
                case '-':
                    result.append(signature.charAt(index));
                    ++index;
                    done = false;
                    break;

                case ')':
                case '^':
                    throw new NoSuchElementException("Should have already thrown NoSuchElementException");
                case 'V':
                default:
                    throw new IllegalStateException("Invalid method signature: '" + signature + "' : "
                            + signature.substring(index) + " " + result);
                }
            } while (!done);
            return result.toString();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private final String signature;

    /**
     * Parses a generic method signature of the form:
     * <code>(argument_signature)return_type_signature</code>
     * 
     * @param signature
     *            the method signature to be parsed
     */
    public GenericSignatureParser(String signature) {
        // XXX not currently handling Type parameters for class, interface or
        // method definitions
        int s = signature.indexOf('(');
        String sig = signature;
        if (s > 0)
            sig = sig.substring(s);
        else if (s < 0 || sig.indexOf(':') >= 0 || sig.startsWith("(V)"))
            throw new IllegalArgumentException("Bad method signature: " + signature);
        this.signature = sig;
    }

    /**
     * Get an Iterator over signatures of the method parameters.
     * 
     * @return Iterator which returns the parameter type signatures in order
     */
    public Iterator<String> parameterSignatureIterator() {
        return new ParameterSignatureIterator();
    }

    public String getParameterSignature(int index) {
        Iterator<String> it = parameterSignatureIterator();
        while (index > 0) {
            it.next();
            index--;
        }
        return it.next();
    }
    
    /**
     * Get the method return type signature.
     * 
     * @return the method return type signature
     */
    public String getReturnTypeSignature() {
        int endOfParams = signature.lastIndexOf(')');
        if (endOfParams < 0)
            throw new IllegalArgumentException("Bad method signature: " + signature);
        return signature.substring(endOfParams + 1);
    }

    /**
     * Get the number of parameters in the signature.
     * 
     * @return the number of parameters
     */
    public int getNumParameters() {
        int count = 0;
        for (Iterator<String> i = parameterSignatureIterator(); i.hasNext();) {
            i.next();
            ++count;
        }
        return count;
    }
}
