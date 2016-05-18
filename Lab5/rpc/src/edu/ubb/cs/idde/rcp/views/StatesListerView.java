package edu.ubb.cs.idde.rcp.views;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import edu.ubb.cs.idde.rcp.model.StatesModelProvider;
import edu.ubb.cs.model.States;

/**
 * Ez a view lekéri az összes mappát a felhasználó home könyvtárából,
 * s kilistázza ezeket.
 */
public class StatesListerView extends ViewPart {
  private Composite parent;
  private ListViewer viewer;
  
  String text1, text2, text3;
  
  public StatesListerView() {
	  text1 = "ID: ";
	  text2 = "    Name: ";
	  text3 = "    Population: ";
  }
  
  @Override
  public void createPartControl(Composite parent) {
    // createViewer(parent);
    RowLayout rowLayout = new RowLayout();
    rowLayout.wrap = false;
    rowLayout.pack = true;
    rowLayout.justify = true;
    rowLayout.type = SWT.VERTICAL;
    rowLayout.marginLeft = 5;
    rowLayout.marginTop = 5;
    rowLayout.marginRight = 5;
    rowLayout.marginBottom = 5;
    rowLayout.spacing = 0;
    parent.setLayout(rowLayout);
    this.parent = parent;
    
  }
  
  @Override
  public void setFocus() {
    // TODO Auto-generated method stub
    
  }
  
  public void createViewer() {
    
    MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Info",
        "Betoltom egy ListViewerbe az aktualis adatbazis osszes soranak nevet es populaciojat");
    
    if (viewer != null) {
    	viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new LabelProvider() {
          public String getText(Object element) {
        	  States f = (States) element;
            return text1 + f.getId() + text2 + f.getState() + text3 + f.getPopulation();
          };
          
        });
        viewer.setInput(StatesModelProvider.INSTANCE.getFiles());
      return;
    }
    
    Label l = new Label(parent, SWT.SHADOW_IN);
    l.setText("Current directory: " + System.getProperty("user.dir"));
    l.setAlignment(SWT.CENTER);
    
    viewer = new ListViewer(parent);
    viewer.setContentProvider(new ArrayContentProvider());
    viewer.setLabelProvider(new LabelProvider() {
      public String getText(Object element) {
    	  States f = (States) element;
    	  return text1 + f.getId() + text2 + f.getState() + text3 + f.getPopulation();
      };
      
    });
    viewer.setInput(StatesModelProvider.INSTANCE.getFiles());
    parent.layout();
    
  }

public void setLang(String string1, String string2, String string3) {
	text1 = string1;
	text2 = string2;
	text3 = string3;
}
}
