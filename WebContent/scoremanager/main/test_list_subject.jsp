<%-- 科目別成績一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧(科目)</h2>
            <div class="row">

                <div class="col">
                    <h3 class="h5 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報</h3>
                    <form method="get">
                        <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter-subject">
                            <div class="col-4">
                                <label class="form-label" for="student-f1-select">入学年度</label>
                                <select class="form-select" id="student-f1-select" name="f1">
                                    <option value="0">--------</option>
                                    <c:forEach var="year" items="${ent_year_set}">
                                        <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-4">
                                <label class="form-label" for="student-f2-select">クラス</label>
                                <select class="form-select" id="student-f2-select" name="f2">
                                    <option value="0">--------</option>
                                    <c:forEach var="num" items="${class_num_set}">
                                        <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-4">
                                <label class="form-label" for="student-f3-select">科目</label>
                                <select class="form-select" id="student-f3-select" name="f3">
                                    <option value="0">--------</option>
                                    <c:forEach var="str" items="${subject_set}">
                                        <option value="${str}" <c:if test="${str==f3}">selected</c:if>>${str}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-2 text-center">
                                <button class="btn btn-secondary" id="filter-button-subject">検索</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h3 class="h5 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報</h3>
                    <form method="get">
                        <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter-student">
                            <div class="col-4">
								<label class="form-label" for="student-number">学生番号</label>
								<input type="text" class="form-control" id="student-number" name="studentNumber" placeholder="学生番号を入力してください">
							</div>
                            <div class="col-2 text-center">
                                <button class="btn btn-secondary" id="filter-button-student">検索</button>
                            </div>
                        </div>
                    </form>
                </div>
								<p><font color="00ffff">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</font></p>
            </div>
        </section>
    </c:param>
</c:import>

<%-- エラーメッセージ --%>
<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧(科目)</h2>
<form action="SubjectCreateExecute.action" method="get" onsubmit="return validateSubjectCode()">
<div class="row mx-3 mb-3 py-2 align-items-center rounded" id="filter">

        <c:if test="${errors.get('f1')!=null}">
<div class="col-16"><font color="FFD500">${errors.get("f1")}</font></div>
</c:if>
<div class="col-16">
<label class="form-label" for="subjects-cd-text">科目コード</label>
<input class="form-control" type="text" placeholder="科目コードを入力してください" name="no" id="subjects-cd-text" maxlength="10" required <c:if test="${no!=null}">value="${no}"</c:if>>
</div>
<div class="col-16 error-message" id="subject-cd-error"></div> <%-- エラーメッセージを表示する要素 --%>

		                    <c:if test="${errors.get('f2')!=null}">
<div class="col-16"><font color="FFD500">${errors.get("f2")}</font></div>
</c:if>
<div class="col-16">
<label class="form-label" for="subjectst-name-text">科目名</label>
<input class="form-control" type="text" placeholder="科目名を入力してください" name="name" id="subjects-name-text" maxlength="30" required <c:if test="${name!=null}">value="${name}"</c:if>>
</div>

		                    <div class="col-2 text-center col-1 mt-3">
<button class="btn btn-secondary px-1" id="end-button" name="end">登録</button>
</div>
<a href="SubjectsList.action" class="mt-3">戻る</a>
</div>
</form>
<%-- 重複エラーメッセージの表示 --%>
<c:if test="${errors.get('duplicate')!=null}">
<div class="col-16"><font color="FFD500">${errors.get("duplicate")}</font></div>
</c:if>
</section>