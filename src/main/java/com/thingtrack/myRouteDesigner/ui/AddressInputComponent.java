package com.thingtrack.myRouteDesigner.ui;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.LocationProvider;
import org.vaadin.addons.locationtextfield.LocationTextField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeNotifier;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class AddressInputComponent extends CustomField implements ValueChangeNotifier {
	@AutoGenerated
	private HorizontalLayout mainLayout;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private LocationTextField<GeocodedLocation> locationTextField;
	private GeocodedLocation geocodedLocation;
	private List<ValueChangeListener> listeners; 

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public AddressInputComponent() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		listeners = new ArrayList<ValueChangeListener>();
	}

	public void setLocationProvider(LocationProvider<GeocodedLocation> locationProvider) {

		// locationTextField
		locationTextField = new LocationTextField<GeocodedLocation>(locationProvider, GeocodedLocation.class);
		locationTextField.setImmediate(false);
		locationTextField.setSizeFull();
		
		mainLayout.addComponent(locationTextField);
		
		locationTextField.addListener(new Property.ValueChangeListener() {			
			@Override
			public void valueChange(Property.ValueChangeEvent event) {				
				geocodedLocation = (GeocodedLocation) event.getProperty().getValue();
				
				// Notify listeners a new geological location
				for(ValueChangeListener listener : listeners) {					
					listener.valueChange(new Field.ValueChangeEvent(AddressInputComponent.this));
				}
			}
		});

	}

	@Override
	public Class<?> getType() {
		return GeocodedLocation.class;
	}
	
	@Override
	public Object getValue() {
		return geocodedLocation; 
	}
	
	@Override
	public void setValue(Object newValue) throws ReadOnlyException,
			ConversionException {

		if(newValue != null && !(newValue instanceof GeocodedLocation))
			throw new ConversionException("There is an " + GeocodedLocation.class.getSimpleName()); 
		
		geocodedLocation = (GeocodedLocation) newValue;
		// Load the geocoded address 
		locationTextField.setLocation(geocodedLocation);
	}

	@Override
	public void addListener(ValueChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(ValueChangeListener listener) {
		listeners.remove(listener);
	}

	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		return mainLayout;
	}

}
