package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

/**
 * Gravity entity
 * 
 * @author Pontus Vainionpää
 */
@Entity
public class Gravity extends Model {
	@Id
	public int id;

	@NotNull
	public Float x;

	@NotNull
	public Float y;

	@NotNull
	public Float z;

	public static Finder<Long, Gravity> find = new Finder<Long, Gravity>(
			Long.class, Gravity.class);

	/**
	 * Find gravity object by id
	 * 
	 * @param id
	 *            Id of gravity object
	 * @return Gravity object || null
	 */
	public static Gravity findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
