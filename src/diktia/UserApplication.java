/*Charis Filis	
 * AEM: 9449
 */
package diktia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ithakimodem.Modem;

public class  UserApplication{
	public UserApplication() {}
	public static void main(String[] args) {

//Code for testing the classes 
//		//echo packet
//		EchoPacket ep = new EchoPacket();
//		String str = ep.getPacket();
//		System.out.println("The packet is:" + str);
//		String str2 = ep.getPacket();
//		System.out.println("Packet 2 is:" + str2);
		//image
//		Image im =  new Image();
//		im.getImage();
		GpsPacket gp = new GpsPacket();
//		String gpsString = gp.getPacket();
//		System.out.println(gpsString);
		gp.getGpsImage("P1576Ô=225735403737T=225734403738T=225733403738T=225732403739\r");
	

//  	ARQ implementation
//		int k;
//		String ack = "Q9426\r";
//		String nack = "R1246\r";
//		long sessionElapsedTime = 0;
//		long sessionStartTime = System.currentTimeMillis();
//		boolean f;
//		int nack_counter = 0;
//		int ack_counter = 0;
//		int flag = 0; // to start writing the packet 
//		boolean flag2 = false;//to check if we will write nack or ack
//		String response = "";
//		String packet = "";
//		int i = 1;
//		long arqPacketElapsedTime;
//		int triesn = 0;
//		int triesa = 0;
//		//file writer
//		try {
//			FileWriter tFW = new FileWriter("arqPacketTimes.txt");
//			FileWriter rFW = new FileWriter("#resendPacket.txt");
//			BufferedWriter out = new BufferedWriter(tFW);
//			BufferedWriter out2 = new BufferedWriter(rFW);
//			//modem code 
//			Modem modem = new Modem();
//			modem.setSpeed(80000);
//			modem.setTimeout(8000);
//			modem.open("ithaki");
//			//300000ms = 5 min 
//			while(sessionElapsedTime < 30000) {
//				response = " ";
//				long arqPacketStartTime = System.currentTimeMillis();
//				if(flag2 == false) {
//					ack_counter+= 1;
//					f = modem.write(ack.getBytes());
//					if(!f) {
//						System.out.println("Problem in writing ack");
//					}
//				}else if(flag2 == true){
//					nack_counter = nack_counter + 1;
//					f = modem.write(nack.getBytes());
//					if(!f) {
//						System.out.println("Problem in writing nack");
//					}
//				}
//				
//				packet = "";
//				for(;;) {
//					try {
//							k = modem.read();
//							if(k == -1) {
//								System.out.println("Connection Closed");
//								break;
//							}
//							response+=(char)k;
//							if(response.contains("PSTART") || flag > 1) {
//								if(flag == 0) {
//									packet = "PSTART";
//									flag++;
//								}else{
//									packet += (char)k;
//								}
//							}
//						//section where we have taken the packet 
//							if(response.contains("PSTOP")) {
//								arqPacketElapsedTime = System.currentTimeMillis() - arqPacketStartTime;
//								System.out.println("Packet Elapsed Time:" + arqPacketElapsedTime + "ms");
//								//write the file with the time elapsed to get each packet
//								out.write(String.valueOf(arqPacketElapsedTime));
//								out.newLine();
//								System.out.println("File Writen");
//								//System.out.println(response);
//								//System.out.println("Packet arrived!");
//								System.out.println(packet);
//								if(i == 1 ) {//for the first packet.
//									String text = packet.substring(31,47);
//									String fcs = packet.substring(49,52);
//									System.out.println(text);
//									System.out.println(fcs);
//									int numberXor = text.charAt(0);
//									for(int j = 1; j < text.length(); j++) {
//										numberXor = numberXor^text.charAt(j);
//									}
//									System.out.print("NumberXor:");
//									System.out.println(numberXor);
//									System.out.println(Integer.valueOf(fcs));
//									if(numberXor != Integer.valueOf(fcs)) {
//										System.out.println("Packet has errors");
//										flag2 = true;
//										i+=1;
//										break;
//									}else {
//										flag2 = false;
//										System.out.println("Correct Packet");
//										i+=1;
//										break;
//									}
//								}else { //for packets after the first one where I discovered that PSTART is delivered as T.
//									String text = packet.substring(26,42);
//									String fcs = packet.substring(44,47);
//									System.out.println(text);
//									System.out.println(fcs);
//									int numberXor = text.charAt(0);
//									for(int j = 1; j < text.length(); j++) {
//										numberXor = numberXor^text.charAt(j);
//									}
//									System.out.print("NumberXor:");
//									System.out.println(numberXor);
//									System.out.println(Integer.valueOf(fcs));
//									if(numberXor != Integer.valueOf(fcs)) {
//										System.out.println("Packet has errors");
//										flag2 = true;
//										triesn += 1;
//										i+=1;
//										break;
//									}else {
//										flag2 = false;
//										triesa +=1;
//										System.out.println("Correct Packet");
//										i+=1;
//										break;
//									}
//								}
//							//	break;
//								//end of code for error in packet handling 
//							
//							}
//						}catch(Exception x) {
//							System.out.println(x);
//						}
//					
//				}
//					sessionElapsedTime = System.currentTimeMillis() - sessionStartTime;
//					System.out.println("Session Elapsed Time:" + sessionElapsedTime);
//					System.out.println(ack_counter);
//					System.out.println(nack_counter);
//					out2.append(String.valueOf(triesa));
//					out2.newLine();
//			}
//			System.out.println(ack_counter);
//			System.out.println(nack_counter);
//			
//			out.close();
//			out2.close();
//		}catch(IOException ex) {
//			System.out.println("IO Exception occurred!");
//			ex.printStackTrace();
//		}
//		
//		
//
//	
//	
	}

		
}
