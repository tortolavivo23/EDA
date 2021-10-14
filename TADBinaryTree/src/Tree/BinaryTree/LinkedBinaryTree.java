
package Tree.BinaryTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import material.Position;

/**
 *
 * @author mayte
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>{
    private BTNode<E> root;

    private class BTNode<T> implements Position<T>{
        private T element;
        private BTNode<T> parent, left, right;

        public BTNode(T element, BTNode<T> parent, BTNode<T> left, BTNode<T> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public T getElement() {
            return null;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public BTNode<T> getParent() {
            return parent;
        }

        public void setParent(BTNode<T> parent) {
            this.parent = parent;
        }

        public BTNode<T> getLeft() {
            return left;
        }

        public void setLeft(BTNode<T> left) {
            this.left = left;
        }

        public BTNode<T> getRight() {
            return right;
        }

        public void setRight(BTNode<T> right) {
            this.right = right;
        }
    }

    @Override
    public Position<E> left(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        return node.getLeft();
    }



    @Override
    public Position<E> right(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        return node.getRight();
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        return left(v)!=null;
    }

    @Override
    public boolean hasRight(Position<E> v) {
        return right(v)!=null;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return !(isLeaf(v)||isRoot(v));
    }

    @Override
    public boolean isLeaf(Position<E> p) {
        return !hasLeft(p)&&!hasRight(p);
    }

    @Override
    public boolean isRoot(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return node.getParent()==null;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public E replace(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        E element = node.getElement();
        node.setElement(e);
        return element;
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        BTNode<E> parent = node.getParent();
        if(parent==null||!hasRight(parent)||!hasLeft(parent)){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        else{
            BTNode<E>left = parent.getLeft();
            BTNode<E>right = parent.getRight();
            if(left.equals(node)) return right;
            else return left;
        }
    }

    @Override
    public Position<E> addRoot(E e) {
        if (root!=null) try {
            throw new Exception();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        root = new BTNode<>(e, null, null, null);
        return root;
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        BTNode<E> parent = checkPosition(p);
        BTNode<E> node = new BTNode<>(e, parent, null, null);
        parent.setLeft(node);
        return node;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        BTNode<E> parent = checkPosition(p);
        BTNode<E> node = new BTNode<>(e, parent, null, null);
        parent.setRight(node);
        return node;
    }

    @Override
    public E remove(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        E element = node.getElement();
        BTNode<E> parent = node.getParent();
        node.setParent(null);
        if (parent==null){
            if(node.equals(root)) root=null;
        }
        else{
            if(parent.getRight().equals(node)){
                parent.setRight(null);
            }
            else{
                parent.setLeft(null);
            }
        }
        return element;
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        BTNode<E> n1 = checkPosition(p1);
        BTNode<E> n2 = checkPosition(p2);
        E element = n1.getElement();
        n1.setElement(n2.getElement());
        n2.setElement(element);
    }

    @Override
    public void attachLeft(Position<E> h, BinaryTree<E> t1) {
        BTNode<E>node = checkPosition(h);
        BTNode<E> left = checkPosition(t1.root());
        node.setLeft(node);
        left.setParent(node);
    }

    @Override
    public void attachRight(Position<E> h, BinaryTree<E> t1) {
        BTNode<E>node = checkPosition(h);
        BTNode<E> right = checkPosition(t1.root());
        node.setRight(node);
        right.setParent(node);
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        return node.getParent();
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        List<Position<E>> list = new LinkedList<>();
        if(hasLeft(v)){
            list.add(node.getLeft());
        }
        if(hasRight(v)){
            list.add((node.getRight()));
        }
        return list;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new BFSIterator<E>(this);
    }

    @Override
    public LinkedBinaryTree<E> subTree(Position<E> h) {
        BTNode<E> node = checkPosition(h);
        remove(node);
        node.setParent(null);
        LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();
        tree.root=node;
        return tree;
    }

    private BTNode<E> checkPosition(Position<E> v) {
        return null;
    }
   
}
