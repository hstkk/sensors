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
		return TODO;
	}

	public static Result result(int id) {
		Sensor sensor = Sensor.findById(id);
		if (sensor == null)
			return badRequest(views.html.notFound.render());
		return TODO;
	}

	public static Result map() {
		return TODO;
		//return ok(Json.toJson(list));
	}

}