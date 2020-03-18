package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import po.Comment;
import po.Orders;
import po.Product;
import po.User;
import service.UserService;

@Controller
public class FirstController {	
	
	ApplicationContext act=new ClassPathXmlApplicationContext("applicationContext.xml");
	UserService service= act.getBean(UserService.class);
	
	//用于记录商品信息
	Product productMark= new Product();
		
	//登录验证
	@RequestMapping("/check")
	@ResponseBody
	public int checkLogin(@RequestBody User user,HttpServletRequest request) {
		
		//定义一个返回值用于ajax判断
		int mark=0;
		//查询数据库账号密码是否正确
		List<User> userList=service.findAllUser();
		for(User ul:userList) {
			if(ul.getName().equals(user.getName())&&ul.getPassword().equals(user.getPassword())) {
				mark=1;
				//登陆成功的时候记录登陆者，把登陆者信息存进session
				request.getSession().setAttribute("userMark", ul);
			}
		}
		return mark;
		
	}
	
	//注销
	@RequestMapping("/logout")
	@ResponseBody
	public int logout(HttpSession session,HttpServletResponse response) throws IOException
	{
		//注销后session清空
		session.invalidate();
		response.sendRedirect("/chapter/main.html");
		return 1;
	}
	
	//注册账号
	@RequestMapping("/register")
	@ResponseBody
	public int register(@RequestBody User user) {
	   
	   List<User> userList=service.findAllUser();
	   //检查用户名是否重复，是否为空
	   for(User u:userList) {
			if(u.getName().equals(user.getName())||user.getName()==null||user.getPassword()==null) {
				return 0;
			  }
			}
	   service.addUser(user);
	   return 1;
	}
	
	//获取登陆者情况
	@RequestMapping("/getStatus")
	@ResponseBody
	public User getStatus(HttpSession session) {	
		
		//实时更新user的余额
		User user1= (User) session.getAttribute("userMark");
		User user=service.findUser(user1);	
		if(user!=null)
			return user;
		user=new User();
		return user;
	}
	
	//获取所有商品,根据接收的参数进行排序
	@RequestMapping("/getProducts")
	@ResponseBody
	public List<Product> getProducts(String sort){
		
		//获取商品仓库
		List<Product> productList=service.findAllProduct();		
		//按价格从小到大排序
		if(sort.equals("price")) {			
			Collections.sort(productList);
			return productList;
		}
		//按照销售量从高到低排序
		if(sort.equals("sales")) {			
			Collections.sort(productList, new Comparator<Product>() {  			  
	            @Override  
	            public int compare(Product o1, Product o2) {  
	                int i = o2.getSales()-o1.getSales();   
	                return i;  
	            }
	        });
			return productList;
		}
		//不排序					
		return productList;
	}
	
	//获取要搜索的商品，并返回视图
	@RequestMapping("/search")
	public String searchProduct(Product product,Model model){
		
		List<Product> listProduct=service.findProduct(product);
		model.addAttribute("listProduct",listProduct);
		return "searchResult";
		}
	
	//获取单件商品的信息
	@RequestMapping("/getProduct")
	@ResponseBody
	public Product getProduct(Product product,HttpServletResponse response) throws IOException
	{
		//如果商品名字不为空，则记录名字，并且重定向到商品详情页
		if(product.getName()!=null) {
			productMark=product;
			response.sendRedirect("/chapter/product.html");
			return new Product();
		}
		//如果商品名字为空，用记录的名字搜索数据库，并返回搜索到的商品信息给商品详情页
		System.out.println(service.findTheProduct(productMark));
		return service.findTheProduct(productMark);
	}
	
	//获取商品的评论区
	@RequestMapping("/getComment")
	@ResponseBody
	public List<Comment> getComment(){
				
		return service.findComment(productMark);
		
	}
	
	//发表评论
	@RequestMapping("/publishComment")
	@ResponseBody
	public int publishComment(Comment comment,HttpSession session,HttpServletResponse response) throws IOException {
		
		//获取登陆者
		User user=(User) session.getAttribute("userMark");
		String name=user.getName();
		//获取商品名字
		String productName=productMark.getName();
		comment.setName(name);
		comment.setProductName(productName);
		//存进数据库
		service.addComment(comment);
		response.sendRedirect("/chapter/product.html#publishComment");
		return 1;
	}
	
	//购买
	@RequestMapping("/buyProduct")
	@ResponseBody
	public int buyProduct(@RequestBody Orders order,HttpSession session,HttpServletResponse response) throws IOException {
		
		//获取购买者余额
		User user=(User) session.getAttribute("userMark");
		int balance=service.findUser(user).getBalance();
		//获取购买者名字
		order.setName(user.getName());
		//获取商品价格
		int price=service.findTheProduct(productMark).getPrice();
		//并获取商品名字
		order.setProductName(productMark.getName());
		//检测是否余额不足
		if(balance>=price) {
			//生成订单
			service.addOrder(order);
			//扣除购买者余额
			user.setBalance(balance-price);
			System.out.println(user);
			service.updateUser(user);
			//销售量+1
			service.updateProductSales(productMark);
			return 1;		
		}
		else
			return 0;					
	}

	//显示个人订单
	@RequestMapping("/getOrders")
	@ResponseBody
	public List<Orders> getOrders(HttpSession session,HttpServletResponse response) throws IOException {

		//获取登陆者
		User user=(User) session.getAttribute("userMark");
		String name=user.getName();
		//创建一个对象用来查询个人订单
		Orders order=new Orders();
		order.setName(name);
		return service.findUserOrders(order);
	}
}
