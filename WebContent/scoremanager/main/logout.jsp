<%-- ログアウトJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">
		得点管理システム
</c:param>

<c:param name="content">
<h2>ログアウト</h2>
<p>ログアウトしました</p>
<a href="../Login.action">ログイン</a>
</c:param>

</c:import>