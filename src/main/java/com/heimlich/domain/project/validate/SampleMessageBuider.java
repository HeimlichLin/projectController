package com.heimlich.domain.project.validate;

public class SampleMessageBuider implements SampleMessage {
	private STATUS status;
	private String message;

	public SampleMessageBuider(STATUS status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public static SampleMessage newMessage(STATUS status, String message) {
		return new SampleMessageBuider(status, message);
	}

	public static SampleMessage newErrorMessage(String message) {
		return new SampleMessageBuider(STATUS.ERROR, message);
	}

	public static SampleMessage newOkMessage(String message) {
		return new SampleMessageBuider(STATUS.OK, message);
	}

	public static SampleMessage newNoneMessage() {
		return new SampleMessageBuider(STATUS.NONE, "");
	}

	public STATUS getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public SampleMessage get() {
		return this;
	}


	@Override
	public String toString() {
		return "SampleMessageBuider [message=" + message + "]";
	}

}
