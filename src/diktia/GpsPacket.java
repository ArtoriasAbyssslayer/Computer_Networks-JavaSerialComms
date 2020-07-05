/*Charis Filis	
 * AEM: 9449
 */
package diktia;
import ithakimodem.*;
import java.io.*;
public class GpsPacket {
	public String GpsResponse;
	public String GpsRequest;
	public File gpsFile;
	void setRequest(String GpsRequest) {this.GpsRequest=GpsRequest;}
	void setResponse(String GpsRequest) {this.GpsRequest=GpsRequest;}
	public GpsPacket() {
		setRequest("P1234\r");
		setResponse("");
	}
	public String getPacket() {
		String GpsRequest = "P1576R=1003050\r";
		boolean flag = false;
		long sessionStartTime = System.currentTimeMillis();
		long sessionElapsedTime = 0;
		Modem modem = new Modem();
		modem.setSpeed(80000);
		modem.setTimeout(8000);
		modem.open("ithaki");
		boolean f;
		do{
			String GpsPacket = "";
			f = modem.write(GpsRequest.getBytes());
			if(!f) {
				System.out.println("Problem in write");
			}
			if((sessionElapsedTime - 10000) % 10000 == 0) {
				while(true) {
					try {
							int k = modem.read();
					//		System.out.println(k);
							if(k == -1) {
								System.out.println("Connection closed.");
								break;
							}
							GpsResponse+=(char)k;
					//		System.out.println(GpsResponse);
							if(GpsResponse.contains("START ITHAKI GPS TRACKING\r\n")) {
								GpsPacket += (char)k;
						
							}
							if(GpsResponse.contains("STOP ITHAKI GPS TRACKING\r\n")) {
								GpsPacket.replace("STOP ITHAKI GPS TRACKING", "");
							//	System.out.println(GpsPacket);
								sessionElapsedTime = System.currentTimeMillis() - sessionStartTime;
								flag = true;
								break;
							}
						
					}catch(Exception x) {
						System.out.println(x);
					}
				}
				
			}else {
				sessionElapsedTime = System.currentTimeMillis() - sessionStartTime;
			}
			
			//System.out.println(GpsPacket);
			return GpsPacket;
	    }while(!flag);
		
		
	}
	//GPS REQUEST ="P0921T=225735403736T=225734403738T=225733403738T=225732403738\r"
	// 4 points 10sec time difference
	public void getGpsImage(String GpsRequest) {
		int p;
		int k = 0;
		int flag = 0;
		Modem modem = new Modem(1000);
		modem.setSpeed(8000);
		modem.setTimeout(8000);
		modem.open("ithaki");
		boolean mw;
		mw = modem.write(GpsRequest.getBytes());
		if(!mw) {
			System.out.println("Problem with write");
			return;
		}
		
		gpsFile = new File("gpsImage.jpg");
		try(FileOutputStream gpsFop = new FileOutputStream(gpsFile)){
			
			do{
			
				
				try {
						//insert the previous value of k
						p = k;
						System.out.println("try2ok");
						k = modem.read();
						System.out.println(k);
						if(k == -1) {
							System.out.println("connection closed");
							break;
						}
						System.out.println(flag);
						if((char)p== 0xFF &&  (char)k== 0xD8) {
							gpsFop.write((char)p);
							gpsFop.write((char)k);
							flag += 1;
							System.out.println("File Start");
						}else if((char)p== 0xFF && (char)k == 0xD9) {
							gpsFop.write((char)p);
							gpsFop.write((char)k);
							System.out.println("File End");
							break;
						}else if(flag > 0) {
							gpsFop.write((char)k);
							System.out.println("F.W");
						
						}
						
				}catch(Exception x) {
					System.out.println(x);
					break;
				}
				
			}while(true);
			
			
			gpsFop.flush();
			gpsFop.close();
		}catch(IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
