//var quantitys = document.getElementsByClassName("soluong").length;
function minusQuantity(index) {
	event.preventDefault();
	var quantitys = document.getElementById('soluong' + index).value;
	var intValue = parseInt(quantitys);
	if (intValue > 1) {
		intValue = intValue - 1;
	}
	quantitys = intValue;
	console.log(quantitys);
	document.getElementById('soluong' + index).value = quantitys;
}

function plusQuantity(index) {
	event.preventDefault();
	var quantitys = document.getElementById('soluong' + index).value;
	var intValue = parseInt(quantitys);
	intValue = intValue + 1;
	quantitys = intValue;
	console.log(quantitys);
	document.getElementById('soluong' + index).value = quantitys;
}
