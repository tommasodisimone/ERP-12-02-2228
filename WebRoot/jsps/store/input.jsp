<%-- input.jsp.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- css block --%>
<link href="../../css/index.css" rel="stylesheet" type="text/css" />
<div styleclass="content-right">
	<div styleclass="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span styleclass="page_title">仓库管理</span>
		</div>
	</div>
	<div styleclass="content-text">
		<div styleclass="square-order">
			<form action="list.jsp" method="post">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">仓库名称</td>
				      <td width="32%">
				      	<input type="text" size="25"/>
				      </td>
				      <td width="18%" align="center">仓库地址</td>
				      <td width="32%">
				      	<input type="text" size="25"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">管理员</td>
				      <td width="32%">

						
													
								<optimyth:textOptionsList
								property="WIDTH:190PX"

								value="-1"
								value="1"
								value="2"
							 />
				      </td>
				      <td width="18%" align="center">最大容积</td>
				      <td width="32%">
				      	<input type="text"size="22"/>&nbsp;米<sup>3</sup>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			<div styleclass="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:document.forms[0].submit()"><img src="../../images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="../../images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="../../images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div styleclass="content-bbg"><img src="../../images/content_bbg.jpg" /></div>
</div>
<%-- end of input.jsp --%>