package demo;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import po.Comment;
import po.Orders;
import po.Product;
import po.User;
import po.UserBS;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import service.UserService;
import util.Factory;


public class Demo {
	
	Factory f=new Factory();
	@Test	
	public void demo()
	{
		
		ApplicationContext act=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService service= act.getBean(UserService.class);
		List<Product> list = service.findAllProduct();
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		Collections.sort(list, new Comparator<Product>() {  			  
            @Override  
            public int compare(Product o1, Product o2) {  
                int i = o2.getSales()-o1.getSales();   
                return i;  
            }
        });
		System.out.println(list);
		
		
	}
	@Test
	public void demo2() {
		//1.连接池pool 基本配置信息
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(5);//最大连接数
		poolConfig.setMaxIdle(1);//最大空闲数
		//2.连接池
		String host = "localhost";
		int port = 6379;
		JedisPool pool = new JedisPool(poolConfig,host,port);
		//获取jedis
		Jedis jedis = pool.getResource();
		HashMap<String,String> hashMap = new HashMap<>();
		hashMap.put("name", "zhong");
		hashMap.put("password", "1234567");
		jedis.hmset("user",hashMap);
		System.out.println(jedis.hgetAll("user"));
	}
	@Test
	public  void demo3(){
		ApplicationContext act=new ClassPathXmlApplicationContext("applicationContext.xml","spring-redis.xml");
       
        
	}
}
