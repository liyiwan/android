package com.yizi.iwuse.user.service;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.AppParams;
import com.yizi.iwuse.common.base.ICoreService;
import com.yizi.iwuse.common.utils.ILog;
import com.yizi.iwuse.constants.UserConst;
import com.yizi.iwuse.framework.model.CmdResultInfo;
import com.yizi.iwuse.framework.service.CmdSendAdapter;
import com.yizi.iwuse.framework.service.MsgInterface.CmdInterface;
import com.yizi.iwuse.user.model.ThridUserInfo;
import com.yizi.iwuse.user.service.events.UserEvents;

import de.greenrobot.event.EventBus;

public class UserService implements ICoreService {
    private static final String TAG = "UserService";

    @Override
    public boolean initState() {
	return true;
    }

    /****
     * ex. 获取用户信息
     * 
     * @return
     */
    public CmdResultInfo getCustomerInfo() {
	// EventBus.getDefault().post(null);
	return CmdSendAdapter.sendCmd(CmdInterface.CUSTOMER_DetailInfo, null);
    }

    /****
     * 登录成功后，更新用户信息
     * 
     */
    public void onLoginUpdateUserInfo(int loginType, Object responseData) {
	ThridUserInfo userInfo = new ThridUserInfo();
	ILog.i(TAG, "onLoginUpdateUserInfo");
	switch (loginType) {
	case UserConst.LOGINTYPE_WEICHAT:
	    break;
	case UserConst.LOGINTYPE_QQ:
	    try {
		JSONObject rspJson = new JSONObject(responseData.toString());
		userInfo.nickName = rspJson.getString("nickname");
		userInfo.headUrl = rspJson.getString("figureurl_qq_2");
	    } catch (JSONException e) {
		ILog.e(TAG, e);
	    }
	    break;
	case UserConst.LOGINTYPE_PAYBAO:
	    break;
	case UserConst.LOGINTYPE_WEIBO:
	    break;
	default:
	    break;
	}
	// 修改用户全局登录状态为已登录
	AppParams.isLogin = true;

	UserEvents loginEvent = new UserEvents();
	loginEvent.eventtype = UserConst.ENVENTTYPE_LOGIN;
	loginEvent.customObj = userInfo;

	EventBus.getDefault().post(loginEvent);
    }

    /***
     * 注销用户，更新用户信息
     * 
     */
    public void onLogoutUpdateUserInfo() {

    }

}
