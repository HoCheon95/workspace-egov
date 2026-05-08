package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.common.exception.OurBusinessException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.dto.ProdDto;

import kr.or.ddit.prod.mapper.ProdMapper;

@Service
public class ProdServiceImpl implements ProdService {
    @Autowired
    private ProdMapper prodMapper;

    @Value("${prod-image.upload.path}")
    private Path dirPath;
    @Value("${prod-image.upload.path}")
    private File dirFile;
    @Value("${prod-image.upload.path}")
    private Resource dirRes;

    private void processProdImage(ProdDto prodDto) {
        try {
            MultipartFile imageFile = prodDto.getProdImage();
            if (imageFile == null || imageFile.isEmpty()) return;
            String savename = prodDto.getProdImg();
            Path filePath = dirPath.resolve(savename);
            imageFile.transferTo(filePath);
        } catch (IOException e) {
            throw new OurBusinessException(prodDto);
        }
    }

    @Transactional
    @Override
    public boolean createProd(ProdDto prodDto) {
        
        int cnt = prodMapper.insertProd(prodDto);
        if(cnt > 0) {
            processProdImage(prodDto);
        }
        return true;
    }

    @Transactional
    @Override
    public boolean modifyProd(ProdDto prodDto) {
        int cnt = prodMapper.updateProd(prodDto);
        if (cnt > 0) {
            processProdImage(prodDto);
        }
        return true;
    }

    @Override
    public ProdDto readProd(String prodId) {
        ProdDto prodDto = prodMapper.selectProd(prodId);
        if (prodDto == null) {
            throw new EntityNotFoundException("%s 상품의 정보가 없습니다.".formatted(prodId));
        } else {
            return prodDto;
        }
    }

    @Override
    public List<ProdDto> readProdList(PaginationInfo<?> paginationInfo) {
        int totalRecord = prodMapper.selectTotalRecord(paginationInfo);
        paginationInfo.setTotalRecord(totalRecord);

        return prodMapper.selectProdList(paginationInfo);
    }

}
