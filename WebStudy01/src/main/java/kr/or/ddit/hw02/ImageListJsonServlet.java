package kr.or.ddit.hw02;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hw02/image-list-json")
public class ImageListJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String directory = "/home/ho/workspace/00.medias/images";

        List<String> fileList = null;

        try (Stream<Path> stream = Files.list(Paths.get(directory))) {
            fileList = stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = new Gson().toJson(fileList);

        String mine = "application/json;charset=UTF-8";
        resp.setContentType(mine);
        try(
            PrintWriter out = resp.getWriter();
        ){
            out.print(json);
        }
        
    }
}
