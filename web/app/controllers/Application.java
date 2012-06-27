package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import models.*;
import play.libs.*;
import java.util.*;

/**
 * @author Sami Hostikka
 */
public class Application extends Controller {

	public static Result index(String order, String by) {
		return page(0, order, by);
	}

	public static Result page(int page, String order, String by) {
		return ok(views.html.results.render(Sensor.page(page, order, by),
				order, by));
	}

	public static Result result(int id) {
		Sensor sensor = Sensor.findById(id);
		if (sensor == null)
			return badRequest(views.html.notFound.render());
		return ok(views.html.result.render(sensor));
	}

	public static Result map() {
		return ok(views.html.map.render());
	}

	public static Result pushpins() {
		return ok(views.html.json.render(Sensor.pushpins()));
	}
}