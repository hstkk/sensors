package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.*;
import play.data.validation.Constraints.*;
import play.db.ebean.Model;

/**
 * @author Sami Hostikka
 */
@Entity
public class Sensor extends Model {

	@Id
	public int id;

	@Required
	public Date created;

	@OneToOne
	public Location location;

	@OneToOne
	public Network network;

	@OneToOne
	public Device device;

	@ManyToOne
	public List<Wifi> wifi;

	public Sensor() {
	}

	public static Finder<Long, Sensor> find = new Finder<Long, Sensor>(
			Long.class, Sensor.class);

	public static Sensor findById(int id) {
		try {
			return find.where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}

	public static Page<Sensor> page(int page, String order, String by) {
		try {
			int pageSize = 10;
			return find.orderBy(by + " " + order).findPagingList(pageSize)
					.getPage(page);
		} catch (Exception e) {
			return null;
		}
	}
}
