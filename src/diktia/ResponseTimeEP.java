/*Charis Filis	
 * AEM: 9449R
 */
package diktia;
import ithakimodem.*;
public class ResponseTimeEP {
	public ResponseTimeEP() {}
	
	public static void main(String[] args) {
		int k;
		boolean f;
		long echoPacketStartTime;
		long echoPacketElapsedTime;
		long sessionElapsedTime = 0;
		long sessionStartTime = System.currentTimeMillis();
		//file to be written
		try{
			FileWriter tFW = new FileWriter("timeResults.txt");
			BufferedWriter out = new BufferedWriter(tFW);
			
			String request = "E7483\r";
			Modem modem = new Modem();
			modem.setSpeed(800000);
			modem.setTimeout(8000);
			modem.open("ithaki");
			//300000 =  5min > 4min sessionTime needed
			while(sessionElapsedTime < 300000) {
				String response = " ";
				echoPacketStartTime = System.currentTimeMillis();
				f = modem.write(request.getBytes());
				if(!f) {
					System.out.println("Problem in write");
				}
				while(true) {
					try {
						
						k = modem.read();
						if(k == -1) {
							System.out.println("Connection Closed");
							break;
						}
						
						response += (char)k;
						if(response.contains("PSTOP")) {
							echoPacketElapsedTime = System.currentTimeMillis() - echoPacketStartTime;
							System.out.println("Packet arrived!");
							System.out.println("Elapsed Time:" + echoPacketElapsedTime + "ms");
							out.write(String.valueOf(echoPacketElapsedTime));
							out.newLine();
							System.out.println("File Writen");
							break;
						}
					}catch(Exception x) {
						System.out.println(x);
					}
				}
				sessionElapsedTime = System.currentTimeMillis() - sessionStartTime;
				System.out.println(sessionElapsedTime);
			}
			out.close();
		
		}catch(IOException ex){
	        System.out.println("Error writing to file " );
	    }	
	}

}
