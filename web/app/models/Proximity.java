package models;

import javax.persistence.*;
import play.db.ebean.Model;

/**
 * @author Pontus Vainionpaa
 */
public class Proximity extends Model {
	@Id
	public int id;

	public Float x;

	public static Finder<Long, Proximity> find = new Finder<Long, Proximity>(
			Long.class, Proximity.class);

	public static Proximity findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
