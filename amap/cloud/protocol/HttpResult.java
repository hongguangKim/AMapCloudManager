package amap.cloud.protocol;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * <b>高德云存储反馈信息类</b><br>
 *此类为访问高德云存储后由高德云存储服务器中反馈的信息。
 * @ File Name : CloudConnection.java
 * @ Author : haifeng.gong hongguang.jin
 */
public class HttpResult {
	private String info = null;
	private String infocode = null;
	private String status = null;
	private Integer count = 0;
	private String id = null;
	/**
	 *<b>构造方法</b><br>
	 *创建此对象时同时处理反馈信息。处理方法为parseJSONInfo方法。
	 * @see amap.cloud.protocol.HttpResultparseData#HttpResult(java.lang.String)
	 */
	public HttpResult(String result) {
		try {
			parseJSONInfo(new JSONObject(result));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getInfo() {
		return info;
	}

	public String getInfocode() {
		return infocode;
	}

	public String getStatus() {
		return status;
	}

	public Integer getCount() {
		return count;
	}

	public String getId() {
		return id;
	}
	/**
	 *<b>反馈信息赋值</b><br>
	 *反馈信息转封装在HttpResult类型的数据。开发者可通过Getter获取相应的信息。
	 * @see amap.cloud.protocol.HttpResult#parseJSONInfo(java.lang.String)
	 * @param result
	 *          高德云存储反馈信息
	 * @return 
	 */
	public void parseJSONInfo(JSONObject result) {
		try {
			if (result.has("info"))
				info = result.getString("info");
			if (result.has("infocode"))
				infocode = result.getString("infocode");
			if (result.has("status"))
				status = result.getString("status");
			if (result.has("count"))
				count = result.getInt("count");
			if (result.has("_id"))
				id = result.getString("_id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "HttpResult [info=" + info + ", infocode=" + infocode
				+ ", status=" + status + ", count=" + count + ", id=" + id
				+ "]";
	}
}
