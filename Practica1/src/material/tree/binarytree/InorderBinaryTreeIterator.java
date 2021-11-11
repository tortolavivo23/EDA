
package material.tree.binarytree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import material.Position;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class InorderBinaryTreeIterator<T> implements Iterator<Position<T>> {
    BinaryTree<T> tree;
    HashSet<Position<T>> mark = new HashSet<Position<T>>();
    Stack<Position<T>> stack = new Stack<>();
       
    public InorderBinaryTreeIterator(BinaryTree <T> tree) {
        this.tree = tree;
        stack.add(tree.root());
    }

    public InorderBinaryTreeIterator(BinaryTree <T> tree, Position<T> node) {
        this.tree=tree;
        stack.add(node);
    }

         
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * This method visits the nodes of a binary tree first left-child, then the node and at last right-child
     */
    @Override
    public Position<T> next() {
        Position<T> position = stack.pop();
        while(mark.add(position)){
            if(tree.hasRight(position)){
                stack.add(tree.right(position));
            }
            stack.add(position);
            if(tree.hasLeft(position)){
                stack.add(tree.left(position));
            }
            position = stack.pop();
        }
        return position;
    }

    
}
