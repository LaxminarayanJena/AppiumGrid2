package appiumTest;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class preinstalled {
	
	protected AppiumDriver<MobileElement> driver;

	@Parameters({"device","apppackage","activity","appiumServer"})
	@BeforeClass
	public void deviceSetUp(String device, String apppackage, String activity, String appiumServer) throws InterruptedException, MalformedURLException, InterruptedException
		{
			
			System.out.println("*****************************************************");
			System.out.println("Setting up device and desired capabilities");		
			
			DesiredCapabilities cap = DesiredCapabilities.android();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME,device);	
			cap.setCapability(MobileCapabilityType.UDID,device);
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
	    	cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,apppackage);
	    	cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,activity);
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME,Platform.ANDROID);
			cap.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.ANDROID);
		

			URL url= new URL(appiumServer);
			driver = new AndroidDriver<MobileElement>(url,cap);	
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		
			@Test
			public void preinstalledapp() throws InterruptedException 
			{	
				try
				{
					System.out.println("*****************************************************");
					System.out.println("Test execution started");		
					
					Thread.sleep(1000);
					driver.findElementById("io.selendroid.testapp:id/my_text_field").sendKeys("hello");
					}	
				catch(NoSuchElementException e)
				{
					e.printStackTrace();
				}
			}
			
			@AfterClass
			public void tearDown() throws Exception
			{
				if(driver!=null)
				{
					System.out.println("******************************************************");
					System.out.println("Destroying Test Environment");
					driver.quit();
				}
			}
		}
