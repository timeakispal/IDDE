package edu.ubb.cs.idde.rcp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class HorizontalPerspective implements IPerspectiveFactory {
  
  @Override
  public void createInitialLayout(IPageLayout layout) {
    // TODO Auto-generated method stub
    layout.setEditorAreaVisible(false);
    
  }
  
}
