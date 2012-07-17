package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

/**
 * @author Pontus Vainionpaa
 */
@Entity
public class Gyroscope extends Model {
	@Id
	public int id;

	@NotNull
	public Float x;

	@NotNull
	public Float y;

	@NotNull
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
