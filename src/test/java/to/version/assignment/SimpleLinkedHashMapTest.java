package to.version.assignment;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.hamcrest.core.IsNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SimpleLinkedHashMapTest {
	@Test
    public void verifyMapRemembersOrderInWhichElementsWereAdded() {
        SimpleLinkedHashMap<String, Integer> linkedHashMap = new SimpleLinkedHashMap<>();
        linkedHashMap.put("if1", 10);
        linkedHashMap.put("if2", 20);
        linkedHashMap.put("if3", 30);
        linkedHashMap.put("if1", 11);
        Iterator<Map.Entry<String, Integer>> iterator = linkedHashMap.iterator();

        Map.Entry<String, Integer> first = iterator.next();
        assertThat(first.getKey(), is("if1"));
        assertThat(first.getValue(), is(11));

        Map.Entry<String, Integer> second = iterator.next();
        assertThat(second.getKey(), is("if2"));
        assertThat(second.getValue(), is(20));
        
        Map.Entry<String, Integer> third = iterator.next();
        assertThat(third.getKey(), is("if3"));
        assertThat(third.getValue(), is(30));
       
        assertThat(iterator.hasNext(), is(false));
    }

    // Add additional tests for the other methods
	@Test
	public void testRetrievingElementByKey() {
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("if1", 1);
        
		SimpleLinkedHashMap <String,Integer> linkedHashMap = new SimpleLinkedHashMap<String,Integer>(hash);    
        assertThat(linkedHashMap.get("if1"), is(1));
    }
	
	@Test
	public void testAddingElementToMap() {
        SimpleLinkedHashMap <String,Integer> linkedHashMap = new SimpleLinkedHashMap<String,Integer>();
        linkedHashMap.put("if1", 1);      
        assertThat(linkedHashMap.get("if1"), is(1));
    }
	
	@Test
	public void testDeletingElementFromMap() {
        SimpleLinkedHashMap <String,Integer> linkedHashMap = new SimpleLinkedHashMap<String,Integer>();
        linkedHashMap.put("if1", 1);      
        assertThat(linkedHashMap.get("if1"), is(1));
        
        linkedHashMap.remove("if1"); 
        assertThat(linkedHashMap.get("if1"), is(IsNull.nullValue()));
    }
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testIteratorWithEmptyElements()	{
		SimpleLinkedHashMap<String, Integer> linkedHashMap = new SimpleLinkedHashMap<>();
        Iterator<Map.Entry<String, Integer>> iterator = linkedHashMap.iterator();

        assertThat(iterator.hasNext(), is(false));
        
        thrown.expect(NoSuchElementException.class);
        iterator.next();
	}
}