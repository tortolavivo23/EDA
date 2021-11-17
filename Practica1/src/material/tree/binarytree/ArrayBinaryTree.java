
package material.tree.binarytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import material.Position;
import material.tree.BreadthFirstTreeIterator;
import material.tree.PosOrderTreeIterator;
import material.tree.PreOrderTreeIterator;

/**
 *
 * @author mayte
 * @param <E>
 */
public class ArrayBinaryTree<E> implements BinaryTree<E> {

    Position<E>[] tree;
    private int cap,size;

    private class BTNode<T> implements Position<T>{
        T element;
        int index;

        public BTNode(T element, int index) {
            this.element = element;
            this.index = index;
        }

        @Override
        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public ArrayBinaryTree(){
        this(100);
    }
    public ArrayBinaryTree(int cap){
        this.cap = cap;
        tree = new Position[cap];
        size=0;
    }


    @Override
    public Position<E> left(Position<E> v) {
        BTNode<E> parent = checkPosition(v);
        int left_index = 2* parent.index+1;
        if(left_index<cap&&tree[left_index]!=null){
            return tree[left_index];
        }
        else{
            throw new RuntimeException("Left does not exist");
        }
    }

    @Override
    public Position<E> right(Position<E> v) {
        BTNode<E> parent = checkPosition(v);
        int right_index = 2* parent.index+2;
        if(right_index<cap&&tree[right_index]!=null){
            return tree[right_index];
        }
        else{
            throw new RuntimeException("Right does not exist");
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
        return !isLeaf(v);
    }

    @Override
    public boolean isLeaf(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        int l = node.getIndex()*2+1;
        int r = l+1;
        return l>=cap|| (tree[l]==null && r<cap && tree[r]==null);
    }

    @Override
    public boolean isRoot(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return node.getIndex()==0;
    }

    @Override
    public Position<E> root() {
        if(tree[0]==null){
            throw new RuntimeException("No Root");
        }
        else{
            return tree[0];
        }
    }

    @Override
    public E replace(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        E out = p.getElement();
        node.setElement(e);
        return out;
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        int ind = node.getIndex();
        int sib_ind = 0;
        if(ind%2==0){
            sib_ind = ind-1;
        }
        else{
            sib_ind =ind+1;
        }
        if(ind==0 ||sib_ind>=cap || tree[sib_ind]==null){
            throw new RuntimeException("No sibling");
        }
        return tree[sib_ind];
    }

    @Override
    public Position<E> addRoot(E e) {
        if(tree[0]!=null){
            throw new RuntimeException("Root already exists");
        }
        tree[0] = new BTNode<>(e, 0);
        size++;
        return tree[0];
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        if(hasLeft(p)){
            throw new RuntimeException("Left exists");
        }
        BTNode<E> parent = checkPosition(p);
        int ind = parent.getIndex()*2+1;
        if(ind>=cap){
            throw new RuntimeException("Full tree");
        }
        tree[ind] = new BTNode<>(e, ind);
        size++;
        return tree[ind];
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        if(hasRight(p)){
            throw new RuntimeException("Right exists");
        }
        BTNode<E> parent = checkPosition(p);
        int ind = parent.getIndex()*2+2;
        if(ind>=cap){
            throw new RuntimeException("Full tree");
        }
        tree[ind] = new BTNode<>(e, ind);
        size++;
        return tree[ind];
    }

    @Override
    public E remove(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        if(!isLeaf(node)){
            throw new RuntimeException("Its not leaf");
        }
        E out = node.getElement();
        tree[node.getIndex()]=null;
        size--;
        return out;
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        BTNode<E> n1 = checkPosition(p1);
        BTNode<E> n2 = checkPosition(p2);
        E aux1 = n1.getElement();
        n1.setElement(n2.getElement());
        n2.setElement(aux1);
    }

    @Override
    public void attachLeft(Position<E> h, BinaryTree<E> t1) {
        BTNode<E> node = checkPosition(h);
        Iterator<Position<E>> iterator = new BreadthFirstTreeIterator<>(t1);
        Queue<Position<E>> nodes = new LinkedList<>();
        Queue<Position<E>>new_nodes = new LinkedList<>();
        if(iterator.hasNext()){
            Position<E> parent = iterator.next();
            Position<E> p = insertLeft(node, parent.getElement());
            nodes.add(parent);
            new_nodes.add(p);
        }
        while (iterator.hasNext()&&!nodes.isEmpty()){
            Position<E> parent = nodes.poll();
            Position<E> p = new_nodes.poll();
            if(t1.hasLeft(parent)){
                Position<E> left = iterator.next();
                new_nodes.add(insertLeft(p, left.getElement()));
                nodes.add(left);
            }
            if(t1.hasRight(parent)){
                Position<E> right = iterator.next();
                new_nodes.add(insertRight(p, right.getElement()));
                nodes.add(right);
            }
        }
    }

    @Override
    public void attachRight(Position<E> h, BinaryTree<E> t1) {
        BTNode<E> node = checkPosition(h);
        Iterator<Position<E>> iterator = new BreadthFirstTreeIterator<>(t1);
        Queue<Position<E>> nodes = new LinkedList<>();
        Queue<Position<E>>new_nodes = new LinkedList<>();
        if(iterator.hasNext()){
            Position<E> parent = iterator.next();
            Position<E> p = insertRight(node, parent.getElement());
            nodes.add(parent);
            new_nodes.add(p);
        }
        while (iterator.hasNext()&&!nodes.isEmpty()){
            Position<E> parent = nodes.poll();
            Position<E> p = new_nodes.poll();
            if(t1.hasLeft(parent)){
                Position<E> left = iterator.next();
                new_nodes.add(insertLeft(p, left.getElement()));
                nodes.add(left);
            }
            if(t1.hasRight(parent)){
                Position<E> right = iterator.next();
                new_nodes.add(insertRight(p, right.getElement()));
                nodes.add(right);
            }
        }
    }

    @Override
    public BinaryTree<E> subTree(Position<E> h) {
        BTNode<E> node = checkPosition(h);
        ArrayBinaryTree<E> out = new ArrayBinaryTree<>();
        Iterator<Position<E>> iterator = new BreadthFirstTreeIterator<>(this, node);
        Queue<Position<E>> nodes = new LinkedList<>();
        Queue<Position<E>>new_nodes = new LinkedList<>();
        if(iterator.hasNext()){
            Position<E> parent = iterator.next();
            Position<E> p = out.addRoot(parent.getElement());
            nodes.add(parent);
            new_nodes.add(p);
        }
        while (iterator.hasNext()&&!nodes.isEmpty()){
            Position<E> parent = nodes.poll();
            Position<E> p = new_nodes.poll();
            if(hasLeft(parent)){
                Position<E> left = iterator.next();
                new_nodes.add(out.insertLeft(p, left.getElement()));
                nodes.add(left);
            }
            if(hasRight(parent)){
                Position<E> right = iterator.next();
                new_nodes.add(out.insertRight(p, right.getElement()));
                nodes.add(right);
            }
        }
        return out;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        int ind = (node.getIndex()-1)/2;
        if(isRoot(node)||tree[ind]==null){
            throw new RuntimeException("No parent");
        }
        return tree[ind];
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        int l = node.getIndex()*2+1;
        int r = l+1;
        LinkedList<Position<E>> list = new LinkedList<>();
        if(l<cap&&tree[l]!=null){
            list.addLast(tree[l]);
        }
        if(r<cap&&tree[r]!=null){
            list.addLast(tree[r]);
        }
        return list;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new InorderBinaryTreeIterator<>(this);
    }

    private BTNode<E> checkPosition(Position<E> p){
        if(p==null||!(p instanceof BTNode<E>)){
            throw new RuntimeException("Invalid Position");
        }
        return (BTNode<E>) p;
    }
    
}
