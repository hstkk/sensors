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

	public String device;

	public String manufacturer;

	public String version;

	public String brand;

	public String model;

	public String product;

	public Device() {
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
