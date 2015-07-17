package org.robovm.rt.lambdas.test003;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

interface I {
    int add();
}

class Locals { 
    public int add(int val) {
        int i = val;
        I l = () -> i;
        return l.add();
    }
}

class Fields {
    int field;
    
    public Fields(int value) {
        this.field = value;
    }
    
    public int add() {
        I i = () -> field;
        return i.add();
    }
}

class LocalsAndFields {
    int field;

    public LocalsAndFields(int value) {
        this.field = value;        
    }

    public int add(int a) {
        I i = () -> a + field;
        return i.add();
    }    
}

public class Test003_CaptureFieldsLocals {

    @Test
    public void test003() {
        for(int i = 0; i < 10; i++) {
            Locals l = new Locals();
            assertEquals(i, l.add(i));
        }
        
        for(int i = 0; i < 10; i++) {
            Fields f = new Fields(i);
            assertEquals(i, f.add());
        }
    
        for(int i = 0; i < 10; i++) {
            LocalsAndFields laf = new LocalsAndFields(i);
            assertEquals(i+i, laf.add(i));
        }
    }
}
