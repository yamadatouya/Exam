<%-- 成績参照検索JSP --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
            <div class="row">
                <div class="col">
                    <h3 class="h5 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報</h3>
                    <form method="get">
                        <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter-subject">
							<div class="col-4">
								<label class="form-label" for="student-f1-select">入学年度</label>
								<select class="form-select" id="student-f1-select" name="f1">
									<option value="0">--------</option>
									<c:forEach var="year" items="${ent_year_set }">
										<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
										<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-4">
								<label class="form-label" for="student-f2-select">クラス</label>
								<select class="form-select " id="student-f2-select" name="f2">
									<option value="0">--------</option>
									<c:forEach var="num" items="${class_num_set }">
										<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
										<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-4">
								<label class="form-label" for="student-f3-select">科目</label>
								<select class="form-select " id="student-f3-select" name="f3">
									<option value="0">--------</option>
									<c:forEach var="str" items="${subject_set }">
										<%-- 現在のstrと選択されていたf3が一致していた場合selectedを追記 --%>
										<option value="${str.cd}" <c:if test="${str.cd==f3}">selected</c:if>>${str.name}</option>
									</c:forEach>
								</select>
							</div>
                            <div class="col-2 text-center">
                            	<input type="hidden" name="f" value="sj">
                                <button class="btn btn-secondary" id="filter-button-subject">検索</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h3 class="h5 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報</h3>
                    <c:if test="${errors.get('f2')!=null}"><div class="col-3"><font color="FFD500">${errors.get("f2")}</font></div></c:if>
                    <form method="get">
                        <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter-student">
                            <div class="col-4">
                                <label class="form-label" for="student-number">学生番号</label>
                                <input type="text" class="form-control" id="student-number" name="studentNumber" placeholder="学生番号を入力してください" required <c:if test="${name!=null}">value="${f4}"</c:if>>
                            </div>
                            <div class="col-2 text-center">
                            	<input type="hidden" name="f" value="st">
                                <button class="btn btn-secondary" id="filter-button-student">検索</button>
                            </div>
                            <div class="mt-2 text-warning">${errors.get("f1")}</div>
                        </div>
                    </form>
                </div>
                <p><font color="00ffff">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</font></p>
            </div>
            <c:choose>
			<c:when test="${students.size()!=0}">
			<div>科目:${students.size()}</div>
			<table class="table table-hover">
			<tr>
			<th>入学年度</th>
			<th>クラス</th>
			<th>学生番号</th>
			<th>氏名</th>
			<th>1回</th>
			<th>2回</th>

									</tr>
			<c:forEach var="student" items="${students}">
			<tr>
			<td>${entYear}</td>
			<td>${classNum}</td>
			<td>${studentNo}</td>
			<td>${studentName}</td>
			<td>${points}</td>
			<td>${points}</td>



										</tr>
			</c:forEach>
			</table>
			</c:when>
			<c:otherwise>
			<div>学生情報が存在しませんでした</div>
			</c:otherwise>
			</c:choose>
        </section>
    </c:param>
</c:import>
