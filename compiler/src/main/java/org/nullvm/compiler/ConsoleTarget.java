/**
 * 
 */
package org.nullvm.compiler;


/**
 * @author niklas
 *
 */
public class ConsoleTarget extends AbstractTarget {

    ConsoleTarget() {
    }
    
    public static class Builder implements Target.Builder {
        private ConsoleTarget target = new ConsoleTarget();

        public void setup(Config.Builder configBuilder) {
        }
        
        public Target build(Config config) {
            target.config = config;
            return target.build(config);
        }
        
    }
}
