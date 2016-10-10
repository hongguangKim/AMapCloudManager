package amap.cloud.manager;

import org.json.JSONException;
import org.json.JSONObject;

import amap.cloud.protocol.HttpUtil;

import com.amap.bean.Fence;

/**
 *<b>Fence表帮助类</b><br>
 *此类继承CloudConnection类，仅仅处理有关用户传过来的data值。<br>
 *insert，update有关post的数据时由于对应参数类型不同（例：历史记录相应数据类型为History，用户信息相关数据类型为User）
 *需在子类相应处理。主要方法为public JSONObject parseData(Object data)
 * @File Name : FenceHelper.java
 * @Author Author: haifeng.gong hongguang.jin
 */
public class FenceHelper extends CloudConnection {
	/**
	 *高德云存储中的历史记录信息表的TABLEID=【579a9bafafdf52627ba21f48】*/
	public String tableID = HttpUtil.FENCE_TABLEID;
	/** 
	 *高德云存储中的历史记录信息表的Column=【_id, _name,_address, userid1, userid2, radius】*/
	private final static String[] FENCE_FIELD = new String[] { "_id", "_name","_address", "userid1", "userid2", "radius" };
	/**
	 *<b>在构造方法</b><br>
	 *new FenceHelper的同时设置将要访问的tableId*/
	public FenceHelper() {
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
		if (data instanceof Fence) {
			try {
				Fence fence = (Fence) data;
				JSONObject jsonData = new JSONObject();
				jsonData.put(FENCE_FIELD[0], fence.getId());
				jsonData.put(FENCE_FIELD[1], fence.getName());
				jsonData.put(FENCE_FIELD[2], fence.getAddress());
				jsonData.put(FENCE_FIELD[3], fence.getUserId1());
				jsonData.put(FENCE_FIELD[4], fence.getUserId2());
				jsonData.put(FENCE_FIELD[5], fence.getRadius());
				return jsonData;
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

}
