package cn.zhangjingyao.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * @author
 */
public final class License {
	private static final Logger LOGGER = LogManager.getLogger(License.class);

	private static final String PARAMETER_FILE = "/license.properties";

	private static final License INST = new License();

	public static License getInstance() {
		return INST;
	}

	private Properties prop = null;

	public Properties getProp() {
		return prop;
	}

	private License() {
		prop = new Properties();
		try {
			LOGGER.info("parameter config init");
			InputStream inputStream=this.getClass().getResourceAsStream(PARAMETER_FILE);
			prop.load(inputStream);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private static String createLicense(String date) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer stringBuffer=new StringBuffer();
		Random random = new Random(System.currentTimeMillis());
		for(int i=0;i<15;i++){
			int randNum = random.nextInt(123);
			if((randNum>=48&&randNum<=57)||(randNum>=65&&randNum<=90)||(randNum>=97&&randNum<=122)){
				char code = (char) randNum;
				stringBuffer.append(code);
			}else{
				stringBuffer.append(0);
			}
		}
		stringBuffer.append(simpleDateFormat.parse(date).getTime());
		for(int i=0;i<38;i++){
			int randNum = random.nextInt(123);
			if((randNum>=48&&randNum<=57)||(randNum>=65&&randNum<=90)||(randNum>=97&&randNum<=122)){
				char code = (char) randNum;
				stringBuffer.append(code);
			}else{
				stringBuffer.append('0');
			}
		}
		return stringBuffer.toString();
	}

	public static void main(String[] args){
		try{
			System.out.println(createLicense("2018-07-04"));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 系统许可证验证
	 * @return 是否通过
	 */
	public static boolean licenseCheck(){
		License license= License.getInstance();
		Properties properties=license.getProp();
		String propwT=properties.getProperty("PropwT");
		propwT=propwT.substring(15,28);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long lt = new Long(propwT);
		Date date = new Date(lt);
		String outTime= simpleDateFormat.format(date);
		String nowTime=simpleDateFormat.format(new Date());
		if(nowTime.compareTo(outTime)>0){
			return false;
		}
		return true;
	}
}
