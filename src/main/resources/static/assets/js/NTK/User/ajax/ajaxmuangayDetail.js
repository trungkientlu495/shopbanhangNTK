function muangayDetail(idProduct) {
	var soluong = document.getElementById("soluong").value;
	console.log(soluong);
	console.log(idProduct);

	$.ajax({
		url: "/User/muangay", // Thay đổi "/your-api-endpoint" thành endpoint API thích hợp trên máy chủ của bạn
		type: "GET",
		data: {
			soluong: soluong,
			idProduct: idProduct
		},
		success: function() {
			// Xử lý phản hồi từ API ở đây
			console.log("thanhcong");
		},
		error: function(xhr, status, error) {
			// Xử lý khi có lỗi xảy ra trong quá trình gọi API
			console.error(error);
		}
	});
}