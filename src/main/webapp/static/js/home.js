

$(function(){
	// 命名空间注册函数 
	var namespace = { 
	    reg : function(s){ 
	        var arr = s.split('.'); 
	        var namespace = window; 
	
	        for(var i=0,k=arr.length;i<k;i++){ 
	            if(typeof namespace[arr[i]] == 'undefined'){ 
	                namespace[arr[i]] = {};  
	            } 
	
	            namespace = namespace[arr[i]];           
	        } 
	    }, 
	    del : function(s){ 
	        var arr = s.split('.'); 
	        var namespace = window; 
	
	        for(var i=0,k=arr.length;i<k;i++){ 
	            if(typeof namespace[arr[i]] == 'undefined'){ 
	                return; 
	            }else if( k == i+1 ){ 
	                delete  namespace[arr[i]]; 
	                return; 
	            }else{ 
	                namespace = namespace[arr[i]];       
	            }        
	        }            
	    } 
	};
	
	/*
	 * 浏览器全屏
	 */
	function fullScreen() {
		  var el = document.documentElement;
		  var rfs = el.requestFullScreen || el.webkitRequestFullScreen;
		  if(typeof rfs != "undefined" && rfs) {
		    rfs.call(el);
		  } else if(typeof window.ActiveXObject != "undefined") {
		    var wscript = new ActiveXObject("WScript.Shell");
		    if(wscript != null) {
		        wscript.SendKeys("{F11}");
		    }
		}else if (el.msRequestFullscreen) {
			el.msRequestFullscreen();
		}else if(el.oRequestFullscreen){
			el.oRequestFullscreen();
	    }else{
	    	swal({   title: "浏览器不支持全屏调用！",   text: "请更换浏览器或按F11键切换全屏！(3秒后自动关闭)", type: "error",  timer: 3000 });	
	    }
	}
	
	/*
	 * 浏览器退出全屏
	 */
	function exitFullScreen() {
		  var el = document;
		  var cfs = el.cancelFullScreen || el.webkitCancelFullScreen || el.exitFullScreen;
		  if(typeof cfs != "undefined" && cfs) {
		    cfs.call(el);
		  } else if(typeof window.ActiveXObject != "undefined") {
		    var wscript = new ActiveXObject("WScript.Shell");
		    if(wscript != null) {
		        wscript.SendKeys("{F11}");
		    }
		}else if (el.msExitFullscreen) {
			el.msExitFullscreen();
		}else if(el.oRequestFullscreen){
			el.oCancelFullScreen();
	    }else{ 
	    	swal({   title: "浏览器不支持全屏调用！",   text: "请更换浏览器或按F11键切换全屏！(3秒后自动关闭)", type: "error",  timer: 3000 });	
	    }  
	}


	
	// 注册命名空间
	namespace.reg("mainpage.mainTabs");
	
	var collapsed = false; 
	var homePageTitle = "主页", homePageHref = null, mainLayout = "#mainLayout", btnFullScreen = "#btnFullScreen";
	
	window.mainpage.instTimeSpan = function(){
		var timerSpan = $("#timerSpan"), interval = function(){ timerSpan.text($.date.toLongDateTimeString(new Date())); };
		interval();
		window.setInterval(interval,1000);
	};
	
	window.mainpage.bindToolbarButtonEvent = function(){
		$("#btnHideNorth").click(function () { window.mainpage.hideNorth(); });
		var btnShow = $("#btnShowNorth").click(function(){ window.mainpage.showNorth() });
		var l = $(mainLayout), north = l.layout("panel", "north"), panel = north.panel("panel"),
			toolbar = $("#toolbar"), topbar = $("#topbar");
		opts = north.panel("options"), onCollapse = opts.onCollapse, onExpand = opts.onExpand;
		opts.onCollapse = function(){
			if ($.isFunction(onCollapse)){
				onCollapse.apply(this, arguments);
			}
			btnShow.show();
			toolbar.insertBefore(panel).addClass("top-toolbar-topmost");
		};
		opts.onExpand = function(){
			if ($.isFunction(onExpand)) { onExpand.apply(this, arguments); }
            btnShow.hide();
            toolbar.insertAfter(topbar).removeClass("top-toolbar-topmost");
		};	
		
		$(btnFullScreen).click(function(){
			var isFullScreen = document.fullscreenElement || document.mozFullScreenElement||document.webkitFullscreenElement;
			if(isFullScreen){
				exitFullScreen();
			}
			else{
				fullScreen();
			}
		});
		
		$(btnExit).click(function(){
			$.messager.confirm("操作提醒", "您确定要退出当前程序并关闭该网页？", function(c){
				if(c){
					window.onbeforeunload = null;
					location.href = "logout.do";
				}
			});
		});
	};
	
	
	window.mainpage.bindMainTabsButtonEvent = function(){
		$("#mainTabs_jumpHome").click(function (){ window.mainpage.mainTabs.jumpHome(); });
		$("#mainTabs_toggleAll").click(function (){ window.mainpage.togglePanels(); });
		$("#mainTabs_jumpTab").click(function (){ window.mainpage.mainTabs.jumpTab(); });
		$("#mainTabs_closeTab").click(function (){ window.mainpage.mainTabs.closeCurrentTab(); });
		$("#mainTabs_refTab").click(function (){ window.mainpage.mainTabs.refCurrentTab(); });
		$("#mainTabs_closeOther").click(function (){ window.mainpage.mainTabs.closeOtherTabs(); });
		$("#mainTabs_closeLeft").click(function (){ window.mainpage.mainTabs.closeLeftTabs(); });
		$("#mainTabs_closeRight").click(function (){ window.mainpage.mainTabs.closeRightTabs(); });
		$("#mainTabs_closeAll").click(function (){ window.mainpage.mainTabs.closeAllTabs(); });
	};
	
	window.mainpage.hideNorth = function(){ $(mainLayout).layout("collapse","north"); };
	
	window.mainpage.showNorth = function(){ $(mainLayout).layout("expand","north"); };
	
	window.mainpage.togglePanels = function(){ 
		if(!collapsed){
			$(mainLayout).layout("collapse","north"); 
			$(mainLayout).layout("collapse","south"); 
			$(mainLayout).layout("collapse","west"); 
			$(mainLayout).layout("collapse","east"); 
			
			collapsed = true;
		}
		else{
			$(mainLayout).layout("expand","north"); 
			$(mainLayout).layout("expand","south"); 
			$(mainLayout).layout("expand","west"); 
			$(mainLayout).layout("expand","east"); 
			
			collapsed = false;
		}
	};
	
	window.mainpage.addModuleTab = function(node){
		var n = node || {}, attrs = node.attributes || {}, opts = $.extend({}, n, { title: n.text }, attrs);
		if(!opts.href) { return; }
		window.mainpage.mainTabs.addModule(opts);
	};
	
    String.prototype.endWith=function(str){
		if(str==null||str==""||this.length==0||str.length>this.length)
		  return false;
		if(this.substring(this.length-str.length)==str)
		  return true;
		else
		  return false;
		return true;
	}
	
	//  判断指定的选项卡是否存在于当前主页面的选项卡组中；
    //  返回值：返回的值可能是以下几种：
    //      0:  表示不存在于当前选项卡组中；
    //      1:  表示仅 title 值存在于当前选项卡组中；
    //      2:  表示 title 和 href 都存在于当前选项卡组中；
    window.mainpage.mainTabs.isExists = function (title, href) {
        var t = $(mainTabs), tabs = t.tabs("tabs");
        var array = tabs.filter(function (val) { 
        	var opts = val.panel("options"); 
        	var tab_ifram = null;
        	if(val.find('iframe').length>0){
        		tab_ifram = val.find('iframe')[0];
        	}
        	var tab_url = null;
        	if(tab_ifram){
        		tab_url = tab_ifram.src;
        	}
        	else{
        		tab_url = opts.href
        	}
        	
        	return opts.title == title && (tab_url == href || tab_url.endWith(href));
        });
        
//        var list = array.filter(function (val) { 
//        	return val.title == title; 
//        	});
        var ret = array.length ? 2 : 0;
            
//        if (ret && list.some(function (val) { return val.href == href; })) { 
//        	ret = 2; 
//        }
        return ret;
    };
    


    window.mainpage.mainTabs.tabDefaultOption = {
        title: "新建选项卡", href: "", iniframe: true, closable: true, refreshable: true, iconCls: "icon-standard-application-form", repeatable: true, selected: true
    };
    window.mainpage.mainTabs.parseCreateTabArgs = function (args) {
        var ret = {};
        if (!args || !args.length) {
            $.extend(ret, window.mainpage.mainTabs.tabDefaultOption);
        } else if (args.length == 1) {
            $.extend(ret, window.mainpage.mainTabs.tabDefaultOption, typeof args[0] == "object" ? args[0] : { href: args[0] });
        } else if (args.length == 2) {
            $.extend(ret, window.mainpage.mainTabs.tabDefaultOption, { title: args[0], href: args[1] });
        } else if (args.length == 3) {
            $.extend(ret, window.mainpage.mainTabs.tabDefaultOption, { title: args[0], href: args[1], iconCls: args[2] });
        } else if (args.length == 4) {
            $.extend(ret, window.mainpage.mainTabs.tabDefaultOption, { title: args[0], href: args[1], iconCls: args[2], iniframe: args[3] });
        } else if (args.length == 5) {
            $.extend(ret, window.mainpage.mainTabs.tabDefaultOption, { title: args[0], href: args[1], iconCls: args[2], iniframe: args[3], closable: args[4] });
        } else if (args.length == 6) {
            $.extend(ret, window.mainpage.mainTabs.tabDefaultOption, { title: args[0], href: args[1], iconCls: args[2], iniframe: args[3], closable: args[4], refreshable: args[5] });
        } else if (args.length >= 7) {
            $.extend(ret, window.mainpage.mainTabs.tabDefaultOption, { title: args[0], href: args[1], iconCls: args[2], iniframe: args[3], closable: args[4], refreshable: args[5], selected: args[6] });
        }
        return ret;
    };

    window.mainpage.mainTabs.createTab = function (title, href, iconCls, iniframe, closable, refreshable, selected) {
        var t = $(mainTabs), i = 0, opts = window.mainpage.mainTabs.parseCreateTabArgs(arguments);
        while (t.tabs("getTab", opts.title + (i ? String(i) : ""))) { i++; }
        
        // 对于iniframe的单独处理成 <iframe 形式
        if(opts.iniframe){
        	var srcUrl = opts.href;
        	delete opts.href;
        	opts.content = '<iframe scrolling="auto" frameborder="0"  src="'+srcUrl+'" style="width:100%;height:100%;"></iframe>';
        }
        
        t.tabs("add", opts);
    };
    
    //  添加一个新的模块选项卡；该方法重载方式如下：
    //      function (tabOption)
    //      function (href)
    //      function (title, href)
    //      function (title, href, iconCls)
    //      function (title, href, iconCls, iniframe)
    //      function (title, href, iconCls, iniframe, closable)
    //      function (title, href, iconCls, iniframe, closable, refreshable)
    //      function (title, href, iconCls, iniframe, closable, refreshable, selected)
    window.mainpage.mainTabs.addModule = function (title, href, iconCls, iniframe, closable, refreshable, selected) {
        var opts = window.mainpage.mainTabs.parseCreateTabArgs(arguments), isExists = window.mainpage.mainTabs.isExists(opts.title, opts.href);
        switch (isExists) {
            case 0: window.mainpage.mainTabs.createTab(opts); break;
            case 1: window.mainpage.mainTabs.createTab(opts); break;
            case 2: window.mainpage.mainTabs.jumeTab(opts.title); break;
            default: break;
        }
    };
    
    window.mainpage.mainTabs.jumeTab = function (which) { $(mainTabs).tabs("select", which); };
    
    window.mainpage.mainTabs.jumpHome = function () {
        var t = $(mainTabs), tabs = t.tabs("tabs"), panel = null;
        $.each(tabs, function(key, val){
        	var opts = val.panel("options");
        	if(opts.title == homePageTitle && opts.href == homePageHref){
        		panel = val;
        		return false;
        	}
        });
        if (panel && panel.length) {
            var index = t.tabs("getTabIndex", panel);
            t.tabs("select", index);
        }
    }

    window.mainpage.mainTabs.jumpTab = function (which) { $(mainTabs).tabs("jumpTab", which); };

    window.mainpage.mainTabs.closeTab = function (which) { $(mainTabs).tabs("close", which); };
    
	window.mainpage.mainTabs.refCurrentTab = function () {
        var t = $(mainTabs);
        var refresh_tab  = t.tabs("getSelected");
        var index  = t.tabs("getTabIndex", refresh_tab);
        var opts = refresh_tab.panel("options");
        
        // 处理tab内容为iframe的情况
        if(refresh_tab && refresh_tab.find('iframe').length > 0){
		    var _refresh_ifram = refresh_tab.find('iframe')[0];
		    var refresh_url = opts.url?opts.url:_refresh_ifram.src;
		    //_refresh_ifram.src = refresh_url;
		    _refresh_ifram.contentWindow.location.href=refresh_url;
	    }
	    else{
	    	refresh_tab.panel("refresh");
	    }
    };

    window.mainpage.mainTabs.closeCurrentTab = function () {
        var t = $(mainTabs);
        var tab  = t.tabs("getSelected");
        var index  = t.tabs("getTabIndex", tab);
        var options = tab.panel("options");
        if(options && options.closable){
	        return t.tabs("close", index);
        }
        else{
        	return null;
        }
    };

    window.mainpage.mainTabs.closeOtherTabs = function (index) {
        var t = $(mainTabs);
        if (index == null || index == undefined) { index = t.tabs("getSelectedIndex"); }
        return t.tabs("closeOtherClosable", index);
    };

    window.mainpage.mainTabs.closeLeftTabs = function (index) {
        var t = $(mainTabs);
        if (index == null || index == undefined) { index = t.tabs("getSelectedIndex"); }
        return t.tabs("closeLeftClosable", index);
    };

    window.mainpage.mainTabs.closeRightTabs = function (index) {
        var t = $(mainTabs);
        if (index == null || index == undefined) { index = t.tabs("getSelectedIndex"); }
        return t.tabs("closeRightClosable", index);
    };

    window.mainpage.mainTabs.closeAllTabs = function () {
        return $(mainTabs).tabs("closeAllClosable");
    };
    
    // 执行
//    window.onbeforeunload = function () { return "您确定要退出本程序？"; };
    window.mainpage.bindToolbarButtonEvent();
    window.mainpage.bindMainTabsButtonEvent();
});