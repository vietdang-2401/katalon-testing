package common
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



class Notify {
 /**
 * Refresh browser
 */
@Keyword
def verifySuccessMessage() {
	if (WebUI.waitForAlert(5)) {
		WebUI.acceptAlert()
	} else {
		KeywordUtil.markError("Không thấy alert xuất hiện")
	}
}

 
}