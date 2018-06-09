package dermatj.type.reflect;

import java.util.Set;

import dermatj.utils.seq.Array;

import java.util.HashSet; 
import java.util.Map;
import java.util.Collections;
import java.util.HashMap; 

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor; 

public class Access 
{
	/**
	 * The reference to the central class type information of interest. 
	 */
	private Class<?> ref;
	/**
	 * The target object to be inquired, if any. 
	 */
	private Object tar;
	/**
	 * All declared fields associated with this class, static and instance. 
	 */
	private Map<String,Field> fields = new HashMap<String,Field>(); 
	/**
	 * Whether a field recorded in the fields map in parallel is static. 
	 */
	private Map<String,Boolean> fIsStatic = new HashMap<String,Boolean>(); 
	/**
	 * All declared methods associated with this class, static and instance. 
	 */
	private Map<String,Method> methods = new HashMap<String,Method>(); 
	/**
	 * Whether a method recorded in the methods map in parallel is static.  
	 */
	private Map<String,Boolean> mIsStatic = new HashMap<String,Boolean>(); 
	/**
	 * All declared constructors of this class. 
	 */
	private Map<String,Constructor<?>> constructors = new HashMap<String,Constructor<?>>(); 
	
	/**
	 * Generically center the access to the type information of the given class to enable
	 * query of fields, methods, and constructors, as well as later manipulation of objects
	 * of this class. If multiple objects of a given class are to be manipulated, the creation
	 * of one such generic access is recommended. 
	 * @param ref The Class whose type information is to be inquired. 
	 */
	public Access(Class<?> ref)
	{
		this.ref = ref; 
		//Load information of type. 
		loadFields(); 
		loadMethods(); 
		loadConstructors(); 
	}
	
	/**
	 * Create an access to the given class and offer a target object that may be conveniently 
	 * manipulated by this access. If manipulation of multiple objects of the given type is desired, 
	 * create instead an access with no specific target and invoke methods that take an object as argument. 
	 * @param ref The Class whose type information is to be inquired. 
	 * @param tar A target object of the given type specified by the class to be manipulated.
	 */
	public Access(Class<?> ref, Object tar)
	{
		this(ref); 
		setTarget(tar); 
	}
	
	/**
	 * Set the target of this access to this particular object. Convenient for manipulation
	 * of one single object. 
	 * @param tar The target object to be manipulated by this access. 
	 */
	public void setTarget(Object tar)
	{
		if(!ref.isInstance(tar))
			throw new RuntimeException("Object Not Ascribable from Given Class!"); 
		this.tar = tar; 
	}
	

	/**
	 * Set the field of the given name to the given value. If the field is static, 
	 * the value is adjusted for the entire class. If it is an instance field, the 
	 * given value is set for that field of the target object of this access. 
	 * @param name The name of the field to be manipulated. 
	 * @param val The value the given field is adjusted to. 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public void setField(String name, Object val) throws IllegalArgumentException, IllegalAccessException
	{
		setField(tar,name,val); 
	}
	
	/**
	 * Set the field of the given name to the given value. If the field is static, 
	 * the value is adjusted for the entire class. If it is an instance field, the 
	 * given value is set for that field of the target object given. 
	 * @param tar The object whose value of this field is to be adjusted. 
	 * @param name The name of the field to be manipulated. 
	 * @param val The value the given field is adjusted to. 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public void setField(Object tar, String name, Object val) throws IllegalArgumentException, IllegalAccessException
	{
		Field field = fields.get(name); 
		field.setAccessible(true); 
		field.set(tar,val);
	}
	
	/**
	 * Invoke method of the given name with the given arguments on the target object given in the argument. 
	 * If the method is static, the target in which the given method is invoked upon is ignored; it is set to the 
	 * target object of this access if otherwise. 
	 * @param name The name of the method to be invoked. 
	 * @param args The arguments to invoke this method. 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public void invokeMethod(String name, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		invokeMethod(tar,name,args);
	}
	
	/**
	 * Invoke method of the given name with the given arguments on the target object given in the argument. 
	 * If the method is static, the target argument specifying the specific object to invoke such method 
	 * is ignored. 
	 * @param tar The target object which this method is invoked upon. 
	 * @param name The name of the method to be invoked. 
	 * @param args The arguments to invoke this method. 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public void invokeMethod(Object tar, String name, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Method met = methods.get(name); 
		met.setAccessible(true); 
		met.invoke(tar,args); 
	}
	
	/**
	 * Load all declared fields contained in this class into a mapping by field name. 
	 */
	private void loadFields()
	{
		String fieldName; 
		for(Field holder: ref.getDeclaredFields())
		{
			fieldName = holder.getName(); 
			fields.put(fieldName, holder); 
			if(holder.toString().contains("static"))
				fIsStatic.put(fieldName,true); 
			else
				fIsStatic.put(fieldName, false); 
		}
		//Set the mapping to be unmodifiable. 
		fields = Collections.unmodifiableMap(fields); 
		fIsStatic = Collections.unmodifiableMap(fIsStatic); 
	}
	
	/**
	 * Load all declared methods contained in this class into a mapping by method name. 
	 */ 
	private void loadMethods()
	{
		String methodName;
		for(Method met: ref.getDeclaredMethods())
		{
			methodName = met.getName(); 
			methods.put(methodName,met); 
			if(met.toString().contains("static"))
				mIsStatic.put(methodName,true); 
			mIsStatic.put(methodName,false); 
		}
		//Set the mapping to be unmodifiable. 
		methods = Collections.unmodifiableMap(methods);
		mIsStatic = Collections.unmodifiableMap(mIsStatic); 
	}
	
	/**
	 * Load all declared constructors contained in this class into a mapping by constructor name. 
	 */
	private void loadConstructors()
	{
		for(Constructor<?> con: ref.getDeclaredConstructors())
			constructors.put(con.getName(),con); 
		//Set the mapping to be unmodifiable. 
		constructors = Collections.unmodifiableMap(constructors); 
	}
	
	/**
	 * Expose the entire hierarchy of all classes from which this given class is derived along
	 * with the interfaces directly implemented by them. 
	 * @param tar The target class to be inquired. 
	 * @return A set that holds the gathered class references to be written by this method.  
	 */
	public static Set<Class<?>> hierarchy(Class<?> tar)
	{
		Set<Class<?>> tree = new HashSet<Class<?>>(); 
		hierarchy(tar,tree); 
		return tree; 
	}
	
	/**
	 * Expose the entire hierarchy of all classes from which this given class is derived along
	 * with the interfaces directly implemented by them. 
	 * @param tar The target class to be inquired. 
	 * @param line A set that holds the gathered class references to be written by this method.  
	 */
	private static void hierarchy(Class<?> tar, Set<Class<?>> line)
	{
		//Base case of no more super class. 
		if(tar==null)
			return; 
		//Add the current class to the class hierarchy. 
		line.add(tar); 
		//Add the interfaces. 
		Collections.addAll(line,tar.getInterfaces()); 
		//Compute hierarchy for super classes. 
		hierarchy(tar.getSuperclass(),line); 
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		Class<?> ref1 = Comparable.class; 
		Class<?> ref2 = String.class; 
		Method one=null, two=null; 
		for(Method holder: ref1.getDeclaredMethods())
			if(holder.getName().contains("compare"))
				one = holder; 
		for(Method holder: ref2.getDeclaredMethods())
			if(holder.getName().contains("compare"))
				two = holder; 
		System.out.println(hierarchy(two.getClass()));
	}

}
