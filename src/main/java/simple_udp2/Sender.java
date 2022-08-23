package simple_udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Sender {

	public Sender() throws Exception {
		DatagramSocket socket = new DatagramSocket();
		Scanner keyboard = new Scanner(System.in);
		String message = "";
		System.out.println(
				"\t \t -------------------------- \n\t \t || To quit, type 'Exit' ||\n\t \t -------------------------- ");
		while (!message.equalsIgnoreCase("EXIT")) {
			// To send a packet
			System.out.print("Enter your message : ");

			message = keyboard.nextLine();

			byte[] buffer = message.getBytes();

			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 2022);
			// You can also use
			// DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
			// InetAddress.getByName("127.0.0.1"), 2022);
			//
			socket.send(packet);

			System.out.println("Sent : " + message);
			
			//To receive a packet
			buffer = new byte[1500];
			packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);

			message = new String(buffer).trim();
			System.out.println("Received : " + message);

		}
		message = "exit";
		System.out.println("Nice Having you ... See you next time.");
	}

	public static void main(String[] args) {
		try {
			new Sender();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
