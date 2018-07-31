<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<jsp:include page="includeHead.jsp" />
<title>商品追加確認画面</title>
</head>
<body>
<jsp:include page="adminHeader.jsp"/>
<div id="contents">
	<div id="admin-content">
		<div>
			<h1>以下の内容で登録します。</h1>
		</div>
		<s:form action="AddProductConfirmAction">
			<table class="vertical-list-table">
				<tr>
					<th><s:label value="商品ID"/></th>
				</tr>
				<tr>
					<td><s:property value="%{#session.addProductDTO.productId}"/></td>
				</tr>
				<tr>
					<th><s:label value="商品名"/></th>
				</tr>
				<tr>
					<td><s:property value="%{#session.addProductDTO.productName}"/></td>
				</tr>
				<tr>
					<th><s:label value="商品かな"/></th>
				</tr>
				<tr>
					<td><s:property value="%{#session.addProductDTO.productNameKana}"/></td>
				</tr>
				<tr>
					<th><s:label value="カテゴリ"/></th>
				</tr>
				<tr>
					<td><s:if test="%{#session.addProductDTO.categoryId == 2}">本</s:if>
					<s:elseif test="%{#session.addProductDTO.categoryId == 3}">家電・パソコン</s:elseif>
					<s:elseif test="%{#session.addProductDTO.categoryId == 4}">おもちゃ・ゲーム</s:elseif></td>
				</tr>
				<tr>
					<th><s:label value="価格(円)"/></th>
				</tr>
				<tr>
					<td><s:property value="%{#session.addProductDTO.price}"/>円</td>
				</tr>
				<tr>
					<th><s:label value="画像"/></th>
				</tr>
				<tr>
					<td><img width="150px" height="150px" src='<s:property value="%{#session.addProductDTO.imageFilePath}"/>/<s:property value="%{#session.addProductDTO.imageFileName}"/>'/></td>
				</tr>
				<tr>
					<th><s:label value="発売年月"/></th>
				</tr>
				<tr>
					<td><s:property value="%{#session.addProductDTO.releaseDate}"/></td>
				</tr>
				<tr>
					<th><s:label value="発売会社"/></th>
				</tr>
				<tr>
					<td><s:property value="%{#session.addProductDTO.releaseCompany}"/></td>
				</tr>
				<tr>
					<th><s:label value="商品詳細"/></th>
				</tr>
				<tr>
					<td><s:property value="%{#session.addProductDTO.productDescription}"/></td>
				</tr>
			</table>
		</s:form>
		<div class="submit_btn_box">
			<div id=".contents-btn-set">
				<s:form action="AddProductCompleteAction" method="post" enctype="multipart/form-date">
					<p><s:submit value="登録する" class="submit_btn"/></p>
				</s:form>
			</div>
			<s:form action="AddProductAction" method="post" enctype="multipart/form-date">
				<s:hidden name="productId" value="%{productId}"/>
				<s:hidden name="productName" value="%{productName}"/>
				<s:hidden name="productNameKana" value="%{productNameKana}"/>
				<s:hidden name="categoryId" value="%{categoyrId}"/>
				<s:hidden name="price" value="%{price}"/>
				<s:hidden name="releaseDate" value="%{releaseDate}"/>
				<s:hidden name="releaseCompany" value="%{releaseCompany}"/>
				<s:hidden name="productDescription" value="%{productDescription}"/>
				<p><s:submit value="修正する" class="submit_btn"/></p>
			</s:form>
		</div>
	</div>
	<div id="footer">
	<s:include value="footer.jsp"/>
	</div>
</div>
</body>
</html>