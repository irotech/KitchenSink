package com.gmail.at.irotech.uploader.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.gmail.at.irotech.uploader.model.FileUploader;
import com.gmail.at.irotech.uploader.model.FileUploaderForm;
import com.gmail.at.irotech.uploader.validator.FileUploaderValidator;


@Controller
public class FileUploaderController {

  @Autowired
  private FileUploaderValidator fileUploaderValidator;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.setValidator(fileUploaderValidator);
  }

  @RequestMapping(value = "/show", method = RequestMethod.GET)
  public String displayForm() {
    return "file_upload_form";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@Valid @ModelAttribute("uploadForm") FileUploaderForm uploadForm,
      BindingResult result, Model map) {

    if (result.hasErrors()) {
      return displayForm();
    }

    List<FileUploader> files = uploadForm.getFiles();

    List<String> fileNames = new ArrayList<String>();

    if (null != files && files.size() > 0) {
      for (FileUploader fileUploader : files) {

        StringBuilder fileNameComplete = new StringBuilder("");

        // if(fileUpload.isDefaultname()) {
        // fileName = UUID.randomUUID().toString();
        // } else {
        //
        // }

        // if(null!=fileUpload.getCaption() && null!=fileUpload.getAlttag()) {
        //
        // }

        MultipartFile file = fileUploader.getFile();
        if (null != file && !file.isEmpty()) {
          fileNameComplete.append(fileUploader.getFile().getOriginalFilename());
          fileNameComplete.append(" - Tag: ");
          fileNameComplete.append(fileUploader.getAlttag());
          fileNameComplete.append(" - Caption: ");
          fileNameComplete.append(fileUploader.getCaption());
          fileNames.add(fileNameComplete.toString());
        }
        // Handle file content - multipartFile.getInputStream()

      }
      // TODO DATABASE STORAGE

      // TODO FILESYSTEM STORAGE
      /*
       * FileCopyUtils.copy(file.getBytes(), new FileOutputStream("FILESYSTEM_PATH" +
       * file.getOriginalFilename()));
       */
    }

    map.addAttribute("files", fileNames);
    return "file_upload_success";
  }

}
