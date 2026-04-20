<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>회원정보 수정</title>
        </head>
    <body>

        <div class="container mt-5 mb-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card shadow">
                        <div class="card-header bg-success text-white"> <h3 class="card-title mb-0">회원정보 수정</h3>
                        </div>
                        <div class="card-body">
                            <form method="post" action="/member/modify" enctype="application/x-www-form-urlencoded">

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="memId" class="form-label">아이디 (수정 불가)</label>
                                        <input id="memId" type="text" name="memId" class="form-control bg-light"
                                        value="${member.memId}" readonly />
                                    </div>
                                    <div class="col-md-6">
                                        <label for="memPass" class="form-label">비밀번호 확인</label>
                                        <input id="memPass" type="password" name="memPass" class="form-control"
                                        placeholder="본인 확인을 위해 입력하세요"  />
                                        <span class="text-danger">${errors.memPass}</span>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="memName" class="form-label">이름</label>
                                        <input id="memName" type="text" name="memName" class="form-control"
                                        value="${member.memName}" />
                                        <span class="text-danger">${errors.memName}</span>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="memBir" class="form-label">생년월일</label>
                                        <input id="memBir" type="date" name="memBirBak" class="form-control"
                                        value="${member.memBir}" />
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="form-label">주민등록번호</label>
                                    <div class="col-md-5">
                                        <input id="memRegno1" type="text" name="memRegno1" class="form-control bg-light"
                                        value="${member.memRegno1}" readonly />
                                    </div>
                                    <div class="col-md-2 text-center align-self-center">-</div>
                                    <div class="col-md-5">
                                        <input id="memRegno2" type="password" name="memRegno2" class="form-control"
                                        placeholder="*******" readonly />
                                    </div>
                                </div>

                                <hr>

                                <div class="mb-3">
                                    <label for="memZip" class="form-label">우편번호</label>
                                    <div class="input-group mb-2">
                                        <input id="memZip" type="text" name="memZip" class="form-control"
                                        value="${member.memZip}" />
                                        <button class="btn btn-outline-secondary" type="button">주소 검색</button>
                                        <span class="text-danger">${errors.memZip}</span>
                                    </div>
                                    <input id="memAdd1" type="text" name="memAdd1" class="form-control mb-2"
                                    value="${member.memAdd1}" placeholder="기본 주소" />
                                    <span class="text-danger">${errors.memAdd1}</span>
                                    <input id="memAdd2" type="text" name="memAdd2" class="form-control"
                                    value="${member.memAdd2}" placeholder="상세 주소" />
                                    <span class="text-danger">${errors.memAdd2}</span>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-4">
                                        <label for="memHp" class="form-label">휴대전화</label>
                                        <input id="memHp" type="tel" name="memHp" class="form-control"
                                        value="${member.memHp}" />
                                    </div>
                                    <div class="col-md-4">
                                        <label for="memHometel" class="form-label">집전화</label>
                                        <input id="memHometel" type="tel" name="memHometel" class="form-control"
                                        value="${member.memHometel}" />
                                    </div>
                                    <div class="col-md-4">
                                        <label for="memComtel" class="form-label">회사전화</label>
                                        <input id="memComtel" type="tel" name="memComtel" class="form-control"
                                        value="${member.memComtel}" />
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="memMail" class="form-label">이메일</label>
                                        <input id="memMail" type="email" name="memMail" class="form-control"
                                        value="${member.memMail}" />
                                        <span class="text-danger">${errors.memMail}</span>
                                    </div>
                                    <div class="col-md-3">
                                        <label for="memJob" class="form-label">직업</label>
                                        <input id="memJob" type="text" name="memJob" class="form-control"
                                        value="${member.memJob}" />
                                    </div>
                                    <div class="col-md-3">
                                        <label for="memLike" class="form-label">취미</label>
                                        <input id="memLike" type="text" name="memLike" class="form-control"
                                        value="${member.memLike}" />
                                    </div>
                                </div>

                                <div class="d-grid gap-2 mt-4">
                                    <button type="submit" class="btn btn-success btn-lg">수정 완료</button>
                                    <button type="button" class="btn btn-light" onclick="history.back();">이전으로</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>