<%@page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@ include file="/WEB-INF/include/base.jsp"%>
	<script type="text/javascript">
		$(function () {
          $(".delId").click(function () {
			  var yOn = confirm("确认删除吗？");
			  if(yOn = false){
			  	return false;
			  }
		  });
          $("#btSub").click(function () {
			  var pageNo = $("#pn_input").val();
			  location.href = "BookServlet?method=findAllBookByPage&pageNo="+pageNo;
		  });
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/WEB-INF/include/header.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach var="book" items="${requestScope.page.list}" >
			<tr>
				<td>${book.title}</td>
				<td>${book.price}</td>
				<td>${book.author}</td>
				<td>${book.sales}</td>
				<td>${book.stock}</td>
				<td><a href="BookServlet?method=getBookById">修改</a></td>
				<td><a class ="delId" href="BookServlet?method=delBookById&id=${book.id}">删除</a></td>
			</tr>
			</c:forEach>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_update.jsp">添加图书</a></td>
			</tr>	
		</table>
		<div id="page_nav">
			<a href="BookServlet?method=findAllBookByPage&pageNo=1">首页</a>
			<c:if test="${page.pageNo>1}">
				<a href="BookServlet?method=findAllBookByPage&pageNo=${page.pageNo-1}">上一页</a>
			</c:if>
			<c:if test="${page.pageNo<page.totalPageNo}">
				<a href="BookServlet?method=findAllBookByPage&pageNo=${page.pageNo+1}">下一页</a>
			</c:if>
			<a href="BookServlet?method=findAllBookByPage&pageNo=${page.totalPageNo}">末页</a>
			共${page.pageNo}/${page.totalPageNo}页，${page.totalRecord}条记录
			到第<input value="${page.pageNo}" name="pn" id="pn_input"/>页
			<input id="btSub" type="button" value="确定">
		</div>
	</div>
	
	<div id="bottom">
		<span>
			书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>