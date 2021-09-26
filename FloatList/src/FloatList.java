/**
 *
 * @author mayte
 */
public interface FloatList {

    /**
     * Returns the number of elements in this list
     */
    default int size() {
        return 0;
    }

    /**
     * Returns true if this list contains no elements
     *
     */
    boolean isempty();
   
    /**
     * Appends the specified element to the begining of this list 
     *
     */
    void add(Float value);

    /**
     * Inserts the specified element at the specified position in this list (optional operation)
     */
    default void add(int index, Float value) {

    }

    /**
     * Returns and returns the element at the beginning of this list
     *
     */
    Float remove();
    
    /**
     * Removes and returns the element at the specified position in this list 
     *
     */
    Float remove(int index);

    Float get();
    
    /**
     * Returns the element at the specified position in this list
     *
     */
    Float get(int index);
    
    /**
     * Returns the element's position if this list contains the specified element
     * otherwise returns 0
     *
     */
    int search(Float value);
    
    /**
     * Returns true if this list contains the specified element.
     *
     */
    boolean contains(Float value);
}
