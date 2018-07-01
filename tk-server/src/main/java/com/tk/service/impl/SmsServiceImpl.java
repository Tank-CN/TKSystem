package com.tk.service.impl;

import com.tk.service.SmsService;
import com.tk.util.HTTPClient;
import com.tk.util.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Service(value = "smsService")
@Transactional(readOnly = true)
public class SmsServiceImpl implements SmsService {

	public static String sms_url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
//	public static String sms_account = "cf_bsoft_test";
//	public static String sms_psd = "bsoft123456";
	public static String sms_account = "cf_tuozun";
	public static String sms_psd = "tuozunwangluo";
	public static String sms_template_code = "您的确认码是：【变量】。请不要把确认码泄露给其他人。如非本人操作，可不用理会！";
	public static String sms_template_psd = "您的新密码为【变量】。请不要泄露给其他人。请立即更改您的密码！";

	/**
	 * 发送短信
	 * 
	 * @param numb
	 * @param code
	 * @param kinds
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public Map<String, Object> sendSmsCode(String numb, String code, int kinds) throws IOException, ParserConfigurationException, SAXException {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, Object> rultMap = new HashMap<String, Object>();
		params.put("account", sms_account);
		params.put("password", sms_psd);
		params.put("mobile", numb);
		switch (kinds) {
		case 1:
			params.put("content", sms_template_code.replace("【变量】", code));
			break;// 验证码
		case 2:
			params.put("content", sms_template_psd.replace("【变量】", code));
			break;// 密码
		default:
			break;
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(HTTPClient.doPost(sms_url, params));
		InputSource is = new InputSource(sr);
		Document document = db.parse(is);
		Element rootElement = document.getDocumentElement();

		NodeList codeList = rootElement.getElementsByTagName("code");
		NodeList msgList = rootElement.getElementsByTagName("msg");
		if (codeList.getLength() > 0 && msgList.getLength() > 0) {
			Node codeNode = codeList.item(0);
			if (codeNode.getFirstChild().getTextContent().equals("2")) {
				rultMap.put("code", ResultCode.SUCCESS);
				rultMap.put("msg", "发送成功");
			} else {
				rultMap.put("code", ResultCode.ERROR);
				rultMap.put("msg", "短信发送失败");
			}
		} else {
			rultMap.put("code", ResultCode.ERROR);
			rultMap.put("msg", "短信错误!");
		}
		return rultMap;
	}
}
