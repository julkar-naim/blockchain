package blockchain;

public class Block {
    private final int id;
    private final long timestamp;
    private final String previousHash;
    private final String hash;

    public Block(int id, long timestamp, String previousHash, String hash) {
        this.id = id;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Id: " + id +
                "\nTimestamp: " + timestamp +
                "\nHash of the previous block:\n" + previousHash +
                "\nHash of the block:\n" + hash + "\n";
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
}
