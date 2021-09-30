package ejercicio3;
import material.DLinkedNode;
import java.util.Iterator;

public class MyIterator<E> implements Iterator<E>{
    private DLinkedNode<E> node;
    private LinkedPositionList<E> list;

    public MyIterator(LinkedPositionList<E> list) throws InvalidPositionException {
        this.list = list;
        this.node = list.checkPosition(list.get());
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
        try {
            list.remove(node);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

}
