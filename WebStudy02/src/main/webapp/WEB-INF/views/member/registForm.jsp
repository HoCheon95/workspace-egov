
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>회원 등록</title>
    </head>
    <body>

        <div class="container mt-5 mb-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card shadow">
                        <div class="card-header bg-primary text-white">
                            <h3 class="card-title mb-0">회원 등록</h3>
                        </div>
                        <div class="card-body">
                            <form method="post" action="/member/regist" enctype="application/x-www-form-urlencoded">

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="memId" class="form-label">아이디</label>
                                        <input id="memId" type="text" name="memId" class="form-control"
                                        value="${member.memId}" placeholder="아이디를 입력하세요" required />
                                    </div>
                                    <div class="col-md-6">
                                        <label for="memPass" class="form-label">비밀번호</label>
                                        <input id="memPass" type="password" name="memPass" class="form-control"
                                        placeholder="비밀번호를 입력하세요" required />
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="memName" class="form-label">이름</label>
                                        <input id="memName" type="text" name="memName" class="form-control"
                                        value="${member.memName}" required />
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
                                        <input id="memRegno1" type="text" name="memRegno1" class="form-control"
                                        value="${member.memRegno1}" placeholder="앞 6자리" maxlength="6" />
                                    </div>
                                    <div class="col-md-2 text-center align-self-center">-</div>
                                    <div class="col-md-5">
                                        <input id="memRegno2" type="password" name="memRegno2" class="form-control"
                                        placeholder="뒤 7자리" maxlength="7" />
                                    </div>
                                </div>

                                <hr>

                                <div class="mb-3">
                                    <label for="memZip" class="form-label">우편번호</label>
                                    <div class="input-group mb-2">
                                        <input id="memZip" type="text" name="memZip" class="form-control"
                                        value="${member.memZip}" />
                                        <button class="btn btn-outline-secondary" type="button">주소 검색</button>
                                    </div>
                                    <input id="memAdd1" type="text" name="memAdd1" class="form-control mb-2"
                                    value="${member.memAdd1}" placeholder="기본 주소" />
                                    <input id="memAdd2" type="text" name="memAdd2" class="form-control"
                                    value="${member.memAdd2}" placeholder="상세 주소" />
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-4">
                                        <label for="memHp" class="form-label">휴대전화</label>
                                        <input id="memHp" type="tel" name="memHp" class="form-control"
                                        value="${member.memHp}" placeholder="010-0000-0000" />
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
                                        value="${member.memMail}" placeholder="example@mail.com" />
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

                                <div class="row mb-4">
                                    <div class="col-md-6">
                                        <label for="memMemorial" class="form-label">기념일 종류</label>
                                        <input id="memMemorial" type="text" name="memMemorial" class="form-control"
                                        value="${member.memMemorial}" placeholder="예: 결혼기념일" />
                                    </div>
                                    <div class="col-md-6">
                                        <label for="memMemorialday" class="form-label">기념일 날짜</label>
                                        <input id="memMemorialday" type="date" name="memMemorialdayBak" class="form-control"
                                        value="${member.memMemorialday}" />
                                    </div>
                                </div>

                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary btn-lg">회원 등록 완료</button>
                                    <button type="reset" class="btn btn-light">취소</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>