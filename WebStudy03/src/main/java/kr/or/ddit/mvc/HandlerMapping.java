package kr.or.ddit.mvc;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 1. 낱개의 요청을 처리할 컨트롤러 정보를 수집하고 -> handler Map을 생성함
 * 2. 현재 요청을 처리할 컨트롤러를 검색하고 반환함
 */
public interface HandlerMapping {
    <T> T findCommandHandler(HttpServletRequest req);
}
