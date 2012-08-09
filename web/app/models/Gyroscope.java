package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

/**
 * Gyroscope entity
 * 
 * @author Sami Hostikka
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

	/**
	 * Find gyroscope object by id
	 * 
	 * @param id
	 *            Id of gyroscope object
	 * @return gyroscope object || null
	 */
	public static Gyroscope findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
