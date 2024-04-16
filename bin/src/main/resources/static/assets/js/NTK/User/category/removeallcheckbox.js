function uncheckAll() {
	var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	checkboxes.forEach(function(checkbox) {
		checkbox.checked = false;
	});
}

function removeCheckbox() {
	var inputElement = document.getElementById("search");
	var nameValue = inputElement.value;
	$.ajax({
		type: "POST",
		url: "/User/removeCheckbox",
		data: { search: nameValue}, // Truyền tham số 'data' như đã định nghĩa trong phương thức 'searchProducts'
		success: function(response) {
			// Xử lý phản hồi thành công từ backend ở đây
			var list = response;

			var productDiv = document.getElementById("yourElementId");
			var Kien = "";
			for (var i = 0; i < list.length; i++) {
				Kien += `
						<div class="col-sm-6 col-md-4 wow fadeInUp kien">
											<div class="products">
												<div class="product" style="height: 350px!important">
													<div class="product-image">
														<div class="image">
															<a href="/User/detail?id=${list[i].id}"><img
																src="${list[i].imageUrl}" alt="a"></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="/User/detail?id=${list[i].id}"
																 class="product_name1">${list[i].name}</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price" >
																${list[i].price} đ</span> <span class="price-before-discount">
																${list[i].priceBegin} đ</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart" onclick="notificationWishlist()"
																	href="/User/createWishlist?id=${list[i].id}" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
					`;
			}
			/*console.log(Kien);*/
			productDiv.innerHTML = Kien;
		},
		error: function(xhr, status, error) {
			console.log("loi");
		}
	});
}

