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
      	<!-- 아이디 정보 저장 -->
        <input type="text" id="article-id" name="id" value="${articleVO.id}">

        <header class="mb-4">
          <input type="text" class="form-control" placeholder="제목" id="title" name="title" value="${articleVO.title}"
          	required />
        </header>
        <section class="mb-5">
          <textarea class="form-control h-25" rows="10" placeholder="내용" id="content"
          	name="content" required>${articleVO.content}</textarea>
        </section>
        <div class="container justify-content-between">
        	<div class="float-left">
        		<a href="/articles/list" class="btn btn-primary btn-sm">목록</a>
        	</div>
	        <!-- id 값이 있을 때는 [수정] 버튼을 화면에 출력 -->
	        <c:if test="${articleVO.id != null}">
	        	<div class="float-right">
	        		<button type="button" id="modify-btn" class="btn btn-primary btn-sm">수정</button>
	        	</div>
	        </c:if>
	        <!-- id 값이 없을 때는 [등록] 버튼을 화면에 출력 -->
	        <c:if test="${articleVO.id == null}">
	        	<div class="float-right">
	        		<button type="button" id="create-btn" class="btn btn-primary btn-sm">등록</button>
	        	</div>
	        </c:if>
        </div>
      </article>
    </div>
  </div>
</div>

<!-- ///// content 끝 ///// -->

<!-- ///// js 시작 ///// -->
<script type="text/javascript">
//[1] 수정 기능 시작 /////////////////////////////////////
//1) id가 modify-btn인 엘리먼트를 조회
const modifyButton = document.getElementById("modify-btn");

if(modifyButton){
	//2) 클릭 이벤트가 감지되면 수정 API 요청 
	modifyButton.addEventListener("click",event=>{
		//location : /articles/new-article?id=6
		//location.search : id=6
		let params = new URLSearchParams(location.search);
		let id = params.get("id");//6
		console.log("modifyButton->id : " + id);
		
		let data = {
			id:id,
			title:document.getElementById("title").value,
			content:document.getElementById("content").value
		};
		
		console.log("modifyButton->data : ", data);
		
		//제목 및 내용 작성 validation
		if(data.title == ""){
			alert("제목을 작성해주세요");
			document.getElementById("title").focus();
			return;
		}
		
		if(data.content == ""){
			alert("내용을 작성해주세요");
			document.getElementById("content").focus();
			return;
		}
		/*
		fetch() 메서드를 통해 수정 API로 /api/articles/ 요청 URI을 PUT 요청 방식으로 요청함.
			요청을 보낼 때 headers에 요청 형식을 지정하고, body에 HTML에 입력한 데이터를 JSON 형식으로
			바꿔 보냄. 요청이 완료되면 then() 메서드로 마무리 작업을 함
		*/
		fetch(`/api/articles/\${id}`,{
			method:"PUT",
			headers:{
				"Content-Type":"application/json",
			},
			body:JSON.stringify(data)
		})
		.then(()=>{
			alert("수정이 완료되었습니다");			
			
			console.log(`modifyButton->fetch->id : \${id}`);
			//location.replace() 메서드는 실행 시 
			//	사용자의 웹 브라우저 화면을 현재 주소를 기반해 옮겨주는 역할을 함
			location.replace(`/articles/\${id}`);
		});
	});
}
//[1] 수정 기능 끝 /////////////////////////////////////

//[2] 생성 기능 시작 ///////////////////////////////////
//1) id가 create-btn인 엘리먼트를 조회
const createButton = document.getElementById("create-btn");

if(createButton){
	//2) 클릭 이벤트가 감지되면 생성 API 요청 
	createButton.addEventListener("click",event=>{
		
		let data = {
			title:document.getElementById("title").value,
			content:document.getElementById("content").value
		};
		
		console.log("createButton->data : ", data);
		
		//제목 및 내용 작성 validation
		if(data.title == ""){
			alert("제목을 작성해주세요");
			document.getElementById("title").focus();
			return;
		}
		
		if(data.content == ""){
			alert("내용을 작성해주세요");
			document.getElementById("content").focus();
			return;
		}
		
		//fetch() 메서드를 통해 생성 API로 
		//	/api/articles 요청 URI를 POST 요청방식으로 요청을 보냄
		fetch("/api/articles",{
			method:"POST",
			headers:{
				"Content-Type":"application/json",
			},
			body:JSON.stringify(data)
		})
		.then(()=>{
			alert("등록이 완료되었습니다");			
			
			//location.replace() 메서드는 실행 시 
			//	사용자의 웹 브라우저 화면을 현재 주소를 기반해 옮겨주는 역할을 함
			location.replace("/articles/list");
		});
	});
}
//[2] 생성 기능 끝 ///////////////////////////////////
</script>
<!-- ///// js 끝 ///// -->

<%@ include file="../include/footer.jsp" %>   

