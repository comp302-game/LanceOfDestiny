package tr.edu.ku;

import java.net.*;
import java.io.*;



public class NetworkHandler {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private int mode;

	public NetworkHandler() {  //Server
		
		try {
			this.serverSocket = new ServerSocket(6666);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public NetworkHandler(String ip) {
		try {
			this.clientSocket = new Socket(ip, 6666);
			this.out = new ObjectOutputStream(clientSocket.getOutputStream());
			this.in = new ObjectInputStream(clientSocket.getInputStream());
			startInputListenerThread(this.in, this.mode);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void setupServer() {
		try {
			this.clientSocket = serverSocket.accept();
			this.out = new ObjectOutputStream(clientSocket.getOutputStream());
			this.in = new ObjectInputStream(clientSocket.getInputStream());
			startInputListenerThread(this.in, mode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public String get_IP(){
		String ipAddress = "";
		try (final DatagramSocket datagramSocket = new DatagramSocket()) {
		    datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
		    return datagramSocket.getLocalAddress().getHostAddress();
		} catch (UnknownHostException | SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ipAddress;
	}


	
	public void sendPlayerInfo(int score, int barriers, int chances) {
		Message message = new Message(this.mode, score, barriers, chances);
		
		try {
			this.out.writeObject(message);
			this.out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void sendSpellIfo(String spellName) {
		try {
			this.out.writeChars(spellName);
			this.out.flush();
		}
		catch(IOException e){	
		}
	}

	
	private static void startInputListenerThread(ObjectInputStream in, int mode) {
        Thread listenerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    
                    while (true) {
                        // Process the received data, you can modify this part as per your requirements
                        try {
							Object message = in.readObject();
							
							if (message instanceof Message) {
		                        Message palyerInfo = (Message) message;
		                        // Handle Message object
		                        
		                        System.out.println("This is player: " + String.valueOf(mode));
		                        System.out.println("Reviced mode: " + String.valueOf(palyerInfo.mode) +
		                        		" other info: "  + String.valueOf(palyerInfo.barriers));
		                        
		                    } else if (message instanceof String) {
		                        String spell = (String) message;
		                        // Handle String object
		                        
		                    }
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        listenerThread.start();
    }
	
	public static void main(String[] args) {
        // Create the first thread for operation 1
       
		NetworkHandler nh = new NetworkHandler();
		System.out.println("IP adress is: " + nh.get_IP());
		nh.setupServer();
		while(true) {
        	try {
        	    // Sleep for 1000 milliseconds (1 second)
        	    Thread.sleep(5000);
        	} catch (InterruptedException e) {
        	    // Handle the InterruptedException (if required)
        	    e.printStackTrace();
        	}
        	nh.sendPlayerInfo(1, 1, 1);
        	
        }
				
        // This message will be printed by the main thread
        
    }
}





