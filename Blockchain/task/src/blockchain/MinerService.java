package blockchain;

import java.util.ArrayList;
import java.util.List;

public class MinerService {
    private final Integer numOfThreads;
    private final Integer numOfBlocks;

    public MinerService(Integer numOfThreads, Integer numOfBlocks) {
        this.numOfThreads = numOfThreads;
        this.numOfBlocks = numOfBlocks;
    }

    private  List<Miner> getReadyThreads() {
        Blockchain blockchain = Blockchain.getBlockChain();
        var listOfThreads = getThreads(blockchain);
        BlockchainPublisher blockchainPublisher = new BlockchainPublisher();
        listOfThreads.forEach(blockchainPublisher::addSubscriber);
        blockchain.setBlockchainPublisher(blockchainPublisher);
        return listOfThreads;
    }

    public void start() throws InterruptedException {

        startAll(numOfBlocks);

        Blockchain.getBlockChain().print();
    }

    private void startAll(int count) throws InterruptedException {
        if (count == 0) return;
        var threads = getReadyThreads().stream()
                .map(Thread::new)
                .toList();
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        startAll(count - 1);
    }

    private List<Miner> getThreads(Blockchain blockchain) {
        List<Miner> listOfThreads = new ArrayList<>();
        for (int i = 0; i < numOfThreads; i++) {
            listOfThreads.add(new Miner(blockchain));
        }
        return listOfThreads;
    }

}
