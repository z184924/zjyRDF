package cn.zhangjingyao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * java压缩zip
 * @author
 */
public class FileZip {

	/**
	 * @param inputFileName 需要压缩的文件夹(整个完整路径)
	 * @param zipFileName   压缩后的文件(整个完整路径)
	 */
	public static void zip(String inputFileName, String zipFileName) throws Exception {
		zip(zipFileName, new File(inputFileName));
	}

	/**
	 * @param inputFileNameList 需要压缩的文件或文件夹列表(整个完整路径)
	 * @param zipFileName   压缩后的文件(整个完整路径)
	 */
	public static void zip(List<String> inputFileNameList, String zipFileName) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		for (String inputFileName : inputFileNameList) {
			File file = new File(inputFileName);
			zip(out, file, "/"+file.getName());
		}
		out.flush();
		out.close();
	}

	private static void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		zip(out, inputFile, "");
		out.flush();
		out.close();
	}

	private static void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			ZipEntry zipEntry = new ZipEntry(base);
			zipEntry.setTime(f.lastModified());
			out.putNextEntry(zipEntry);
			writeZip(out,f);
		}
	}

	private static void writeZip(ZipOutputStream out,File f)throws Exception{
		FileInputStream in = new FileInputStream(f);
		int b;
		//System.out.println(base);
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		in.close();
	}

	public static void main(String[] temp) {
		long statTime = System.currentTimeMillis();
		try {
			List<String> fileList = new ArrayList<>();
			fileList.add("C:\\Users\\ZJY\\Desktop\\test.docx");
			fileList.add("C:\\Users\\ZJY\\Desktop\\北城建一体化安全管理模块子系统_用户手册_施工方巡查人.docx");
			fileList.add("C:\\Users\\ZJY\\Desktop\\联系单编号.xls");
			fileList.add("C:\\Users\\ZJY\\Desktop\\testZiped");
			zip(fileList, "C:\\Users\\ZJY\\Desktop\\testZip.zip");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Used Time:"+((endTime-statTime)/1000)+"s");
	}
}


//=====================文件压缩=========================
/*//把文件压缩成zip
File zipFile = new File("E:/demo.zip");
//定义输入文件流
InputStream input = new FileInputStream(file);
//定义压缩输出流	
ZipOutputStream zipOut = null;
//实例化压缩输出流,并制定压缩文件的输出路径  就是E盘下,名字叫 demo.zip
zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
zipOut.putNextEntry(new ZipEntry(file.getName()));
//设置注释
zipOut.setComment("www.demo.com");
int temp = 0;
while((temp = input.read()) != -1) {
	zipOut.write(temp);	
}		
input.close();
zipOut.close();*/
//==============================================
