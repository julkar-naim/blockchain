package blockchain;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Blockchain {

    private static Blockchain blockchain;
    private CopyOnWriteArrayList<Block> chain = new CopyOnWriteArrayList<>();
    private AtomicInteger N = new AtomicInteger(0);
    private BlockchainPublisher blockchainPublisher;

    private Blockchain() {}

    public static Blockchain getBlockChain() {
        if (blockchain == null)
            return blockchain = new Blockchain();
        return blockchain;
    }

    public synchronized void acceptBlock(Block block) {
        if (!isNewBlockValid(block)) return;
        regulateN(block.getTimeTaken().intValue());
        block.setN(N.intValue());
        blockchainPublisher.alert();
        if (isNewBlockValid(block)) chain.add(block);
    }

    public boolean isNewBlockValid(Block block) {
        return block.getPreviousHash().equals(getLastBlockHash()) &&
                block.getHash().equals(StringUtil.blockToHash(block));
    }

    public String getLastBlockHash() {
        return chain.isEmpty() ? "0" : chain.get(chain.size() - 1).getHash();
    }

    public Integer getLastBlockId() {
        return chain.size();
    }

    public Integer getN() {
        return this.N.intValue();
    }

    private void regulateN(int time) {
        if (time >= 60) N.decrementAndGet();
        else if (time < 10) N.incrementAndGet();
    }

    public void setBlockchainPublisher(BlockchainPublisher blockchainPublisher) {
        this.blockchainPublisher = blockchainPublisher;
    }

    public void print() {
        for (int i = 0; i < chain.size(); i++) {
            System.out.println(chain.get(i));
            if (i == 0) {
                System.out.println("N was increased to " + chain.get(i).getN() + "\n");
                continue;
            }
            int cur = chain.get(i).getN();
            int prev = chain.get(i - 1).getN();
            String out = "";
            if (cur == prev) {
                out = "N stays the same " + cur;
            }
            else if (cur > prev) {
                out = "N was increased to " + cur;
            }
            else {
                out = "N was decreased to " + cur;
            }
            System.out.println(out + "\n");
        }
    }
}
