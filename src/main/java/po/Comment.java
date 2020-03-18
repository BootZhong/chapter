package po;

public class Comment {
	
	private String comment;
	private String name;
	private String productName;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", name=" + name + ", productName=" + productName + "]";
	}
	
	

}
