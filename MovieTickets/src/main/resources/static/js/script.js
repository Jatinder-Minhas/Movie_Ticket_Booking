/**
 * 
 */
 
var isStudent = document.getElementById("student");
var orgPrice = document.getElementById("price").textContent;
 
isStudent.onclick = function()
{
    if(document.getElementById("student").checked == true)
    {
    	
    	if(orgPrice > 5)
    	{
      		document.getElementById("price").innerHTML = 8; 
        	document.getElementById("input").value = 8;
       	}
    }
    else
    {
        document.getElementById("price").innerHTML = orgPrice;
        document.getElementById("input").value = orgPrice;
    }
}

function validateForm() 
{
  	var x = document.getElementById("dateS").selectedIndex;
  	
  	if (x == 0) 
  	{
    	document.getElementById("missing").style.display="block";
    	return false;
  	}
  	else
  	{
  		document.getElementById("missing").style.display="none"
  		return true;
  	}
}