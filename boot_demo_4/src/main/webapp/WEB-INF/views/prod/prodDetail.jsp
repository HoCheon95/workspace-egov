<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 상세 조회 - ${prod.prodName}</title>
    <style>
        body { background-color: #f4f7f6; }
        .detail-label { background-color: #e9ecef; font-weight: bold; width: 30%; }
        .prod-img-container { background: #fff; border: 1px solid #dee2e6; border-radius: 10px; padding: 20px; text-align: center; }
        .price-badge { font-size: 1.2rem; }
    </style>
</head>
<body>

<div class="container my-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold"><span class="text-primary">|</span> 상품 상세 정보</h2>
        <div>
            <a href="productList.do" class="btn btn-outline-secondary">목록으로</a>
            <a href="/prod/edit?id=${prod.prodId}" class="btn btn-warning">수정</a>
        </div>
    </div>

    <div class="row g-4">
        <div class="col-lg-4">
            <div class="prod-img-container shadow-sm mb-4">
                <c:choose>
                    <c:when test="${not empty prod.prodImg}" >
                        <img src="/prodimages/${prod.prodImg}" >
                    </c:when>
                    <c:otherwise>
                        <div class="py-5 bg-light text-muted">이미지가 없습니다.</div>
                    </c:otherwise>
                </c:choose>
                <hr>
                <h4 class="mt-3 fw-bold">${prod.prodName}</h4>
                <p class="text-muted">${prod.prodOutline}</p>
                <div class="d-grid gap-2">
                    <span class="badge bg-primary price-badge p-2">판매가: <fmt:formatNumber value="${prod.prodPrice}" pattern="#,###"/>원</span>
                </div>
            </div>

            <div class="card shadow-sm">
                <div class="card-header bg-dark text-white fw-bold">제조사 정보</div>
                <div class="card-body">
                <a target="_blank" href="/buyer/detail?id=${prod.buyer.buyerId}" class="text-decoration-none">
                    <h5 class="card-title">${prod.buyer.buyerName}</h5>
                </a>
                    <p class="card-text mb-1">☎ ${prod.buyer.buyerComtel}</p>
                    <p class="card-text text-muted small">📧 ${prod.buyer.buyerMail}</p>
                    <hr>
                    <p class="card-text small text-secondary">
                        [${prod.buyer.buyerZip}]<br>
                        ${prod.buyer.buyerAdd1} ${prod.buyer.buyerAdd2}
                    </p>
                </div>
            </div>
        </div>

        <div class="col-lg-8">
            <div class="card shadow-sm">
                <div class="card-body p-0">
                    <table class="table table-bordered mb-0">
                        <tbody>
                            <tr>
                                <td class="detail-label text-center align-middle">상품ID</td>
                                <td>${prod.prodId}</td>
                                <td class="detail-label text-center align-middle">분류명</td>
                                <td><span class="badge bg-info text-dark">${prod.lprod.lprodName}</span></td>
                            </tr>
                            <tr>
                                <td class="detail-label text-center align-middle">구매가</td>
                                <td class="text-end text-secondary"><fmt:formatNumber value="${prod.prodCost}" pattern="#,###"/>원</td>
                                <td class="detail-label text-center align-middle">세일가</td>
                                <td class="text-end text-danger fw-bold"><fmt:formatNumber value="${prod.prodSale}" pattern="#,###"/>원</td>
                            </tr>
                            <tr>
                                <td class="detail-label text-center align-middle">마일리지</td>
                                <td class="text-success">${prod.prodMileage} P</td>
                                <td class="detail-label text-center align-middle">입고일자</td>
                                <td>${prod.prodInsdate}</td>
                            </tr>
                            <tr>
                                <td class="detail-label text-center align-middle">총재고 / 적정재고</td>
                                <td colspan="3">
                                    현재고: <span class="badge bg-secondary">${prod.prodTotalstock}</span> / 
                                    적정: <span class="badge bg-light text-dark border">${prod.prodProperstock}</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="detail-label text-center align-middle">규격 / 색상</td>
                                <td>${prod.prodSize} / ${prod.prodColor}</td>
                                <td class="detail-label text-center align-middle">단위</td>
                                <td>${prod.prodUnit}</td>
                            </tr>
                            <tr>
                                <td class="detail-label text-center align-middle">배송방법</td>
                                <td colspan="3">${prod.prodDelivery}</td>
                            </tr>
                            <tr>
                                <td class="detail-label text-center align-middle">상세 설명</td>
                                <td colspan="3" class="py-4">
                                    <div class="px-2" style="min-height: 150px; line-height: 1.6;">
                                        <%-- ${prod.prodDetail} --%>
                                        ${prod.prodOutline}
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            
            <div class="alert alert-light border mt-3 small text-muted">
                * 위 정보는 
                <a href="/buyer/detail?id=${prod.buyer.buyerId}" class="text-decoration-none">
                <strong>${prod.buyer.buyerName}</strong>
                </a>
                로부터 제공된 공식 정보입니다. 담당자: ${prod.buyer.buyerCharger} 
            </div>
        </div>
    </div>
</div>

</body>
</html>