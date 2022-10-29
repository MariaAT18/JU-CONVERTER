/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprietary information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package com.jalasoft.convert.controller.endpoint;


import com.jalasoft.convert.common.exception.FileStorageException;
import com.jalasoft.convert.controller.response.ErrorResponse;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.jalasoft.convert.controller.service.ConvertImageToTextOCR;

import com.jalasoft.convert.controller.service.FileStorageService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * It is responsible for reading images and converting them to text
 *
 * @author Jose Romay
 * @version 1.0
 */

@RestController
public class OcrController {

    @Autowired
    private FileStorageService fileStorageService;


    @PostMapping("/uploadOcrImg")
    public ResponseEntity<Object> translateGt(@RequestParam("img") MultipartFile file,
            @RequestParam("lang") String lang){
        try {
            String fileName = fileStorageService.storeFile(file);
            ConvertImageToTextOCR convert = new ConvertImageToTextOCR();
            convert.convertImageToText(fileName, lang);
            return  downloadFile(convert.getPathOcr());
        } catch (TesseractException | FileStorageException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("400", e.getMessage()));
        }
    }

    public ResponseEntity<Object> downloadFile(String pathOcr){
       String filename = pathOcr;
       File file = new File(filename);
       InputStreamResource resource = null;
       try {
            resource = new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(
              file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
            return responseEntity;
       } catch (FileNotFoundException e) {
           return ResponseEntity.badRequest().body(new ErrorResponse("400",e.getMessage()));
       }
    }
    
}