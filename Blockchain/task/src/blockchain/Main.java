package blockchain;


import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MinerService minerService = new MinerService(5, 5);
        minerService.start();
    }
}
