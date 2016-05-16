package edu.ubb.cs.idde.osgi.interfesz;

public class States {
	protected int id;
	protected String state;
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