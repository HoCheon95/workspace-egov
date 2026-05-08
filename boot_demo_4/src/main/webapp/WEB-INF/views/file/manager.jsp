<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>파일 매니저</title>
        <style>
            body { background-color: #f8f9fa; }
            .container { max-width: 800px; margin-top: 50px; }
            #files-area { min-height: 100px; }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">파일 매니저</h4>
                </div>

                <div class="card-body">
                    <!-- 파일 목록 영역 -->
                    <h5 class="card-title border-bottom pb-2">서버 파일 목록</h5>
                    <ul id="files-area" class="list-group list-group-flush mb-4">
                        <!-- JS를 통해 파일 목록이 동적으로 추가될 위치입니다 -->
                        <li class="list-group-item text-muted">업로드된 파일이 없습니다.</li>
                    </ul>

                    <!-- 업로드 폼 영역 -->
                    <form id="upload-form" action="/files" method="post" enctype="multipart/form-data">
                        <div class="input-group">
                            <input type="file" name="uploadFile" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
                            <button class="btn btn-outline-primary" type="submit" id="inputGroupFileAddon04">업로드</button>
                        </div>
                        <div class="form-text mt-2">파일을 선택한 후 업로드 버튼을 클릭하세요.</div>
                    </form>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="/js/app/file/manager.js"></script>
    </body>
</html>