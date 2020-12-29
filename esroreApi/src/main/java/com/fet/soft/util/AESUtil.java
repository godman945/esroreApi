package com.fet.soft.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class AESUtil {

	private static final String characterEncoding = "UTF-8";
	private static final String cipherTransformation = "AES/CBC/PKCS5Padding";
	private static final String aesEncryptionAlgorithm = "AES";
	private static final String iv = "7LPMMNG4ECGPPF69";
	private static final String key = "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93";

	private static byte[] decrypt(byte[] cipherText, byte[] key, byte[] initialVector) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, NoSuchProviderException {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(cipherTransformation);
		SecretKeySpec secretKeySpecy = new SecretKeySpec(key, aesEncryptionAlgorithm);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy, ivParameterSpec);
		cipherText = cipher.doFinal(cipherText);
		return cipherText;
	}

	private static byte[] encrypt(byte[] plainText, byte[] key, byte[] initialVector)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(cipherTransformation);
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		plainText = cipher.doFinal(plainText);
		return plainText;
	}

	private static byte[] getKeyBytes(String key) throws UnsupportedEncodingException {
		byte[] keyBytes = new byte[16];
		byte[] parameterKeyBytes = key.getBytes(characterEncoding);
		System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
		return keyBytes;
	}

	public static String encrypt(String plainText, String key)
			throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] plainTextbytes = plainText.getBytes(characterEncoding);
		byte[] keyBytes = getKeyBytes(key);


		byte[] alex = encrypt(plainText.getBytes(), key.getBytes(), iv.getBytes());
		return Base64.encodeBase64String(alex);

	}

	public static String decrypt(String encryptedText, String key)
			throws KeyException, GeneralSecurityException, GeneralSecurityException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, IOException {


		byte[] alex = Base64.decodeBase64(encryptedText);
		return new String(decrypt(alex, key.getBytes(), iv.getBytes()), characterEncoding);

	}

	public static void main(String args[]) throws Exception {
//    	ZGzkHbz0/FFxe9l8tikOFq0fTQxPzYKtilpflkGegGg=
//    	System.out.println(AES.encrypt("2020062968113737589867", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
//    	System.out.println(AES.decrypt("KDkEmT1YZ2zUYfevwMbZXs2xiXDjDFtV76S6v/O/ny8=","TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));

//		System.out.println(AESUtil.encrypt("ALEX#@$%^20&19.陳", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));

//		System.out.println(AESUtil.decrypt("b7LVFr2xZXgfs++KuwVhDVV/qdqnRdXNG6thnnZYarM=", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
//		System.out.println(AESUtil.encrypt("", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("JuZYY3Dbvh57HE68HikG8vkpFhbq36oNz7OkmoyQMVM=", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("VjzQR9mkvtkOVXzhJqK2Pg==", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("I6IbQX4/KYJGj4j7rLgs0w==", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("rJ1EjeIzlvzboglzRp8HAg==", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("rJ1EjeIzlvzboglzRp8HAg==", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("rJ1EjeIzlvzboglzRp8HAg==", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("1Cd67uqfcEMBxYYGVuvcrOh/2i7OsUK8QltXYF08FekyOpVaklCUDCd6uHnQaUJzcfkNx+P3vxUQ9vBmLNcoNQ==", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("Iw7Lfs3YJO9V3fXotcU/fw==", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("JuZYY3Dbvh57HE68HikG8vkpFhbq36oNz7OkmoyQMVM=", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("xmlkEtkoHKvkITeo50ESsFnaEz4FBRJBZ0P8ghJYqosABlZqszByB+delARUCEPp", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("xmlkEtkoHKvkITeo50ESsMy+tP/R7lK2hVKWs/SsBUEV674tHB27bXFAwCLIzEUD", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("JuZYY3Dbvh57HE68HikG8vkpFhbq36oNz7OkmoyQMVM=", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("I6IbQX4/KYJGj4j7rLgs0w==", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		System.out.println(AESUtil.decrypt("hu182aT5eEdJmfR26Yx4rg==", "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93"));
		
	}
}
