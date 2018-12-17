package cn.zhangjingyao.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.MessageDigest;

public class DESEncryptor {

	public static final String ALGORITHM = "DES";

	private static String strDefaultKey = "huatek";
	private static final Log LOGGER = LogFactory.getLog(DESEncryptor.class);

	private SecretKey desKey;

	public DESEncryptor(){
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strDefaultKey.getBytes());
			byte[] pswKey = md.digest();
			DESKeySpec desKeySpec = new DESKeySpec(pswKey);
			SecretKeyFactory desKeyFac = SecretKeyFactory.getInstance(ALGORITHM);
			desKey = desKeyFac.generateSecret(desKeySpec);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());

		}
	}

	public String encrypt(String password) {
		String enStr = null;
		try{
			Cipher encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, desKey);

			byte[] enPassword = encryptCipher.doFinal(password.getBytes("UTF8"));

			 enStr = new BASE64Encoder().encode(enPassword);

		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}

		return enStr;

	}

	public String decrypt(String password){

		String deStr = null;

		try{
			Cipher decryptCipher = Cipher.getInstance("DES");
			decryptCipher.init(Cipher.DECRYPT_MODE, desKey);

			byte[] dePassword = new BASE64Decoder().decodeBuffer(password);

			byte[] dec = decryptCipher.doFinal(dePassword);

			deStr = new String(dec, "UTF8");

		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}

		return deStr;

	}


//	/**
//	 * 测试方法
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		String str = new DESEncryptor().decrypt("dX3o2n7E3ok=");
//		System.out.println(str);
//	}

}
