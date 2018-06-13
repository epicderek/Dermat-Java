package dermatj.utils.excs;

public abstract class Exceptions 
{
	
	/**
	 * This exception indicates that an object of an identity different from a primitive
	 * array is encountered. Its main library usage appears in dermatj.utils.seq.Array. 
	 */
	public static class NonePrimitiveArrayException extends RuntimeException
	{
		/**
		 * Identification for NonePrimitiveArrayExceptions. 
		 */
		public static final long serialVersionUID = 1L;

		public NonePrimitiveArrayException()
		{
			super(); 
		}
		
		public NonePrimitiveArrayException(String mess)
		{
			super(mess); 
		}
		
		public NonePrimitiveArrayException(Exception ori)
		{
			super(ori); 
		}
	}
	
	public static class DuplicateValueException extends RuntimeException
	{
		/**
		 * The identification for DuplicateValueExceptions. 
		 */
		public static final long serialVersionUID = 2L;

		public DuplicateValueException()
		{
			super(); 
		}
		
		public DuplicateValueException(String mess)
		{
			super(mess); 
		}
		
		public DuplicateValueException(Exception ori)
		{
			super(ori); 
		}
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
