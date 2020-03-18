package service;
import java.util.List;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import mapperFactory.Mapper;
import po.Comment;
import po.Orders;
import po.Product;
import po.User;
import po.UserBS;



@Service
public class UserService {
	@Resource(name="mapper")
	private Mapper mapper;
	
	
	//操作user表
	public User findUser(User user){
		return mapper.findUser(user);
	}
	public List<User> findAllUser(){
		return mapper.findAllUser();
	}
	public Integer addUser(User user) {
		return mapper.addUser(user);
	}
	public Integer updateUser(User user) {
		return mapper.updateUser(user);
	}
	
	//操作userBS表
	public UserBS findUserBS(UserBS userBS) {
		return mapper.findUserBS(userBS);
	}
	public List<UserBS> findAllUserBS(){
		return mapper.findAllUserBS();
	}
	public Integer addUserBS(UserBS userBS) {
		return mapper.addUserBS(userBS);
	}
	
	//操作comment表
	public List<Comment> findComment(Product product){
		return mapper.findComment(product);
	}
	public Integer addComment(Comment comment) {
		return mapper.addComment(comment);
	}
	
	//操作product表
	public Product findTheProduct(Product product) {
		return mapper.findTheProduct(product);
	}
	public List<Product> findProduct(Product product) {
		return mapper.findProduct(product);
	}
	public List<Product> findAllProduct(){
		return mapper.findAllProduct();
	}
	public Integer addProduct(Product product) {
		return mapper.addProduct(product);
	}
	public Integer updateProduct(Product product) {
		return mapper.updateProduct(product);
	}
	public Integer deleteProduct(Product product) {
		return mapper.deleteProduct(product);
	}
	//销售量+1
	public Integer updateProductSales(Product product) {
		return mapper.updateProductSales(product);
	}
	
	//操作订单表
	public Orders findOrder(Orders order) {
		return mapper.findOrder(order);
	}
	public List<Orders> findUserOrders(Orders order){
		return mapper.findUserOrders(order);
	}
	public List<Orders> findSomeOrders(Orders order) {
		return mapper.findSomeOrders(order);
	}
	public List<Orders> findAllOrders(Orders order) {
		return mapper.findAllOrders(order);
	}
	public Integer addOrder(Orders order) {
		return mapper.addOrder(order);
	}
	public Integer updateOrder(Orders order) {
		return mapper.updateOrder(order);
	}
			
	
}
