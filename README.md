POST http://localhost:1337/plan
	
Sample Request:

	{
    "countryCode" : "BG",
    "perCountryBudget": 100,
    "totalBudget": 1200,
    "currency": "EUR"
	}
	
Sample Response:

	{
    "tourCount": 2,
    "leftover": 200,
    "expenses": [
        {
            "country": {
                "code": "GR"
            },
            "total": 200,
            "currency": "EUR"
        },
        {
            "country": {
                "code": "RO"
            },
            "total": 974.400,
            "currency": "RON"
        },
        {
            "country": {
                "code": "TR"
            },
            "total": 1811.0800,
            "currency": "TRY"
        },
        {
            "country": {
                "code": "MK"
            },
            "total": 200,
            "currency": "EUR"
        },
        {
            "country": {
                "code": "SR"
            },
            "total": 200,
            "currency": "EUR"
        }
    	]
	}