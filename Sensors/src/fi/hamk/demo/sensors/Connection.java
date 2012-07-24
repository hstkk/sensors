package fi.hamk.demo.sensors;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

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
	int id = Conf.STATUS_ERROR;

	public Connection(Context context, Sensor sensor, Handler handler) {
		preferences = context.getSharedPreferences(
				context.getString(R.string.preferences), 0);
		preferences_url = context.getString(R.string.preferences_url);
		preferences_port = context.getString(R.string.preferences_port);
		this.json = sensor.toJson();
		this.handler = handler;
		ConnectionManager.getConnectionManager().add(this);
	}

	private void post(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-type", "application/json");
		HttpConnectionParams.setSoTimeout(httpClient.getParams(),
				Conf.CONNECTION_TIMEOUT);
		try {
			httpPost.setEntity(new StringEntity(json, "UTF-8"));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == Conf.STATUS_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(httpEntity.getContent()));
					String line;
					StringBuilder json = new StringBuilder();
					while ((line = bufferedReader.readLine()) != null)
						json.append(line);
					ObjectMapper objectMapper = new ObjectMapper();
					JsonNode jsonNode = objectMapper.readTree(json.toString());
					id = jsonNode.findPath("id").asInt(Conf.STATUS_ERROR);
				}
			}
		} catch (Exception e) {
			// unused
		}
	}

	public void run() {
		StringBuilder url = new StringBuilder();
		url.append(preferences.getString(preferences_url, Conf.DEFAULT_SERVER));
		url.append(":");
		url.append(preferences.getInt(preferences_port,
				Conf.DEFAULT_SERVER_PORT));
		url.append("/");
		url.append(Conf.SERVER_PATH);

		post(url.toString());
		handler.sendMessage(Message.obtain(handler, id));
		if (id > 0)
			ConnectionManager.getConnectionManager().done(this);
		else
			try {
				Thread.sleep(Conf.CONNECTION_SLEEP);
			} catch (InterruptedException e) {
				// unused
			}
	}
}
