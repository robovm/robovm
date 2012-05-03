/**
 * 
 */
package org.nullvm.compiler.llvm;

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
