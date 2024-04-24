<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="scripts">
    </c:param>
    <c:param  name="content">
        <div class="container">
            <h2>学生情報登録</h2>

            <form action="StudentCreateExecute.action" method="post" class="form" onsubmit="return validateForm()">


                <div class="form-group">
                    <label for="no">科目コード</label>
                    <input type="text" id="no" name="no" class="form-control"
                    placeholder="科目コードを入力してください">
                </div>

                <div class="form-group">
                    <label for="student.name">科目名</label>
                    <input type="text" id="name" name="name" class="form-control"
                     placeholder="科目名を入力してください">
                </div>


                <button type="submit" class="btn btn-primary">登録</button>
            </form>

            <form action="StudentList.action" method="post">
                <button type="submit" class="btn btn-secondary">終了</button>
            </form>
        </div>
    </c:param>
</c:import>
