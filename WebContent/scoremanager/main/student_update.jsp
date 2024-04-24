<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="scripts" value="" />
    <c:param  name="content">
        <div class="container">


        <form action="StudentUpdateExecute.action">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

            <label>入学年度</label><br>
            <input class="ps-3 border-0" type="text" id="ent_year" name="ent_year" value="${ent_year}" readonly><br>


            <label>学生番号</label><br>
            <input class="ps-3 border-0" type="text" name="no" id="no" value="${no}" readonly><br>

			<div class="me-4">
            <label>氏名</label><br>
            <input type="text" id="" name="name" value="${name}" maxlength="30" required><br>
            </div>

			<div class="me-4">
            <label>クラス</label><br>
            <select id="" name="class_num">
                <option  value="0"></option>
                <c:forEach var="num" items="${num}">
                    <option>${num}</option>
                </c:forEach>
            </select><br>
            </div>

            <label>在学中:</label>
            <input type="checkbox" id="si_attend" name="si_attend" checked><br>

            <button input="submit">変更</button>
        </form>


            <a href="StudentList.action">戻る</a>

            <form action="StudentList.action" method="post">
                <button type="submit" class="btn btn-secondary">終了</button>
            </form>
        </div>
    </c:param>
</c:import>

