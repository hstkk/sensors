package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

/**
 * @author Sami Hostikka
 */
public class Application extends Controller {

	public static Result index() {
		return page(1);
	}

	public static Result page(int n){
		return TODO;
	}
	
	public static Result search(String query){
		return TODO;
	}
	
	public static Result result(int id){
		return TODO;
	}
	
	public static Result resultAsJson(int id){
		return TODO;
	}
	
	public static Result map(){
		return TODO;
	}
	
}