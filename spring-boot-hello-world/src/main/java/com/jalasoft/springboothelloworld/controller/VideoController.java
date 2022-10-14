package com.jalasoft.springboothelloworld.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.jalasoft.springboothelloworld.FileStorageService;
import com.jalasoft.springboothelloworld.model.Executor;
import com.jalasoft.springboothelloworld.model.commandbuilder.CommandBuilder;
import com.jalasoft.springboothelloworld.model.commandbuilder.VideoCommand;
import com.jalasoft.springboothelloworld.response.VideoUploadResponse;


@RestController
public class VideoController {


    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping("/uploadVideo")
    public VideoUploadResponse uploadVideo(@RequestParam("file") MultipartFile file,
                                        @RequestParam("outName") String newName,
                                        @RequestParam("outFormat") String outFormat,
                                        @RequestParam("volume") String volume,
                                        @RequestParam("removeAudio") String removeAudio,
                                        @RequestParam("videoBitrate") String videoBitrate,
                                        @RequestParam("audioBitrate") String audioBitrate,
                                        @RequestParam("fps") String fps,
                                        @RequestParam("color") String color,
                                        @RequestParam("size") String size) throws IOException {
        String fileName = fileStorageService.storeFile(file);   
        List<String> parameters = new ArrayList<String>();
        parameters.add("Uploads\\" + fileName);
        System.out.println("Uploads\\" + fileName);
        parameters.add(newName);
        parameters.add(outFormat);
        parameters.add(volume);
        parameters.add(removeAudio);
        parameters.add(videoBitrate);
        parameters.add(audioBitrate);
        parameters.add(fps);
        parameters.add(color);
        parameters.add(size);
        CommandBuilder builderCommand = new VideoCommand();
        builderCommand.setParameters(parameters);
        Executor executor = new Executor();
        executor.runCommand(builderCommand.getCommand());
        return new VideoUploadResponse(fileName,
                file.getContentType(), newName, outFormat, volume, removeAudio, videoBitrate, audioBitrate, fps, color, size);
    }

}