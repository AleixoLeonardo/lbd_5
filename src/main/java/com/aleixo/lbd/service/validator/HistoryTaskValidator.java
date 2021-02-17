package com.aleixo.lbd.service.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.Field;
import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.exception.ValidateException;
import com.aleixo.lbd.model.HistoryTask;

@Service
public class HistoryTaskValidator {
	public void validateData(HistoryTask historyTask, boolean isUpdate) {
		List<String> invalidFields = new ArrayList<>();
		if (null == historyTask.getHistoryDate()) {
			invalidFields.add(String.format("%s %s", Field.HISTORY_TASK_DATE, ValidateMessage.EMPTY_FIELD.getDescription()));
		}

		if (null == historyTask.getTaskId()) {
			invalidFields.add(String.format("%s %s", Field.HISTORY_TASK_TASK_ID, ValidateMessage.EMPTY_FIELD.getDescription()));
		}

		if (null == historyTask.getUserId()) {
			invalidFields.add(String.format("%s %s", Field.HISTORY_TASK_USER_ID, ValidateMessage.EMPTY_FIELD.getDescription()));
		}

		if (isUpdate && null == historyTask.getId()) {
			invalidFields.add(String.format("%s", ValidateMessage.EMPTY_ID.getDescription()));
		}

		if (!invalidFields.isEmpty()) {
			StringBuilder info = new StringBuilder();
			for (String s : invalidFields) {
				info.append(s);
				info.append("\n");
			}
			throw new ValidateException(info.toString());
		}
	}
}
