package kr.or.ddit.hw02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hw02/image-list")
public class ImageListUIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String directory = "/home/ho/workspace/00.medias/images";

        List<String> fileList = new ArrayList<>();

        try (Stream<Path> stream = Files.list(Paths.get(directory))) {
            fileList = stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        req.setAttribute("fileName", fileList);
        req.getRequestDispatcher("/WEB-INF/views/hw02/image-list.jsp").forward(req, resp);
    }
}
