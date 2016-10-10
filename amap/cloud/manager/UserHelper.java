package amap.cloud.manager;

import org.json.JSONException;
import org.json.JSONObject;

import amap.cloud.protocol.HttpUtil;

import com.amap.bean.User;
/**
 *<b>UserHelper表帮助类</b><br>
 *此类继承CloudConnection类，仅仅处理有关用户传过来的data值。<br>
 *insert，update有关post的数据时由于对应参数类型不同（例：历史记录相应数据类型为History，用户信息相关数据类型为User）
 *需在子类相应处理。主要方法为public JSONObject parseData(Object data)
 * @File Name : UserHelper.java
 * @Author Author: haifeng.gong hongguang.jin
 */
public class UserHelper extends CloudConnection {
	/**
	 *高德云存储中的历史记录信息表的TABLEID=【5795c9e67bbf1978ba6916a4】*/
	private String tableID = HttpUtil.USER_TABLEID;
	/**高德云存储中的历史记录信息表的Column=【_id, _name,_address, userid, password, status】*/
	private final static String[] USER_FIELD = new String[] { "_id", "_name","_address", "userid", "password", "status" };
	/**
	*<b>在构造方法</b><br>
	*new UserHelper的同时设置将要访问的tableId*/
	public UserHelper() {
		setTableId(tableID);
	}
	/**
	 *<b>转换为JSONObject</b><br>
	 *把传入的形参Object data参数转换为JSONObject类型的数据。<br>
	 * @see amap.cloud.manager.CloudConnection#parseData(java.lang.Object)
	 * @param data
	 *          post到高德云存储表中的数据。
	 * @return JSONObject 
	 * 			转换后的JSONObject类型数据
	 */
	public JSONObject parseData(Object data) {
		if (data instanceof User) {
			try {
				User user = (User) data;
				JSONObject jsonData = new JSONObject();
				jsonData.put(USER_FIELD[0], user.getId());
				jsonData.put(USER_FIELD[1], user.getName());
				jsonData.put(USER_FIELD[2], user.getCurrentAddress());
				jsonData.put(USER_FIELD[3], user.getUserId());
				jsonData.put(USER_FIELD[4], user.getUserPassword());
				jsonData.put(USER_FIELD[5], user.getStatus());
				return jsonData;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
