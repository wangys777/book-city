<%@page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>书城首页</title>
	<%@ include file="/WEB-INF/include/base.jsp"%>
	<script type="text/javascript">
		$(function () {
			$(".book_cond>button").click(function () {
				var min = $("input[name='min']").val();
				var max = $("input[name='max']").val();
				//获取当前页
				var pageNo = $("#pn_input").val();
				location = "/BookClientServlet?method =getBookByPageAndPrice&min"+min+"&max"+max+"&pageNo"+pageNo;
			});
		});
	</script>
</head>
<body>

<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
	<span class="wel_word">网上书城</span>
	<%@ include file="/WEB-INF/include/welcome.jsp"%>
</div>

<div id="main">
	<div id="book">
		<div class="book_cond">
			价格：<input type="text" name="min" value="${param.min}"> 元 - <input type="text" name="max" value="${param.max}"> 元 <button>查询</button>
		</div>
		<div style="text-align: center">
			<span>您的购物车中有3件商品</span>
			<div>
				您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
			</div>
		</div>
		<c:forEach var="book" items="${requestScope.page.list}"></c:forEach>
		<div class="b_list">
			<div class="img_div">
				<img class="book_img" alt="" src="static/img/default.jpg" />
			</div>
			<div class="book_info">
				<div class="book_name">
					<span class="sp1">书名:</span>
					<span class="sp2">${book.title}</span>
				</div>
				<div class="book_author">
					<span class="sp1">作者:</span>
					<span class="sp2">${book.author}</span>
				</div>
				<div class="book_price">
					<span class="sp1">价格:</span>
					<span class="sp2">${book.price}</span>
				</div>
				<div class="book_sales">
					<span class="sp1">销量:</span>
					<span class="sp2">${book.sales}</span>
				</div>
				<div class="book_amount">
					<span class="sp1">库存:</span>
					<span class="sp2">${book.stock}</span>
				</div>
				<div class="book_add">
					<button>加入购物车</button>
				</div>
			</div>
		</div>
	</div>

	<div id="page_nav">
		<c:choose>
			<c:when test="${page.totalPageNo<5}">
			<c:set var="begin" value="1"></c:set>
			<c:set var="end" value="${page.totalPageNo}"></c:set>
			</c:when>
			<c:when test="${page.pageNo<=3}">
				<c:set var="begin" value="1"></c:set>
				<c:set var="end" value="5"></c:set>
			</c:when>
			<c:when test="${page.pageNo>3 and page.pageNo<=page.totalPageNo-2}">
				<c:set var="begin" value="${page.pageNo-2}"></c:set>
				<c:set var="end" value="${page.pageNo+2}"></c:set>
			</c:when>
			<c:otherwise >
				<c:set var="begin" value="${page.totalPageNo-4}"></c:set>
				<c:set var="end" value="${page.totalPageNo}"></c:set>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${begin}" end="${end}" step="1">
			<c:if test="${i==page.pageNo}">
				【${i}】
			</c:if>
			<c:if test="${i!=page.pageNo}">
			<a href="/BookClientServlet?method=getBookByPageAndPrice&pageNo=${i}&min=${param.min}&max=${param.max}">${i}</a>
			</c:if>
		</c:forEach>
		共${page.pageNo}/${page.totalPageNo}页，${page.totalRecord}条记录
		到第<input value="${page.pageNo}" name="pn" id="pn_input"/>页
		<input type="button" value="确定">
	</div>

</div>

<div id="bottom">
		<span>
			书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>