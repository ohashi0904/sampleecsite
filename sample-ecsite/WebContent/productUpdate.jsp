<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	$(function(){
		var setFileInput = $('.preview');
		setFileInput.each(function(){
			var selfFile = $(this), selfInput = $(this).find('input[type=file]');
			selfInput.change(function(){
				var file = $(this).prop('files')[0], fileRdr = new FileReader(), selfImg = selfFile.find('.imgView');

				if(!this.files.length){
					if(0 < selfImg.size()){
						selfImg.remove();
						return;
					}
				}else{
					if(file.type.match('image.*')){
						if(!(0 < selfImg.size())){
							selfFile.append('<img alt="" class="imgView">');
						}
						var prevElm = selfFile.find('.imgView');
						fileRdr.onload = function(){
							prevElm.attr('src', fileRdr.result);
						}
						fileRdr.readAsDataURL(file);
					}else{
						if(0 < selfImg.size()){
							selfImg.remove();
							return;
						}
					}
				}
			});
		});
	});
</script>
<title>商品情報変更画面</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="contents">
	<div>
	<s:form action="ProductUpdateConfirmAction">
		<h1>商品情報変更画面</h1>
		<div class="infoBox">
			<div class="info-title">商品ID</div><br>
			<s:textfield name="productId" value="%{#session.updateProductDTO.productId}"/>
				<br>
			<s:if test="!#session.productIdErrorMessageList.isEmpty()">
				<div class="errormessage">
					<s:iterator value="#session.productIdErrorMessageList">
						<s:property />
						<br>
					</s:iterator>
				</div><br>
			</s:if>
			<div class="info-title">商品名</div><br>
			<s:textfield name="productName" value="%{#session.updateProductDTO.productName}"/>
				<br>
			<s:if test="!#session.productNameErrorMessageList.isEmpty()">
				<div class="errormessage">
					<s:iterator value="#session.productNameErrorMessageList">
						<s:property/><br>
					</s:iterator>
				</div>
			</s:if><br>
			<div class="info-title">商品名かな</div><br>
			<s:textfield name="productNameKana" value="%{#session.updateProductDTO.productNameKana}"/>
				<br>
			<s:if test="!#session.productNameKanaErrorMessageList.isEmpty()">
				<div class="errormessage">
					<s:iterator value="#session.productNameKanaErrorMessageList">
						<s:property/><br>
					</s:iterator>
				</div>
			</s:if><br>
			<div class="info-title">カテゴリ</div><br>
			<s:select name="categoryId" value="%{#session.updateProductDTO.categoryId}" list="#session.mCategoryDTOList" listValue="categoryName" listKey="categoryId"/>
				<br>
			<div class="info-title">価格(円)</div><br>
			<s:textfield name="price" value="%{#session.updateProductDTO.price}"/><span>円</span>
				<br>
			<s:if test="!#session.priceErrorMessageList.isEmpty()">
				<div class="errormessage">
					<s:iterator value="#session.priceErrorMessageList">
						<s:property/><br>
					</s:iterator>
				</div>
			</s:if><br>
			<div class="info-title">画像選択</div>
			<s:file name="productImage" accept="image/jpeg"/>
				<br>
			<s:if test="!#session.productImageErrorMessageList.isEmpty()">
				<div class="errormessage">
					<s:iterator value="#session.productImageErrorMessageList">
						<s:property/><br>
					</s:iterator>
				</div>
			</s:if><br>
			<div class="info-title">発売年月</div><br>
			<s:textfield name="releaseDate" value="%{#session.updateProductDTO.releaseDate}"/>
				<br>
			<s:if test="!#session.releaseDateErrorMessageList.isEmpty()">
				<div class="errormessage">
					<s:iterator value="#session.releaseDateErrorMessageList">
						<s:property/><br>
					</s:iterator>
				</div>
			</s:if><br>
			<div class="info-title">発売会社</div><br>
			<s:textfield name="releaseCompany" value="%{#session.updateProductDTO.releaseCompany}"/>
				<br>
			<s:if test="!#session.releaseCompanyErrorMessageList.isEmpty()">
				<div class="errormessage">
					<s:iterator value="#session.releaseCompanyErrorMessageList">
						<s:property/><br>
					</s:iterator>
				</div>
			</s:if><br>
			<div class="info-title">商品詳細</div><br>
			<s:textarea name="productDescription" value="%{#session.updateProductDTO.productDescription}"/>
				<br>
			<s:if test="!#session.productDescriptionErrorMessageList.isEmpty()">
				<div class="errormessage">
					<s:iterator value="#session.productDescriptionErrorMessageList">
						<s:property/><br>
					</s:iterator>
				</div>
			</s:if><br><br>
		</div>
		<s:submit value="更新" class="submit_btn"/>
	</s:form>
	</div>
</div>
<div>
	<jsp:include page="footer.jsp"/>
</div>
</body>
</html>