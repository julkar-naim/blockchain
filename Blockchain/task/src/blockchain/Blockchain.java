package blockchain;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Blockchain {

    private List<Block> chain = new ArrayList<>();
    int prefixLength;

    public Blockchain(int prefixLength) {
        this.prefixLength = prefixLength;
    }

    public void createNewBlock() {
        int id = chain.size() + 1;
        long timestamp = new Date().getTime();
        String previousHash = chain.isEmpty() ? "0" :
                chain.get(chain.size() - 1).getHash();
        Block newBlock = createNewBlock(id, timestamp, previousHash);
        chain.add(newBlock);
    }

    private Block createNewBlock(int id, long timestamp, String previousHash) {
        Instant startTime = Instant.now();
        Random random = new Random();
        int magicNumber;
        String hash;
        do {
            magicNumber = random.nextInt();
            hash = generateHash(id, timestamp, previousHash, magicNumber);
        }while(!isValidHash(hash, prefixLength));
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        long timeTaken = duration.toSeconds();
        return new Block(id, timestamp, previousHash, magicNumber, hash, timeTaken);
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
                    previousBlock.getPreviousHash(),
                    previousBlock.getMagicNumber()
            );
            ok &= prevHash.equals(currentBlock.getPreviousHash());
        }
        return ok;
    }

    private boolean isValidHash(String hash, int prefLen) {
        int countZeroes = 0;
        for (int i = 0; i < hash.length(); i++) {
            if (hash.charAt(i) != '0') break;
            countZeroes++;
        }
        return countZeroes == prefLen;
    }

    private String generateHash(int id, long timestamp, String previousHash, int magicNumber) {
        String allProps = Integer.toString(id) + Long.toString(timestamp) + previousHash
                + Integer.toString(magicNumber);
        return StringUtil.applySha256(allProps);
    }

    @Override
    public String toString() {
        return chain.stream()
                .map(Block::toString)
                .collect(Collectors.joining("\n"));
    }
}
