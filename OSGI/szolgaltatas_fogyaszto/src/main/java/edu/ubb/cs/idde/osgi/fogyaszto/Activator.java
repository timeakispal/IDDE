package edu.ubb.cs.idde.osgi.fogyaszto;

import java.util.Collection;

import javax.swing.JFrame;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import edu.ubb.cs.idde.osgi.interfesz.GenericDao;
import edu.ubb.cs.idde.osgi.interfesz.States;

/**
 * A jelenlegi bundle itt lesz inditva/zarva. Ezt az osztalyt az osgi kontener a
 * POM Bundle-Activator mezeje alapjan talalja meg.
 */
public class Activator implements BundleActivator {

  @SuppressWarnings({ "unchecked", "rawtypes" })
public void start(BundleContext context) throws Exception {
    System.out.println("Starting the consumer bundle");

    // igy kerhetjuk le a szolgaltatast interfesz alapjan
//     ServiceReference<GenericDao> reference =
//     context.getServiceReference(GenericDao.class);

    // de nalunk 2 szolgaltatas van ugyanazon interfeszhez, szoval szuro
    // segitsegevel lekerjuk az egyiket
    Collection<ServiceReference<GenericDao>> references = context
        .getServiceReferences(GenericDao.class, "(service.name=Elso)");

    ServiceReference<GenericDao> reference = references.iterator().next();
      GenericDao<States> szolgaltatas = context.getService(reference);
      Kliens<States> s = new Kliens<States>(States.class,szolgaltatas);
    s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    s.pack();
    s.setVisible(true);

  }

  public void stop(BundleContext context) throws Exception {
    System.out.println("Stopping the bundle");
  }

}