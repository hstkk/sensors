package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import play.db.ebean.Model;

/**
 * @author Sami Hostikka
 */
@Entity
public class Location extends Model {

	public Double altitude;
	
	public Double latitude;
	
	public Double longitude;

	public Location() {
	}

	public static Finder<Long, Device> find = new Finder<Long, Device>(
			Long.class, Device.class);

	public static Device findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
