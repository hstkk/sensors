package fi.hamk.demo.sensors;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import android.os.Handler;
import android.os.Message;

/**
 * @author Sami Hostikka
 */
public class Connection implements Runnable {

	int status = Conf.STATUS_ERROR;
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
		HttpConnectionParams.setSoTimeout(httpClient.getParams(), Conf.CONNECTION_TIMEOUT);
		try {
			httpPost.setEntity(new StringEntity(data));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			status = httpResponse.getStatusLine().getStatusCode();
		} catch (Exception e) {
		}
		handler.sendMessage(Message.obtain(handler, status));
		if (status == Conf.STATUS_OK)
			ConnectionManager.getConnectionManager().done(this);
		else
			try {
				Thread.sleep(Conf.CONNECTION_SLEEP);
			} catch (InterruptedException e) {
			}
	}
}
