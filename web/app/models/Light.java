package models;

import javax.persistence.*;
import play.db.ebean.Model;

/**
 * @author Pontus Vainionpaa
 */
public class Light extends Model {
	@Id
	public int id;

	public Float x;
	
	public static Finder<Long, Light> find = new Finder<Long, Light>(
			Long.class, Light.class);

	public static Light findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
