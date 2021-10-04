package com.heimlich.domain.project.validate;

/**
 * 資料驗證
 *
 * @param <Value>
 */
public interface ValidateObserver<Value> {
	
	/**
	 * 驗證
	 * 
	 * @param value
	 * @return
	 */
	SampleMessage isValidate(Value value);
}
