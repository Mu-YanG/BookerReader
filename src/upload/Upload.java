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
/**@author ����
 * @version 1.0
 * �������ڶ��ļ����ϴ�������
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
	 * ���Ե�������
	 * @throws IOException 
	 * */
	public static void main(String args[]) throws IOException{
	    String a="C:\\Users\\��\\Desktop\\�½��ļ��� (4)\\�½��ļ��� (2)\\aa.png"; 
	    String c="F:\\���.txt"; 
	    
		//UpImg(c,"10qwe1","userphoto");
		
		
		
	}
	
	
	
	/**
	 *�ϴ�������
	 *����  ��string ·��
	 *	       ��  �û�ͼƬ
	 *	       �� �����ļ��дӱ����ϴ���������
	 *���ж��ļ�
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("deprecation")
	public static void UpImg(HttpServletRequest request,CommonsMultipartFile file [], String id,String pwd) throws IOException{
		//int position  = filepath.lastIndexOf("\\");//ȡ���ļ�
		//String filename = filepath.substring(position+1, filepath.length());//��ȡ���ļ���:�������
		//System.out.println(filename);
		for (int i = 0; i < file.length; i++) {
			
		
		String filename= file[i].getOriginalFilename();
		int surfix = filename.lastIndexOf(".");
		String ssurfix = filename.substring(surfix, filename.length());
		//System.out.println(ssurfix);
		//File f  = new File("/WebRoot/photps/"+pwd+"/"+id+ssurfix);
		//��ϵͳĿ¼�´����ļ���
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
		System.out.println("�ϴ��ɹ�");
	}

       @SuppressWarnings("deprecation")
	public static void UpImg1(HttpServletRequest request,CommonsMultipartFile file [], String id,String pwd) throws IOException{
		//int position  = filepath.lastIndexOf("\\");//ȡ���ļ�
		//String filename = filepath.substring(position+1, filepath.length());//��ȡ���ļ���:�������
		//System.out.println(filename);
		for (int i = 0; i < file.length; i++) {
			 
		
		String filename= file[i].getOriginalFilename();
		int surfix = filename.lastIndexOf(".");
		String ssurfix = filename.substring(surfix, filename.length());
		//System.out.println(ssurfix);
		//File f  = new File("/WebRoot/photps/"+pwd+"/"+id+ssurfix);
		//��ϵͳĿ¼�´����ļ���
		String path = request.getRealPath(pwd);
		File f  = new File(path+"\\"+id+ssurfix);
		setFn(id+ssurfix);
		System.out.println(path+"\\"+id+ssurfix);
		
		 if (!f.exists()) {  
	            f.mkdir();  
	        } 
		 
		file[i].transferTo(f);
		
		}
		System.out.println("�ϴ��ɹ�");
	}

	/**
	 * 
	 *ͼ���ļ���txt ��  photos���ϴ�
	 * 
	 * 
	 * 
	 * */
   	@SuppressWarnings("deprecation")
	public static void Upfile(HttpServletRequest request,CommonsMultipartFile file [], String id) throws IOException{
		//int position  = filepath.lastIndexOf("\\");//ȡ���ļ�
		//String filename = filepath.substring(position+1, filepath.length());//��ȡ���ļ���:�������
		//System.out.println(filename);
   		String path = request.getRealPath("");
		
		for (int i = 0; i < file.length; i++) {
			
		
		String filename= file[i].getOriginalFilename();
		int surfix = filename.lastIndexOf(".");
		String ssurfix = filename.substring(surfix, filename.length());
		System.out.println("�ļ�����"+ssurfix);
		//File f  = new File("/WebRoot/photps/"+pwd+"/"+id+ssurfix);
		//��ϵͳĿ¼�´����ļ���
		
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
		System.out.println("�ϴ��ɹ�");
	}
	
	
	
	
	
	
}
