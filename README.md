# 欢迎查阅Appium（自动化测试框架体系）
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

### Appium
    Appium是一个移动端的自动化框架，可用于测试原生应用，移动网页应用和混合型应用，且是跨平台的，可用于IOS和Android以及Firefox OS的操作系统
        •  原生的应用是指用Android或IOS的SDK编写的应用
        •  移动网页应用是指网页应用，例如IOS中Safari，Chrome等浏览器的应用。
        •  混合应用是指一种包裹WebView的应用，原生应用于网页内容交互性的应用，例如狼人杀排行榜、红包等
        •  其中最重要的是Appium是跨平台的，何为跨平台，意思就是可以针对不同的平台用一套API来编写测试用例

 ---
### 框架介绍
    Java + Appium + Maven + TestNG + Git + +Ant + +Allure + Jenkins 
        •  使用Java作为项目编程语言
        •  使用Appium作为App项目底层服务驱动框架
        •  使用Maven作为项目类型，方便管理JAR包
        •  使用TestNG作为项目运行框架，方便执行测试用例
        •  使用Git作为仓库管理工具，方便管理项目代码
        •  使用Ant作为Java的build打包工具，方便项目代码打包
		•  使用Allure作为测试模板，方便生成测试报告
        •  使用Jenkins作为自动化持续集成平台，方便自动编译，自动打包，自动运行测试脚本，邮件发送测试报告
		•  使用PageObject设计模式，可以以页面为单位，将元素及动作的细节封裝起來，隐藏测试脚本细节，结构变更后也更容易修改
		
 ---
### 主要功能
    1.  实现了基于Appium，WebDriver等常用操作方法的二次封装，包括（滑动，点击，输入，元素定位）等，使用起来更简便
    2.  实现了基于Windows，Android，IOS操作系统的cmd，adb，terminal常用DOS命令的快速调用
    3.  实现了基于Windows，Android，IOS操作系统等键盘按键功能的调用，可模拟实际的键盘操作
    4.  实现了基于Appium的断言功能，检查点失败自动截图保存，可在测试报告中查看，一个检查点失败不影响后续用例执行
    5.  实现了基于Xml文件内容的基本解析，可存储和读取设备和用例信息
    6.  实现了基于PO的设计模式，以页面为单位，将元素及动作的细节封裝起來，方便维护
    8.  实现了基于Allure，TestNG生成的测试报告二次美化功能，界面更美观，内容清晰

 ---
### 环境配置
   1. [JDK1.7以上](http://www.Oracle.com/technetwork/Java/javase/downloads/index.html)
   2. [Eclipse](http://www.eclipse.org/downloads)/[IDEA](https://www.jetbrains.com/idea/)
   3. [Android SDK](http://www.androiddevtools.cn) 
   4. [Appium](https://github.com/appium/appium-desktop/releases/tag/v1.15.1)   npm -g install appium
   5. [Maven](http://maven.apache.org/download.cgi)
   6. [Git](https://git-scm.com/) 
   7. [Ant](https://ant.apache.org) 
   8. [Allure](https://pan.baidu.com/s/1fneedXGBD9iBJOJuzorBiQ)   提取码：zovf     brew install allure
   9. [Jenkins](https://jenkins.io) 
   
 - 部分网站需要翻墙，具体安装参考：ftp://test/李红娜文件夹/文档/Appium环境搭建-MAC系统.docx
 
 ---
### 注意事项
 - 工程项目编码需要设置成UTF-8，否则会出现中文乱码情况

 ---
### 一、创建PageObject类，例如【NumberLoginPage.java】
	public class NumberLoginPage extends LoginPage {
	
		public static Logger log = Logger.getLogger(NumberLoginPage.class);
		
		public NumberLoginPage(AppiumDriver driver) {
			super(driver);
			// TODO Auto-generated constructor stub
		}
		// find 手机号登录页面title  by xpath (RN)
		public static final String PHONELOGIN_TITLE = "//*[contains(@text,'手機號登錄')  or contains(@text,'手机号登录')]";
		// find 号码输入框   by xpath (RN)
		public static final String PHONENUMBER_XPATH = "//*[contains(@text,'輸入手機號')  or contains(@text,'输入手机号')]";    
		// find 密码输入框  by xpath (RN)
		public static final String PASSWORD_XPATH = "//*[contains(@text,'請輸入密碼')  or contains(@text,'请输入密码')]";    
		// find 登录按钮 by xpath (RN)
		public static final String LOGIN_XPATH = "//*[contains(@text,'登錄')  or contains(@text,'登录')]";
		// find 国家/地区  by xpath (RN)
		public static final String COUNTRY_XPATH = "//*[contains(@text,'國家')  or contains(@text,'国家')]";    
		public static final String COUNTRY_CHINA_XPATH = "//*[contains(@text,'中國')  or contains(@text,'中国')]";    
		public static final String COUNTRY_A_XPATH = "//*[(@text='A')]";           
		
		//输入手机号    
		public void inputPhoneNumber(String pNumer) {
			WebElement phone = FindXpath(PHONENUMBER_XPATH);
			phone.sendKeys(pNumer);
		}
		
		//输入密码
		public void inputPassword(String pWord) {
			WebElement password = FindXpath(PASSWORD_XPATH);			
			password.sendKeys(pWord);
		}
		
		//点击登录按钮
		public void clickLoginButton() {
			WebElement login = FindXpath(LOGIN_XPATH);			
			press(login);
		}	
		
		//修改国家/地区
		public void clickChangeCountry() {
			if(isElementsExist(By.xpath(COUNTRY_CHINA_XPATH))) {
				return;
			}		
			WebElement country = FindXpath(COUNTRY_XPATH);	
			press(country);		
			if(isElementsExist(By.xpath(COUNTRY_A_XPATH))) {			
				swipePageUtilFoundElement("中", "UP", 5);			
				WebElement china = FindXpath(COUNTRY_CHINA_XPATH);	
				press(china);
			}
		}	
	}

### 二、创建测试用例脚本，例如【NumberLogin.java】
	public class NumberLogin extends BaseTest{
	
		private static AppiumDriver driver;
		@Feature("登录")
		@Stories(value = { @Story(value = "手机号码登录")})
		@Description("正常登录")
		@Step("测试步骤：1、进入手机登录页面；2、切换国家；3、输入手机号{1}；4、输入密码{2}；5、点击登录")
		@Parameters({"udid","pNum","passWrod"})
		@Test(priority = 0)
		public void test_login(String udid,String pNum,String passWrod) throws InterruptedException{
			System.out.println("PhoneLogin * 正常登录 * " + udid);
			driver = getDriver(udid);		
			NumberLoginPage pl = new NumberLoginPage(driver);
			// 进入手机登录页面
			pl.toPhoneLogin();
			// 切换国家/地区
			pl.clickChangeCountry();
			// 输入手机号
			pl.inputPhoneNumber(pNum);
			// 输入密码
			pl.inputPassword(passWrod);
			// 点击登录
			pl.clickLoginButton();		
			// 允许权限
			pl.allowPermissionPopup(); 
			// 设置悬窗
			pl.cancelSuspendedpermissionPopup(); 			
			// 断言-进入首页
			boolean flag = pl.isElementsExist(By.xpath("//*[contains(@text,'ID:')]"));
			Asserts.isFoundElement(flag);				
		}		
	}

### 三、创建testng.xml

	<?xml version="1.0" encoding="UTF-8"?><!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
	<suite name="Suite">
		
	  <listeners>
		  <listener class-name="com.orangelab.listener.AllureReporterListener" />
	  </listeners>    
	  <test name="192.168.30.101:5555">
		<parameter name="port" value="4725"/>
		<parameter name="platformVersion" value="7.1.1"/>
		<parameter name="udid" value="192.168.30.101:5555"/>
		<parameter name="pNum" value="15210047015"/>
		<parameter name="newNum" value="300001"/>
		<parameter name="passWrod" value="111111"/>
		<parameter name="passWrod2" value="123456"/>
		<classes>
		  <class name="com.orangelab.testcase.login.NumberLogin"/>      
		</classes>
	  </test>
	   
	</suite> 

	[注意] test name 的值 = udid的值
	
### 四、Appium服务配置    

	//设置自动化相关参数:
	DesiredCapabilities capabilities = new DesiredCapabilities();
	//设置Appium测试引擎:
	capabilities.setCapability("device", "uiautomator2");
	//指定测试设备系统及系统版本:
	capabilities.setCapability("platformName", PlatformName);
	capabilities.setCapability("platformVersion", PlatformVersion);	
	//指定测试设备名称及设备ID:
	capabilities.setCapability("deviceName", DeviceID);
	//初始化APP缓存，false(初始化)/true(不初始化)
	capabilities.setCapability("noReset", true);
	//重新安装APP，true(重新安装)/false(不重新安装)
	capabilities.setCapability("fullReset", false);	
	//启动时是否覆盖session，true(覆盖)/false(不覆盖)
	capabilities.setCapability("sessionOverride", false);
	//开启中文输入，安装Unicode输入法，true(安装)/false(不安装)
	capabilities.setCapability("unicodeKeyboard", true);
	//还原系统默认输入法，true(还原)/false(不还原)
	capabilities.setCapability("resetKeyboard", true);
	//设置Appium超时时间:
	capabilities.setCapability("newCommandTimeout", 60000);
	//APK重新签名，false(重签)/true(不重签)
	capabilities.setCapability("noSign", true);
	//已安装后启动APP
	capabilities.setCapability("app", apk.getAbsolutePath());
	//初始化AndroidDriver
	driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
	//命令启动Appium Service
	//node C:\Users\King-liu\AppData\Local\Programs\Appium\resources\app\node_modules\appium\build\lib\main.js --address 127.0.0.1 --port 4723
	//appium -a 127.0.0.1 -p 4723 -U DeviceID


 - 测试执行时需要指定DeviceName，PlatformName，PlatformVersion, 端口号等信息，DeviceName通过命令adb devices获取
 
### 五、执行用例
 - mvn clean
 - mvn test
 - 也可以在eclipse中右击testng.xml选择RUNAS->TestNG
 - 或右击pom.xml选择RUNAS->maven test

### 六、测试报告
 - 测试报告分为两种，一种是TestNG自带的TestngReport测试报告，另外一种则是调用Allure生成的报告，第二种更加美观 

### 七、Jnekins持续集成：
 - 搭建Jenkins环境，具体请参考: https://blog.csdn.net/wuxuehong0306/article/details/50016547
 - 配置Jenkins自动化持续集成项目，即可实现远程服务器自动（构建，编译，打包）运行脚本，发送邮件测试报告等
 - 参考：ftp://test/李红娜文件夹/文档/Jenkins配置.docx


### 八、脚本编写原则
	1、一个脚本只验证一个功能点
	2、尽量只做功能中正向逻辑的验证，不要考虑太多逆向逻辑的验证
	3、脚本之间尽量不要产生关联性，也就是说编写的每一个脚本都是独立的，不能依赖或影响其他脚本。
	4、如果对数据进行了修改，需要对数据进行还原。
	5、在整个脚本中只对验证点进行验证，不要对整个脚本每一步都做验证


### They are based on the sources in
 - autotest_wolf：(https://lhn0826@bitbucket.org/lhn0826/autotest_wolf.git) on bitbucket
 
 

作者：lihongna         
QQ：335032076 

