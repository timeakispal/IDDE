package edu.ubb.cs.idde.osgi.impl;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;

import edu.ubb.cs.idde.osgi.interfesz.GenericDao;
import edu.ubb.cs.idde.osgi.interfesz.States;

/**
 * A jelenlegi bundle itt lesz inditva/zarva. Ezt az osztalyt az osgi kontener a
 * POM Bundle-Activator mezeje alapjan talalja meg.
 */
public class Activator implements BundleActivator {

  @SuppressWarnings("rawtypes")
public void start(BundleContext context) {
    System.out.println("Starting the implementation bundle");

    // peldanyositjuk a szolgaltatast
    GenericDao szolgaltatas = new JdbcDao<>(States.class);

    // megadunk par beallitast
    Hashtable<String, String> dictionary = new Hashtable<String, String>();
    dictionary.put(Constants.SERVICE_DESCRIPTION, "oran keszitett osgi szolgaltatas");
    dictionary.put("service.name", "Elso"); // eszerint kulonboztetjuk meg a ket
                                            // implementaciot

    // regisztraljuk a szolgaltatast
    // ezutan kulso bundle-ek elerhetik
    ServiceRegistration<GenericDao> registration = context.registerService(GenericDao.class,
        szolgaltatas, dictionary);

    if (registration != null) {
      System.out.println("Szolgaltatas sikeresen regisztralva");
    }
  }

  public void stop(BundleContext context) {
    System.out.println("Stopping the bundle");
  }

}