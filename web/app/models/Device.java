package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import play.db.ebean.Model;

/**
 * @author Sami Hostikka
 */
@Entity
public class Device extends Model {

	public String deviceId;

	public String manufacturer;

	public String version;

	public String brand;

	public String model;

	public Device() {
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
