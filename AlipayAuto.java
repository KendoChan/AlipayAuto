package com.onsale.nju;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AlipayAuto {
	private static WebDriver driver;

	public static void main(String[] args) {
		login();
		String oppositeUser = getOppositeUser("20150826110500100010740029003925");
		System.out.println("���׷��Է���Ϣ��" + oppositeUser);
		oppositeUser = getOppositeUser("20150720110500100010740025980311");
		System.out.println("���׷��Է���Ϣ��" + oppositeUser);
		oppositeUser = getOppositeUser("2015081521001004740064396260");
		System.out.println("���׷��Է���Ϣ��" + oppositeUser);

	}

	// �����Լ������߷��������������
	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	// ��¼���������𽫽�����ת�����׼�¼����
	private static void login() {
		// ������������
		 System.setProperty("webdriver.firefox.bin", "D:\\firfox\\firefox.exe");
		 WebDriver driver = new FirefoxDriver();
		// ����Chrome�����
//		System.setProperty("webdriver.chrome.driver", "E:\\javafiles\\NjuOnSale\\chromedriver.exe");
//		driver = new ChromeDriver();
		// ��ȡ��¼ҳ��
		driver.get("https://auth.alipay.com/login/index.htm");
		// ��ȡ�û��������
		
		String username = "15056919620";
		driver.findElement(By.id("J-input-user")).clear();
		for(int i = 0, len = username.length(); i < len; i++){
			driver.findElement(By.id("J-input-user")).sendKeys(username.charAt(i) + "");
			sleep(500);			
		}		
		//driver.findElement(By.id("J-input-user")).sendKeys("15056919620");
		// ��Ϣ500ms�������ٶ�̫�죬�Ὣ����������䵽�û����������
		sleep(500);
		// ��ȡ���������
		driver.findElement(By.id("password_rsainput")).clear();
		sleep(1000);
		String password = "123qwertyuiopp??";
		for(int i = 0, len = password.length(); i < len; i++){
			driver.findElement(By.id("password_rsainput")).sendKeys(password.charAt(i) + "");
			sleep(500);			
		}

		// ��Ϣ8��ȴ��û�������֤��
		sleep(2000);
		// ��ǰURL 0
		// ��https://authsu18.alipay.com/login/certCheck.htm?goto=https%3A%2F%2Fwww.alipay.com%2F
		System.out.println("��ǰURL 0 ��" + driver.getCurrentUrl());
		//driver.get("https://www.alipay.com/");
		// ��������û���¼
		//driver.findElement(By.id("J-login-btn")).click();
		WebElement login_button = driver.findElement(By.id("J-login-btn"));
		sleep(2000); 
		login_button.click();
		sleep(20000);//����������֤��
		//driver.findElement(By.className("personal-login")).click();
		// ��ǰURL 1 ��https://www.alipay.com/
		System.out.println("��ǰURL 1 ��" + driver.getCurrentUrl());
		sleep(2000);
		//WebElement myAlipay = driver.findElement(By.className("am-button-innerNav,button-myalipay"));
		//System.out.println("myAlipay isSelected ��" + myAlipay.isSelected());// false
		//System.out.println("myAlipay isEnabled ��" + myAlipay.isEnabled());// true
		//System.out.println("myAlipay isDisplayed ��" + myAlipay.isDisplayed());// true
		// ��������ҵ�֧������ť
		//driver.findElement(By.className("am-button-innerNav,button-myalipay")).click();
		// ��ǰURL 2 ��https://my.alipay.com/portal/i.htm
		System.out.println("��ǰURL 2 ��" + driver.getCurrentUrl());
		// boolean selected1 =
		// driver.findElement(By.className("fn-ml10")).isSelected();
		// System.out.println("��֧��ϸ�Ƿ�ѡ�У�" + selected1);
		// org.openqa.selenium.NoSuchElementException: no such keyword: Element
		// was not in a form, so could not submit.
		// driver.findElement(By.className("fn-ml10")).submit();//��ת��֧��ϸ
		// Exception in thread "main" org.openqa.selenium.WebDriverException:
		// unknown error: Element is not clickable at point
		// driver.findElement(By.xpath("//*[@id=\"J-trend-consume\"]/div/div[1]/div/a[1]")).click();
		// driver.findElement(By.className("fn-ml10")).click();
		// �޷�Ӧ��������һֱ������������
		// WebDriverWait webDriverWait = new WebDriverWait(driver, 3);
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"J-trend-consume\"]/div/div[1]/div/a[1]")));
		// ��ת����֧��ϸ����
		// driver.get("https://xlab.alipay.com/consume/record/items.htm");
		// ��ת�����׼�¼����
		driver.get("https://consumeprod.alipay.com/record/index.htm");
		String currentUrl = driver.getCurrentUrl();
		// ��ǰURL 3 ��https://consumeprod.alipay.com/record/advanced.htm
		System.out.println("��ǰURL 3 ��" + currentUrl);
		sleep(1000);
	}

	// ��ȡ���׶Է���Ϣ
	private static String getOppositeUser(String transactionNo) {
		// ��ȡ�ؼ��ֶ�Ӧ��������
		WebElement keywordInput = driver.findElement(By.id("J-keyword"));
		keywordInput.clear();
		keywordInput.sendKeys(transactionNo);
		WebElement keywordSelect = driver.findElement(By.id("keyword"));
		List<WebElement> options = keywordSelect.findElements(By.tagName("option"));
		// until������ʾֱ���ɵ��ٵ�
		// WebElement selectElement = wait.until(ExpectedConditions
		// .visibilityOfElementLocated(By.id("keyword")));
		// ��Ҫִ��JavaScript��䣬����ǿתdriver
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Ҳ������ô��setAttribute("style","");
		js.executeScript("document.getElementById('keyword').style.display='list-item';");
		js.executeScript("document.getElementById('keyword').removeAttribute('smartracker');");
		js.executeScript("document.getElementById('keyword').options[1].selected = true;");
		js.executeScript("document.getElementById('J-select-range').style.display='list-item';");
		// ���ý���ʱ��ѡ��
		Select selectTime = new Select(driver.findElement(By.id("J-select-range")));
		selectTime.selectByIndex(3);// ѡ�е������������
		System.out.println("selectTime.isMultiple() : " + selectTime.isMultiple());
		// ���ùؼ���ѡ��
		Select selectKeyword = new Select(driver.findElement(By.id("keyword")));
		// selectKeyword.selectByValue("bizInNo");//�˴���value��д<option>��ǩ�е�valueֵ
		selectKeyword.selectByIndex(1);// ѡ�е��ǽ��׺�
		System.out.println("selectKeyword.isMultiple() : " + selectKeyword.isMultiple());
		WebElement queryButton = driver.findElement(By.id("J-set-query-form"));// �õ�������ť
		// ���������ť
		queryButton.submit();
		WebElement tr = driver.findElement(By.id("J-item-1"));// �Ȼ�ȡtr
		WebElement td = tr.findElement(By.xpath("//*[@id=\"J-item-1\"]/td[5]/p[1]"));
		return td.getText();
	}
}
