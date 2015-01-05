/*
 * Copyright (C) 2013 Trillian Mobile AB
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
        if (search.startsWith("/") && search.endsWith("/")) {
            Pattern pattern = Pattern.compile(search.substring(1, search.length() - 1));
            for (SigningIdentity id : ids) {
                if (pattern.matcher(id.name).find()) {
                    return id;
                }
            }
        } else {
            for (SigningIdentity id : ids) {
                if (id.name.startsWith(search) || id.fingerprint.equals(search.toUpperCase())) {
                    return id;
                }
            }
        }
        throw new IllegalArgumentException("No signing identity found matching '" + search + "'");
    }
    
    protected static List<SigningIdentity> parse(String securityOutput) {
        /* Output from security looks like this:
         *   [... ommitted output ...]
         *   Valid identities only
         *   1) 433D4A1CD97F77226F67959905A2840265A92D31 "iPhone Developer: Rolf Hudson (HS5OW37HQP)" (CSSMERR_TP_CERT_REVOKED)
         *   2) F8E60167BD74A2E9FC39B239E58CCD73BE1112E6 "iPhone Developer: Rolf Hudson (HS5OW37HQP)"
         *   3) AC2EC9D4D26889649DE4196FBFD54BF5924169F9 "iPhone Distribution: Acme Inc"
         *     3 valid identities found
         */
        ArrayList<SigningIdentity> ids = new ArrayList<SigningIdentity>();
        Pattern pattern = Pattern.compile("^\\d+\\)\\s+([0-9A-F]+)\\s+\"([^\"]*)\"\\s*(.*)");
        for (String line : securityOutput.split("\n")) {
            line = line.trim();
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) {
                continue;
            }
            String name = matcher.group(2);
            String fingerprint = matcher.group(1);
            String flags = matcher.group(3);
            // See cssmerr.h for possible CSSMERR_TP_CERT_* constants.
            if (flags == null || !flags.contains("CSSMERR_TP_CERT_")) {
                ids.add(new SigningIdentity(name, fingerprint));
            }
        }
        Collections.sort(ids);
        return ids;
    }

    public static List<SigningIdentity> list() {
        try {
            return parse(new Executor(Logger.NULL_LOGGER, "security")
                .args("find-identity", "-v", "-p", "codesigning").execCapture());
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
