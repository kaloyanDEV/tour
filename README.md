Hit in the browser http://localhost:1337/plan
Copy the Cookie value from browser
In Postman add new http header

Cookie:JSESSIONID=</JSESSION_ID/>
	
Sample Payload:

	{
    "countryCode" : "BG",
    "perCountryBudget": 100,
    "totalBudget": 1200,
    "currency": "EUR"
	}