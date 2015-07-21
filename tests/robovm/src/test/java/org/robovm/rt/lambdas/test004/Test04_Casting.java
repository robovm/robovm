package org.robovm.rt.lambdas.test004;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

interface I {
    int add();
}

public class Test04_Casting {

  @Test
  public void test004() {
    assertEquals(10, ((I)()->10).add());   
  }
}
