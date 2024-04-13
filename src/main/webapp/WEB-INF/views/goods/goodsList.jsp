<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.shoppingmall.domain.dto.goods.GoodsDTO"%>

<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<table align="center" width="710" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td height="1" colspan="8" bgcolor="CECECE"></td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<%
						List<GoodsDTO> goodsList = (List<GoodsDTO>) request.getAttribute("goodsList");
						if(goodsList != null) {
							for(int i = 0; i < goodsList.size(); i++) {
								GoodsDTO dto = goodsList.get(i);
					%>
					<td>
						<table style='padding: 15px'>
							<tr>
								<td><a href="/goods/detail?gCode=<%=dto.getGCode()%>"> <img
										src="/images/items/<%=dto.getGImage()%>.gif" border="0" align="center" width="200">
								</a></td>
							</tr>
							<tr>
								<td height="10">
							</tr>
							<tr>
								<td class="td_default" align="center"><a class="a_black"
																		 href="/goods/detail?gCode=<%=dto.getGCode()%>"> <%=dto.getGName()%> <br>
								</a> <font color="gray"> -------------------- </font></td>
							</tr>
							<tr>
								<td height="10">
							</tr>
							<tr>
								<td class="td_gray" align="center"><%=dto.getGContent()%></td>
							</tr>
							<tr>
								<td height="10">
							</tr>
							<tr>
								<td class="td_red" align="center"><font color="red"><strong>
									<%=dto.getGPrice()%></strong></font></td>
							</tr>
						</table>
					</td>
					<%
								// 한 줄에 4개씩 표시 후 줄바꿈
								if((i + 1) % 4 == 0) {
									out.println("</tr><tr><td height='10'></td></tr><tr>");
								}
							}
						}
					%>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10">
	</tr>
</table>
