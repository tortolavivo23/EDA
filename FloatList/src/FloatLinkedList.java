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
    float cabeza;
    int size;
    FloatLinkedList cola;

    public FloatLinkedList(){
        size=0;
    }
    private FloatLinkedList(FloatLinkedList list){
        cabeza=list.cabeza;
        size=list.size;
        cola = list.cola;
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
        this.cola= new FloatLinkedList(this);
        this.cabeza = value;
        size++;
    }

    @Override
    public void add(int index, Float value) {
        FloatLinkedList list = this;
        for(int i=1; i<=index; i++){
            list = list.cola;
        }
        list.cola = new FloatLinkedList(list);
        list.cabeza = value;
        size++;
    }

    @Override
    public Float remove() {
        FloatLinkedList list = this.cola;
        Float out = cabeza;
        cabeza = list.cabeza;
        cola = list.cola;
        size--;
        return out;
    }

    @Override
    public Float remove(int index) {
        FloatLinkedList list = this;
        for(int i=1; i<index; i++){
            list= list.cola;
        }
        FloatLinkedList list1 = list.cola;
        Float out = list.cabeza;
        list.cabeza = list1.cabeza;
        list.cola = list1.cola;
        size--;
        return out;
    }

    @Override
    public Float get() {
        return cabeza;
    }

    @Override
    public Float get(int index) {
        FloatLinkedList list = this;
        for(int i=1; i<index; i++){
            list = list.cola;
        }
        return list.cabeza;
    }

    @Override
    public int search(Float value) {
        FloatLinkedList list = this;
        for (int i=1; i<=size; i++){
            if (list.cabeza==value){
                return i;
            }
            else{
                list = list.cola;
            }
        }
        return 0;
    }

    @Override
    public boolean contains(Float value) {
        FloatLinkedList list = this;
        for (int i=1; i<=size; i++){
            if (list.cabeza==value){
                return true;
            }
            else{
                list = list.cola;
            }
        }
        return false;
    }
    
}
