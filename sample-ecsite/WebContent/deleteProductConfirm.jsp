<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<title>商品削除確認画面</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="contents">
	<div>
		<h1>商品削除確認画面</h1>
		<div class="infoBox">
			<div class="info-title">商品ID</div><br>
			<s:property value="%{#session.deleteProductDTO.productId}"/>
				<br>
			<div class="info-title">商品名</div><br>
			<s:property value="%{#session.deleteProductDTO.productName}"/>
				<br>
			<div class="info-title">商品名かな</div><br>
			<s:property value="%{#session.deleteProductDTO.productNameKana}"/>
				<br>
			<div class="info-title">商品詳細</div><br>
			<s:property value="%{#session.deleteProductDTO.productDescription}"/>
				<br>
			<div class="info-title">カテゴリ</div><br>
			<s:property value="%{#session.deleteProductDTOCategory}"/>
				<br>
			<div class="info-title">価格(円)</div><br>
			<s:property value="%{#session.deleteProductDTO.price}"/><span>円</span>
				<br>
			<div class="info-title">画像</div>
			<img src='<s:property value="%{#session.deleteProductDTO.imageFilePath}"/>/<s:property value="%{#session.deleteProductDTO.imageFileName}"/>' width="150px" height="150px"/>
				<br>
			<div class="info-title">発売年月</div><br>
			<s:property value="%{#session.deleteProductDTO.releaseDate}"/>
				<br>
			<div class="info-title">発売会社</div><br>
			<s:property value="%{#session.deleteProductDTO.releaseCompany}"/>
				<br><br>
		</div>
	</div>
	<s:form action="DeleteProductCompleteAction">
		<s:submit value="決定" class="submit_btn"/>
	</s:form>
</div>
<div>
	<jsp:include page="footer.jsp"/>
</div>
</body>
</html>