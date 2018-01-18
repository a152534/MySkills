package org.notice.server;

import org.notice.buscontrol.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SkillServer {
	private BusinessControl bc;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private SkillsServerWorkerThread worker;

	public SkillServer() {
		bc = new BusinessControl();
		System.out.println("Server running ");
		serverSocket = null;

		try {
			serverSocket = new ServerSocket(60606);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			System.out.println("Server waiting for client connection ..");
			try {
				clientSocket = serverSocket.accept();
				worker = new SkillsServerWorkerThread(clientSocket, bc);
				worker.getThread().start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new SkillServer();
	}

}