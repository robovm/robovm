/*
 * Copyright (C) 2013 Trillian AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler.target.ios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.robovm.compiler.log.Logger;
import org.robovm.compiler.util.Executor;

/**
 * Represents a signing identity.
 */
public class SigningIdentity implements Comparable<SigningIdentity> {

    private final String name;
    private final String fingerprint;
    
    SigningIdentity(String name, String fingerprint) {
        this.name = name;
        this.fingerprint = fingerprint;
    }
    
    public String getName() {
        return name;
    }
    
    public String getFingerprint() {
        return fingerprint;
    }
    
    @Override
    public int compareTo(SigningIdentity o) {
        return this.name.compareToIgnoreCase(o.name);
    }
    
    @Override
    public String toString() {
        return "SigningIdentity [name=" + name + ", fingerprint=" + fingerprint
                + "]";
    }

    public static SigningIdentity find(List<SigningIdentity> ids, String search) {
        for (SigningIdentity id : ids) {
            if (id.name.startsWith(search) || id.fingerprint.equals(search.toUpperCase())) {
                return id;
            }
        }
        throw new IllegalArgumentException("No signing identity found matching '" + search + "'");
    }
    
    public static List<SigningIdentity> list() {
        try {
            String out = new Executor(Logger.NULL_LOGGER, "security")
                .args("find-identity", "-v", "-p", "codesigning").execCapture();
            /* Output from security looks like this:
             *   1) 62480BA6FC7FACD7CA4100812ABAE9C86FB43DCF "iPhone Developer: Niklas Therning (NZ2HZ85PAR)"
             *   2) 069675F14EDB7A7482A7357B34A9383D84B7DFEA "iPhone Distribution: Trillian AB"
             *     2 valid identities found
             */
            ArrayList<SigningIdentity> ids = new ArrayList<SigningIdentity>();
            Pattern pattern = Pattern.compile("^\\d+\\) ([0-9A-F]+) \"(.*)\"$");
            for (String line : out.split("\n")) {
                line = line.trim();
                Matcher matcher = pattern.matcher(line);
                if (!matcher.find()) {
                    break;
                }
                ids.add(new SigningIdentity(matcher.group(2), matcher.group(1)));
            }
            Collections.sort(ids);
            return ids;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(list());
        } else {
            System.out.println(find(list(), args[0]));
        }
    }
}
