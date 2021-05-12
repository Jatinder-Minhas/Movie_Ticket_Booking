var isStudent = document.getElementById("studentCB");
 
isStudent.onclick = function()
{
    if(isStudent.checked == true)
    {
      	document.getElementById("college").style.display="block";
    }
    else
    {
        document.getElementById("college").style.display="none";
    }
}

function validateForm() 
{
  	var x = document.getElementById("collegeS").selectedIndex;
  	
  	if (x == 0 && isStudent.checked == true) 
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