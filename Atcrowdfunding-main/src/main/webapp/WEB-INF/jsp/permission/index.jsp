<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<link rel="icon" href="${APP_PATH }/img/crowdfunding.png" sizes="32x32" type="image/png">
	<link rel="stylesheet" href="${AAP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${AAP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${AAP_PATH }/css/main.css">
	<link rel="stylesheet" href="${AAP_PATH }/css/doc.min.css">
	<link rel="stylesheet" href="${AAP_PATH }/ztree/zTreeStyle.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
           <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 许可维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<jsp:include page="/WEB-INF/jsp/common/userInfo.jsp"></jsp:include>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<div class="panel panel-default">
              <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表 <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
			  		<!-- ztree展示区域 -->
                  <ul id="treeDemo" class="ztree"></ul>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>没有默认类</h4>
				<p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>没有默认类</h4>
				<p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <script src="${AAP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${AAP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${AAP_PATH }/script/docs.min.js"></script>
	<script src="${AAP_PATH }/ztree/jquery.ztree.all-3.5.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
				
			    
			    loadData();
			    		
            });
            
            function loadData() {
            	
            	/* zTree展示权限列表 */
			    var loadingIndex = -1;
			    
			    var setting = {
		   			    view : {
		   			    	addDiyDom: function(treeId, treeNode){
		   				    	var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
		   			    		if ( treeNode.icon ) {
		   			    			icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
		   			    		}
		   			    	},
							addHoverDom: function(treeId, treeNode){   //设置自定义按钮组,在节点后面悬停显示增删改按钮组.
								var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
								aObj.attr("href", "javascript:void(0)"); // 取消当前链接事件.
								aObj.removeAttr('target');
								aObj.removeAttr('onclick');
								if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
								var s = '<span id="btnGroup'+treeNode.tId+'">';
								if ( treeNode.level == 0 ) { //根节点
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:void(0)" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?id='+treeNode.id+'\'" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
								} else if ( treeNode.level == 1 ) { //分支节点
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="javascript:void(0)" onclick="window.location.href=\'${APP_PATH}/permission/toUpdate.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
									if (treeNode.children.length == 0) {
										s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:void(0)" onclick="deletePermission('+treeNode.id+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
									}
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:void(0)" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?id='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
								} else if ( treeNode.level == 2 ) { //叶子节点
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="javascript:void(0)"  onclick="window.location.href=\'${APP_PATH}/permission/toUpdate.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:void(0)" onclick="deletePermission('+treeNode.id+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
								}
				
								s += '</span>';
								aObj.after(s);
							},
							removeHoverDom: function(treeId, treeNode){
								$("#btnGroup"+treeNode.tId).remove();
							}

		   			   }
		    		};
            	
            	$.ajax({
					url:"${APP_PATH}/permission/loadData.do",
					type:"POST",
					beforeSend:function(){
						loadingIndex = layer.load(2,{time:10*1000});
						return true;
					},
					success:function(result){
						layer.close(loadingIndex);
						if (result.status == 200) {
							var zNodes = result.message;
							$.fn.zTree.init($("#treeDemo"), setting, zNodes); 

						}else {
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					},
					error:function(result){
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				})		
			}
            
            /* 删除节点根据 */
            function deletePermission(fid) {
            	var loadingIndex = -1;
            	layer.confirm("确定删除该许可吗？",{icon:3,title:'提示'},function(cindex){
            		$.ajax({
    					url:"${APP_PATH}/permission/delete.do",
    					type:"POST",
    					data:{
    						"id":fid
    					},
    					beforeSend:function(){
    						loadingIndex = layer.load(2,{time:10*1000});
    						return true;
    					},
    					success:function(result){
    						layer.close(loadingIndex);
    						if (result.status == 200) {
    							loadData();
    						}else {
    							layer.msg(result.message,{time:1000,icon:5,shift:6})
    						}
    					},
    					error:function(result){
    						layer.msg(result.message,{time:1000,icon:5,shift:6})
    					}
    				})	
					layer.close(cindex);
				},function(cindex){
					layer.close(cindex); 
				})	
			}
            
        </script>
</body>
</html>