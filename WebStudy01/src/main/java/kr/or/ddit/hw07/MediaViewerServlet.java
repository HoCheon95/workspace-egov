package kr.or.ddit.hw07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hw07/media-list.json")
public class MediaViewerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String directoryPath = "/home/ho/workspace/00.medias";
        File rootFolder = new File(directoryPath);

        Map<String, List<String>> mediaMap = new HashMap<>();

        if (rootFolder.exists() && rootFolder.isDirectory()) {
            File[] subFolders = rootFolder.listFiles(File::isDirectory);

            if (subFolders != null) {
                // 🔴 Stream API와 Collectors.toMap을 사용하여 이중 for문을 하나의 파이프라인으로 처리한다
                mediaMap = Arrays.stream(subFolders)
                        .collect(Collectors.toMap(
                                File::getName, // 🔴 Map의 Key 값으로 폴더명을 지정한다
                                sub -> {
                                    // 🔴 참고 코드처럼 list()와 람다식을 사용하여 하위 폴더 내의 '파일'만 배열로 추출한다
                                    String[] files = sub.list((dir, name) -> new File(dir, name).isFile());

                                    // 🔴 파일이 없으면 빈 리스트를 반환하고, 있다면 Stream의 map()을 통해 '폴더명/파일명' 형태로 즉시 변환하여 List로
                                    // 묶어준다
                                    return files == null ? Collections.emptyList()
                                            : Arrays.stream(files)
                                                    .map(fileName -> sub.getName() + "/" + fileName)
                                                    .collect(Collectors.toList());
                                }));
            }
        }
        // 3. 응답 설정 및 출력
        resp.setContentType("application/json;charset=UTF-8");

        System.out.println(mediaMap);
    }
}
