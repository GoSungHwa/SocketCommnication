package test.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPEchoServer {
	public void tcpServer(int port) {
		// 서버 소켓 관련 객체
		ServerSocket serverSocket = null;
		Socket socket = null;
		InetAddress inetAddr = null;

		// 입출력 객체
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;
		PrintWriter wr = null;

		try {
			// 서버 소켓 생성
			serverSocket = new ServerSocket(port);

			while (true) {
				// 클라이언트 접속 대기
				System.out.println("=====클라이언트 접속 대기 중======" + "(port)" + serverSocket.getLocalPort() + "====");
				socket = serverSocket.accept(); // 대기를 기다렸다가 접속 시도하면 소켓을 발동?

				// 클라이언트의 접속 요청
				inetAddr = serverSocket.getInetAddress();
				System.out.println("클라이언트(" + inetAddr.getHostAddress() + ") 접속");
				// 클라이언트와 통신을 위한 stream 생성
				in = socket.getInputStream();
				out = socket.getOutputStream();
				br = new BufferedReader(new InputStreamReader(in));//
				wr = new PrintWriter(new OutputStreamWriter(out));//보조스트림
				String msg = null;
				// 클라이언트와 통신
				while ((msg = br.readLine()) != null) {
					System.out.println("\tCLIENT> " + msg);
					wr.println(msg);
					wr.flush(); //스트림 안의 남아있는 데이터 비워낸다.
				}
			}
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			try {
				br.close();
				wr.close();
				in.close();
				out.close();
				socket.close();
				serverSocket.close();
				System.out.println("종료.");
			} catch (IOException ie) {
				System.out.println(ie);
			}
		}
	}
}
