package models;

import javax.persistence.*;
import play.db.ebean.Model;

/**
 * @author Sami Hostikka
 */
@Entity
public class Location extends Model {

	@Id
	public int id;

	public Double altitude;

	public Double latitude;

	public Double longitude;

	public Location() {
	}

	public static Finder<Long, Location> find = new Finder<Long, Location>(
			Long.class, Location.class);

	public static Location findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}