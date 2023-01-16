package Mobile.Msg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class MobileMsg {

	public static void sendSms(String msg, String number)
	{
		System.out.println("Message : "+msg);
		System.out.println("Number : "+number);
		
		try 
		{
			String apiKey="iIexjP8aC1koXA4Nb2HJfYThEq6ntFpOSy3lVKzLrWucv5M0B7zkieLEAtPvf3RVrCJB95FM8gYwdbIK";
			String senderId="FSTSMS";
			//Encode String
			
		 
			msg = URLEncoder.encode(msg,"UTF-8");
		
		 
		 String lang="english";
		 String route="p";
		 String url="https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&sender_id="+senderId+"&message="+msg+"&language="+lang+"&route="+route+"&numbers="+number;

		 //sending get req using java
		 
		
			URL myUrl=new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) myUrl.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Google Chrome");
			con.setRequestProperty("cache-control", "no-cache");
			System.out.println("Wait............");
			int responseCode = con.getResponseCode();
			System.out.println("Response Code : "+responseCode);
			
			StringBuffer responseStringBuffer = new StringBuffer();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while(true)
			{
				String line=bufferedReader.readLine();
				if(line==null)
				{
					break;
				}
				responseStringBuffer.append(line);
			}
			System.out.println(responseStringBuffer);
			
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		MobileMsg.sendSms("Kya Hal Hai....Kya Chal Rha Hai... "+new Date().toLocaleString(), "9565501695");
		
	}
}
