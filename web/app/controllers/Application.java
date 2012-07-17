package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import models.*;
import play.data.DynamicForm;
import play.libs.*;
import java.util.*;

import org.joda.time.DateTime;

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

	public static Result resultAsTr(int id) {
		Sensor sensor = Sensor.findById(id);
		if (sensor == null)
			return badRequest("<tr></tr>");
		return ok(views.html.tags.result.render(sensor));
	}

	public static Result map() {
		/*Location l = new Location();
		l.accuracy = (float) 5;
		l.altitude = (double) 0;
		l.latitude = 60.7346;
		l.longitude = 24.7612;
		l.provider = "GPS";
		l.satellites = 3;
		l.speed = (float) 0;
		l.save();
		Sensor s = new Sensor();
		s.measured = new Date();
		s.location = l;
		s.save();*/
		
		return ok(views.html.map.render());
	}

	public static Result pushpins() {
		List<Sensor> results = Sensor.pushpins();
		if (results == null || results.isEmpty())
			return TODO;
		return ok(Json.toJson(results));
	}

	public static Result add(){
		try{
			DynamicForm dynamicForm = form().bindFromRequest();
			  for (Object key: dynamicForm.get().getData().entrySet()){
				  System.out.println("Key : " + key.toString() 
			       			+ " Value : " + dynamicForm.get((String) key));
			  }
			return ok();
		} catch(Exception e){
			System.out.print(e.toString());
			return badRequest();
		}
	}
}