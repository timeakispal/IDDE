package edu.ubb.cs.idde.rcp.model;

import java.util.ArrayList;

import edu.ubb.cs.model.States;
import edu.ubb.cs.repository.service.Service;

public enum StatesModelProvider {
  INSTANCE;
  private ArrayList<States> statesToDisplay;
  
  private StatesModelProvider() {
	  
	  statesToDisplay = new ArrayList<States>();
	  
	  Service service = new Service();
	  for (States s : service.getAllStates()) {
		  statesToDisplay.add(s);
	  }
  }
  
  public ArrayList<States> getFiles() {
    return statesToDisplay;
  }
  
}
