package com.tk.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/5/15.
 */
public class HttpPostUploadUtil {

	//内网图片服务地址
	private String uploadUrl;

	//外网访问地址
	private String netServiceUrl;

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getNetServiceUrl() {
		return netServiceUrl;
	}

	public void setNetServiceUrl(String netServiceUrl) {
		this.netServiceUrl = netServiceUrl;
	}

	/**
	 * 上传图片
	 *
	 * @return
	 */
	public String formUpload(MultipartFile file, String... sizes) {
		String urlStr = uploadUrl;
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符
		try {
			if (null != sizes && sizes.length > 0) {
				JSONObject textObj = new JSONObject();
				JSONArray arr = new JSONArray();
				for (int i = 0; i < sizes.length; i++) {
					String[] sis = sizes[i].split("x");
					if (null != sis && sis.length == 2) {
						JSONObject obj = new JSONObject();
						obj.put("w", sis[0]);
						obj.put("h", sis[1]);
						arr.add(obj);
					}
				}
				textObj.put("sizes", arr);
				urlStr = urlStr + "?imageinfo=" + URLEncoder.encode(textObj.toJSONString(), "utf-8");
			}

			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());

			//            Map<String, String> textMap = new HashMap<>();
			//            if (null != sizes && sizes.length > 0) {
			//                JSONObject textObj = new JSONObject();
			//                JSONArray arr = new JSONArray();
			//                for (int i = 0; i < sizes.length; i++) {
			//                    String[] sis = sizes[i].split("x");
			//                    if (null != sis && sis.length == 2) {
			//                        JSONObject obj = new JSONObject();
			//                        obj.put("w", sis[0]);
			//                        obj.put("h", sis[1]);
			//                        arr.add(obj);
			//                    }
			//                }
			//                textObj.put("sizes", arr);
			//
			////                textMap.put("imageinfo", URLEncoder.encode(textObj.toJSONString(), "utf-8"));
			//                textMap.put("imageinfo", "asdasdasdasd");
			//
			//
			////                StringBuffer strBuf = new StringBuffer();
			////                strBuf.append("\r\n").append("--").append(BOUNDARY).append(
			////                        "\r\n");
			////                strBuf.append("Content-Disposition: form-data; name=\""
			////                        + "imageinfo" + "\"\r\n\r\n");
			////                strBuf.append(textObj.toJSONString());
			////                out.write(strBuf.toString().getBytes());
			//            }
			//
			//            if (textMap != null && textMap.size() > 0) {
			//                StringBuffer strBuf = new StringBuffer();
			//                Iterator iter = textMap.entrySet().iterator();
			//                while (iter.hasNext()) {
			//                    Map.Entry entry = (Map.Entry) iter.next();
			//                    String inputName = (String) entry.getKey();
			//                    String inputValue = (String) entry.getValue();
			//                    if (inputValue == null) {
			//                        continue;
			//                    }
			//                    strBuf.append("\r\n").append("--").append(BOUNDARY)
			//                            .append("\r\n");
			//                    strBuf.append("Content-Disposition: form-data; name=\""
			//                            + inputName + "\"\r\n\r\n");
			//                    strBuf.append(inputValue);
			//                    strBuf.append("\r\n");
			//                }
			//                out.write(strBuf.toString().getBytes());
			//            }

			// file
			if (file != null) {
				String contentType = "image/jpeg";
				StringBuffer strBuf = new StringBuffer();
				strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
				strBuf.append("Content-Disposition: form-data; name=\"" + file.getName() + "\"; filename=\"" + file.getName() + "\"\r\n");
				strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

				out.write(strBuf.toString().getBytes());

				DataInputStream in = new DataInputStream(file.getInputStream());
				int bytes = 0;
				byte[] bufferOut = new byte[1024];
				while ((bytes = in.read(bufferOut)) != -1) {
					out.write(bufferOut, 0, bytes);
				}
				in.close();

			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

}
