package mapperFactory;

import java.util.List;

import po.Comment;
import po.Orders;
import po.Product;
import po.User;
import po.UserBS;

public interface Mapper {
	
	//操作User表
	public User findUser(User user);
	public List<User> findAllUser();
	public Integer addUser(User user);
	public Integer updateUser(User user);

	//操作UserBS表
	public UserBS findUserBS(UserBS userBS);
	public List<UserBS> findAllUserBS();
	public Integer addUserBS(UserBS userBS);
	
	//操作comment表
	public List<Comment> findComment(Product product);
	public Integer addComment(Comment comment);
	
	//操作product表
	public Product findTheProduct(Product product);
	public List<Product> findProduct(Product product);
	public List<Product> findAllProduct();
	public Integer addProduct(Product product);
	public Integer updateProduct(Product product);
	public Integer updateProductSales(Product product);
	public Integer deleteProduct(Product product);
	
	
	//操作订单表
	public Orders findOrder(Orders order);
	public List<Orders> findUserOrders(Orders order);
	public List<Orders> findSomeOrders(Orders order);
	public List<Orders> findAllOrders(Orders order);
	public Integer addOrder(Orders order);
	public Integer updateOrder(Orders order);

}
