package blockchain;

import java.util.List;
import java.util.Scanner;

class A {
    public List<Integer> call() {
        return List.of(1, 2, 3);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter how many zeros the hash must start with: ");
        Scanner scanner = new Scanner(System.in);
        int prefixLength = scanner.nextInt();
        Blockchain blockchain = new Blockchain(prefixLength);
        for (int i = 0; i < 5; i++) {
            blockchain.createNewBlock();
        }
        System.out.println(blockchain.toString());
    }
}
