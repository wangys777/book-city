<%@page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城会员注册页面</title>
	<%@ include file="/WEB-INF/include/base.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#sub_btn").click(function(){
			//校验用户名(字母数字_-  6-18)
			var uname = $("#username").val();
			var regUname = /^[a-zA-Z0-9_-]{6,18}$/;
			if(!regUname.test(uname)){
				alert("用户名不合法,请重新输入（字母数字_-  6-18）");
				return false;
			}
			//校验密码
			var pass = $("#password").val();
			var regPass = /^[a-zA-Z0-9_-]{6,18}$/;
			if(!regPass.test(pass)){
				alert("密码不合法,请重新输入（字母数字_-  6-18）");
				return false;
			}
			//确认密码
			var rePass = $("#repwd").val();
			if(rePass != pass){
				alert("两次密码不一致,请重新输入（字母数字_-  6-18）");
				return false;
			}
			//校验邮箱  zs-+ls@
			var email = $("#email").val();
			var regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if(!regEmail.test(email)){
				alert("邮箱格式不正确,请重新输入");
				return false;
			}
		});
		//验证码
		$("#kapImg").click(function () {
			//img src属性赋值
			$(this).attr("src","KaptchaServlet");
		});
	});

</script>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册书城会员</h1>
									<c:if test="${not empty requestScope.msg}"></c:if>
									<%--<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
								<span class="errorMsg">${requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="UserServlet?method=regist" method="post">
									<%--<input type="hidden" name="method" value="regist">--%>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" value="${param.username}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" value="${param.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code" name="code"/>
									<img id = "kapImg" alt="" src="KaptchaServlet" style="float: right; margin-right: 40px;width: 80px;height: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>