package edu.ubb.cs.idde.server.pojo;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.Id;

@Entity(name="states")
public class States {
	 @Id
	 @Column(name="id")
	protected int id;
	 @Column(name="state")
	protected String state;
	 @Column(name="population")
	protected int population;
	public States() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	@Override
	public String toString() {
		return "States [id=" + id + ", state=" + state + ", population=" + population + "]";
	}
}