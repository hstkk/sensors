package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

/**
 * Location entity
 * 
 * @author Sami Hostikka
 */
@Entity
public class Location extends Model {

	@Id
	public int id;

	public Double altitude;

	@NotNull
	public Double latitude;

	@NotNull
	public Double longitude;

	public Float accuracy;

	public Float speed;

	@NotNull
	public String provider;

	public Integer satellites;

	public Location() {
	}

	public static Finder<Long, Location> find = new Finder<Long, Location>(
			Long.class, Location.class);

	/**
	 * Find location object by id
	 * 
	 * @param id
	 *            Id of location object
	 * @return Location object || null
	 */
	public static Location findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
