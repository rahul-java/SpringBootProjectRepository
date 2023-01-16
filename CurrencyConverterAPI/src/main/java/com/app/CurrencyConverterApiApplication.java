package com.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import javax.naming.spi.DirStateFactory.Result;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyConverterApiApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CurrencyConverterApiApplication.class, args);
		
		String result=sendHttpRequest("USD", "INR", 100);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$    :::::"+result);
	}
	
	public static String sendHttpRequest(String from, String to, double amount) throws IOException 
	{
		DecimalFormat decimalFormat=new DecimalFormat("00.00");
		String GET_URL="https://api.apilayer.com/exchangerates_data/convert?to=" + to + "&from=" + from + "&amount="+amount+"&apikey=Mf7TXnmk6iH7XykSB3LFWe00xZ1p5tVr";
		

			URL url=new URL(GET_URL);
			HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			int responseCode=httpURLConnection.getResponseCode();
			
			if(responseCode==HttpURLConnection.HTTP_OK)
			{
				BufferedReader in=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			    String inputLine;
			    StringBuffer response=new StringBuffer();
			    
			    while((inputLine=in.readLine())!=null)
			    {
			    	response.append(inputLine);
			    }
			    in.close();
			    
			    JSONObject json_Object=new JSONObject(response.toString());
			    System.out.println(json_Object);
			    double exchangeRate = json_Object.getJSONObject("info").getDouble("rate");
			    Double finalAmount=json_Object.getDouble("result");
			    
			    //return decimalFormat.format(exchangeRate);
			    return decimalFormat.format(finalAmount);
			}
			else {
				System.out.println("GET Request Failed....");
			    return null;	
			}
		
	}

}
