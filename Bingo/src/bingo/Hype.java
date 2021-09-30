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
class Hype {
    private HashSet<Integer> hype;
    private final int n = 90;

    public Hype() {
        hype= new HashSet<>();
        hype.add(0);
    }

    boolean hasNumbers() {
        return hype.size()<=n;
    }

    int getNumber() {
        int n=0;
        while (!hype.add(n)){
            n = ((int)(Math.random()*90))+1;
        }
        return n;
    }
    
}
