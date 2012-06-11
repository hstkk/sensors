package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import play.db.ebean.Model;

/**
 * @author Sami Hostikka
 */
@Entity
public class Device extends Model {

	public String id;

	@NotNull
	public String name;

	@NotNull

	public Device() {
	}

	public Device(String id, String name) {
		this.id = id;
		this.name = name;
		this.save();
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
