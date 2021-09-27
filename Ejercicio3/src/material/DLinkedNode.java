package material;

public class DLinkedNode<E> implements Position{
    private DLinkedNode<E> next, prev;
    E element;


    public DLinkedNode(DLinkedNode<E> next, DLinkedNode<E> prev, E element) {
        this.next = next;
        this.prev = prev;
        this.element = element;
    }


    public DLinkedNode<E> getNext() {
        return next;
    }

    public void setNext(DLinkedNode<E> next) {
        this.next = next;
    }

    public DLinkedNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DLinkedNode<E> prev) {
        this.prev = prev;
    }

    @Override
    public E getElement() {
        return element;
    }


    public void setElement(E element) {
        this.element = element;
    }
}
