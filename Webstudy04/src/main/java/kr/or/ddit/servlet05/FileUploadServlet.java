package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/05/upload")
@MultipartConfig(fileSizeThreshold = 10 * 1024, maxFileSize = 10 * 5 * 1024, maxRequestSize = 1024 * 1024 * 5)
public class FileUploadServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String view = "/WEB-INF/views/05/fileForm.jsp";
        req.getRequestDispatcher(view).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploader = req.getParameter("uploader");
        // String uploadFile = req.getParameter("uploadFile");

        // System.out.printf("uploader : %s\n", uploader);
        // System.out.printf("uploadFile : %s\n", uploadFile);
        // Part uploader = req.getPart("uploader");

        Path dirPath = Path.of("/home/ho/00.medias/savefiles");
        Part uploadFile = req.getPart("uploadFile");
        String originalFilename =System.currentTimeMillis() + uploadFile.getSubmittedFileName();

        Path filePath = dirPath.resolve(originalFilename);
        try(
            InputStream is =uploadFile.getInputStream();
        ){
            Files.copy(is, filePath);
            uploadFile.write(filePath.toString());
        }

        System.out.printf("uploader : %s\n", uploader);
        System.out.printf("uploadFile : %s\n", uploadFile);
    }
}