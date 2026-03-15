
<html>
  %scope param(packageName='Sephora_UMUtils')%
  <head>
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta http-equiv="Expires" CONTENT="-1">
    <link rel="stylesheet" type="text/css" href="webMethods.css">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <script src="menu.js.txt"></script>
    <style>body { border-top: 1px solid #97A6CB; }</style>
  </head>
  
  <body class="menu" onload="initMenu('GeneralInfo');">
    <P>
    <table WIDTH="100%" cellspacing=0 cellpadding=1 border=0>
    
      <!-- Subtitle -->
      <tr><td class="menusection-Server"><img src="images/blank.gif" width="4" height="1" border="0">Sephora UM Utilities</td></tr>
      
      <!-- General Info -->
      %scope param(itemName='GeneralInfo')%
      <TR>
        <TD id="%value itemName%" class="menuitem" onmouseover="menuMouseOver(this, '%value itemName%');" onmouseout="menuMouseOut(this);" onclick="document.all['a%value itemName%'].click();">
          <nobr>
            <img valign="middle" src="images/blank.gif" width="4" height="1" border="0">
            <img valign="middle" id="%value itemName%" name="%value itemName%" src="images/blank.gif" height="8" width="8" border="0">
            <A id="a%value itemName%" TARGET="body" HREF="info.dsp?packageName=Sephora_UMUtils" onclick="menuMove('%value itemName%', 'body'); return true;">
            <span class="menuitemspane">General Info</span></a>
          </nobr>
        </TD>
      </TR>
      %endscope%
      
      <!-- Queue/Topic Monitor -->
      %scope param(itemName='QueueTopicMonitor')%
      <TR>
        <TD id="%value itemName%" class="menuitem" onmouseover="menuMouseOver(this, '%value itemName%');" onmouseout="menuMouseOut(this);" onclick="document.all['a%value itemName%'].click();">
          <nobr>
            <img valign="middle" src="images/blank.gif" width="4" height="1" border="0">
            <img valign="middle" id="%value itemName%" name="%value itemName%" src="images/blank.gif" height="8" width="8" border="0">
            <A id="a%value itemName%" TARGET="body" HREF="topicQueueMonitor.dsp" onclick="menuMove('%value itemName%', 'body'); return true;">
            <span class="menuitemspane">Queue/Topic Monitor</span></a>
          </nobr>
        </TD>
      </TR>
      %endscope%

      
      
      


	  
    </table>
  </body>
  %endscope%
</html>