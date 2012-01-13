package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import service.UserService;

public class FileUpload extends ActionSupport implements SessionAware {
    private UserService userService;
    private Map<String, Object> session;    
    private File userImage;
    private String userImageContentType;
    private String userImageFileName;

    public String execute() {
        try {
            FileInputStream fin = new FileInputStream(userImage);
            byte[] fileContent = new byte[(int) userImage.length()];
            fin.read(fileContent);
            User user = (User) session.get("user");
            user.setPhoto(fileContent);
            userService.save(user);
            
            

        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the file " + ioe);
        }
        return SUCCESS;
    }

    public File getUserImage() {
        return userImage;
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public String getUserImageContentType() {
        return userImageContentType;
    }

    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public String getUserImageFileName() {
        return userImageFileName;
    }

    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }  
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }    
}
