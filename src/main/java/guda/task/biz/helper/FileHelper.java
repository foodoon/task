package guda.task.biz.helper;

import guda.task.common.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileHelper {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private String fileSever;
    private String fileDir;
    private long maxSize;

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public String getFileSever() {
        return fileSever;
    }

    public void setFileSever(String fileSever) {
        this.fileSever = fileSever;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String putFileToPlace(long userId,MultipartFile file,String taskId) throws IOException {
        File newFile = getNewFile(userId,file, getFileDir(),taskId);
        file.transferTo(newFile);
        return fileSever + "/"+newFile.getName();
    }

    private File getNewFile(long userId,MultipartFile file, String path,String taskId) {
        String fileName = fixFileName(file.getOriginalFilename());
        String f = getDestinationFileName(userId,fileName,taskId);
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdirs();
        }
        File newFile = new File(path,f);
        if(!newFile.exists()){
            newFile.mkdirs();
        }
        return newFile;
    }

    private String fixFileName(String fileName) {
        while(fileName.contains("..")){
            fileName = fileName.replace("..", ".");
        }

        if(fileName.lastIndexOf(".")==0){
            fileName = System.currentTimeMillis() + fileName;
        }
        if(fileName.startsWith(".")){
            fileName = fileName.substring(1, fileName.length());
        }
        return fileName;
    }


    public static String getDestinationFileName(long userId,String fileName,String taskId) {
        int cutIndex = fileName.lastIndexOf(".");
        String fileExt = "";
        if (-1 != cutIndex) {
            fileExt = fileName.substring(cutIndex);
            return String.valueOf(userId) +File.separator + taskId + File.separator + DateHelper.currentTimeForDir() + fileExt;
        }
        return String.valueOf(userId) +File.separator + taskId + File.separator + DateHelper.currentTimeForDir() ;
    }




    public static boolean isImage(MultipartFile file) {
        return "image/pjpeg".equals(file.getContentType()) || "image/jpeg".equals(file.getContentType()) || "image/gif".equals(file.getContentType())
                || "image/png".equals(file.getContentType());
    }


    public boolean deleteFile(String fileName, String path) {
        String file = path + fileName;
        File f = new File(file);
        if (!f.exists()) {
            logger.debug("FileService.deleteFile(): can't delete " + fileName + " - not exist");
        }
        if (!f.canWrite()) {
            logger.debug("FileService.deleteFile(): can't delete " + fileName + " - not allowed to delete");
        }
        if (f.isDirectory()) {
            logger.debug("FileService.deleteFile(): can't delete " + fileName + " - not file, but directory");
        }
        return f.delete();
    }
}
