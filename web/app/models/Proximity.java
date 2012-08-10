package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

/**
 * Proximity entity
 * 
 * @author Pontus Vainionpaa
 */
@Entity
public class Proximity extends Model {
	@Id
	public int id;

	@NotNull
	public Float x;

	public static Finder<Long, Proximity> find = new Finder<Long, Proximity>(
			Long.class, Proximity.class);

	/**
	 * Find proximity object by id
	 * 
	 * @param id
	 *            Id of proximity object
	 * @return Proximity object || null
	 */
	public static Proximity findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
