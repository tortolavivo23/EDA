import material.Position;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mayte
 * @param <E>
 */
public class LCRSTree<E> implements NAryTree<E>{

    private class LCRSnode<T> implements Position<T>{
        private T element;
        private LCRSnode<T> child, next, parent;
        public LCRSnode(T element) {
            this.element = element;
        }

        @Override
        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public LCRSnode<T> getChild() {
            return child;
        }

        public void setChild(LCRSnode<T> left) {
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


    private LCRSnode<E> root;

    public LCRSTree() {root=null;}


    @Override
    public Position<E> addRoot(E e) {
        root = new LCRSnode<>(e);
        return root;
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        LCRSnode<E> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSnode<E> prev = parent.getChild();
        LCRSnode<E> child = new LCRSnode<>(element);
        child.setParent(parent);
        if(prev==null){
            parent.setChild(child);
        }
        else{
            int i = 0;
            while (prev.getNext() != null) {
                prev = prev.getNext();
                i++;
            }
            child.setNext(prev.getNext());
            prev.setNext(child);

        }
        return child;
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        LCRSnode<E> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSnode<E> prev = parent.getChild();
        LCRSnode<E> child = new LCRSnode<>(element);
        child.setParent(parent);
        if(prev==null){
            parent.setChild(child);
        }
        else{
            if(n==0){
                parent.setChild(child);
                child.setNext(prev);
            }
            else {
                int i = 0;
                while (prev.getNext() != null && i < n-1) {
                    prev = prev.getNext();
                    i++;
                }
                child.setNext(prev.getNext());
                prev.setNext(child);
            }
        }
        return child;
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        E value1 = p1.getElement();
        E value2 = p2.getElement();
        try {
            LCRSnode<E> n1 = checkPosition(p1);
            LCRSnode<E> n2 = checkPosition(p2);
            n1.setElement(value2);
            n2.setElement(value1);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E replace(Position<E> p, E e) {
        LCRSnode<E> n = null;
        try {
            n = checkPosition(p);
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        E element = n.getElement();
        n.setElement(e);
        return element;
    }

    @Override
    public void remove(Position<E> p) {
        if(isRoot(p)){
            root=null;
        }
        else{
            LCRSnode<E> node = null;
            try {
                node = checkPosition(p);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            LCRSnode<E> parent = node.getParent();
            LCRSnode<E> prev = parent.getChild();
            if(prev.equals(node)){
               parent.setChild(node.getNext());
            }
            else{
                while (!prev.getNext().equals(node)){
                    prev = prev.getNext();
                }
                prev.setNext(node.getNext());
            }
        }
    }

    @Override
    public NAryTree<E> subTree(Position<E> v) {
        LCRSnode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSTree<E> tree = new LCRSTree<>();
        if(isRoot(v)){
            root=null;
        }
        else{
            LCRSnode<E> parent = node.getParent();
            LCRSnode<E> prev = parent.getChild();
            if(prev.equals(node)){
                parent.setChild(null);
            }
            else{
                while (!prev.getNext().equals(node)){
                    prev = prev.getNext();
                }
                prev.setNext(node.getNext());
            }
            node.setParent(null);
        }
        tree.root = node;
        return tree;
    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        LCRSnode<E> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSnode<E> node = null;
        try {
            node = checkPosition(t.root());
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        node.setNext(parent.getNext());
        parent.setChild(node);
        node.setParent(parent);
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        LCRSnode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.getParent();
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        LCRSnode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSnode<E> child = node.getChild();
        List<Position<E>> list = new LinkedList<>();
        while(child!=null){
            list.add(child);
            child=child.getNext();
        }
        return list;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return !(isLeaf(v)||isRoot(v));
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        LCRSnode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.getChild()==null;
    }

    @Override
    public boolean isRoot(Position<E> v) {
        LCRSnode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.equals(root);
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new PreOrderIterator<>(this);
    }

    public int size() {
        int size = 0;
        for(Position<E> position: this){
            size++;
        }
        return size;
    }
    private LCRSnode<E> checkPosition(Position<E> p)throws InvalidPositionException{
        if(p==null || !(p instanceof LCRSnode<E>)){
            throw new InvalidPositionException("The position is invalid");
        }
        return (LCRSnode<E>) p;
    }



}

