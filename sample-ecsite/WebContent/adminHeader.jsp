<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者用ヘッダー</title>
<script>
function goLoginAction(){
	document.getElementById("form").action="LoginAction";
}
function goLogoutAction(){
	document.getElementById("form").action="LogoutAction";
}
function goAdminProductAction(){
	document.getElementById("form").action="AdminProductAction";
}
function goAddProductAction(){
	document.getElementById("form").action="AddProductAction";
}
</script>
</head>
<body>
<header>
	<div id="header">
		<div id="header-title">
		Admin Header
		</div>
		<div id="header-menu">
			<ul>
				<s:form id="form" name="form">
<%-- 					<s:if test='#sesssion.containsKey("mCategoryDTOList")'> --%>
					<li><s:submit value="商品追加" class="submit_btn" onclick="goAddProductAction();"/></li>
					<li><s:submit value="商品一覧" class="submit_btn" onclick="goAdminProductAction();"/></li>
					<s:if test="#session.logined==1">
					<li><s:submit value="ログアウト" class="submit_btn" onclick="goLogoutAction();"/></li>
					</s:if>
					<s:else>
						<li><s:submit value="ログイン" class="submit_btn" onclick="goLoginAction();"/></li>
					</s:else>
<%-- 					</s:if> --%>
				</s:form>
			</ul>
		</div>
	</div>
</header>
</body>
</html>