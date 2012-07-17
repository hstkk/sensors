package models;

import java.util.*;
import javax.persistence.*;

import org.codehaus.jackson.JsonNode;

import com.avaje.ebean.*;
import com.avaje.ebean.validation.NotNull;

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
		location = Json.fromJson(json.get("location"), Location.class);
		network = Json.fromJson(json.get("network"), Network.class);
		device = Json.fromJson(json.get("device"), Device.class);
		accelerometer = Json.fromJson(json.get("accelerometer"),
				Accelerometer.class);
		proximity = Json.fromJson(json.get("proximity"), Proximity.class);
		gravity = Json.fromJson(json.get("gravity"), Gravity.class);
		gyroscope = Json.fromJson(json.get("gyroscope"), Gyroscope.class);
		light = Json.fromJson(json.get("light"), Light.class);
		magfield = Json.fromJson(json.get("magfield"), MagneticField.class);
		measured = new Date(json.findPath("measured").getLongValue());
		this.save();
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
			return find.fetch("location").orderBy(by + " " + order)
					.findPagingList(pageSize).getPage(page);
		} catch (Exception e) {
			return null;
		}
	}

	public static List<Sensor> pushpins() {
		try {
			return find.select("id, created, location").fetch("location")
					.where().isNotNull("location.latitude")
					.isNotNull("location.longitude").findList();
		} catch (Exception e) {
			return null;
		}
	}
}
