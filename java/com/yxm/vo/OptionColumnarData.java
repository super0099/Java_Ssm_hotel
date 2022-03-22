package com.yxm.vo;

import java.io.Serializable;
import java.util.List;

public class OptionColumnarData implements Serializable {
    private MonthColumnarData xAxis;
    private yAxisColumnarData yAxis;
    private List<SeriesColumnarData> series;

    public MonthColumnarData getxAxis() {
        return xAxis;
    }

    public void setxAxis(MonthColumnarData xAxis) {
        this.xAxis = xAxis;
    }

    public yAxisColumnarData getyAxis() {
        return yAxis;
    }

    public void setyAxis(yAxisColumnarData yAxis) {
        this.yAxis = yAxis;
    }

    public List<SeriesColumnarData> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesColumnarData> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "optionColumnarData{" +
                "xAxis=" + xAxis +
                ", yAxis=" + yAxis +
                ", series=" + series +
                '}';
    }
}
