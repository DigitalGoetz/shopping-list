package digital.goetz.shopping.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue
	Long id = null;

	String name;
	Date created;
	Date updated;

	@Enumerated(EnumType.ORDINAL)
	State state;

	public Item() {
		this.name = "";
		Date now = new Date();
		this.created = now;
		this.updated = now;

		this.state = State.BUY;
	}

	public Item(String name) {
		this.name = name;
		Date now = new Date();
		this.created = now;
		this.updated = now;

		this.state = State.BUY;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Item [ ");
		if (this.getId() != null) {
			sb.append("id: " + getId() + ", ");
		}
		sb.append("name: " + getName() + ", ");
		sb.append("created: " + getCreated() + ", ");
		sb.append("updated: " + getUpdated());
		sb.append(" ]");
		return sb.toString();
	}

}
