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
	public String operator;

	@NotNull
	public String type;

	public boolean isNetworkRoaming;

	public Integer cell = null;

	public Network() {
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
