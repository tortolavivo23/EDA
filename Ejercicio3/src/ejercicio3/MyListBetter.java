
package ejercicio3;

import material.*;

import java.util.Iterator;

/**
 *
 * @author mayte
 * @param <E>
 */

public interface MyListBetter<E> extends Iterable<Position<E>>{
    /**
     * Returns the number of elements in this list
     * 
     * @return 
     */
    int size();
    
    /**
     * Returns true if this list contains no elements
     * 
     * @return 
     */
    boolean isempty();

    /**
     * Appends the specified element to the begining of this list
     *
     * @param value
     * @return
     */
    Position<E> add(E value);

    /**
     * Inserts the specified element after the specified position in this list (optional operation)
     * 
     * @param pos
     * @param value 
     * @return  
     */
    Position<E> addAfter(Position<E> pos, E value) throws InvalidPositionException;
    
    /**
     * Inserts the specified element before the specified position in this list (optional operation)
     * 
     * @param pos
     * @param value 
     * @return  
     */
    Position<E> addBefore(Position<E> pos, E value) throws InvalidPositionException;
    
    /**
     * Remove and returns the element at the beginning of this list
     * 
     * @param pos
     * @return 
     */
    E remove(Position<E> pos) throws InvalidPositionException;


    /**
     * Returns the element at the beginning of this list
     * 
     * @return 
     */
    Position<E> get();
    
    /**
     * Modifies the element at the specified position in this list
     * 
     * @param pos
     * @param value
     * @return 
     */
    Position<E> set(Position<E> pos, E value) throws InvalidPositionException;
    
    /**
     * Returns the element's Position if this list contains the specified element
     * otherwise returns null
     * 
     * @param value
     * @return 
     */
    Position<E> search(E value);
    
    /**
     * Returns true if this list contains the specified element.
     * 
     * @param value
     * @return 
     */
    boolean contains(E value);
    
    /**
     * Returns an iterator of the elements
     * 
     * @return 
     */
    @Override
    Iterator<Position<E>> iterator();

}
