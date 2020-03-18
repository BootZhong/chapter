package po;

public class Product implements Comparable<Product> {
	
	private Integer id;
	private String name;
	private String img;
	private Integer price;
	private Integer sales;
	
	//自然排序(从小到大排序)
	 /*
     * 先随便拿数组的第一个数据与其他所有数据（形参）对比大小，若返回1则说明是形参较小
     * 返回负数则说明是形参较大,若一直返回负数，则可以得出该数据是最小的，然后放在第一位
     * 然后以此类推排序完整个列表
     * 根据上面所说，把下面方法形参和本类的数据的位置对换，变成从大到小排序
     */
	@Override
	public int compareTo(Product product) {
		
		int i =this.getPrice()-product.getPrice();
		return i;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", img=" + img + ", price=" + price + ", sales=" + sales + "]";
	}
	
			

}
