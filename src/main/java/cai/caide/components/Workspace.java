package cai.caide.components;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Workspace {
	final Array<Component> components = new Array<>();
	final Array<Connection> connections = new Array<>();
	final Array<Event> events = new Array<>();
	final Array<Event> tmpEvents = new Array<>();
	final Array<WorkspaceListener> listeners = new Array<>();

	public Array<Component> getComponents() {
		return components;
	}

	public void addListener(WorkspaceListener listener) {
		this.listeners.add(listener);
	}

	public void removeListener(WorkspaceListener listener) {
		this.listeners.removeValue(listener, true);
	}

	public Array<Connection> getConnections() {
		return connections;
	}

	public void addComponent(Component component) {
		components.add(component);
		for(WorkspaceListener l: listeners) l.addedComponent(component);
	}

	public void removeComponent(Component component) {
		components.removeValue(component, true);
		for(WorkspaceListener l: listeners) l.removedComponent(component);

		Iterator<Connection> iter = connections.iterator();
		while (iter.hasNext()) {
			Connection c = iter.next();
			if (c.getSource().getParent() == component || c.getTarget().getParent() == component) {
				iter.remove();
				for(WorkspaceListener l: listeners) l.removedConnection(c);
			}
		}
	}

	public void addConnection(Slot sourceSlot, Slot targetSlot) {
		if (sourceSlot.getType() != targetSlot.getType()) throw new IllegalArgumentException("types of source and target slot must be the same");
		Connection c = new Connection(sourceSlot, targetSlot);
		connections.add(c);
		for(WorkspaceListener l: listeners) l.addedConnection(c);
	}

	public void sendEvent(Slot sourceSlot, Object value) {
		events.add(new Event(sourceSlot, value));
	}

	public void update(float delta) {
		for (Component c: components) {
			c.update(delta);
		}

		while (events.size > 0) {
			tmpEvents.clear();
			tmpEvents.addAll(events);
			events.clear();
			for (Event event: tmpEvents) {
				for (Connection c: connections) {
					if (c.getSource() == event.sourceSlot) {
						c.getTarget().getParent().event(event.sourceSlot.getParent(), event.sourceSlot, c.getTarget(), event.value);
					}
				}
			}
		}
	}

	private static class Event {
		final Slot sourceSlot;
		final Object value;

		public Event(Slot sourceSlot, Object value) {
			this.sourceSlot = sourceSlot;
			this.value = value;
		}
	}

	public static interface WorkspaceListener {
		void addedComponent(Component c);
		void removedComponent(Component c);
		void addedConnection(Connection c);
		void removedConnection(Connection c);
	}
}
