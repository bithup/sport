//一级
var sports=[
	{'id':'001','value':'球类','parentId':'0'},
	{'id':'002','value':'户外运动','parentId':'0'},
	{'id':'003','value':'室内运动','parentId':'0'}
];
//二级
var sports_item=[
//球类
	{'id':'100','value':'篮球','parentId':'001'},
	{'id':'101','value':'足球','parentId':'001'},
	{'id':'102','value':'羽毛球','parentId':'001'},
	{'id':'103','value':'乒乓球','parentId':'001'},
	{'id':'104','value':'排球','parentId':'001'},
//户外
	{'id':'110','value':'攀岩','parentId':'002'},
	{'id':'111','value':'攀岩','parentId':'002'},
	{'id':'112','value':'攀岩','parentId':'002'},
//室内
	{'id':'120','value':'游泳','parentId':'003'},
	{'id':'121','value':'游泳','parentId':'003'},
	{'id':'122','value':'游泳','parentId':'003'}
];

//区域
var site_range=[
    {'id':'01','value':'二七区'},
    {'id':'02','value':'高新区'},
    {'id':'03','value':'中原区'},
    {'id':'04','value':'二七区'},
    {'id':'05','value':'高新区'},
    {'id':'06','value':'中原区'},
    {'id':'07','value':'二七区'},
    {'id':'08','value':'高新区'}
];

$(function(){
    $.ajax({
        type:'POST',
        url:'/kinds/getChildVenueKinds.htm',
        dataType:'json',
        success:function(data){
            sports = [];
            sports_item = [];
            if(data.resultFlg==1){ 
                var list = data.resultData;
                $.each(list,function(i,item){
                   var obj = {};
                   obj.id = item.parentKinds.id;
                   obj.value = item.parentKinds.name;
                   obj.parentId = item.parentKinds.parentId+'';
                    sports.push(obj);
                    var childList = item.childList;
                   $.each(childList,function(j,child){
                       var childObj = {};
                       childObj.id = child.id;
                       childObj.value = child.name;
                       childObj.parentId = child.parentId+'';
                       sports_item.push(childObj);
                   });
                });
            }else{
                alert(data.resultMsg);
            }
        }
    });

    /*$.ajax({
        type:'POST',
        url:'/zone/getAreaListByPcode.htm',
        dataType:'json',
        success:function(data){
            site_range = [];
            if(data.resultFlg==1){
                var list = data.resultData;
                $.each(list,function(i,item){
                    var obj = {};
                    obj.id = item.parentKinds.id;
                    obj.value = item.parentKinds.name;
                    obj.parentId = item.parentKinds.parentId+'';
                    site_range.push(obj);
                });
                console.log(site_range);
            }else{
                alert(data.resultMsg);
            }
        }
    });*/

});