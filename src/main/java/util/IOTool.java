package util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class IOTool {
	
	  
	
	  //通过传入的照片的名字删除照片
	  public void deleteImg(String img,HttpServletRequest request) {
		  
		String dirPath = request.getServletContext().getRealPath("img");
		/*String dirPath = "/chapter/img";*/
		System.out.println(dirPath);
		//创建File对象，一个File类对象对应系统的一个磁盘文件或文件夹，所以创建File类对象要给出文件夹名
		File file = new File(dirPath);
		//获取该目录下的所有文件名
		String[] files = file.list(); 
		for (String f : files){ 
			System.out.println(f);
			if(img.contains(f)) {
				//注意这个删除只是删除了部署了的项目的图片，没有部署的项目不影响
				//所以每次重新部署的时候，图片会再次出现
				Boolean a=new File(dirPath+"\\"+f).delete();
				System.out.println(a);
				//下面这个是删除未部署的之前的项目的照片，删除后，部署后的项目也没有照片
				/*Boolean a2=new File("D:/JavaProgram/chapter/src/main/webapp/img/"+f).delete();
				System.out.println(a);*/
			}
		}
		//listFiles是获取该目录下所有文件和目录的绝对路径  
		/*System.out.println(img);
        File[] fs = file.listFiles();  
        for (File f : fs){  
           if(f.getName().contains(img))
        	   f.delete(); 
        }  */
  }
}
