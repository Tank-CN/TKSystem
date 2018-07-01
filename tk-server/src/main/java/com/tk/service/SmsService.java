package com.tk.service;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

/**
 * 短信服务
 */
public interface SmsService {

	public Map<String, Object> sendSmsCode(String numb, String code, int kinds) throws IOException, ParserConfigurationException, SAXException;
}
