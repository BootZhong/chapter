<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="X-UA-Compatible" content="ie=edge" />
		<title>Awesome search Box</title>
		<link href="https://cdn.bootcss.com/font-awesome/5.8.0/css/all.css" rel="stylesheet" />
		<style>
			body {
				margin: 0;
				padding: 0;				
				background-image: linear-gradient(
						45deg,
						#9fbaa8,
						#31354c
				); 
			}
			.search-box {
				position: absolute;
				top: 20%;
				left: 50%;
				transform: translate(-50%, -50%);
				background: #2f3640;
				height: 40px;
				border-radius: 40px;
				padding: 10px;
			}
			.search-btn {
				color: #e84118;
				float: right;
				width: 40px;
				height: 40px;
				border-radius: 50%;
				background: white;
				display: flex;
				justify-content: center;
				align-items: center;
				transition: 0.4s;
				text-decoration: none;
			}
			.search-txt {
				border: none;
				background: none;
				outline: none;
				float: left;
				padding: 0 6px;
				color: white;
				font-size: 16px;
				transition: 0.4s;
				line-height: 40px;
				width: 240px;
			}
            img{
                width: 350px;
                height: 300px;
            }
		</style>
	</head>
	<body>
		<div class="search-box">
			<input class="search-txt" type="text" id="search-txt" placeholder="Type to search" />
			<a class="search-btn" >
				<i class="fas fa-search"></i>
			</a>
		</div>
		
		<c:set var="mark" value="1" scope="page" ></c:set>
		<table style="padding: 20px" >
			<c:forEach var="product" items="${listProduct}">
                <c:if test="${mark==1 }">
                	<tr>
                </c:if>
				<td>
                     <a href="getProduct?name=${product.name}"><img src="/chapter/${product.img}"></a></br>
					 <a href="getProduct?name=${product.name}">${product.name}</a>
				</td>
                <c:if test="${mark%4==0 }">
					</tr><tr>
                </c:if>
                <c:set var="mark" value="${mark+1}" scope="page"></c:set>
			</c:forEach>
			</tr>
		</table>
	</body>
	<script >
        document.getElementById("search-txt").addEventListener("click",function(){
		window.location.href="searchBox.html";
		})
	</script>
</html>
