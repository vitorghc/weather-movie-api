package com.example.api.enums;

public enum EnumGenre {

	ACTION(28), COMEDY(36), ANIMATION(16), TRILLER(53), DOCUMENT(99);

	private int value;

	private EnumGenre(int valueChoice) {
		value = valueChoice;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
