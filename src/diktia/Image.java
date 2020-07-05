/*Charis Filis	
 * AEM: 9449
 */
package diktia;
import ithakimodem.*;

import java.io.*;
public class Image {
	public String imRequest;
	public String imStrResponse;
	public File imFile;
	public void setImRequest(String imRequest) {this.imRequest = imRequest;}
	public void setImResponse(String imStrResponse) {this.imStrResponse = imStrResponse;}
	
	public Image() {
		setImRequest("M0119\r");
		setImResponse("");  
	}
	public void getImage() {
		imRequest = "G4558\r";
		Modem modem = new Modem(1000);
		modem.setSpeed(80000);
		modem.setTimeout(8000);
		modem.open("ithaki");
		
		modem.write(imRequest.getBytes());
		
		int k = 0;
		// p for previous variable of k in order to check successive bytes 
		int p;
		int flag = 0;
		
		//create a file to store the image
		imFile = new File("imageFile.jpg");
		//create a File Output Stream to store it to the file
		try(FileOutputStream imFop = new FileOutputStream(imFile)){
			do{
				
				try {
						//insert the previous value of k
						p = k;
					//	System.out.println("try2ok");
						k = modem.read();
					//	System.out.println(k);
						if(k == -1) {
							System.out.println("connection closed");
							break;
						}
					//	System.out.println(flag);
						if(p== 0xFF &&  k== 0xD8) {
							imFop.write((char)p);
							imFop.write((char)k);
							flag += 1;
							System.out.println("File Start");
						}else if(p== 0xFF && k == 0xD9) {
							imFop.write((char)p);
							imFop.write((char)k);
							System.out.println("File End");
							break;
						}else if(flag > 0) {
							imFop.write((char)k);
						//	System.out.println("F.W");
						
						}
						
				}catch(Exception x) {
					System.out.println(x);
					break;
				}
				
			}while(true);
			imFop.flush();
			imFop.close();
			System.out.println("Done");
					
		}catch(IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		modem.close();
		System.out.println("Modem Closed");
	}
}
