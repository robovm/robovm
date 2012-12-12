/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.compiler.llvm;

/**
 * @author niklas
 *
 */
public class Landingpad extends Instruction {
    private final Variable result;
    private final Constant personalityFn;
    private final boolean cleanup;
    private final Clause[] clauses;
    
    public Landingpad(Variable result, Constant personalityFn, Clause ... clauses) {
        this(result, personalityFn, false, clauses);
    }
    
    public Landingpad(Variable result, Constant personalityFn,
            boolean cleanup, Clause ... clauses) {
        
        this.result = result;
        this.personalityFn = personalityFn;
        this.cleanup = cleanup;
        this.clauses = new Clause[clauses.length];
        System.arraycopy(clauses, 0, this.clauses, 0, clauses.length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(result);
        sb.append(" = landingpad ");
        sb.append(result.getType());
        sb.append(" personality ");
        sb.append(personalityFn.getType());
        sb.append(' ');
        sb.append(personalityFn);
        if (cleanup) {
            sb.append(" cleanup");
        }
        for (Clause clause : clauses) {
            sb.append(' ');
            sb.append(clause);
        }
        return sb.toString();
    }
    
    public interface Clause {}
    
    public static class Catch implements Clause {
        private final Value value;
        public Catch(Value value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return "catch " + value.getType() + " " + value;
        }
    }
    
    public static class Filter implements Clause {
        private final ArrayConstant value;
        public Filter(ArrayConstant value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return "filter " + value.getType() + " " + value;
        }
    }
}
