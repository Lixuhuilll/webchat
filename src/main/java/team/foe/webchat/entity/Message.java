package team.foe.webchat.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    /**
     * 用于身份验证时的状态码，通常不用于消息推送
     */
    private Integer errorCode;
    /**
     * 应该写入从 1970-1-1 00:00:00 UTC 到该日期字符串所表示日期的毫秒数
     */
    private Long time;
    private String msg;
    private String from;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    // 设置为 1970-1-1 00:00:00 UTC 到当前时间的毫秒数
    public void setNowTime() {
        this.time = System.currentTimeMillis();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
