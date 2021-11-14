
package practica1;


/**
 *
 * @author mayte
 */
public class FendTree {

    private int[] biTree;
    
    /**
     * Builds a Fendwick Tree with the array that receives
     * 
     * @param array
     */
    public FendTree(int [] array){
        biTree = new int[array.length+1];
        for(int i=1; i<= array.length; i++){
            int k = i-1;
            int parent = i-(i&(-i));
            int s = array[i-1];
            while (k!=parent){
                s+=biTree[k];
                k = k-(k&(-k));
            }
            biTree[i]=s;
        }
    }
    
    /**
     * Receives an index and returns the partial sum from zero to that index.
     * An exception is thrown if the index is less than zero or greater than or equal to n.
     * @param index
     * @return 
     */
    public int getSum(int index){
        check(index);
        int x = index+1;
        int s = 0;
        while(x!=0){
            s+=biTree[x];
            x = x-(x&(-x));
        }
        return s;
    }
 
    /**
     * Update the value of the position index in the array.
     * 
     * @param index
     * @param val
     */
    public void upDate (int index, int val) {
        check(index);
        int x = index+1;
        while(x<biTree.length){
           biTree[x]+=val;
           x = x+(x&(-x));
        }
    }
    private void check(int index){
        if(index<0||index>=biTree.length-1){
            throw new RuntimeException("Index "+index+" out of bounds for length "+(biTree.length-1));
        }
    }
    
}