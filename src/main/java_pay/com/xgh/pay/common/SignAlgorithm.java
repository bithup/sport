/**
 * 服务签名算法
 *
 * @author xiezz
 * @version 1.1.2
 */
package com.xgh.pay.common;


import java.util.HashMap;
import java.util.Map;

public class SignAlgorithm {

    private final static String DEFAULT_SIGN_TYPE = "SHA1";
    private final static Map<String, String> signAlg = new HashMap<String, String>() {{
        put("cib.epay.acquire.easypay.acctAuth", "SHA1");
        put("cib.epay.acquire.easypay.quickAuthSMS", "SHA1");
        put("cib.epay.acquire.checkSms", "SHA1");
        put("cib.epay.acquire.easypay.cancelAuth", "SHA1");
        put("cib.epay.acquire.easypay.acctAuth.query", "SHA1");
        put("cib.epay.acquire.easypay", "SHA1");
        put("cib.epay.acquire.easypay.query", "SHA1");
        put("cib.epay.acquire.easypay.refund", "SHA1");
        put("cib.epay.acquire.easypay.refund.query", "SHA1");
        put("cib.epay.acquire.authAndPay", "SHA1");
        put("cib.epay.acquire.easypay.quickAuth", "RSA");

        put("cib.epay.acquire.cashier.netPay", "SHA1");
        put("cib.epay.acquire.cashier.query", "SHA1");
        put("cib.epay.acquire.cashier.refund", "SHA1");
        put("cib.epay.acquire.cashier.refund.query", "SHA1");

        put("cib.epay.payment.getMrch", "RSA");
        put("cib.epay.payment.pay", "SHA1");
        put("cib.epay.payment.get", "SHA1");

        put("cib.epay.acquire.settleFile", "SHA1");
        put("cib.epay.payment.receiptFile", "SHA1");
    }};

    public static String get(String service) {
        if (signAlg.containsKey(service))
            return signAlg.get(service);
        else
            return DEFAULT_SIGN_TYPE;
    }
}
