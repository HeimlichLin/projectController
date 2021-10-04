package com.heimlich.domain.project.validate;

public interface SampleMessage {
	enum STATUS {
		OK, ERROR, WARM, NONE
	}

	public STATUS getStatus();

	public String getMessage();
	
}
