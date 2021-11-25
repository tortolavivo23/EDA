
package material.tree.narytree;

import java.util.Iterator;
import java.util.LinkedList;

import material.Position;
import material.tree.BreadthFirstTreeIterator;
import material.tree.PreOrderTreeIterator;


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

    @Override
    public Position<T> addRoot(T e) {
        if(root!=null){
            throw new RuntimeException("Exists another root");
        }
        root = new LCRSNode<>(e, null);
        return root;
    }

    @Override
    public Position<T> add(T element, Position<T> p) {
        LCRSNode<T> node = checkNode(p);
        LCRSNode<T> child = new LCRSNode<>(element, node);
        if(node.getChild()==null){
            node.setChild(child);
        }
        else{
            LCRSNode<T> left = node.getChild();
            while (left.getSibling()!=null){
                left = left.getSibling();
            }
            left.setSibling(child);
        }
        return child;

    }

    @Override
    public Position<T> add(T element, Position<T> p, int n) {
        LCRSNode<T> node = checkNode(p);
        LCRSNode<T> child = new LCRSNode<>(element, node);
        if(node.getChild()==null){
            node.setChild(child);
        }
        else{
            LCRSNode<T> left = node.getChild();
            int i = 0;
            while (left.getSibling()!=null&&i<n){
                left = left.getSibling();
                i++;
            }
            node.setSibling(left.getSibling());
            left.setSibling(node);
        }
        return child;
    }

    @Override
    public void swapElements(Position<T> p1, Position<T> p2) {
        LCRSNode<T> n1 = checkNode(p1);
        LCRSNode<T> n2 = checkNode(p2);
        T e1 = n1.getElement();
        n1.setElement(n2.getElement());
        n2.setElement(e1);
    }

    @Override
    public T replace(Position<T> p, T e) {
        LCRSNode<T> node = checkNode(p);
        T out = node.getElement();
        node.setElement(e);
        return out;
    }

    @Override
    public void remove(Position<T> p) {
        LCRSNode<T> node = checkNode(p);
        if(isRoot(node)){
            root=null;
        }
        else{
            LCRSNode<T> parent = node.getParent();
            LCRSNode<T> left = parent.getChild();
            if(left.equals(node)){
                parent.setChild(left.getSibling());
            }
            else{
                while (!left.getSibling().equals(node)){
                    left = left.getSibling();
                }
                left.setSibling(node.getSibling());
            }
        }

    }

    @Override
    public NAryTree<T> subTree(Position<T> v) {
        LCRSNode<T> node = checkNode(v);
        if(isRoot(node)){
            root=null;
        }
        else{
            LCRSNode<T> parent = node.getParent();
            LCRSNode<T> left = parent.getChild();
            if(left.equals(node)){
                parent.setChild(left.getSibling());
            }
            else{
                while (!left.getSibling().equals(node)){
                    left = left.getSibling();
                }
                left.setSibling(node.getSibling());
            }
        }
        node.setParent(null);
        node.setSibling(null);
        LCRSTree<T>out = new LCRSTree<>();
        out.root = node;
        return out;
    }

    @Override
    public void attach(Position<T> p, NAryTree<T> t) {
        LCRSNode<T> node = checkNode(p);
        LCRSNode<T> child = checkNode(t.root());
        if(node.getChild()==null){
            node.setChild(child);
        }
        else{
            LCRSNode<T> left = node.getChild();
            while (left.getSibling()!=null){
                left = left.getSibling();
            }
            left.setSibling(node);
        }
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public Position<T> root() {
        return root;
    }

    @Override
    public Position<T> parent(Position<T> v) {
        LCRSNode<T> node = checkNode(v);
        return node.getParent();
    }

    @Override
    public Iterable<? extends Position<T>> children(Position<T> v) {
        LCRSNode<T> node = checkNode(v);
        LinkedList<Position<T>> list = new LinkedList<>();
        LCRSNode<T> left = node.getChild();
        while (left!=null){
            list.addLast(left);
            left = left.getSibling();
        }
        return list;
    }

    @Override
    public boolean isInternal(Position<T> v) {
        return !isLeaf(v);
    }

    @Override
    public boolean isLeaf(Position<T> v) {
        LCRSNode<T> node = checkNode(v);
        return node.getChild()==null;
    }

    @Override
    public boolean isRoot(Position<T> v) {
        LCRSNode<T> node = checkNode(v);
        return node.getParent()==null;
    }

    @Override
    public Iterator<Position<T>> iterator() {
        return new BreadthFirstTreeIterator<>(this);
    }

    int size() {
        int out = 0;
        Iterator<Position<T>> it = iterator();
        while (it.hasNext()){
            out++;
            it.next();
        }
        return out;
    }

    private LCRSNode<T> checkNode(Position<T> p){
        if(p==null||!(p instanceof LCRSNode<T>)){
            throw new RuntimeException("Invalid position");
        }
        return (LCRSNode<T>) p;
    }



    
}
