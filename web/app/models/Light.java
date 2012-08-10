package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

/**
 * Light entity
 * 
 * @author Pontus Vainionpää
 */
@Entity
public class Light extends Model {
	@Id
	public int id;

	@NotNull
	public Float x;

	public static Finder<Long, Light> find = new Finder<Long, Light>(
			Long.class, Light.class);

	/**
	 * Find light object by id
	 * 
	 * @param id
	 *            Id of light object
	 * @return Light object || null
	 */
	public static Light findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
