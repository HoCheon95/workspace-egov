<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>제품 추가</title>
</head>
<body class="bg-light" data-cpath="${pageContext.request.contextPath}">

    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-11">

                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">
                            <c:choose>
                                <c:when test="${not empty prod.prodId}">제품 수정</c:when>
                                <c:otherwise>제품 추가</c:otherwise>
                            </c:choose>
                        </h4>
                    </div>

                    <div class="card-body p-4">
                        <form method="post" action="${pageContext.request.contextPath}${empty prod.prodId ? '/prod/create' : '/prod/modify'}">
                            <%-- 수정 시에만 prodId를 서버로 보냄 --%>
                            <c:if test="${not empty prod.prodId}">
                                <input type="hidden" name="prodId" value="${prod.prodId}" />
                                <span class="text-danger">${errors.prodId}</span>
                            </c:if>
                            <div class="row">
                                <h5 class="mb-3 text-secondary border-bottom pb-2">기본 정보</h5>

                                <div class="col-md-6 mb-3">
                                    <label for="prodName" class="form-label">상품명 <span class="text-danger">*</span></label>
                                    <input type="text"
                                           class="form-control"
                                           id="prodName"
                                           name="prodName"
                                           value="${prod.prodName}">
                                    <span class="text-danger">${errors.prodName}</span>
                                </div>

                                <div class="col-md-3 mb-3">
                                    <label for="lprodGu" class="form-label">분류코드 <span class="text-danger">*</span></label>
                                    <select class="form-select" id="lprodGu" name="lprodGu" data-selected="${prod.lprodGu}">
                                        <option value="">분류를 선택하세요</option>
                                        <c:forEach items="${lprodList}" var="lprod">
                                            <option value="${lprod.lprodGu}" ${prod.lprodGu eq lprod.lprodGu ? 'selected' : ''}>
                                                ${lprod.lprodName} (${lprod.lprodGu})
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <span class="text-danger">${errors.lprodGu}</span>
                                </div>

                                <div class="col-md-3 mb-3">
                                    <label for="buyerId" class="form-label">거래처코드 <span class="text-danger">*</span></label>
                                    <select class="form-select" id="buyerId" name="buyerId" data-selected="${prod.buyerId}">
                                        <option value="">거래처를 선택하세요</option>
                                        <c:forEach items="${buyerList}" var="buyer">
                                            <option value="${buyer.buyerId}" ${prod.buyerId eq buyer.buyerId ? 'selected' : ''}>
                                                ${buyer.buyerName} (${buyer.buyerId})
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <span class="text-danger">${errors.buyerId}</span>
                                </div>

                                <h5 class="mt-4 mb-3 text-secondary border-bottom pb-2">가격 정보</h5>

                                <div class="col-md-4 mb-3">
                                    <label for="prodCost" class="form-label">매입단가</label>
                                    <input type="number"
                                           class="form-control"
                                           id="prodCost"
                                           name="prodCost"
                                           value="${prod.prodCost}">
                                    <span class="text-danger">${errors.prodCost}</span>
                                </div>

                                <div class="col-md-4 mb-3">
                                    <label for="prodPrice" class="form-label">매출단가</label>
                                    <input type="number"
                                           class="form-control"
                                           id="prodPrice"
                                           name="prodPrice"
                                           value="${prod.prodPrice}">
                                    <span class="text-danger">${errors.prodPrice}</span>
                                </div>

                                <div class="col-md-4 mb-3">
                                    <label for="prodSale" class="form-label">할인판매단가</label>
                                    <input type="number"
                                           class="form-control"
                                           id="prodSale"
                                           name="prodSale"
                                           value="${prod.prodSale}">
                                    <span class="text-danger">${errors.prodSale}</span>
                                </div>

                                <h5 class="mt-4 mb-3 text-secondary border-bottom pb-2">상품 설명</h5>

                                <div class="col-md-12 mb-3">
                                    <label for="prodOutline" class="form-label">상품 개요</label>
                                    <input type="text"
                                           class="form-control"
                                           id="prodOutline"
                                           name="prodOutline"
                                           value="${prod.prodOutline}">
                                    <span class="text-danger">${errors.prodOutline}</span>
                                </div>

                                <div class="col-md-12 mb-3">
                                    <label for="prodDetail" class="form-label">상품 상세</label>
                                    <textarea class="form-control"
                                              id="prodDetail"
                                              name="prodDetail"
                                              rows="5">${prod.prodDetail}</textarea>
                                    <span class="text-danger">${errors.prodDetail}</span>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="prodImg" class="form-label">상품 이미지 파일명</label>
                                    <input type="text"
                                           class="form-control"
                                           id="prodImg"
                                           name="prodImg"
                                           value="${prod.prodImg}"
                                           placeholder="예: P101000001.jpg">
                                    <span class="text-danger">${errors.prodImg}</span>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="prodInsdate" class="form-label">입고일</label>
                                    <input type="date"
                                           class="form-control"
                                           id="prodInsdate"
                                           name="prodInsdate"
                                           value="${prod.prodInsdate}">
                                    <span class="text-danger">${errors.prodInsdate}</span>
                                </div>

                                <h5 class="mt-4 mb-3 text-secondary border-bottom pb-2">재고 정보</h5>

                                <div class="col-md-3 mb-3">
                                    <label for="prodTotalstock" class="form-label">전체재고량</label>
                                    <input type="number"
                                           class="form-control"
                                           id="prodTotalstock"
                                           name="prodTotalstock"
                                           value="${prod.prodTotalstock}">
                                    <span class="text-danger">${errors.prodTotalstock}</span>
                                </div>

                                <div class="col-md-3 mb-3">
                                    <label for="prodProperstock" class="form-label">적정재고량</label>
                                    <input type="number"
                                           class="form-control"
                                           id="prodProperstock"
                                           name="prodProperstock"
                                           value="${prod.prodProperstock}">
                                    <span class="text-danger">${errors.prodProperstock}</span>
                                </div>

                                <div class="col-md-3 mb-3">
                                    <label for="prodQtyin" class="form-label">입고수량</label>
                                    <input type="number"
                                           class="form-control"
                                           id="prodQtyin"
                                           name="prodQtyin"
                                           value="${prod.prodQtyin}">
                                    <span class="text-danger">${errors.prodQtyin}</span>
                                </div>

                                <div class="col-md-3 mb-3">
                                    <label for="prodQtysale" class="form-label">판매수량</label>
                                    <input type="number"
                                           class="form-control"
                                           id="prodQtysale"
                                           name="prodQtysale"
                                           value="${prod.prodQtysale}">
                                    <span class="text-danger">${errors.prodQtysale}</span>
                                </div>

                                <h5 class="mt-4 mb-3 text-secondary border-bottom pb-2">기타 정보</h5>

                                <div class="col-md-3 mb-3">
                                    <label for="prodSize" class="form-label">크기</label>
                                    <input type="text"
                                           class="form-control"
                                           id="prodSize"
                                           name="prodSize"
                                           value="${prod.prodSize}">
                                    <span class="text-danger">${errors.prodSize}</span>
                                </div>

                                <div class="col-md-3 mb-3">
                                    <label for="prodColor" class="form-label">색상</label>
                                    <input type="text"
                                           class="form-control"
                                           id="prodColor"
                                           name="prodColor"
                                           value="${prod.prodColor}">
                                    <span class="text-danger">${errors.prodColor}</span>
                                </div>

                                <div class="col-md-3 mb-3">
                                    <label for="prodDelivery" class="form-label">배송방법</label>
                                    <input type="text"
                                           class="form-control"
                                           id="prodDelivery"
                                           name="prodDelivery"
                                           value="${prod.prodDelivery}">
                                    <span class="text-danger">${errors.prodDelivery}</span>
                                </div>

                                <div class="col-md-3 mb-3">
                                    <label for="prodUnit" class="form-label">판매단위</label>
                                    <input type="text"
                                           class="form-control"
                                           id="prodUnit"
                                           name="prodUnit"
                                           value="${prod.prodUnit}">
                                    <span class="text-danger">${errors.prodUnit}</span>
                                </div>

                                <div class="col-md-4 mb-3">
                                    <label for="prodMileage" class="form-label">상품별마일리지</label>
                                    <input type="number"
                                           class="form-control"
                                           id="prodMileage"
                                           name="prodMileage"
                                           value="${prod.prodMileage}">
                                    <span class="text-danger">${errors.prodMileage}</span>
                                </div>
                            </div>

                            <hr class="my-4">

                            <div class="d-flex justify-content-end pb-3">
                                <a href="${pageContext.request.contextPath}/prod/list" class="btn btn-secondary me-2">목록으로</a>
                                <button type="reset" class="btn btn-outline-secondary me-2">초기화</button>
                                <%-- 버튼 문구 변경 --%>
                                <button type="submit" class="btn btn-primary px-4">
                                    ${empty prod.prodId ? '제품 등록 완료' : '제품 수정 완료'}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value='/resources/js/app/prod/prodForm.js'/>"></script>
</body>
</html>