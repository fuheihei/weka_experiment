package com.njuse.dm.preprocess;

import java.util.List;

public class DataSetAttribute {
    String attrName;
    boolean isNumeric;
    List<String> clsNames;

    boolean isCls;

    public boolean isCls() {
        return isCls;
    }

    public void setCls(boolean cls) {
        isCls = cls;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public boolean isNumeric() {
        return isNumeric;
    }

    public void setNumeric(boolean numeric) {
        isNumeric = numeric;
    }

    public List<String> getClsNames() {
        return clsNames;
    }

    public void setClsNames(List<String> clsNames) {
        this.clsNames = clsNames;
    }
}
