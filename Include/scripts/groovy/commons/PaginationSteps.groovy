package commons
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.regex.Pattern

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

import CustomKeywords
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



class PaginationSteps {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Given("Bảng có nhiều hơn một trang dữ liệu")
	def haveMoreThanOnePage() {
		String pagiInfo = WebUI.getText(findTestObject('Object Repository/Common/Table/pagination/span_pagi_info'))
		
		Pattern p = Pattern.compile(/Trang\s+(\d+)\/(?!\1)(\d+)/)
		def matcher = p.matcher(pagiInfo)

		boolean isValid = matcher.find()
		
		if (isValid) {
			KeywordUtil.markPassed("Bảng có nhiều hơn 1 trang")
		} else {
			KeywordUtil.markErrorAndStop("Bảng chỉ có 1 trang")
		}
	}

	@Then("Pagination reset về trang đầu tiên")
	def paginationIsInFirstPage() {
		boolean hasPagination = CustomKeywords.'common.Pagination.isVisible'()

		if (!hasPagination || CustomKeywords.'common.Pagination.isFirstPage'()) {
			KeywordUtil.markPassed("Pagination đã reset về trang 1")
		} else {
			KeywordUtil.markError("Error when reset pagination")
		}
	}
}
