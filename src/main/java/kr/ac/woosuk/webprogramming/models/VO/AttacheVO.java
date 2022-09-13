package kr.ac.woosuk.webprogramming.models.VO;

public class AttacheVO {
    private int id;
    private int boardId;
    private String path;
    private String originalFileName;
    private String saveFileName;
    private byte[] imageFile;

    public AttacheVO(int id, int boardId, String path, String originalFileName, String saveFileName, byte[] imageFile) {
        this.id = id;
        this.boardId = boardId;
        this.path = path;
        this.originalFileName = originalFileName;
        this.saveFileName = saveFileName;
        this.imageFile = imageFile;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public int getId() {
        return id;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getPath() {
        return path;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getSaveFileName() {
        return saveFileName;
    }
}
