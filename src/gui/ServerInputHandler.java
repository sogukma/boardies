package gui;


public class ServerInputHandler implements Runnable{
	private static int id = 0;
	MessageHandler mh;

	public ServerInputHandler(MessageHandler messageHandler)
	{
		this.mh = messageHandler;
		id++;
	}
	
	@Override
	public void run() {
	
		while(true){
		String response = mh.receive();
		System.out.println(response);
		if(response.equalsIgnoreCase("name:*"))
		{
			
		}
		//if chat -> sendToAllCLients() ; if game -> game()
		}
	} 
}
