/** 表单序列化成json字符串的方法  */
function form2JsonString(formId) {
    var paramArray = $('#' + formId).serializeArray();  
     /*请求参数转json对象*/  
     var jsonObj={};  
      $(paramArray).each(function(){  
          jsonObj[this.name]=this.value;  
      });  
     console.log(jsonObj);  
     // json对象再转换成json字符串
     return JSON.stringify(jsonObj);
}

//序列化表单字段为json对象
$.fn.serializeFormToJson = function(){
    var arr = $(this).serializeArray();//form表单数据 name：value
    var param = {};
    $.each(arr,function(i,obj){ //将form表单数据封装成json对象
        param[obj.name] = obj.value;
    })
    return param;
}
