<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>제조사 정보 입력</title>
    </head>
    <body class="bg-light">

        <div class="container mt-5 mb-5">
            <div class="row justify-content-center">
                <div class="col-md-10">
                    <div class="card shadow">
                        <div class="card-header bg-primary text-white">
                            <h4 class="mb-0">🏢 신규 거래처 등록</h4>
                        </div>
                        <div class="card-body p-4">
                            <form method="post">

                                <div class="row">
                                    <h5 class="mb-3 text-secondary border-bottom pb-2">기본 정보</h5>
                                    <div class="col-md-6 mb-3">
                                        <label for="lprodGu" class="form-label">상품 분류 (필수)</label>
                                        <select class="form-select" id="lprodGu" name="lprodGu" >
                                            <option value="" selected disabled>분류를 선택하세요</option>
                                            <option value="P101">전자제품 (P101)</option>
                                            <option value="P102">식료품 (P102)</option>
                                            <option value="P201">의류 (P201)</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="buyerName" class="form-label">거래처 이름 (필수)</label>
                                        <input type="text" class="form-control" id="buyerName" name="buyerName" >
                                        <span class="text-danger">${errors.buyerName}</span>
                                    </div>

                                    <h5 class="mt-4 mb-3 text-secondary border-bottom pb-2">금융 정보</h5>
                                    <div class="col-md-4 mb-3">
                                        <label for="buyerBank" class="form-label">거래 은행</label>
                                        <input type="text" class="form-control" id="buyerBank" name="buyerBank">
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="buyerBankno" class="form-label">계좌 번호</label>
                                        <input type="text" class="form-control" id="buyerBankno" name="buyerBankno">
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="buyerBankname" class="form-label">예금주명</label>
                                        <input type="text" class="form-control" id="buyerBankname" name="buyerBankname">
                                    </div>

                                    <h5 class="mt-4 mb-3 text-secondary border-bottom pb-2">소재지 및 연락처</h5>
                                    <div class="col-md-3 mb-3">
                                        <label for="buyerZip" class="form-label">우편번호</label>
                                        <input type="text" class="form-control" id="buyerZip" name="buyerZip">
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="buyerAdd1" class="form-label">기본 주소</label>
                                        <input type="text" class="form-control" id="buyerAdd1" name="buyerAdd1">
                                    </div>
                                    <div class="col-md-5 mb-3">
                                        <label for="buyerAdd2" class="form-label">상세 주소</label>
                                        <input type="text" class="form-control" id="buyerAdd2" name="buyerAdd2">
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="buyerComtel" class="form-label">회사 전화번호 (필수)</label>
                                        <input type="text" class="form-control" id="buyerComtel" name="buyerComtel">
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="buyerFax" class="form-label">팩스 번호 (필수)</label>
                                        <input type="text" class="form-control" id="buyerFax" name="buyerFax">
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="buyerMail" class="form-label">이메일 (필수)</label>
                                        <input type="email" class="form-control" id="buyerMail" name="buyerMail" >
                                    </div>

                                    <h5 class="mt-4 mb-3 text-secondary border-bottom pb-2">담당자 정보</h5>
                                    <div class="col-md-6 mb-3">
                                        <label for="buyerCharger" class="form-label">담당자 이름</label>
                                        <input type="text" class="form-control" id="buyerCharger" name="buyerCharger">
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="buyerTelext" class="form-label">내선 번호</label>
                                        <input type="text" class="form-control" id="buyerTelext" name="buyerTelext">
                                    </div>
                                </div>

                                <hr class="my-4">

                                <div class="d-flex justify-content-end pb-3">
                                    <button type="reset" class="btn btn-outline-secondary me-2">초기화</button>
                                    <button type="submit" class="btn btn-primary px-4">제조사 등록 완료</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>