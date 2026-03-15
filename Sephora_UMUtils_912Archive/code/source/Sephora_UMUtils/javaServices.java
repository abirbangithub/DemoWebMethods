package Sephora_UMUtils;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-04-25 04:23:13 EDT
// -----( ON-HOST: ctsusbrwsagcoe.cts.com

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import com.pcbsys.nirvana.client.*;
import com.pcbsys.nirvana.nAdminAPI.*;
import com.pcbsys.nirvana.nAdmin.*;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.pcbsys.nirvana.nAdminAPI.nRealmNode;
import com.pcbsys.nirvana.client.nChannel;
import com.pcbsys.nirvana.nAdminAPI.nLeafNode;
// --- <<IS-END-IMPORTS>> ---

public final class javaServices

{
	// ---( internal utility methods )---

	final static javaServices _instance = new javaServices();

	static javaServices _newInstance() { return new javaServices(); }

	static javaServices _cast(Object o) { return (javaServices)o; }

	// ---( server methods )---




	public static final void getChannelEventCount (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getChannelEventCount)>> ---
		// @sigtype java 3.5
		// [i] field:0:required destinationName
		// [i] field:0:required realmUrl
		// [o] recref:1:required channelEventCount Sephora_UMUtils.docs:channelEventCount
		// first try to find the channel and create a nChannel object for the same
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	destinationName = IDataUtil.getString( pipelineCursor, "destinationName" );
		String	realmUrl = IDataUtil.getString( pipelineCursor, "realmUrl" );
		pipelineCursor.destroy();
		
		 String numberOfEvents="";  
		 Integer numberOfReaders=0;
		 IData[] channelEventCount;
		 IDataCursor pipelineCursor_1 = pipeline.getCursor();
		try{
			
		 String[] RNAME={realmUrl};
		 nSessionAttributes nsa = new nSessionAttributes(RNAME);  
		 
		 nSession mySession=nSessionFactory.create(nsa);  
		 
		 mySession.init();  
		 
		 nChannelAttributes cattrib = new nChannelAttributes();  
		 cattrib.setName(destinationName); 
		 
		  
		   // for channel
		 nChannel myChannel=mySession.findChannel(cattrib);  
		 
		nNamedObject nno[]=myChannel.getNamedObjects();
		
		channelEventCount = new IData[nno.length];
		
		for(int i=0;i<nno.length;i++)
		{
			channelEventCount[i] = IDataFactory.create();
			IDataCursor channelEventCountCursor = channelEventCount[i].getCursor();
			IDataUtil.put( channelEventCountCursor, "topicName", myChannel.getName() );
			IDataUtil.put( channelEventCountCursor, "durableSubscriberName", nno[i].getName() );
			Long lt=nno[i].getSharedNamedObjectOutstandingEvents ();
			IDataUtil.put( channelEventCountCursor, "eventCount", lt.toString() );
			Long et=nno[i].getEID();
			IDataUtil.put( channelEventCountCursor, "lastEventID", et.toString() );
			channelEventCountCursor.destroy();
		}
		 
		
			
				
		 	}  
		 catch(Exception e){  
			 		throw new ServiceException(e.toString());  
		 	}   
		 
		// pipeline
			
			IDataUtil.put( pipelineCursor_1, "channelEventCount", channelEventCount );
			
			pipelineCursor_1.destroy();
			
			
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void getQueueEventCount (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getQueueEventCount)>> ---
		// @sigtype java 3.5
		// [i] field:0:required destinationName
		// [i] field:0:required realmUrl
		// [o] field:0:required currentEventCount
		// [o] field:0:required lastEventID
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	destinationName = IDataUtil.getString( pipelineCursor, "destinationName" );
		String	realmUrl = IDataUtil.getString( pipelineCursor, "realmUrl" );
		pipelineCursor.destroy();
		
		 Integer numberOfEvents=0;  
		 Integer numberOfReaders=0;
		 String lastEid="";
		
		try{
			
		 String[] RNAME={realmUrl};
		 nSessionAttributes nsa = new nSessionAttributes(RNAME);  
		 nSession mySession=nSessionFactory.create(nsa);  
		 mySession.init();  
		 nChannelAttributes cattrib = new nChannelAttributes();  
		 cattrib.setName(destinationName);  
		  
		   // for queue
			 	nQueue myQueue=mySession.findQueue(cattrib);  
				nQueueDetails nqd = myQueue.getDetails();  
				numberOfEvents=nqd.getNoOfEvents();  
				numberOfReaders=nqd.getNoOfReaders();  
				
				nRealmNode realm = new nRealmNode(nsa);
				nNode nn=realm.findNode(destinationName);
				
				if (nn instanceof nLeafNode) 
				{
					nLeafNode leaf = (nLeafNode)nn;
					Long lt=leaf.getLastEID ();
					lastEid=lt.toString();
				}
			
			
				
		 	}  
		 catch(Exception e){  
			 		throw new ServiceException(e.toString());  
		 	}   
		 
		// pipeline
			IDataCursor pipelineCursor_1 = pipeline.getCursor();
			
			IDataUtil.put( pipelineCursor_1, "currentEventCount", numberOfEvents.toString() );
			IDataUtil.put( pipelineCursor_1, "lastEventID", lastEid );
			pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void isTopicOrQueue (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(isTopicOrQueue)>> ---
		// @sigtype java 3.5
		// [i] field:0:required realmUrl
		// [i] field:0:required destinationName
		// [o] field:0:required topicFlag
		// [o] field:0:required queueFlag
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	realmUrl = IDataUtil.getString( pipelineCursor, "realmUrl" );
			String	destinationName = IDataUtil.getString( pipelineCursor, "destinationName" );
		pipelineCursor.destroy();
		
		String topicFlag="";
		String queueFlag="";
		
		try
		{
			String[] RNAME={realmUrl}; 
			nSessionAttributes nsa=new nSessionAttributes(RNAME); 
			nRealmNode realm = new nRealmNode(nsa);
			
			nNode nn=realm.findNode(destinationName);
			
			if (nn instanceof nLeafNode) 
			{
				nLeafNode leaf = (nLeafNode)nn;
				if (leaf.isChannel()) 
				{
					topicFlag="true";
					queueFlag="false";
				} 
				else if (leaf.isQueue()) 
				{
					queueFlag="true";
					topicFlag="false";
				}
				
			}
		}
		catch(Exception e)
		{
			throw new ServiceException(e.toString());
		}
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "topicFlag", topicFlag );
		IDataUtil.put( pipelineCursor_1, "queueFlag", queueFlag );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void listTopicQueues (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(listTopicQueues)>> ---
		// @sigtype java 3.5
		// [i] field:0:required realmUrl
		// [o] field:1:required topics
		// [o] field:1:required queues
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	realmUrl = IDataUtil.getString( pipelineCursor, "realmUrl" );
		pipelineCursor.destroy();
		
		String topics[]={""};
		String queues[]={""};
		String errorMsg="";
		
		StringBuilder tp=new StringBuilder("");
		StringBuilder qq=new StringBuilder("");
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		try
		{
			String[] RNAME={realmUrl}; 
			nSessionAttributes nsa=new nSessionAttributes(RNAME); 
			nRealmNode realm = new nRealmNode(nsa);
			
			Enumeration<String> children=realm.getNodes();
			
			
			while (children.hasMoreElements()) 
				{
					
				
					//nNode child = (nNode)children.nextElement();
					Object child=children.nextElement();
					//nNode child = realm.findNode(children.nextElement());
					if (child instanceof nLeafNode) 
					{
						nLeafNode leaf = (nLeafNode)child;
						if (leaf.isChannel()) 
						{
							tp.append(leaf.getName());
							tp.append("\n");
							
						} 
						else if (leaf.isQueue()) 
						{
							qq.append(leaf.getName());
							qq.append("\n");
							
						}
					}
				}
		}
		catch(Exception e)
		{
			errorMsg=e.toString();
		}
		topics=tp.toString().split("\n");
		queues=qq.toString().split("\n");
		// pipeline
		//IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
		IDataUtil.put( pipelineCursor_1, "topics", topics );
		
		IDataUtil.put( pipelineCursor_1, "queues", queues );
		IDataUtil.put( pipelineCursor_1, "errorMsg", errorMsg );
		
		
		
		
		pipelineCursor_1.destroy();
		
		 
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void listTopicQueues_functionCall (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(listTopicQueues_functionCall)>> ---
		// @sigtype java 3.5
		// [i] field:0:required realmUrl
		// [o] field:1:required topics
		// [o] field:1:required queues
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	realmUrl = IDataUtil.getString( pipelineCursor, "realmUrl" );
		pipelineCursor.destroy();
		
		String topics[]={""};
		String queues[]={""};
		String errorMsg="";
		
		StringBuilder tp=new StringBuilder("");
		StringBuilder qq=new StringBuilder("");
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		try
		{
			String[] RNAME={realmUrl}; 
			nSessionAttributes nsa=new nSessionAttributes(RNAME); 
			nRealmNode realm = new nRealmNode(nsa);
			
			
			nContainer nc=(nContainer)realm;
			
			topics=searchTopics(nc);
			
			queues=searchQueues(nc);
			 
			
				
		}
		catch(Exception e)
		{
			errorMsg=e.toString();
		}
		
		// pipeline
		//IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
		IDataUtil.put( pipelineCursor_1, "topics", topics );
		
		IDataUtil.put( pipelineCursor_1, "queues", queues );
		IDataUtil.put( pipelineCursor_1, "errorMsg", errorMsg );
		
		
		
		
		
		pipelineCursor_1.destroy();
		
		 
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void purgeChannelEvents (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(purgeChannelEvents)>> ---
		// @sigtype java 3.5
		// [i] field:0:required destinationName
		// [i] field:0:required realmUrl
		// [o] field:0:required currentEventCount
		// first try to find the channel and create a nChannel object for the same
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	destinationName = IDataUtil.getString( pipelineCursor, "destinationName" );
		String	realmUrl = IDataUtil.getString( pipelineCursor, "realmUrl" );
		pipelineCursor.destroy();
		
		 String numberOfEvents="";  
		 Integer numberOfReaders=0;
		 IDataCursor pipelineCursor_1 = pipeline.getCursor();
		try{
			
		 String[] RNAME={realmUrl};
		 nSessionAttributes nsa = new nSessionAttributes(RNAME);  
		 
		 nSession mySession=nSessionFactory.create(nsa);  
		 
		 mySession.init();  
		 
		 nChannelAttributes cattrib = new nChannelAttributes();  
		 cattrib.setName(destinationName); 
		 
		  
		   // for channel
		 nChannel myChannel=mySession.findChannel(cattrib);  
		 
		nNamedObject nno[]=myChannel.getNamedObjects();
		 
		Long lt=nno[0].getSharedNamedObjectOutstandingEvents ();
		 
		
		numberOfEvents=lt.toString();
		
		long dt=myChannel.getLastEID ();
		long i=0;
		myChannel.purgeEvents(i,dt);
			
				
		 	}  
		 catch(Exception e){  
			 		throw new ServiceException(e.toString());  
		 	}   
		 
		// pipeline
			
			
			IDataUtil.put( pipelineCursor_1, "purgedEventCount", numberOfEvents );
			pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void purgeQueueEvents (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(purgeQueueEvents)>> ---
		// @sigtype java 3.5
		// [i] field:0:required destinationName
		// [i] field:0:required realmUrl
		// [o] field:0:required currentEventCount
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	destinationName = IDataUtil.getString( pipelineCursor, "destinationName" );
		String	realmUrl = IDataUtil.getString( pipelineCursor, "realmUrl" );
		pipelineCursor.destroy();
		
		 Integer numberOfEvents=0;  
		 Integer numberOfReaders=0;
		
		try{
			
		 String[] RNAME={realmUrl};
		 nSessionAttributes nsa = new nSessionAttributes(RNAME);  
		 nSession mySession=nSessionFactory.create(nsa);  
		 mySession.init();  
		 nChannelAttributes cattrib = new nChannelAttributes();  
		 cattrib.setName(destinationName);  
		  
		   // for queue
			 	nQueue myQueue=mySession.findQueue(cattrib);  
				nQueueDetails nqd = myQueue.getDetails();  
				numberOfEvents=nqd.getNoOfEvents();  
				numberOfReaders=nqd.getNoOfReaders();  
				myQueue.purge();
			
				
		 	}  
		 catch(Exception e){  
			 		throw new ServiceException(e.toString());  
		 	}   
		 
		// pipeline
			IDataCursor pipelineCursor_1 = pipeline.getCursor();
			
			IDataUtil.put( pipelineCursor_1, "purgedEventCount", numberOfEvents.toString() );
			pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	static String arrT[]={""};
	static StringBuilder sbT=new StringBuilder("");
	
	static String arrQ[]={""};
	static StringBuilder sbQ=new StringBuilder("");
	
	public static String[] searchQueues(nContainer container) 
	{
		Enumeration children = container.getNodes(); 
		//String arr[]={""};
		//StringBuilder sb=new StringBuilder("");
		while (children.hasMoreElements()) { 
			nNode child = (nNode)children.nextElement(); 
			if (child instanceof nContainer) { 
					searchQueues((nContainer)child); 
			} else if (child instanceof nLeafNode) { 
					nLeafNode leaf = (nLeafNode)child; 
					if (leaf.isQueue()) { 
							sbQ.append(leaf.getName());
							sbQ.append("\n");
					} 
					} 
			} 
		arrQ=sbQ.toString().split("\n");
		return arrQ;
	} 
	
	
	
	
	public static String[] searchTopics(nContainer container) 
	{
		Enumeration children = container.getNodes(); 
		//String arr[]={""};
		//StringBuilder sb=new StringBuilder("");
		while (children.hasMoreElements()) { 
			nNode child = (nNode)children.nextElement(); 
			if (child instanceof nContainer) { 
					searchTopics((nContainer)child); 
			} else if (child instanceof nLeafNode) { 
					nLeafNode leaf = (nLeafNode)child; 
					if (leaf.isChannel()) { 
							sbT.append(leaf.getName());
							sbT.append("\n");
					} 
					} 
			} 
		arrT=sbT.toString().split("\n");
		return arrT;
	} 
	
	 
	// --- <<IS-END-SHARED>> ---
}

