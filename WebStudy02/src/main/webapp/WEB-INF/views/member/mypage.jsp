<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 상세 정보</title>
    
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    
    <style>
        body { background-color: #f4f7f6; font-family: 'Pretendard', sans-serif; }
        .card { border: none; border-radius: 15px; }
        .card-header { border-radius: 15px 15px 0 0 !important; padding: 1.5rem; }
        .section-title { font-size: 1.1rem; font-weight: 700; color: #334155; border-left: 4px solid #0d6efd; padding-left: 10px; margin-bottom: 20px; }
        .info-box { background-color: #ffffff; padding: 20px; border-radius: 12px; border: 1px solid #e2e8f0; height: 100%; }
        .label-text { font-size: 0.85rem; color: #64748b; margin-bottom: 4px; font-weight: 600; }
        .value-text { font-size: 1rem; color: #1e293b; font-weight: 500; min-height: 24px; }
        .badge-role { padding: 0.5em 0.8em; border-radius: 6px; font-weight: 500; }
        .status-active { color: #10b981; background-color: #ecfdf5; padding: 4px 12px; border-radius: 20px; font-size: 0.85rem; font-weight: bold; }
    </style>

    <script type="text/javascript">
        alert("마이페이지임!");
    </script>
</head>
<body>

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-xl-9">
            
            <div class="card shadow-sm mb-4">
                <div class="card-header bg-white d-flex justify-content-between align-items-center">
                    <div class="d-flex align-items-center">
                        <div class="rounded-circle bg-primary text-white d-flex align-items-center justify-content-center me-3" style="width: 60px; height: 60px;">
                            <i class="bi bi-person-fill fs-2"></i>
                        </div>
                        <div>
                            <h3 class="mb-0 fw-bold">${member.memName} <span class="fs-6 text-muted fw-normal">(${member.memId})</span></h3>
                            <div class="mt-1">
                                <c:choose>
                                    <c:when test="${member.memDelete eq 'Y'}">
                                        <span class="badge bg-danger">탈퇴 회위</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status-active"><i class="bi bi-check-circle-fill me-1"></i>활동 중</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="text-end">
                        <div class="label-text">보유 마일리지</div>
                        <div class="h4 mb-0 text-primary fw-bold"><fmt:formatNumber value="${member.memMileage}" pattern="#,###" /> P</div>
                    </div>
                </div>
            </div>

            <div class="card shadow-sm p-4">
                
                <div class="section-title"><i class="bi bi-card-list me-2"></i>기본 인적 사항</div>
                <div class="row g-4 mb-5">
                    <div class="col-md-4">
                        <div class="label-text">주민등록번호</div>
                        <div class="value-text">${member.memRegno1} - ${member.memRegno2.substring(0,1)}******</div>
                    </div>
                    <div class="col-md-4">
                        <div class="label-text">생년월일</div>
                        <div class="value-text">${member.memBir}</div>
                    </div>
                    <div class="col-md-4">
                        <div class="label-text">직업 / 취미</div>
                        <div class="value-text">${member.memJob} / ${member.memLike}</div>
                    </div>
                </div>

                <div class="section-title"><i class="bi bi-telephone me-2"></i>연락처 및 주소</div>
                <div class="row g-4 mb-4">
                    <div class="col-md-4">
                        <div class="label-text">휴대전화</div>
                        <div class="value-text text-primary fw-bold">${member.memHp}</div>
                    </div>
                    <div class="col-md-4">
                        <div class="label-text">이메일</div>
                        <div class="value-text">${member.memMail}</div>
                    </div>
                    <div class="col-md-4">
                        <div class="label-text">유선번호 (집/회사)</div>
                        <div class="value-text text-muted small">${member.memHometel} / ${member.memComtel}</div>
                    </div>
                    <div class="col-12">
                        <div class="info-box border-0 bg-light">
                            <div class="label-text">배송지 주소</div>
                            <div class="value-text">[${member.memZip}] ${member.memAdd1} ${member.memAdd2}</div>
                        </div>
                    </div>
                </div>

                <div class="row g-4 mt-2">
                    <div class="col-md-6">
                        <div class="section-title"><i class="bi bi-calendar-event me-2"></i>기념일</div>
                        <div class="info-box">
                            <span class="badge bg-info text-dark mb-2">${member.memMemorial}</span>
                            <div class="value-text fw-bold">${member.memMemorialday}</div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="section-title"><i class="bi bi-shield-check me-2"></i>부여된 권한</div>
                        <div class="info-box">
                            <c:forEach var="role" items="${member.memRoles}">
                                <span class="badge bg-secondary badge-role me-2"><i class="bi bi-key me-1"></i>${role}</span>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <hr class="my-5">

                <div class="d-flex justify-content-between">
                    <a href="/" class="btn btn-light px-4"><i class="bi bi-arrow-left me-2"></i>목록으로</a>
                    <div class="gap-2 d-flex">
                        <a href="memberUpdate.do?memId=${member.memId}" class="btn btn-warning px-4 fw-bold text-white">정보 수정</a>
                        <c:if test="${member.memDelete ne 'Y'}">
                            <button type="button" class="btn btn-outline-danger px-4" onclick="confirm('정말 탈퇴 처리하시겠습니까?')">회원 탈퇴</button>
                        </c:if>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
</html>