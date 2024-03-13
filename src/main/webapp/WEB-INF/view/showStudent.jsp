<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>


<div class="border rounded mx-auto p-4 ">
	<div class="p-3">
		<table class="table table-bordered ">
			<thead>
				<tr>
					<th>學號</th>
					<th>姓名</th>
					<th>性別</th>
					<th>身分證</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${student}">
					<tr>
						<td>${ student.sno }</td>
						<td>${ student.sname }</td>
						<td>${	student.sex == 2 ? '女' : '男'}</td>
						<td>${ student.idcard  }</td>
					</tr>

				</c:forEach>

			</tbody>
		</table>
	</div>
</div>

<%@ include file="/WEB-INF/view/footer.jsp"%>