package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.Valid;

import com.avaje.ebean.*;
import com.avaje.ebean.validation.NotNull;

import play.data.validation.Validation;
import play.data.validation.Constraints.*;
import play.db.ebean.Model;

/**
 * Sensor entity
 * 
 * @author Sami Hostikka
 */
@Entity
public class Sensor extends Model {

	@Id
	public int id;

	@Required
	@NotNull
	public Date measured;

	@OneToOne(cascade = CascadeType.ALL)
	public Location location;

	@OneToOne(cascade = CascadeType.ALL)
	public Network network;

	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	@Valid
	public Device device;

	@OneToOne(cascade = CascadeType.ALL)
	public Accelerometer accelerometer;

	@OneToOne(cascade = CascadeType.ALL)
	public Proximity proximity;

	@OneToOne(cascade = CascadeType.ALL)
	public Gravity gravity;

	@OneToOne(cascade = CascadeType.ALL)
	public Gyroscope gyroscope;

	@OneToOne(cascade = CascadeType.ALL)
	public Light light;

	@OneToOne(cascade = CascadeType.ALL)
	public MagneticField magfield;

	@OneToMany(cascade = CascadeType.ALL)
	public List<Wifi> wifi = new ArrayList<Wifi>();

	public Sensor() {
	}

	/**
	 * Validate objects before saving and finally save
	 */
	@Override
	public void save() {
		this.location = validate(this.location);
		this.network = validate(this.network);
		this.accelerometer = validate(this.accelerometer);
		this.proximity = validate(this.proximity);
		this.gravity = validate(this.gravity);
		this.gyroscope = validate(this.gyroscope);
		this.light = validate(this.light);
		this.magfield = validate(this.magfield);
		this.wifi = validate(this.wifi);
		super.save();
	}

	/**
	 * Generic method for validating objects
	 * 
	 * @param t
	 *            Object for validation
	 * @return object || null
	 */
	private <T> T validate(T t) {
		if (t != null && Validation.getValidator().validate(t).isEmpty())
			return t;
		return null;
	}

	public static Finder<Long, Sensor> find = new Finder<Long, Sensor>(
			Long.class, Sensor.class);

	/**
	 * Find sensor object by id
	 * 
	 * @param id
	 *            Id of sensor object
	 * @return Sensor object || null
	 */
	public static Sensor findById(int id) {
		try {
			return find.fetch("location").fetch("network").fetch("device")
					.fetch("accelerometer").fetch("proximity").fetch("gravity")
					.fetch("gyroscope").fetch("light").fetch("magfield")
					.fetch("wifi").where().eq("id", id).findUnique();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Find sensor page
	 * 
	 * @param page
	 *            Page to display
	 * @param order
	 *            Property order
	 * @param by
	 *            Property used to sort page
	 * @return Page of sensor
	 */
	public static Page<Sensor> page(int page, String order, String by) {
		try {
			int pageSize = 10;
			return find.fetch("device").orderBy(by + " " + order)
					.findPagingList(pageSize).getPage(page);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Find list of sensor objects
	 * 
	 * @return Sensor list || null
	 */
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
