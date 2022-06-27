package com.enigma.simplebackend.service;

import com.enigma.simplebackend.entity.PhotoProfile;
import com.enigma.simplebackend.payload.response.photoprofile.PhotoProfileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

public interface PhotoProfileService {

    PhotoProfileResponse create(MultipartFile multipartFile);
    PhotoProfile getById(String id);

    Stream<PhotoProfile> getAll();

    String deleteById(String id);


}
