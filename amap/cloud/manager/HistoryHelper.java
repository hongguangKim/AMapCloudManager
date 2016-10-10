package amap.cloud.manager;

import org.json.JSONException;
import org.json.JSONObject;

import amap.cloud.protocol.HttpUtil;

import com.amap.bean.History;
/**
 *<b>History表帮助类</b><br>
 *此类继承CloudConnection类，仅仅处理有关用户传过来的data值。<br>
 *insert，update有关post的数据时由于对应参数类型不同（例：历史记录相应数据类型为History，用户信息相关数据类型为User）
 *需在子类相应处理。主要方法为public JSONObject parseData(Object data)
 * @File Name : HistoryHelper.java
 * @Author Author: haifeng.gong hongguang.jin
 */
public class HistoryHelper extends CloudConnection {
	/**
	 *高德云存储中的历史记录信息表的TABLEID=【5799bc78afdf52627b9dd3ac】*/
	public String tableID=HttpUtil.HISTORY_TABLEID;
	/**高德云存储中的历史记录信息表的Column=【_id,_name,_address,userid】*/
	private final static String[] LOCATION_FIELD = new String[]{"_id","_name","_address","userid"};

	/**
	*<b>在构造方法</b><br>
	*new HistoryHelper的同时设置将要访问的tableId*/
	public  HistoryHelper() {
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
	@Override
	public JSONObject parseData(Object data) {
		// TODO Auto-generated method stub
		if (data instanceof History) {
			try {
				History history = (History) data;
				JSONObject jsonData = new JSONObject();
				jsonData.put(LOCATION_FIELD[0], history.getId());
				jsonData.put(LOCATION_FIELD[1], history.getName());
				jsonData.put(LOCATION_FIELD[2], history.getAddress());
				jsonData.put(LOCATION_FIELD[3], history.getUserid());
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
