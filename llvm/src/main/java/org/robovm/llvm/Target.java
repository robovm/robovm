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
package org.robovm.llvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.robovm.llvm.binding.CodeGenOptLevel;
import org.robovm.llvm.binding.CodeModel;
import org.robovm.llvm.binding.LLVM;
import org.robovm.llvm.binding.RelocMode;
import org.robovm.llvm.binding.StringOut;
import org.robovm.llvm.binding.TargetMachineRef;
import org.robovm.llvm.binding.TargetRef;

/**
 * 
 */
public class Target {
    private static final String HOST_TRIPLE = LLVM.getLlvmHostTriple();
    
    protected TargetRef ref;
    private String name;
    private String description;

    Target(TargetRef ref) {
        this.ref = ref;
        this.name = LLVM.GetTargetName(ref);
        this.description = LLVM.GetTargetDescription(ref);
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean hasJIT() {
        return LLVM.TargetHasJIT(ref);
    }

    public boolean hasTargetMachine() {
        return LLVM.TargetHasTargetMachine(ref);
    }

    public boolean hasAsmBackend() {
        return LLVM.TargetHasAsmBackend(ref);
    }

    public TargetMachine createTargetMachine(String triple) {
        return createTargetMachine(triple, null, null, null, null, null);
    }
    
    public TargetMachine createTargetMachine(String triple, String cpu, String features, 
            CodeGenOptLevel optLevel, RelocMode relocMode, CodeModel codeModel) {
        
        if (triple == null) {
            throw new NullPointerException("triple");
        }
        
        cpu = cpu == null ? "" : cpu;
        features = features == null ? "" : features;
        optLevel = optLevel == null ? CodeGenOptLevel.CodeGenLevelDefault : optLevel;
        relocMode = relocMode == null ? RelocMode.RelocDefault : relocMode;
        codeModel = codeModel == null ? CodeModel.CodeModelDefault : codeModel;
        
        TargetMachineRef machineRef = LLVM.CreateTargetMachine(ref, triple, cpu, features, optLevel, relocMode, codeModel);
        if (machineRef == null) {
            throw new LlvmException("Failed to create TargetMachine for triple '" + triple + "'");
        }
        return new TargetMachine(machineRef);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ref == null) ? 0 : ref.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Target other = (Target) obj;
        if (ref == null) {
            if (other.ref != null) {
                return false;
            }
        } else if (!ref.equals(other.ref)) {
            return false;
        }
        return true;
    }

    public static String getHostTriple() {
        return HOST_TRIPLE;
    }
    
    public static Target getHostTarget() {
        return lookupTarget(HOST_TRIPLE);
    }
    
    public static Target getTarget(String name) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        TargetRef ref = LLVM.GetFirstTarget();
        while (ref != null) {
            if (name.equals(LLVM.GetTargetName(ref))) {
                return new Target(ref);
            }
            ref = LLVM.GetNextTarget(ref);
        }
        throw new LlvmException("No target with name '" + name + "' found");
    }

    public static Target lookupTarget(String triple) {
        if (triple == null) {
            throw new NullPointerException("triple");
        }
        StringOut ErrorMessage = new StringOut();
        TargetRef ref = LLVM.LookupTarget(triple, ErrorMessage);
        if (ref == null) {
            throw new LlvmException(ErrorMessage.getValue().trim());
        }
        return new Target(ref);
    }
    
    public static List<Target> getTargets() {
        List<Target> result = new ArrayList<Target>();
        TargetRef ref = LLVM.GetFirstTarget();
        while (ref != null) {
            result.add(new Target(ref));
            ref = LLVM.GetNextTarget(ref);
        }
        return result;
    }
    
    public static Map<String, Target> getTargetsMap() {
        Map<String, Target> result = new TreeMap<String, Target>();
        for (Target target : getTargets()) {
            result.put(target.name, target);
        }
        return result;
    }
}
