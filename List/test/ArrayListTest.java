

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author mayte
 */
public class ArrayListTest {
    
    public ArrayListTest() {
    }

    public ArrayList<Float> inicializa(){
       
        ArrayList<Float> instance = new ArrayList(6);
        
        instance.add(3F);
        instance.add(8F);
        instance.add(12F);
        
        return instance; //[12, 8, 3]
    }
    /**
     * Test of size method, of class FloatArrayList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        ArrayList<Float> instance = inicializa();
              
        int expResult = 3;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of isempty method, of class FloatArrayList.
     */
    @Test
    public void testIsempty1() {
        System.out.println("isempty");
        ArrayList<Float> instance = inicializa();
        boolean expResult = false;
        boolean result = instance.isempty();
        assertEquals(false, result);
       
    }

    /**
     * Test of isempty method, of class FloatArrayList.
     */
    @Test
    public void testIsempty2() {
        System.out.println("isempty");
        ArrayList<Float> instance = new ArrayList(2);
        boolean expResult = true;
        boolean result = instance.isempty();
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of add method, of class FloatArrayList.
     */
    @Test
    public void testAdd_Float() {
        System.out.println("add");
        Float value = null;
        ArrayList<Float> instance = new ArrayList(3);
        assertTrue(instance.isempty());
        instance.add(2F);
        assertFalse(instance.isempty());
        assertEquals(instance.size(),1);
    }

    /**
     * Test of add and remove methods, of class FloatArrayList.
     */
    @Test
    public void testAdd_int_Remove() {
        System.out.println("add");
        int index = 3;
        Float value = 5F;
        ArrayList<Float> instance = inicializa(); // [12, 8, 3]
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
     * Test of remove method, of class FloatArrayList.
     */
    @Test
    public void testRemove_int() {
        System.out.println("remove");
        int index = 2;
        ArrayList<Float> instance = inicializa(); // [12, 8, 3]
        Float result = instance.remove(index);// 8
        assertEquals(8.0, result,0.01);
       
    }

    /**
     * Test of get method, of class FloatArrayList.
     */
    @Test
    public void testGet_0args() {
        System.out.println("get");
        ArrayList<Float> instance = inicializa(); // [12, 8, 3]
        Float result = instance.get();//12
        assertEquals(12.0, result,0.01);
        
    }

    /**
     * Test of get method, of class FloatArrayList.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int index = 2;
        ArrayList<Float> instance = inicializa(); // [12, 8, 3]
        Float result = instance.get(index);// 8
        assertEquals(8.0, result,0.01);
       
    }

    /**
     * Test of search method, of class FloatArrayList.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        Float value = 5F;
        ArrayList<Float> instance = inicializa();// [12, 8, 3]
        int expResult = 0;
        int result = instance.search(value);
        assertEquals(expResult, result);
        value = 3F;
        expResult = 3;
        result = instance.search(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class FloatArrayList.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Float value = 5F;
        ArrayList<Float> instance = inicializa();// [12, 8, 3]
        boolean result = instance.contains(value);
        assertFalse(result);
        value = 12F;
        result = instance.contains(value);
        assertTrue(result);
    }
    
}
