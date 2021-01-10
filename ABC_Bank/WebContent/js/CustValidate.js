/**
 * 
 */
function validateCustomer(){
	var x = document.getElementById('ssn').value;
	if(x=="")
		{
			alert("ssn must be filled out");
			return false;
		}
	x = document.getElementById('name').value;
	if(x=="")
	{
		alert("name must be filled out");
		return false;		
	}
	x = document.getElementById('age').value;
	if(x=="")
	{
		alert("age must be filled out");
		return false;		
	}
	x = document.getElementById('address').value;
	if(x=="")
	{
		alert("address must be filled out");
		return false;		
	}
	x = document.getElementById('state').value;
	if(x=="")
	{
		alert("state must be filled out");
		return false;		
	}
	x = document.getElementById('city').value;
	if(x=="")
	{
		alert("city must be filled out");
		return false;		
	}
	validateUserSSN();
	validateAge();
	validateName();
	
	return true;
}

function validateUserSSN()
{
	var user = document.getElementById("ssn").value;
	if(user.length>9)
		{
		alert("SSN must be 9 digits");
		return false;
		}
	var numbers = /^[0-9]+$/;
	if((!user.value.match(numbers)))
		{
		alert("SSN must be only number");
		return false;
		}
	}
function validateAge()
{
	var user = document.getElementById("age").value;
	if(user>99)
		{
		alert("Age must be below 99 years ");
		return false;
		}
	var numbers = /^[0-9]+$/;
	if((!user.value.match(numbers)))
		{
		alert("SSN must be only number");
		return false;
		}
}

function validateName()
{
		var name = document.getElementById("name");
		var letters = /^[A-Za-z]+$/;
		if((!name.value.match(letters)))
			{
				alert("Name must contain only alphabets");
				return false;
			}
}