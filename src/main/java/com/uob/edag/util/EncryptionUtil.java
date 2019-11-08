package com.uob.edag.util;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class EncryptionUtil
{
	
	
	private static final char[] PASSWORD = "basgqweytwnsuyh23872jsjnsusdflshjynsadios".toCharArray();
	private static final byte[] SALT = { -34, 51, 16, 18, -34, 51, 16, 18 };
  
	private static final char[] PASSWORD_F = "enfldsgbnlsngdlksdsgm".toCharArray();
	private static final byte[] SALT_F = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12 };


  public static void main(String[] args)
    throws Exception
  {
    Scanner scanner = null;
    try {
      //scanner = new Scanner(System.in, "UTF-8");
      //Console console = System.console();
      //String c ="OU26ty98";
      String c ="EDF";
     //jun17eda17
      EncryptionUtil util = new EncryptionUtil();
      
      String encryptedInput = util.encrypt(c);
      System.out.println("Encrypted input: " + encryptedInput);
      
      
      //System.out.println("Encrypted input: jO8bfnBc2WY4ciLnuGrE5A " + util.decrypt("jO8bfnBc2WY4ciLnuGrE5A==") +":" );
      //System.out.println("Encrypted input: 0xhfvj8JS " + util.decrypt("0xhfvj8JS+4f+fuujrErHQ==") +":" );
      //System.out.println("Encrypted input: 88kjm0KhAqw0dM0iGNNmTw " + util.decrypt("88kjm0KhAqw0dM0iGNNmTw==") +":" );
      //System.out.println("Encrypted input: " + util.decrypt("UO36sCOuIdvr12B+nKnPXw=="));
      System.out.println("Encrypted input: " + util.decrypt(encryptedInput));
            
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }
  
  public static void main2(String[] args)
    throws Exception
  {
    Scanner scanner = null;
    try {
      scanner = new Scanner(System.in, "UTF-8");
      Console console = System.console();
      String input="";
      input = new String(console.readPassword("Enter string to be encrypted : ", new Object[0]));  
      EncryptionUtil util = new EncryptionUtil();
      
      String encryptedInput = util.encrypt(input);
      System.out.println("Encrypted input: " + encryptedInput);
      util.write("AS400T4.password="+ encryptedInput+"\n");
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }

  public void write(String s){
	try{
			RandomAccessFile file = new RandomAccessFile(new File("./password.txt"),"rw");
			file.seek(file.length());
			file.write(s.getBytes("UTF-8"));
			file.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}





  public String encrypt(String property)
    throws GeneralSecurityException, UnsupportedEncodingException
  {
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
    SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
    Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
    pbeCipher.init(1, key, new PBEParameterSpec(SALT, 20));
    return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
  }
  
  private String base64Encode(byte[] bytes) {
    return Base64.getEncoder().encodeToString(bytes);
  }
  





  public String decrypt(String property)
    throws GeneralSecurityException, IOException	
  {
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
    SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
    Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
    pbeCipher.init(2, key, new PBEParameterSpec(SALT, 20));
    return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
  }
  
  public String decryptFramework(String property)
    throws GeneralSecurityException, IOException
  {
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
    SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD_F));
    Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
    pbeCipher.init(2, key, new PBEParameterSpec(SALT_F, 20));
    return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
  }
  



  public String encryptFramework(String property)
    throws GeneralSecurityException, UnsupportedEncodingException
  {
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
    SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
    Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
    pbeCipher.init(1, key, new PBEParameterSpec(SALT_F, 20));
    return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
  }
  
  
  
  
  private byte[] base64Decode(String property) throws IOException {
    return Base64.getDecoder().decode(property);
  }
}


