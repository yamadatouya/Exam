<%--科目画面一覧JSP--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="/common/base.jsp">
<c:param name="title">
		得点管理システム
</c:param>
<c:param name="scripts"></c:param>

	<c:param name="content">

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>

			<div class="my-2 text-end px-4">
			<a href="SubjectsCreate.action">新規登録</a>
			</div>
		<form method="get">
		<div class="mt-2 text-warning">${errors.get("f1")}</div>

		</form>
		<c:choose>
		<c:when test="${subjects.size()!=0}">

		<table class="table table-hover">
		<tr>
		<th>科目コード</th>
		<th>科目名</th>
		<th></th>
		<th></th>
		</tr>
		<c:forEach var="subjects" items="${subjects}">
		<tr>
		<td>${subjects.code}</td>
		<td>${subjects.name}</td>

		<td class="text-center">

		<td><a href="SubjectstUpdate.action?no=${subjects.no}">変更</a></td>
		<td><a href="SubjectsDelete.action?no=${subjects.no}">削除</a></td>
		</tr>
		</c:forEach>
		</table>
		</c:when>
		<c:otherwise>
			<div>学生情報が存在しませんでした</div>
		</c:otherwise>
		</c:choose>

	</c:param>
</c:import>

</body>
</html>