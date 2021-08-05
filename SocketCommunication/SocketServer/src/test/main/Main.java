package test.main;

import test.tcp.TCPEchoServer;

public class Main {
public static void main(String[] args) {
	new TCPEchoServer().tcpServer(9007);
}
}
