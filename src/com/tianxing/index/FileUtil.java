package com.tianxing.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileUtil {

	 public static String readTxtFile(String filePath){
		 StringBuilder result = new StringBuilder();
	        try {
	                String encoding="UTF-8";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                    	result.append(System.lineSeparator()+lineTxt);
	                    }
	                    read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
	        return result.toString();
	    }
	 
	public static Boolean copyFile(String newFilePath, String newFileName,
			File file) {
		Boolean result = false;
		try {
			File fileDir = new File(newFilePath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			File newFile = new File(fileDir, newFileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			InputStreamReader input = new InputStreamReader(
					new FileInputStream(file), "utf-8");
			OutputStreamWriter output = new OutputStreamWriter(
					new FileOutputStream(newFile), "utf-8");
			int in = input.read();
			while (in != -1) {
				output.write(in);
				in = input.read();
			}
			input.close();
			output.flush();
			output.close();
			result = true;
		} catch (FileNotFoundException e) {
			System.out.println("上传出现错误，请稍后再上传");
		} catch (IOException e) {
			System.out.println("文件写入服务器出现错误，请稍后再上传");
		}
		return result;
	}
	 
	 /** 
	     *  
	     * @return WebRoot目录的绝对路径 
	     */  
	    public static String getWebRootAbsolutePath() {  
//	        String path = null;  
//	        String folderPath = Path.class.getProtectionDomain().getCodeSource()  
//	                .getLocation().getPath();  
//	        if (folderPath.indexOf("WEB-INF") > 0) {  
//	            path = folderPath.substring(0, folderPath  
//	                    .indexOf("WEB-INF/classes"));  
//	        }  
//	        return path;  
	        ClassLoader classLoader = Thread.currentThread()  
	                .getContextClassLoader();  
	        if (classLoader == null) {  
	            classLoader = ClassLoader.getSystemClassLoader();  
	        }  
	        java.net.URL url = classLoader.getResource("");  
	        String ROOT_CLASS_PATH = url.getPath() + "/";  
	        File rootFile = new File(ROOT_CLASS_PATH);  
	        String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + "/";  
	        File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);  
	        String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + "/";  
	      
	                   //这里 SERVLET_CONTEXT_PATH 就是WebRoot的路径  
	      
	        String path = SERVLET_CONTEXT_PATH + "/" ;  
	        path = path.replaceAll("%20", " ");  
	        return path;  
	    }  
	    
	    private String generateWord() {
	        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
	                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
	                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
	                "W", "X", "Y", "Z" };
	        List<String> list = Arrays.asList(beforeShuffle);
	        Collections.shuffle(list);
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < list.size(); i++) {
	            sb.append(list.get(i));
	        }
	        String afterShuffle = sb.toString();
	        String result = afterShuffle.substring(5, 9);
	        return result;
	    }
}
