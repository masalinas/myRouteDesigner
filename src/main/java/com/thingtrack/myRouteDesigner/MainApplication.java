package com.thingtrack.myRouteDesigner;

import com.vaadin.Application;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
public class MainApplication extends Application
{
	private static final long serialVersionUID = -8873245242163572864L;
    
	private VerticalLayout mainLayout;

    @Override
    public void init()
    {
    	setTheme("myRouteDesigner");
    	
    	Window mainWindow = new Window("MyRouteDesigner Sample Application");
    	
    	mainLayout = (VerticalLayout)mainWindow.getContent();
	    mainLayout.setSizeFull();
	    mainLayout.setMargin(true);
	    mainLayout.addComponent(new MainLayout());
	    
        setMainWindow(mainWindow);
       
    }

}
