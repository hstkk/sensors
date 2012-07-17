package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

/**
 * @author Sami Hostikka
 */
@Entity
public class Accelerometer extends Model {

	@Id
	public int id;

	@NotNull
	public Float x;

	@NotNull
	public Float y;

	@NotNull
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
