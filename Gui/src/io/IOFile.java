/**
 * 
 */
package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Giang Nguyễn
 *
 * Nhìn cái giề, đọc code kìa!
 */
public class IOFile {
	
	public List<String> readData(String path){
		List<String> list = new ArrayList<>();
		FileReader fr;
		try {
			fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null){
				list.add(line);
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void writeData(List<String> list, String path){
		
		try {
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i<list.size();i++){
				bw.write(list.get(i));
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
