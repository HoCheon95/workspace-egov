<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buyer Insert/Update Form</title>
    <style>
        body { background-color: #f8f9fa; padding: 20px; }
        .container { max-width: 800px; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.1); }
        .form-label { font-weight: 600; }
    </style>
</head>
<body>

<div class="container">
    <h2 class="mb-4 text-center">거래처 등록/수정</h2>
        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">
                <ul class="mb-0">
                    <c:forEach var="error" items="${errorMsg}">
                        <li>${error.field} ${error.defaultMessage}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    <form:form method="POST"  modelAttribute="buyer" novalidate="novalidate" class="needs-validation" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="" />
        <input type="file" name="buyerImage" />
        <div class="row g-3 mb-4">
            <h5 class="border-bottom pb-2">기본 정보</h5>
                <input type="text" readonly id="buyerId" name="buyerId" value="${buyer.buyerId}">
                <form:errors path="buyerId" cssClass="text-danger" element="span" />

            <div class="col-md-4">
                <label for="buyerName" class="form-label">거래처명</label>
                <input type="text" class="form-control" id="buyerName" name="buyerName" value="${buyer.buyerName}" required>
                <form:errors path="buyerName" cssClass="text-danger" element="span" />
            </div>
            <div class="col-md-4">
                <label for="lprodGu" class="form-label">상품 분류코드</label>
                <input type="hidden" id="lprodGuHidden" name="lprodGuHidden" value="${buyer.lprodGu}">
                <select class="form-select" id="lprodGu" name="lprodGu" required  data-selected="${buyer.lprodGu}"
                   <c:if test="${formType == 'update'}">
                    disabled
                   </c:if>
                 >

                </select>
            </div>
        </div>

        <div class="row g-3 mb-4">
            <h5 class="border-bottom pb-2">결제 계좌 정보</h5>
            <div class="col-md-4">
                <label for="buyerBank" class="form-label">거래 은행</label>
                <input type="text" class="form-control" id="buyerBank" name="buyerBank" value="${buyer.buyerBank}">
                <form:errors path="buyerBank" cssClass="text-danger" element="span" />
            </div>
            <div class="col-md-4">
                <label for="buyerBankno" class="form-label">계좌 번호</label>
                <input type="text" class="form-control" id="buyerBankno" name="buyerBankno" value="${buyer.buyerBankno}">
                <form:errors path="buyerBankno" cssClass="text-danger" element="span" />
            </div>
            <div class="col-md-4">
                <label for="buyerBankname" class="form-label">예금주</label>
                <input type="text" class="form-control" id="buyerBankname" name="buyerBankname" value="${buyer.buyerBankname}">
                <form:errors path="buyerBankname" cssClass="text-danger" element="span" />
            </div>
        </div>

        <div class="row g-3 mb-4">
            <h5 class="border-bottom pb-2">소재지 정보</h5>
            <div class="col-md-3">
                <label for="buyerZip" class="form-label">우편번호</label>
                <input type="text" class="form-control" id="buyerZip" name="buyerZip" value="${buyer.buyerZip}">
                <form:errors path="buyerZip" cssClass="text-danger" element="span" />
            </div>
            <div class="col-md-9 align-self-end">
                <button type="button" class="btn btn-outline-secondary">주소 검색</button>
                <form:errors path="buyerAdd1" cssClass="text-danger" element="span" />
            </div>
            <div class="col-12">
                <label for="buyerAdd1" class="form-label">기본 주소</label>
                <input type="text" class="form-control" id="buyerAdd1" name="buyerAdd1" value="${buyer.buyerAdd1}">
                <form:errors path="buyerAdd1" cssClass="text-danger" element="span" />
            </div>
            <div class="col-12">
                <label for="buyerAdd2" class="form-label">상세 주소</label>
                <input type="text" class="form-control" id="buyerAdd2" name="buyerAdd2" value="${buyer.buyerAdd2}">
                <form:errors path="buyerAdd2" cssClass="text-danger" element="span" />
            </div>
        </div>

        <div class="row g-3 mb-4">
            <h5 class="border-bottom pb-2">담당자 및 연락처</h5>
            <div class="col-md-6">
                <label for="buyerComtel" class="form-label">회사 전화번호</label>
                <input type="tel" class="form-control" id="buyerComtel" name="buyerComtel" placeholder="02-123-4567" value="${buyer.buyerComtel}">
                <form:errors path="buyerComtel" cssClass="text-danger" element="span" />
            </div>
            <div class="col-md-6">
                <label for="buyerFax" class="form-label">팩스 번호</label>
                <input type="tel" class="form-control" id="buyerFax" name="buyerFax" value="${buyer.buyerFax}">
                <form:errors path="buyerFax" cssClass="text-danger" element="span" />
            </div>
            <div class="col-md-6">
                <label for="buyerMail" class="form-label">이메일</label>
                <input type="email" class="form-control" id="buyerMail" name="buyerMail" placeholder="example@mail.com" value="${buyer.buyerMail}">
                <form:errors path="buyerMail" cssClass="text-danger" element="span" />
            </div>
            <div class="col-md-3">
                <label for="buyerCharger" class="form-label">담당자명</label>
                <input type="text" class="form-control" id="buyerCharger" name="buyerCharger" value="${buyer.buyerCharger}">
                <form:errors path="buyerCharger" cssClass="text-danger" element="span" />
            </div>
 
        </div>

        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button type="reset" class="btn btn-secondary me-md-2">초기화</button>
            <button type="submit" class="btn btn-primary px-5">등록/수정 하기</button>
        </div>
        
    </form:form>
</div>
    <script type="text/javascript" src="/js/app/buyer/buyerForm.js"></script>

</body>
</html>