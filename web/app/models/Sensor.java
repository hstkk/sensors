package models;

import java.util.*;
import javax.persistence.*;

import org.codehaus.jackson.JsonNode;

import com.avaje.ebean.*;
import com.avaje.ebean.validation.NotNull;

import play.data.validation.Validation;
import play.data.validation.Constraints.*;
import play.db.ebean.Model;
import play.libs.Json;

/**
 * @author Sami Hostikka
 */
@Entity
public class Sensor extends Model {

	@Id
	public int id;

	@Required
	@NotNull
	public Date measured;

	@OneToOne
	public Location location;

	@OneToOne
	public Network network;

	@OneToOne
	@NotNull
	public Device device;

	@OneToOne
	public Accelerometer accelerometer;

	@OneToOne
	public Proximity proximity;

	@OneToOne
	public Gravity gravity;

	@OneToOne
	public Gyroscope gyroscope;

	@OneToOne
	public Light light;

	@OneToOne
	public MagneticField magfield;

	@ManyToOne
	public List<Wifi> wifi = new ArrayList<Wifi>();

	public Sensor() {
	}

	public Sensor(JsonNode json) {
		this.measured = new Date(json.findPath("measured").getLongValue());
		this.device = Json.fromJson(json.get("device"), Device.class);
		this.device.save();
		this.save();
	}

	private <T> boolean validate(T t) {
		System.out.println(t.toString());
		return Validation.getValidator().validate(t).isEmpty();
	}

	public static Finder<Long, Sensor> find = new Finder<Long, Sensor>(
			Long.class, Sensor.class);

	public static Sensor findById(int id) {
		try {
			return find.fetch("location").where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}

	public static Page<Sensor> page(int page, String order, String by) {
		try {
			int pageSize = 10;
			return find.fetch("device").orderBy(by + " " + order)
					.findPagingList(pageSize).getPage(page);
		} catch (Exception e) {
			return null;
		}
	}

	public static List<Sensor> pushpins() {
		try {
			return find.select("id, measured, location").fetch("location")
					.where().isNotNull("location.latitude")
					.isNotNull("location.longitude").findList();
		} catch (Exception e) {
			return null;
		}
	}
}
