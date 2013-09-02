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

import org.robovm.llvm.binding.LLVM;
import org.robovm.llvm.binding.PassManagerRef;

/**
 * 
 */
public class PassManager {
    protected PassManagerRef ref;

    public PassManager() {
        ref = LLVM.CreatePassManager();
        if (ref == null) {
            throw new LlvmException("Failed to create PassManager");
        }
    }

    protected final void checkDisposed() {
        if (ref == null) {
            throw new LlvmException("Already disposed");
        }
    }

    public synchronized void dispose() {
        checkDisposed();
        LLVM.DisposePassManager(ref);
        ref = null;
    }

    public void addAggressiveDCEPass() {
        checkDisposed();
        LLVM.AddAggressiveDCEPass(ref);
    }
    public void addAlwaysInlinerPass() {
        checkDisposed();
        LLVM.AddAlwaysInlinerPass(ref);
    }
    public void addArgumentPromotionPass() {
        checkDisposed();
        LLVM.AddArgumentPromotionPass(ref);
    }
    public void addBasicAliasAnalysisPass() {
        checkDisposed();
        LLVM.AddBasicAliasAnalysisPass(ref);
    }
    public void addBBVectorizePass() {
        checkDisposed();
        LLVM.AddBBVectorizePass(ref);
    }
    public void addCFGSimplificationPass() {
        checkDisposed();
        LLVM.AddCFGSimplificationPass(ref);
    }
    public void addConstantMergePass() {
        checkDisposed();
        LLVM.AddConstantMergePass(ref);
    }
    public void addConstantPropagationPass() {
        checkDisposed();
        LLVM.AddConstantPropagationPass(ref);
    }
    public void addCorrelatedValuePropagationPass() {
        checkDisposed();
        LLVM.AddCorrelatedValuePropagationPass(ref);
    }
    public void addDeadArgEliminationPass() {
        checkDisposed();
        LLVM.AddDeadArgEliminationPass(ref);
    }
    public void addDeadStoreEliminationPass() {
        checkDisposed();
        LLVM.AddDeadStoreEliminationPass(ref);
    }
    public void addDemoteMemoryToRegisterPass() {
        checkDisposed();
        LLVM.AddDemoteMemoryToRegisterPass(ref);
    }
    public void addEarlyCSEPass() {
        checkDisposed();
        LLVM.AddEarlyCSEPass(ref);
    }
    public void addFunctionAttrsPass() {
        checkDisposed();
        LLVM.AddFunctionAttrsPass(ref);
    }
    public void addFunctionInliningPass() {
        checkDisposed();
        LLVM.AddFunctionInliningPass(ref);
    }
    public void addGlobalDCEPass() {
        checkDisposed();
        LLVM.AddGlobalDCEPass(ref);
    }
    public void addGlobalOptimizerPass() {
        checkDisposed();
        LLVM.AddGlobalOptimizerPass(ref);
    }
    public void addGVNPass() {
        checkDisposed();
        LLVM.AddGVNPass(ref);
    }
    public void addIndVarSimplifyPass() {
        checkDisposed();
        LLVM.AddIndVarSimplifyPass(ref);
    }
    public void addInstructionCombiningPass() {
        checkDisposed();
        LLVM.AddInstructionCombiningPass(ref);
    }
    public void addInternalizePass(boolean allButMain) {
        checkDisposed();
        LLVM.AddInternalizePass(ref, allButMain ? 1 : 0);
    }
    public void addIPConstantPropagationPass() {
        checkDisposed();
        LLVM.AddIPConstantPropagationPass(ref);
    }
    public void addIPSCCPPass() {
        checkDisposed();
        LLVM.AddIPSCCPPass(ref);
    }
    public void addJumpThreadingPass() {
        checkDisposed();
        LLVM.AddJumpThreadingPass(ref);
    }
    public void addLICMPass() {
        checkDisposed();
        LLVM.AddLICMPass(ref);
    }
    public void addLoopDeletionPass() {
        checkDisposed();
        LLVM.AddLoopDeletionPass(ref);
    }
    public void addLoopIdiomPass() {
        checkDisposed();
        LLVM.AddLoopIdiomPass(ref);
    }
    public void addLoopRotatePass() {
        checkDisposed();
        LLVM.AddLoopRotatePass(ref);
    }
    public void addLoopUnrollPass() {
        checkDisposed();
        LLVM.AddLoopUnrollPass(ref);
    }
    public void addLoopUnswitchPass() {
        checkDisposed();
        LLVM.AddLoopUnswitchPass(ref);
    }
    public void addLoopVectorizePass() {
        checkDisposed();
        LLVM.AddLoopVectorizePass(ref);
    }
    public void addLowerExpectIntrinsicPass() {
        checkDisposed();
        LLVM.AddLowerExpectIntrinsicPass(ref);
    }
    public void addMemCpyOptPass() {
        checkDisposed();
        LLVM.AddMemCpyOptPass(ref);
    }
    public void addPromoteMemoryToRegisterPass() {
        checkDisposed();
        LLVM.AddPromoteMemoryToRegisterPass(ref);
    }
    public void addPruneEHPass() {
        checkDisposed();
        LLVM.AddPruneEHPass(ref);
    }
    public void addReassociatePass() {
        checkDisposed();
        LLVM.AddReassociatePass(ref);
    }
    public void addScalarReplAggregatesPass() {
        checkDisposed();
        LLVM.AddScalarReplAggregatesPass(ref);
    }
    public void addScalarReplAggregatesPassSSA() {
        checkDisposed();
        LLVM.AddScalarReplAggregatesPassSSA(ref);
    }
    public void addScalarReplAggregatesPassWithThreshold(int threshold) {
        checkDisposed();
        LLVM.AddScalarReplAggregatesPassWithThreshold(ref, threshold);
    }
    public void addSCCPPass() {
        checkDisposed();
        LLVM.AddSCCPPass(ref);
    }
    public void addSimplifyLibCallsPass() {
        checkDisposed();
        LLVM.AddSimplifyLibCallsPass(ref);
    }
    public void addSLPVectorizePass() {
        checkDisposed();
        LLVM.AddSLPVectorizePass(ref);
    }
    public void addStripDeadPrototypesPass() {
        checkDisposed();
        LLVM.AddStripDeadPrototypesPass(ref);
    }
    public void addStripSymbolsPass() {
        checkDisposed();
        LLVM.AddStripSymbolsPass(ref);
    }
    public void addTailCallEliminationPass() {
        checkDisposed();
        LLVM.AddTailCallEliminationPass(ref);
    }
    public void addTypeBasedAliasAnalysisPass() {
        checkDisposed();
        LLVM.AddTypeBasedAliasAnalysisPass(ref);
    }
    public void addVerifierPass() {
        checkDisposed();
        LLVM.AddVerifierPass(ref);
    }
    
    public void run(Module module) {
        checkDisposed();
        LLVM.RunPassManager(ref, module.ref);
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
        PassManager other = (PassManager) obj;
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
