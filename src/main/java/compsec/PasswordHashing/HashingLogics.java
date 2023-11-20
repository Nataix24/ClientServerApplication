package compsec.PasswordHashing;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashingLogics {

    // Pepper is global, should be static
    private static final String PEPPER = "R3d_H0t_Ch1ll1_P3pp3R";

    private byte[] salt;
    private String hashedPassword;

    public HashingLogics(String rawPassword){
        this.salt = new byte[]{-38, -69, -23, -119, 1, 38, 69, -52, 113, -122, 39, -95, 102, -1, 59, 126};
        this.hashedPassword = hash(rawPassword, this.salt);
    }
    public byte[] getSalt(){
        return this.salt;
    }
    public String getHashedPassword(){
        return this.hashedPassword;
    }

    // Utility, convert byte[] to String
    public static String bytesToString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02x", aByte));
        }
        return result.toString();
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltArr = new byte[16];
        random.nextBytes(saltArr);
        return saltArr;
    }


    // Hash the password given salt
    public static String hash(String rawPassword, byte[] salt) {
        try {
            rawPassword += PEPPER;
            KeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");;
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return bytesToString(hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Oopsie with getting algorithm instance 0_0");
            e.printStackTrace();
            return "ERROR_1";
        } catch (InvalidKeySpecException e) {
            System.out.println("Oopsie with generating the hashed password 0_0");
            e.printStackTrace();
            return "ERROR_2";
        }
    }

    // Compare a raw String to a hashed password
    public static boolean verifyPassword(String rawPassword, String hashedPassword, byte[] salt) {
        String newHash = hash(rawPassword, salt);
        return hashedPassword.equals(newHash);
    }

    public static void main(String[] args) {
        String originalRaw = "MyCoolPasswordHaha";

        HashingLogics cypher = new HashingLogics(originalRaw);
        byte[] salt = cypher.getSalt();
        String hashedOriginal = cypher.getHashedPassword();
        
        String testRaw = "MyCoolPasswordHaha";
        boolean match = verifyPassword(testRaw, hashedOriginal, salt);

        SecureRandom random = new SecureRandom();
        byte[] saltArr = new byte[16];
        random.nextBytes(saltArr);
        System.out.println(Arrays.toString(saltArr));

        System.out.println("Raw stored:     " + originalRaw);
        System.out.println("Raw input:      " + testRaw);
        System.out.println("Hashed stored:  " + hashedOriginal);
        System.out.println("Match:          " + match);
    }
}
