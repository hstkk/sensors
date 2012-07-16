package models;

import javax.persistence.*;
import play.db.ebean.Model;

/**
 * @author Pontus Vainionpaa
 */
public class Gyroscope extends Model {
	@Id
	public int id;

	public Float x;
	public Float y;
	public Float z;

	public static Finder<Long, Gyroscope> find = new Finder<Long, Gyroscope>(
			Long.class, Gyroscope.class);

	public static Gyroscope findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
