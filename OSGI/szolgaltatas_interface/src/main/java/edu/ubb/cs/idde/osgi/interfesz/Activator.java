package edu.ubb.cs.idde.osgi.interfesz;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * A jelenlegi bundle itt lesz inditva/zarva. Ezt az osztalyt az osgi kontener a
 * POM Bundle-Activator mezeje alapjan talalja meg.
 */
public class Activator implements BundleActivator {

  public void start(BundleContext context) {
    System.out.println("Starting the interface bundle");
  }

  public void stop(BundleContext context) {
    System.out.println("Stopping the interface bundle");
  }

}