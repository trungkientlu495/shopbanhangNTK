function updateCart() {
	var soluong = document.getElementsByClassName("soluong").length;
	var listSoLuong = [];
	
	for(var i = 0; i < soluong; i++) {
		var kien = document.getElementById('soluong'+i).value;
		console.log('kien',kien);
		listSoLuong.push(kien);
	}
	console.log(JSON.stringify(listSoLuong));
	// Gửi yêu cầu Ajax
	$.ajax({
		type: "GET",
		url: '/User/updateProductCart', // Đường dẫn đến endpoint xử lý cập nhật giỏ hàng
		data: {
			listSoLuong: JSON.stringify(listSoLuong)
		},
		success: function(response) {
			console.log('Cập nhật giỏ hàng thành công');
			location.reload();
		},
		error: function(xhr, status, error) {
			// Xử lý lỗi
			console.error('Lỗi cập nhật giỏ hàng: ' + error);
			// Hiển thị thông báo lỗi cho người dùng
		}
	});
}