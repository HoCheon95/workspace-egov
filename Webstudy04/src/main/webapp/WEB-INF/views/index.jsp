<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>Web Study 4</title>

        <style>
            /* 링크 리스트 스타일 */
            #link-div {
                display: flex;
                flex-direction: column;
                gap: 12px;
                max-width: 50%;
                margin: auto;
                background-color: #f1f1f1;
            }
            
            .link-item {
                text-decoration: none;
                color: var(--text-color);
                padding: 15px 20px;
                border-radius: 8px;
                border: 1px solid var(--border-color);
                background-color: #ebebeb;
                transition: all 0.2s ease-in-out;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            
            .link-item:hover {
                background-color: var(--hover-bg);
                border-color: var(--primary-color);
                transform: translateX(5px);
                box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.05);
            }
            
            .link-name {
                font-weight: bold;
                font-size: 1.1rem;
            }
            
            .link-url {
                font-size: 0.85rem;
                color: #95a5a6;
            }
            
            .link-desc {
                font-size: 0.8rem;
                color: #9b9b9b;
            }
        </style>
    </head>
    <body>

        <span>Web Study 4</span>
        <h4>웰컴패키지</h4>
        <%
            request.getUserPrincipal();
        %>
        <c:if test="${empty pageContext.request.userPrincipal }">
            <a href="<c:url value='/login'/>">로그인</a>
            <a href="<c:url value='/member/regist'/>">회원가입</a>
        </c:if>
        <c:if test="${not empty pageContext.request.userPrincipal }">
            ${pageContext.request.userPrincipal.name } <a href="<c:url value='/logout'/>">로그아웃</a>
        </c:if>
        <ul>
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN') }">
                <li>
                    <a href="<c:url value='/admin/member-list'/>">관리자용 회원 목록</a>
                </li>
            </c:if>
            <c:if test="${not empty pageContext.request.userPrincipal }">
                <li>
                    <a href="<c:url value='/member/change-password'/>">비밀번호 변경</a>
                </li>
                <li>
                    <a href="<c:url value='/member/mypage'/>">마이페이지</a>
                </li>
            </c:if>
        </ul>
        <div id="link-div">


        </div>
        <script>
            const list = [
                {
                    url: "/member/mypage",
                    name: "My Page",
                    desc: "마이페이지",
                },
                {
                    url: "/admin/member-list",
                    name: "Member List",
                    desc: "관리자용 회원 목록"
                },
                {
                    url: "/member/change-password",
                    name: "Member Change Password",
                    desc: "비밀번호 변경",
                },
                {
                    url: "/buyer/list",
                    name: "Buyer list",
                    desc: "거래처 목록",
                    date: "2026-04-23"
                },
                {
                    url: "/buyer/create",
                    name: "Buyer Create",
                    desc: "신규 거래처 등록",
                    date: "2026-04-23"
                }
            ];
            
            document.addEventListener("DOMContentLoaded", async () => {
                const container = document.getElementById("link-div");
                
                list.reverse().forEach((item) => {
                    const link = document.createElement("a");
                    const span = document.createElement("span");
                    link.href = item.url;
                    link.target = "_blank";
                    link.className = "link-item";
                    
                    link.innerHTML = `
                    <span class="link-name">
                    \${item.name}
                    <span class="link-desc">\${item.desc || ""}</span>
                    <span class="link-desc">\${item.date || ""}</span>
                    
                    </span>
                    <span class="link-url">\${item.url}</span>
                    `;
                    
                    container.appendChild(link);
                });
            });
        </script>
    </body>
</html>