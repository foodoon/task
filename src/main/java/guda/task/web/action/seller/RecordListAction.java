package guda.task.web.action.seller;

import guda.task.biz.TaskListBiz;
import guda.task.biz.helper.FileHelper;
import guda.task.biz.vo.RecordVO;
import guda.task.common.security.AppContexHolder;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.DateHelper;
import guda.task.common.util.ErrorCode;
import guda.task.dao.domain.TaskListDO;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foodoon on 2014/12/29.
 */
@Controller
public class RecordListAction {

    public static final String url_char = "/";

    @Autowired
    private FileHelper filerHelper;
    @Autowired
    private TaskListBiz taskListBiz;

    @RequestMapping(value = "seller/record/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int t = RequestUtil.getInt(request, "t");
        long id = RequestUtil.getLong(request, "id");
        long taskId = RequestUtil.getLong(request, "taskId");
        //校验任务是否是自己发布的
        TaskListDO taskListDO = taskListBiz.queryById(taskId);
        if (taskListDO.getSellerId().longValue() != AppContexHolder.getContext().getUserDO().getId().longValue()) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR));
            return "500.vm";
        }
        String relativeDir = String.valueOf(id) + url_char + String.valueOf(taskId);

        File file = new File(filerHelper.getFileDir() + url_char + relativeDir);
        List<RecordVO> recordVOList = new ArrayList<RecordVO>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File f : files) {
                RecordVO recordVO = new RecordVO();
                recordVO.setImgName(f.getName());
                recordVO.setImgUrl(filerHelper.getFileSever() + url_char + relativeDir + url_char + f.getName());
                recordVO.setTime(DateHelper.format(f.lastModified()));
                recordVOList.add(recordVO);
            }

        }
        modelMap.addAttribute("recordVOList", recordVOList);
        return "seller/record/list.vm";

    }
}
