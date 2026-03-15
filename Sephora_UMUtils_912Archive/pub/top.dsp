

<HTML>
  <HEAD>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="-1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <LINK REL="stylesheet" TYPE="text/css" HREF="webMethods.css">
  </HEAD>  
  
  <BODY  class="topbar" topmargin="0" leftmargin="0" marginwidth="0" marginheight="0">
  %invoke wm.server.query:getServerInstanceName%
    <table border=0 cellspacing=0 cellpadding=0 height=100% width=100%>
      <tr>
         <td>
          <TABLE width=100% CELLSPACING=0 BORDER=0>
            <TR>
              <TD nowrap class="toptitle" width="100%">%value $host%::Integration Server:: %value instancename encode(html)% :: Sephora_UMUtils</TD>
              %invoke wm.server.query:getCurrentUser%
              <TD nowrap bgcolor="FFFFFF">%value  username%</TD>
              %endinvoke%
            </TR>
          </TABLE>
        </td>
      </tr>
      <tr height=100%><td></td></tr>
    </table>
  %endinvoke%
  </BODY>
</HTML>
