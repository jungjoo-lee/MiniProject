<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문목록</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="<c:url value='/resources/css/goods.css'/>">
</head>
<body class="sb-nav-fixed">
        <!-- header -->
        <jsp:include page="../fix/admin/header.jsp"/>
        
        <div id="layoutSidenav">
        	<!-- side -->
        	<jsp:include page="../fix/admin/sidemenu.jsp"/>
        	
        	<div id="layoutSidenav_content">
        		<div class="container-fluid px-4">
                	<h1 class="mt-4">  </h1><br/> <!-- 제목 -->
					<div class="row w-100">
				    	<div class="col d-flex"> <!-- 목록 선택 -->
							<div class="btn-group">
				                <button type="button" class="btn btn-outline-secondary active" id="all"><i class="bi bi-list"></i></button>
				                <button type="button" class="btn btn-outline-secondary" id="notnull"><i class="bi bi-check-circle"></i></button>
				            	<button type="button" class="btn btn-outline-secondary" id="null"><i class="bi bi-x-circle"></i></button>
				            </div>
				    	</div>
				    	<div class="col d-flex justify-content-end"> <!-- 검색 폼 -->
				    		<select class="form-select w-25 me-1" name="searchBy" id="searchBy">
						  		<option value="userid" selected>주문인 ID</option>
						  		<option value="username">주문인 성명</option>
						  		<option value="gname">상품명</option>
							</select>
				      		<div class="d-flex">
					      		<input class="form-control me-2" name="searchKey" id="searchKey" type="text" placeholder="Search">
					    	</div>
				    	</div>
					</div>
					<br/>
					<form name="adminOrderForm">
					<div class="row w-100"> <!--  목록 테이블 -->
				  		<table class="table table-hover">
				  			<thead class="text-center table-light">
				  				<tr>
				  					<th>주문번호</th>
				  					<th>주문인ID</th>
				  					<th>주문인성명</th>
				  					<th>주문상품</th>
				  					<th>주문일시</th>
				  					<th>주문총액</th>
				  					<th>처리상태</th>
				  					<th><input type="checkbox" id=checkAll></th>
				  				</tr>
				  			</thead>
				  			<tbody id="infoTable">
				  				<c:forEach var="ord" items="${orderList}">
				  					<tr>
				  						<td>
				  							<a href="<c:url value='/gshop.do?command=adminOrderDetailView&oseq=${ord.oseq}'/>">
				  								${ord.oseq}
				  							</a>
				  						</td>
				  						<td class="text-center">
				  							<a href="<c:url value='/gshop.do?command=adminOrderDetailView&oseq=${ord.oseq}'/>">
				  								${ord.userid}
				  							</a>				  						
				  						</td>
				  						<td class="text-center">
				  							<a href="<c:url value='/gshop.do?command=adminOrderDetailView&oseq=${ord.oseq}'/>">
				  								${ord.name}
				  							</a>				  						
				  						</td>				  						
				  						<td class="text-center">
				  							<a href="<c:url value='/gshop.do?command=adminOrderDetailView&oseq=${ord.oseq}'/>">
				  								${ord.gname} 포함 ${ord.quantity} 건
				  							</a>
				  						</td>
				  						<td class="text-center">
				  							<a href="<c:url value='/gshop.do?command=adminOrderDetailView&oseq=${ord.oseq}'/>">
				  								${ord.indate}
				  							</a>
				  						</td>
				  						<td class="text-center">
				  							<a href="<c:url value='/gshop.do?command=adminOrderDetailView&oseq=${ord.oseq}'/>">
				  								${ord.totalprice}
				  							</a>
				  						</td>
				  						<td class="text-center">
											${ord.status}
				  						</td>
										<td>
											<input type="checkbox" id="checkboxes" name="gseq"
											value="${ord.oseq}" />
										</td>
								</tr>
				  				</c:forEach>
				  			</tbody>
				  		</table>
					<div class="col d-flex justify-content-end">
						<select class="form-select w-25 me-1" name="osseq"
							id="osseq">
							<option value="0" selected> 주문상태 선택 </option>
							<option value="1" >주문완료</option>
							<option value="2">배송준비중</option>
							<option value="3">배송중</option>
							<option value="4">배송완료</option>
						</select> <input type="button" id="changeStat" value="변경"> 
					</div>
				</div>
				</form>


            	</div>
        	</div>            
        </div>
	<script type="text/javascript" src="<c:url value='/resources/js/admin/admin.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/admin/adminOrder.js'/>"></script>
    </body>
</html>