package com.gmail.at.irotech.uploader.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

@JsonIgnoreProperties({"bytes"})
public class FileUploader {

  private MultipartFile file;

  private String caption;

  private String alttag;

  private boolean defaultname;

  private byte[] bytes;

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getAlttag() {
    return alttag;
  }

  public void setAlttag(String alttag) {
    this.alttag = alttag;
  }

  public boolean isDefaultname() {
    return defaultname;
  }

  public void setDefaultname(boolean defaultname) {
    this.defaultname = defaultname;
  }

  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

}
