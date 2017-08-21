package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DB.SelectList;

import modle.Book;
import modle.User;

public class PageGroup {
	static int sumPage ;//总页数
	static int newPage = 0 ;//现在页数
	
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from ruser ";
	List<User> newlist =SelectList.SelectUserList(sql);

	System.out.println(PageGroup(newlist, 0, 6).get(0).getName());
	}
	//返回页面的页号
	 
	//返回链表
	public static ArrayList<User>  PageGroup (List<User> list ,int a,int show){
		if(a<0){
			
			newPage=0;
			
		}else if(a>list.size()/show){
			
			
			newPage=list.size()/show;
			
		}else{
			newPage=a;
			
		}
		
		
			
		
		System.out.println(newPage);
		//1
		ArrayList<User>  arrlist = new ArrayList<User>();
		int newjl = newPage*show;
		int lastjl = newPage*show +show;
		
		if(newPage ==(list.size()/show)){//当现实为最后一页是
			
			lastjl = list.size();//容量为最大容量，即最后一页仅比规定显示数目少或相等
		System.out.println(lastjl);
		}
		
		for(int i = newjl;i<lastjl;i++){
			
			
			User user  = list.get(i) ;
			arrlist.add(user);
			 
		}
		
		
		return arrlist;
	}
	
	
	public static int [] Page(List<User> list ,int show){
int a = list.size()/show;
if(a == 0){//如果是第一也
	
	a=1;
	int arr[] ={1};
	return arr;
}else if(list.size()%show==0){
	
	

		int arr[] = new int[a];
		for (int i = 0; i <list.size()/show; i++) {
			arr[i]=i+1;//返还一个由1-最大页码的数组
		}
		return arr;
}else{
	int arr[] = new int[a+1];
	for (int i = 0; i <=list.size()/show; i++) {
		arr[i]=i+1;//返还一个由1-最大页码的数组
	}
	return arr;
	
	
	
}
	}
	//重写上述方法进行书的分页
	public static ArrayList<Book>  Pagegroup(List<Book> list ,int a,int show){
		if(a<0){
			
			newPage=0;
			
		}else if(a>list.size()/show){
			
			
			newPage=list.size()/show;
			
		}else{
			newPage=a;
			
		}
		
		
			
		
		System.out.println(newPage);
		//1
		ArrayList<Book>  arrlist = new ArrayList<Book>();
		int newjl = newPage*show;
		int lastjl = newPage*show +show;
		
		if(newPage ==(list.size()/show)){//当现实为最后一页是
			
			lastjl = list.size();//容量为最大容量，即最后一页仅比规定显示数目少或相等
		System.out.println(lastjl);
		}
		
		for(int i = newjl;i<lastjl;i++){
			
			
			Book user  = list.get(i) ;
			arrlist.add(user);
			 
		}
		
		
		return arrlist;
	}
	
	
	public static int [] page(List<Book> list ,int show){
int a = list.size()/show;
if(a == 0){//如果是第一也
	
	a=1;
	int arr[] ={1};
	return arr;
}else if(list.size()%show==0){
	
	

		int arr[] = new int[a];
		for (int i = 0; i <list.size()/show-1; i++) {
			arr[i]=i+1;//返还一个由1-最大页码的数组
		}
		return arr;
}else{
	int arr[] = new int[a+1];
	for (int i = 0; i <=list.size()/show; i++) {
		arr[i]=i+1;//返还一个由1-最大页码的数组
	}
	return arr;
	
	
	
}
	}




}
