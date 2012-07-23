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
		Location location = Json.fromJson(json.get("location"), Location.class);
		if (validate(location))
			this.location = location;
		Network network = Json.fromJson(json.get("network"), Network.class);
		if (validate(network))
			this.network = network;
		Device device = Json.fromJson(json.get("device"), Device.class);
		if (validate(device))
			this.device = device;
		Accelerometer accelerometer = Json.fromJson(json.get("accelerometer"),
				Accelerometer.class);
		if (validate(accelerometer))
			this.accelerometer = accelerometer;
		Proximity proximity = Json.fromJson(json.get("proximity"),
				Proximity.class);
		if (validate(proximity))
			this.proximity = proximity;
		Gravity gravity = Json.fromJson(json.get("gravity"), Gravity.class);
		if (validate(gravity))
			this.gravity = gravity;
		Gyroscope gyroscope = Json.fromJson(json.get("gyroscope"),
				Gyroscope.class);
		if (validate(gyroscope))
			this.gyroscope = gyroscope;
		Light light = Json.fromJson(json.get("light"), Light.class);
		if (validate(light))
			this.light = light;
		MagneticField magfield = Json.fromJson(json.get("magfield"),
				MagneticField.class);
		if (validate(magfield))
			this.magfield = magfield;
		this.wifi = Json.fromJson(json.get("wifi"), WifiList.class);
		measured = new Date(json.findPath("measured").getLongValue());
		if (this.location != null || this.network != null
				|| this.device != null || this.accelerometer != null
				|| this.proximity != null || this.gravity != null
				|| this.gyroscope != null || this.light != null
				|| this.magfield != null || !this.wifi.isEmpty())
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
			return find.fetch("location").orderBy(by + " " + order)
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
