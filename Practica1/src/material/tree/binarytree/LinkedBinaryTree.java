
package material.tree.binarytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import material.Position;
import material.tree.InvalidPositionException;

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
            return element;
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
        BTNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        BTNode<E> left = node.getLeft();
        if (left==null){
            throw new RuntimeException("Left does not exist");
        }
        else{
            return left;
        }
    }



    @Override
    public Position<E> right(Position<E> v) {
        BTNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        BTNode<E> right = node.getRight();
        if (right==null){
            throw new RuntimeException("Right does not exist");
        }
        else{
            return right;
        }
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        try {
            left(v);
            return true;
        }
        catch (RuntimeException e){
            return false;
        }
    }

    @Override
    public boolean hasRight(Position<E> v) {
        try {
            right(v);
            return true;
        }
        catch (RuntimeException e){
            return false;
        }
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
        BTNode<E> node = null;
        try {
            node = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid position");
        }
        return node.getParent()==null;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public E replace(Position<E> p, E e) {
        BTNode<E> node = null;
        try {
            node = checkPosition(p);
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Invalid position");
        }
        E element = node.getElement();
        node.setElement(e);
        return element;
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        BTNode<E> node = null;
        try {
            node = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid position");
        }
        BTNode<E> parent = node.getParent();
        if(parent==null||!hasRight(parent)||!hasLeft(parent)){
            throw new RuntimeException("No Sibling");
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
        if (root!=null) {
            throw new RuntimeException("Root already exists");
        }
        root = new BTNode<>(e, null, null, null);
        return root;
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        if (hasLeft(p)){
            throw new RuntimeException("Exists already a left node");
        }
        BTNode<E> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException ex) {
            throw new RuntimeException("Invalid position");
        }
        BTNode<E> node = new BTNode<>(e, parent, null, null);
        parent.setLeft(node);
        return node;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        if(hasRight(p)){
            throw new RuntimeException("Exists already a right node");
        }
        BTNode<E> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException ex) {
            throw new RuntimeException("Invalid position");
        }
        BTNode<E> node = new BTNode<>(e, parent, null, null);
        parent.setRight(node);
        return node;
    }

    @Override
    public E remove(Position<E> p) {
        BTNode<E> node = null;
        try {
            node = checkPosition(p);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        if(hasRight(node)||hasLeft(node)){
            throw new RuntimeException("The node has a child");
        }
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
        BTNode<E> n1 = null;
        try {
            n1 = checkPosition(p1);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        BTNode<E> n2 = null;
        try {
            n2 = checkPosition(p2);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        E element = n1.getElement();
        n1.setElement(n2.getElement());
        n2.setElement(element);
    }

    @Override
    public void attachLeft(Position<E> h, BinaryTree<E> t1) {
        BTNode<E>node = null;
        try {
            node = checkPosition(h);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        if(hasLeft(node)){
            throw new RuntimeException("It already has left");
        }
        BTNode<E> left = null;
        try {
            left = checkPosition(t1.root());
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }

        node.setLeft(left);
        left.setParent(node);
    }

    @Override
    public void attachRight(Position<E> h, BinaryTree<E> t1) {
        BTNode<E>node = null;
        try {
            node = checkPosition(h);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        if(hasRight(node)){
            throw new RuntimeException("It already has right");
        }
        BTNode<E> right = null;
        try {
            right = checkPosition(t1.root());
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        node.setRight(right);
        right.setParent(node);
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        BTNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        BTNode<E> parent = node.getParent();
        if (parent==null){
            throw new RuntimeException("No parent found");
        }
        else{
            return parent;
        }
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        BTNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
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
        return new InorderBinaryTreeIterator<>(this);
    }

    @Override
    public LinkedBinaryTree<E> subTree(Position<E> h) {
        BTNode<E> node = null;
        try {
            node = checkPosition(h);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        BTNode<E> parent = node.getParent();
        if(parent==null){
            this.root=null;
        }
        else{
            if(parent.getLeft().equals(node)){
                parent.setLeft(null);
            }
            else if(parent.getRight().equals(node)){
                parent.setRight(null);
            }
            node.setParent(null);
        }
        LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();
        tree.root=node;
        return tree;
    }

    private BTNode<E> checkPosition(Position<E> v) throws InvalidPositionException {
        if(v==null || !(v instanceof BTNode<E>)){
            throw new InvalidPositionException("The position is invalid");
        }
        BTNode<E> n = (BTNode<E>) v;
        return n;
    }

}
