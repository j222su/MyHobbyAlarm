package com.example.myhobbyalarm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class ToDoItem implements Serializable {

    /**
     * 일정 기록에 사용될 변수
     * */
    public String C_ID = "_id";
    public String TITLE = "title";
    public String TYPE = "type";
    public String DETAIL = "description";
    public String TIME = "time";
    public String DATE = "date";
    public String ALARM_TIMES = "alarmTimes";
    public String CHANNEL_NAME = "channelName";

    /**
     * TODO
     * 포기/미루기/다음 알람 설정
     * */

    public ToDoItem(String c_ID, String TITLE, String TYPE, String DETAIL, String TIME, String DATE, String ALARM_TIMES, String CHANNEL_NAME) {
        C_ID = c_ID;
        this.TITLE = TITLE;
        this.TYPE = TYPE;
        this.DETAIL = DETAIL;
        this.TIME = TIME;
        this.DATE = DATE;
        this.ALARM_TIMES = ALARM_TIMES;
        this.CHANNEL_NAME = CHANNEL_NAME;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "C_ID='" + C_ID + '\'' +
                ", TITLE='" + TITLE + '\'' +
                ", TYPE='" + TYPE + '\'' +
                ", DETAIL='" + DETAIL + '\'' +
                ", TIME='" + TIME + '\'' +
                ", DATE='" + DATE + '\'' +
                ", ALARM_TIMES='" + ALARM_TIMES + '\'' +
                ", CHANNEL_NAME='" + CHANNEL_NAME + '\'' +
                '}';
    }

    public String getC_ID() {
        return C_ID;
    }

    public void setC_ID(String c_ID) {
        C_ID = c_ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getDETAIL() {
        return DETAIL;
    }

    public void setDETAIL(String DETAIL) {
        this.DETAIL = DETAIL;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getALARM_TIMES() {
        return ALARM_TIMES;
    }

    public void setALARM_TIMES(String ALARM_TIMES) {
        this.ALARM_TIMES = ALARM_TIMES;
    }

    public String getCHANNEL_NAME() {
        return CHANNEL_NAME;
    }

    public void setCHANNEL_NAME(String CHANNEL_NAME) {
        this.CHANNEL_NAME = CHANNEL_NAME;
    }
}
