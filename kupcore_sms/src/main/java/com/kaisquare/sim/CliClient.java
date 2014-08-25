package com.kaisquare.sim;

import com.kaisquare.stream.thrift.SmStreamInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CliClient {
	static void process(String line) {
		String tokens[] = line.split(" ");
		if (tokens.length == 0)
			return;

		String cmd = tokens[0];
		if (cmd.compareToIgnoreCase("addst") == 0) {
			if (tokens.length != 6) {
				System.out.println("Syntax: addst <devid> <chanid> <src> <srctype> <outtype>");
				return;
			}

			SmStreamInfo si = new SmStreamInfo();
			si.setSource(tokens[3]);
			si.setSourceType(tokens[4]);
			si.setOutputType(tokens[5]);
			try {
				si.setDeviceId(Long.parseLong(tokens[1]));
				si.setChannelId(Integer.parseInt(tokens[2]));
				SmsClient.addStream(si);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cmd.compareToIgnoreCase("rmst") == 0) {
			if (tokens.length != 4) {
				System.out.println("Syntax: rmst <devid> <chanid> <outtype>");
				return;
			}

			SmStreamInfo si = new SmStreamInfo();
			si.setOutputType(tokens[3]);
			try {
				si.setDeviceId(Long.parseLong(tokens[1]));
				si.setChannelId(Integer.parseInt(tokens[2]));
				SmsClient.removeStream(si);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cmd.compareToIgnoreCase("clients") == 0) {
			if (tokens.length != 4) {
				System.out.println("Syntax: clients <devid> <chanid> <outtype>");
				return;
			}

			SmStreamInfo si = new SmStreamInfo();
			si.setOutputType(tokens[3]);
			try {
				si.setDeviceId(Long.parseLong(tokens[1]));
				si.setChannelId(Integer.parseInt(tokens[2]));
				SmsClient.getClients(si);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cmd.compareToIgnoreCase("exit") == 0) {
			System.exit(1);
		} else if (cmd.compareToIgnoreCase("servers") == 0) {
			SmsClient.getServerPoolInfo();
		} else {
			System.err.println("Unknown command: " + cmd);
		}
	}

	public static void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;

		while (true) {
			try {
				line = br.readLine();
				process(line);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
