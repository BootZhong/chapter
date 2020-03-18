<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
// 检查输入的数据是否为空
function check(){
    var name = document.getElementById("name").value;
    var price = document.getElementById("price").value;
    if(name==""){
        alert("Pls specify a username");
        return false;
    }
    if(price==""){
        alert("Pls specify a price");
        return false;
    }
    if(name=="${requestScope.name}"){
        alert("名字重复了");
        return false;
    }
    return true;
}
</script>
<style type="text/css">
	.div{
		magin:70px auto;
	}
</style>
</head>
<body>
<div class="div">
    <form action="/chapter/client/updateProduct2"
    method="post"  onsubmit="return check()">
	商品名: <input type="text" name="name" id="name" placeholder="${product.name}"/><br />
	价格:   <input type="text" name="price" id="price" placeholder="${product.price}"/>	<br />
			<input type="hidden" name="id"  value="${product.id}">
		    <input type="submit" value="修改" />
	</form>
</div>	
</body>
</html>