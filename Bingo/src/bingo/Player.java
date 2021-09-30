/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import utils.*;

/**
 *
 * @author jose.velez
 */
class Player {

    private Card [] cards;
    public Player() {
        NumberGetter n = new NumberGetter();
        int numCards = n.getNumber("Number of cards:");
        cards = new Card[numCards];
        for (int i = 0; i < numCards; i++) {
            cards[i] = new Card();
        }
    }

    void removeNumber(int number) {
       for(int i= 0; i<cards.length; i++){
           cards[i].removeNumber(number);
       }
    }

    boolean hasLine() {
        int i=0;
        boolean line = false;
        while (i<cards.length && !line){
            line = line || cards[i].hasLine();
            i++;
        }
        return line;
    }

    boolean hasBingo() {
        int i=0;
        boolean bingo = false;
        while (i<cards.length && !bingo){
            bingo = bingo || cards[i].hasBingo();
            i++;
        }
        return bingo;
    }
    
}
