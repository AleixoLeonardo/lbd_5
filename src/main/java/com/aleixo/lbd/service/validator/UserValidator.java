package com.aleixo.lbd.service.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.Field;
import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.exception.ValidateException;
import com.aleixo.lbd.model.User;

@Service
public class UserValidator {

	public void validateData(User user, boolean isUpdate) throws ValidateException {
		List<String> invalidFields = new ArrayList<>();
		if (null == user.getBirthDate()) {
			invalidFields.add(String.format("%s %s", Field.USER_BIRTH_DATE, ValidateMessage.EMPTY_FIELD.getDescription()));
		}
		if (null == user.getCpf()) {
			invalidFields.add(String.format("%s %s", Field.USER_CPF, ValidateMessage.EMPTY_FIELD.getDescription()));
		}
		if (null == user.getJobId()) {
			invalidFields.add(String.format("%s %s", Field.USER_JOB_ID, ValidateMessage.EMPTY_FIELD.getDescription()));
		}
		if (null == user.getName()) {
			invalidFields.add(String.format("%s %s", Field.USER_NAME, ValidateMessage.EMPTY_FIELD.getDescription()));
		}
		if (isUpdate && null == user.getId()) {
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
