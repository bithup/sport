/**
 * 智能代付单笔付款接口
 *
 * @author xiezz
 * @version 1.1.2
 */
package com.xgh.pay.service.py;



import com.xgh.pay.common.Configure;
import com.xgh.pay.common.SignAlgorithm;
import com.xgh.pay.service.IPostService;
import com.xgh.pay.util.DateTimeUtil;
import com.xgh.pay.util.Signature;

import java.util.Map;

public class PyPay extends IPostService {

    private static final String SERVICE_NAME = "cib.epay.payment.pay";
    private static final String SERVICE_VER = "02";
    private static final String SERVICE_CUR = "CNY";

    @Override
    public String exec(Map<String, String> params) {

        params.put("appid", Configure.getAppid());
        params.put("service", SERVICE_NAME);
        params.put("ver", SERVICE_VER);
        params.put("sub_mrch", Configure.getSub_mrch());
        params.put("cur", SERVICE_CUR);
        params.put("timestamp", DateTimeUtil.getDateTime());
        params.put("sign_type", SignAlgorithm.get(SERVICE_NAME));
        params.put("mac", Signature.generateMAC(params));

        return txn(Configure.isDevEnv() ? Configure.PY_DEV_API : Configure.PY_PROD_API, params);
    }
}
