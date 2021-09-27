/**
 *
 * @author mayte
 */
public class LinkedList<E> implements List {

    private class Node<E>{
       
        private E elem;
        private Node next;

        public E getElem() {
            return elem;
        }

        public void setElem(E elem) {
            this.elem = elem;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
        
        public Node(E e, Node sig){
            this.elem = e;
            this.next = sig;
        }        
             
    }
    
    private Node forward(int index){
        if ((index < 1) || (index > (size + 1)))
            throw new RuntimeException("The index is out of limits.");
        Node ant = null;
        Node act = head;
        for (int i = 1; i < index; i++){
            ant = act;
            act = act.getNext();
        }
        return ant;
    }
    
    private int size;
    private Node head;
    
    public LinkedList(){
        this.size = 0;
        this.head = null;
    }
    
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isempty() {
        return (this.size == 0);
    }

    @Override
    public void add(Object value) {
        Node aux = new Node(value, head);
        this.head = aux;
        this.size++;
    }

     
    @Override
    public void add(int index, Object value) {
        Node ant = forward(index);
        if (ant == null)
            add(value);
        else{
            Node aux = new Node(value, ant.getNext());
            this.size++;
            ant.setNext(aux);
        }          
    }

    @Override
    public E remove() {
        if (this.isempty())
            throw new RuntimeException("It is not allowed remove elements from a empty list.");
        Node aux = head;
        size--;
        head = head.getNext();
        return (E) aux.getElem();
    }

    
    @Override
    public E remove(int index) {
        Node ant = forward(index);
        if (ant == null)
            return remove();
        else{
            this.size--;
            Node aux = ant.getNext();
            ant.setNext(aux.getNext()); 
            return (E) aux.getElem();
        }
    }

    @Override
    public E get() {
        if (this.isempty())
             throw new RuntimeException("The index is out of limits.");
        return (E) head.getElem();
    }

    @Override
    public E get(int index) {
        Node ant = forward(index+1);
        return (E) ant.getElem();
    }

    @Override
    public int search(Object value) {
        if (isempty())
            return 0;
        Node aux = head;
        int i = 1;
        while ((i < size) && (!aux.getElem().equals(value))){
            aux = aux.getNext();
            i++;
        }
        if ((i < size)||(aux.getElem().equals(value)))
            return i;
        return 0;
    }

    @Override
    public boolean contains(Object value) {
       return (search(value) != 0);
    }
    
}
