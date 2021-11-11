
package material.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.narytree.NAryTree;

/**
 *
 * @author mayte
 * @param <T>
 */
public class MoreFunctionality<T> {
  
        
    /**
     * This method recives a NArytree and returns a List with the elements of the 
     * tree that can be seen if the tree is viewed from the left side.
     * @param tree
     * @return 
     */
    public List<T> leftView(NAryTree<T> tree){
        List<T> list = new LinkedList<>();
        if(tree.root()!=null) {
            int level = 0;
            int lastAdd = -1;
            Queue<Position<T>> queue = new LinkedList<>();
            queue.add(tree.root());
            queue.add(null);
            while (queue.size() > 1) {
                Position<T> pos = queue.poll();
                if (pos == null) {
                    level++;
                    queue.add(null);
                } else {
                    if (lastAdd < level) {
                        list.add(pos.getElement());
                        lastAdd = level;
                    }
                    for (Position<T> p : tree.children(pos)) {
                        queue.add(p);
                    }
                }
            }
        }
        return list;
    }

   /**
     * This method recives a NArytree and returns a List with the elements of the 
     * tree that can be seen if the tree is viewed from the right side.
     * @param tree
     * @return 
     */
    public List<T> rightView(NAryTree<T> tree){
        List<T> list = new LinkedList<>();
        if(tree.root()!=null) {
            T last = null;
            Queue<Position<T>> queue = new LinkedList<>();
            queue.add(tree.root());
            queue.add(null);
            while (queue.size() > 1) {
                Position<T> pos = queue.poll();
                if (pos == null) {
                    list.add(last);
                    queue.add(null);
                } else {
                    last = pos.getElement();
                    for (Position<T> p : tree.children(pos)) {
                        queue.add(p);
                    }
                }
            }
            list.add(last);
        }
        return list;
    }
}
