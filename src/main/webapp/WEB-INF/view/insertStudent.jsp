<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css">

<script>
	function submitFrom(flagValue) {
		document.getElementById("flag").value = flagValue;
		//document.getElementById("checkIn").action = "./addcheckIn/${empBook.empId}?flag=" + flagValue;
		document.getElementById("checkIn").submit();
	}
</script>
</head>
<body>


	<div
		class="d-flex justify-content-center align-items-center vh-100 mx-auto ">
		<form class="needs-validation border rounded mx-auto p-4" novalidate
			method="post" id="Form"
			action="${ pageContext.request.contextPath }/jdbc">
			<fieldset>
				<h4 class="text-center mb-3">請輸入欲產生的學生資料筆數</h4>
				<div>
					<input type="number" class="form-control" id="num" name="num"
						placeholder="請輸入學生資料筆數" required>
					<div class="invalid-feedback">請輸入帳號</div>
				</div>
				<input type="hidden" id="flag" name="flag" value="1" /> <input
					type="hidden" id="flag" name="flag" value="2" />
				<div class="d-flex justify-content-center mt-4 mx-3">
					<button class="btn mx-3 align-items-center m-4"
						onclick="submitFrom(1)" style="background-color: #e3f2fd"
						type="submit">提交</button>
					<button onclick="submitFrom(2)"
						class="btn mx-3 align-items-center m-4" id="checkOutbtn"
						style="background-color: #ccdce8">查詢</button>
				</div>

				<c:choose>
					<c:when test="${not empty success}">
						<p style="color: ${success ? 'green' : 'red'};">${success ? '新增成功，成功筆數: ' : '新增失敗，失敗筆數: '}
							${message}</p>
					</c:when>
					<c:otherwise>
						<!-- 不显示任何消息 -->
					</c:otherwise>
				</c:choose>
			</fieldset>

		</form>
	</div>
</body>