


import material.Position;

import java.util.Iterator;

/**
 *
 * @author mayte
 * @param <E>
 */
public class LCRSTree<E> implements NAryTree<E> {


    private class LCRSnode<T> implements Position<T>{
        private T element;
        private LCRSnode<T> child, next, parent;
        public LCRSnode(T element) {
            this.element = element;
        }

        @Override
        public T getElement() {
            return null;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public LCRSnode<T> getLeft() {
            return child;
        }

        public void setLeft(LCRSnode<T> left) {
            this.child = left;
        }

        public LCRSnode<T> getNext() {
            return next;
        }

        public void setNext(LCRSnode<T> next) {
            this.next = next;
        }

        public LCRSnode<T> getParent() {
            return parent;
        }

        public void setParent(LCRSnode<T> parent) {
            this.parent = parent;
        }
    }





    public LCRSTree<E>(){
        size=0;
    }


    @Override
    public Position<E> addRoot(E e) {
        if(this.isEmpty()){
            root = new LCRSnode<>(e);
        }
        else{
            try {
                throw new Exception();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return root;
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        LCRSnode<E> parent = null;
        try {
            parent = checknode(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSnode<E> prev = parent.getLeft();
        LCRSnode<E> child = new LCRSnode<>(element);
        child.setParent(parent);
        if(prev==null){
            parent.setLeft(child);
        }
        else{
            while(prev.getNext()!=null){
                prev = prev.getNext();
            }
            prev.setNext(child);
        }
        size++;
        return child;
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        LCRSnode<E> parent = null;
        try {
            parent = checknode(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSnode<E> prev = parent.getLeft();
        LCRSnode<E> child = new LCRSnode<>(element);
        child.setParent(parent);
        if(prev==null){
            parent.setLeft(child);
        }
        else{
            int i = 0;
            while(prev.getNext()!=null&& i<n){
                prev = prev.getNext();
                i++;
            }
            child.setNext(prev.getNext());
            prev.setNext(child);
        }
        size++;
        return child;
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E replace(Position<E> p, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NAryTree<E> subTree(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> root() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> parent(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInternal(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRoot(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<Position<E>> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private LCRSnode<E> checknode(Position<E> p)throws InvalidPositionException{
        if(p==null || !(p instanceof LCRSnode<E>)){
            throw new InvalidPositionException("The position is invalid");
        }

        LCRSnode<E>n = (LCRSnode<E>) p;
        while (n.getParent()!=null){
            n = n.getParent();
        }
        if(n!=root){
            throw new InvalidPositionException("The position is invalid");
        }
        return (LCRSnode<E>) p;
    }

    private LCRSnode<E> root;
    private int size;
}
