package fi.hamk.demo.sensors;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import fi.hamk.demo.sensors.models.Sensor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

/**
 * @author Sami Hostikka
 */
public class Connection implements Runnable {

	SharedPreferences preferences;
	String preferences_url;
	String preferences_port;
	String json;
	Handler handler;

	public Connection(Context context, Sensor sensor, Handler handler) {
		preferences = context.getSharedPreferences(
				context.getString(R.string.preferences), 0);
		preferences_url = context.getString(R.string.preferences_url);
		preferences_port = context.getString(R.string.preferences_port);
		this.json = sensor.toJson();
		this.handler = handler;
		ConnectionManager.getConnectionManager().add(this);
	}

	private int post(String url) {
		int status = Conf.STATUS_ERROR;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		HttpConnectionParams.setSoTimeout(httpClient.getParams(),
				Conf.CONNECTION_TIMEOUT);
		try {
			httpPost.setEntity(new StringEntity(json));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			status = httpResponse.getStatusLine().getStatusCode();
		} catch (Exception e) {
			// unused
		}
		return status;
	}

	public void run() {
		StringBuilder url = new StringBuilder();
		url.append(preferences.getString(preferences_url, Conf.DEFAULT_SERVER));
		url.append(":");
		url.append(preferences.getLong(preferences_port, Conf.SERVER_PORT));
		url.append("/");
		url.append(Conf.SERVER_PATH);

		int status = post(url.toString());
		handler.sendMessage(Message.obtain(handler, status));
		if (status == Conf.STATUS_OK)
			ConnectionManager.getConnectionManager().done(this);
		else
			try {
				Thread.sleep(Conf.CONNECTION_SLEEP);
			} catch (InterruptedException e) {
				// unused
			}
	}
}
