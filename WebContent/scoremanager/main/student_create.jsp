
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
                    <label for="student-f1-select">入学年度</label>
                    <select class="form-select" id="student-f1-select" name="f1">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </div>

				<%-- エラー表示 --%>
                <c:if test="${errors.get('f1')!=null}"><div class="col-16"><font color="FFD500">${errors.get("f1")}</font></div></c:if>

                <div class="form-group">
                    <label class="form-label" for="no">学年番号</label>
                    <input type="text" id="no" name="no" class="form-control" placeholder="学生番号を入力してください" name="no" id="no" maxlength="10" required <c:if test="${no!=null}">value="${no}"</c:if>>
                </div>

				<%-- エラー表示 --%>
				<c:if test="${errors.get('f2')!=null}"><div class="col-16"><font color="FFD500">${errors.get("f2")}</font></div></c:if>

                <div class="form-group">
                    <label class="form-label" for="student.name">氏名</label>
                    <input type="text" id="name" name="name" class="form-control" placeholder="氏名を入力してください" name="name" id="student-name-text" maxlength="30" required <c:if test="${name!=null}">value="${name}"</c:if>">
                </div>

                <div class="form-group">
                    <label for="student-f2-select">クラス</label>
                    <select class="form-select" id="student-f2-select" name="f2">
                        <option value="0">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">登録して終了</button>
            </form>
            <form action="StudentList.action" method="post">
                <button type="submit" class="btn btn-secondary">戻る</button>
            </form>
        </div>
    </c:param>
</c:import>
