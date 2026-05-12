<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp" %>

<script type="text/javascript" src="/js/jquery-3.6.0.js"></script>

<!-- ///// content 시작 ///// -->
  <div class="p-5 mb-5 text-center</> bg-light">
    <h1 class="mb-3">My Blog</h1>
    <h4 class="mb-3">블로그에 오신 것을 환영합니다.</h4>
  </div>

  <div class="container mt-5">
    <div class="row">
      <div class="col-lg-8">
        <article>
          <input type="text" id="id" name="id" value="${articleVO.id}" />
          <header class="mb-4">
            <h1 class="fw-bolder mb-1">${articleVO.title}</h1>
            <div class="text-muted fst-italic mb-2">
            	<!-- 
            	JSTL의 fmt 중 formatDate는 날짜 형식을 yyyy-MM-dd hh:mm:ss 로써 패턴처리 함
            	 -->
            	<fmt:formatDate value="${articleVO.createdAt}" pattern="yyyy-MM-dd hh:mm:ss" />
            </div>
          </header>
          <section class="mb-5">
            <p class="fs-5 mb-4">${articleVO.content}</p>
          </section>
		  <div class="container justify-content-between">
			  <div class="float-left">
			  	<button type="button" id="modify-btn"
                  onclick="location.href='/articles/list'" 
                  class="btn btn-primary btn-sm">목록</button>
			  </div>
			  <div class="float-right">
			  	<button type="button" id="modify-btn"
                  onclick="location.href='/articles/new-article?id=${articleVO.id}'" 
                  class="btn btn-primary btn-sm">수정</button>&nbsp;
		        <button type="button" id="delete-btn"
		          class="btn btn-secondary btn-sm">삭제</button>
			  </div>
		  </div>
        </article>
      </div>
    </div>
  </div>
<!-- ///// content 끝 ///// -->

<!-- ///// js 시작 ///// -->
<script type="text/javascript">
//삭제 기능
//id를 delete-btn으로 설정한 엘리먼트를 찾아 그 엘리먼트에서 클릭 이벤트가 발생하면
//	fetch() 메서드를 통해 /api/articles/기본키 요청URI을 DELETE 요청방식으로 발생시키고
//	fetch() 메서드에 이어지는 then() 메서드는 fetch()가 잘 완료되면 연이어 실행되는 메서드임
//  location.replace() 메서드는 실행 시 사용자의 웹 브라우저 화면을 현재 주소를 기반해 옮겨주는 역할을 함
const deleteButton = document.getElementById("delete-btn");

if(deleteButton){
	deleteButton.addEventListener("click",event=>{
		let id = document.getElementById("id").value;
		console.log("id : " + id);
		fetch(`/api/articles/${id}`,{
			method:"DELETE"
		})
		.then(()=>{
			alert("삭제가 완료되었습니다");
			location.replace("/articles/list");
		});
	});
}
</script>
<!-- ///// js 끝 ///// -->

<%@ include file="../include/footer.jsp" %>   
