package fi.hamk.demo.sensors;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import android.os.Handler;
import android.os.Message;

import fi.hamk.demo.sensors.models.Sensor;

/**
 * @author Sami Hostikka
 */
public class Connection implements Runnable {

	public static final int OK = 200;
	public static final int INTERNAL_SERVER_ERROR = 500;
	public static final int ERROR = -1;
	public static final int CONNECTION_ERROR = 2;
	final int TIMEOUT = 10000; // milliseconds
	String url;
	String data;
	Handler handler;

	public Connection(String url, String data, Handler handler) {
		this.url = url;
		this.data = data;
		this.handler = handler;
		if (data.length() > 0)
			ConnectionManager.getConnectionManager().add(this);
	}

	public void run() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		HttpConnectionParams.setSoTimeout(httpClient.getParams(), TIMEOUT);
		try {
			httpPost.setEntity(new StringEntity(data));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			handler.sendMessage(Message.obtain(handler, httpResponse
					.getStatusLine().getStatusCode()));
		} catch (Exception e) {
			handler.sendMessage(Message.obtain(handler, ERROR));
		}
		//if (httpResponse.getStatusLine().getStatusCode() == 200)
		ConnectionManager.getConnectionManager().done(this);
	}
}
