package com.enigma.simplebackend.controller;

import com.enigma.simplebackend.entity.PhotoProfile;
import com.enigma.simplebackend.payload.PhotoProfileResponse;
import com.enigma.simplebackend.service.PhotoProfileService;
import com.enigma.simplebackend.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photoProfile")
public class PhotoProfileController {

    @Autowired
    PhotoProfileService photoProfileService;

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE,
            },
            produces = "application/json"

    )
    public ResponseEntity<WebResponse<PhotoProfileResponse>> upload(
            @RequestPart(name = "file",required = true)MultipartFile multipartFile)

    {
        PhotoProfileResponse photoProfileResponse = photoProfileService.create(multipartFile);
        return ResponseEntity.ok().body(new WebResponse<>("Success Upload File",photoProfileResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id){
        PhotoProfile photoProfile = photoProfileService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attacment; filename \"" + photoProfile.getName() + "\"")
                .body(photoProfile.getData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WebResponse<String>> deletePhoto(@PathVariable String id){
        photoProfileService.deleteById(id);
        return ResponseEntity.ok().body(new WebResponse<>("SuccessFully Delete Product with id = " + id + "",null));
    }

}
