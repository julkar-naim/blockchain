package blockchain;

import java.util.List;

class A {
    public List<Integer> call() {
        return List.of(1, 2, 3);
    }
}

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        for (int i = 0; i < 5; i++) {
            blockchain.createNewBlock();
        }
        System.out.print(blockchain.toString());
    }
}
