package commons
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When



class BackgroundSteps {

	def pageNameInVnToName(String pageName) {
		switch (pageName) {
			case "Từ điển năng lực":
				return 'competency_url'
		}
	}

	@Given('^User đăng nhập thành công với role (.*)$')
	def login_with_role(String role) {
		try {
			if (WebUI.getUrl().contains(role)) {
				println "Login with role " + role + " successfully"
				return
			}
		} catch (Exception e) {
			WebUI.openBrowser(GlobalVariable.login_url)
		}

		String oldUrl = WebUI.getUrl()
		WebUI.click(findTestObject('Object Repository/Page/Login/btn_login_sso'))
		WebUI.delay(2)
		String currentUrl = WebUI.getUrl()
		if (!currentUrl.contains("login")) {
			// user logged in
			println "Login successfully"
		} else if (currentUrl.contains(GlobalVariable.KC_BASE_URL)) {
			WebUI.setText(findTestObject("Object Repository/Page/Login/input_username"), GlobalVariable.email)
			WebUI.setEncryptedText(findTestObject("Object Repository/Page/Login/input_Password_password"), GlobalVariable.pass)
			WebUI.click(findTestObject('Object Repository/Page/Login/button_Sign In'))
			WebUI.delay(2)
			if (WebUI.getUrl().contains(GlobalVariable.base_url)) {
				println "Login successfully"
			}
		}

		WebUI.navigateToUrl(GlobalVariable.base_url + role)
		WebUI.delay(2)
		if (WebUI.getUrl().contains(role)) {
			println "Login with role " + role + " successfully"
		}
	}

	@Given('User đang ở trang "(.*)"$')
	def open_specific_page(String pageName) {
		String expectUrl = GlobalVariable.base_url + GlobalVariable[pageNameInVnToName(pageName)]
		if (WebUI.getUrl() !== expectUrl) {
			WebUI.navigateToUrl(expectUrl);
			WebUI.delay(2)
			WebUI.verifyMatch(WebUI.getUrl(), expectUrl, false)
		}
	}
}
