package guda.task.web.helper;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by foodoon on 2014/12/19.
 */
public class JsonViewHelper {

    public static String toJson(Object obj) {
        if (obj == null) {
            return "{}";
        } else {
            return JSON.toJSONString(obj);
        }
    }

    public static void ajaxReturn(Object obj, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.print(toJson(obj));
            out.flush();
        } catch (IOException e) {

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void ajaxReturnObj(Object obj, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/json;charset=UTF-8");
            out = response.getWriter();
            out.print(obj);
            out.flush();
        } catch (IOException e) {

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void ajaxReturnObjText(Object obj, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/plain;charset=UTF-8");
            out = response.getWriter();
            out.print(toJson(obj));
            out.flush();
        } catch (IOException e) {

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void jsonReturn(Object obj, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/json;charset=UTF-8");
            out = response.getWriter();
            out.print(obj);
            out.flush();
        } catch (IOException e) {

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
