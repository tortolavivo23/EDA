import material.Position;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PreOrderIterator<E> implements Iterator<Position<E>> {

    List<Position<E>>list = new LinkedList<>();
    Tree<E> tree;

    public PreOrderIterator(Tree<E> tree){
        this(tree, tree.root());
    }
    public PreOrderIterator(Tree<E> tree, Position<E> root){
        this.tree= tree;
        if(root!=null) list.add(root);
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public Position<E> next() {
        Position<E> first = list.remove(0);
        LinkedList<Position<E>>aux = new LinkedList<>();
        for(Position<E> position : tree.children(first)){
            aux.add(position);
        }
        for(Position<E> position: aux){
            list.add(position);
        }
        return first;
    }
}
