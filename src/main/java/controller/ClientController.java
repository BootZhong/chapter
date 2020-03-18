package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import po.Orders;
import po.Product;
import po.User;
import po.UserBS;
import service.UserService;
import util.IOTool;

@Controller
@RequestMapping("/client")
public class ClientController {

	ApplicationContext act=new ClassPathXmlApplicationContext("applicationContext.xml");
	UserService service= act.getBean(UserService.class);
	IOTool io=new IOTool();
	
	//登录检查
	@RequestMapping("/check")
	@ResponseBody
	public int check(@RequestBody UserBS userBS,HttpSession session) {
		
		//后台账号可以用于前台
		User user=new User();
		user.setName(userBS.getName());
		user.setPassword(userBS.getPassword());
		//账号密码正确返回1，并且用session记录该用户
		List<UserBS> userBSList=service.findAllUserBS();
		for(UserBS ub:userBSList) {
			if(ub.getName().equals(userBS.getName())&&ub.getPassword().equals(userBS.getPassword())) {
				//后台登录了前台也自动登陆了
				session.setAttribute("userBSMark",userBS);
				session.setAttribute("userMark", user);
				return 1;
			}
		}
		return 0;	
	}
	
	//上架商品
	@RequestMapping("/addProduct")	
	public String addProduct(Product product,
			@RequestParam("uploadfile") List<MultipartFile> uploadfile, HttpServletRequest request) {
		
		//商品名不能重复，重复则返回0
		List<Product> productList=service.findAllProduct();
		for(Product pd:productList)
		{
			if(pd.getName().equals(product.getName()))
				return "error";
		}
		
		String originalFilename="";
		// 检查上传的东西是否为空
		if (!uploadfile.isEmpty() && uploadfile.size() > 0) {
			// 循环输出上传的所有文件
			for (MultipartFile file : uploadfile) {
				// 获取上传文件的原始名称
				originalFilename = file.getOriginalFilename();
				// 设置上传文件的保存的地址
				//发布后的项目中
				String dirPath = request.getServletContext().getRealPath("/img/");
				/*//发布前的项目中
				String dirPath2 ="D:\\JavaProgram\\chapter\\src\\main\\webapp\\img\\";*/
			   
				try {
					// 把上传的文件以originalFilename为名字存入指定文件夹dirPath，若文件夹不存在则不行
					file.transferTo(new File(dirPath + originalFilename));
				} catch (Exception e) {
					e.printStackTrace();
					return "error";
				}
			}
			//检查图片是否名字重复
			for(Product pd:productList)
			{
				if(pd.getImg().contains(originalFilename))
					return "error";
			}
			//设置商品的存储路径信息
			product.setImg("img/"+originalFilename);
			//把商品信息存进数据库
			System.out.println(product);
			int i=service.addProduct(product);
			System.out.println(i);
			// 跳转到成功页面
			return "success2";
		} else {
			return "error";
		}
	}
	
	//修改商品信息
	@RequestMapping("/updateProduct")
	public String updateProduct( Product product,Model model,HttpSession session) {
		
		
		session.setAttribute("nameUpdate", product.getName());
		model.addAttribute("product",product);
		return "update";
	}
	
	//修改商品信息2
	@RequestMapping("/updateProduct2")
	public String updateProduct2( Product product,Model model,HttpSession session) {
		
		
		//商品名不能重复，重复则返回0
		List<Product> productList=service.findAllProduct();
		for(Product pd:productList)
			{
				if(!pd.getName().equals((String)session.getAttribute("nameUpdate"))) {
					if(pd.getName().equals(product.getName()))
						return "error";
				}
				
			}
		service.updateProduct(product);
		return "success";
	}
	
	//删除商品
	@RequestMapping("/deleteProduct")
	public void deleteProduct( Product product,HttpServletRequest request,HttpServletResponse response) throws IOException {
			
		io.deleteImg(product.getImg(),request);
		service.deleteProduct(product);
		response.sendRedirect("/chapter/backStage.html");
		
	}
	
	//获取未发货订单的数量
	@RequestMapping("/checkOrders")
	@ResponseBody
	public int checkOrders( HttpServletRequest request,HttpServletResponse response) throws IOException {
			
		Orders order=new Orders();
		order.setState(0);
		List<Orders> listOrders = service.findSomeOrders(order);
		//未发货订单的数量
		return listOrders.size();		
	}

	//获取全部订单
	@RequestMapping("/getOrders")
	@ResponseBody
	public List<Orders> getOrders( HttpServletRequest request,HttpServletResponse response) throws IOException {

		//创建一个orders对象来查找数据库
		Orders order=new Orders();
		order.setState(0);
		//未发货订单的数量
		List<Orders> listOrders = service.findSomeOrders(order);
		//已发货订单的数量
		order.setState(1);
		List<Orders> listOrders2 = service.findSomeOrders(order);
		//对订单进行排序，已经发货的在下面
		listOrders.addAll(listOrders2);
		System.out.println(listOrders);
		return listOrders;
	}

	//发货
	@RequestMapping("/updateOrders")
	public void updateOrders( Orders order,HttpServletRequest request,HttpServletResponse response) throws IOException {

		System.out.println(order);
		//状态改为已发货
		order.setState(1);
		//修改数据库信息
		service.updateOrder(order);
		//重定向到的订单页
		response.sendRedirect("/chapter/order.html");

	}
}
