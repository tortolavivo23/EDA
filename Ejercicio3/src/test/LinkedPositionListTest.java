package test;

import ejercicio3.InvalidPositionException;
import ejercicio3.LinkedPositionList;
import ejercicio3.MyListBetter;
import material.DLinkedNode;
import material.Position;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LinkedPositionListTest{

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
    public static MyListBetter<Double> inicializar(){
        MyListBetter<Double> list = new LinkedPositionList<>();
        list.add(3.2);
        list.add(2.1);
        list.add(4.2);
        return list;
    }

    @org.junit.jupiter.api.Test
    void size() {
        MyListBetter<Double> list = new LinkedPositionList<>();
        assertEquals(0, list.size());
        list.add(3.2);
        assertEquals(1, list.size());
        list.add(1.0);
        assertEquals(2, list.size());
    }

    @org.junit.jupiter.api.Test
    void isempty() {
        MyListBetter<Double> list = new LinkedPositionList<>();
        assertTrue(list.isempty());
        list.add(1.0);
        assertFalse(list.isempty());
    }

    @org.junit.jupiter.api.Test
    void add() {
        MyListBetter<Double> list = inicializar();
        assertEquals(3, list.size());
    }

    @org.junit.jupiter.api.Test
    void get() {
        MyListBetter<Double> list = new LinkedPositionList<>();
        list.add(3.2);
        assertEquals(3.2, list.get().getElement());
        list.add(2.1);
        assertEquals(2.1, list.get().getElement());
        list.add(4.2);
        assertEquals(4.2, list.get().getElement());
    }

    @org.junit.jupiter.api.Test
    void search() {
        MyListBetter<Double> list = inicializar();
        assertEquals(null, list.search(1.0));
        assertEquals(4.2, list.search(4.2).getElement());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        MyListBetter<Double> list = inicializar();
        assertTrue(list.contains(4.2));
        assertFalse(list.contains(1.0));
    }

    @org.junit.jupiter.api.Test
    void iterator() {
        MyListBetter<Double> list = inicializar();
        Iterator<Double> it = list.iterator();
        double comp = 0;
        while (it.hasNext()){
            comp = it.next();
        }
        assertEquals(3.2, comp);
    }

    @org.junit.jupiter.api.Test
    void set() throws InvalidPositionException {
        MyListBetter<Double> list= inicializar();
        Position<Double> pos = list.get();
        list.set(pos, 5.0);
        assertEquals(5.0, list.get().getElement());
    }

    @org.junit.jupiter.api.Test
    void remove() throws InvalidPositionException {
        MyListBetter<Double> list = inicializar();
        Position<Double> pos = list.get();
        assertEquals(4.2, list.remove(pos));
        assertEquals(2.1, list.get().getElement());
        assertEquals(2, list.size());
        MyListBetter<Integer> list1 = new LinkedPositionList<>();
        Position<Integer> pos1 = list1.add(3);
        assertEquals(3, list1.remove(pos1));
    }

    @org.junit.jupiter.api.Test
    void addBefore() throws InvalidPositionException {
        MyListBetter<Double>list = inicializar(); //[4.2, 2.1, 3.2]
        Position<Double> p0 = list.get();
        Position<Double> p1 = list.add(3.1); //[3.1, 4.2, 2.1, 3.2]
        Position<Double> p2 = list.addBefore(p0, 6.9); //[3.1, 6.9, 4.2, 2.1, 3.2]
        list.remove(p1); //[6.9, 4.2, 2.1, 3.2]
        assertEquals(6.9, list.get().getElement());
        list.addBefore(p2, 1.2); //[1.2, 6.9, 4.2, 2.1, 3.2]
        assertEquals(1.2, list.get().getElement());
    }

    @org.junit.jupiter.api.Test
    void addAfter() throws InvalidPositionException {
        MyListBetter<Double>list = inicializar(); //[4.2, 2.1, 3.2]
        Position<Double> p = list.get();
        list.addAfter(p, 6.9); //[4.2, 6.9, 2.1, 3.2]
        list.remove(p); //[6.9, 2.1, 3.2]
        assertEquals(6.9, list.get().getElement());
    }
}