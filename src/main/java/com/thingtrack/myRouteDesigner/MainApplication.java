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
 
		StringBuffer buffer = new StringBuffer();
		buffer.append("jQuery.ajax({");
		buffer.append(" url: \"https://thingtrack.atlassian.net/s/d41d8cd98f00b204e9800998ecf8427e/en_US-7jch18-1988229788/6144/71/1.4.0-m6/_/download/batch/com.atlassian.jira.collector.plugin.jira-issue-collector-plugin:issuecollector/com.atlassian.jira.collector.plugin.jira-issue-collector-plugin:issuecollector.js?collectorId=dad45031\"");
		buffer.append(",type: \"get\"");
		buffer.append(",cache: true");
		buffer.append(",dataType: \"script\"});");
		getMainWindow().executeJavaScript(buffer.toString());
    }

}
