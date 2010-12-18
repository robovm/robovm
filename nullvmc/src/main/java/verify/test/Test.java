

package verify.test;


interface X { void foo (); }

class A implements X
{
  public void foo () { System.out.println ("A::foo"); }
}

class B implements X
{
  public void foo () { System.out.println ("B::foo"); }
}

class Test {

  public static void main (String[] args)
  {
    X xx;

    if (args.length > 1)
      xx = new A ();
    else
      xx = new B ();

    xx.foo ();

  }

}
