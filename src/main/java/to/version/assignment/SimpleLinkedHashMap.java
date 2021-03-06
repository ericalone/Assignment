package to.version.assignment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

// Please feel free to make any code cleanups or changes
public class SimpleLinkedHashMap<K, V> {

	// This is inefficient with respect to space consumed as we're storing data in
	// multiple data structures
	// can we eliminate one of the data structures?
	
	// looks like 'orderedKeys' is only used to keep track of the order of 
	// insertion.
    // 1. we might want to use array instead of linked list. But array is 
	//    data structure too. so it might not help that much. LinkedList 
	//    maintains element data and two pointers for neighbor nodes hence 
	//    the memory consumption is high in LinkedList comparatively.
	// 2. Use Map.entry? I believe that is what the full version of 
	//	  LinedHaspMap
	// 3. any other thought?
	
	private HashMap<K, V> entries = new HashMap<K, V>();
	private LinkedList<K> orderedKeys = new LinkedList<K>();

	public SimpleLinkedHashMap() {
	}

	public SimpleLinkedHashMap(HashMap<K, V> initial) {
		this.entries = initial;
		this.orderedKeys.addAll(initial.keySet());
	}

	public void put(K key, V value) {
		entries.put(key, value);
		if(!orderedKeys.contains(key)) orderedKeys.add(key);
	}

	public V get(K key) {
		return entries.get(key);
	}

	public V remove(K key) {
		orderedKeys.remove(key);
		return entries.remove(key);
	}
	
	public Iterator<Map.Entry<K, V>> iterator() {
		// TODO: Implement this
		
		return new Iterator<Map.Entry<K, V>>() {
			Iterator<K> it = orderedKeys.iterator();
			
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Map.Entry<K, V> next() {	
				if(!it.hasNext())	{
					throw new NoSuchElementException();
				}
				
				K key = it.next();
				for (Map.Entry<K, V> entry : entries.entrySet()) {
			        if (key.equals(entry.getKey())) 	return entry;
			    } 

				return null;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};

	}

}
