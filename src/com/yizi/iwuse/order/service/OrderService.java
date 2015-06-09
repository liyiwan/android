package com.yizi.iwuse.order.service;

import com.yizi.iwuse.common.base.ICoreService;
import com.yizi.iwuse.framework.model.CmdResultInfo;
import com.yizi.iwuse.framework.service.CmdSendAdapter;
import com.yizi.iwuse.framework.service.MsgInterface.CmdInterface;

public class OrderService implements ICoreService {

    @Override
    public boolean initState() {
	return true;
    }

    /****
     * ex. 获取订单信息
     * 
     * @return
     */
    public CmdResultInfo getProductInfo() {

	// EventBus.getDefault().post(null);

	return CmdSendAdapter.sendCmd(CmdInterface.ORDER_OrderInfo, null);
    }

}
