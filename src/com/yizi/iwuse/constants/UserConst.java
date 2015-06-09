package com.yizi.iwuse.constants;

/****
 * 用户常量
 * 
 * @author zhangxiying
 *
 */
public class UserConst {

    /** 用户事件类型-登录 **/
    public static final int ENVENTTYPE_LOGIN = 0x500001;

    public static final int LOGINTYPE_WEICHAT = 0x9001;
    public static final int LOGINTYPE_QQ = 0x9002;
    public static final int LOGINTYPE_PAYBAO = 0x9003;
    public static final int LOGINTYPE_WEIBO = 0x9004;

    public static final String LOGIN_YOUMENG = "com.umeng.login";

    /** ~~~~~~~~~~~~~~授权登录~~~~~~~~~~~~~~~ **/
    // 微信
    // 应用标识
    public static final String OAUTH_WEICHAT_APPID = "wxd240c43dd010f108";// "wxd930ea5d5a258f4f";//
    // 应用密钥
    public static final String OAUTH_WEICHAT_SECRET = "8b0423e8272fecafbb61ece531c03d05";// "375a728a398ae4c73d4edf1d833f80bc";//
    // 授权作用域
    public static final String OAUTH_WEICHAT_SCOPE = "all";
    // 保持请求和回调的状态
    public static final String OAUTH_WEICHAT_STATE = "weichat_iwuse_0x9891";
    /** 微信授权登录成功，获取数据 **/
    // 第一步URL 获取code
    public static final String OAUTH_WEICHAT_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?";
    // appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"
    // 第二步URL 获取用户信息
    public static String GetUserInfo = "https://api.weixin.qq.com/sns/userinfo?";
    // "access_token=ACCESS_TOKEN&openid=OPENID";
    // QQ
    // 应用标识
    public static final String OAUTH_QQ_APPID = "1104654550";

    public static final String OAUTH_QQ_APPKEY = "bBt8igECdhIH2aGU";

    public static final String OAUTH_QQ_SCOPE = "get_user_info,add_t";

    // 新浪微博
    public static final String OAUTH_WEIBO_APPID = "723854602";
    public static final String OAUTH_WEIBO_SECRET = "4681fd71ab31140082c9bc522025682f";
    public static final String OAUTH_WEIBO_REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    public static final String OAUTH_WEIBO_SCOPE = "";

}
