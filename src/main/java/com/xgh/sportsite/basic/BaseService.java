package com.xgh.sportsite.basic;

import com.xgh.sportsite.util.ConstantUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础Service
 *
 * @author: duanxg
 * @date: 2015/12/23 16:03
 * @Email:
 */
@Service
public class BaseService {


    /**
     * 获取节点
     *
     * @return
     */
    protected String getOsNO() {
        return "101000";
    }

    /**
     * 获取行业Id
     *
     * @return
     */
    protected long getCurrentInstId(HttpServletRequest request) {
        return 100L;
    }

    protected String getServerUrl() {
        //文件上传的临时路径判断
        return ConstantUtil.SERVER_URL;
    }
    protected double formatDouble(double d){
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 获取返回的resultMap
     *
     * @param flg
     * @param msg
     * @return
     */
    protected Map<String, Object> getResultMap(Object flg, Object msg) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put(ConstantUtil.ResultKey.resultFlg.value(), flg);
        resultMap.put(ConstantUtil.ResultKey.resultMsg.value(), msg);

        return resultMap;
    }

    protected Map<String, Object> getResultMap(Object flg, Object msg, Object data) {
        Map<String, Object> resultMap = getResultMap(flg, msg);

        resultMap.put(ConstantUtil.ResultKey.resultData.value(), data);

        return resultMap;
    }
}
