<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />得点管理システム
    <c:param name="scripts">
    </c:param>
    <c:param  name="content">
        <div class="container">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>




                <label for="ent_year">入学年度</label>
                <input type="text" id="ent_year" value="${ent_year}" readonly><br>

                <label for="">学生番号</label>
                <input type="text" id="" value="${no}" readonly><br>

                <label for="">氏名</label>
                <input type="text" id="" name="name" value="${name}" maxlength="30" required><br>

                <label for="">クラス</label>
                <select id="" name="class_num">
                    <option value="${num}">${num}</option>
                </select><br>

                <label for="isAttend">在学中:</label>
                <input type="checkbox" id="isAttend" name="isAttend" checked><br>

                <input type="submit" value="変更" onclick="submitForm()">
                <a href="StudentList.action">戻る</a>
















            <form action="StudentList.action" method="post">
                <button type="submit" class="btn btn-secondary">終了</button>
            </form>
        </div>
    </c:param>
</c:import>
