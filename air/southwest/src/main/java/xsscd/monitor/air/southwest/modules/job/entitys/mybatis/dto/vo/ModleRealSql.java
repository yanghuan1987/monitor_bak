package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo;

import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;

/**
 * Created by train on 17/2/16.
 */
public class ModleRealSql {
    public String getExpertAirLvlCountSourceSql(SelectPiontVO selectPiontVO){
        String returnSql ="SELECT\n" +
                "\tdatetime,\n" +
                "\tProvinceName,\n" +
                "\tareaName,\n" +
                "\tCityName,\n" +
                "\tCityCode,\n" +
                "\tquality_score,\n" +
                "\tpm2_5_score,\n" +
                "\taqi_score,\n" +
                "\tprimarypollutant1_score\n" +
                "FROM\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tdatetime,\n" +
                "\t\t\tProvinceName,\n" +
                "\t\t\tareaName,\n" +
                "\t\t\tCityName,\n" +
                "\t\t\tCityCode,\n" +
                "\t\t\tquality_real,\n" +
                "\t\t\tquality1,\n" +
                "\t\t\tprimarypollutant_real,\n" +
                "\t\t\tprimarypollutant1,\n" +
                "\t\t\tpm2_5,\n" +
                "\t\t\tpm2_5_real,\n" +
                "\t\t\taqi,\n" +
                "\t\t\taqi_real,\n" +
                "\t\t\tPM25_le,\n" +
                "\t\t\tCASE\n" +
                "\t\tWHEN aqi = aqi_real THEN\n" +
                "\t\t\t100\n" +
                "\t\tWHEN aqi < aqi_real THEN\n" +
                "\t\t\t(1 -(aqi_real - aqi) / aqi_real) * 100\n" +
                "\t\tWHEN aqi > aqi_real THEN\n" +
                "\t\t\t(1 -(aqi - aqi_real) / aqi) * 100\n" +
                "\t\tELSE\n" +
                "\t\t\t0\n" +
                "\t\tEND aqi_score,\n" +
                "\t\tCASE\n" +
                "\tWHEN pm2_5 = pm2_5_real THEN\n" +
                "\t\t100\n" +
                "\tWHEN pm2_5 < pm2_5_real THEN\n" +
                "\t\t(\n" +
                "\t\t\t1 - (pm2_5_real - pm2_5) / pm2_5_real\n" +
                "\t\t) * 100\n" +
                "\tWHEN pm2_5 > pm2_5_real THEN\n" +
                "\t\t(1 -(pm2_5 - pm2_5_real) / pm2_5) * 100\n" +
                "\tELSE\n" +
                "\t\t0\n" +
                "\tEND pm2_5_score,\n" +
                "\tCASE\n" +
                "WHEN CHARINDEX(\n" +
                "\tprimarypollutant_real,\n" +
                "\tprimarypollutant1\n" +
                ") > 0\n" +
                "OR (\n" +
                "\tprimarypollutant_real = '无'\n" +
                "\tAND CHARINDEX(quality_real, quality1) > 0\n" +
                ") THEN\n" +
                "\t100\n" +
                "ELSE\n" +
                "\t0\n" +
                "END primarypollutant1_score,\n" +
                " CASE\n" +
                "WHEN CHARINDEX(quality_real, quality1) > 0 THEN\n" +
                "\t100\n" +
                "ELSE\n" +
                "\t0\n" +
                "END quality_score,\n" +
                " CASE\n" +
                "WHEN quality_real IS NOT NULL THEN\n" +
                "\t1\n" +
                "ELSE\n" +
                "\t0\n" +
                "END quality_day,\n" +
                " CASE\n" +
                "WHEN quality1 IS NOT NULL THEN\n" +
                "\t1\n" +
                "ELSE\n" +
                "\t0\n" +
                "END quality_f_day,\n" +
                " CASE\n" +
                "WHEN CHARINDEX(\n" +
                "\tprimarypollutant_real,\n" +
                "\tprimarypollutant1\n" +
                ") > 0\n" +
                "OR (\n" +
                "\tprimarypollutant_real = '无'\n" +
                "\tAND CHARINDEX(quality_real, quality1) > 0\n" +
                ") THEN\n" +
                "\t1\n" +
                "ELSE\n" +
                "\t0\n" +
                "END primarypollutant1_day,\n" +
                " CASE\n" +
                "WHEN CHARINDEX(quality_real, quality1) > 0 THEN\n" +
                "\t1\n" +
                "ELSE\n" +
                "\t0\n" +
                "END quality_accurate,\n" +
                " CASE\n" +
                "WHEN quality_real = quality1 THEN\n" +
                "\t1\n" +
                "WHEN CHARINDEX(quality_real, quality1) > 0 THEN\n" +
                "\t0.5\n" +
                "WHEN quality_real = quality1 THEN\n" +
                "\t1\n" +
                "ELSE\n" +
                "\t0\n" +
                "END quality_hit,\n" +
                " CASE\n" +
                "WHEN quality_real = '重度污染'\n" +
                "OR quality_real = '严重污染' THEN\n" +
                "\t1\n" +
                "ELSE\n" +
                "\t0\n" +
                "END quality_heavy,\n" +
                " CASE\n" +
                "WHEN CHARINDEX('重度污染', quality1) > 0\n" +
                "OR CHARINDEX('严重污染', quality1) > 0 THEN\n" +
                "\t1\n" +
                "ELSE\n" +
                "\t0\n" +
                "END quality_f_heavy,\n" +
                " CASE\n" +
                "WHEN (\n" +
                "\tquality_real = '重度污染'\n" +
                "\tOR quality_real = '严重污染'\n" +
                ")\n" +
                "AND (\n" +
                "\tCHARINDEX('重度污染', quality1) > 0\n" +
                "\tOR CHARINDEX('严重污染', quality1) > 0\n" +
                ") THEN\n" +
                "\t1\n" +
                "ELSE\n" +
                "\t0\n" +
                "END quality_heavy_accurate,\n" +
                " CASE\n" +
                "WHEN (\n" +
                "\tquality_real = '重度污染'\n" +
                "\tAND CHARINDEX('重度污染', quality1) = 0\n" +
                ")\n" +
                "OR (\n" +
                "\tquality_real = '严重污染'\n" +
                "\tAND CHARINDEX('严重污染', quality1) = 0\n" +
                ") THEN\n" +
                "\t1\n" +
                "ELSE\n" +
                "\t0\n" +
                "END quality_heavy_false,\n" +
                " CASE\n" +
                "WHEN (\n" +
                "\tquality_real <> '重度污染'\n" +
                "\tAND quality_real <> '严重污染'\n" +
                ")\n" +
                "AND (\n" +
                "\tCHARINDEX('重度污染', quality1) > 0\n" +
                "\tOR CHARINDEX('严重污染', quality1) > 0\n" +
                ") THEN\n" +
                "\t1\n" +
                "ELSE\n" +
                "\t0\n" +
                "END quality_heavy_empty\n" +
                "FROM\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tb.datetime,\n" +
                "\t\t\tp.ProvinceName,\n" +
                "\t\t\tca.areaName,\n" +
                "\t\t\ta.CityName,\n" +
                "\t\t\ta.CityCode,\n" +
                "\t\t\tb.AQI AS aqi_real,\n" +
                "\t\t\tCASE\n" +
                "\t\tWHEN b.Chiefly_Infectant = '—' THEN\n" +
                "\t\t\tREPLACE(\n" +
                "\t\t\t\tb.Chiefly_Infectant,\n" +
                "\t\t\t\t'—',\n" +
                "\t\t\t\t'无'\n" +
                "\t\t\t)\n" +
                "\t\tELSE\n" +
                "\t\t\tREPLACE(\n" +
                "\t\t\t\tb.Chiefly_Infectant,\n" +
                "\t\t\t\t'O3_8',\n" +
                "\t\t\t\t'臭氧'\n" +
                "\t\t\t)\n" +
                "\t\tEND primarypollutant_real,\n" +
                "\t\tb.GradeDescription quality_real,\n" +
                "\t\tb.PM25 AS pm2_5_real,\n" +
                "\t\tdbo.GetStrArrayStrOfIndex (f.quality, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/) quality1,\n" +
                "\t\t(\n" +
                "\t\t\tCAST (\n" +
                "\t\t\t\tCASE ISNUMERIC(\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.pm2_5, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t1\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\t)\n" +
                "\t\t\t\tWHEN 0 THEN\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\tELSE\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.pm2_5, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t1\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\tEND AS FLOAT\n" +
                "\t\t\t) + CAST (\n" +
                "\t\t\t\tCASE ISNUMERIC(\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.pm2_5, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t2\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\t)\n" +
                "\t\t\t\tWHEN 0 THEN\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\tELSE\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.pm2_5, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t2\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\tEND AS FLOAT\n" +
                "\t\t\t)\n" +
                "\t\t) / 2 pm2_5,\n" +
                "\t\t(\n" +
                "\t\t\tCAST (\n" +
                "\t\t\t\tCASE ISNUMERIC(\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.aqi, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t1\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\t)\n" +
                "\t\t\t\tWHEN 0 THEN\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\tELSE\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.aqi, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t1\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\tEND AS FLOAT\n" +
                "\t\t\t) + CAST (\n" +
                "\t\t\t\tCASE ISNUMERIC(\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.aqi, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t2\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\t)\n" +
                "\t\t\t\tWHEN 0 THEN\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\tELSE\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.aqi, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t2\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\tEND AS FLOAT\n" +
                "\t\t\t)\n" +
                "\t\t) / 2 aqi,\n" +
                "\t\t(\n" +
                "\t\t\tCASE\n" +
                "\t\t\tWHEN CAST (\n" +
                "\t\t\t\tCASE ISNUMERIC(b.PM25)\n" +
                "\t\t\t\tWHEN 0 THEN\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\tELSE\n" +
                "\t\t\t\t\tb.PM25\n" +
                "\t\t\t\tEND AS FLOAT\n" +
                "\t\t\t) >= CAST (\n" +
                "\t\t\t\tCASE ISNUMERIC(\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.pm2_5, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t1\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\t)\n" +
                "\t\t\t\tWHEN 0 THEN\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\tELSE\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.pm2_5, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t1\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\tEND AS FLOAT\n" +
                "\t\t\t)\n" +
                "\t\t\tAND CAST (\n" +
                "\t\t\t\tCASE ISNUMERIC(b.PM25)\n" +
                "\t\t\t\tWHEN 0 THEN\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\tELSE\n" +
                "\t\t\t\t\tb.PM25\n" +
                "\t\t\t\tEND AS FLOAT\n" +
                "\t\t\t) <= CAST (\n" +
                "\t\t\t\tCASE ISNUMERIC(\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.pm2_5, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t2\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\t)\n" +
                "\t\t\t\tWHEN 0 THEN\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\tELSE\n" +
                "\t\t\t\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\t\t\t\tdbo.GetStrArrayStrOfIndex (f.pm2_5, '|', "+ selectPiontVO.getForcastDay() +" /*{day}*/),\n" +
                "\t\t\t\t\t\t'~',\n" +
                "\t\t\t\t\t\t2\n" +
                "\t\t\t\t\t)\n" +
                "\t\t\t\tEND AS FLOAT\n" +
                "\t\t\t) THEN\n" +
                "\t\t\t\t1\n" +
                "\t\t\tELSE\n" +
                "\t\t\t\t0\n" +
                "\t\t\tEND\n" +
                "\t\t) PM25_le,\n" +
                "\t\tdbo.GetStrArrayStrOfIndex (\n" +
                "\t\t\tf.primarypollutant,\n" +
                "\t\t\t'|',\n" +
                "\t\t\t"+ selectPiontVO.getForcastDay() +" /*{day}*/\n" +
                "\t\t) primarypollutant1\n" +
                "\tFROM\n" +
                "\t\tsouthwestCity a\n" +
                "\tLEFT JOIN CityArea ca ON ca.id = a.areaId\n" +
                "\tLEFT JOIN Province1 p ON p.id = ca.provincesId\n" +
                "\tLEFT JOIN (\n" +
                "\t\tSELECT\n" +
                "\t\t\t*\n" +
                "\t\tFROM\n" +
                "\t\t\tGW_Fact_CityDay\n" +
                "\t\tWHERE\n" +
                "\t\t\tdatetime >= '"+ DateUtil.convertToStr(selectPiontVO.getDateST()) +"' /*{time}*/\n" +
                "\t\tAND datetime <= '"+ DateUtil.convertToStr(selectPiontVO.getDateED()) +"' /*{time}*/\n" +
                "\t) b ON a.CityCode1 = b.citycode\n" +
                "\tLEFT JOIN (\n" +
                "\t\tSELECT\n" +
                "\t\t\tforecastaudit.citycode,\n" +
                "\t\t\tDATEADD(\n" +
                "\t\t\t\tDAY,\n" +
                "\t\t\t\t"+ selectPiontVO.getForcastDay() +"/*{day}*/,\n" +
                "\t\t\t\tforecastaudit.timepoint\n" +
                "\t\t\t) timepoint,\n" +
                "\t\t\tforecastaudit.aqi,\n" +
                "\t\t\tforecastaudit.content,\n" +
                "\t\t\tREPLACE(\n" +
                "\t\t\t\tforecastaudit.primarypollutant,\n" +
                "\t\t\t\t'臭氧8小时',\n" +
                "\t\t\t\t'臭氧'\n" +
                "\t\t\t) primarypollutant,\n" +
                "\t\t\tREPLACE(\n" +
                "\t\t\t\tforecastaudit.quality,\n" +
                "\t\t\t\t'至',\n" +
                "\t\t\t\t'或'\n" +
                "\t\t\t) quality,\n" +
                "\t\t\tforecastset.pm2_5_dr AS pm2_5\n" +
                "\t\tFROM\n" +
                "\t\t\tforecastaudit\n" +
                "\t\tLEFT JOIN forecastset ON forecastaudit.citycode = forecastset.citycode\n" +
                "\t\tAND forecastaudit.timepoint = forecastset.timepoint\n" +
                "\t\tWHERE\n" +
                "\t\t\tforecastaudit.timepoint >= DATEADD(DAY,-"+ selectPiontVO.getForcastDay() +",'"+ DateUtil.convertToStr(selectPiontVO.getDateST()) +"') /*{time}*/\n" +
                "\t\tAND forecastaudit.timepoint <= DATEADD(DAY,-"+ selectPiontVO.getForcastDay() +",'"+ DateUtil.convertToStr(selectPiontVO.getDateST()) +"') /*{time}*/\n" +
                "\t) f ON a.CityCode1 / 100 = f.citycode\n" +
                "\tAND f.timepoint = b.datetime\n" +
                "\t) data\n" +
                "\t) m;";
        return returnSql;
 }





}
