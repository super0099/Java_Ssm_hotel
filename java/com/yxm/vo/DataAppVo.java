package com.yxm.vo;

import java.io.Serializable;

public class DataAppVo implements Serializable {
    private Integer state;
    private Boolean isOk;
    private Integer controllerCode;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }

    public Integer getControllerCode() {
        return controllerCode;
    }

    public void setControllerCode(Integer controllerCode) {
        this.controllerCode = controllerCode;
    }
}
