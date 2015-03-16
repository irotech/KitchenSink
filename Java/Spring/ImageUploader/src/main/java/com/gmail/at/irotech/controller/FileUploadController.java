package com.gmail.at.irotech.controller;

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

import com.gmail.at.irotech.model.FileUpload;
import com.gmail.at.irotech.model.FileUploadForm;
import com.gmail.at.irotech.validator.FileUploadValidator;

@Controller
public class FileUploadController {

	@Autowired
	private FileUploadValidator fileUploadValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(fileUploadValidator);
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String displayForm() {
		return "file_upload_form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@Valid @ModelAttribute("uploadForm") FileUploadForm uploadForm,
			BindingResult result, Model map) {

		if (result.hasErrors()) {
			return displayForm();
		}

		List<FileUpload> files = uploadForm.getFiles();

		List<String> fileNames = new ArrayList<String>();

		if (null != files && files.size() > 0) {
			for (FileUpload fileUpload : files) {
				
				StringBuilder fileNameComplete = new StringBuilder("");
				
				MultipartFile file = fileUpload.getFile();
				if (null != file && !file.isEmpty()) {
					fileNameComplete.append(fileUpload.getFile().getOriginalFilename());
					fileNameComplete.append(" - Tag: ");
					fileNameComplete.append(fileUpload.getAlttag());
					fileNameComplete.append(" - Caption: ");
					fileNameComplete.append(fileUpload.getCaption());
					fileNames.add(fileNameComplete.toString());
				}
				// Handle file content - multipartFile.getInputStream()
				
			}
			//TODO DATABASE STORAGE
			
			//TODO FILESYSTEM STORAGE
			/*
			 * FileCopyUtils.copy(file.getBytes(), new FileOutputStream("FILESYSTEM_PATH" + file.getOriginalFilename()));
			 */
		}

		map.addAttribute("files", fileNames);
		return "file_upload_success";
	}

}