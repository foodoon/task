package guda.task.web.action.buyer;

import guda.task.biz.helper.FileHelper;
import guda.task.biz.vo.AjaxResponce;
import guda.task.common.security.AppContexHolder;
import guda.task.web.action.BaseAjaxAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by well on 2014/12/29.
 */
@Controller
public class FileUploadAction extends BaseAjaxAction{

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FileHelper fileHelper;

    @RequestMapping("buyer/upload.json")
    public void upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        try {
            String taskId = request.getParameter("taskId");

            if(!FileHelper.isImage(file)){
                ajaxReturn(new AjaxResponce(false,"不允许的文件"), response);
                return;
            }
            if(file.getSize()>fileHelper.getMaxSize()){
                ajaxReturn(new AjaxResponce(false,"文件过大"), response);
                return;
            }
            String url = fileHelper.putFileToPlace(AppContexHolder.getContext().getUserDO().getId(),file,taskId);
            ajaxReturn(new AjaxResponce(true,url), response);
            return;
        } catch (IOException e) {
            logger.error("",e);
        }
        ajaxReturn(new AjaxResponce(false,"服务器错误"), response);
    }
}
