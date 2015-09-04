package org.robovm.rt.lambdas;

import static org.junit.Assert.*;

import org.junit.Test;

public class Issue1141Test {
    interface LongFunction {
        public Long func(Long a);
    }
    
    interface BinaryLongFunction {
        public long sum(Long a, Long b);
    }
    
    private static long method(Long identity, LongFunction func, BinaryLongFunction func2) {
        return identity + func.func(1L) + func2.sum(3L,  4L);
    }
    
    public static long sum(long a, long b) {
        return a+ b;
    }
    
    @Test
    public void testIssue1141() {
        assertEquals(0 + 1 + 3 + 4, method(0L, (e) -> 1L, Issue1141Test::sum));
    }
}
