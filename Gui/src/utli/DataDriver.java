/**
 * 
 */
package utli;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import org.omg.CORBA.portable.OutputStream;

import netscape.javascript.JSObject;

/**
 * @author Giang Nguyễn
 *
 * Nhìn giề! đọc code kìa!
 */
public class DataDriver {
	
	public JSONObject getToken(String email,String pass) {
		String sUrl = "http://127.0.0.1:8000/login";
		JSONObject obj = new JSONObject();
		obj.put("email", email);
		obj.put("password", pass);
		JSONObject result = null;
		try {
			URL url = new URL(sUrl);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			
			request.setRequestMethod("POST");
			request.setRequestProperty("Accept", "application/json");
			request.setRequestProperty("Content-Type", "application/json");
			request.setDoOutput(true);
			
			request.connect();
			
			String parameter = obj.toString();
			
			DataOutputStream wr = new DataOutputStream(request.getOutputStream());
			wr.writeBytes(parameter);
			wr.flush();
			wr.close();
			
			BufferedReader br= new BufferedReader(new InputStreamReader(request.getInputStream()));
			String temp = "";
			StringBuffer buffer = new StringBuffer();
			
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
			}
			
			result = new JSONObject(buffer.toString());
			
			br.close();
			wr.close();
			request.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	public JSONObject getUser(String token){
		JSONObject result = null;
		try {
			URL url = new URL("http://127.0.0.1:8000/users?token="+token);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			
			request.setRequestMethod("GET");
			request.setRequestProperty("Accept", "application/json");
			request.setRequestProperty("Content-Type", "application/json");
			request.setDoOutput(true);
			request.connect();
			
			BufferedReader br= new BufferedReader(new InputStreamReader(request.getInputStream()));
			String temp = "";
			StringBuffer buffer = new StringBuffer();
			
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
			}
			
			result = new JSONObject(buffer.toString());
			System.out.println(result.toString());
			br.close();
			request.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
