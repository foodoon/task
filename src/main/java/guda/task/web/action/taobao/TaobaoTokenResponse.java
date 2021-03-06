/**
 * zoneland.net Inc.
 * Copyright (c) 2002-2013 All Rights Reserved.
 */
package guda.task.web.action.taobao;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author foodoon
 * @version $Id: TaobaoTokenResponse.java, v 0.1 2013-7-23 上午8:45:22 foodoon Exp $
 */
public class TaobaoTokenResponse {

    private long w2_expires_in;

    private String taobao_user_id;

    private String taobao_user_nick;
    private long w1_expires_in;
    private long re_expires_in;
    private long r2_expires_in;

    private long expires_in;
    private String token_type;
    private String refresh_token;
    private String access_token;
    private long r1_expires_in;

    public long getW2_expires_in() {
        return w2_expires_in;
    }

    public void setW2_expires_in(long w2_expires_in) {
        this.w2_expires_in = w2_expires_in;
    }

    public String getTaobao_user_id() {
        return taobao_user_id;
    }

    public void setTaobao_user_id(String taobao_user_id) {
        this.taobao_user_id = taobao_user_id;
    }

    public String getTaobao_user_nick() {
        return taobao_user_nick;
    }

    public void setTaobao_user_nick(String taobao_user_nick) {
        this.taobao_user_nick = taobao_user_nick;
    }

    public long getW1_expires_in() {
        return w1_expires_in;
    }

    public void setW1_expires_in(long w1_expires_in) {
        this.w1_expires_in = w1_expires_in;
    }

    public long getRe_expires_in() {
        return re_expires_in;
    }

    public void setRe_expires_in(long re_expires_in) {
        this.re_expires_in = re_expires_in;
    }

    public long getR2_expires_in() {
        return r2_expires_in;
    }

    public void setR2_expires_in(long r2_expires_in) {
        this.r2_expires_in = r2_expires_in;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getR1_expires_in() {
        return r1_expires_in;
    }

    public void setR1_expires_in(long r1_expires_in) {
        this.r1_expires_in = r1_expires_in;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
