package com.example
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.reader.ExcelFactory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject



public class WebUICustomKeywords {

	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up 
	 * @return true if element present, otherwise false
	 */
	@Keyword
	def isElementPresent(TestObject to, int timeout){
		//Use Katalon built-in function to find elements with time out 1 seconds
		List<WebElement> elements = WebUiBuiltInKeywords.findWebElements(to, timeout)
		return elements.size() > 0
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table 
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}

	/**
	 * Get all cells of HTML table row
	 * @param row a WebElement instance represent for HTML table row 
	 * @param tagName HTML column tag name, usually is TD/TH
	 * @return All cells inside HTML table row 
	 */
	@Keyword
	def List<WebElement> getHtmlTableColumns(WebElement row, String tagName) {
		List<WebElement> selectedColumns = row.findElements(By.tagName(tagName))
		return selectedColumns
	}

	/** Function check email registered or not
	 *  return "true" if e mail registered
	 **/
	@Keyword
	def CheckEmailRegistered(String email) {
		String excelPath = RunConfiguration.getProjectDir() + '/Data Files/Test Data Email.xlsx'
		Object excelData = ExcelFactory.getExcelDataWithDefaultSheet(excelPath, 'Sheet1', true)
		int count = excelData.getRowNumbers()
		for (int i = 1 ; i <= count ; i++){
			if(email == excelData.getValue('EmailRegistered', i)){
				return true
			}
		}
		return false
	}

	@Keyword
	def CheckEmailValid(String email){
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w-]+\\.)+[\\w]+[\\w]"
		return email.matches(regex)
	}


	/**Check input: if data:
	 *  isn't an email -> return 1
	 *  is an email but wasn't an email registered -> return 2
	 * 	was an email registered
	 */

	@Keyword
	def CheckInput(String email) {
		if(CheckEmailValid(email)){
			if(CheckEmailRegistered(email)){
				return '3'
			}
			return '2'
		}
		else{
			return '1'
		}
	}
}