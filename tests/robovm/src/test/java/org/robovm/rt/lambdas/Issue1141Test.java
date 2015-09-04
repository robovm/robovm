package org.robovm.rt.lambdas;

import static org.junit.Assert.*;

import org.junit.Test;

public class Issue1141Test {
    interface Function<T> {
        public T func(T a);
    }
    
    interface BinaryFunction<T> {
        public T sum(T a, T b);
    }
    
    private static long method(Long identity, Function<Long> func, BinaryFunction<Long> func2) {
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
