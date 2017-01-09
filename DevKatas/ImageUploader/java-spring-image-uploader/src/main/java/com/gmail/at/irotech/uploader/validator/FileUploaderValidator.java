package com.gmail.at.irotech.uploader.validator;

import java.io.IOException;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gmail.at.irotech.uploader.model.FileUploader;
import com.gmail.at.irotech.uploader.model.FileUploaderForm;

public class FileUploaderValidator implements Validator {

  private static final String[] ACCEPTED_CONTENT_TYPES = new String[] {"image/jpeg", "image/gif",
      "image/jpg", "image/png"};

  private static final String[] ACCEPTED_EXTENSIONS = new String[] {"png", "gif", "jpg", "jpeg"};

  private static final int MAX_DIMENSION = 512000; // 500KB

  @Override
  public boolean supports(Class<?> arg0) {
    return FileUploaderForm.class.equals(arg0);
  }

  @Override
  public void validate(Object obj, Errors errors) {

    String incomingContentType = null;
    boolean acceptableContentType = false;
    boolean acceptableDimension = true;

    FileUploaderForm fileUploadForm = (FileUploaderForm) obj;

    List<FileUploader> files = fileUploadForm.getFiles();

    for (FileUploader fileUpload : files) {
      incomingContentType = fileUpload.getFile().getContentType();

      if ("application/octet-stream".equalsIgnoreCase(incomingContentType)) {
        int index = fileUpload.getFile().getOriginalFilename().lastIndexOf('.');
        String incomingExtension = fileUpload.getFile().getOriginalFilename().substring(index + 1);
        for (String extendsion : ACCEPTED_EXTENSIONS) {
          if (extendsion.equalsIgnoreCase(incomingExtension)) {
            acceptableContentType = true;
            break;
          }
        }
      } else {
        for (String currentContentType : ACCEPTED_CONTENT_TYPES) {
          if (currentContentType.equalsIgnoreCase(incomingContentType)) {
            acceptableContentType = true;
            break;
          } else {
            acceptableContentType = false;
          }
        }
      }
      try {
        if (fileUpload.getFile().getBytes().length > MAX_DIMENSION) {
          acceptableDimension = false;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    if (!acceptableContentType) {
      errors.reject("error.upload.contenttype",
          "Please upload a file with one of the following file types; .png, .gif, .jpg, .jpeg .");
    } else if (!acceptableDimension) {
      errors.reject("error.upload.dimension", "Please upload a image file with dimension at least "
          + MAX_DIMENSION / 1024 + " KB.");
    }

  }

}
