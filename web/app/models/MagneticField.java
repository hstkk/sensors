package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

/**
 * MagneticField entity
 * 
 * @author Pontus Vainionpaa
 */
@Entity
public class MagneticField extends Model {

	@Id
	public int id;

	@NotNull
	public Float x;

	@NotNull
	public Float y;

	@NotNull
	public Float z;

	public static Finder<Long, MagneticField> find = new Finder<Long, MagneticField>(
			Long.class, MagneticField.class);

	/**
	 * Find magneticField object by id
	 * 
	 * @param id
	 *            Id of magneticField object
	 * @return MagneticField object || null
	 */
	public static MagneticField findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
