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

import org.robovm.llvm.binding.FPOpFusionMode;
import org.robovm.llvm.binding.FloatABIType;
import org.robovm.llvm.binding.LLVM;
import org.robovm.llvm.binding.TargetOptionsRef;

/**
 * 
 */
public class TargetOptions {
    protected TargetOptionsRef ref;

    TargetOptions(TargetOptionsRef ref) {
        this.ref = ref;
    }

    public boolean getPrintMachineCode() {
        return LLVM.TargetOptionsGetPrintMachineCode(ref);
    }

    public void setPrintMachineCode(boolean value) {
        LLVM.TargetOptionsSetPrintMachineCode(ref, value);
    }

    public boolean getNoFramePointerElim() {
        return LLVM.TargetOptionsGetNoFramePointerElim(ref);
    }

    public void setNoFramePointerElim(boolean value) {
        LLVM.TargetOptionsSetNoFramePointerElim(ref, value);
    }

    public boolean getNoFramePointerElimNonLeaf() {
        return LLVM.TargetOptionsGetNoFramePointerElimNonLeaf(ref);
    }

    public void setNoFramePointerElimNonLeaf(boolean value) {
        LLVM.TargetOptionsSetNoFramePointerElimNonLeaf(ref, value);
    }

    public boolean getLessPreciseFPMADOption() {
        return LLVM.TargetOptionsGetLessPreciseFPMADOption(ref);
    }

    public void setLessPreciseFPMADOption(boolean value) {
        LLVM.TargetOptionsSetLessPreciseFPMADOption(ref, value);
    }

    public boolean getUnsafeFPMath() {
        return LLVM.TargetOptionsGetUnsafeFPMath(ref);
    }

    public void setUnsafeFPMath(boolean value) {
        LLVM.TargetOptionsSetUnsafeFPMath(ref, value);
    }

    public boolean getNoInfsFPMath() {
        return LLVM.TargetOptionsGetNoInfsFPMath(ref);
    }

    public void setNoInfsFPMath(boolean value) {
        LLVM.TargetOptionsSetNoInfsFPMath(ref, value);
    }

    public boolean getNoNaNsFPMath() {
        return LLVM.TargetOptionsGetNoNaNsFPMath(ref);
    }

    public void setNoNaNsFPMath(boolean value) {
        LLVM.TargetOptionsSetNoNaNsFPMath(ref, value);
    }

    public boolean getHonorSignDependentRoundingFPMathOption() {
        return LLVM.TargetOptionsGetHonorSignDependentRoundingFPMathOption(ref);
    }

    public void setHonorSignDependentRoundingFPMathOption(boolean value) {
        LLVM.TargetOptionsSetHonorSignDependentRoundingFPMathOption(ref, value);
    }

    public boolean getUseSoftFloat() {
        return LLVM.TargetOptionsGetUseSoftFloat(ref);
    }

    public void setUseSoftFloat(boolean value) {
        LLVM.TargetOptionsSetUseSoftFloat(ref, value);
    }

    public boolean getNoZerosInBSS() {
        return LLVM.TargetOptionsGetNoZerosInBSS(ref);
    }

    public void setNoZerosInBSS(boolean value) {
        LLVM.TargetOptionsSetNoZerosInBSS(ref, value);
    }

    public boolean getJITEmitDebugInfo() {
        return LLVM.TargetOptionsGetJITEmitDebugInfo(ref);
    }

    public void setJITEmitDebugInfo(boolean value) {
        LLVM.TargetOptionsSetJITEmitDebugInfo(ref, value);
    }

    public boolean getJITEmitDebugInfoToDisk() {
        return LLVM.TargetOptionsGetJITEmitDebugInfoToDisk(ref);
    }

    public void setJITEmitDebugInfoToDisk(boolean value) {
        LLVM.TargetOptionsSetJITEmitDebugInfoToDisk(ref, value);
    }

    public boolean getGuaranteedTailCallOpt() {
        return LLVM.TargetOptionsGetGuaranteedTailCallOpt(ref);
    }

    public void setGuaranteedTailCallOpt(boolean value) {
        LLVM.TargetOptionsSetGuaranteedTailCallOpt(ref, value);
    }

    public boolean getDisableTailCalls() {
        return LLVM.TargetOptionsGetDisableTailCalls(ref);
    }

    public void setDisableTailCalls(boolean value) {
        LLVM.TargetOptionsSetDisableTailCalls(ref, value);
    }

    public int getStackAlignmentOverride() {
        return LLVM.TargetOptionsGetStackAlignmentOverride(ref);
    }

    public void setStackAlignmentOverride(int value) {
        LLVM.TargetOptionsSetStackAlignmentOverride(ref, value);
    }

    public boolean getRealignStack() {
        return LLVM.TargetOptionsGetRealignStack(ref);
    }

    public void setRealignStack(boolean value) {
        LLVM.TargetOptionsSetRealignStack(ref, value);
    }

    public int getSSPBufferSize() {
        return LLVM.TargetOptionsGetSSPBufferSize(ref);
    }

    public void setSSPBufferSize(int value) {
        LLVM.TargetOptionsSetSSPBufferSize(ref, value);
    }

    public boolean getEnableFastISel() {
        return LLVM.TargetOptionsGetEnableFastISel(ref);
    }

    public void setEnableFastISel(boolean value) {
        LLVM.TargetOptionsSetEnableFastISel(ref, value);
    }

    public boolean getPositionIndependentExecutable() {
        return LLVM.TargetOptionsGetPositionIndependentExecutable(ref);
    }

    public void setPositionIndependentExecutable(boolean value) {
        LLVM.TargetOptionsSetPositionIndependentExecutable(ref, value);
    }

    public boolean getEnableSegmentedStacks() {
        return LLVM.TargetOptionsGetEnableSegmentedStacks(ref);
    }

    public void setEnableSegmentedStacks(boolean value) {
        LLVM.TargetOptionsSetEnableSegmentedStacks(ref, value);
    }

    public boolean getUseInitArray() {
        return LLVM.TargetOptionsGetUseInitArray(ref);
    }

    public void setUseInitArray(boolean value) {
        LLVM.TargetOptionsSetUseInitArray(ref, value);
    }

    public FloatABIType getFloatABIType() {
        return LLVM.TargetOptionsGetFloatABIType(ref);
    }

    public void setFloatABIType(FloatABIType value) {
        LLVM.TargetOptionsSetFloatABIType(ref, value);
    }

    public FPOpFusionMode getAllowFPOpFusion() {
        return LLVM.TargetOptionsGetAllowFPOpFusion(ref);
    }

    public void setAllowFPOpFusion(FPOpFusionMode value) {
        LLVM.TargetOptionsSetAllowFPOpFusion(ref, value);
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
        TargetOptions other = (TargetOptions) obj;
        if (ref == null) {
            if (other.ref != null) {
                return false;
            }
        } else if (!ref.equals(other.ref)) {
            return false;
        }
        return true;
    }
}
