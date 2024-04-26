<%-- 科目変更完了画面JSP --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp" >
<c:param name="title">
		得点管理システム
</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
	        <div class="container">

             <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
            </div>


    <div class="alert alert-success text-center" role="alert">
    <a>変更に成功しました</a>
       <p>${message}</p>
    </div>




















	<div class="text-center">
		<a href="SubjectList.action">科目一覧</a>

	</div>





	</c:param>
</c:import>