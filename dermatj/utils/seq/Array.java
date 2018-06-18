package dermatj.utils.seq;

import static dermatj.utils.excs.Exceptions.*; 
import dermatj.utils.seq.container.map.Maps; 
import dermatj.utils.seq.container.map.DuoMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map; 
import java.util.Iterator; 


public abstract class Array 
{	
	/**
	 * The associated primitive types with their wrapper types. 
	 */
	public static final DuoMap<Class<?>,Class<?>> PMTYPES = new DuoMap<Class<?>,Class<?>>(); 
	/**
	 * The associated primitive types with their one dimensional primitive array counterparts. 
	 */
	public static final DuoMap<Class<?>,Class<?>> PMATYPES = new DuoMap<Class<?>,Class<?>>(); 
	
	/**
	 * The number of dashes printed when the Array.printDash method is invoked. 
	 */
	public static int dashNum = 50; 
	
	static
	{
		//Load the primitive classes and their wrapper counterparts. 
		Maps.putAll(PMTYPES,
				new Class<?>[]
		{boolean.class,byte.class,char.class,short.class,int.class,long.class,float.class,double.class},
				new Class<?>[]
		{Boolean.class,Byte.class,Character.class,Short.class,Integer.class,Long.class,Float.class,Double.class});
		//Load the primitive classes and their 1D primitive array counterparts. 
		Maps.putAll(PMATYPES,
				new Class<?>[] 
		{boolean.class,byte.class,char.class,short.class,int.class,long.class,float.class,double.class},
		 		new Class<?>[]
		{boolean[].class,byte[].class,char[].class,short[].class,int[].class,long[].class,float[].class,double[].class}); 
		
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
		if(PMATYPES.containsValue(type))
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
	 * Print potentially nested Collection with Collections, Maps, and Arrays in the standard format
	 * as for arrays. 
	 * @param col The collection to be printed. 
	 */
	private static <T> void printCollection(Collection<T> col)
	{
		Iterator<T> ite = col.iterator(); 
		System.out.print("[");
		for(int i=0; i<col.size(); i++)
		{
			//Get the next item. 
			T item = ite.next(); 
			//If an array is present. 
			if(item.getClass().getComponentType()!=null)
				helpArray((Object[])item); 
			//If a collection is nested. 
			else if(item instanceof Collection)
				printCollection((Collection<?>)item); 
			//If a map is present. 
			else if(item instanceof Map)
				printMap((Map<?,?>)item);
			else
				System.out.print(item); 
			if(i==col.size()-1)
				System.out.print("]"); 
			else
				System.out.print(", ");
		}
	}
	
	/**
	 * Print the potentially nested Map with Maps, Collections, and Arrays in its entries in the standard
	 * format as for a Map.  
	 * @param map The map to be printed. 
	 */
	private static <K,V> void printMap(Map<K,V> map)
	{
		System.out.print("{"); 
		Iterator<Map.Entry<K,V>> ite = map.entrySet().iterator(); 
		for(int i=0; i<map.size(); i++)
		{
			//Get the next entry. 
			Map.Entry<K,V> ent = ite.next(); 
			//If a map is nested as the key of the entry. 
			if(ent.getKey() instanceof Map)
				printMap((Map<?,?>)ent.getKey()); 
			//If an array is present. 
			else if(ent.getKey().getClass().getComponentType()!=null)
				helpArray((Object[])ent.getKey()); 
			//If a collection is present. 
			else if(ent.getKey() instanceof Collection)
				printCollection((Collection<?>)ent.getKey()); 
			else
				System.out.print(ent.getKey());
			System.out.print("=");
			//If a map is nested as the value of the entry. 
			if(ent.getValue() instanceof Map)
				printMap((Map<?,?>)ent.getValue()); 
			//If an array is present. 
			else if(ent.getValue().getClass().getComponentType()!=null)
				helpArray((Object[])ent.getValue()); 
			//If a collection is present. 
			else if(ent.getValue() instanceof Collection)
				printCollection((Collection<?>)ent.getValue()); 
			else
				System.out.print(ent.getValue());
			if(i==map.size()-1)
				System.out.print("}"); 
			else
				System.out.print(", ");
		}
	}
	
	/**
	 * Print the variable argument list without wrapping it in an array on the current line, without
	 * commas separating two elements but spaces. For data structures, including arrays, it provides 
	 * a deep printing in which the base level of the container, or multidimensional array, is exposed 
	 * and printed. Primitive arrays are printed in the standard format as of object arrays. 
	 * @param args The variable argument list to be printed. 
	 */
	@SafeVarargs
	public static void print(Object... args)
	{
		for(Object item: args)
		{
			//If an array is present. 
			if(item.getClass().getComponentType()!=null)
			{
				helpArray((Object[])item); 
				System.out.print(" ");
			}
			//If a collection is present. 
			else if(item instanceof Collection)
				printCollection((Collection<?>)item); 
			//If a map is present. 
			else if(item instanceof Map)
				printMap((Map<?,?>)item); 
			else 
				System.out.print(item+" ");
		}
		System.out.println();
	}
	
	/**
	 * Print the variable argument list in a line without wrapping it in an array and separating
	 * items with commas but with spaces. For data structures, including arrays, it provides a deep 
	 * printing in which the base level of the container, or multidimensional array, is exposed and 
	 * printed. Primitive arrays are printed in the standard format as of object arrays. 
	 * @param args The variable argument list to be printed in a line. 
	 */
	@SafeVarargs
	public static void printline(Object... args)
	{
		print(args); 
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
			boolean isArray = input[0].getClass().getComponentType()!=null; 
			//If not an array, wrap it as an array. 
			print(isArray?"":"[",input[0],isArray?"":"]","\n");
			return; 
		}
		System.out.print("[");
		for(int i=0; i<input.length; i++)
		{
			print(input[i]); 
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
			else if(input[i] instanceof Collection)
				printCollection((Collection<?>)input[i]);
			else if(input[i] instanceof Map)
				printMap((Map<?,?>)input[i]); 
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
	 * Fill the given assumed 1D array of Object reference starting from the given starting index 
	 * with the elements given in the variable argument list of the same type. This method will 
	 * not generate an exception if the target array is filled before the filling arguments are 
	 * exhausted. It will only throw an IndexOutOfBoundsException when the filling list is exhausted 
	 * before the target array is filled. 1D primitive arrays can also be filled in by this method. 
	 * Prefer the compile time checked version of Array.addAll when the type of array is known. 
	 * @param arr The 1D array to be filled. 
	 * @param index The starting index to fill the array. 
	 * @param fill The argument array to fill the array with starting from its first index. Arguments of wrong types generate exceptions. 
	 */
	public static void addAll(Object arr, int index, Object fill)
	{
		for(int i=0; i<java.lang.reflect.Array.getLength(arr)-index; i++)
			java.lang.reflect.Array.set(arr,index+i,java.lang.reflect.Array.get(fill,i));
	}
	
	public static <T> void addAll(T[] arr, int index, Collection<T> holder)
	{
		if(arr.length-index<holder.size())
			throw new SizeMismatchException("More Elements than Can Be Held by the Given Array!"); 
		for(T item: holder)
			arr[index++] = item;
	}
	
	public static <T> void clearAll(ArrayList<T> list, int start, int end)
	{
		for(int i=0; i<end-start; i++)
			list.remove(start); 
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
	
	/**
	 * Copy the given object array of any dimensions under object reference to an identical array of the
	 * same dimensions by copying the references of the most fundamental elements, that is, a deep copy
	 * is performed. Primitive arrays should be copied by the overloaded method in which the identify
	 * of the array is veiled under an object reference. 
	 * @param arr The array to be copied. 
	 * @return The copied array. 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] copy(T[] arr)
	{
		return (T[])copy((Object)arr); 
	}
	
	/**
	 * Copy the given array of any dimensions under object reference to an identical array of the
	 * same dimensions by copying the references of the most fundamental elements, that is, a deep copy
	 * is performed. Primitive arrays should be copied by this method, whereas Object arrays should 
	 * be copied by overloaded method with compile time type checking. 
	 * @param arr The array to be copied. 
	 * @return The copied array. 
	 */
	public static Object copy(Object arr)
	{
		//The component type of this array. 
		Class<?> componentType = arr.getClass().getComponentType(); 
		//The length of this array. 
		int length = java.lang.reflect.Array.getLength(arr); 
		Object wrapped = java.lang.reflect.Array.newInstance(componentType,length); 
		//Base case of a one-dimensional array. 
		if(componentType.getComponentType()==null)
			addAll(wrapped,0,arr); 
		//If nested layers. 
		else
			for(int i=0; i<length; i++)
				java.lang.reflect.Array.set(wrapped,i,copy(java.lang.reflect.Array.get(arr,i)));
		return wrapped; 
	}
	
	public static void dimensions(Object[] tar)
	{
		
	}
	
	/**
	 * Wrap the given primitive array object of arbitrary dimensions in an array object of the
	 * same dimensions but of the wrapper type instead of the primitive. The returned array 
	 * should be casted by the user if the compile time reference is significant. Warnings, if
	 * generated, should be suppressed with knowledge of the correctness of this method. This 
	 * method is implemented with reflection for generality; if minute efficiency is demanded, 
	 * with the proper knowledge of the peculiar type of the array, wrap that array manually. 
	 * This method first checks whether the given object is indeed an instance of a primitive 
	 * array. If not, an Exceptions.NonePrimitiveArrayException is thrown. 
	 * @param obj The primitive array of some dimensions to be wrapped in an array of the same dimensions of the wrapper type. 
	 * @return The array object of the wrapper type correspondent to the primitive type of the array given. 
	 */
	public static Object wrap(Object obj)
	{
		if(!isPrimitiveArray(obj))
			throw new NonePrimitiveArrayException("The Given Argument is not a Primitive Array!"); 
		//Clear the helper container. 
		nestedArrs.clear(); 
		return wrapping(obj); 
	}
	
	/**
	 * A helper list to the Array.wrapping method that contains all the arrays created of the next nested level. 
	 */
	private static ArrayList<Object> nestedArrs = new ArrayList<Object>(); 
	
	/**
	 * Wrap the given primitive array object of arbitrary dimensions in an array object of the
	 * same dimensions but of the wrapper type instead of the primitive without first checking 
	 * if the given object is a primitive array. The returned array should be casted by the user 
	 * if the compile time reference is significant. Warnings, if generated, should be suppressed£»
	 * and type to be casted if necessary. This method is implemented with reflection for generality; 
	 * if minute efficiency is demanded, with the proper knowledge of the peculiar type of the array, 
	 * wrap that array manually. 
	 * @param obj The primitive array of some dimensions to be wrapped in an array of the same dimensions of the wrapper type. 
	 * @return The array object of the wrapper type correspondent to the primitive type of the array given. 
	 */
	private static Object wrapping(Object obj)
	{
		//Type of this array. 
		Class<?> type = obj.getClass(); 
		//The superficial length of this array, the dimension of this level.  
		int length = java.lang.reflect.Array.getLength(obj); 
		//The first index available after storage of parallel arrays of this nested level. 
		int start = nestedArrs.size(); 
		Object wrapped;
		//Base case of a 1D primitive array. 
		if(PMATYPES.containsValue(type))
		{
			//Create and fill the 1D primitive array into its wrapper type. 
			wrapped = java.lang.reflect.Array.newInstance(PMTYPES.get(type.getComponentType()),length); 
			//Fill. 
			addAll(wrapped,0,obj); 
			//Return this 1D array to be included in its upper level if applicable. 
			return wrapped; 
		}
		//Still layers nested, recursively solve for each nested array.  
		for(int i=0; i<length; i++)
			nestedArrs.add(wrapping(java.lang.reflect.Array.get(obj,i)));  
		//Create new array based on nested type captured; the last element, the most recent, is used.   
		wrapped = java.lang.reflect.Array.newInstance(nestedArrs.get(nestedArrs.size()-1).getClass(),length);     
		//Access each sub-array and assign it. 
		int index = 0; 
		for(int i=start; i<nestedArrs.size(); i++)
			java.lang.reflect.Array.set(wrapped,index++,nestedArrs.get(i));  
		//Discard the computed sub-arrays of this level that are not of interest to the upper level. 
		clearAll(nestedArrs,start,nestedArrs.size()); 
		return wrapped; 
	}
	
	
	
	
	public static void main(String[] args) 
	{	
		Map<Integer,Integer> tester = new HashMap<Integer,Integer>(); 
		tester.put(0,1);
		tester.put(1,2);
		printMap(tester); 
	}

}
