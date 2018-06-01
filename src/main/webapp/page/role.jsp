<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Quản lý vai trò</h3>
              </div>

              <div class="title_right">
                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                  </div>
                </div>
              </div>
            </div>

            <div class="clearfix"></div>
			 <div class='alert alert-success' id="alert">
				<strong><i class="fa fa-check" aria-hidden="true"></i> ${messeage}</strong>
			</div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Quản lý vai trò</h2>
               		 <div class=" pull-right">
           				 <a id="btn-add"class="btn btn-primary" href="javascript:;"> <i class="fa fa-user-plus" aria-hidden="true"></i>Thêm mới vai trò</a>
         			 </div>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  	 <div class="modal fade" id="modal-add">
                  	<div class="modal-dialog">
                  		<div class="modal-content">
                  			<div class="modal-header">
                  				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  				<h4 class="modal-title">Thêm mới vai trò</h4>
                  			</div>
                  			<form action="" method="post" id="frm-add-role">
	                  			<div class="modal-body">
	                  			<input type="hidden" name="action" id="action" class="form-control" value="add" required="required"  title="">
	                  				<label>Name *</label>
	                  				<input type="text" name="name" id="name" class="form-control" value="" required="required"  title="">
	                  				<label>Code *</label>
	                  				<input type="text" name="code" id="code" class="form-control" value="" required="required" title="">
	                  			</div>
	                  			<div class="modal-footer">
	                  				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	                  				<button type="submit" id="btn-add" class="btn btn-primary">Save changes</button>
	                  			</div>
                  			</form>
                  		</div>
                  	</div>
                  </div>
                  <div class="modal fade" id="modal-edit">
                  	<div class="modal-dialog">
                  		<div class="modal-content">
                  			<div class="modal-header">
                  				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  				<h4 class="modal-title">Chỉnh sửa vai trò</h4>
                  			</div>
                  			<form action="" method="post" id="frm-edit-role">
	                  			<div class="modal-body">
	                  			<input type="hidden" name="action" id="action" class="form-control" value="edit" required="required"  title="">
	                  			<input type="hidden" name="id" id="id" class="form-control" value="" required="required"  title="">
	                  				<label>Name *</label>
	                  				<input type="text" name="name_edit" id="name_edit" class="form-control" value="" required="required"  title="">
	                  				<label>Code *</label>
	                  				<input type="text" name="code_edit" id="code_edit" class="form-control" value="" required="required" title="">
	                  			</div>
	                  			<div class="modal-footer">
	                  				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	                  				<button type="submit" id="btn-edit" class="btn btn-primary">Save changes</button>
	                  			</div>
                  			</form>
                  		</div>
                  	</div>
                  </div>
                  <div class="modal fade" id="modal-menu">
                  	<div class="modal-dialog">
                  		<div class="modal-content">
                  			<div class="modal-header">
                  				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  				<h4 class="modal-title">Phân quyền menu</h4>
                  			</div>
                  			
	                  			<div class="modal-body">
	                  				<div id="jstree">
									</div>
	                  			</div>
                  		</div>
                  	</div>
                  </div>
                      <table id="tablerole" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                      	 <thead>
		                        <tr>
		                          <th>ID</th>
		                          <th>Tên hiển thị</th>
		                          <th>Ký hiệu</th>
		                          <th>Ngày tạo</th>
		                          <th>Thao tác</th>
		                        </tr>
                      	</thead>
	                    <tbody>
	                    </tbody>
                      </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->
<%@include file="footer.jsp" %> 
    <script type="text/javascript">
    	function abc(){
    		
    		return datatest;
    	}
    	$(document).ready(function(){
			var a = abc();
    		var ctx = "${pageContext.request.contextPath}";
    		$('#jstree').jstree({
    			  'plugins': ['checkbox', 'wholerow'],
    			  'core': {
    			    'data':a ,
    			    'animation': false,
    			    //'expand_selected_onload': true,
    			    'themes': {
    			      'icons': false,
    			    }
    			  },
    			  'search': {
    			    'show_only_matches': true,
    			    'show_only_matches_children': true
    			  }
    			});
      		$("#tablerole").DataTable( {
      	        "bProcessing": true,
      	        "bServerSide": true,
      	        "sort": "position",
      	     	" bSortable": true,
      	        //bStateSave variable you can use to save state on client cookies: set value "true" 
      	        "bStateSave": false,
      	        //Default: Page display length
      	        "iDisplayLength": 10,
      	        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
      	        "iDisplayStart": 0,
      	        "fnDrawCallback": function () {  
      	        },         
      	        "sAjaxSource": ctx+"/listrole",
      	      	"columnDefs" : [ {
    				"className" : "text-center",
    				"targets" : 0
    			},{
    				"className" : "text-center",
    				"targets" : 1
    			},{
    				"className" : "text-center",
    				"targets" : 2
    			},{
    				"className" : "text-center",
    				"targets" : 3
    			},{
    				"className" : "text-center",
    				"targets" : 4
    			}],
      	        "aoColumns": [
      	            { "data": "id" },
      	            { "data": "name" },
      	         	{ "data": "code" },
      	         	{ "data": "createdAt",render:function(data){
      	         		return moment(data).format("HH:mm | DD/MM/YYYY");
      	         	}  },
      	         	{ "data": "action",
      	            	render: function (data, type, row) {
      	                    return '<a class="btn btn-success btn-sm btn-permission" href="'+ctx+'/role/'+row.id+'/permission" id="'+row.id+'"  title="Thêm quyền hạn"><i class="fa fa-shield" aria-hidden="true"></i></a> <a class="btn btn-warning btn-sm btn-menu" href="javascript:;" id="'+row.id+'"  title="Thêm menu"><i class="fa fa-bars" aria-hidden="true"></i></a> <a class="btn btn-primary btn-sm btn-edit" href="javascript:;" id="'+row.id+'"  title="Sửa"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a> <a id="'+row.id+'" class="btn btn-danger btn-sm btn-delete " href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
      	            }},  
      	        ]
      	    });
      		//add role
      		$("#btn-add").on("click",function(){
      			$("#modal-add").modal("show");
      		});
      		
      		//add menu
      		$("#tablerole").on("click",'.btn-menu',function(){
      			$("#modal-menu").modal("show");
      		});
      		
      		$('#frm-add-role').on('submit', function(e) {
				e.preventDefault();
				var form = $('#frm-add-role');
				var formData = form.serialize();
				$.ajax({
					type : 'POST',
					url : ctx + "/role/add",
					data : formData,
					success : function(res) {
						if (res.status=="SUCCESS") {
							toastr["success"]("Thêm vai trò thành công !");
							$('#modal-add').modal('hide');
							$('#tablerole').DataTable().ajax.reload(); 
							$("#name").val("");
							$("#code").val("");

						} else {

							if (!IsNull(data.message.name)) {
								toastr.error(data.message.name[0]);
							}
							if (!IsNull(data.message.display_name)) {
								toastr.error(data.message.display_name[0]);
							}

						}

					},
					error : function(xhr, ajaxOptions, thrownError) {

						toastr.error(thrownError);
					}
				});

			});
      		//edit role
      		$("#tablerole").on("click",'.btn-edit',function(){
      			$("#modal-edit").modal("show");
      			$("#id").val($(this).attr('id'));
      			$("#code_edit").val($(this).parent().parent().find("td:eq(2)").text());
      			$("#name_edit").val($(this).parent().parent().find("td:eq(1)").text());
      			$('#frm-edit-role').on('submit', function(e) {
    				e.preventDefault();
    				var form = $('#frm-edit-role');
    				var formData = form.serialize();
    				$.ajax({
    					type : 'POST',
    					url : ctx + "/role/edit",
    					data : formData,
    					success : function(res) {
    						if (res.status=="SUCCESS") {
    							toastr["success"]("Cập nhật vai trò thành công !");
    							$('#modal-edit').modal('hide');
    							$('#tablerole').DataTable().ajax.reload(); 

    						} else {

    							if (!IsNull(data.message.name)) {
    								toastr.error(data.message.name[0]);
    							}
    							if (!IsNull(data.message.display_name)) {
    								toastr.error(data.message.display_name[0]);
    							}

    						}

    					},
    					error : function(xhr, ajaxOptions, thrownError) {

    						toastr.error(thrownError);
    					}
    				});

    			});
      			
      			
      		});
      		//delete role
      		$("#tablerole").on("click",'.btn-delete',function(){
      			var code = $(this).attr('id');
      			swal({
      			  title: "Are you sure delete?",
      			  text: "",
      			  type: "warning",
      			  showCancelButton: true,
      			  confirmButtonClass: "btn-danger",
      			  confirmButtonText: "Yes, delete it!",
      			  cancelButtonText: "No, cancel plx!",
      			  closeOnConfirm: false,
      			  closeOnCancel: false
      			},
      			function(isConfirm) {
      			  if (isConfirm) {
      				 $.ajax({
   		              type: "POST",
   		              url: ctx+"/role/delete",
   		              data:{
   		            	  action: 'delete',
   		            	  id: code
   		              },
   		              success: function(res)
   		              {
   		       
   		                if(res.status=="SUCCESS") {
   		                	swal("Deleted!", "Your imaginary file has been deleted.", "success");
   		                  	$('#tablerole').DataTable().ajax.reload();   
   		                  	toastr.success('Xóa thành công!');
   		                }else{
   		                	toastr.error('Lỗi!');
   		                }
   		              },
   		              error: function (xhr, ajaxOptions, thrownError) {
   		                toastr.error(thrownError);
   		              }
   		       		 }); 
      			  } else {
      			    swal("Cancelled", "Your imaginary file is safe :)", "error");
      			  }
      			});
      		});
    		if($("#alert strong").text()==" "){
    			$("#alert").hide();
    		}else{
    			$("#alert").hide();
    			toastr.success("Cập nhật thành công !")
    		}
      	 	
    	})
    </script>
 
