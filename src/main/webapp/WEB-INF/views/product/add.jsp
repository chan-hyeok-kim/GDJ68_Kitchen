<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add</title>

<c:import url="../temp/bootStrap.jsp" ></c:import>
<link rel="icon" href="/resources/img/window.png" type="image/x-icon" sizes="16x16">


</head>
<body>
	<c:import url="../temp/header.jsp"></c:import>

		<section class="container mt-5">
			<h1 class="my-4">Add Page</h1>
		
			<form action="./add" method="post" enctype="multipart/form-data">
		<div class="mb-3">
		  <label for="productName" class="form-label">상품명</label>
		  <input type="text" name="productName" class="form-control" id="productName" placeholder="상품명">
		</div>
		<div class="mb-3">
		  <label for="contents" class="form-label">상품내용</label>
		  <input type="text" name="contents" class="form-control" id="contents" placeholder="내용을 입력해주세요" >
		</div>
		<div class="mb-3">
		  <label for="address" class="form-label">주소</label>
		  <input type="text" name="address" class="form-control" id="address" placeholder="주소를 입력해주세요">
		</div>
		<div class="mb-3">
		  <label for="price" class="form-label">상품가격</label>
		  <input type="text" name="price" class="form-control" id="price" placeholder="','을 빼주세요 / 가격을 입력해주세요">
		</div>
		<div class="mb-3">
		  <label for="tell" class="form-label">연락처</label>
		  <input type="text" name="phone" class="form-control" id="phone" placeholder="'-'를 넣어서 입력해주세요">
		</div>

		
	
<div class="mb-3">
  <label for="pic" class="form-label">사진첨부 (최대 5개)</label>
  <div id="fileUploadContainer">
    <!-- 동적으로 추가될 파일 업로드 필드와 삭제 버튼이 들어갈 곳입니다. -->
  <br>
  </div>
  <br>
  <button class="btn btn-primary" type="button" id="addFileField">파일 추가</button>
</div>

		

		<div class="my-3">
			<button type="submit" id="submitButton" class="btn btn-success">상품등록</button>
		</div>

	</form>
	
	<script>
document.getElementById('submitButton').addEventListener('click', function(event) {
  // 필수 정보를 확인합니다.
  var productName = document.getElementById('productName').value;
  var contents = document.getElementById('contents').value;
  var address = document.getElementById('address').value;
  var price = document.getElementById('price').value;
  var phone = document.getElementById('phone').value;

  // 필수 정보 중 하나라도 누락된 경우 알림을 표시하고 폼 제출을 막습니다.
  if (!productName || !contents || !address || !price || !phone) {
    alert('모든 필수 정보를 입력하세요.');
    event.preventDefault(); // 폼 제출을 막음
  }
});
</script>

		</section>



<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</body>
<footer>
<script src="/resources/js/bootstrap.bundle.min.js"></script>
<c:import url="../temp/footer.jsp"></c:import>
</footer>
</html>