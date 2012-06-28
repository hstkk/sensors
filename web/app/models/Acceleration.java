package models;

import javax.persistence.*;
import play.db.ebean.Model;

/**
 * @author Sami Hostikka
 */
@Entity
public class Acceleration extends Model {

	@Id
	public int id;

	public Float x;

	public Float y;

	public Float z;

	public Acceleration() {
	}

	public static Finder<Long, Acceleration> find = new Finder<Long, Acceleration>(
			Long.class, Acceleration.class);

	public static Acceleration findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
