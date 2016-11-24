package com.one.more.see.bean.todo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mcz on 2016/11/20.
 */

public class TodoBean implements Serializable {
    private boolean isDone;
    private String Thing;
    private Date createTime;
    private Date updateTime;
    private Date finishTime;
    public int  prioroty;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getThing() {
        return Thing;
    }

    public void setThing(String thing) {
        Thing = thing;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getPrioroty() {
        return prioroty;
    }

    public void setPrioroty(int prioroty) {
        this.prioroty = prioroty;
    }
}
