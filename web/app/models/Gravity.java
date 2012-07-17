package models;

import javax.persistence.*;

import play.db.ebean.Model;

/**
 * @author Pontus Vainionpaa
 */
@Entity
public class Gravity extends Model {
	@Id
	public int id;

	public Float x;
	public Float y;
	public Float z;

	public static Finder<Long, Gravity> find = new Finder<Long, Gravity>(
			Long.class, Gravity.class);

	public static Gravity findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
