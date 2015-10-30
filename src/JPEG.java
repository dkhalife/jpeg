import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JPEG {
	private static final byte TEM = (byte) 0x01;
	
	private static final byte SOF0 = (byte) 0xC0;
	private static final byte SOF1 = (byte) 0xC1;
	private static final byte SOF2 = (byte) 0xC2;
	private static final byte SOF3 = (byte) 0xC3;
	
	private static final byte DHT = (byte) 0xC4;
	
	private static final byte SOF5 = (byte) 0xC5;
	private static final byte SOF6 = (byte) 0xC6;
	private static final byte SOF7 = (byte) 0xC7;
	
	private static final byte JPG = (byte) 0xC8;
	private static final byte SOF9 = (byte) 0xC9;
	private static final byte SOF10 = (byte) 0xCA;
	private static final byte SOF11 = (byte) 0xCB;
	
	private static final byte DAC = (byte) 0xCC;
	
	private static final byte SOF13 = (byte) 0xCD;
	private static final byte SOF14 = (byte) 0xCE;
	private static final byte SOF15 = (byte) 0xCF;
	
	private static final byte SOI = (byte) 0xD8;
	private static final byte EOI = (byte) 0xD9;
	private static final byte SOS = (byte) 0xDA;
	private static final byte DQT = (byte) 0xDB;
	private static final byte DNL = (byte) 0xDC;
	private static final byte DRI = (byte) 0xDD;
	private static final byte DHP = (byte) 0xDE;
	private static final byte EXP = (byte) 0xDF;
	
	private static final byte COM = (byte) 0xFE;
	
	private static final byte MARKER = (byte) 0xFF;

	public static void main(String [] args){
		for(String filePath: args){
			File f = new File(filePath);
			if(f.exists()){
				readBytes(f);
			}
			else {
				System.err.println("File [" + filePath + "] does not exist!");
			}
		}
	}
	
	private static void readBytes(File file){
		try {
			byte[] bytes = Files.readAllBytes(file.toPath());
			
			for(int i=0; i<bytes.length; ++i){
				if(bytes[i] == MARKER){
					byte nextB = bytes[++i];
					
					switch(nextB){
					case SOF0:
					case SOF1:
					case SOF2:
					case SOF3:
						System.out.println("[" + i + "] SOF - ND");
					break;
					
					case SOF5:
					case SOF6:
					case SOF7:
						System.out.println("[" + i + "] SOF - D");
					break;
					
					case JPG:
					case SOF9:
					case SOF10:
					case SOF11:
						System.out.println("[" + i + "] SOF - NDA");
					break;
					
					case SOF13:
					case SOF14:
					case SOF15:
						System.out.println("[" + i + "] SOF - DA");
					break;
					
					case DHT:
						System.out.println("[" + i + "] DHT");
					break;
					
					case DAC:
						System.out.println("[" + i + "] DAC");
					break;
					
					case SOI:
						System.out.println("[" + i + "] SOI");
					break;
					
					case EOI:
						System.out.println("[" + i + "] EOI");
					break;
					
					case SOS:
						System.out.println("[" + i + "] SOS");
					break;
					
					case DQT:
						System.out.println("[" + i + "] DQT");
					break;
						
					case DNL:
						System.out.println("[" + i + "] DNL");
					break;
					
					case DRI:
						System.out.println("[" + i + "] DRI");
					break;
						
					case DHP:
						System.out.println("[" + i + "] DHP");
					break;
						
					case EXP:
						System.out.println("[" + i + "] EXP");
					break;
					
					case COM:
						System.out.println("[" + i + "] COM");
					break;
					
					//case MARKER:
						//System.out.println("[" + i + "] IMPOSSIBLE");
					//break;
					
					case TEM:
						System.out.println("[" + i + "] TEM");
					break;
					
					default:
						if(nextB >= 0xD0 && nextB <= 0xD7){
							System.out.println("[" + i + "] RST");
						}
						if(nextB >= 0xE0 && nextB <= 0xEF){
							System.out.println("[" + i + "] APP");
						}
						else if(nextB >= 0xF0 && nextB <= 0xFD){
							System.out.println("[" + i + "] JPG");
						}
						else if(nextB >= 0x02 && nextB <= 0xBF){
							System.out.println("[" + i + "] RES");
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
