
<HTML>
  
  <HEAD>
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta http-equiv="Expires" CONTENT="-1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    
    <script type="text/javascript">
        							
        		function getDispData(element)
				{
					var v=element.id;
					var parent=element.parentNode;
					while(parent.nodeName!="FORM")
						{
							parent=parent.parentNode;
						}
					var formId=parent.id;
					alert(v);
									        								                                
					if(v=="bckButton")
						{   
							var manIp=document.getElementById("manualInput").value
							var tmp=document.getElementById("input_data").value;
							tmp=tmp.replace("\","");
							document.getElementById("input_data").value=tmp;
							
							alert("inside back button");
							
							if(manIp=="true")
								{
									document.getElementById("manualInput").value="true";
									document.getElementById("input_data").value=tmp;
									document.getElementById(formId).action="schedulerInput.dsp";
									document.getElementById(formId).submit();
								}
							else
								{
									document.getElementById(formId).action="serverAliasScheduler.dsp";
									document.getElementById(formId).submit();
						 		}
									        																				                                                
						 }				        								                                
					
									        								                                
        			  }
        							
        							
        							
    	</script>
  </HEAD>

  <BODY bgcolor="#E7EBEF">
    <CENTER><H1>Welcome To The Topic/Queue Details Page .</H1></CENTER>


<form name="monitorForm" id="monitorForm" >



<input type="hidden" name="rname" id="rname" value=%value rname%>
<input type="hidden" name="destinations" id="destinations" value=%value destinations%>

%ifvar queueAction equals('queuePurge')%

%invoke Sephora_UMUtils.flowServices:queuePurgeWrapper%
%endinvoke%

%endifvar%

%ifvar topicAction equals('topicPurge')%

%invoke Sephora_UMUtils.flowServices:channelPurgeWrapper%
%endinvoke%

%endifvar%
	

%invoke Sephora_UMUtils.flowServices:wrapperMain%
%ifvar errorMessage%
<table width="100%" border="1" >
				<tr>
				<td style="text-align:left;color:#000099;font-weight:bold;width:10%">Error Message : </td>
				<td style="text-align:left;color:#990000;width:90%">%value errorMessage%</td>
				</tr>
		</table>
%else%
	%ifvar destinationDetails/topicDetails%
		<table width="40%">
				<tr>
				<td style="text-align:left;color:#000099;font-weight:bold">Topic Details</td>
				</tr>
		</table>
		<table width="100%" border="1">
			<tr>
				<td width="30%" style="text-align:center;color:#990000"><b>Topic Name</b></td>
				<td width="30%" style="text-align:center;color:#990000"><b>Durable Subscriber Name</b></td>
				<td width="30%" style="text-align:center;color:#990000"><b>Current Event Count</b></td>
				
				<td width="10%" style="text-align:center;color:#990000"><b>Purge Topic</b></td>
			</tr>
			%loop destinationDetails/topicDetails%
			%rename /rname rname -copy%
			%rename /destinations destinations -copy%
			
				<tr>
								<td width="30%" style="text-align:left;color:#0000FF">%value topicName%</td>
								<td width="30%" style="text-align:left;color:#0000FF">%value durableSubscriberName%</td>
								<td width="30%" style="text-align:center;color:#0000FF">%value eventCount%</td>
								
								<td width="10%" style="text-align:center;color:#0000FF"><A HREF="topicQueueDetails.dsp?topicAction=topicPurge&amp;topicName=%value topicName%&amp;rname=%value rname%&amp;destinations=%value destinations%">Purge</A></td>
			</tr>
			%end loop%
			
		</table>
	%endifvar%
	%ifvar destinationDetails/queueDetails%
			<table width="40%">
					<tr>
					<td style="text-align:left;color:#000099;font-weight:bold">Queue Details</td>
					</tr>
			</table>
			<table width="100%" border="1">
				<tr>
					<td width="50%" style="text-align:center;color:#990000"><b>Queue Name</b></td>
					
					<td width="40%" style="text-align:center;color:#990000"><b>Current Event Count</b></td>
					
					<td width="10%" style="text-align:center;color:#990000"><b>Purge Queue</b></td>
				</tr>
				%loop destinationDetails/queueDetails%
				%rename /rname rname -copy%
				%rename /destinations destinations -copy%
					<tr>
									<td width="50%" style="text-align:left;color:#0000FF">%value queueName%</td>
									
									<td width="40%" style="text-align:center;color:#0000FF">%value eventCount%</td>
									
									<td width="10%" style="text-align:center;color:#0000FF"><A HREF="topicQueueDetails.dsp?queueAction=queuePurge&amp;queueName=%value queueName%&amp;rname=%value rname%&amp;destinations=%value destinations%">Purge</A></td>
				</tr>
				%end loop%
				
			</table>
	%endifvar%
	%ifvar destinationDetails/undefinedList%
			<table width="40%">
					<tr>
					<td style="text-align:left;color:#000099;font-weight:bold">Undefined Topics/Queues</td>
					</tr>
			</table>
			<table width="100%" border="1">
				<tr>
					<td width="50%" style="text-align:center;color:#990000"><b>Topic/Queue Name</b></td>
					<td width="50%" style="text-align:center;color:#990000"><b>Status</b></td>
					
				</tr>
				%loop destinationDetails/undefinedList%
				%rename /rname rname -copy%
				%rename /destinations destinations -copy%
				
					<tr>
									<td width="50%" style="text-align:left;color:#0000FF">%value destinationNotFound%</td>
									<td width="50%" style="text-align:left;color:#0000FF">Not Found in Realm : %value rname%</td>
									
				</tr>
				%end loop%
				
			</table>
	%endifvar%
%endifvar%
%end invoke%	
<table>
	<tr>
		<td></td>
	</tr>
	<tr>
			<td></td>
	</tr>
	<tr>
		
			
			<!--<A HREF="serverAliasScheduler.dsp">Back</A>-->
			%rename /rname rname -copy%
			%rename /destinations destinations -copy%
			<td>
			
						<A HREF="topicQueueMonitor.dsp?rname=%value rname%&amp;destinations=%value destinations%"><b>[<<< Back <<<]</b></A>
			
			<!--<input type="image" src="images/back-button1.png" name="bckButton" id="bckButton" width="65" height="20" value="back" onclick="getDispData(this);"/>-->
			</td>
			
			
		
	</tr>
</table>
</form>
</BODY>

</HTML>