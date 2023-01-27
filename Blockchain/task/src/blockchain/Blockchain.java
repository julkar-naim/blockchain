package blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Blockchain {

    private List<Block> chain = new ArrayList<>();

    public void createNewBlock() {
        int id = chain.size() + 1;
        long timestamp = new Date().getTime();
        String previousHash = chain.isEmpty() ? "0" :
                chain.get(chain.size() - 1).getHash();
        String hash = generateHash(id, timestamp, previousHash);
        chain.add(new Block(id, timestamp, previousHash, hash));
    }

    public boolean isValid() {
        if (chain.isEmpty() || chain.size() == 1) return true;
        boolean ok = true;
        for (int i = 1; i < chain.size(); i++) {
            Block previousBlock = chain.get(i - 1);
            Block currentBlock = chain.get(i);
            String prevHash = generateHash(
                    previousBlock.getId(),
                    previousBlock.getTimestamp(),
                    previousBlock.getPreviousHash()
            );
            ok &= prevHash.equals(currentBlock.getPreviousHash());
        }
        return ok;
    }

    private String generateHash(int id, long timestamp, String previousHash) {
        String allProps = Integer.toString(id) + Long.toString(timestamp) + previousHash;
        return StringUtil.applySha256(allProps);
    }

    @Override
    public String toString() {
        return chain.stream()
                .map(Block::toString)
                .collect(Collectors.joining("\n"));
    }
}
