function sendEmailUser() {
	$.ajax({
		url: "/sendEmail/dangkythanhcong", // Thay đổi "/your-api-endpoint" thành endpoint API thích hợp trên máy chủ của bạn
		type: "GET",
		data: {
			
		},
		success: function() {
			console.log("thanhcong");
		},
		error: function(xhr, status, error) {
			// Xử lý khi có lỗi xảy ra trong quá trình gọi API
			console.error(error);
		}
	});
}