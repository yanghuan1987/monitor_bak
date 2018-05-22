package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.ProvinceReportDto;

import java.io.Serializable;
import java.util.Date;

public class ProvinceReportVo extends ProvinceReportDto implements Serializable {

    private static final long serialVersionUID = 1L;
    //更新时间开始
    private Date creatDateST;
    //更新时间结束
    private Date creatDateED;

    private Integer deletePic;

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getCreatDateST() {
        return creatDateST;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setCreatDateST(Date creatDateST) {
        this.creatDateST = creatDateST;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getCreatDateED() {
        return creatDateED;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setCreatDateED(Date creatDateED) {
        this.creatDateED = creatDateED;
    }

    public Integer getDeletePic() {
        return deletePic;
    }

    public void setDeletePic(Integer deletePic) {
        this.deletePic = deletePic;
    }
}