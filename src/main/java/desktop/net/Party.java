package desktop.net;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class is used as a structure holding basic information about a party
 *
 * @author Aleksander Lasecki
 */
public class Party {

	private final StringProperty nameValue = new SimpleStringProperty();
	private final IntegerProperty leftValue = new SimpleIntegerProperty();
	private final IntegerProperty maxValue = new SimpleIntegerProperty();

	/**
	 * Class constructor
	 *
	 * @param name  The name of the party
	 * @param left  How many slots are available in the party
	 * @param max   How many slots are in the party (available + taken)
	 */
	public Party(String name, int left, int max) {
		nameValue.setValue(name);
		leftValue.setValue(left);
		maxValue.setValue(max);
	}

	public String getNameValue() {
		return nameValue.get();
	}

	public StringProperty nameValueProperty() {
		return nameValue;
	}

	public void setNameValue(String nameValue) {
		this.nameValue.set(nameValue);
	}

	public Integer getLeftValue() {
		return leftValue.get();
	}

	public IntegerProperty leftValueProperty() {
		return leftValue;
	}

	public void setLeftValue(int leftValue) {
		this.leftValue.set(leftValue);
	}

	public Integer getMaxValue() {
		return maxValue.get();
	}

	public IntegerProperty maxValueProperty() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue.set(maxValue);
	}
}
