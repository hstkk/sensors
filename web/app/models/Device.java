package models;

import javax.persistence.*;
import play.db.ebean.Model;

/**
 * Device entity
 * 
 * @author Sami Hostikka
 */
@Entity
public class Device extends Model {

	@Id
	public int id;

	public String manufacturer;

	public String version;

	public String brand;

	public String model;

	public Device() {
	}

	public static Finder<Long, Device> find = new Finder<Long, Device>(
			Long.class, Device.class);

	/**
	 * Find device object by id
	 * 
	 * @param id
	 *            Id of device object
	 * @return Device object || null
	 */
	public static Device findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
