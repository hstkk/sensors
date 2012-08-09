package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import models.*;
import play.db.ebean.Transactional;
import play.libs.*;

import java.util.*;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Handles user interaction
 * 
 * @author Sami Hostikka
 */
public class Application extends Controller {

	/**
	 * Display index page
	 * 
	 * @param order
	 * @param by
	 */
	public static Result index(String order, String by) {
		return page(0, order, by);
	}

	/**
	 * Display sensor page
	 * 
	 * @param page
	 * @param order
	 * @param by
	 */
	public static Result page(int page, String order, String by) {
		return ok(views.html.results.render(Sensor.page(page, order, by),
				order, by));
	}

	/**
	 * Display specific result
	 * 
	 * @param id
	 */
	public static Result result(int id) {
		Sensor sensor = Sensor.findById(id);
		if (sensor == null)
			return badRequest(views.html.notFound.render());
		return ok(views.html.result.render(sensor));
	}

	/**
	 * Display specific result as table row
	 * 
	 * @param id
	 */
	public static Result resultAsTr(int id) {
		Sensor sensor = Sensor.findById(id);
		if (sensor == null)
			return badRequest("<div></div>");
		return ok(views.html.tags.result.render(sensor));
	}

	/**
	 * Display map
	 */
	public static Result map() {
		return ok(views.html.map.render());
	}

	/**
	 * Display sensors as JSON
	 * 
	 * @return list of sensor objects as JSON || badRequest
	 */
	public static Result pushpins() {
		List<Sensor> results = Sensor.pushpins();
		if (results == null || results.isEmpty())
			return badRequest();
		return ok(Json.toJson(results));
	}

	/**
	 * Add JSON to database
	 * 
	 * @return ok || badRequest
	 */
	@Transactional
	public static Result add() {
		try {
			JsonNode json = request().body().asJson();
			if (json != null) {
				Sensor sensor = Json.fromJson(json, Sensor.class);
				sensor.save();
				ObjectNode result = Json.newObject();
				result.put("id", sensor.id);
				return ok(result);
			}
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return badRequest();
	}
}