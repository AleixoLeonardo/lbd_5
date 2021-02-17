package com.aleixo.lbd.service.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.Field;
import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.exception.ValidateException;
import com.aleixo.lbd.model.Job;

@Service
public class JobValidator {

	public void validateData(Job job, boolean isUpdate) {
		List<String> invalidFields = new ArrayList<>();
		if (null == job.getName()) {
			invalidFields.add(String.format("%s %s", Field.JOB_NAME, ValidateMessage.EMPTY_FIELD.getDescription()));
		}

		if (isUpdate && null == job.getId()) {
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
