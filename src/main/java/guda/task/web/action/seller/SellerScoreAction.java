package guda.task.web.action.seller;

import guda.mvc.form.Form;
import guda.task.biz.*;
import guda.task.biz.enums.TaskStatusEnum;
import guda.task.biz.query.TaobaoItemQuery;
import guda.task.common.security.AppContexHolder;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.ErrorCode;
import guda.task.dao.domain.*;
import guda.task.web.action.admin.form.ScoreForm;
import guda.task.web.action.seller.form.ActForm;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by foodoon on 2014/12/28.
 */
@Controller
public class SellerScoreAction {
    private Logger log  = LoggerFactory.getLogger(SellerScoreAction.class);

    @Autowired
    private TaskListBiz taskListBiz;
    @Autowired
    private TaskAcceptBiz taskAcceptBiz;
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private UserScoreBiz userScoreBiz;
    @Autowired
    private UserScoreStatBiz userScoreStatBiz;
    @RequestMapping(value = "/seller/act/score.htm", method = RequestMethod.GET)
    public String doGet(  ScoreForm scoreForm, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        long taskId = RequestUtil.getLong(request, "id");
        TaskListDO taskListDO = taskListBiz.queryById(taskId);
        if(taskListDO == null){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        if(taskListDO.getStatus().intValue()!= TaskStatusEnum.SUCCESS.getValue()){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.STATUS_ERROR));
            return "500.vm";
        }
        TaskAcceptDO taskAcceptDO = taskAcceptBiz.queryValidAccept(taskId);
        UserDO buyer = userBiz.queryById(taskAcceptDO.getUserId());
        modelMap.addAttribute("taskListDO",taskListDO);
        modelMap.addAttribute("buyer",buyer);
        modelMap.addAttribute("taskAcceptDO",taskAcceptDO);
        return "seller/act/score.vm";

    }

    @Form
    @RequestMapping(value = "/seller/act/doScore.htm", method = RequestMethod.POST)
    public String doScore(@Valid ScoreForm scoreForm, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        if (result.hasErrors()) {
            return "seller/act/score.vm";
        }
        TaskListDO taskListDO = taskListBiz.queryById(scoreForm.getTaskId());
        if(taskListDO == null){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        if(taskListDO.getStatus().intValue()!= TaskStatusEnum.SUCCESS.getValue()){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.STATUS_ERROR));
            return "500.vm";
        }
        taskListDO.setScored(1);
        taskListBiz.update(taskListDO);
        try {
            TaskAcceptDO taskAcceptDO = taskAcceptBiz.queryValidAccept(scoreForm.getTaskId());
            UserDO buyer = userBiz.queryById(taskAcceptDO.getUserId());
            UserScoreDO userScoreDO = new UserScoreDO();
            userScoreDO.setUserId(buyer.getId());
            userScoreDO.setScore(scoreForm.getScore());
            userScoreDO.setTaskId(scoreForm.getTaskId());
            userScoreDO.setRemark(scoreForm.getMsg());
            userScoreBiz.create(userScoreDO);
            UserScoreStatDO userScoreStatDO = userScoreStatBiz.queryByUserId(buyer.getId());
            if (userScoreStatDO == null) {
                userScoreStatDO = new UserScoreStatDO();
                userScoreStatDO.setUserId(buyer.getId());
                userScoreStatDO.setGmtModified(new Date());
                userScoreStatDO.setScore(scoreForm.getScore());
                userScoreStatDO.setScoreNum(1);
                userScoreStatBiz.create(userScoreStatDO);
            } else {
                userScoreStatDO.setScoreNum(userScoreStatDO.getScoreNum() + 1);
                userScoreStatDO.setScore(userScoreStatDO.getScore() + scoreForm.getScore());
                userScoreStatDO.setGmtModified(new Date());
                userScoreStatBiz.update(userScoreStatDO);
            }
            return "redirect:/seller/act/his.htm";
        }catch(Exception e){
            log.error("",e);
        }
        return "500.vm";

    }
}
