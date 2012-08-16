package fi.hamk.demo.sensors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import fi.hamk.demo.sensors.models.Sensor;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

/**
 * Async connection to server.
 * 
 * @author Sami Hostikka
 */
public class Connection implements Runnable {

	SharedPreferences preferences;
	Context context;
	String json;
	Handler handler;
	int id = Conf.STATUS_ERROR;

	public Connection(Context context, Sensor sensor, Handler handler) {
		this.context = context;
		this.preferences = context.getSharedPreferences(
				context.getString(R.string.preferences), 0);
		this.json = sensor.toJson();
		this.handler = handler;
		ConnectionManager.getConnectionManager().add(this);
	}

	/**
	 * Tries to send data to server
	 * 
	 * @param url
	 */
	private void post(String url) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-type", "application/json");
			HttpConnectionParams.setSoTimeout(httpClient.getParams(),
					Conf.CONNECTION_TIMEOUT);

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

	/**
	 * Thread tries to execute post method until it succeeds.
	 */
	public void run() {
		String url = Utils.urlify(context, preferences);

		post(url + Conf.SERVER_PATH);
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
