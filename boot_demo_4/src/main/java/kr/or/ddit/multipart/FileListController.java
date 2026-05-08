package kr.or.ddit.multipart;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class FileListController {
    
    @Value("${prod-image.upload.path}")
    private Path dirPath;
    @Value("${prod-image.upload.path}")
    private File dirFile;
    @Value("${prod-image.upload.path}")
    private Resource dirRes;

    @PostConstruct
    public void init() {
        log.info("저장 경로 : {}", dirPath);
    }


    @GetMapping("/file/manager") // RequestToViewNameTranslator 의 동작.
    public void fileManagetUi(){
        // return "file/manager";
    }

    /**
     * 파일 목록 조회
     * @return
     * @throws IOException
     */
    @GetMapping("/files")
    @ResponseBody
    public List<String> fileList() throws IOException {
        try(Stream<Path> stream = Files.list(dirPath)){
            return stream.map(p -> p.getFileName().toString()).toList();
        }
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<?> downloadCase3(@PathVariable String filename) throws IOException {
        Resource fileRes = dirRes.createRelative(filename);
        if (fileRes.exists() && fileRes.isFile() && fileRes.isReadable()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(
                ContentDisposition.attachment()
                                    .filename(fileRes.getFilename().toString(), Charset.forName("UTF-8"))
                                    .build()
            );
            return ResponseEntity.ok()
                        .headers(headers)
                        .body(fileRes);
                        
        } else {
            return ResponseEntity.status(404)
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .body(Map.of("message", "파일없음"));
        }
    }

    public void downloadCase2(
        @PathVariable String filename, 
        HttpServletResponse resp,
        OutputStream os
    ) throws IOException {
        Path filePath = dirPath.resolve(filename);
        Files.copy(filePath, os);
    }

    // @GetMapping("/files/{filename}")
    public void downloadCase1(@PathVariable String filename, HttpServletResponse resp) throws IOException {
        // Content-Disposition: attachment; filename="filename.jpg"
        resp.setHeader("Content-Disposition", "attachment; filename=\"%s\"".formatted(filename));
        Path filePath = dirPath.resolve(filename);
        try(
            OutputStream os = resp.getOutputStream();
        ){
            Files.copy(filePath, os);
        }

    }

    /**
     * 파일 업로드
     * @param uploadFile
     * @param redirectAttributes
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @PostMapping("/files")
    public String upload(
        @RequestPart(name = "uploadFile", required = true) MultipartFile uploadFile,
        RedirectAttributes redirectAttributes
    ) throws IllegalStateException, IOException {
        if (uploadFile.isEmpty()) {
            // 400 에러 코드
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "업로드 파일 누락");
        }

        log.info("uploadFile : {}", uploadFile);
        String originalFilename = uploadFile.getOriginalFilename();

        // 날짜 형식을 파일 시스템에서 허용하는 형태로 포맷팅한다.
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        File saveFile = new File(dirFile, timestamp + "_" + originalFilename);
        uploadFile.transferTo(saveFile);

        redirectAttributes.addFlashAttribute("filePath", saveFile.getAbsolutePath());
        return "redirect:/files";
    }
}
