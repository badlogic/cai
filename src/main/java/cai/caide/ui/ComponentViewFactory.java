package cai.caide.ui;

import cai.caide.components.Component;

public interface ComponentViewFactory<T extends Component> {
	ComponentView<T> newComponentView(WorkspaceUI ui, T component);
	Class<T> getComponentType();
}
