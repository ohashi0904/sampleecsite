<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/adminProduct.css">

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
<title>商品追加画面</title>
</head>
<body>
<jsp:include page="adminHeader.jsp"/>
<div id="main">
	<div id="admin-contents">
		<div class="title">
			<h1>商品追加画面</h1>
		</div>
		<div class="AllBox">
			<s:form action="AddProductConfirmAction" method="post" enctype="multipart/form-data">
				<div class="text-title">
				商品ID<br>
				</div>
				<s:textfield name="productId" value="%{productId}" class="txt2"/>
				<br>
				<s:if test="!#session.productIdErrorMessageList.isEmpty()">
					<div class="errormessage">
						<s:iterator value="#session.productIdErrorMessageList">
							<s:property />
							<br>
						</s:iterator>
					</div>
				</s:if><br>
				<div class="text-title">商品名<br></div>
				<s:textfield name="productName" value="%{productName}" class="txt2"/><br>
				<s:if test="!#session.productNameErrorMessageList.isEmpty()">
					<div class="errormessage">
						<s:iterator value="#session.productNameErrorMessageList">
							<s:property/><br>
						</s:iterator>
					</div>
				</s:if><br>
				<div class="text-title">商品かな<br></div>
				<s:textfield name="productNameKana" value="%{productNameKana}" class="txt2"/><br>
				<s:if test="!#session.productNameKanaErrorMessageList.isEmpty()">
					<div class="errormessage">
						<s:iterator value="#session.productNameKanaErrorMessageList">
							<s:property/><br>
						</s:iterator>
					</div>
				</s:if><br>
				<div class="text-title">カテゴリ<br></div>
				<s:select name="categoryId" list="#session.mCategoryDTOList" listValue="categoryName" listKey="categoryId"/><br><br>
				<div class="text-title">価格(円)<br></div>
				<s:textfield name="price" value="%{price}" class="txt2"/><br>
				<s:if test="!#session.priceErrorMessageList.isEmpty()">
					<div class="errormessage">
						<s:iterator value="#session.priceErrorMessageList">
							<s:property/><br>
						</s:iterator>
					</div>
				</s:if><br>
				<div class="text-title">画像ファイル選択<br></div>
				<div class="preview">
					<s:file name="productImage" accept="image/jpeg"/>
				</div><br>
				<s:if test="!#session.productImageErrorMessageList.isEmpty()">
					<div class="errormessage">
						<s:iterator value="#session.productImageErrorMessageList">
							<s:property/><br>
						</s:iterator>
					</div>
				</s:if><br>
				<div class="text-title">発売年月<br></div>
				<s:textfield name="releaseDate" value="%{releaseDate}" class="txt2"/><br>
				<s:if test="!#session.releaseDateErrorMessageList.isEmpty()">
					<div class="errormessage">
						<s:iterator value="#session.releaseDateErrorMessageList">
							<s:property/><br>
						</s:iterator>
					</div>
				</s:if><br><br>
				<div class="text-title">発売会社<br></div>
				<s:textfield name="releaseCompany" value="%{releaseCompany}" class="txt2"/><br>
				<s:if test="!#session.releaseCompanyErrorMessageList.isEmpty()">
					<div class="errormessage">
						<s:iterator value="#session.releaseCompanyErrorMessageList">
							<s:property/><br>
						</s:iterator>
					</div>
				</s:if><br>
				<div class="text-title">商品詳細<br></div>
				<s:textarea name="productDescription" value="%{productDescription}" class="productDescription"/><br>
				<s:if test="!#session.productDescriptionErrorMessageList.isEmpty()">
					<div class="errormessage">
						<s:iterator value="#session.productDescriptionErrorMessageList">
							<s:property/><br>
						</s:iterator>
					</div>
				</s:if><br><br>
				<s:submit value="確認画面へ" class="submit_btn"/>
			</s:form>
			<a href="AdminProductAction" id="correction">戻る</a>
		</div>
	</div>
	<div id="footer"></div>
</div>
</body>
</html>