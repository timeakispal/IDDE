package edu.ubb.cs.idde.rcp.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import edu.ubb.cs.idde.rcp.views.StatesListerView;

/**
 * A programbol kilepo gomb kezeloje. A plugin.xml-ben talalhato ennek az
 * osztalynak a gombbal valo osszekotese
 */
public class LanguageCommand implements IHandler {
  
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    IWorkbenchPage p = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    
    // Ennek segítségével rákereshetünk egy adott iD-val rendelkező view-re,
    // majd konvertálhatjuk egy konkrét típusra
    StatesListerView v = (StatesListerView) p.findView("rcpexample.filelisterview");
    v.setLang("ID: ", "    Nev: ", "    Populacio: ");
    v.createViewer();
    return null;
  }
  
  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {
  }
  
  @Override
  public void removeHandlerListener(IHandlerListener handlerListener) {
  }
  
  @Override
  public void dispose() {
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
  
  @Override
  public boolean isHandled() {
    return true;
  }
  
}
