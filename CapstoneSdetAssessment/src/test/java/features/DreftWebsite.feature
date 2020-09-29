Feature: Dreft Application User Validation

Scenario: Load Test Data Before Starting The Test Execution
Given Read The TestData

Scenario Outline: Registration Login Forgot and Reset Password Validation
Given Initialize the Webdriver
When launch the Application URL <siteUrl> and <siteRegion>
Then Click Register Button in HomePage
Then Enter the Registration details
And Login To Account
And LogOut of the Account
Then Verify Password Reset Function
And Quite The Webdriver

Examples:
|siteUrl	|siteRegion	|
|https://dreft.com/en-us	|USEnglish|
|https://www.dreft.com/es-us |USSpanish|
