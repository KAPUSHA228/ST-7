package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.FileWriter;

public class Task3
{
    public static void main( String[] args )
    {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
		  try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            webDriver.get(url);
            WebElement pre = webDriver.findElement(By.tagName("pre"));
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(pre.getText());
            
            JSONObject hourly = (JSONObject) json.get("hourly");
            JSONArray time = (JSONArray) hourly.get("time");
            JSONArray temperature = (JSONArray) hourly.get("temperature_2m");
            JSONArray rain = (JSONArray) hourly.get("rain");
            
            StringBuilder table = new StringBuilder("№\tДата/время\tТемпература\tОсадки (мм)\n");
            for (int i = 0; i < time.size(); i++) {
                table.append(String.format("%d\t%s\t%.1f°C\t%.2f мм\n",
                        i + 1,
                        time.get(i),
                        (double) temperature.get(i),
                        (double) rain.get(i)));
            }
            
            try (FileWriter file = new FileWriter("result/forecast.txt")) {
                file.write(table.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }
}
