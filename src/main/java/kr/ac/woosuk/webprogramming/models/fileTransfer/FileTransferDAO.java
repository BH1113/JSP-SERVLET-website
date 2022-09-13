package kr.ac.woosuk.webprogramming.models.fileTransfer;

public interface FileTransferDAO {
    public abstract void downloadImageFiles(String path);
    public abstract void uploadImageFiles(String path, String saveFileName);
}
