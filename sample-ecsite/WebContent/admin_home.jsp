<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/adminProduct.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('a[href^="#"]').click(function(){
			var speed = 1500;
			var href= $(this).attr("href");
			var target = $(href == "#" || href == "" ? 'html' : href);
			var position = target.offset().top;
			$("body, html").animate({scrollTop:position}, speed, "swing");
			return false;
			});
		});
</script>

<title>管理者画面</title>
</head>
<body>
<div id="top"></div>
<jsp:include page="adminHeader.jsp" />
<div id="main">
	<div id="admin-contents" class="center">
		<h1>商品一覧</h1>
		<s:iterator value="%{productList}">
		<div class="admin-list-box">
			<img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' class="image_box"/><br>
			<s:property value="productName"/><br>
			<s:property value="price"/><span>円</span><br>
			<s:form action="ProductUpdateAction">
				<s:hidden name="id" value="%{id}"/>
				<s:submit value="編集" class="submit_btn"/>
			</s:form>
			<s:form action="DeleteProductConfirmAction">
				<s:hidden name="id" value="%{id}"/>
				<s:submit value="削除" class="submit_btn_delete"/>
			</s:form><br><br>
		</div>
		</s:iterator>
	</div>
<a id="under" href="#top">ページTOPへ</a>
</div>

<s:include value="footer.jsp" />
</body>
</html>