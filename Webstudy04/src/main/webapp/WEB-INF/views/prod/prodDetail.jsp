<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${prod.prodName} - 상품 상세</title>
    </head>
    <body class="bg-light">

        <div class="container mt-5 mb-5">

            <div class="card shadow">
                <div class="card-body p-4">

                    <div class="row">
                        <!-- 상품 이미지 영역 -->
                        <div class="col-md-5 text-center border-end">
                            <c:choose>
                                <c:when test="${not empty prod.prodImg}">
                                    <img src="${pageContext.request.contextPath}/resources/images/prod/${prod.prodImg}"
                                    alt="${prod.prodName}"
                                    class="img-fluid rounded border"
                                    style="max-height: 420px; object-fit: contain;">
                                </c:when>
                                <c:otherwise>
                                    <div class="d-flex align-items-center justify-content-center bg-secondary-subtle rounded border"
                                    style="height: 420px;">
                                    <span class="text-muted">상품 이미지 없음</span>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <!-- 상품 구매 정보 영역 -->
                    <div class="col-md-7 ps-md-5 mt-4 mt-md-0">
                        <div class="mb-3">
                            <span class="badge bg-primary">${prod.lprod.lprodName}</span>
                            <span class="badge bg-secondary">${prod.lprodGu}</span>
                        </div>

                        <h2 class="fw-bold mb-3">${prod.prodName}</h2>

                        <p class="text-muted mb-4">
                            상품코드 : ${prod.prodId}
                        </p>

                        <div class="mb-4">
                            <div class="d-flex justify-content-between border-bottom py-2">
                                <span class="text-muted">제조사</span>
                                <strong>${prod.buyer.buyerName}</strong>
                            </div>

                            <div class="d-flex justify-content-between border-bottom py-2">
                                <span class="text-muted">판매가</span>
                                <strong class="fs-4 text-danger">${prod.prodPrice}원</strong>
                            </div>

                            <div class="d-flex justify-content-between border-bottom py-2">
                                <span class="text-muted">세일가</span>
                                <strong>${prod.prodSale}원</strong>
                            </div>

                            <div class="d-flex justify-content-between border-bottom py-2">
                                <span class="text-muted">마일리지</span>
                                <span>${prod.prodMileage}점</span>
                            </div>

                            <div class="d-flex justify-content-between border-bottom py-2">
                                <span class="text-muted">배송방법</span>
                                <span>${prod.prodDelivery}</span>
                            </div>

                            <div class="d-flex justify-content-between border-bottom py-2">
                                <span class="text-muted">재고수량</span>
                                <span>${prod.prodTotalstock}개</span>
                            </div>
                        </div>

                        <form method="post" action="${pageContext.request.contextPath}/cart/insert">
                            <input type="hidden" name="prodId" value="${prod.prodId}">

                            <div class="mb-4">
                                <label for="cartQty" class="form-label fw-bold">구매 수량</label>
                                <input type="number"
                                class="form-control"
                                id="cartQty"
                                name="cartQty"
                                value="1"
                                min="1"
                                max="${prod.prodTotalstock}">
                            </div>

                            <div class="d-grid gap-2 d-md-flex">
                                <button type="submit" class="btn btn-primary btn-lg flex-fill">
                                    장바구니 담기
                                </button>

                                <button type="button" class="btn btn-danger btn-lg flex-fill">
                                    바로 구매
                                </button>
                            </div>
                        </form>

                        <div class="mt-4 text-end">
                            <a href="${pageContext.request.contextPath}/prod/list" class="btn btn-outline-secondary">
                                목록으로
                            </a>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <!-- 상품 상세 설명 -->
        <div class="card shadow mt-4">
            <div class="card-header bg-white">
                <h5 class="mb-0 fw-bold">상품 상세 설명</h5>
            </div>

            <div class="card-body">
                <h6 class="fw-bold text-secondary">상품 개요</h6>
                <p>${prod.prodOutline}</p>

                <hr>

                <h6 class="fw-bold text-secondary">상세 정보</h6>
                <p>${prod.prodDetail}</p>

                <hr>

                <div class="row">
                    <div class="col-md-6">
                        <table class="table table-sm">
                            <tbody>
                                <tr>
                                    <th class="bg-light">크기</th>
                                    <td>${prod.prodSize}</td>
                                </tr>
                                <tr>
                                    <th class="bg-light">색상</th>
                                    <td>${prod.prodColor}</td>
                                </tr>
                                <tr>
                                    <th class="bg-light">판매 단위</th>
                                    <td>${prod.prodUnit}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-md-6">
                        <table class="table table-sm">
                            <tbody>
                                <tr>
                                    <th class="bg-light">입고일</th>
                                    <td>${prod.prodInsdate}</td>
                                </tr>
                                <tr>
                                    <th class="bg-light">입고 수량</th>
                                    <td>${prod.prodQtyin}</td>
                                </tr>
                                <tr>
                                    <th class="bg-light">판매 수량</th>
                                    <td>${prod.prodQtysale}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
            <div class="card-footer text-end">
                <a href="${pageContext.request.contextPath}/prod/list" class="btn btn-secondary">목록으로</a>
                <c:url value="/prod/modify" var="modifyUrl">
                    <c:param name="what" value="${prod.prodId}"/>
                </c:url>
                <a href="${modifyUrl}" class="btn btn-warning">수정하기</a>
            </div>
        </div>

    </div>
</body>
</html>