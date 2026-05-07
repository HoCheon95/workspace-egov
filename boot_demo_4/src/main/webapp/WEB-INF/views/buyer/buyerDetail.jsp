<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buyer Detail</title>
    <style>
        body { background-color: #f8f9fa; font-family: 'Pretendard'}
        .detail-label { font-weight: 600; color: #6c757d; width: 30%; }
        .card { border: none; border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
    </style>
</head>
<body>
   <div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="fw-bold text-dark">거래처 상세 정보</h2>
                <a href="/buyer/list" class="btn btn-outline-secondary btn-sm">목록으로</a>
            </div>

            <div class="card bg-white">
                <div class="card-header bg-primary text-white py-3">
                    <h5 class="card-title mb-0">${buyer.buyerName}</h5>
                </div>
                <div class="card-body p-0">
                    <table class="table table-hover mb-0">
                        <tbody>
                            <tr>
                                <td class="detail-label ps-4">분류 코드 / 명</td>
                                <td>
                                    <span class="badge bg-light text-primary border">${buyer.lprodGu}</span>
                                    <span class="ms-2 fw-bold">${buyer.lprod.lprodName}</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="detail-label ps-4">은행 정보</td>
                                <td>
                                    <span class="fw-bold">${buyer.buyerBank}</span> 
                                    <small class="text-muted">(${buyer.buyerBankname})</small>
                                    <div class="text-secondary mt-1">${buyer.buyerBankno}</div>
                                </td>
                            </tr>
                            <tr>
                                <td class="detail-label ps-4">주소</td>
                                <td>
                                    <div>[${buyer.buyerZip}]</div>
                                    <div>${buyer.buyerAdd1} ${buyer.buyerAdd2}</div>
                                </td>
                            </tr>
                            <tr>
                                <td class="detail-label ps-4">연락처 (Tel / Fax)</td>
                                <td>
                                    <div><i class="bi bi-telephone"></i> ${buyer.buyerComtel}</div>
                                    <div class="text-muted small">${buyer.buyerFax} (Fax)</div>
                                </td>
                            </tr>
                            <tr>
                                <td class="detail-label ps-4">이메일</td>
                                <td><a href="mailto:${buyer.buyerMail}" class="text-decoration-none">${buyer.buyerMail}</a></td>
                            </tr>
                            <tr>
                                <td class="detail-label ps-4">담당자</td>
                                <td><span class="fw-bold text-dark">${buyer.buyerCharger}</span></td>
                            </tr>
                            

                        </tbody>
                    </table>
                    
                </div>
                <div class="card bg-white mt-4">
                <div class="card-header bg-dark text-white py-3 mt-5">
                    <h5 class="card-title mb-0">취급 상품 목록</h5>
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover mb-0 text-center">
                            <thead class="table-light">
                                <tr>
                                    <th>상품코드</th>
                                    <th class="text-start">상품명</th>
                                    <th>구매가</th>
                                    <th>판매가</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${not empty buyer.prodList}">
                                        <c:forEach var="prod" items="${buyer.prodList}">
                                            <tr>
                                                <td class="text-secondary small">${prod.prodId}</td>
                                                <td class="text-start fw-bold">
                                                    <a target="_blank" href="/prod/detail?id=${prod.prodId}">
                                                    ${prod.prodName}
                                                    </a>
                                                </td>

                                                <td>${prod.prodPrice}</td>
                                                <td class="text-primary fw-bold">${prod.prodSale}</td>
                                                
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="5" class="py-4 text-muted">등록된 상품이 없습니다.</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
                <div class="card-footer bg-light text-end py-3">
                    <a href="/buyer/update?id=${buyer.buyerId}" class="btn btn-warning text-white fw-bold">수정</a>
                    <button id="deleteBtn" class="btn btn-danger fw-bold">삭제</button>
                </div>
            </div>
            
            <%-- 디버깅용 데이터 확인 (개발 완료 후 삭제) --%>
            <div class="mt-4 small text-muted p-3 border rounded bg-light" style="font-family: monospace;">
                Raw Data: ${buyer}
            </div>

        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">제조사 삭제 확인</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
        <form action="<c:url value='/buyer/delete'/>" method="POST">
            <input type="text" hidden name="id" value="${buyer.buyerId}">
            <div class="modal-body">
                <p>정말 삭제하시겠습니까?</p>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary">
                    제조사 삭제
                </button>
            </div>
        </form>
    </div>
  </div>
</div>
<script>


document.addEventListener('DOMContentLoaded', function() {
        const myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
        const deleteBtn = document.getElementById('deleteBtn');
        deleteBtn.addEventListener('click', function() {
                myModal.show();
        });
    });
</script>

</body>
</html>