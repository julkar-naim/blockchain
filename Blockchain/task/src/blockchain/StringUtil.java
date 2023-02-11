package blockchain;

import java.security.MessageDigest;

public class StringUtil {
    /* Applies Sha256 to a string and returns a hash. */
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String blockToHash(Block block) {
        String props = block.getId().toString() +
                block.getCreatedBy() +
                block.getTimestamp().toString() +
                block.getPreviousHash() +
                block.getMagicNumber();
        return applySha256(props);
    }

    public static boolean isValidN(String hash, int n) {
        int count = 0;
        for (int i = 0; i < hash.length(); i++) {
            if (hash.charAt(i) != '0')
                break;
            count++;
        }
        return count == n;
    }
}
