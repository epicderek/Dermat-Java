package dermatj.utils.seq;

public abstract class Array 
{	
	/**
	 * The primitive array types allowed by java. 
	 */
	private static final Class<?>[] PMATYPES = new Class<?>[8]; 
	/**
	 * The number of dashes printed when the Array.printDash method is invoked. 
	 */
	public static int dashNum = 50; 
	
	static
	{
		//Load the primitive array classes. 
		addAll(PMATYPES,0,boolean[].class,byte[].class,char[].class,short[].class,
						  int[].class,long[].class,float[].class,double[].class);
	}
	
	/**
	 * Check whether the given object is a primitive array of any dimension. 
	 * @param obj The suspect object of being a primitive array of any dimension. 
	 * @return true if the suspect object is a primitive array of any dimension, false otherwise. 
	 */
	public static boolean isPrimitiveArray(Object obj)
	{
		return isPrimitiveArray(obj.getClass()); 
	}
	
	/**
	 * Check whether the given class information of an object is that of a primitive array of
	 * any dimension. 
	 * @param type The class object representing the type of the object to be checked. 
	 * @return true if the class represents objects of a primitive array of any dimension, false otherwise. 
	 */
	public static boolean isPrimitiveArray(Class<?> type)
	{
		for(Class<?> holder: PMATYPES)
			if(holder==type)
				return true; 
			else if(Object[].class.isAssignableFrom(type))
				if(isPrimitiveArray(type.getComponentType()))
					return true; 
		return false; 
	}
	
	/**
	 * Print the suspect primitive array if so, that is to first check whether the object is a
	 * one dimensional primitive array, then print such array if so. The result of printing, whether
	 * printed, is shown by the returned boolean value¡ªtrue for printed, false for not. 
	 * @param obj The suspect primitive array. 
	 * @return true if the object is indeed a primitive array and is printed, false otherwise. 
	 */
	private static boolean printPrimitiveArray(Object obj)
	{
		if(obj instanceof boolean[])
		{
			System.out.print("["); 
			boolean[] casted = (boolean[])obj; 
			for(int i=0; i<casted.length; i++)
				if(i!=casted.length-1)
					System.out.print(casted[i]+", ");
				else
					System.out.print(casted[i]);
			System.out.print("]");
			return true; 
		}
		if(obj instanceof byte[])
		{
			System.out.print("[");
			byte[] casted = (byte[])obj; 
			for(int i=0; i<casted.length; i++)
				if(i!=casted.length-1)
					System.out.print(casted[i]+", ");
				else
					System.out.print(casted[i]);
			System.out.print("]");
			return true; 
		}
		if(obj instanceof char[])
		{
			System.out.print("[");
			char[] casted = (char[])obj; 
			for(int i=0; i<casted.length; i++)
				if(i!=casted.length-1)
					System.out.print(casted[i]+", ");
				else
					System.out.print(casted[i]);
			System.out.print("]");
			return true; 
		}
		if(obj instanceof short[])
		{
			System.out.print("[");
			short[] casted = (short[])obj; 
			for(int i=0; i<casted.length; i++)
				if(i!=casted.length-1)
					System.out.print(casted[i]+", ");
				else
					System.out.print(casted[i]);
			return true; 
		}
		if(obj instanceof int[])
		{
			System.out.print("[");
			int[] casted = (int[])obj; 
			for(int i=0; i<casted.length; i++)
				if(i!=casted.length-1)
					System.out.print(casted[i]+", ");
				else
					System.out.print(casted[i]);
			System.out.print("]");
			return true; 
		}
		if(obj instanceof long[])
		{
			System.out.print("[");
			long[] casted = (long[])obj; 
			for(int i=0; i<casted.length; i++)
				if(i!=casted.length-1)
					System.out.print(casted[i]+", ");
				else
					System.out.print(casted[i]);
			System.out.print("]");
			return true; 
		}
		if(obj instanceof float[])
		{
			System.out.print("[");
			float[] casted = (float[])obj; 
			for(int i=0; i<casted.length; i++)
				if(i!=casted.length-1)
					System.out.print(casted[i]+", ");
				else
					System.out.print(casted[i]);
			System.out.print("]");
			return true; 
		}
		if(obj instanceof double[])
		{
			System.out.print("[");
			double[] casted = (double[])obj; 
			for(int i=0; i<casted.length; i++)
				if(i!=casted.length-1)
					System.out.print(casted[i]+", ");
				else
					System.out.print(casted[i]);
			System.out.print("]");
			return true; 
		}
		return false; 
		
	}
	
	/**
	 * Print the variable argument list in a line without wrapping it in an array. 
	 * For data structures, including arrays, it provides a deep printing in which 
	 * the base level of the container, or multidimensional array, is exposed and printed. 
	 * Primitive arrays are printed in the standard format as of object arrays. 
	 * @param args The variable argument list to be printed in a line. 
	 */
	@SafeVarargs
	public static void printline(Object... args)
	{
		for(Object holder: args)
		{
			if(holder instanceof Object[])
			{
				helpArray((Object[])holder); 
				System.out.print(" ");
			}
			else if(printPrimitiveArray(holder))
				;
			else 
				System.out.print(holder+" ");
		}
		System.out.println();
	}
	
	/**
	 * Print the given argument list of elements. Unless the case for a one dimensional array, 
	 * the list are wrapper in an array¡ªeach element is printed and separated with 
	 * a comma and a space, and the elements are surrounded by a pair of square brackets. 
	 * When encountering an array as one of the element, print the array as it would of 
	 * be printed if isolated, and treat this array as a whole as one element of the 
	 * entire input array. Arrays of any dimension, if singly included in the argument, 
	 * is not wrapped in an extra array, but printed as is. Both object and primitive arrays
	 * are accepted. 
	 * @param input The input arguments to be wrapped in an array and printed. 
	 */
	@SafeVarargs
	public static void printArray(Object... input)//Print a list in a line with space between each element.
	{
		if(input.length==0)
		{
			System.out.println();
			return;
		}
		if(input.length==1) 
		{
			if(input[0] instanceof Object[])
				helpArray((Object[])input[0]); 
			else if(printPrimitiveArray(input[0]))
				; 
			else
				System.out.print("["+input[0]+"]"); 
			System.out.println();
			return; 
		}
		System.out.print("[");
		for(int i=0; i<input.length; i++)
		{
			if(input[i] instanceof Object[])
				helpArray((Object[])input[i]); 
			else if(printPrimitiveArray(input[i])) 
				;
			else
				System.out.print(input[i]); 
			if(i==input.length-1)
				System.out.print("]");
			else
				System.out.print(", ");
		}
	}
	
	/**
	 * A helper method to printArray. This method prints any nested array, including primitive
	 * arrays.
	 * @param input The object array to be printed. 
	 */
	private static void helpArray(Object[] input)
	{
		System.out.print("[");
		if(input.length==0)
			System.out.print("]");
		for(int i=0; i<input.length; i++)
		{
			if(input[i] instanceof Object[])
				helpArray((Object[])input[i]); 
			else if(printPrimitiveArray(input[i]))
				; 
			else
				System.out.print(input[i]);
			if(i==input.length-1)
				System.out.print("]");
			else
				System.out.print(", ");
		}	
	}
	
	/**
	 * Fill the given target array starting from the given starting index with the elements 
	 * given in the variable argument list of the same type. This method will not generate an
	 * exception if the target array is filled before the filling arguments are exhausted. It 
	 * will only throw an IndexOutOfBoundsException when the filling list is exhausted before
	 * the target array is filled. 
	 * @param tar The array to be filled. 
	 * @param index The starting index of the array to be filled to begin the filling. 
	 * @param fill the arguments to fill the target array. 
	 */
	@SafeVarargs
	public static <T> void addAll(T[] tar, int index, T... fill)
	{
		for(int i=0; i<tar.length-index; i++)
			tar[i+index] = fill[i]; 
	}
	
	/**
	 * Print a dashed line of the number of segments given by the customary public Array.dashNum field. 
	 */
	public static void printDash()
	{
		printDash(dashNum);
	}
	
	/**
	 * Print a dashed line of the number of segments given by the number in the argument. 
	 * @param num The number of dashed segments to be printed in this line. 
	 */
	public static void printDash(int num)
	{
		for(int i=0; i<num; i++)
			System.out.print("-");
		System.out.println();
	}
	
	public static void main(String[] args) 
	{	
		printline(isPrimitiveArray(new Object[]{})); 
	}

}
