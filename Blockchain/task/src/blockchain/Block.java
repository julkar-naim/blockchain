package blockchain;

public class Block {
    private final int id;
    private final long timestamp;
    private final String previousHash;
    private final String hash;
    private final int magicNumber;
    private final long timeTaken;

    public Block(int id, long timestamp, String previousHash, int magicNumber, String hash, long timeTaken) {
        this.id = id;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.hash = hash;
        this.magicNumber = magicNumber;
        this.timeTaken = timeTaken;
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Id: " + id +
                "\nTimestamp: " + timestamp +
                "\nMagic number: " + magicNumber +
                "\nHash of the previous block:\n" + previousHash +
                "\nHash of the block:\n" + hash +
                "\nBlock was generating for " + timeTaken + " seconds\n";
    }

    public int getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public long getTimeTaken() {
        return timeTaken;
    }
}
