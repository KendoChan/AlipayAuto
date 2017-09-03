

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
		//��¼�������ȥ�˵���Ϣ��
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
		
		// -------------������������--------------------
		 System.setProperty("webdriver.firefox.bin", "D:\\firfox\\firefox.exe");
		 WebDriver driver = new FirefoxDriver();
		 
		// -------------����Chrome�����------------------
		//System.setProperty("webdriver.chrome.driver", "E:\\javafiles\\NjuOnSale\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		// ��ȡ��¼ҳ��
		driver.get("https://auth.alipay.com/login/index.htm");
		
		// ��ȡ�û��������
		String username = "15****9620";
		driver.findElement(By.id("J-input-user")).clear();
		for(int i = 0, len = username.length(); i < len; i++){//�����û�����ÿ��500ms����һ���ַ�������������¼ʧ��
			driver.findElement(By.id("J-input-user")).sendKeys(username.charAt(i) + "");
			sleep(500);			
		}		
		sleep(500);
		
		// ��ȡ���������
		driver.findElement(By.id("password_rsainput")).clear();
		sleep(1000);
		String password = "123****";
		for(int i = 0, len = password.length(); i < len; i++){//��ֹ��������
			driver.findElement(By.id("password_rsainput")).sendKeys(password.charAt(i) + "");
			sleep(500);			
		}
		sleep(2000);

		//��ȡ��¼��ť
		WebElement login_button = driver.findElement(By.id("J-login-btn"));
		sleep(2000); //�ڻ�ȡ��¼��ť�͵����¼��ť֮����2s
		login_button.click();
		
		//�ȴ�20s�����������ֻ���֤��
		sleep(20000);//����������֤��
		
		// ��ת�����׼�¼����
		driver.get("https://consumeprod.alipay.com/record/index.htm");
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
