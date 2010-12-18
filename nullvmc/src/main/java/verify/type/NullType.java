

package verify.type;


public class NullType extends Type
{
  NullType (TypeContext ctx)
  {
    super (ctx, T_NULL);
  }

  protected void subclassCheckAssignmentFrom (Type other)
    throws IncompatibleTypesException
  {
    // we use this, when we store an object reference into a object
    // array, for which we do not know the element type.  

    if (other.storageClass == SC_ADDR)
      return;

    throw new InternalError ("something is declared of type"+" null!");
  }

  public String toString () { return "<null>"; }

  public Type mergeWith (Type other)
  {
    if (other == null)
      return null;

    if (other == this)
      return this;

    if (other.tag == T_CLASS || other.tag == T_ARRAY || other.tag == T_NEW)
      return other;

    return null;
  }
}
