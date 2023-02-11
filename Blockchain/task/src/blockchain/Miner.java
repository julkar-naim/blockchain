package blockchain;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Miner implements Runnable, Subscriber {

    private volatile boolean running;
    private AtomicReference<Blockchain> blockchainRef = new AtomicReference<>();

    public Miner(Blockchain blockchain) {
        this.blockchainRef.set(blockchain);
        running = true;
    }

    @Override
    public void update() {
        this.running = false;
    }

    private Block createNewBlock() {
        Blockchain chain = blockchainRef.get();
        Block block = new Block();
        block.setId(chain.getLastBlockId() + 1);
        block.setCreatedBy(Thread.currentThread().getName().split("-")[1]);
        block.setTimestamp(new Date().getTime());
        block.setPreviousHash(chain.getLastBlockHash());
        return block;
    }

    @Override
    public void run() {
        Block block = createNewBlock();
        Random random = new Random();
        int N = blockchainRef.get().getN();
        String hash = "";
        Instant startTime = Instant.now();

        do {
            int magicNumber = random.nextInt();
            block.setMagicNumber(magicNumber);
            hash = StringUtil.blockToHash(block);
            block.setHash(hash);
        }while(this.running && !StringUtil.isValidN(hash, N));

        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        block.setTimeTaken(duration.toSeconds());

        if (this.running) blockchainRef.get().acceptBlock(block);
    }
}
