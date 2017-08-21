package upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
/**@author 吕洋
 * @version 1.0
 * 该类用于对文件的上传和下载
 * */
public class Upload {
	
	
	
	public static String fn;



	public static String getFn() {
		return fn;
	}



	public static void setFn(String fn) {
		Upload.fn = fn;
	}



	/**
	 * 测试的主方法
	 * @throws IOException 
	 * */
	public static void main(String args[]) throws IOException{
	    String a="C:\\Users\\洋\\Desktop\\新建文件夹 (4)\\新建文件夹 (2)\\aa.png"; 
	    String c="F:\\你好.txt"; 
	    
		//UpImg(c,"10qwe1","userphoto");
		
		
		
	}
	
	
	
	/**
	 *上传方法：
	 *输入  ：string 路径
	 *	       ：  用户图片
	 *	       ： 保存文件夹从本地上传到服务器
	 *进行对文件
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("deprecation")
	public static void UpImg(HttpServletRequest request,CommonsMultipartFile file [], String id,String pwd) throws IOException{
		//int position  = filepath.lastIndexOf("\\");//取到文件
		//String filename = filepath.substring(position+1, filepath.length());//获取到文件名:测试完成
		//System.out.println(filename);
		for (int i = 0; i < file.length; i++) {
			
		
		String filename= file[i].getOriginalFilename();
		int surfix = filename.lastIndexOf(".");
		String ssurfix = filename.substring(surfix, filename.length());
		//System.out.println(ssurfix);
		//File f  = new File("/WebRoot/photps/"+pwd+"/"+id+ssurfix);
		//在系统目录下创建文件夹
		String path = request.getRealPath(pwd);
	//s	String path = request.getContextPath()+"";
		File f  = new File(path+"\\"+id+ssurfix);
		setFn(id+ssurfix);
		System.out.println(path+"\\"+id+ssurfix);
		 if (!f.exists()) {  
	            f.mkdir();  
	        } 
		 
		file[i].transferTo(f);
		
		
		}
		System.out.println("上传成功");
	}

       @SuppressWarnings("deprecation")
	public static void UpImg1(HttpServletRequest request,CommonsMultipartFile file [], String id,String pwd) throws IOException{
		//int position  = filepath.lastIndexOf("\\");//取到文件
		//String filename = filepath.substring(position+1, filepath.length());//获取到文件名:测试完成
		//System.out.println(filename);
		for (int i = 0; i < file.length; i++) {
			 
		
		String filename= file[i].getOriginalFilename();
		int surfix = filename.lastIndexOf(".");
		String ssurfix = filename.substring(surfix, filename.length());
		//System.out.println(ssurfix);
		//File f  = new File("/WebRoot/photps/"+pwd+"/"+id+ssurfix);
		//在系统目录下创建文件夹
		String path = request.getRealPath(pwd);
		File f  = new File(path+"\\"+id+ssurfix);
		setFn(id+ssurfix);
		System.out.println(path+"\\"+id+ssurfix);
		
		 if (!f.exists()) {  
	            f.mkdir();  
	        } 
		 
		file[i].transferTo(f);
		
		}
		System.out.println("上传成功");
	}

	/**
	 * 
	 *图书文件（txt 和  photos）上传
	 * 
	 * 
	 * 
	 * */
   	@SuppressWarnings("deprecation")
	public static void Upfile(HttpServletRequest request,CommonsMultipartFile file [], String id) throws IOException{
		//int position  = filepath.lastIndexOf("\\");//取到文件
		//String filename = filepath.substring(position+1, filepath.length());//获取到文件名:测试完成
		//System.out.println(filename);
   		String path = request.getRealPath("");
		
		for (int i = 0; i < file.length; i++) {
			
		
		String filename= file[i].getOriginalFilename();
		int surfix = filename.lastIndexOf(".");
		String ssurfix = filename.substring(surfix, filename.length());
		System.out.println("文件名称"+ssurfix);
		//File f  = new File("/WebRoot/photps/"+pwd+"/"+id+ssurfix);
		//在系统目录下创建文件夹
		
		File ff  = new File(path+"\\bookfiles\\"+id+"\\");
		if (!ff.exists()) {
			ff.mkdirs();
		}
	
		File f =new File(path+"\\bookfiles\\"+id+"\\"+id+ssurfix);
		System.out.println(path+"\\"+id+"\\"+id+ssurfix);
		
		if (i==0) {
			setFn(id+ssurfix);	
		}
			
			file[i].transferTo(f);
		
		 
		
		
		}
		System.out.println("上传成功");
	}
	
	
	
	
	
	
}
