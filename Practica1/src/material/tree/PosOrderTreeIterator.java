package material.tree;

import material.Position;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class PosOrderTreeIterator<T> implements Iterator<Position<T>> {
    Tree<T> tree;
    Stack<Position<T>> stack = new Stack<>();
    HashSet<Position<T>> mark = new HashSet<>();

       
    public PosOrderTreeIterator(Tree<T> tree) {
        this.tree = tree;
        stack.add(tree.root());
    }

    public PosOrderTreeIterator(Tree<T> tree, Position<T> root) {
        this.tree = tree;
        stack.add(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a pos-order
     */
    @Override
    public Position<T> next() {
        Position<T> position = stack.pop();
        while (mark.add(position)){
            stack.add(position);
            Stack<Position<T>> stackAux = new Stack<>();
            for(Position<T> k: tree.children(position)){
                stackAux.add(k);
            }
            while(!stackAux.isEmpty()){
                stack.add((stackAux.pop()));
            }
            position= stack.pop();
        }
        return position;
    }

}
