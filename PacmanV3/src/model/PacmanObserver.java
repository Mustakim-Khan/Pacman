package model;

import java.util.List;

import javax.swing.JComponent;

public interface PacmanObserver {
	public void notify(List<PacmanEvent> events);
	public JComponent getComponent();
}