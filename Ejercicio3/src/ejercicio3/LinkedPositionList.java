package ejercicio3;

import material.DLinkedNode;
import material.Position;

import java.util.Iterator;

public class LinkedPositionList<E> implements MyListBetter<E>{
    private Position<E> head;
    private int size;

    public LinkedPositionList(){}

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isempty() {
        return size==0;
    }

    @Override
    public Position<E> add(E value) {
        DLinkedNode<E> nextNode = (DLinkedNode<E>)head;
        DLinkedNode<E> newNode = new DLinkedNode<E>(nextNode, null, value);
        head= newNode;
        if(nextNode!=null) {
            nextNode.setPrev(newNode);
        }
        size++;
        return head;
    }

    @Override
    public Position<E> get() {
        return head;
    }

    @Override
    public Position<E> search(E value) {
        DLinkedNode<E> node = (DLinkedNode<E>) head;
        while (node!=null){
            if(node.getElement().equals(value)){
                return node;
            }
            else{
                node=node.getNext();
            }
        }
        return null;
    }

    @Override
    public boolean contains(E value) {
        return search(value)!=null;
    }

    @Override
    public Iterator<E> iterator() {
        try {
            return new MyIterator<E>(this);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Position<E> set(Position<E> pos, E value) throws InvalidPositionException {
        DLinkedNode<E> node = checkPosition(pos);
        node.setElement(value);
        return node;
    }

    @Override
    public E remove(Position pos) throws InvalidPositionException {
        DLinkedNode<E>node = checkPosition(pos);
        DLinkedNode<E>next = node.getNext();
        if(node == head){
            head = next;
        }
        else{
            DLinkedNode<E>prev = node.getPrev();
            prev.setNext(next);
        }
        if(next!=null) {
            next.setPrev(node.getPrev());
        }
        size--;
        return node.getElement();
    }

    @Override
    public Position<E> addBefore(Position<E> pos, E value) throws InvalidPositionException {
        DLinkedNode<E> nextNode = checkPosition(pos);
        DLinkedNode<E> newNode = new DLinkedNode<>(nextNode, nextNode.getPrev(), value);
        if(nextNode == head){
            head = newNode;
        }
        else{
            nextNode.getPrev().setNext(newNode);
        }
        nextNode.setPrev(newNode);
        size++;
        return newNode;
    }

    @Override
    public Position<E> addAfter(Position<E> pos, E value) throws InvalidPositionException {
        DLinkedNode<E> prevNode = checkPosition(pos);
        DLinkedNode<E> newNode = new DLinkedNode<>(prevNode.getNext(), prevNode, (E)value);
        prevNode.setNext(newNode);
        size++;
        return newNode;
    }

    protected DLinkedNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
        if(p==null || !(p instanceof DLinkedNode)){
            throw new InvalidPositionException("The position is invalid");
        }
        return (DLinkedNode<E>) p;
    }
}
