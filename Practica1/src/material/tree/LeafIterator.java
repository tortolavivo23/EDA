
package material.tree;

import java.util.Iterator;
import java.util.Stack;

import material.Position;

/**
 *
 * @author mayte
 * @param <T>
 */
public class LeafIterator<T> implements Iterator<Position<T>>  {
    
    Tree<T> tree;
    Stack<Position<T>> stack = new Stack<>();

    public LeafIterator(Tree<T> tree, Position<T> root){
        this.tree = tree;
        stack.add(root);
    }
    
    public LeafIterator(Tree<T> tree){
        this.tree = tree;
        stack.add(tree.root());
    }
    
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * This method only visits the leaf nodes 
     */
    @Override
    public Position<T> next() {
        Position<T> pos = stack.pop();
        while(!tree.isLeaf(pos)){
            Stack<Position<T>> aux = new Stack<>();
            for(Position<T> k : tree.children(pos)){
                aux.add(k);
            }
            while(!aux.isEmpty()){
                stack.add(aux.pop());
            }
            pos = stack.pop();
        }
        return pos;
    }

    
    
}
