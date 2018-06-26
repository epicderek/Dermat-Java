package dermatj.utils.seq.container.map;

import static dermatj.utils.excs.Exceptions.*; 
 
import java.util.Set; 
import java.util.Map; 
import java.util.AbstractSet;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator; 

/**
 * A dermatj.utils.seq.container.map.DuoMap is a parallel-access hashed Map whose collection of 
 * values must be a unique set with no duplicate elements that enables lookup and modification
 * of entries in constant time from both the key to the value and the value to the key. One such
 * Map is implemented by holding two HashMaps with mappings in both directions. Therefore, both the 
 * types of the key and the value must be capable of being readily hashed, with their hashCode() methods
 * effectively overridden. Whenever an insertion of an entry whose value is a duplicate to those 
 * entries presently contained in this DuoMap with different keys to that of this entry, 
 * a dermatj.utils.excs.Exceptions.DuplicateValueException is thrown. This exception is a RuntimeException, 
 * and can therefore be ignored if handling if unnecessary or can be attributed to a programming error. 
 * As specific type information of a Map being a dermatj.utils.seq.container.map.DuoMap is essential for
 * invoking the reverse access from values to keys, when such a Map is used, its specific type information
 * should not be abstracted. 
 * <p>
 * To set this Map unmodifiable, invoke the DuoMap.setUnmodifiable method. Once set to unmodifiable, 
 * it cannot generally be reset. Whereas classes in the same package, or derived classes, may have 
 * access to the variable unmodifiable. 
 * @author Derek
 *
 * @param <K> Base type of keys. 
 * @param <V> Base type of values. 
 */
public class DuoMap<K,V> extends AbstractMap<K,V>
{
	/**
	 * Whether this DuoMap can be modified. 
	 */
	protected boolean unmodifiable = false; 
	/**
	 * Forward normal access of key to value. 
	 */
	private HashMap<K,V> forward = new HashMap<K,V>(); 
	/**
	 * Reversal access from value to key. 
	 */
	private HashMap<V,K> backward = new HashMap<V,K>(); 
	
	/**
	 * Construct a hashed parallel access Map that enables quick lookup of entries from both the key
	 * to the value and the value to the key. Both the key and the value must be capable of being hashed, 
	 * that is with their hashCode() method overridden. No entry of different keys but identical values
	 * with the current mappings contained in this DuoMap can be inserted. If any such insertion occurs, 
	 * an Exceptions.DuplicateValueException derived from RuntimeException is thrown. 
	 */
	public DuoMap()
	{
		
	}
	
	/**
	 * Construct a hashed parallel access Map that enables quick lookup of entries from both the key 
	 * to the value and the value to the key with a set of original mappings contained in another Map. 
	 * The mappings contained in such Map should not entertain duplicate values.  
	 * @param ori The source Map with the original mappings to be imported to this DuoMap. 
	 */
	public DuoMap(Map<? extends K,? extends V> ori)
	{
		this();
		//Place all current mappings. 
		putAll(ori); 
	}
	
	public boolean containsKey(Object key)
	{
		return forward.containsKey(key); 
	}
	
	/**
	 * Check if there currently exists an entry in this map whose value is identical to this given value.
	 * This operation consumes constant time, that is O(c).  
	 * @return Whether there exists a entry with the given value. 
	 */
	public boolean containsValue(Object val)
	{
		return backward.containsKey(val); 
	}
	
	/**
	 * Insert a new entry of the given key and value into the current map. If the key is present,
	 * that particular mapping is overwritten. If an entry with its value a duplicate of the current
	 * entries in this map of unique keys to this key, an Exceptions.DuplicateValueException is thrown,
	 * which, as it constitutes a derived RuntimeException, is left to the discretion of the user. 
	 * @param key The key of this entry to be inserted. 
	 * @param val The value of this entry to be inserted. This value should not be duplicate with that of distinct previous entries. 
	 * @return The value that is previously associated with this key, null if the key is unique to all present mappings.  
	 */
	public V put(K key, V val) throws DuplicateValueException
	{
		//If currently at the state in which no modification of entries are allowed. 
		if(unmodifiable)
			throw new ItemUnmodifiableException("This DuoMap Is Unmodifiable!"); 
		//If the key is not already present. 
		if(!forward.containsKey(key))
		{
			//If the given value if duplicate of that of another present entry. 
			if(backward.containsKey(val))
				throw new DuplicateValueException("Duplicate Entries with the Given Value!"); 
			//Record the entry. 
			backward.put(val,key); 
			return forward.put(key,val);
		}
		//If the key is present. 
		else
		{
			//If the value is present and is not of that which corresponds to this key. 
			if(backward.containsKey(val)&&!backward.get(val).equals(key)) 
				throw new DuplicateValueException("Duplicate Entries with the Given Value!"); 
			//Record the entry. 
			backward.put(val,key); 
			return forward.put(key,val);
		}
	}
	
	public void putAll(Map<? extends K, ? extends V> ori) 
	{
		for(Entry<? extends K,? extends V> ent: ori.entrySet())
			put(ent.getKey(),ent.getValue()); 
	}
	
	public V get(Object key)
	{
		return forward.get(key); 
	}
	
	/**
	 * Return the key associated with this value if the value is present. Null is returned otherwise. 
	 * This method consumes constant lookup time, that is O(c). 
	 * @param val The value whose key is to be sought. 
	 * @return The key associated with this value if the key is present, null otherwise. 
	 */
	public K getKey(V val)
	{
		return backward.get(val); 
	}
	
	/**
	 * Remove the entry of the given key if it be present and return the value associated with this
	 * present key in the entry. Return null otherwise. 
	 * @return The value associated with this key if the key is present, null otherwise.  
	 */
	public V remove(Object key)
	{
		//If currently at the state in which no modification of entries are allowed. 
		if(unmodifiable)
			throw new ItemUnmodifiableException("This DuoMap Is Unmodifiable!"); 
		V val = forward.remove(key); 
		if(val==null)
			return null; 
		backward.remove(val); 
		return val; 
	}
	
	/**
	 * Remove the entry of the given value if it be present and return the value associated with this
	 * present value in the entry. Return null otherwise. 
	 * @return The key associated with this value if the value is present, null otherwise.  
	 */
	public K removeValue(V val)
	{
		//If currently at the state in which no modification of entries are allowed. 
		if(unmodifiable)
			throw new ItemUnmodifiableException("This DuoMap Is Unmodifiable!"); 
		K key = backward.remove(val); 
		if(key==null)
			return null; 
		forward.remove(key); 
		return key; 
	}
	
	/**
	 * Clear the current entries currently stored in this value map. 
	 */
	public void clear()
	{
		forward.clear(); 
		backward.clear(); 
	}
	
	/**
	 * Construct a read-only view of the current set of entries. It is recommended that the iterator 
	 * obtained from this set after some insertion of entry be exhausted before the occasion of 
	 * new insertions to avoid confusions of the relative sequence of keys. 
	 * @return A read-only view of the current set of entries contained in this Map. 
	 */
	public Set<Entry<K, V>> entrySet()
	{
		return new AbstractSet<Entry<K,V>>()
				{
					public Iterator<Entry<K,V>> iterator() 
					{
						return new Iterator<Entry<K,V>>()
								{
									private Iterator<Entry<K,V>> ite = forward.entrySet().iterator(); 
									
									public boolean hasNext() 
									{
										return ite.hasNext(); 
									}

									public Entry<K, V> next() 
									{
										return ite.next(); 
									}
								}; 
					}

					public int size() 
					{
						return forward.size(); 
					}
				}; 
	}
	
	/**
	 * Set this DuoMap to the state of unmodifiable, which indicates that no entries shall be modified
	 * by insertion or removal. Hence, the putting and removing are disabled. In case that it is desired
	 * that the state be reset to modifiable again, use package access to manipulate . 
	 */
	public void setUnmodifiable()
	{
		unmodifiable = true; 
	}
	
	public static void main(String[] args) 
	{
		
	}
}
