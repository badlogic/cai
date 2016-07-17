package cai.caide.components;

public class Slot {
	final Component parent;
	final Class<?> type;
	final String name;

	public Slot(Component parent, Class<?> type, String name) {
		if (type == null) throw new IllegalArgumentException("type must not be null");
		if (parent == null) throw new IllegalArgumentException("parent must not be null");
		this.parent = parent;
		this.type = type;
		this.name = name;
	}

	public Component getParent() {
		return parent;
	}

	public Class<?> getType() {
		return type;
	}

	public String getName() {
		return name;
	}
}