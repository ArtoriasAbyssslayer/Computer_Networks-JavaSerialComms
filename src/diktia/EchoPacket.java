/*Charis Filis	
 * AEM: 9449
 */
package diktia;
import ithakimodem.*;

public class EchoPacket {
	public String request;
	public String response;
	void setRequest(String request) {this.request=request;}
	void setResponse(String response) {this.response=response;}
	public EchoPacket() {
		setRequest("E1234\r");
		setResponse("");
	}
	public String getPacket() {
		request = "E2283\r";
	    response = "";
		Modem modem = new Modem();
		modem.setSpeed(800000);
		modem.setTimeout(8000);
		modem.open("ithaki");
		modem.write(request.getBytes());
		String packet = "";
		int flag = 0;
		boolean flag2 = false;
		while(flag2 == false){
			try {
					//System.out.println("ep");
					 modem.write(request.getBytes());
					 while(packet.contains("PSTOP")!=true){
						int p = modem.read();
						if(p == -1) {
							System.out.println("connection closed");
							break;
						}
						response+=(char)p;
						if(response.contains("PSTART") || flag > 0 ) {
							
							if(flag == 0) {
								packet = "PSTART";
								flag++;
							}else{
								packet += (char)p;
							}
							
						}
						if(packet.contains("PSTOP")==true) {
							//System.out.println("Packet is here");
							System.out.println(packet);
							flag2 = true;
							break;
						}
				     }
				
				}catch(Exception x) {
				System.out.println(x);
			}
		}
		modem.close();
		//System.out.println(response);
		return packet;
		
	}
}