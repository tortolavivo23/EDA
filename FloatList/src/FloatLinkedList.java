/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayte
 */
public class FloatLinkedList implements FloatList{
    private class Node{
        Node next;
        float element;

        public Node(float e){
            element = e;
        }
        public Node(Node n, float e){
            next=n;
            element = e;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public float getElement() {
            return element;
        }

        public void setElement(float element) {
            this.element = element;
        }
    }
    Node head;
    int size;

    public FloatLinkedList(){
        size=0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isempty() {
        return size==0;
    }

    @Override
    public void add(Float value) {
        head = new Node(head, value);
        size++;
    }

    @Override
    public void add(int index, Float value) {
        if (index == 1) {
            add(value);
        } else {
            Node node = this.head;
            for (int i = 1; i < index; i++) {
                node = node.getNext();
            }
            node.setNext(new Node(node.next, value));
            size++;
        }
    }

    @Override
    public Float remove() {
        float out = head.getElement();
        head = head.getNext();
        size--;
        return out;
    }

    @Override
    public Float remove(int index) {
        if(index==1){
            return remove();
        }
        else{
            Node node = this.head;
            for(int i=1; i<index-1; i++){
                node = node.getNext();
            }
            Float out = node.getNext().getElement();
            node.setNext(node.getNext().getNext());
            size--;
            return out;
        }
    }

    @Override
    public Float get() {
        return head.getElement();
    }

    @Override
    public Float get(int index) {
        Node node = head;
        for(int i=1; i<index; i++){
            node = node.getNext();
        }
        return node.getElement();
    }

    @Override
    public int search(Float value) {
        Node node = head;
        for (int i=1; i<=size; i++){
            if (node.getElement()==value){
                return i;
            }
            else{
                node = node.getNext();
            }
        }
        return 0;
    }

    @Override
    public boolean contains(Float value) {
        return search(value)>0;
    }
    
}
