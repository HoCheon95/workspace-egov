<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/header.jsp" %>

<!-- ///// content 시작 ///// -->
	<p>${articleVOList}</p>
	<div class="p-5 mb-5 text-center</> bg-light">
        <h1 class="mb-3">My Blog</h1>
        <h4 class="mb-3">블로그에 오신 것을 환영합니다.</h4>
    </div>

    <div class="container">
        <button type="button" id="create-btn"
                onclick="location.href='/articles/new-article'"
                class="btn btn-secondary btn-sm mb-3">글 등록</button>
        <!-- 
        articleVOList : List<ArticleVO> 타입
        articleVOList 객체 안의 articleVO 개수만큼 반복
         -->
        <c:forEach var="articleVO" items="${articleVOList}" varStatus="stat">
        <div class="row-6">
            <div class="card">
            	<!-- articleVO 객체의 프로퍼티인 id의 값을 출력 -->
                <div class="card-header">${articleVO.id}</div>
                <div class="card-body">
                    <h5 class="card-title">${articleVO.title}</h5>
                    <p class="card-text">${articleVO.content}</p>
                    <!-- 
                    [보러 가기]를 클릭했을 때 /articles/1 와 같은 방법으로 요청URI인 글 상세를 요청함.
                     -->
                    <a href="/articles/${articleVO.id}" class="btn btn-primary">보러가기</a>
                </div>
            </div>
            <br>
        </div>
        </c:forEach>
    </div>

<!--     <script src="/js/article.js"></script> -->
<!-- ///// content 끝 ///// -->

<%@ include file="../include/footer.jsp" %>      
