package com.heimlich.domain.project.validate;

import com.heimlich.domain.project.controller.fospm2.Fospm2Controller;
import com.heimlich.domain.project.validate.SampleMessage.STATUS;

public class ValidateActionSubject {
	
	public static <T> boolean validate(ValidateObserver<T> validateObserver, T t, Fospm2Controller defaultAction) {
		SampleMessage sampleMessage = validateObserver.isValidate(t);
		if (sampleMessage.getStatus() == STATUS.ERROR) {
//			defaultAction.addActionError(sampleMessage.getMessage());
			return false;
		}
		return true;
	}

}
