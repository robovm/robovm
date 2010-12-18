
package verify.type;

public class ArrayType extends Type {

  static ArrayType forName (TypeContext ctx, String name,
			    int start, int[] end)
    throws IllegalTypeNameException
  {
    if (name.charAt (start) == '[')
      {
	int array = 1;
	while (name.charAt (start+array) == '[') 
	  array++;
	Type type = ctx.typeForName (name, start+array, end);
	while (array-- > 1)
	  type = ArrayType.forElement (type);
	return ArrayType.forElement (type);
      }
    throw new IllegalTypeNameException (name);
  }

  public static ArrayType forElement (Type element)
  {
    if (element == null)
      throw new IllegalArgumentException ("ArrayType.forElement(null)");

    if (element.arrayType == null)
      element.arrayType = new ArrayType (element);

    return element.arrayType;
  }

  public final Type elementType;

  private ArrayType (Type element)
  {
    super (element.context, T_ARRAY);
    elementType = element;
  }

  protected void subclassCheckAssignmentFrom (Type type)
    throws verify.VerificationException
  {
    /* only arrays can be assigned to other arrays */
    if (type.tag == T_ARRAY)
      {
	ArrayType other = (ArrayType)type;

	if (other.elementType == elementType)
	  {
	    return;
	  }
	else if (elementType.storageClass == T_ADDR)
	  {
	    elementType.checkAssignmentFrom (other.elementType);
	    return;
	  }
      }

    throw new IncompatibleTypesException (type, this);
  }

  public String toString ()
  {
    return elementType.toString () + "[]";
  }

  public Type mergeWith (Type other)
    throws verify.VerificationException
  {
    if (other == null)
      return null;
    
    if (this == other)
      return this;
    
    if (other.tag == T_ARRAY)
      {
	ArrayType oa = (ArrayType)other;

	Type merged = elementType.mergeWith (oa.elementType);

	if (merged == null)
	  return context.OBJECT;

	return ArrayType.forElement (merged);
      }

    if (other.storageClass != SC_ADDR)
      return null;
    
    if (other.tag == T_CLASS)
      return context.OBJECT;
    
    if (other.tag == T_NULL)
      return this;
    
    return null;
  }
}





