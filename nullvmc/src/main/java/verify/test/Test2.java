

package verify.test;

class Test2 {

  Throwable foo ()
  throws Throwable
    {
      try 
	{
	  System.out.println ("try");
	}
      catch (Throwable x)
	{
	  System.out.println ("caught an exception!");
	  return x;
	}
      finally
	{
	  try 
	    {
	      System.out.println ("try again");
	    }
	  catch (Throwable y)
	    {
	      System.out.println ("here is more to do");
	      throw y;
	    }
	  finally
	    {
	      System.out.println ("finally once again");
	    }
	  System.out.println ("finally, a last time");
	  return null;
	}

    }
}
