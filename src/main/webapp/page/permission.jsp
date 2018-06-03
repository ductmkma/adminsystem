<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>Quản lý quyền hạn</h3>
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
						<h2>Quản lý quyền hạn - ${role.name}</h2>

						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<table id="tablerolepermission" class="table table-striped table-bordered nowrap"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th class="text-center">ID</th>
									<th class="text-center">Tên hiển thị</th>
									<th class="text-center">Ký hiệu</th>
									<th class="text-center">Thao tác</th>
								</tr>
							</thead>
							<tbody>
							   <c:set var="roleid" value="${roleId}" />
								<c:forEach items="${listPermission}" var="permission">
									<tr>
										<c:set value="fa-circle-o" var="check"></c:set>
										<c:set value="0" var="checkval"></c:set>
										<td class="text-center">${permission.id}</td>
										<td>${permission.name}</td>
										<td>${permission.code}</td>
										<td class="text-center">
											<input type="hidden" id="checked-${permission.id}" value="<c:forEach items="${listRolePermission}" var="pr"><c:if test="${pr.permission.id==permission.id&&roleid==pr.role.id}"><c:set value="1" var="checkval"></c:set></c:if></c:forEach><c:out value="${checkval}"></c:out>">
												<i id="action-${permission.id}" class="fa <c:forEach items="${listRolePermission}" var="pr"><c:if test="${pr.permission.id==permission.id&&roleid==pr.role.id}"><c:set value="fa-check-circle" var="check"></c:set></c:if></c:forEach><c:out value="${check}"></c:out>" title='<c:if test="${check=='fa-check-circle'}">Xóa</c:if><c:if test="${check=='fa-circle-o'}">Thêm</c:if>' onclick="addPermission(${roleId},${permission.id})" aria-hidden="true" style="cursor: pointer; color: #3598dc;font-size: 20px;"></i>
										</td>
									<tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->
<%@include file="footer.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var ctx = "${pageContext.request.contextPath}";
		/* $("#tablerolepermission").DataTable(); */
	})
   function addPermission(role_id, permission_id) {
	var ctx = "${pageContext.request.contextPath}";
	var checked = $('#checked-' + permission_id).val();
    $.ajax({
          type: "POST",
          url: ctx+"/role/"+role_id+"/permission",
          data: {
            role_id: role_id,
            permission_id: permission_id,
            checked: checked,
          },
          success: function(res)
          {
            if (res.status == "DELETED") {
              $('#action-' + permission_id).removeClass('fa-check-circle').addClass('fa-circle-o');
              $('#checked-' + permission_id).val(0);
              toastr.success('Xóa thành công');
            } 

            if (res.status == "ADDED") {
              $('#action-' + permission_id).removeClass('fa-circle-o').addClass('fa-check-circle');
              $('#checked-' + permission_id).val(1);
              toastr.success('Thêm thành công');
            }
            

          },
          error: function (xhr, ajaxOptions, thrownError) {
            toastr.error(thrownError);
          }
    });
    };
</script>

