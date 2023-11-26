function selectDomain(){
	
	var domainselect = document.getElementById("domain_list");
	
	var domainValue = domainselect.options[document.getElementById("domain_list").selectedIndex].value;
	document.getElementById("domain_txt").value = domainValue;
	var domaintxt = document.getElementById("domain_txt").value;
	
	if(domaintxt != ""){
		document.getElementById("domain_txt").setAttribute("value", domaintxt);
		document.getElementById("email").setAttribute("value", document.getElementById("email1").value + "@" + domaintxt);
	}
	if(domaintxt == ""){
		document.getElementById("domain_txt").setAttribute("value", domaintxt);
		document.getElementById("domain_txt").removeAttribute("disabled");
	}
};