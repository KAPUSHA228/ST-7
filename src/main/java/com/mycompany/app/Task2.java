package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


public class Task2 
{
    public static void main( String[] args )
    {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
		 try {
            webDriver.get("https://api.ipify.org/?format=json");
            WebElement pre = webDriver.findElement(By.tagName("pre"));
            
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(pre.getText());
            
            String ip = (String) json.get("ip");
            System.out.println("Ваш IP-адрес: " + ip);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webDriver.quit(); 
        }
    }
}
