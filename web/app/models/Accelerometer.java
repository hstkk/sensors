package models;

import javax.persistence.*;
import play.db.ebean.Model;

/**
 * @author Sami Hostikka
 */
@Entity
public class Accelerometer extends Model {

	@Id
	public int id;

	public Float x;

	public Float y;

	public Float z;

	public Accelerometer() {
	}

	public static Finder<Long, Accelerometer> find = new Finder<Long, Accelerometer>(
			Long.class, Accelerometer.class);

	public static Accelerometer findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
