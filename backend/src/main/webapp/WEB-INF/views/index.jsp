<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp"%>

<body>
	<%@ include file="common/SignIn.jsp"%>
	<%@ include file="common/nav.jsp"%>

	<!-- ======= Intro Section ======= -->
	<div class="intro intro-carousel swiper position-relative">

		<div class="swiper-wrapper">

			<div class="swiper-slide carousel-item-a intro-item bg-image"
				style="background-image: url(${root}/assets/img/Gwanghwamun.jpg)">
				<div class="overlay overlay-a"></div>
				<div class="intro-content display-table">
					<div class="table-cell">
						<div class="container">
							<div class="row">
								<div class="col-lg-8">
									<div class="intro-body">
										<p class="intro-title-top">
											Jong-ro, Seoul
											<!-- <br> 78345 -->
										</p>
										<h1 class="intro-title mb-4 ">
											<span class="color-b">03045 </span> <br> Gwanghwamun
										</h1>
										<p class="intro-subtitle intro-price">
											<a href="#"><span class="price-a">view more</span></a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="swiper-slide carousel-item-a intro-item bg-image"
				style="background-image: url(${root}/assets/img/jamsil-tower.jpg)">
				<div class="overlay overlay-a"></div>
				<div class="intro-content display-table">
					<div class="table-cell">
						<div class="container">
							<div class="row">
								<div class="col-lg-8">
									<div class="intro-body">
										<p class="intro-title-top">
											Song-pa, Seoul <br> 05556
										</p>
										<h1 class="intro-title mb-4">
											<span class="color-b">300 </span> <br> Olympic-ro
										</h1>
										<p class="intro-subtitle intro-price">
											<a href="#"><span class="price-a">view more</span></a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="swiper-slide carousel-item-a intro-item bg-image"
				style="background-image: url(${root}/assets/img/Suwon-Hwaseong.jpg)">
				<div class="overlay overlay-a"></div>
				<div class="intro-content display-table">
					<div class="table-cell">
						<div class="container">
							<div class="row">
								<div class="col-lg-8">
									<div class="intro-body">
										<p class="intro-title-top">
											Suwon, Gyeonggi <br> 16255
										</p>
										<h1 class="intro-title mb-4">
											<span class="color-b">21 </span> <br> Changnyong-daero
										</h1>
										<p class="intro-subtitle intro-price">
											<a href="#"><span class="price-a">view more</span></a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="swiper-pagination"></div>
	</div>
	<!-- End Intro Section -->

	<main id="main"> <!-- ======= Latest    Properties Section ======= -->
		<c:if test="${not empty recommendAttraction}">
			<section class="section-property section-t8">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="title-wrap d-flex justify-content-between">
								<div class="title-box">
									<h2 class="title-a">우리 지역 여행지 추천</h2>
								</div>
								<div class="title-link">
									<a href="${root}/trip/recommend">더 보기 <span
										class="bi bi-chevron-right"></span>
									</a>
								</div>
							</div>
						</div>
					</div>

					<div id="property-carousel" class="swiper">
						<div class="swiper-wrapper">

							<c:forEach var="attraction" items="${recommendAttraction}">
							<div class="carousel-item-b swiper-slide">
								<div class="card-box-a card-shadow">
									<div class="img-box-a" style="height: 480px;">
										<img src="${attraction.firstImage}" alt=""
											class="img-a img-fluid">
									</div>
									<div class="card-overlay">
										<div class="card-overlay-a-content">
											<div class="card-header-a">
												<h2 class="card-title-a">
													<a href="property-single.jsp">
														${attraction.title}
													</a>
												</h2>
											</div>
											<div class="card-body-a">
												<div class="price-box d-flex">
													<span class="price-a">주소</span>
												</div>
												<div style="color: white">${attraction.address1} ${attraction.address2}</div>
												<a href="#" class="link-a">Click here to view <span
													class="bi bi-chevron-right"></span>
												</a>
											</div>
											<div class="card-footer-a">
												<ul class="card-info d-flex justify-content-around">
													<li>
														<h4 class="card-info-title">Area</h4> <span>340m <sup>2</sup>
													</span>
													</li>
													<li>
														<h4 class="card-info-title">Beds</h4> <span>2</span>
													</li>
													<li>
														<h4 class="card-info-title">Baths</h4> <span>4</span>
													</li>
													<li>
														<h4 class="card-info-title">Garages</h4> <span>1</span>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							</c:forEach>
							<!-- End carousel item -->
						</div>
					</div>
					<div class="propery-carousel-pagination carousel-pagination"></div>

				</div>
			</section>
		</c:if>
	<!-- End Latest Properties Section --> <!-- ======= Latest News Section ======= -->
	<section class="section-news section-t8">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="title-wrap d-flex justify-content-between">
						<div class="title-box">
							<h2 class="title-a">Hot Place</h2>
						</div>
						<div class="title-link">
							<a href="${root}/trip/hotPlace">더보기 <span class="bi bi-chevron-right"></span>
							</a>
						</div>
					</div>
				</div>
			</div>

			<div id="news-carousel" class="swiper">
				<div class="swiper-wrapper">

					<c:forEach var="hotPlace" items="${hotPlaceList}" begin="0" end="10">
						<div class="carousel-item-c swiper-slide">
							<div class="card-box-b card-shadow news-box">
								<div class="img-box-b" style="height: 280px">
									<c:choose>
										<c:when test="${not empty hotPlace.imgPath}">
											<img src="${hotPlace.imgPath}" alt="" class="img-b img-fluid">
										</c:when>
										<c:otherwise>
											<img src="${root}/assets/img/noimg.gif" alt="" class="img-b img-fluid">
										</c:otherwise>
									</c:choose>
								</div>
								<div class="card-overlay">
									<div class="card-header-b">
										<div class="card-category-b">
											<a class="category-b">♥ ${hotPlace.count}</a>
										</div>
										<div class="card-title-b">
											<h2 class="title-2">
												<a>${hotPlace.title}</a>
											</h2>
										</div>
										<div class="card-date">
											<span class="date-b">${hotPlace.address1} ${hotPlace.address2}</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- End carousel item -->
					</c:forEach>
				</div>
			</div>

			<div class="news-carousel-pagination carousel-pagination"></div>
		</div>
	</section>
	<!-- End Latest News Section --> </main>
	<!-- End #main -->
	<%@ include file="common/footer.jsp"%>
</body>
</html>