
package material.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import material.Position;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class BreadthFirstTreeIterator<T> implements Iterator<Position<T>> {

    Tree<T> tree;
    Queue<Position<T>> queue= new LinkedList<>();

    public BreadthFirstTreeIterator(Tree<T> tree, Position<T> root) {
        this.tree = tree;
        queue.add(root);

    }
    public BreadthFirstTreeIterator(Tree<T> tree) {
        this.tree=tree;
        if(!tree.isEmpty())
        queue.add(tree.root());
    }
    
    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a breath-first order
     */
    @Override
    public Position<T> next() {
        Position<T> position = queue.poll();
        for(Position<T> child : tree.children(position)){
            queue.add(child);
        }
        return position;
    }

   
}
