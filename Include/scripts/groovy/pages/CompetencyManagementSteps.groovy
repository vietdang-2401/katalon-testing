package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.JavascriptExecutor

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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import CustomKeywords
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import internal.GlobalVariable

class CompetencyManagementSteps {

	@Given('Bộ lọc đang ở chế độ mặc định$')
	def resetSearchToDefault() {
		WebUI.setText(findTestObject('Object Repository/Page/Competency/Search/input_search_name'), '')
		WebUI.click(findTestObject('Object Repository/Page/Competency/Search/select_search_core'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/Page/Competency/Search/select_search_core_option', ['index': '[1]']), 0)
		WebUI.click(findTestObject('Object Repository/Page/Competency/Search/select_search_core_option', ['index': '[1]']))
		TestObject checkAllASK = findTestObject("Object Repository/Page/Competency/Search/checkbox_nangluc_all")
		if (!WebUI.findWebElement(checkAllASK, 0).isSelected())
			WebUI.click(checkAllASK)
	}

	@When('Trang từ điển năng lực được tải thành công')
	def verifyCompetencyPageLoaded() {
		WebUI.verifyElementVisible(findTestObject('Object Repository/Page/Competency/Search/input_search_name'))
	}

	@When('Admin lọc theo tên "(.*)"$')
	def searchCompetencyByName(String name) {
		WebUI.setText(findTestObject('Object Repository/Page/Competency/Search/input_search_name'), name)
	}

	@And('Danh sách chỉ hiển thị các năng lực có tên chứa "(.*)"$')
	def verifyCompetencyNameContains(String name) {
		boolean haveData = WebUI.verifyElementPresent(
				findTestObject('Object Repository/Common/Table/1st_row'),
				10,
				FailureHandling.OPTIONAL
				)

		if (!haveData) {
			if (WebUI.verifyElementPresent(findTestObject("Object Repository/Common/Table/div_nodata"), 3)) {
				KeywordUtil.markPassed("no-data")
			} else {
				KeywordUtil.markFailedAndStop("error occur")
			}
		} else {
			TestObject rows = findTestObject("Object Repository/Common/Table/data/1st_col_data")
			WebUI.findWebElement(rows).each { el ->
				if (!el.getText().contains(name))
					KeywordUtil.markFailed("Filter by name error")
			}
		}
	}

	@When('Admin lọc theo "Chỉ Core"')
	def filterByCore() {
		WebUI.click(findTestObject('Object Repository/Page/Competency/Search/select_search_core'))
		WebUI.click(findTestObject('Object Repository/Page/Competency/Search/select_search_core_option', ['index': '[2]']))
	}

	@When('Admin lọc theo loại năng lực "(.*)"$')
	def selectCompetencyType(String type) {

		TestObject checkAllASK = findTestObject("Object Repository/Page/Competency/Search/checkbox_nangluc_all")
		if (WebUI.findWebElement(checkAllASK, 0).isSelected())
			WebUI.click(checkAllASK)

		TestObject typeObject = null
		switch(type) {
			case 'Kỹ năng (S)':
				typeObject = findTestObject('Object Repository/Page/Competency/Search/checkbox_nangluc_kinang')
				break
			case 'Kiến thức (K)':
				typeObject = findTestObject('Object Repository/Page/Competency/Search/checkbox_nangluc_kienthuc')
				break
			case 'Thái độ (A)':
				typeObject = findTestObject('Object Repository/Page/Competency/Search/checkbox_nangluc_thaido')
				break
			default:
				typeObject = findTestObject('Object Repository/Page/Competency/Search/checkbox_nangluc_all')
				break
		}

		if (typeObject != null) {
			WebUI.click(typeObject)
		}
	}

	@Then('Danh sách chỉ hiển thị các năng lực Core')
	def verifyCoreCompetenciesOnly() {
		boolean haveData = CustomKeywords.'common.Table.haveData'()

		if (!haveData) {
			if (WebUI.verifyElementPresent(findTestObject("Object Repository/Common/Table/div_nodata"), 3)) {
				KeywordUtil.markPassed("no-data")
			} else {
				KeywordUtil.markFailedAndStop("error occur")
			}
		} else {
			TestObject rows = findTestObject("Object Repository/Common/Table/data/3rd_col_data")
			WebUI.findWebElement(rows).each { el ->
				if (!el.getText().contains('Core'))
					KeywordUtil.markFailed("Filter by core error")
			}
		}
	}

	@Then('Danh sách chỉ hiển thị các năng lực loại "(.*)"$')
	def verifyCompetencyType(String typeCode) {
		boolean haveData = CustomKeywords.'common.Table.haveData'()

		String testString = ''
		switch (typeCode) {
			case 'S':
				testString = 'Kỹ năng'
				break
			case 'A':
				testString = 'Thái độ'
				break
			case 'K':
				testString = 'Kiến thức'
				break
			default:
				testString = ''
				break
		}

		if (!haveData) {
			if (CustomKeywords.'common.Table.haveNoDataMessage'()) {
				KeywordUtil.markPassed("no-data")
			} else {
				KeywordUtil.markFailedAndStop("error occur")
			}
		} else {
			TestObject rows = findTestObject("Object Repository/Common/Table/data/2nd_col_data")
			WebUI.findWebElement(rows).each { el ->
				if (!el.getText().contains(testString))
					KeywordUtil.markFailed("Filter by type error")
			}
		}
	}

	@Then('Danh sách chỉ hiển thị các năng lực thỏa mãn tất cả điều kiện')
	def verifyCombinedFilters() {
		verifyCoreCompetenciesOnly()
		verifyCompetencyType("S")
		verifyCompetencyNameContains("Giao tiếp")
	}

	@Given('Admin đã áp dụng các bộ lọc')
	def applyFilters() {
		searchCompetencyByName("Test")
		filterByCore()
		selectCompetencyType("Kỹ năng (S)")
	}

	@Given('Admin đã áp dụng bộ lọc ngẫu nhiên')
	def applyOneFilter() {
		searchCompetencyByName("NonExistentData-randommm")
	}

	@When('Admin click nút xóa bộ lọc')
	def clickClearFilter() {
		WebUI.click(findTestObject('Object Repository/Page/Competency/button_clearSearch'))
	}

	@Then('Tất cả bộ lọc được reset về mặc định')
	def verifyFiltersReset() {
		WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Page/Competency/Search/input_search_name'), 'value', '', 2)

		String selectedValue = WebUI.getAttribute(
				findTestObject('Object Repository/Page/Competency/Search/select_search_core'),
				'value'
				)
		WebUI.verifyMatch(selectedValue, 'Tất cả', false)

		WebUI.verifyElementChecked(findTestObject('Object Repository/Page/Competency/Search/checkbox_nangluc_all'), 0)
	}

	@And('Danh sách hiển thị lại tất cả năng lực')
	def verifyAllCompetenciesDisplayed() {
		assert CustomKeywords.'common.Table.haveData'() == true
	}

	@When('Không có dữ liệu phù hợp')
	def noMatchingData() {
		String accessToken = CustomKeywords.'common.Api.getNewAccessToken'()
		def data = CustomKeywords.'common.Api.getResponseData'(findTestObject('Object Repository/Api/Competency/GetList', ['access_token': accessToken]))
		Integer listSize = data.list.size()
		WebUI.verifyEqual(listSize, 0)
	}

	@Then('Hiển thị thông báo không có kết quả')
	def verifyNoDataMessage() {
		WebUI.verifyElementVisible(findTestObject('Object Repository/Common/Table/div_nodata'))
	}

	@And('Không hiển thị bảng danh sách')
	def verifyTableNotVisible() {
		WebUI.verifyElementVisible(findTestObject('Object Repository/Common/Table/div_nodata'))
	}

	@And('Không hiển thị bảng danh sách năng lực')
	def verifyCompetencyTableNotVisible() {
		verifyTableNotVisible()
	}

	@Then('Hiển thị bảng danh sách năng lực')
	def verifyCompetencyTableVisible() {
		WebUI.verifyElementVisible(findTestObject('Object Repository/Common/Table/1st_row'))
	}

	@And('Mỗi dòng hiển thị thông tin năng lực cơ bản')
	def verifyRowContent() {
		WebUI.verifyElementVisible(findTestObject('Object Repository/Common/Table/1st_row'))
	}

	@And('Có phân trang')
	def verifyPaginationVisible() {
		WebUI.verifyElementVisible(findTestObject('Object Repository/Common/Table/pagination/div_pagination'))
	}

	@And('Mặc định hiển thị trang đầu tiên')
	def verifyDefaultFirstPage() {
		// Placeholder
	}

	@And('Không hiển thị phân trang')
	def verifyPaginationNotVisible() {
		WebUI.verifyElementNotVisible(findTestObject('Object Repository/Common/Table/pagination/div_pagination'))
	}

	@When('Admin chuyển sang trang tiếp theo')
	def navigateNextPage() {
		CustomKeywords.'common.Pagination.goToNextPage'()
	}

	@Then('Hiển thị dữ liệu trang tiếp theo')
	def verifyNextPageData() {
		WebUI.delay(1)
	}

	@And('Nút quay lại trang trước khả dụng')
	def verifyPrevButtonEnabled() {
		WebUI.verifyElementClickable(findTestObject('Object Repository/Common/Table/pagination/button_prev_page'))
	}

	@When('Admin quay lại trang trước')
	def navigatePrevPage() {
		CustomKeywords.'common.Pagination.backToPrevPage'()
	}

	@Then('Hiển thị dữ liệu trang trước đó')
	def verifyPrevPageData() {
		WebUI.delay(1)
	}

	// --- CRUD Steps ---

	@Given('Admin đang ở trang thêm mới năng lực')
	def openCreateCompetencyPage() {
		WebUI.click(findTestObject('Object Repository/Page/Competency/button_themNangLuc'))
	}

	@When('Admin nhập đầy đủ thông tin năng lực hợp lệ')
	def fillValidCompetencyInfo() {
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/input_ten_nang_luc'), 'test')
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/textarea_Level 1_behavior'), 'level1')
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/textarea_Level 2_behavior'), 'level2')
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/textarea_Level 3_behavior'), 'level3')
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/textarea_Level 4_behavior'), 'level4')
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/textarea_Level 5_behavior'), 'level5')
	}

	@And('Admin click nút lưu/cập nhật$')
	def clickSaveButton() {
		WebUI.click(findTestObject("Object Repository/Page/Competency/FormModal/button_create_or_edit"))
	}

	@And('Hiển thị thông báo thêm mới thành công')
	def verifyCreateSuccessMessage() {
		CustomKeywords.'common.Notify.verifySuccessMessage'()
	}

	@Given('Admin đang ở trang chỉnh sửa năng lực')
	def openEditCompetencyPage() {
		WebUI.findWebElements(findTestObject('Object Repository/Common/Table/button_edit'), 10)[0].click()
	}

	@When('Admin thay đổi thông tin năng lực')
	def editCompetencyInfo() {
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/input_ten_nang_luc'), 'test')
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/textarea_Level 1_behavior'), 'level1')
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/textarea_Level 2_behavior'), 'level2')
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/textarea_Level 3_behavior'), 'level3')
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/textarea_Level 4_behavior'), 'level4')
		WebUI.setText(findTestObject('Object Repository/Page/Competency/FormModal/textarea_Level 5_behavior'), 'level5')
	}

	@Then('Thông tin năng lực được cập nhật thành công')
	def verifyCompetencyUpdated() {
		WebUI.comment('TODO: Verify update')
	}

	@And('Hiển thị thông báo cập nhật thành công')
	def verifyUpdateSuccessMessage() {
		CustomKeywords.'common.Notify.verifySuccessMessage'()
	}

	@Given('Admin chọn năng lực cần xóa')
	def selectCompetencyToDelete() {
		WebUI.focus(findTestObject('Object Repository/Common/Table/1st_row'))
	}

	@When('Admin click nút xóa')
	def clickDeleteButton() {
		WebUI.findWebElements(findTestObject('Object Repository/Common/Table/button_delete'), 10)[0].click()
	}

	@And('Admin xác nhận xóa')
	def confirmDelete() {
		WebUI.click(findTestObject("Object Repository/Page/Competency/button_confirm_delete"))
	}

	@Then('Năng lực bị xóa khỏi danh sách')
	def verifyCompetencyDeleted() {
		WebUI.comment('TODO: Verify deletion')
	}

	@And('Hiển thị thông báo xóa thành công')
	def verifyDeleteSuccessMessage() {
		CustomKeywords.'common.Notify.verifySuccessMessage'()
	}
}
