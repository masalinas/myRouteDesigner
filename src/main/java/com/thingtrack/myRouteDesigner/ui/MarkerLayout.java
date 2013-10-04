package com.thingtrack.myRouteDesigner.ui;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import org.vaadin.sasha.portallayout.PortalLayout;
import org.vaadin.sasha.portallayout.client.ui.PortalConst;
import org.vaadin.sasha.portallayout.event.Context;
import org.vaadin.vol.Vector;

import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class MarkerLayout extends PortalLayout {
	
	// marker listeners
	private MarkerAddListener listenerMarkerAdd = null;
	private MarkerMoveListener listenerMarkerMove = null;
	private MarkerRemoveListener listenerMarkerRemove = null;
		
	@Override
    public void changeVariables(Object source, Map<String, Object> variables) {
		super.changeVariables(source, variables);
		
		// listen to move marker event
        if (variables.containsKey(PortalConst.PORTLET_POSITION_UPDATED)) {
        	@SuppressWarnings("unchecked")
			final Map<String, Object> portletParameters = (Map<String, Object>) variables.get(PortalConst.PORTLET_POSITION_UPDATED);
            final Component markerPanelComponent = (Component) portletParameters.get(PortalConst.PAINTABLE_MAP_PARAM);
            final Integer markerPanelPosition = (Integer) portletParameters.get(PortalConst.PORTLET_POSITION);
            
            MarkerPanel markerPanel = (MarkerPanel) markerPanelComponent;            
            Vector vector = (Vector) markerPanel.getVector();
                                    
            if (markerPanelPosition != markerPanel.getPosition() - 1) {            	
            	if (listenerMarkerMove != null)
            		listenerMarkerMove.markerMove(new MarkerEvent(markerPanelComponent, markerPanelPosition, vector));
            	
            	reorderComponents();
            }
            
        }
	}
	
	@Override
    public void addComponent(Component component) {
		super.addComponent(component);
				
		MarkerPanel markerPanel = (MarkerPanel) component;
		Vector markerVector = (Vector) markerPanel.getVector();
		
		markerPanel.setPosition(size());
		
		if (listenerMarkerAdd != null)
			listenerMarkerAdd.markerAdd(new MarkerEvent(component, size(), markerVector));
	}
	
	@Override
    public void removeComponent(Component component) {
		fireEvent(new PortletClosedEvent(component, new Context(this, component)));
		super.removeComponent(component);
		
		MarkerPanel markerPanel = (MarkerPanel) component;
		Vector markerVector = (Vector) markerPanel.getVector();
        
		if (listenerMarkerRemove != null)
			listenerMarkerRemove.markerRemove(new MarkerEvent(component, -1, markerVector));		
			
		reorderComponents();
		
		requestRepaint();
	}
	
	public int size() {
		Integer size = 0;
		
		@SuppressWarnings("rawtypes")
		Iterator i= getComponentIterator();
		while(i.hasNext()) {
			i.next();
			size ++;
        }
		
		return size;
	}
	
	private void reorderComponents() {
		Integer size = 1;
		
		@SuppressWarnings("rawtypes")
		Iterator i= getComponentIterator();
		while(i.hasNext()) {
			MarkerPanel markerPanel = (MarkerPanel)i.next();
			markerPanel.setPosition(size);
			
			size++;
        }
	}
	
	public MarkerPanel getMarkerPanel(Vector vector) {
		@SuppressWarnings("rawtypes")
		Iterator i= getComponentIterator();
		while(i.hasNext()) {
			MarkerPanel markerPanel = (MarkerPanel)i.next();
			Vector v = (Vector) markerPanel.getVector();
			
			if (v != null && v.equals(vector))
				return markerPanel;
				
        }
		
		return null;
	}
	
	public Iterator<Component> getMarkers() {
		return getComponentIterator();
		
	}
	
	public void addListenerMarkerAdd(MarkerAddListener listener) {
		this.listenerMarkerAdd = listener;
		
	}
	
	public interface MarkerAddListener extends Serializable {
        public void markerAdd(MarkerEvent event);

    }
	
	public void addListenerMarkerMove(MarkerMoveListener listener) {
		this.listenerMarkerMove = listener;
		
	}
	
	public interface MarkerMoveListener extends Serializable {
        public void markerMove(MarkerEvent event);

    }
	
	public void addListenerMarkerRemove(MarkerRemoveListener listener) {
		this.listenerMarkerRemove = listener;
		
	}
	
	public interface MarkerRemoveListener extends Serializable {
        public void markerRemove(MarkerEvent event);

    }
	
	public class MarkerEvent extends Event {
		private int index;
		private Vector vector;

		public MarkerEvent(Component source, int index, Vector vector) {
			super(source);
			
			this.index = index;
			this.vector = vector;
		}

		/**
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * @param index the index to set
		 */
		public void setIndex(int index) {
			this.index = index;
		}

		/**
		 * @return the vector
		 */
		public Vector getVector() {
			return vector;
		}

		/**
		 * @param vector the vector to set
		 */
		public void setVector(Vector vector) {
			this.vector = vector;
		}		
		
	  }
}
