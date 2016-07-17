package cai.caide.components;

public class Connection {
	private final Slot source;
	private final Slot sink;

	public Connection(Slot source, Slot sink) {
		this.source = source;
		this.sink = sink;
	}

	public Slot getSource() {
		return source;
	}

	public Slot getTarget() {
		return sink;
	}
}
