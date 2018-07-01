package com.tk.easemob;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tk.util.http.HttpClientTemplate;
import com.tk.util.http.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/10/5.
 */
public class EasemobUtils {


    private static JsonNodeFactory factory = new JsonNodeFactory(false);

    private static String APPKEY;
    private static final String API_SERVER_HOST="a1.easemob.com";
    private static String APP_CLIENT_ID;
    private static String APP_CLIENT_SECRET;

    private static final String API_HTTP_SCHEMA = "https";
    private static URL TOKEN_APP_URL;
    private static URL USERS_URL;
    private static URL MESSAGES_URL;
    private static URL CHATGROUPS_URL;
    private static URL CHATFILES_URL;

    private static final Logger LOGGER = LoggerFactory.getLogger(EasemobUtils.class);
    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential;
    /**
     * 批量注册用户每次注册数
     */
    private static final Long perNumber = 30L;

    private static HttpClientTemplate clientTemplate;
    static{
        try {
            clientTemplate = HttpClientTemplate.instance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查easemob用户，如果用户不存在则添加easemob用户
     *
     * @param userName
     * @return (用户存在返回true,用户不存在添加成功返回true,否则返回false)
     */
    public static boolean checkUser(String userName){
        /**
         * 获取IM用户[主键查询]
         */
        Map<String, String> ebUserMap = generateIMUserInfo(userName);
        HttpResult iMUserNameNode = EasemobUtils.getIMUsersByUserName(ebUserMap.get("username"));
        if (null != iMUserNameNode && 200==iMUserNameNode.getStatusCode()) {
            return true;
        } else {
            /**
             * 注册IM用户
             */
            ObjectNode datanode = JsonNodeFactory.instance.objectNode();
            datanode.put("username", ebUserMap.get("username"));
            datanode.put("password", ebUserMap.get("password"));
            HttpResult result = EasemobUtils.createNewIMUserSingle(datanode);
            if (null != result && result.getStatusCode()==200) {
                return true;
            }else{
                return false;
            }
        }
    }


    /**
     * 获取IM用户
     *
     * @param userName
     *            用户主键：username或者uuid
     * @return
     */
    public static HttpResult getIMUsersByUserName(String userName) {
        HttpResult result = new HttpResult();
        try {

            // check APPKEY format
            if (!match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", getAPPKEY())) {
                LOGGER.error("Bad format of APPKEY: " + getAPPKEY());
                result.setBody("Bad format of APPKEY");
                result.setStatusCode(650);
                return result;
            }

            // check properties that must be provided
            if (StringUtils.isEmpty(userName)) {
                LOGGER.error("The userName that will be used to query must be provided .");
                result.setBody("The userName that will be used to query must be provided .");
                result.setStatusCode(650);
                return result;
            }


            URL userPrimaryUrl = getURL(getAPPKEY().replace("#", "/") + "/users/" + userName);
            result = clientTemplate.doGet("HTTPS", userPrimaryUrl.toString(), getHeadMap(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 注册IM用户[单个]
     *
     * 给指定APPKEY创建一个新的用户
     *
     * @param dataNode
     * @return
     */
    public static HttpResult createNewIMUserSingle(ObjectNode dataNode) {
        HttpResult result = new HttpResult();
        try {

            // check APPKEY format
            if (!match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", getAPPKEY())) {
                LOGGER.error("Bad format of APPKEY: " + getAPPKEY());
                result.setBody("Bad format of APPKEY");
                result.setStatusCode(650);
                return result;
            }

            // check properties that must be provided
            if (null != dataNode && !dataNode.has("username")) {
                LOGGER.error("Property that named username must be provided .");
                result.setBody("Property that named username must be provided .");
                result.setStatusCode(650);
                return result;
            }
            if (null != dataNode && !dataNode.has("password")) {
                LOGGER.error("Property that named password must be provided .");
                result.setBody("Property that named password must be provided .");
                result.setStatusCode(650);
                return result;
            }

            result = clientTemplate.doPost("HTTPS", getUsersUrl().toString(), getHeadMap(), dataNode, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 注册IM用户[批量]
     *
     * 给指定Constants.APPKEY创建一批用户
     *
     * @param dataArrayNode
     * @return
     */
    public static HttpResult createNewIMUserBatch(ArrayNode dataArrayNode) {
        HttpResult result = new HttpResult();
        // check Constants.APPKEY format
        if (!match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", getAPPKEY())) {
            LOGGER.error("Bad format of Constants.APPKEY: " + getAPPKEY());
            result.setBody("Bad format of Constants.APPKEY");
            result.setStatusCode(650);
            return result;
        }

        // check properties that must be provided
        if (dataArrayNode.isArray()) {
            for (JsonNode jsonNode : dataArrayNode) {
                if (null != jsonNode && !jsonNode.has("username")) {
                    LOGGER.error("Property that named username must be provided .");
                    result.setBody("Property that named username must be provided .");
                    result.setStatusCode(650);
                    return result;
                }
                if (null != jsonNode && !jsonNode.has("password")) {
                    LOGGER.error("Property that named password must be provided .");
                    result.setBody("Property that named password must be provided .");
                    result.setStatusCode(650);
                    return result;
                }
            }
        }
        try {
            result = clientTemplate.doPost("HTTPS", getUsersUrl().toString(), getHeadMap(), dataArrayNode, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 添加好友[单个]
     *
     * @param ownerUserName
     * @param friendUserName
     *
     * @return
     */
    public static HttpResult addFriendSingle(String ownerUserName, String friendUserName) {
        HttpResult result = new HttpResult();
        try {

            // check Constants.APPKEY format
            if (!match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", getAPPKEY())) {
                LOGGER.error("Bad format of Constants.APPKEY: " + getAPPKEY());
                result.setBody("Bad format of Constants.APPKEY");
                result.setStatusCode(650);
                return result;
            }

            if (StringUtils.isEmpty(ownerUserName)) {
                LOGGER.error("Your userName must be provided，the value is username of imuser.");
                result.setBody("Your userName must be provided，the value is username of imuser.");
                result.setStatusCode(650);
                return result;
            }

            if (StringUtils.isEmpty(friendUserName)) {
                LOGGER.error("The userName of friend must be provided，the value is username of imuser.");
                result.setBody("The userName of friend must be provided，the value is username of imuser.");
                result.setStatusCode(650);
                return result;
            }


            URL addFriendSingleUrl = getURL(getAPPKEY().replace("#", "/") + "/users/"
                    + ownerUserName + "/contacts/users/" + friendUserName);

            ObjectNode body = factory.objectNode();
            result = clientTemplate.doPost("HTTPS",addFriendSingleUrl.toString(),getHeadMap(), body, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 获取用户所有好友
     *
     * @param ownerUserName
     *
     * @return
     */
    public static HttpResult getFriends(String ownerUserName) {
        HttpResult result = new HttpResult();
        try {
            // check Constants.APPKEY format
            if (!match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", getAPPKEY())) {
                LOGGER.error("Bad format of Constants.APPKEY: " + getAPPKEY());
                result.setBody("Bad format of Constants.APPKEY");
                result.setStatusCode(650);
                return result;
            }

            if (StringUtils.isEmpty(ownerUserName)) {
                LOGGER.error("Your userName must be provided，the value is username of imuser.");
                result.setBody("Your userName must be provided，the value is username of imuser.");
                result.setStatusCode(650);
                return result;
            }


            URL addFriendSingleUrl = getURL(getAPPKEY().replace("#", "/") + "/users/"
                    + ownerUserName + "/contacts/users");

            result = clientTemplate.doGet("HTTPS", addFriendSingleUrl.toString(), getHeadMap(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }








    /**
     * 通过手机号生成Eb账号密码
     * @param userPhoneNO
     * @return
     */
    public static Map<String,String> generateIMUserInfo(String userPhoneNO){
        String ebUserName = "eb" + userPhoneNO;
        String ebPassword =  "ebpwd"+userPhoneNO;
        Map<String,String> map = new HashMap<String,String>();
        map.put("username", ebUserName);
        map.put("password", ebPassword);
        return map;
    }

    private static Map<String,String> getHeadMap(){
        Map<String, String> header = new HashMap<String, String>();
        header.put("Authorization","Bearer "+EasemobUtils.getCredential().getToken().toString());
        return header;
    }

    public static void setAPPKEY(String APPKEY) {
        EasemobUtils.APPKEY = APPKEY;
    }

    public static void setAppClientId(String appClientId) {
        APP_CLIENT_ID = appClientId;
    }

    public static void setAppClientSecret(String appClientSecret) {
        APP_CLIENT_SECRET = appClientSecret;
    }


    public static String getAPPKEY() {
        return APPKEY;
    }


    public static String getApiServerHost() {
        return API_SERVER_HOST;
    }


    public static String getAppClientId() {
        return APP_CLIENT_ID;
    }


    public static String getAppClientSecret() {
        return APP_CLIENT_SECRET;
    }

    public static URL getTokenAppUrl() {
        if(TOKEN_APP_URL==null){
            TOKEN_APP_URL = getURL(EasemobUtils.getAPPKEY().replace("#", "/") + "/token");
        }
        return TOKEN_APP_URL;
    }


    public static URL getUsersUrl() {
        if(USERS_URL==null){
            USERS_URL = getURL(EasemobUtils.getAPPKEY().replace("#", "/") + "/users");
        }
        return USERS_URL;
    }

    public static URL getMessagesUrl() {
        if(MESSAGES_URL==null){
            MESSAGES_URL = getURL(EasemobUtils.getAPPKEY().replace("#", "/") + "/messages");
        }
        return MESSAGES_URL;
    }

    public static URL getChatgroupsUrl() {
        if(CHATGROUPS_URL==null){
            CHATGROUPS_URL = getURL(EasemobUtils.getAPPKEY().replace("#", "/") + "/chatgroups");
        }
        return CHATGROUPS_URL;
    }

    public static URL getChatfilesUrl() {
        if(CHATFILES_URL==null){
            CHATFILES_URL = getURL(EasemobUtils.getAPPKEY().replace("#", "/") + "/chatfiles");
        }
        return CHATFILES_URL;
    }


    public static Credential getCredential() {
        if(credential==null){
            credential = new ClientSecretCredential(getAppClientId(),
                    getAppClientSecret());
        }
        return credential;
    }


    public static HttpClient getClient(boolean isSSL) {

        HttpClient httpClient = new DefaultHttpClient();
        if (isSSL) {
            X509TrustManager xtm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            try {
                SSLContext ctx = SSLContext.getInstance("TLS");

                ctx.init(null, new TrustManager[] { xtm }, null);

                SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);

                httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

            } catch (Exception e) {
                throw new RuntimeException();
            }
        }

        return httpClient;
    }

    public static URL getURL(String path) {
        URL url = null;

        try {
            url = new URL(API_HTTP_SCHEMA, EasemobUtils.getApiServerHost(), "/" + path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.lookingAt();
    }

}
