/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material.tree.binarysearchtree;

import java.util.*;

import material.Position;
import material.tree.PreOrderTreeIterator;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;

/**
 *
 * @author mayte
 * @param <E>
 */
public class LinkedBinarySearchTree<E> implements BinarySearchTree<E> {
    private BinaryTree<E> tree;
    private Comparator<E> comparator;
    private int size;
    public LinkedBinarySearchTree(){
        this(new DefaultComparator<>());
    }
    public LinkedBinarySearchTree(Comparator<E> comparator){
        tree = new LinkedBinaryTree<>();
        this.comparator = comparator;
        size=0;
    }
    @Override
    public Position<E> find(E value) {
        Position<E> out = null;
        if(!tree.isEmpty()){
            Position<E> act = tree.root();
            while (act.getElement().equals(value)&&!tree.isLeaf(act)&&act!=null){
                int comp = comparator.compare(value, act.getElement());
                if(comp > 0){
                    if(tree.hasLeft(act)){
                        act= tree.left(act);
                    }
                    else{
                        act = null;
                    }
                }
                else if(comp < 0){
                    if(tree.hasRight(act)){
                        act= tree.right(act);
                    }
                    else{
                        act = null;
                    }
                }
            }
            if(act!=null&&act.getElement().equals(value)){
                out = act;
            }
        }
        return out;
    }

    @Override
    public Iterable<? extends Position<E>> findAll(E value) {
        List<Position<E>> out = new LinkedList<>();
        if(!tree.isEmpty()){
            Position<E>act = tree.root();
            while (act!=null){
                int comp = comparator.compare(act.getElement(), value);
                if(comp>=0){
                    if(comp==0){
                        out.add(act);
                    }
                    if(tree.hasRight(act)){
                        act = tree.right(act);
                    }
                    else{
                        act = null;
                    }
                }
                else{
                    if(tree.hasLeft(act)){
                        act = tree.left(act);
                    }
                    else{
                        act = null;
                    }
                }
            }
        }
        return out;
    }

    @Override
    public Position<E> insert(E value) {
        Position<E> out = null;
        size++;
        if(tree.isEmpty()){
            out = tree.addRoot(value);
        }
        else{
            Position<E> act = tree.root();
            boolean inserted = false;
            while (!inserted){
                int comp = comparator.compare(act.getElement(), value);
                if(comp>=0){
                    if(tree.hasRight(act)){
                        act = tree.right(act);
                    }
                    else{
                        inserted = true;
                        out = tree.insertRight(act, value);
                    }
                }
                else{
                    if(tree.hasLeft(act)){
                        act = tree.left(act);
                    }
                    else{
                        inserted=true;
                        out = tree.insertLeft(act, value);
                    }
                }
            }
        }
        return out;
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void remove(Position<E> pos) {
        tree.remove(pos);
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<? extends Position<E>> rangeIterator(E m, E M) {
        List<Position<E>> out = new LinkedList<>();
        if(!tree.isEmpty()){
            Queue<Position<E>> queue = new LinkedList<>();
            queue.add(tree.root());
            while (!queue.isEmpty()){
                Position<E> k = queue.poll();
                int c1 = comparator.compare(k.getElement(), m);
                int c2 = comparator.compare(k.getElement(), M);
                if(c1>=0&&c2<=0){
                    out.add(k);
                }
                if(c1>=0){
                    if(tree.hasLeft(k)){
                        out.add(tree.left(k));
                    }
                }
                if(c2<=0){
                    if(tree.hasRight(k)){
                        out.add(tree.right(k));
                    }
                }
            }
        }
        return out;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new PreOrderTreeIterator<>(tree);
    }
    
}
