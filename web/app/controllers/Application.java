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

	public static Result index() {
		return page(1);
	}

	public static Result page(int n) {
		return TODO;
	}

	public static Result search(String query) {
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