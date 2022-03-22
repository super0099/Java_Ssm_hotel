package com.yxm.po;

import java.io.Serializable;

public class SysEnvironment implements Serializable {
    private Integer id;
    private String pictureName;
    private byte pictureType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public byte getPictureType() {
        return pictureType;
    }

    public void setPictureType(byte pictureType) {
        this.pictureType = pictureType;
    }

    @Override
    public String toString() {
        return "SysEnvironment{" +
                "id=" + id +
                ", pictureName='" + pictureName + '\'' +
                ", pictureType=" + pictureType +
                '}';
    }
}
