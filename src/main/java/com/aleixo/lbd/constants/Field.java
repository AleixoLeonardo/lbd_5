package com.aleixo.lbd.constants;

public enum Field {
	USER_BIRTH_DATE("birthDate"), USER_CPF("cpf"), USER_JOB_ID("jobId"), USER_NAME("name"), JOB_NAME("name"),
	USER_PASSWORD("password"),
	TASK_NAME("name"), HISTORY_TASK_DATE("historyDate"), HISTORY_TASK_TASK_ID("taskId"), HISTORY_TASK_USER_ID("userId");

	private String description;

	Field(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
