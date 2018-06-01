<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="header.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>Quản lý người dùng</h3>
			</div>

			<div class="title_right">
				<div
					class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for..."> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button">Go!</button>
						</span>
					</div>
				</div>
			</div>
		</div>

		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Chỉnh sửa người dùng</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form:form modelAttribute="userDTO" action="" method="post"
							enctype="multipart/form-data" id="upload">
								<div class="col-md-4">
								<div class="form-group">
									<label>Avata</label>
									<div class="input-group">
										<span class="input-group-btn"> <span
											class="btn btn-default btn-file"> Browse… 
											<form:input path="avata"  type="file" id="imgInp" name="avata"/>	
											<form:input path="fileNameOld" type="hidden" value="${fileNameOld}"/>
										</span>
										</span><input type="text" class="form-control" readonly>
									</div>
									<form:errors path="avata" cssClass="error"></form:errors>
									<img id='img-upload' src="<%=request.getContextPath() %>/avata/${userDTO.id}" />
								</div>
							</div>
							<div class="col-md-8">
								<div class="col-md-12">
									<label>Name</label>
									<form:input path="name" type="text" placeholder="" name="name"
										id="name" class="form-control input-group" title="" />
									<form:errors path="name" cssClass="error"></form:errors>
									<br>
								</div>
								<div class="col-md-12">
									<label>Email</label>
									<form:input path="email" class="form-control" id="email"
										name="email" />
									<form:errors path="email" cssClass="error"></form:errors>
								</div>
								<div class="col-md-12 ">
									<label>Gender</label> <br>
									<form:radiobutton path="gender" value="1" id="gender"
										name="gender" />
									Nam
									<form:radiobutton path="gender" value="2" id="gender"
										name="gender" />
									Nữ
									<form:radiobutton path="gender" value="3" id="gender"
										name="gender" />
									Khác
									<br>
									<form:errors path="gender" cssClass="error"></form:errors>
									<br>
								</div>
								<div class="col-md-12">
									<label>Address</label>
									<form:input path="address" class="form-control" id="address"
										name="address" />
									<form:errors path="address" cssClass="error"></form:errors>
								</div>
								<div class="col-md-12">
								<br>
									<button type="submit"
										class="btn btn-primary">
										<i class="fa fa-rocket" aria-hidden="true"></i> Thêm mới
									</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->

<%@include file="footer.jsp"%>
<script type="text/javascript">
	$(function() {
		$(document).on('change', '.btn-file :file', function() {
			var input = $(this),
				label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
			input.trigger('fileselect', [label]);
			});

			$('.btn-file :file').on('fileselect', function(event, label) {
			    
			    var input = $(this).parents('.input-group').find(':text'),
			        log = label;
			    
			    if( input.length ) {
			        input.val(log);
			    } else {
			        if( log ) alert(log);
			    }
		    
			});
			function readURL(input) {
			    if (input.files && input.files[0]) {
			        var reader = new FileReader();
			        
			        reader.onload = function (e) {
			            $('#img-upload').attr('src', e.target.result);
			        }
			        
			        reader.readAsDataURL(input.files[0]);
			    }
			}

			$("#imgInp").change(function(){
			    readURL(this);
			}); 	
	});
</script>
