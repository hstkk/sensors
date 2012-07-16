package models;

import javax.persistence.*;
import play.db.ebean.Model;

/**
 * @author Pontus Vainionpaa
 */
public class MagneticField extends Model {

	@Id
	public int id;

	public Float x;
	public Float y;
	public Float z;

	public static Finder<Long, MagneticField> find = new Finder<Long, MagneticField>(
			Long.class, MagneticField.class);

	public static MagneticField findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
