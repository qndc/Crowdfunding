//异步加载地址列表
function Loadaddr() {
	var content = "";
	$.ajax({
		url:"/personal/Loadaddr.do",
		type:"get",
		success:function(result){
			if (result.status == 200) {
				if(result.message != null){
					$("#addrs").empty();
					$.each(result.message,function(index,item){
						content = '<div class="defau-mod ">'
							+'<div class="addr-state">'
							+'<div class="def-addr-sure">'
							+'<div class="way compile-way delivery-btn"><i class="delete" onclick="modifyAddr('+item.id+')" title="删除"></i></div>'
							+'<div class="way compile-way delivery-btn"><i class="compile" onclick="delAddr('+item.id+')" title="修改"></i></div>'
							+'</div></div>'
							+'<div class="addr-info-sure">'
							+'<div class="addr-ne" title="'+item.name+'">'
							+'<span class="name">'+item.name+'</span>'
							+'<span class="iphone">'+item.phone+'</span>'
							+'</div>'
							+'<div class="addr-hd" title="'+item.province+' '+item.city+' '+item.district+'">'
							+'<span>'+item.province+'</span><span>'+item.city+'</span><span>'+item.district+'</span>'
							+'</div>'
							+'<div class="addr-deail">'
							+'<span class="addr-detailAddress">'+item.detail+'</span>'
							+'</div>'
							+'<div class="hide">'
							+'<span class="addr-phone"></span>'
							+'</div></div>'
							+'<em class="addr-topbg"></em><em class="addr-botbg"></em></div>';
						$("#addrs").append(content);
					})	
					
					$("#addrs").append("<div name='address_index_info_address01' onclick='addAddr()' class='add-addr'></div>");
				}
			}else{
				layer.msg(result.message,{time:1000,icon:5,shift:6})
			}
		},
		error:function(result){
			layer.msg(result.message,{time:1000,icon:5,shift:6})
		}
	})
}

//修改地址
function modifyAddr(id) {
	alert(id);
}
//删除地址
function delAddr(id) {
	alert(id);
}
//添加地址
function addAddr() {
	alert("添加");
}
