package util;

import po.Comment;
import po.Product;
import po.User;

public class Factory {
	
	Comment comment=new Comment();
	Product product=new Product();
	User user=new User();
	
	//快速创建一个product对象
	public Product setProduct(String name,String img,Integer price)
	{
		product.setName(name);
		product.setImg(img);
		product.setPrice(price);
		return product;	
	}
}
