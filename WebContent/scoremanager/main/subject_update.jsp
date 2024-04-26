<%-- 科目変更画面JSP --%>
<%-- 科目管理一覧ページからとんでこの変更画面にたどり着く --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="scripts">
    </c:param>
    <c:param  name="content">
        <div class="container">
            <h2>科目情報変更</h2>

            <form action="StudentCreateExecute.action" method="post" class="form" onsubmit="return validateForm()">

				<%-- ここは変更画面から飛んでくるページなので科目コードは変わらず --%>
                <div class="form-group">
                    <label for="no">科目コード</label>
                    <input type="text" id="no" name="no" class="form-control">
                </div>

				<%-- 個々の部分は最初のデータが枠内に入っていてカーソルを持ってくと変更可能になる --%>
                <div class="form-group">
                    <label for="student.name">科目名</label>
                    <input type="text" id="name" name="name" class="form-control"
                     placeholder="科目名を入力してください">
                </div>



                <button type="submit" class="btn btn-primary">変更</button>
            </form>

            <form action="StudentList.action" method="post">
                <button type="submit" class="btn btn-secondary">戻る</button>
            </form>
        </div>
    </c:param>
</c:import>
