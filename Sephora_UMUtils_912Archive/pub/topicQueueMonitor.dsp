
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
    								
    								                                
    								                                
    								                                if(v=="submitButton")
    														 {
    															var value1=document.getElementById('destinations').value;
    															var value2=document.getElementById('rname').value;
    															if(value1=="" || value2=="")
    															{
    																alert("Please enter both values");
    															}
    															else
    															{
    																
    																document.getElementById(formId).action="topicQueueDetails.dsp";
    																document.getElementById(formId).submit();
    															}
    								                                }
    			                 					 }
    							
    							
    							
    				</script>
		
  </HEAD>

  <BODY bgcolor="#E7EBEF">
    <CENTER><H1>Welcome To The Topics/Queues Input Page .</H1></CENTER>

<form name="inputForm" id="inputForm" >
<input type="hidden" name="manualInput" id="manualInput" value="true"/>
<table>
	
	<tr>
		<td class="heading"><u><b>Input UM Realm URL<u><b></td>
	</tr>
	
			<tr>
				<td>
					Sample Entry --> nsp://10.x.y.z:9000
				</td>
	</tr>
	<tr>
				<td>
					
				</td>
	</tr>
	<tr>
		
		%ifvar rname%
		<td><textarea style="background-color:#ffffff; resize:none;" name="rname" id="rname" rows="1" cols="100" wrap="off">%value rname%</textarea></td>
		%else%
		<td><textarea style="background-color:#ffffff; resize:none;" name="rname" id="rname" rows="1" cols="100" wrap="off"></textarea></td>
		%endifvar%
	</tr>
	<tr>
		<td></td>
	</tr>
	<tr>
			<td class="heading"><u><b>Input Topic/Queue Names</b></u></td>
		</tr>
		<tr>
		<td>
		The entries should be separated by ",". Sample entry, </td>
		</tr>
		<tr>
			<td>
				queueName1,topicName1,topicName2,queueName2
			</td>
	</tr>
	<tr>
		%ifvar destinations%
		<td><textarea style="background-color:#ffffff; resize:none;" name="destinations" id="destinations" rows="20" cols="100" wrap="off">%value destinations%</textarea></td>
		%else%
		<td><textarea style="background-color:#ffffff; resize:none;" name="destinations" id="destinations" rows="20" cols="100" wrap="off"></textarea></td>
		%endifvar%
	</tr>
	<tr>
									<td>
									
										<table width="100%">
											<tr>
												<td width="70%">
												<!--<A HREF="serverAliasPort.dsp"><b>[<<< Back <<<]</b></A>-->
												<!--<input type="image" src="images/back-button1.png" name="backButton" id="backButton" width="85" height="20" value="back" onclick="getDispData(this);"/>-->
												</td>
	
	
												<td idth="30%">
													<input type="image" src="images/submit-button.png" align="right" name="submitButton" id="submitButton" width="85" height="20" value="Submit" onclick="getDispData(this);"/>
													
	
												</td>
											</tr>
										</table>
									</td>
		
							</tr>
							
</table>
</form>
</BODY>

</HTML>