package gameforum.encje;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="rola")

public class Rola implements Serializable {
	
	@Id
	@GeneratedValue
	private int id;
	private String login;
	private String rola;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getRola() {
		return rola;
	}
	public void setRola(String rola) {
		this.rola = rola;
	}
	
}
