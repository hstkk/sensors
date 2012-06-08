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
	}
}
