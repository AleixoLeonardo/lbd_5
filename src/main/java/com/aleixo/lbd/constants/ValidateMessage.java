package com.aleixo.lbd.constants;

public enum ValidateMessage {

	EMPTY_FIELD("field cannot be null"),
	EMPTY_ID("field ID cannot be null "),
	NOT_FOUND("data not found"),
	PARSE_ERROR("Can't convert data"),
	INVALID_DATA_ERROR("Invalid data")
	;

	private String description;

	ValidateMessage(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
