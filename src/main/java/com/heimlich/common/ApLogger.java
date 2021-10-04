package com.heimlich.common;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.tradevan.commons.logging.LogLevel;
import com.tradevan.commons.logging.LogObject;
import com.tradevan.commons.logging.Logger;
import com.tradevan.framework.FrameworkContext;

public class ApLogger extends Logger {
	
	public ApLogger() {
	}

	@Override
	public void log(LogLevel level, Object logObj) {
		if (logObj instanceof LogObject == false) {
			logObj = toLogObject(logObj);
		}
		super.log(level, logObj);
	}

	@Override
	public void log(LogLevel level, Object logObj, Throwable t) {
		if (logObj instanceof LogObject == false) {
			logObj = toLogObject(logObj);
		}
		super.log(level, logObj, t);
	}

	public void log(LogLevel level, Object logObj, boolean isNotify) {
		this.log(level, logObj, isNotify);

		FrameworkContext context = FrameworkContext.getContext();
		String systemNotify = context.getProperty("aplogger-system-notify");
		if (systemNotify.equals("true") && isNotify) {
			// TODO implement notify ......
		}

	}

	public void log(LogLevel level, Object logObj, Throwable t, boolean isNotify) {
		this.log(level, logObj, t, isNotify);

		FrameworkContext context = FrameworkContext.getContext();
		String systemNotify = context.getProperty("aplogger-system-notify");
		if (systemNotify.equals("true") && isNotify) {
			// TODO implement notify ......
		}
	}

	/**
	 * 產生LogObject，其訊息為Event.xml中${eventId}, ${code}設定的訊息。
	 * 
	 * @param eventId
	 * @param code
	 * @return
	 */
	public LogObject newLogObject(String eventId, String code) {
		LogObject logObj = factory.newLogObject(eventId, code);
		ActionProxy proxy = ActionContext.getContext().getActionInvocation()
				.getProxy();
		logObj.setProgramName(proxy.getActionName());
		logObj.setExecCommand(proxy.getMethod());

		return logObj;
	}

	/**
	 * 產生LogObject
	 * 
	 * @param logObj
	 * @return
	 */
	public LogObject newLogObject(Object logObj) {
		LogObject logObject;
		if (logObj != null) {
			logObject = factory.newLogObject(logObj.toString());
		} else {
			logObject = factory.newLogObject(null);
		}

		ActionProxy proxy = ActionContext.getContext().getActionInvocation()
				.getProxy();
		logObject.setProgramName(proxy.getActionName());
		logObject.setExecCommand(proxy.getMethod());

		return logObject;
	}

	/**
	 * 
	 * @param logObj
	 * @return
	 */
	private Object toLogObject(Object logObj) {
		if (factory == null) {
			return logObj;
		}
		if (ActionContext.getContext() == null
				|| ActionContext.getContext().getActionInvocation() == null) {
			return logObj;
		}
		return newLogObject(logObj);
	}

}
