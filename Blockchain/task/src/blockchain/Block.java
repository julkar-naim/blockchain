package blockchain;

public class Block {

    private Integer id;
    private String createdBy;
    private Long timestamp;
    private String previousHash;
    private Integer magicNumber;
    private Long timeTaken;
    private String hash;
    private Integer N;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(Integer magicNumber) {
        this.magicNumber = magicNumber;
    }

    public Long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public Integer getN() {
        return N;
    }

    public void setN(Integer n) {
        N = n;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Block:" +
                "\nCreated by miner # " + createdBy +
                "\nId: " + id +
                "\nTimestamp: " + timestamp +
                "\nMagic number: " + magicNumber +
                "\nHash of the previous block:\n" + previousHash +
                "\nHash of the block:\n" + hash +
                "\nBlock was generating for " + timeTaken + " seconds";

    }
}
