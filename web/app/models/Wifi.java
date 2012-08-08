package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * @author Sami Hostikka
 */
@Entity
public class Wifi extends Model {

	@Id
	public int id;

	@Required
	@NotNull
	public String BSSID;

	@Required
	@NotNull
	public String SSID;

	public String capabilities;

	public int frequency;

	public int level;

	public Wifi() {
	}

	public static Finder<Long, Wifi> find = new Finder<Long, Wifi>(Long.class,
			Wifi.class);

	public static Wifi findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
