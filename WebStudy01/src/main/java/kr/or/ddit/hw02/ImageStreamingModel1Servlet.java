package kr.or.ddit.hw02;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hw02/image")
public class ImageStreamingModel1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String directory = "/home/ho/workspace/00.medias/images";
        String fileName = req.getParameter("imageName");
        if(fileName == null || fileName.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
            return;
        }

        Path imagePath = Paths.get(directory, fileName);
        if(!Files.exists(imagePath)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "%s 라는 이미지는 없음.".formatted(fileName));
            return;
        }

        Cookie imageCookie = new Cookie("imageCookie", fileName);
        imageCookie.setMaxAge(60*60*24*3);
        // imageCookie.setPath(req.getContextPath());
        imageCookie.setPath("/");
        resp.addCookie(imageCookie);


        ServletContext application = getServletContext();
        String mime = application.getMimeType(fileName);
        resp.setContentType(mime);
        try (
                OutputStream out = resp.getOutputStream();) {

            Files.copy(imagePath, out);
        }
    }
}
