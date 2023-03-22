package teamproject.comProjectPP.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.json.JSONObject;

import teamproject.comProjectPP.Controller.Basket_Controller;
import teamproject.comProjectPP.Controller.Board_Controller;
import teamproject.comProjectPP.Controller.User_Controller;
import teamproject.comProjectPP.DTO.Users;

public class SocketClient {
	Server server;
	Socket socket;
	
	DataInputStream dis;
	DataOutputStream dos;
	String Output;
	String clientIp;
	BasketService basket;
	Users users = new Users();
	
	
	
	public SocketClient(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
		try {
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
			InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
			this.clientIp = isa.getHostName();
			receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receive() {
		
		server.threadPool.execute(() -> {
			try {
				while (true) {
					String receiveJson = dis.readUTF();
					JSONObject jsonObject = new JSONObject(receiveJson);
					
					
					String controller = jsonObject.getString("controller");
					switch(controller) {
					
					case  "user_function" : 
						User_Controller user_controller = new User_Controller();
						Output = user_controller.InputJson(jsonObject);
						break;
						
					case "basket_Function" :
						Basket_Controller basket_controller = new Basket_Controller();
						Output = basket_controller.InputJson(jsonObject);
						break;
						
					case "Board_Function" :
						Board_Controller board_controller = new Board_Controller();
						Output = board_controller.InputJson(jsonObject);
						
						break;
						
						
						
						
					}
					send(Output);
					
					/*
					switch(jsonObject.getString("Command")) {
					//Product Receive()
					case"ProductList":
						Service_Product.getList(this,jsonObject.getString("startRow"),jsonObject.getString("endRow"));
						break;
					case "getTotalRow":
						Service_Product.getTotalROw(this);
						break;
					case "ProductContent":
						Service_Product.getProductContent(this,jsonObject.getString("Product_Id"));
						break;
					case "ProductOption1":
						basket.getOption1(jsonObject.getString("Product_Id"));
						break;
					case "ProductOption2":
						basket.getOption2(jsonObject.getString("Product_Id"),jsonObject.getString("Color"));
						break;
						}
					*/
					
				}
			} catch (IOException e) {
				// 연결이 끊겼을때
				// chatServer.sendToAll(this, "나가셨습니다.");
				server.removeSocketClient(this);
			}
		});

	}

	public static void main(String[] args) {
	}

	public void send(String json) {
		try {

			dos.writeUTF(json);
			dos.flush();
		} catch (IOException e) {

		}
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {

		}
	}

}
