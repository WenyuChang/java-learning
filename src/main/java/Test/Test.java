package Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

public class Test {

	public static void main(String[] args)  {		
//		Test test = new Test();
//		test.pay();
//		test.refund();
		
		
		String aa = "amount=1&app_id=1001&auth_code=130551205247670274&body=商品明细&client_ip=127.0.0.1&currency=cny&device_info=05315820&nonce_str=fdaeac653c98494daefe1c80ee1d3937&order_no=130551205247670274&payment_channel=WX_CODEPAY&subject=商品&time_expire=600&";
		System.out.println(VpMD5.MD5Encode(aa).toUpperCase());
	}

	private String getOrder() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date today = Calendar.getInstance().getTime();
		TimeZone utc = TimeZone.getTimeZone("UTC");
		df.setTimeZone(utc);
		String order = df.format(today);
		return order;
	}
	
	public void pay() {
		String url = "http://52.76.95.172:8080/payment/codePay";

		String md5Key = "111111";

		Map<String, Object> map = new HashMap<String, Object>();
		// 配置参数
		map.put("app_id", "1001");
		map.put("payment_channel", "WX_CODEPAY");
		// 增加加密随机性
		map.put("nonce_str", "d976ac5a58c84098a1728125fff2c84e");
		map.put("time_expire", "200");
		// 业务参数
		map.put("order_no", getOrder());
		map.put("amount", "1");
		map.put("currency", "cny");
		map.put("client_ip", "127.0.0.1");
		map.put("subject", "商品");
		map.put("body", "商品明细");
		map.put("auth_code", "130299835204608680");
		map.put("device_info", "05315820");
		
		List<String> extraList=new ArrayList<>();
		 
		// 所有请求参数都参与签名
		map.put("sign", VpSignature.getSign(map,md5Key));

		try {
			System.out.println(map.toString());
			String result = SimpleHttpClientUtils.excuteSimplePost(url, map);
			System.out.println(result);
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/**
		 * 返回实例
		 * {"success":true,"errorCode":null,"message":null,"data":{"t":{"orderNo":"2016072800002","appId":"1001","paymentChannel":"WX_CODEPAY","amount":1,"clientIp":"127.0.0.1","currency":"cny","subject":"测试","body":"好东西","timeExpire":200,"authCode":"130469581881037580","deviceInfo":null,"extraMap":null,"dataChannel":null,"serverIp":"127.0.0.1","paymentId":"211607280010010000064"},"veryPlanetNum":null,"finishTime":1469700191298,"status":"1"}}
		 */
	}

//	public void refund() {
////		String url = "http://52.76.95.172:8080/payment/refundPay";
//		
//		String url = "http://127.0.0.1:8083/payment/refundPay";
//
//
//		String md5Key = "111111";
//
//		Map<String, Object> map = new HashMap<>();
//		// 配置参数
//		map.put("app_id", "1001");
// 		// 增加加密随机性
//		map.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
//		 
//		// 业务参数
//		map.put("order_no", "2016072800007");
//		map.put("amount", "1");
// 		map.put("client_ip", "127.0.0.1");
// 		map.put("ext_trade_no", "");
// 		map.put("description", "就是要退");
//
//		// 所有请求参数都参与签名
//		map.put("sign", VpSignature.getSign(map, md5Key));
//
//		try {
//			System.out.println(map.toString());
//			String result = SimpleHttpClientUtils.excuteSimplePost(url, map);
//			System.out.println(result);
//		} catch (URISyntaxException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		/**
//		 * {"success":true,"errorCode":null,"message":null,"data":{"t":{"orderNo":"2016072800002","paymentId":null,"appId":"1001","extTradeNo":"","amount":1,"refundChannel":null,"refundComment":null,"dataChannel":null,"refundId":"2116072800100100069","extraMap":null},"veryPlanetNum":null,"finishTime":1469700668869,"status":"1"}}
//		 */
//		
//	}
}
