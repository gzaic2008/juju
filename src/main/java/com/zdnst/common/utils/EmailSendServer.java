package com.zdnst.common.utils;

import java.util.Timer;



public class EmailSendServer {

	private long period = 1000 * 60 * 10; // 每十分钟

	private Timer sendMail = null;

	public EmailSendServer() {
		sendMail = new Timer();
		//period = "600000" ;
	}

	public void run() {
		EmailServer mailTask = new EmailServer();
		sendMail.schedule(mailTask, 1000 * 60, period);
	}

}
