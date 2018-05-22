package xsscd.monitor.air.southwest.modules.core.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
public interface ImportDateService {
    public  boolean  importPollutantAnalysisDayDate( String time) throws ParseException;
  
}
