package kr.ac.woosuk.webprogramming.models.DTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class ImageDTO {
    private FileInputStream fileInputStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ByteArrayInputStream byteArrayInputStream;
    private String saveFileName;

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public ByteArrayOutputStream getByteArrayOutputStream() {
        return byteArrayOutputStream;
    }

    public void setByteArrayOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
        this.byteArrayOutputStream = byteArrayOutputStream;
    }

    public ByteArrayInputStream getByteArrayInputStream() {
        return byteArrayInputStream;
    }

    public void setByteArrayInputStream(ByteArrayInputStream byteArrayInputStream) {
        this.byteArrayInputStream = byteArrayInputStream;
    }


}
