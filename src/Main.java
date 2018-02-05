import java.security.MessageDigest;
import java.util.*;

public class Main {

    private static String sha = "5491aec0206e6dd40b9f8d86fce427d66b188dfe";
    private static String charset = "1234567890abcdefghijklmnopqrstuvwxyz~!@#$%^&*+=";

    public static void main(String[] args) {
        System.out.println("password: " + gen());
    }
    private static String gen() {
        StringBuilder pass = new StringBuilder();
        System.out.println("decrypting....");

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < charset.length(); j++) {
                for(int k = 0; k < charset.length(); k++) {
                    for(int l = 0; l < charset.length(); l++) {
                        for(int m = 0; m < charset.length(); m++) {
                            pass.append("v");
                            pass.append(charset.charAt(j));
                            pass.append(charset.charAt(k));
                            pass.append(charset.charAt(l));
                            pass.append(charset.charAt(m));
                            pass.append("-");
                            if(sha.equals(encryptPassword(pass.toString()))) {
                                return pass.toString();
                            }
                            pass.delete(0,6);
                        }
                    }
                }
            }
        }


        return pass.toString();
    }

    private static String encryptPassword(String password){
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[]hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    private static byte[] hexToByte(String sha) {
        byte[] b = new byte[sha.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(sha.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }
}
