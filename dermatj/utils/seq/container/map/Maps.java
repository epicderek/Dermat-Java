package dermatj.utils.seq.container.map;

import static dermatj.utils.excs.Exceptions.*; 

import java.util.Map; 

public abstract class Maps 
{

	public static <K,V> void putAll(Map<K,V> map, K[] keys, V[] vals)
	{
		for(int i=0; i<keys.length; i++)
			try
			{
				map.put(keys[i],vals[i]); 
			}catch(IndexOutOfBoundsException ex)
			{
				throw new SizeMismatchException("Length of Values Array Smaller than That of Keys!");
			}
	}
	
	public static void main(String[] args) 
	{

	}

}
