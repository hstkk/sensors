package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import play.db.ebean.Model;

/**
 * @author Sami Hostikka
 */
@Entity
public class Network extends Model {

	@Id
	public int id;

	@NotNull
	public String name;

	@NotNull
	public double quality;

	public String mac;

	public Network() {
	}

	public Network(String name, double quality, String mac) {
		this.name = name;
		this.quality = quality;
		this.mac = mac;
		this.save();
	}

	public static Finder<Long, Network> find = new Finder<Long, Network>(
			Long.class, Network.class);

	public static Network findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
