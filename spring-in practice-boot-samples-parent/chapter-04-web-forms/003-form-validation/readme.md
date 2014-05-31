# 003-form-validation

This is the third example of SiP Chapter 4 - Basic Web Forms:
	Validating Form Data
It has been migrated to Spring Boot and Java Config.		
				
## Functionality
It is based on project 003- but includes two types of backend validations:
* Field Filtering: ensure that all submitted field names are expected (Spring's @InitBinder).
* Field Validation: ensure that all submitted fields values allow validation rules (JSR303 + Hibernate Validator)

If there are errors, informational messages are included to help the user understand what when wrong and how to address the issue.

	
## Components (added/changed over 002-)

### AccountController.java
The class now includes a method annotated with @InitBinder which whitelists all the fields that are allowed to be bound to the Controller. Therefore, if a parameter that is not listed here is sent it will be ignored, even when the receiving bean has a field with that name.
* processingRegistrationForm(): this AccountForm method is annotated with both @ModelAttribute("account") to customize the name of the model attribute from accountForm to account. Additionally, the parameter is also annotated with @Valid to trigger the validation of the AccountForm bean.
Additionally, a new parameter BindingResult bindingResult is added to the method in which the result of the bean validation errors will be accumulated.
Inside the method, the BindingResult is sent to a method that inspects the globalErrors inside that BindingResult object and checks whether the default message is equal to "account.password.mismatch.message". If that is the case it will mean the @ScriptAssert rule would have failed. If that is the case, and no other errors are associated to the password field, the error key "error.mismatch" is added to the BindingResult and associated to the password field.
Therefore, in application.properties an entry will have to be defined for error.mismatch.account.password.
					

### i18n/messages.properties
Added entries for the Spring Managed errors:
* error.global: message to be displayed when any error is found
* error.mismatch.account.password: message to be displayed when the password field doesn't match the confirmation password field.

### accounts/registrationForm.jsp
Heavily modified to display validation errors:
First of all, a small style section is added on the head section to define the error class.
Then, a form:errors section is included to display the error.global. This will be displayed when any validation error is found.
After that, for each of the fields is included a form:errors element that will automatically display the error associated to the field identified with the path attribute.
