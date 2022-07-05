package com.enigma.simplebackend.service.impl;

import com.enigma.simplebackend.entity.PhotoProfile;
import com.enigma.simplebackend.exception.NotAcceptableException;
import com.enigma.simplebackend.exception.NotFoundException;
import com.enigma.simplebackend.payload.PhotoProfileResponse;
import com.enigma.simplebackend.repository.PhotoProfileRepository;
import com.enigma.simplebackend.service.PhotoProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class PhotoProfileServiceImpl implements PhotoProfileService {

        @Autowired
        PhotoProfileRepository photoProfileRepository;

        private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

        @Override
        public PhotoProfileResponse create(MultipartFile multipartFile){

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            PhotoProfileResponse photoProfileResponse = new PhotoProfileResponse();
            try{
                PhotoProfile  photoProfile = new PhotoProfile();

                if(contentTypes.contains(multipartFile.getContentType())){
                    photoProfile.setName(fileName);
                    photoProfile.setType(multipartFile.getContentType());
                    photoProfile.setData(multipartFile.getBytes());
                    PhotoProfile savePhoto = photoProfileRepository.save(photoProfile);
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/photoProfile/")
                            .path(savePhoto.getFileId())
                            .toUriString();
                    photoProfileResponse.setId(savePhoto.getFileId());
                    photoProfileResponse.setName(savePhoto.getName());
                    photoProfileResponse.setUrl(fileDownloadUri);
                    photoProfileResponse.setType(savePhoto.getType());
                    photoProfileResponse.setSize(savePhoto.getData().length);
                }else{
                    throw  new NotAcceptableException("Only jpg,jpeg,png or JPG images are allowed");
                }




            } catch (IOException e) {
                e.printStackTrace();
            }


            return photoProfileResponse;
        }

        @Override
        public PhotoProfile getById(String id) {
            return photoProfileRepository.findById(id).orElseThrow(() -> new NotFoundException("File Not Found"));
        }

        @Override
        public Stream<PhotoProfile> getAll() {
            return photoProfileRepository.findAll().stream();

        }

        @Override
        public String deleteById(String id) {
            PhotoProfile photoProfile = getById(id);
            photoProfileRepository.delete(photoProfile);
            return id;
        }

}
