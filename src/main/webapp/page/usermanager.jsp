<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Quản lý người dùng</h3>
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
                    <h2>Quản lý người dùng</h2>
               		 <div class=" pull-right">
           				 <a id="btn-add"class="btn btn-primary" href="<%=request.getContextPath()%>/add"> <i class="fa fa-user-plus" aria-hidden="true"></i>Thêm mới</a>
         			 </div>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                      <table id="tableuser" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                      	 <thead>
		                        <tr>
		                          <th>ID</th>
		                          <th>Image</th>
		                          <th>Name</th>
		                          <th>Gender</th>
		                          <th>Birthday</th>
		                          <th>Address</th>
		                          <th>Action</th>
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
    	$(document).ready(function(){
    		var ctx = "${pageContext.request.contextPath}";
      		$("#tableuser").DataTable( {
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
      	        "sAjaxSource": ctx+"/listuser",
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
    				"targets" : 4,
    				"bSortable": true,
    			},{	"className" : "text-center",
					"targets" : 5
				},{	"className" : "text-center",
					"targets" : 6
				}],
      	        "aoColumns": [
      	            { "data": "id" },
      	          	{ "data": "image",render: function(data,type,row){
        	          	return ' <img style="border-radius:0%;" width="50px" height="50px" src="http://localhost:8080/adminsystem/avata/'+row.id+'"  alt="avatar"/>'
      	            } },
      	            { "data": "name" },
      	            { "data": "gender",render: function(data,type,row){
      	            	if(data==1){
      	            		return "Nam";
      	            	}else if(data==2){
      	            		return "Nữ";
      	            	}else if(data==3){
      	            		return "Khác";
      	            	}
      	            } },
      	         	 { "data": "birthday" },
      	          	 { "data": "address"},
      	         	{ "data": "action",
      	            	render: function (data, type, row) {
      	                    return '<a class="btn btn-primary btn-sm btn-edit" href="'+ctx+'/edit/'+row.id+'" id="'+row.id+'"  title="Sửa"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Vai trò</a> <a class="btn btn-primary btn-sm btn-edit" href="'+ctx+'/edit/'+row.id+'" id="'+row.id+'"  title="Sửa"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Sửa</a> <a id="'+row.id+'" class="btn btn-danger btn-sm btn-delete " href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i> Xóa</a>';
      	            }},  
      	        ]
      	    });
      		$("#tableuser").on("click",'.btn-delete',function(){
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
   		              url: ctx+"/delete",
   		              data:{
   		            	  action: 'delete',
   		            	  id: code
   		              },
   		              success: function(res)
   		              {
   		       
   		                if(res.status=="SUCCESS") {
   		                	swal("Deleted!", "Your imaginary file has been deleted.", "success");
   		                  	$('#tableuser').DataTable().ajax.reload();   
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
    		debugger;
    		if($("#alert strong").text()==" "){
    			$("#alert").hide();
    		}else{
    			$("#alert").hide();
    			toastr.success("Cập nhật thành công !")
    		}
      	 	

    	})
    </script>
 
