package simple_udp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Receiver {
	
	public Receiver() throws Exception{
		DatagramSocket socket = new DatagramSocket(2022);
		System.out.println("Receiver is running.");
		
		Scanner keyboard = new Scanner(System.in);
		
		// How much data can be sent in a single packet
		byte[] buffer = new byte[1500]; // MTU(Maximum Transmission Unit) 
		
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		
		socket.receive(packet); // retrieve the Sender's message
		
		String message = new String(buffer).trim(); // trim() is called to get rid of any unnecessary data that comes after the message
		System.out.println("Received : "+message);
		
		InetAddress senders_address = packet.getAddress();
		int senders_port = packet.getPort();
		
		System.out.print("Enter your message : ");
		message = keyboard.nextLine();
		
		buffer = message.getBytes(); //Switch the message ( of type String) to bytes
		
		packet = new DatagramPacket(buffer,buffer.length, senders_address, senders_port);
		socket.send(packet);
		
		System.out.println("Sent : "+message);
		
	}
	
	public static void main(String[] args) {
		
		try {
			new Receiver();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
