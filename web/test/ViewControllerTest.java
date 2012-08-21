import java.util.Date;
import org.junit.*;
import play.mvc.Result;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import models.*;

/**
 * Test cases for views and controllers.
 * 
 * @author Sami Hostikka
 */
public class ViewControllerTest {
	@Test
	public void index() {
		running(fakeApplication(inMemoryDatabase()), new Runnable() {
			public void run() {
				Result result = callAction(controllers.routes.ref.Application
						.index("desc", "measured"));
				assertThat(status(result)).isEqualTo(OK);
				assertThat(contentType(result)).isEqualTo("text/html");
				assertThat(contentAsString(result)).contains("alert");
				ModelTest.addSensor();
				result = callAction(controllers.routes.ref.Application.index(
						"desc", "measured"));
				assertThat(contentAsString(result)).doesNotContain("alert");
			}
		});
	}

	@Test
	public void result() {
		running(fakeApplication(inMemoryDatabase()), new Runnable() {
			public void run() {
				Result result = callAction(controllers.routes.ref.Application
						.result(ModelTest.id));
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
				assertThat(contentType(result)).isEqualTo("text/html");
				assertThat(contentAsString(result)).contains("alert");
				ModelTest.addSensor();
				result = callAction(controllers.routes.ref.Application
						.result(ModelTest.id));
				assertThat(status(result)).isEqualTo(OK);
				assertThat(contentAsString(result)).doesNotContain("alert");
			}
		});
	}

	@Test
	public void resultAsTr() {
		running(fakeApplication(inMemoryDatabase()), new Runnable() {
			public void run() {
				Result result = callAction(controllers.routes.ref.Application
						.resultAsTr(ModelTest.id));
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
				assertThat(contentType(result)).isEqualTo("text/plain");
				assertThat(contentAsString(result)).contains("<div></div>");
				ModelTest.addSensor();
				result = callAction(controllers.routes.ref.Application
						.resultAsTr(ModelTest.id));
				assertThat(status(result)).isEqualTo(OK);
				assertThat(contentAsString(result)).doesNotContain(
						"<div></div>");
			}
		});
	}

	@Test
	public void pushpins() {
		running(fakeApplication(inMemoryDatabase()), new Runnable() {
			public void run() {
				Result result = callAction(controllers.routes.ref.Application
						.pushpins());
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
				Sensor sensor = new Sensor();
				sensor.measured = new Date();
				sensor.id = ModelTest.id;
				sensor.device = new Device();
				sensor.device.id = ModelTest.id;
				sensor.location = new Location();
				sensor.location.id = ModelTest.id;
				sensor.location.latitude = 0.0;
				sensor.location.longitude = 0.0;
				sensor.location.provider = "";
				sensor.save();
				result = callAction(controllers.routes.ref.Application
						.pushpins());
				assertThat(contentType(result)).isEqualTo("application/json");
				assertThat(status(result)).isEqualTo(OK);
			}
		});
	}
}
