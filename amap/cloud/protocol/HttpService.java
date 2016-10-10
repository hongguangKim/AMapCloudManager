package amap.cloud.protocol;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
/**
 *<b>Http{do，post}操作类</b><br>
 *此类为访问高德云存储的操作类。主要实现Http协议的Get，Post方法。
 * @ File Name : HttpService.java
 * @ Author : haifeng.gong hongguang.jin
 */
public class HttpService {
	/**
	 *<b>post操作方法</b><br>
	 *此方法实现Http协议中的Post方法。来实现数据上传，更新操作。
	 * @see amap.cloud.protocol.HttpService#HttpPost(java.lang.String，java.util.List)
	 * @param url
	 *          上传，更新数据时Http格式的url。
	 * @param params
	 *          上传或更新的数据。
	 * @return String
	 * 			高德云反馈信息。
	 */
	public String HttpPost(String url, List<NameValuePair> params) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			httppost.addHeader("Content-Type","application/x-www-form-urlencoded");
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				return EntityUtils.toString(response.getEntity());
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *<b>get操作方法</b><br>
	 *此方法实现Http协议中的Get方法。来实现数据查询操作。。
	 * @see amap.cloud.protocol.HttpService#HttpGet(java.lang.String)
	 * @param url
	 *          查询数据时Http格式的url。
	 * @return String
	 * 			查询到的数据。
	 */
	public String HttpGet(String url) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			request.addHeader("Accept", "text/json");
			HttpResponse response = httpclient.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
