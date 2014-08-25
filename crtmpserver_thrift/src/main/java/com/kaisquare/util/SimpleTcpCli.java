package com.kaisquare.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleTcpCli {
	public static String execute(String addr, int port, String cmd) throws Exception {
		Socket s = new Socket(addr, port);
		OutputStream os = s.getOutputStream();
		DataOutputStream writer = new DataOutputStream(os);
		InputStreamReader isr = new InputStreamReader(s.getInputStream());
		BufferedReader reader = new BufferedReader(isr);

		writer.writeBytes(cmd);

		String resp = reader.readLine();

		s.close();

		return resp;
	}
}
