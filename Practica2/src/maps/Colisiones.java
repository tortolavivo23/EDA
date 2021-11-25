package maps;

public class Colisiones {
    public static void main(String[] args) {
        int lp=0;
        int qp=0;
        int dh =0;
        for (int t = 0; t < 20; t++) {
            HashTableMapLP<Integer, Integer> hashTableMapLP = new HashTableMapLP<>(15000);
            HashTableMapQP<Integer, Integer> hashTableMapQP = new HashTableMapQP<>(15000);
            HashTableMapDH<Integer, Integer> hashTableMapDH = new HashTableMapDH<>(15000);
            for (int i = 0; i < 10000; i++) {
                hashTableMapDH.put(i, i);
                hashTableMapLP.put(i, i);
                hashTableMapQP.put(i, i);
            }
            System.out.println("1: LP: " + hashTableMapLP.getColisiones() + " QP: " + hashTableMapQP.getColisiones() + " DH: " + hashTableMapDH.getColisiones());
            for (int i = 10000; i < 1000000; i++) {
                hashTableMapDH.put(i, i);
                hashTableMapLP.put(i, i);
                hashTableMapQP.put(i, i);
            }
            System.out.println("2: LP: " + hashTableMapLP.getColisiones() + " QP: " + hashTableMapQP.getColisiones() + " DH: " + hashTableMapDH.getColisiones());
            lp+=hashTableMapLP.getColisiones();
            qp+=hashTableMapQP.getColisiones();
            dh+=hashTableMapDH.getColisiones();
        }
        System.out.println("3: LP: " +lp+ " QP: " + qp + " DH: " + dh);

    }
}
