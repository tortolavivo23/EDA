package ejercicio3;
import material.DLinkedNode;
import java.util.Iterator;

public class MyIterator<E> implements Iterator<E>{
    private DLinkedNode<E> node;

    public MyIterator(DLinkedNode<E> node) {
        this.node = node;
    }

    @Override
    public boolean hasNext() {
        return node!=null;
    }

    @Override
    public E next() {
        E out = node.getElement();
        node = node.getNext();
        return out;
    }

    @Override
    public void remove() {
        node = node.getNext();
    }

}
