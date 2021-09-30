/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.util.HashSet;

/**
 *
 * @author jose.velez
 */
class Card {
    private HashSet<Integer>[] card;
    public Card(){
        card = new HashSet[3];
        HashSet<Integer> aux = new HashSet<>();
        for(int i=0; i<3; i++){
            card[i] = new HashSet<>();
        }
        aux.add(0);
        for(int i=0; i<3; i++){
            for (int j=0; j<5; j++){
                int n=0;
                while (!aux.add(n)){
                    n = ((int)(Math.random()*90))+1;
                }
                card[i].add(n);
            }
        }
    }
    public void removeNumber(int number){
        for(int i=0; i<3; i++){
            card[i].remove(number);
        }
    }

    public boolean hasLine() {
        for (int i=0; i<3; i++){
            if(card[i].size()==0){
                return true;
            }
        }
        return false;
    }

    public boolean hasBingo(){
         boolean bingo = true;
         for(int i=0; i<3; i++){
             bingo &= card[i].size()==0;
         }
         return bingo;
    }
}
