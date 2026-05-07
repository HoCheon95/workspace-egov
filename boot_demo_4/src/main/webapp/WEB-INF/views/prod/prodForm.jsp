<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>신규 상품 등록</title>
    <style>
        body { background-color: #f4f7f6; }
        .detail-label { background-color: #e9ecef; font-weight: bold; width: 20%; }
        .required::after { content: ' *'; color: red; }
    </style>
</head>
<body>

<div class="container my-5">
    <form:form action="" method="post" modelAttribute="prod" novalidate="novalidate">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold"><span class="text-success">|</span> 신규 상품 등록/수정</h2>
            <div>
                <button type="submit" class="btn btn-primary">저장하기</button>
                <button type="reset" class="btn btn-outline-danger">취소</button>
                <a href="productList.do" class="btn btn-outline-secondary">목록으로</a>
            </div>
        </div>

        <div class="row g-4">
            <div class="col-lg-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-header bg-dark text-white fw-bold">이미지 및 분류</div>
                    <div class="card-body">
                        <div class="mb-3">
                            <label class="form-label required">상품 이미지</label>
                            <input type="file" name="prodImage" class="form-control" accept="image/*">
                        </div>
                        <div class="mb-3">
                            <label class="form-label required">상품분류(LPROD)</label>
                            <select name="lprodGu" id="lprodGu" class="form-select" required data-selected="${prod.lprodGu}">
                                <option value="" disabled ${empty prod.lprodGu ? 'selected' : ''}>상품분류를 선택하세요</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label required">제조사(BUYER)</label>
                            <select name="buyerId" id="buyerId" class="form-select" required data-selected="${prod.buyerId}">
                                <option value="" disabled ${empty prod.buyerId ? 'selected' : ''}>제조사를 선택하세요</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-8">
                <div class="card shadow-sm">
                    <div class="card-body p-0">
                        <table class="table table-bordered mb-0">
                            <tbody>
                                <tr>
                                    <td class="detail-label text-center align-middle required">상품명</td>
                                    <td colspan="3">
                                        <input type="hidden" name="prodId" value="${prod.prodId}">
                                        <input type="text" name="prodName" class="form-control" placeholder="상품명을 입력하세요" required value="${prod.prodName}">
                                         <form:errors path="prodName" cssClass="text-danger" element="span" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="detail-label text-center align-middle required">구매가</td>
                                    <td>
                                        <input type="number" name="prodCost" class="form-control text-end" placeholder="0" required value="${prod.prodCost}">
                                         <form:errors path="prodCost" cssClass="text-danger" element="span" />
                                    </td>
                                    <td class="detail-label text-center align-middle required">판매가</td>
                                    <td>
                                        <input type="number" name="prodPrice" class="form-control text-end" placeholder="0" required value="${prod.prodPrice}">
                                         <form:errors path="prodPrice" cssClass="text-danger" element="span" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="detail-label text-center align-middle required">세일가</td>
                                    <td>
                                        <input type="number" name="prodSale" class="form-control text-end" placeholder="0" required value="${prod.prodSale}">
                                            <form:errors path="prodSale" cssClass="text-danger" element="span" />
                                    </td>
                                    <td class="detail-label text-center align-middle">마일리지</td>
                                    <td>
                                        <input type="number" name="prodMileage" class="form-control text-end" placeholder="0" value="${prod.prodMileage}">
                                            <form:errors path="prodMileage" cssClass="text-danger" element="span" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="detail-label text-center align-middle required">총재고</td>
                                    <td>
                                        <input type="number" name="prodTotalstock" class="form-control text-end" placeholder="0" required value="${prod.prodTotalstock}">
                                            <form:errors path="prodTotalstock" cssClass="text-danger" element="span" />
                                    </td>
                                    <td class="detail-label text-center align-middle">적정재고</td>
                                    <td>
                                        <input type="number" name="prodProperstock" class="form-control text-end" placeholder="0" value="${prod.prodProperstock}">
                                            <form:errors path="prodProperstock" cssClass="text-danger" element="span" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="detail-label text-center align-middle">규격 / 색상</td>
                                    <td>
                                        <div class="input-group">
                                            <input type="text" name="prodSize" class="form-control" placeholder="규격" value="${prod.prodSize}">
                                            <form:errors path="prodSize" cssClass="text-danger" element="span" />
                                            <input type="text" name="prodColor" class="form-control" placeholder="색상" value="${prod.prodColor}">
                                            <form:errors path="prodColor" cssClass="text-danger" element="span" />
                                        </div>
                                    </td>
                                    <td class="detail-label text-center align-middle">단위</td>
                                    <td>
                                        <input type="text" name="prodUnit" class="form-control" placeholder="예: EA, 개" value="${prod.prodUnit}">
                                        <form:errors path="prodUnit" cssClass="text-danger" element="span" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="detail-label text-center align-middle">배송방법</td>
                                    <td colspan="3">
                                        <input type="text" name="prodDelivery" class="form-control" placeholder="배송 정보를 입력하세요" value="${prod.prodDelivery}">
                                        <form:errors path="prodDelivery" cssClass="text-danger" element="span" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="detail-label text-center align-middle required">한줄 요약</td>
                                    <td colspan="3">
                                        <input type="text" name="prodOutline" class="form-control" placeholder="상품에 대한 간단한 설명을 입력하세요" required value="${prod.prodOutline}">
                                        <form:errors path="prodOutline" cssClass="text-danger" element="span" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="detail-label text-center align-middle">상세 설명</td>
                                    <td colspan="3">
                                        <textarea name="prodDetail" class="form-control" rows="5" placeholder="상세한 상품 설명을 입력하세요">${prod.prodDetail}</textarea>
                                        <form:errors path="prodDetail" cssClass="text-danger" element="span" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>

<c:if test="${not empty formType}" >
    <script type="text/javascript" src="/js/app/prod/prodForm.js" ></script>
</c:if>
</body>
</html>