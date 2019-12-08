<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 
<html>
<head>
<link rel="stylesheet" href="./static/css/home.css" />
<script type="text/javascript" src="./static/js/home.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理系统</title>

</head>
<body>
	<div id="mainLayout" class="easyui-layout hidden" data-options="fit: true">
		<div id="northPanel" data-options="region: 'north', border: false, collapsed:false" style="height: 80px; overflow: hidden;">
			<div id="topbar" class="top-bar">
				<div class="top-bar-left">
					<h1 style="margin-left: 10px; margin-top: 10px;color: #fff"><span style="color: white">管理系统</span></h1>
				</div>
				<div class="top-bar-right">
					<div id="timerSpan"></div>
	                <div id="themeSpan">
	                    <a id="btnHideNorth" class="easyui-linkbutton" data-options="plain: true, iconCls: 'layout-button-up'"></a>
	                </div>
				</div>
			</div>
			<div id="toolbar"   class="custom-panel-header top-toolbar" >
				<div id="infobar">
					<span class="icon-hamburg-user"
						style="padding-left: 25px; background-position: left center;">
						大侠，你好
					</span>
				</div>
	
				<div id="buttonbar">
<!-- 					<span>更换皮肤：</span> <select id="themeSelector"></select>  -->
					<a href="javascript:void(0);" class="easyui-menubutton"
						data-options="menu:'#layout_north_set'" iconCls="icon-standard-cog">系统</a>
					<div id="layout_north_set">
						<div id="btnFullScreen" data-options="iconCls:'key'">全屏切换</div>
						<div id="btnExit" data-options="iconCls:'logout'">退出系统</div>
					</div>
					<a id="btnShowNorth" class="easyui-linkbutton"
						data-options="plain: true, iconCls: 'layout-button-down'"
						style="display: none;"></a>
				</div>
			</div>
		</div>
		<div data-options="region: 'west', title: '菜单导航栏', iconCls: 'icon-standard-map', split: true, minWidth: 200, maxWidth: 400" style="width: 220px; padding: 1px;">
			<div id="myMenu" class="easyui-accordion" data-options="fit:true,border:false">
				<c:forEach items="${sessionScope.parentMenus}" var="pMenu">
					<div title="${pMenu.name}" data-options="border:false,iconCls:'${pMenu.icon}'" style="padding:5px">
						<div>
							<c:forEach items="${sessionScope.menuMap.get(pMenu.id)}" var="cMenu">
								<a id="btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'${cMenu.icon}'" style="width:98%;margin-bottom:5px;" onclick="window.mainpage.mainTabs.addModule('${cMenu.name}','${cMenu.url }','${cMenu.icon }')">${cMenu.name}</a>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		
		<div data-options="region: 'center'">
            <div id="mainTabs_tools" class="tabs-tool">
                <table>
                    <tr>
                        <td><a id="mainTabs_jumpHome" class="easyui-linkbutton easyui-tooltip" title="跳转至主页选项卡" data-options="plain: true, iconCls: 'icon-hamburg-home'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
						<td><a id="mainTabs_toggleAll" class="easyui-linkbutton easyui-tooltip" title="最大化" data-options="plain: true, iconCls: 'icon-standard-arrow-out'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_refTab" class="easyui-linkbutton easyui-tooltip" title="刷新当前选中的选项卡" data-options="plain: true, iconCls: 'icon-standard-arrow-refresh'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_closeTab" class="easyui-linkbutton easyui-tooltip" title="关闭当前选中的选项卡" data-options="plain: true, iconCls: 'icon-standard-application-form-delete'"></a></td>
                    </tr>
                </table>
            </div>
            <div id="mainTabs" class="easyui-tabs" data-options="fit: true, border: false, showOption: true, enableNewTabMenu: true, tools: '#mainTabs_tools', enableJumpTabMenu: true">
                <div id="homePanel" data-options="title: '主页', iconCls: 'icon-hamburg-home'">
                    <div class="easyui-layout" data-options="fit: true">
                        <div data-options="region: 'north', split: false, border: false" style="height: 33px;">
                           	
                        </div>
                        <div data-options="region: 'center', border: false" style="overflow: hidden;">
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div data-options="region: 'east', title: '日历', iconCls: 'icon-standard-date', split: true,collapsed: true, minWidth: 160, maxWidth: 500" style="width: 220px;">
            <div id="eastLayout" class="easyui-layout" data-options="fit: true">
                <div data-options="region: 'north', split: false, border: false" style="height: 220px;">
                    <div class="easyui-calendar" data-options="fit: true, border: false"></div>
                </div>
                <div id="linkPanel" data-options="region: 'center', border: false, title: '通知', iconCls: 'icon-hamburg-link', tools: [{ iconCls: 'icon-hamburg-refresh', handler: function () { window.link.reload(); } }]">
                    
                </div>
            </div>
        </div>

        <div data-options="region: 'south', title: '关于...', iconCls: 'icon-standard-information', collapsed: true, border: false" style="height: 70px;">
            <div style="color: #4e5766; padding: 6px 0px 0px 0px; margin: 0px auto; text-align: center; font-size:12px; font-family:微软雅黑;">
                
            </div>
        </div>
	</div>
	
	<script>
	$.ajax({
		async:false,
		type:'get',
		url:"${ctx}/load.do",
		success: function(data){
			// alert("load success");
		}
	});
	
	$(".easyui-linkbutton").on("click",function(){
		$(".easyui-linkbutton").linkbutton({selected:false});
		$(this).linkbutton({selected:true});
	});
	</script>

</body>
</html>