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
class Game {

    private final Hype hype;
    private Player[] players;

    public Game() {
        hype = new Hype();
        createPlayers();
    }

    void play() {
        boolean lineDetected = false;
        boolean bingo = false;
        while (hype.hasNumbers()&&!bingo) {
            int number = hype.getNumber();
            for (int i = 0; i < players.length; i++) {
                players[i].removeNumber(number);
                if (!lineDetected) {
                    if (players[i].hasLine()) {
                        System.out.println("Line detected "+i);
                        lineDetected = true;
                    }
                }
                if (players[i].hasBingo()) {
                    System.out.println("Bingo detected "+i);
                    bingo=true;
                    break;
                }

            }
        }
        
    }

    private void createPlayers() {
        NumberGetter n = new NumberGetter();
        final int numPlayers = n.getNumber("Number of players:");
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player();
        }
        
    }


}
