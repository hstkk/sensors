package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import play.db.ebean.Model;

/**
 * Network entity
 * 
 * @author Sami Hostikka
 */
@Entity
public class Network extends Model {

	@Id
	public int id;

	@NotNull
	public String operator;

	@NotNull
	public String technology;

	public boolean isNetworkRoaming;

	public Integer cell;

	public Network() {
	}

	public static Finder<Long, Network> find = new Finder<Long, Network>(
			Long.class, Network.class);

	/**
	 * Find network object by id
	 * 
	 * @param id
	 *            Id of network object
	 * @return Network object || null
	 */
	public static Network findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
}
