import java.util.Date;
import org.junit.*;
import com.avaje.ebean.Page;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import models.*;

/**
 * Test cases for models.
 * 
 * @author Sami Hostikka
 */
public class ModelTest {
	static int id = 1;
	public static void addSensor(){
		Sensor sensor = new Sensor();
		sensor.measured = new Date();
		sensor.id = id;
		sensor.device = new Device();
		sensor.device.id = id;
		sensor.save();
	}

	@Test
	public void findById() {
		running(fakeApplication(inMemoryDatabase()), new Runnable() {
			public void run() {
				assertThat(Sensor.findById(id)).isEqualTo(null);
				assertThat(Accelerometer.findById(id)).isEqualTo(null);
				assertThat(Device.findById(id)).isEqualTo(null);
				assertThat(Gravity.findById(id)).isEqualTo(null);
				assertThat(Gyroscope.findById(id)).isEqualTo(null);
				assertThat(Light.findById(id)).isEqualTo(null);
				assertThat(Location.findById(id)).isEqualTo(null);
				assertThat(MagneticField.findById(id)).isEqualTo(null);
				assertThat(Network.findById(id)).isEqualTo(null);
				assertThat(Proximity.findById(id)).isEqualTo(null);
				assertThat(Wifi.findById(id)).isEqualTo(null);

				Sensor sensor = new Sensor();
				sensor.measured = new Date();
				sensor.id = id;
				sensor.accelerometer = new Accelerometer();
				sensor.accelerometer.id = id;
				sensor.accelerometer.x = 0f;
				sensor.accelerometer.y = 0f;
				sensor.accelerometer.z = 0f;
				sensor.device = new Device();
				sensor.device.id = id;
				sensor.gravity = new Gravity();
				sensor.gravity.id = id;
				sensor.gravity.x = 0f;
				sensor.gravity.y = 0f;
				sensor.gravity.z = 0f;
				sensor.gyroscope = new Gyroscope();
				sensor.gyroscope.id = id;
				sensor.gyroscope.x = 0f;
				sensor.gyroscope.y = 0f;
				sensor.gyroscope.z = 0f;
				sensor.light = new Light();
				sensor.light.id = id;
				sensor.light.x = 0f;
				sensor.location = new Location();
				sensor.location.id = id;
				sensor.location.latitude = 0.0;
				sensor.location.longitude = 0.0;
				sensor.location.provider = "";
				sensor.magfield = new MagneticField();
				sensor.magfield.id = id;
				sensor.magfield.x = 0f;
				sensor.magfield.y = 0f;
				sensor.magfield.z = 0f;
				sensor.network = new Network();
				sensor.network.id = id;
				sensor.network.operator = "";
				sensor.network.technology = "";
				sensor.network.isNetworkRoaming = false;
				sensor.proximity = new Proximity();
				sensor.proximity.id = id;
				sensor.proximity.x = 0f;
				Wifi wifi = new Wifi();
				wifi.id = id;
				wifi.BSSID = "";
				wifi.SSID = "";
				sensor.wifi.add(wifi);
				sensor.save();

				assertThat(Sensor.findById(id)).isNotEqualTo(null);
				assertThat(Accelerometer.findById(id)).isNotEqualTo(null);
				assertThat(Device.findById(id)).isNotEqualTo(null);
				assertThat(Gravity.findById(id)).isNotEqualTo(null);
				assertThat(Gyroscope.findById(id)).isNotEqualTo(null);
				assertThat(Light.findById(id)).isNotEqualTo(null);
				assertThat(Location.findById(id)).isNotEqualTo(null);
				assertThat(MagneticField.findById(id)).isNotEqualTo(null);
				assertThat(Network.findById(id)).isNotEqualTo(null);
				assertThat(Proximity.findById(id)).isNotEqualTo(null);
				assertThat(Wifi.findById(id)).isNotEqualTo(null);
			}
		});
	}

	@Test
	public void pagination() {
		running(fakeApplication(inMemoryDatabase()), new Runnable() {
			public void run() {
				Page<Sensor> page = Sensor.page(0, "desc", "measured");
				assertThat(page.getTotalRowCount()).isEqualTo(0);
				assertThat(page.getTotalPageCount()).isEqualTo(0);

				addSensor();

				page = Sensor.page(0, "desc", "measured");
				assertThat(page.getTotalRowCount()).isEqualTo(1);
				assertThat(page.getTotalPageCount()).isEqualTo(1);
			}
		});
	}
}
