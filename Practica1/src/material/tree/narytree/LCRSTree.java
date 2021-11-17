
package material.tree.narytree;

import java.util.Iterator;
import material.Position;



/**
 *
 * @author mayte
 * @param <T>
 */
public class LCRSTree<T> implements NAryTree<T> {

    private class LCRSNode<E> implements Position<E>{
        E element;
        LCRSNode<E> parent, child, sibling;

        public LCRSNode(E element, LCRSNode<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public LCRSNode(E element, LCRSNode<E> parent, LCRSNode<E> sibling) {
            this.element = element;
            this.parent = parent;
            this.sibling = sibling;
        }

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public LCRSNode<E> getParent() {
            return parent;
        }

        public void setParent(LCRSNode<E> parent) {
            this.parent = parent;
        }

        public LCRSNode<E> getChild() {
            return child;
        }

        public void setChild(LCRSNode<E> child) {
            this.child = child;
        }

        public LCRSNode<E> getSibling() {
            return sibling;
        }

        public void setSibling(LCRSNode<E> sibling) {
            this.sibling = sibling;
        }
    }

    private LCRSNode<T> root;
    private int size;

    @Override
    public Position<T> addRoot(T e) {
        if(root==null){
            throw new RuntimeException("Exists another root");
        }
        root = new LCRSNode<>(e, null);
        size++;
        return root;
    }

    @Override
    public Position<T> add(T element, Position<T> p) {
        LCRSNode<T> node = checkNode(p);
        LCRSNode<T> child = null;
        if(node.getChild()==null){
            child = new LCRSNode<>(element, node);
        }
        else{
            LCRSNode<T> right = node.getChild();
            child = new LCRSNode<>(element, node, right);
        }
        node.setChild(child);
        return child;

    }

    @Override
    public Position<T> add(T element, Position<T> p, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void swapElements(Position<T> p1, Position<T> p2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T replace(Position<T> p, T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Position<T> p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NAryTree<T> subTree(Position<T> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attach(Position<T> p, NAryTree<T> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<T> root() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<T> parent(Position<T> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<? extends Position<T>> children(Position<T> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInternal(Position<T> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLeaf(Position<T> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRoot(Position<T> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<Position<T>> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int size() {
        return size;
    }

    private LCRSNode<T> checkNode(Position<T> p){
        if(p==null||!(p instanceof LCRSNode<T>)){
            throw new RuntimeException("Invalid position");
        }
        return (LCRSNode<T>) p;
    }



    
}
