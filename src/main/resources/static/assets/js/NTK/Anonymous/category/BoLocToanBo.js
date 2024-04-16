function searchLocalBuyandShipping() {
	if ((document.getElementById("hanoi").checked || document.getElementById("thanhhoa").checked || document.getElementById("haiphong").checked) 
	&&
	(document.getElementById("hoatoc").checked || document.getElementById("nhanh").checked || document.getElementById("tietkiem").checked)
	&&
	(!document.getElementById("unisex").checked && !document.getElementById("tlu").checked && !document.getElementById("yody").checked)
	) {
		var inputElement = document.getElementById("search");
		var nameValue = inputElement.value;
		/*console.log(nameValue);*/
		var hanoi = document.getElementById("hanoi").checked ? document.getElementById("checkboxLabel").innerText : "1";
		var thanhhoa = document.getElementById("thanhhoa").checked ? document.getElementById("checkboxLabel1").innerText : "1";
		var haiphong = document.getElementById("haiphong").checked ? document.getElementById("checkboxLabel2").innerText : "1";
		var hoatoc = document.getElementById("hoatoc").checked ? document.getElementById("shipping1").innerText : "1";
		var nhanh = document.getElementById("nhanh").checked ? document.getElementById("shipping2").innerText : "1";
		var tietkiem = document.getElementById("tietkiem").checked ? document.getElementById("shipping3").innerText : "1";
		//var hoatoc = document.getElementById("hoatoc").checked ? document.getElementById("shipping1").innerText : null;
		/*var nhanh = document.getElementById("nhanh").checked ? document.getElementById("shipping2").innerText : null;
		var tietkiem = document.getElementById("tietkiem").checked ? document.getElementById("shipping3").innerText : null;*/
		// Dữ liệu bạn muốn gửi đi
		$.ajax({
			type: "POST",
			url: "/Anonymous/searchbyLocalBuyandShipping",
			data: { search: nameValue, hanoi: hanoi, thanhhoa: thanhhoa, haiphong: haiphong , hoatoc: hoatoc, nhanh: nhanh, tietkiem: tietkiem}, // Truyền tham số 'data' như đã định nghĩa trong phương thức 'searchProducts'
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
															<a href="/Anonymous/detail?id=${list[i].id}"><img
																src="${list[i].imageUrl}" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="/Anonymous/detail?id=${list[i].id}"
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
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
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
}

// search localBuy and brand

function searchLocalBuyandBrand() {
	if ((document.getElementById("hanoi").checked || document.getElementById("thanhhoa").checked || document.getElementById("haiphong").checked) 
	&&
	(document.getElementById("unisex").checked || document.getElementById("tlu").checked || document.getElementById("yody").checked)
	&&
	(!document.getElementById("hoatoc").checked && !document.getElementById("nhanh").checked && !document.getElementById("tietkiem").checked)
	) {
		var inputElement = document.getElementById("search");
		var nameValue = inputElement.value;
		/*console.log(nameValue);*/
		var hanoi = document.getElementById("hanoi").checked ? document.getElementById("checkboxLabel").innerText : "1";
		var thanhhoa = document.getElementById("thanhhoa").checked ? document.getElementById("checkboxLabel1").innerText : "1";
		var haiphong = document.getElementById("haiphong").checked ? document.getElementById("checkboxLabel2").innerText : "1";
		var unisex = document.getElementById("unisex").checked ? document.getElementById("brand1").innerText : "1";
		var tlu = document.getElementById("tlu").checked ? document.getElementById("brand2").innerText : "1";
		var yody = document.getElementById("yody").checked ? document.getElementById("brand3").innerText : "1";
		//var hoatoc = document.getElementById("hoatoc").checked ? document.getElementById("shipping1").innerText : null;
		/*var nhanh = document.getElementById("nhanh").checked ? document.getElementById("shipping2").innerText : null;
		var tietkiem = document.getElementById("tietkiem").checked ? document.getElementById("shipping3").innerText : null;*/
		// Dữ liệu bạn muốn gửi đi
		$.ajax({
			type: "POST",
			url: "/Anonymous/searchbyLocalBuyandBrand",
			data: { search: nameValue, hanoi: hanoi, thanhhoa: thanhhoa, haiphong: haiphong , unisex: unisex,tlu: tlu,yody: yody}, // Truyền tham số 'data' như đã định nghĩa trong phương thức 'searchProducts'
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
															<a href="/Anonymous/detail?id=${list[i].id}"><img
																src="${list[i].imageUrl}" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="/Anonymous/detail?id=${list[i].id}"
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
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
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
}

// SHIPPING AND BRAND

function searchShippingandBrand() {
	if ((document.getElementById("hoatoc").checked || document.getElementById("nhanh").checked || document.getElementById("tietkiem").checked) 
	&&
	(document.getElementById("unisex").checked || document.getElementById("tlu").checked || document.getElementById("yody").checked)
	&&
	(!document.getElementById("hanoi").checked && !document.getElementById("thanhhoa").checked && !document.getElementById("haiphong").checked)
	) {
		var inputElement = document.getElementById("search");
		var nameValue = inputElement.value;
		/*console.log(nameValue);*/
		var hoatoc = document.getElementById("hoatoc").checked ? document.getElementById("shipping1").innerText : "1";
		var nhanh = document.getElementById("nhanh").checked ? document.getElementById("shipping2").innerText : "1";
		var tietkiem = document.getElementById("tietkiem").checked ? document.getElementById("shipping3").innerText : "1";
		var unisex = document.getElementById("unisex").checked ? document.getElementById("brand1").innerText : "1";
		var tlu = document.getElementById("tlu").checked ? document.getElementById("brand2").innerText : "1";
		var yody = document.getElementById("yody").checked ? document.getElementById("brand3").innerText : "1";
		//var hoatoc = document.getElementById("hoatoc").checked ? document.getElementById("shipping1").innerText : null;
		/*var nhanh = document.getElementById("nhanh").checked ? document.getElementById("shipping2").innerText : null;
		var tietkiem = document.getElementById("tietkiem").checked ? document.getElementById("shipping3").innerText : null;*/
		// Dữ liệu bạn muốn gửi đi
		$.ajax({
			type: "POST",
			url: "/Anonymous/searchbyShippingandBrand",
			data: { search: nameValue, hoatoc: hoatoc,nhanh: nhanh,tietkiem: tietkiem, unisex: unisex,tlu: tlu,yody: yody}, // Truyền tham số 'data' như đã định nghĩa trong phương thức 'searchProducts'
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
															<a href="/Anonymous/detail?id=${list[i].id}"><img
																src="${list[i].imageUrl}" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="/Anonymous/detail?id=${list[i].id}"
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
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
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
}

// search LocalBuy and Shipping and Brand

function searchLocalBuyandShippingandBrand() {
	if ((document.getElementById("hanoi").checked || document.getElementById("thanhhoa").checked || document.getElementById("haiphong").checked)
	&&
		(document.getElementById("hoatoc").checked || document.getElementById("nhanh").checked || document.getElementById("tietkiem").checked) 
	&&
	(document.getElementById("unisex").checked || document.getElementById("tlu").checked || document.getElementById("yody").checked)
	) {
		var inputElement = document.getElementById("search");
		var nameValue = inputElement.value;
		/*console.log(nameValue);*/
		var hanoi = document.getElementById("hanoi").checked ? document.getElementById("checkboxLabel").innerText : "1";
		var thanhhoa = document.getElementById("thanhhoa").checked ? document.getElementById("checkboxLabel1").innerText : "1";
		var haiphong = document.getElementById("haiphong").checked ? document.getElementById("checkboxLabel2").innerText : "1";
		var hoatoc = document.getElementById("hoatoc").checked ? document.getElementById("shipping1").innerText : "1";
		var nhanh = document.getElementById("nhanh").checked ? document.getElementById("shipping2").innerText : "1";
		var tietkiem = document.getElementById("tietkiem").checked ? document.getElementById("shipping3").innerText : "1";
		var unisex = document.getElementById("unisex").checked ? document.getElementById("brand1").innerText : "1";
		var tlu = document.getElementById("tlu").checked ? document.getElementById("brand2").innerText : "1";
		var yody = document.getElementById("yody").checked ? document.getElementById("brand3").innerText : "1";
		//var hoatoc = document.getElementById("hoatoc").checked ? document.getElementById("shipping1").innerText : null;
		/*var nhanh = document.getElementById("nhanh").checked ? document.getElementById("shipping2").innerText : null;
		var tietkiem = document.getElementById("tietkiem").checked ? document.getElementById("shipping3").innerText : null;*/
		// Dữ liệu bạn muốn gửi đi
		$.ajax({
			type: "POST",
			url: "/Anonymous/searchbyLocalBuyandShippingandBrand",
			data: { search: nameValue,hanoi: hanoi,thanhhoa: thanhhoa,haiphong: haiphong, hoatoc: hoatoc,nhanh: nhanh,tietkiem: tietkiem, unisex: unisex,tlu: tlu,yody: yody}, // Truyền tham số 'data' như đã định nghĩa trong phương thức 'searchProducts'
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
															<a href="/Anonymous/detail?id=${list[i].id}"><img
																src="${list[i].imageUrl}" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="/Anonymous/detail?id=${list[i].id}"
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
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
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
}

