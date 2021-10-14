package Tree.BinaryTree;

import Tree.Tree;
import material.Position;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFSIterator<E> implements Iterator<Position<E>> {
    private Tree<E> tree;
    private Queue<Position<E>> queue = new LinkedList<>();

    public BFSIterator(Tree<E> t){
        this.tree= t;
        queue.add(tree.root());
    }

    @Override
    public boolean hasNext() {
        return (!queue.isEmpty());
    }

    @Override
    public Position<E> next() {
        Position<E>  p= queue.poll();
        for(Position<E> child : tree.children(p)){
            queue.add(child);
        }
        return p;
    }
}
