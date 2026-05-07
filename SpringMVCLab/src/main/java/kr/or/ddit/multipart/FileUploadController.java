package kr.or.ddit.multipart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequestMapping("/file/upload")
public class FileUploadController {
    @Value("file:/home/ho/00.medias/savefiles/")
    private Resource saveDir;
    @Value("file:/home/ho/00.medias/savefiles/")
    private File dirFile;
    @Value("file:/home/ho/00.medias/savefiles/")
    private Path dirPath;

    public static final String MODELNAME = "uploadDto";

    @PostConstruct
    public void init() {
        log.info("saveDir : {}", saveDir);
        log.info("dirFile : {}", dirFile);
        log.info("dirPath : {}", dirPath);
    }

    @ModelAttribute(MODELNAME)
    public FileUploadRequest uploadDto() {
        return new FileUploadRequest();
    }

    @GetMapping
    public String formUi() {
        return "file/uploadForm";
    }

    /**
     * MultipartConfig + "multipartResolver" 빈으로 등록된 MultipartResolver 가 필요함
     * @param reqDto
     * @param errors
     * @param redirectAttributes
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @PostMapping
    public String multipartProcessCase2(
        @Valid @ModelAttribute(MODELNAME) FileUploadRequest reqDto,
        BindingResult errors,
        RedirectAttributes redirectAttributes
    ) throws IllegalStateException, IOException {

        String uploader = reqDto.getUploader();
        MultipartFile uploadFile = reqDto.getUploadFile();

        String lvn = null;
        if (errors.hasErrors()) {
            redirectAttributes.addFlashAttribute(MODELNAME, reqDto);
            String errorName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
            redirectAttributes.addFlashAttribute(errorName, errors);
            lvn = "redirect:/file/upload";
        } else {
            log.info("uploader : {}", uploader);
            String orginalFilename = reqDto.getUploadFile().getOriginalFilename();

            File saveFile = new File(dirFile, orginalFilename);
            uploadFile.transferTo(saveFile);

            redirectAttributes.addFlashAttribute("uploader", uploader);
            redirectAttributes.addFlashAttribute("filePath", saveFile.getAbsolutePath());
            lvn = "redirect:/file/upload";
        }
        return lvn;
    }

    // @PostMapping
    /**
     * MultipartConfig 설정만 있으면 동작
     * @param uploader
     * @param uploadFile
     * @param redirectAttributes
     * @return
     * @throws IOException
     */
    public String multipartProcessCase1(
        @RequestParam(name = "uploader", required = true) String uploader,
        @RequestPart(name = "uploadFile", required = true) MultipartFile uploadFile,
        RedirectAttributes redirectAttributes
    ) throws IOException {
        log.info("uploader : {}", uploader);
        String originalFilename = uploadFile.getOriginalFilename();

        File saveFile = new File(dirFile, originalFilename);
        uploadFile.transferTo(saveFile);

        redirectAttributes.addFlashAttribute("uploader", uploader);
        redirectAttributes.addFlashAttribute("filePath", saveFile.getAbsolutePath());
        return "redirect:/file/upload";
    }
}
