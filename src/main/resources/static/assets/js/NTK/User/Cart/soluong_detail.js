function minusQuantity() {
	event.preventDefault();
	var quantitys = document.getElementById("soluong").value;
	var intValue = parseInt(quantitys);
	if (intValue > 1) {
		intValue = intValue - 1;
	}
	quantitys = intValue;
	console.log(quantitys);
	document.getElementById("soluong").value = quantitys;
}

function plusQuantity() {
	event.preventDefault();
	var quantitys = document.getElementById("soluong").value;
	var intValue = parseInt(quantitys);
	intValue = intValue + 1;
	quantitys = intValue;
	console.log(quantitys);
	document.getElementById("soluong").value = quantitys;

}

