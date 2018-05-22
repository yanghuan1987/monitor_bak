package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.Date;

public class ProvinceReportDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long forecastId;
    //区域辖区预报信息
    private String forecastInfo;
    //区域辖区形势图-有辖区形势图
    private byte[] mapPath;
    //区域辖区形势图-有辖区形势图
    private String mapPathName;
    //转发区域辖区正式发布的预警信息-有预警信息
    private String warningInfo;
    //区域辖区更多预报信息-有更多区域预报信息
    private String otherInfo;
    //区域辖区更多预报信息-无更多区域预报信息
    private byte[] detailSceneryImagePath;
    //区域辖区更多预报信息-无更多区域预报信息
    private String detailSceneryImagePathName;
    //区域辖区形势图-无辖区形势图
    private byte[] sceneryImagesPath;
    //区域辖区形势图-无辖区形势图
    private String sceneryImagesPathName;
    //转发区域辖区正式发布的预警信息-手动填写健康提示
    private String healthTips;
    //省编码
    private Integer provinceCode;
    //0:无预警信息，1：有预警信息
    private Integer isWarning;
    //1:手动填写健康提示,0:自动健康提示
    private Integer ishealthTips;
    //0:无更多区域预报信息,1:有更多区域预报信息
    private Integer isReportInfo;
    //0:无辖区形势图,1:有辖区形势图
    private Integer isPicInfo;
    //图片位置
    private  Integer picPosition;
    //更新时间
    private Date creatDate;
    //更新人
    private  String creatName;

    public Integer getPicPosition() {
        return picPosition;
    }

    public void setPicPosition(Integer picPosition) {
        this.picPosition = picPosition;
    }

    public long getForecastId() {
        return forecastId;
    }

    public void setForecastId(long forecastId) {
        this.forecastId = forecastId;
    }

    public String getForecastInfo() {
        return forecastInfo;
    }

    public void setForecastInfo(String forecastInfo) {
        this.forecastInfo = forecastInfo;
    }

    public byte[] getMapPath() {
        return mapPath;
    }

    public void setMapPath(byte[] mapPath) {
        this.mapPath = mapPath;
    }

    public String getWarningInfo() {
        return warningInfo;
    }

    public void setWarningInfo(String warningInfo) {
        this.warningInfo = warningInfo;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public byte[] getDetailSceneryImagePath() {
        return detailSceneryImagePath;
    }

    public void setDetailSceneryImagePath(byte[] detailSceneryImagePath) {
        this.detailSceneryImagePath = detailSceneryImagePath;
    }

    public byte[] getSceneryImagesPath() {
        return sceneryImagesPath;
    }

    public void setSceneryImagesPath(byte[] sceneryImagesPath) {
        this.sceneryImagesPath = sceneryImagesPath;
    }

    public String getHealthTips() {
        return healthTips;
    }

    public void setHealthTips(String healthTips) {
        this.healthTips = healthTips;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Integer getIsWarning() {
        return isWarning;
    }

    public void setIsWarning(Integer isWarning) {
        this.isWarning = isWarning;
    }

    public Integer getIshealthTips() {
        return ishealthTips;
    }

    public void setIshealthTips(Integer ishealthTips) {
        this.ishealthTips = ishealthTips;
    }

    public Integer getIsReportInfo() {
        return isReportInfo;
    }

    public void setIsReportInfo(Integer isReportInfo) {
        this.isReportInfo = isReportInfo;
    }

    public Integer getIsPicInfo() {
        return isPicInfo;
    }

    public void setIsPicInfo(Integer isPicInfo) {
        this.isPicInfo = isPicInfo;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    public String getMapPathName() {
        return mapPathName;
    }

    public void setMapPathName(String mapPathName) {
        this.mapPathName = mapPathName;
    }

    public String getDetailSceneryImagePathName() {
        return detailSceneryImagePathName;
    }

    public void setDetailSceneryImagePathName(String detailSceneryImagePathName) {
        this.detailSceneryImagePathName = detailSceneryImagePathName;
    }

    public String getSceneryImagesPathName() {
        return sceneryImagesPathName;
    }

    public void setSceneryImagesPathName(String sceneryImagesPathName) {
        this.sceneryImagesPathName = sceneryImagesPathName;
    }
}