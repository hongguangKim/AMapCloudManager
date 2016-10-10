package amap.cloud.manager;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import amap.cloud.protocol.HttpService;
import amap.cloud.protocol.HttpUtil;
/**
 * <b>高德云存储操作类</b><br>
 *此类为抽象类。实现了FenceHelper，HistoryHelper，UserHelper的共同部分。
 *开发者可以通过此类去创建FenceHelper，HistoryHelper，UserHelper类的实例。
 *可进行insert，update，delete，query相关操作。
 * @ File Name : CloudConnection.java
 * @ Author : haifeng.gong hongguang.jin
 */
public abstract class CloudConnection {
	/**
	 *<b>处理数据的上传与下载操作的对象</b>*/
	public HttpService connection;
	/** 
	 *<b>高德云图数据表ID</b>。
	 *操作高德云存储时需指定一个tableid才能访问对应的数据表*/
	public String tableId = null;
	/**
	 *<b>构造方法</b>，
	 *new一个实例对象CloudConnection的同时创建HttpService对象*/
	public CloudConnection() {
		connection = new HttpService();
	}
	/**
	 *<b>添加数据</b><br>
	 *添加数据到高德云存储数据表中，一次只能上传一条数据。
	 * @see amap.cloud.manager.CloudConnection#insert(java.lang.Object)
	 * @param data
	 *          添加到高德云存储表中的数据。
	 * @return JSONObject 
	 * 			返回高德云存储反馈信息。
	 */
	public String insert(Object data) {
		return connection.HttpPost(HttpUtil.INSERT,
				toNameValuePairData(parseData(data)));
	}
	/**
	 *<b>删除数据</b><br>
	 *从高德云存储数据表中删除一条数据。一次只能删除一条数据。<br>
	 *并指定高德云存储相应表中的一个_id<br>
	 * @see amap.cloud.manager.CloudConnection#delete(java.lang.String)
	 * @param id
	 *          删除所需条目的id值
	 * @return JSONObject 
	 * 			返回高德云存储反馈信息。
	 */
	public String delete(String id) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("key", HttpUtil.WEBAPIKEY));
		formparams.add(new BasicNameValuePair("tableid", tableId));
		formparams.add(new BasicNameValuePair("ids", id));
		return connection.HttpPost(HttpUtil.DELETE, formparams);
	}
	/**
	 *<b>更新数据</b><br>
	 *更新数据到高德云存储数据表中，参数data中必须指明id值。<br>
	 * @see amap.cloud.manager.CloudConnection#update(java.lang.Object)
	 * @param data
	 *          更新的数据
	 * @return JSONObject 
	 * 			返回高德云存储反馈信息。
	 */
	public String update(Object data) {
		return connection.HttpPost(HttpUtil.UPDATE,
				toNameValuePairData(parseData(data)));
	}
	/**
	 *<b>查询数据</b><br>
	 *从高德云存储数据表中查询数据，用户通过索引值过滤获取数据。<br>
	 * @see amap.cloud.manager.CloudConnection#query(java.lang.String)
	 * 
	 * 
	 * 
	 * @param filter
	 *          查询条件。<br>
	 *          1.单跳过滤条件：{columName:value}<br><br>
	 *          for example:<br>
	 *          {@code
	 *          	query("userid:1111");
	 *          }
	 *          <br><br>
	 *      	2.多条过滤条件：{columName:value+columName1:value2}<br>
	 *          for example:<br>
	 *          query("userid:1111+"status:1");<br>
	 * @return JSONObject 
	 * 			返回高德云存储反馈信息。
	 */
	public String query(String filter) {
		return connection.HttpGet(HttpUtil.LOCAL_QUERY + "tableid=" + tableId
				+ "&city=全国&keywords=&filter=" + filter + "&key="
				+ HttpUtil.WEBAPIKEY);
	}
	/**
	 *<b>设置TableId</b><br>
	 *设置对应操作表的id。<br>
	 * @see amap.cloud.manager.CloudConnection#setTableId(java.lang.String)
	 * @param tableID
	 *          接收FenceHelper，HistoryHelper，UserHelper中定义的tableid。
	 * @return 
	 */
	public void setTableId(String tableID) {
		this.tableId = tableID;
	}
	/**
	 *<b>转换为List<NameValuePair></b><br>
	 *post数据类型转换。JSONObject类型转换为List<NameValuePair>
	 * @see amap.cloud.manager.CloudConnection#toNameValuePairData(org.json.JSONObject)
	 * @param data
	 *          post数据，接收子类对象中parseData方法的返回值。
	 * @return 
	 */
	public List<NameValuePair> toNameValuePairData(JSONObject data) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("key", HttpUtil.WEBAPIKEY));
		formparams.add(new BasicNameValuePair("tableid", tableId));
		formparams.add(new BasicNameValuePair("loctype", "2"));
		formparams.add(new BasicNameValuePair("data", data.toString()));
		return formparams;
	}
	/**
	 *<b>转换为JSONObject</b><br>
	 *post数据类型转换。Object类型转换为JSONObject。此方法为抽象方法。在子类实现。
	 * @see amap.cloud.manager.CloudConnection#parseData(org.json.JSONObject)
	 * @param data
	 *          post数据。
	 * @return 
	 */
	public abstract JSONObject parseData(Object data);
}
