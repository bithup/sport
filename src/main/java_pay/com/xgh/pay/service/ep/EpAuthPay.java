/**
 * 快捷支付认证支付合一交易
 *
 * @author xiezz
 * @version 1.1.2
 */
package com.xgh.pay.service.ep;



import com.xgh.pay.common.Configure;
import com.xgh.pay.common.SignAlgorithm;
import com.xgh.pay.service.IRedirectService;
import com.xgh.pay.util.DateTimeUtil;
import com.xgh.pay.util.Signature;

import java.util.Map;


public class EpAuthPay extends IRedirectService {

    private static final String SERVICE_NAME = "cib.epay.acquire.authAndPay";
    private static final String SERVICE_VER = "01";
    private static final String SERVICE_CUR = "CNY";

    public String build(Map<String, String> params) {

        params.put("appid", Configure.getAppid());
        params.put("service", SERVICE_NAME);
        params.put("ver", SERVICE_VER);
        params.put("sub_mrch", Configure.getSub_mrch());
        params.put("cur", SERVICE_CUR);
        params.put("order_time", DateTimeUtil.getDateTime());
        params.put("timestamp", DateTimeUtil.getDateTime());
        params.put("sign_type", SignAlgorithm.get(SERVICE_NAME));
        params.put("mac", Signature.generateMAC(params));

        return buildRedirectFullPage(Configure.isDevEnv() ? Configure.EP_DEV_API : Configure.EP_PROD_API, params);
    }
}
