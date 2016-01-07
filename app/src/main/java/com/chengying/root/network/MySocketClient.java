package com.chengying.root.network;


import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;


public class MySocketClient {

		Socket socket =null;
		PrintWriter printWriter;
		BufferedReader bufferedReader;
		public static String STATE_TIME_OUT="网络连接超时";
		public static String STATE_SERVER_BUSY="服务器繁忙";
		public static String STATE_SERVER_ERROR="服务器内部错误";

		static MySocketClient qs=null;
			public static MySocketClient getInstance(){
//				if(qs==null){
//					qs=new QufeiSocketClient();
//				}
				qs=new MySocketClient();
				return qs;
		
			}
			private MySocketClient(){
				try {
					AppConfig app=AppConfig.getInstance();
					String ip=app.getIp();
					Log.i("ipp",ip);
					socket =new Socket(ip,Integer.parseInt(AppConfig.getInstance().getSocketPort()));
					 
					socket.setSoTimeout(AppConfig.TIMEOUT);
					
					
		
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public String send(String cmd,String str){{
				return send(">>"+cmd+"<<"+str);
				}
			}
			public String send(String cmd,JSONArray str){{
				return send(">>"+cmd+"<<"+str.toString());
				}
			}
	public String send(String cmd,Map<String,String> map){
		Set<String> keys=map.keySet();
		JSONArray ja=new JSONArray();
		JSONObject jo=new JSONObject();

		for(String key:keys){
			try {
				jo.put(key, map.get(key));
			}catch (JSONException e){
				e.printStackTrace();;
			}
		}
		ja.put(jo);
		return send(cmd,ja);

	}
		public String send(String str){
			String result =null;
			try {
				
				printWriter =new PrintWriter(socket.getOutputStream(),true);
				//str+="\n";
				
				bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
				printWriter.println(str.toCharArray());
				printWriter.flush();
				result = bufferedReader.readLine();
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//				System.out.println("Server say : " + result);

			
			return result;

		}
		public void close(){
			try {

				bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
				printWriter.println("bye".toCharArray());
				printWriter.flush();
				printWriter.close();
				bufferedReader.close();
				socket.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
}
