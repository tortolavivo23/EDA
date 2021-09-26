/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mayte
 */
public class FloatLinkedListTest {
    
    public FloatLinkedListTest() {
    }
    
    
    public FloatLinkedList inicializa(){
       
        FloatLinkedList instance = new FloatLinkedList();
        
        instance.add(3F);
        instance.add(8F);
        instance.add(12F);
        
        return instance; //[12, 8, 3]
    }
    /**
     * Test of size method, of class FloatLinkedList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        FloatLinkedList instance = inicializa();
              
        int expResult = 3;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of isempty method, of class FloatLinkedList.
     */
    @Test
    public void testIsempty1() {
        System.out.println("isempty");
        FloatLinkedList instance = inicializa();
        boolean result = instance.isempty();
        assertFalse(result);
       
    }

    /**
     * Test of isempty method, of class FloatLinkedList.
     */
    @Test
    public void testIsempty2() {
        System.out.println("isempty");
        FloatLinkedList instance = new FloatLinkedList();
        boolean result = instance.isempty();
        assertTrue(result);
       
    }
    
    /**
     * Test of add method, of class FloatLinkedList.
     */
    @Test
    public void testAdd_Float() {
        System.out.println("add");
        FloatLinkedList instance = new FloatLinkedList();
        assertTrue(instance.isempty());
        instance.add(2F);
        assertFalse(instance.isempty());
        assertEquals(instance.size(),1);
    }

    /**
     * Test of add and remove methods, of class FloatLinkedList.
     */
    @Test
    public void testAdd_int_Remove() {
        System.out.println("add");
        int index = 2;
        Float value = 5F;
        FloatLinkedList instance = inicializa(); // [12, 8, 3]
        instance.add(index, value); // [12, 8, 5, 3]
        assertEquals(instance.size(),4);
        Float remove = instance.remove();
        assertEquals(12.0,remove,0.01);
        remove = instance.remove();
        assertEquals(8.0,remove,0.01);
        remove = instance.remove();
        assertEquals(5.0,remove,0.01);
        remove = instance.remove();
        assertEquals(3.0,remove,0.01);
        assertTrue(instance.isempty());
    }

    /**
     * Test of remove method, of class FloatLinkedList.
     */
    @Test
    public void testRemove_int() {
        System.out.println("remove");
        int index = 2;
        FloatLinkedList instance = inicializa(); // [12, 8, 3]
        Float result = instance.remove(index);// 8
        assertEquals(8.0, result,0.01);
       
    }

    /**
     * Test of get method, of class FloatLinkedList.
     */
    @Test
    public void testGet_0args() {
        System.out.println("get");
        FloatLinkedList instance = inicializa(); // [12, 8, 3]
        Float result = instance.get();//12
        assertEquals(12.0, result,0.01);
        
    }

    /**
     * Test of get method, of class FloatLinkedList.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int index = 2;
        FloatLinkedList instance = inicializa(); // [12, 8, 3]
        Float result = instance.get(index);// 8
        assertEquals(8.0, result,0.01);
       
    }

    /**
     * Test of search method, of class FloatLinkedList.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        float value = 5F;
        FloatLinkedList instance = inicializa();// [12, 8, 3]
        int expResult = 0;
        int result = instance.search(value);
        assertEquals(expResult, result);
        value = 3F;
        expResult = 3;
        result = instance.search(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class FloatLinkedList.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        float value = 5F;
        FloatLinkedList instance = inicializa();// [12, 8, 3]
        boolean result = instance.contains(value);
        assertFalse(result);
        value = 12F;
        result = instance.contains(value);
        assertTrue(result);
    }
    
}
