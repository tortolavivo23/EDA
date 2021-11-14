
package material.tree;

import material.Position;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class PreOrderTreeIterator<T> implements Iterator<Position<T>> {

    Tree<T> tree;
    Queue<Position<T>> queue = new LinkedList<>();

    public PreOrderTreeIterator(Tree<T> tree) {
        this.tree = tree;
        if(!tree.isEmpty()) queue.add(tree.root());
    }

    public PreOrderTreeIterator(Tree<T> tree, Position<T> root) {
        this.tree = tree;
        queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a pre-order
     */
    @Override
    public Position<T> next() {
        Position<T> position = queue.poll();
        Queue<Position<T>> auxQueue = new LinkedList<>();
        for(Position<T>k : tree.children(position)){
            auxQueue.add(k);
        }
        for(Position<T> k : queue){
            auxQueue.add(k);
        }
        queue = auxQueue;
        return position;
    }

}
